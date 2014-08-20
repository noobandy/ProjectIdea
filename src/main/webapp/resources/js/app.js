'use strict';

//Declare app level module which depends on filters, and services
angular.module(
		'ProjectIdeaApp',
		[ 'ui.router', 'ui.bootstrap','ui.select2',
		  'angularFileUpload','angularCharts','ncy-angular-breadcrumb',
		  , 'ProjectIdeaApp.filters', 'ProjectIdeaApp.services',
		  'ProjectIdeaApp.factories', 'ProjectIdeaApp.directives',
		  'ProjectIdeaApp.controllers' ]).constant('AUTH_EVENTS', {
			  loginSuccess : 'auth-login-success',
			  loginFailed : 'auth-login-failed',
			  logoutSuccess : 'auth-logout-success',
			  sessionTimeout : 'auth-session-timeout',
			  notAuthenticated : 'auth-not-authenticated',
			  notAuthorized : 'auth-not-authorized'
		  }).config(function($stateProvider, $urlRouterProvider, $httpProvider) {


			  var logsOutUserOn401 = [ '$q', '$location', function($q, $location) {
				  var success = function(response) {
					  return response;
				  };

				  var error = function(response) {
					  if (response.status === 401) {
						  //redirect them back to login page
						  $location.path('/login');

						  return $q.reject(response);
					  } else {
						  return $q.reject(response);
					  }
				  };

				  return function(promise) {
					  return promise.then(success, error);
				  };
			  } ];

			  $httpProvider.responseInterceptors.push(logsOutUserOn401);

			  $httpProvider.responseInterceptors.push('myHttpInterceptor');

			  var spinnerFunction = function spinnerFunction(data, headersGetter) {
				  $("#spinner").show();
				  return data;
			  };

			  $httpProvider.defaults.transformRequest.push(spinnerFunction);

			  $urlRouterProvider.otherwise('/home');

			  $stateProvider.state('login', {
				  url : '/login',
				  templateUrl : 'partials/login',
				  controller : 'LoginController',
				  data : {
					  isSecure : false,
					  ncyBreadcrumbSkip: true
				  },
				  resolve : {
					  message : function() {
						  return 'Please Login';
					  }
				  }
			  }).state('logout', {
				  url : '/logout',
				  templateUrl : 'partials/login',
				  controller : 'LoginController',
				  data : {
					  isSecure : false,
					  ncyBreadcrumbSkip: true
				  },
				  resolve : {
					  message : function(AuthService) {
						  AuthService.logout().then(function() {
							  return 'Logged out successfully';
						  });
					  }
				  }
			  }).state('accessDenied', {
				  url : '/accessDenied',
				  template : 'accessDenied',
				  data : {
					  isSecure : false,
					  ncyBreadcrumbSkip: true
				  }
			  }).
			  state('dashboard',{
				  url: '/dashboard',
				  templateUrl: 'partials/dashboard',
				  controller: 'DashBoardController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbLabel: 'Dashboard'
				  }
			  }).
			  state('dashboard.users',{
				  url: '/users',
				  templateUrl: 'partials/users',
				  controller: 'UserController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbLabel: 'Users',
					  ncyBreadcrumbParent: 'dashboard'
				  }
			  }).
			  state('dashboard.users.user',{
				  url: '/{username}',
				  templateUrl: 'partials/user',
				  controller: 'UserController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbParent: 'dashboard.users',
					  ncyBreadcrumbLabel: '{{user.username}}'
				  }
			  }).
			  state('dashboard.groups',{
				  url: '/groups',
				  templateUrl: 'partials/groups',
				  controller: 'GroupController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbLabel: 'Groups',
					  ncyBreadcrumbParent: 'dashboard'
				  }
			  }).
			  state('dashboard.groups.group',{
				  url: '/{groupName}',
				  templateUrl: 'partials/group',
				  controller: 'GroupController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbParent: 'dashboard.groups',
					  ncyBreadcrumbLabel: '{{group.groupName}}'
				  }
			  }).
			  state('dashboard.authorities',{
				  url: '/authorities',
				  templateUrl: 'partials/authorities',
				  controller: 'AuthorityController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbLabel: 'Authorities',
					  ncyBreadcrumbParent: 'dashboard'
				  }
			  }).
			  state('dashboard.analytics',{
				  url: '/analytics',
				  templateUrl: 'partials/analytics',
				  controller: 'AnalyticsController',
				  data: {
					  isSecure: true,
					  ncyBreadcrumbLabel: 'Analytics',
					  ncyBreadcrumbParent: 'dashboard'
				  }
			  })
			  .
			  state('chat', {
				  url : '/chat',
				  templateUrl : 'partials/chat',
				  controller : 'ChatController',
				  data : {
					  isSecure : true,
					  ncyBreadcrumbLabel: 'Chat'
				  }
			  }).
			  state("userProfile", {
				  url: "/user/{username}",
				  onEnter: ['$stateParams', '$rootScope', '$state', '$modal', '$http', function($stateParams,$rootScope, $state, $modal, $http) {
					  $modal.open({
						  templateUrl: 'partials/userProfile',
						  resolve: {
							  userProfile: function ($q){
								  var deferred = $q.defer();

								  $http.get('api/users/'+$stateParams.username).success(function(data){
									  deferred.resolve(data);
								  });
								  return deferred.promise;

							  }
						  },
						  controller: function($scope,$modalInstance,userProfile){

							  $scope.userProfile = userProfile;

							  $scope.cancel = function () {
								  $modalInstance.dismiss('cancel');
							  };

							  $scope.updatePasswordCommand = {
									  oldPassword:'',
									  newPassword:'',
									  confirmPassword:''
							  };

							  $scope.alerts = [];

							  $scope.closeAlert = function(index) {
								  $scope.alerts.splice(index, 1);
							  };

							  $scope.clearUpdatePasswordCommand = function(){
								  $scope.updatePasswordCommand = {
										  oldPassword:'',
										  newPassword:'',
										  confirmPassword:''
								  };
							  };

							  $scope.updatePassword = function(){
								  $http.put('api/users/'+$stateParams.username+'/updatePassword',$scope.updatePasswordCommand).success(function(data){
									  $scope.clearUpdatePasswordCommand();
									  $scope.alerts.push({ type: 'success', msg: 'Password updated' });
								  }).error(function(){
									  $scope.clearUpdatePasswordCommand();
									  $scope.alerts.push({ type: 'danger', msg: 'Failed to update password' });
								  });
							  };
						  }
					  }).result.then(function(result) {
						  $state.go($rootScope.previousState_name,$rootScope.previousState_params);
					  },function(result) {
						  $state.go($rootScope.previousState_name,$rootScope.previousState_params);
					  });
				  }],
				  data : {
					  isSecure : false,
				  }
			  }).
			  state('home', {
				  url : '/home?tag',
				  templateUrl : 'partials/home',
				  controller : 'HomeController',
				  data : {
					  isSecure : false,
					  ncyBreadcrumbLabel: 'Home'
				  }

			  }).state('projectIdea', {
				  url : '/projectIdea/{id}',
				  templateUrl : 'partials/projectIdea',
				  controller : 'ProjectIdeaController',
				  data : {
					  isSecure : false,
					  ncyBreadcrumbLabel: 'Project Idea {{projectIdea.title}}'
				  }

			  }).
			  state('projectIdea.reviews', {
				  url : '/reviews',
				  templateUrl : 'partials/projectIdeaReviews',
				  controller : 'ProjectIdeaReviewController',
				  data : {
					  isSecure : false,
					  ncyBreadcrumbParent: 'projectIdea',
					  ncyBreadcrumbLabel: 'Reviews'
				  }

			  })
			  .state('myProjectIdeas', {
				  abstract: true,
				  url : '/myProjectIdeas',
				  templateUrl : 'partials/myProjectIdeas',
				  data : {
					  isSecure : true,
					  ncyBreadcrumbLabel: 'My Project Ideas'
				  }
			  }).state('myProjectIdeas.drafted', {
				  url : '/drafted?tag',
				  templateUrl : 'partials/myDraftedProjectIdeas',
				  controller : 'MyDraftedProjectIdeaController',
				  data : {
					  isSecure : true,
					  ncyBreadcrumbParent: 'myProjectIdeas',
					  ncyBreadcrumbLabel: 'Draft'
				  }
			  }).state('myProjectIdeas.published', {
				  url : '/published?tag',
				  templateUrl : 'partials/myPublishedProjectIdeas',
				  controller : 'MyPublishedProjectIdeaController',
				  data : {
					  isSecure : true,
					  ncyBreadcrumbParent: 'myProjectIdeas',
					  ncyBreadcrumbLabel: 'Published'
				  }
			  }).
			  state('myProjectIdeas.new', {
				  url : '/new',
				  templateUrl : 'partials/newProjectIdea',
				  controller : 'NewProjectIdeaController',
				  data : {
					  isSecure : true,
					  ncyBreadcrumbParent: 'myProjectIdeas',
					  ncyBreadcrumbLabel: 'New'
				  }
			  }).
			  state('myProjectIdeas.edit', {
				  url : '/edit/{draftId}',
				  templateUrl : 'partials/editProjectIdea',
				  controller : 'UpdateProjectIdeaController',
				  data : {
					  isSecure : true,
					  ncyBreadcrumbParent: 'myProjectIdeas',
					  ncyBreadcrumbLabel: 'Edit'
				  }
			  });
		  }).run(
				  function($location, $rootScope, AUTH_EVENTS,
						  AuthService) {

					  $rootScope.$on('$stateChangeStart', function(event, next, current) {

						  if (next.data.isSecure) {
							  if (!AuthService.isAuthenticated()) {
								  event.preventDefault();
								  $location.path('/login').replace();
								  $rootScope.$apply();
							  }
						  }
					  });
					  
					  $rootScope.$on("$stateChangeSuccess",  function(event, toState, toParams, fromState, fromParams) {
						  // to be used for back button //won't work when page is reloaded.
						  $rootScope.previousState_name = fromState.name;
						  $rootScope.previousState_params = fromParams;
					  });

				  });