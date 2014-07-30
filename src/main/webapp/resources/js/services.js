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
				itemsPerPage: itemsPerPage
		};

		return $http.get('projectIdea/publishedProjectIdeas',{params: page});
	};

	this.getDraftedProjectIdeasOfUser = function(tag,pageNumber,itemsPerPage){
		var page = {
				tag: tag,
				page: pageNumber,
				itemsPerPage: itemsPerPage
		};
		return $http.get('projectIdea/draftedProjectIdeasOfUser',{params: page});
	};

	this.getPublishedProjectIdeasOfUser = function(tag,pageNumber,itemsPerPage){
		var page = {
				tag: tag,
				page: pageNumber,
				itemsPerPage: itemsPerPage
		};
		return $http.get('projectIdea/publishedProjectIdeasOfUser',{params: page});
	};

	this.getProjectIdea = function(id){
		return $http.get('projectIdea/'+id);
	};	

	this.getPublishedTagBadges = function(){
		return $http.get('projectIdea/publishedProjectIdeaTagCount');
	};

	this.getPublishedTagBadgesOfUser = function(){
		return $http.get('projectIdea/publishedProjectIdeaTagCountOfUser');
	};

	this.getDraftedTagBadgesOfUser = function(){
		return $http.get('projectIdea/draftedProjectIdeaTagCountOfUser');
	};

	this.draftProjectIdea = function(projectIdea){
		return $http.post('projectIdea/new',projectIdea);
	};

	this.publishProjectIdea = function(id){

	};

}).service('ProjectIdeaReviewService',function($http){

	this.getProjectIdeaReviews = function(projectIdeaId,pageNumber,itemsPerPage){

	};

	this.addProjectIdeaReview = function(projectIdeaReview){

	};

	this.deleteProjectIdeaReview = function(projectIdeaReviewId){

	};

}).
service('TagService',function($http){
	this.getAllTags = function(){
		return $http.get('tag/tags');
	};
})
.service('Session', function () {

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
