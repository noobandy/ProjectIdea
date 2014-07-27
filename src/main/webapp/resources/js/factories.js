'use strict';

angular.module('ProjectIdeaApp.factories', []).factory('AuthService', function ($rootScope,$http,Session) {
	var authService = {};
	authService.login = function (credentials) {
		var promise =  $http.post('j_spring_security_check?username='+credentials.username+"&password="+credentials.password);

		var success = function(data){
			Session.create(credentials.username);
		};

		return promise.then(success);
	};


	authService.logout = function(){
		var success = function(){
			Session.destroy();
		};

		var promise =  $http.get('j_spring_security_logout');

		return promise.then(success);

	};

	authService.isAuthenticated =  function () {
		return (Session.getAuthenticatedUser() !== null);
	};
	
	return authService;
});