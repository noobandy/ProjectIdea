'use strict';

//Declare app level module which depends on filters, and services
angular.module(
		'ProjectIdeaApp',
		[ 'ui.router', 'ngTable', 'ui.bootstrap', 'xeditable',,'ui.select2',
		  'angularFileUpload'
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

			  $urlRouterProvider.otherwise('/publishedProjectIdeas');

			  $stateProvider.state('login', {
				  url : '/login',
				  templateUrl : 'partials/login',
				  controller : 'LoginController',
				  data : {
					  isSecure : false
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
					  isSecure : false
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
					  isSecure : false
				  }
			  }).
			  state('dashboard',{
				  abstract: true,
				  url: '/dashboard',
				  templateUrl: 'partials/dashboard',
				  controller: 'DashBoardController',
				  data: {
					  isSecure: true
				  }
			  }).
			  state('dashboard.users',{
				  url: '/users',
				  templateUrl: 'partials/users',
				  controller: 'UserController',
				  data: {
					  isSecure: true
				  }
			  }).
			  state('dashboard.users.user',{
				  url: '/{username}',
				  templateUrl: 'partials/user',
				  controller: 'UserController',
				  data: {
					  isSecure: true
				  }
			  }).
			  state('dashboard.groups',{
				  url: '/groups',
				  templateUrl: 'partials/groups',
				  controller: 'GroupController',
				  data: {
					  isSecure: true
				  }
			  }).
			  state('dashboard.groups.group',{
				  url: '/{groupName}',
				  templateUrl: 'partials/group',
				  controller: 'GroupController',
				  data: {
					  isSecure: true
				  }
			  }).
			  state('dashboard.analytics',{
				  url: '/analytics',
				  templateUrl: 'partials/analytics',
				  controller: 'AnalyticsController',
				  data: {
					  isSecure: true
				  }
			  })
			  .
			  state('chat', {
				  url : '/chat',
				  templateUrl : 'partials/chat',
				  controller : 'ChatController',
				  data : {
					  isSecure : true
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

								  $http.get('user/'+$stateParams.username).success(function(data){
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
								  $http.put('user/'+$stateParams.username+'/updatePassword',$scope.updatePasswordCommand).success(function(data){
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
				  abstract: true,
				  url : '/publishedProjectIdeas',
				  templateUrl : 'partials/home',
				  controller : 'HomeController',
				  data : {
					  isSecure : false,
				  }

			  }).state('home.publishedProjectIdeas', {
				  url : '?tag',
				  templateUrl : 'partials/publishedProjectIdeas',
				  controller : 'PublishedProjectIdeaController',
				  data : {
					  isSecure : false,
				  }

			  }).state('projectIdea', {
				  url : '/projectIdea/{id}',
				  templateUrl : 'partials/projectIdea',
				  controller : 'ProjectIdeaController',
				  data : {
					  isSecure : false,
				  }

			  }).
			  state('projectIdea.reviews', {
				  url : '/reviews',
				  templateUrl : 'partials/projectIdeaReviews',
				  controller : 'ProjectIdeaReviewController',
				  data : {
					  isSecure : false,
				  }

			  })
			  .state('myProjectIdeas', {
				  abstract: true,
				  url : '/myProjectIdeas',
				  templateUrl : 'partials/myProjectIdeas',
				  data : {
					  isSecure : true,
				  }
			  }).state('myProjectIdeas.drafted', {
				  abstract: true,
				  url : '/drafted',
				  templateUrl : 'partials/myDraftedProjectIdeas',
				  controller : 'MyDraftedProjectIdeaController',
				  data : {
					  isSecure : true,
				  }
			  }).state('myProjectIdeas.published', {
				  abstract: true,
				  url : '/published',
				  templateUrl : 'partials/myPublishedProjectIdeas',
				  controller : 'MyPublishedProjectIdeaController',
				  data : {
					  isSecure : true,
				  }
			  }).state('myProjectIdeas.drafted.projectIdeas', {
				  url : '?tag',
				  templateUrl : 'partials/draftedProjectIdeas',
				  controller : 'UserDraftedProjectIdeaController',
				  data : {
					  isSecure : true,
				  }
			  }).
			  state('myProjectIdeas.drafted.new', {
				  url : '/new',
				  templateUrl : 'partials/newProjectIdea',
				  controller : 'NewProjectIdeaController',
				  data : {
					  isSecure : true,
				  }
			  }).
			  state('myProjectIdeas.drafted.edit', {
				  url : '/edit/{draftId}',
				  templateUrl : 'partials/editProjectIdea',
				  controller : 'UpdateProjectIdeaController',
				  data : {
					  isSecure : true,
				  }
			  })
			  .state('myProjectIdeas.published.projectIdeas', {
				  url : '?tag',
				  templateUrl : 'partials/publishedProjectIdeas',
				  controller : 'UserPublishedProjectIdeaController',
				  data : {
					  isSecure : true,
				  }
			  });
		  }).run(
				  function($location, $rootScope,$templateCache, editableOptions, AUTH_EVENTS,
						  AuthService) {

					  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'

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

					  $rootScope.$on('$viewContentLoaded', function() {
						  $templateCache.remove('partials/projectIdeaReviews');
					  });
				  });