'use strict';

/* Services */


//Demonstrate how to register services
//In this case it is a simple value service.
angular.module('ProjectIdeaApp.services', []).
value('version', '0.1').service('ChatService',function($http){
	// Gets the list of old chat messages
	this.list = function() {
		return $http.get('loadChat/chatLogs');

	};
	
	this.recentMessagesOfAuthor = function(){
		return $http.get('loadChat/recentMessagesOfAuthor');
	};

}).service('Session', function () {

	this.create = function (username) {
		sessionStorage.setItem('username',username);
	};
	this.destroy = function () {
		sessionStorage.removeItem('username');
	};

	this.getAuthenticatedUser = function(){
		return sessionStorage.getItem('username');
	};
});
