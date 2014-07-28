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

}).service('ProjectIdeaService',function($http){
	this.getPublishedProjectIdeas = function(tag,pageNumber,itemsPerPage){
		var page = {
			tag: tag,
			page: pageNumber,
			status: 'PUBLISHED',
			itemsPerPage: itemsPerPage
		};
		
		return $http.get('projectIdea/publishedProjectIdeas',{params: page});
	};
	
this.getDraftedProjectIdeasOfUser = function(tag,start,noOfRecords){
		
	};
	
this.getPublishedProjectIdeasOfUser = function(tag,start,noOfRecords){
		
	};
	
this.getProjectIdea = function(id){
		
	};	
	
this.getPublishedTagBadges = function(){
	
};

this.getPublishedTagBadgesOfUser = function(){
	
};

this.getDraftedTagBadgesOfUser = function(){
	
};

this.draftProjectIdea = function(projectIdea){
	
};

this.publishProjectIdea = function(id){
	
};

}).service('ProjectIdeaReviewService',function($http){
	
	this.getProjectIdeaReviews = function(projectIdeaId,start,noOfRecords){
		
	};
	
	this.addProjectIdeaReview = function(projectIdeaReview){
		
	};
	
	this.deleteProjectIdeaReview = function(projectIdeaReviewId){
		
	};
	
}).
service('Session', function () {

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
