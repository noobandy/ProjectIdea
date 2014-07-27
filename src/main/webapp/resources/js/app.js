'use strict';

//Declare app level module which depends on filters, and services
angular.module(
		'ProjectIdeaApp',
		[ 'ui.router', 'ngTable', 'ui.bootstrap', 'xeditable',
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

	$urlRouterProvider.otherwise('home');

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
			isSecure : true,
			authorizedRole : 'ROLE_USER'
		}
	}).state('home', {
		url : '/home/{tag}',
		templateUrl : 'partials/home',
		controller : 'HomeController',
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

	});
}).run(
		function($location, $rootScope, editableOptions, AUTH_EVENTS,
				AuthService) {

			editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'

			$rootScope.$on('$stateChangeStart', function(event, next, current) {
				if (next.data.isSecure) {
					if (!AuthService.isAuthenticated()) {
						event.preventDefault();
						$location.path('/login');
					}
				}
			});
		});