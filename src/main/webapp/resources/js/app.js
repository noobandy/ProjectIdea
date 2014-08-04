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
			  }).state('chat', {
				  url : '/chat',
				  templateUrl : 'partials/chat',
				  controller : 'ChatController',
				  data : {
					  isSecure : true
				  }
			  }).state('home', {
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


					  $rootScope.$on('$viewContentLoaded', function() {
						  $templateCache.remove('partials/projectIdeaReviews');
					  });
				  });