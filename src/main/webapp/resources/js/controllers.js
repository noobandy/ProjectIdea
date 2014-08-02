'use strict';

/* Controllers */

angular.module('ProjectIdeaApp.controllers', []).
controller('HomeController',function($scope,ProjectIdeaService){

	$scope.tags = [];

	ProjectIdeaService.getPublishedTagBadges().success(function(data){
		$scope.tags = data;
	});


}).
controller('PublishedProjectIdeaController',function($scope,$stateParams,ProjectIdeaService){

	$scope.activeTag = $stateParams.tag;

	$scope.projectIdeas = [];

	$scope.itemsPerPage = 10;
	$scope.currentPage = 1;
	$scope.maxSize = 5;
	$scope.totalItems = 0;

	$scope.pageChanged = function(){
		ProjectIdeaService.getPublishedProjectIdeas($scope.activeTag,$scope.currentPage,$scope.itemsPerPage).success(function(page){
			$scope.projectIdeas = page.data;
		});
	};


	ProjectIdeaService.getPublishedProjectIdeas($scope.activeTag,$scope.currentPage,$scope.itemsPerPage).success(function(page){
		$scope.projectIdeas = page.data;
		$scope.totalItems = page.totlaItems;
	});

}).
controller('MyDraftedProjectIdeaController',function($scope,ProjectIdeaService){

	$scope.tags = [];
	ProjectIdeaService.getDraftedTagBadgesOfUser().success(function(data){
		$scope.tags = data;
	});
}).
controller('MyPublishedProjectIdeaController',function($scope,ProjectIdeaService){

	$scope.tags = [];
	ProjectIdeaService.getPublishedTagBadgesOfUser().success(function(data){
		$scope.tags = data;
	});
}).
controller('UserDraftedProjectIdeaController',function($scope,$stateParams,ProjectIdeaService){

	$scope.activeTag = $stateParams.tag;

	$scope.projectIdeas = [];

	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10;
	$scope.maxSize = 5;
	$scope.numPages;

	$scope.pageChanged = function() {
		ProjectIdeaService.getDraftedProjectIdeasOfUser($scope.activeTag,$scope.currentPage,$scope.itemsPerPage).success(function(page){
			$scope.projectIdeas = page.data;
		});
	};

	ProjectIdeaService.getDraftedProjectIdeasOfUser($scope.activeTag,$scope.currentPage,$scope.itemsPerPage).success(function(page){
		$scope.projectIdeas = page.data;
		$scope.totalItems = page.totlaItems;
	});
})
.controller('NewProjectIdeaController',function($scope,ProjectIdeaService,TagService){
	$scope.alerts = [];

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};

	$scope.newProjectidea = {
			title: '',
			description: '',
			tags: []
	};

	$scope.select2Options = {
			'multiple': true,
			'simple_tags': true,
			'tags': []   // Can be empty list.
	};

	TagService.getAllTags().success(function(data){
		angular.forEach(data,function(key,value){
			$scope.select2Options.tags.push(key.tag);
		});
	});


	$scope.saveDraft = function(projectIdeaDraft){
		ProjectIdeaService.draftProjectIdea(projectIdeaDraft).success(function(data){
			$scope.newProjectidea = data;
			$scope.alerts.push({ type: 'success', msg: 'Draft Saved successfully' });
		}).error(function(data){
			$scope.alerts.push({ type: 'danger', msg: 'Failed to Save draft' });
		});
	};

}).
controller('UserPublishedProjectIdeaController',function($scope,$stateParams,ProjectIdeaService){

	$scope.activeTag = $stateParams.tag;
	$scope.projectIdeas = [];

	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10;
	$scope.maxSize = 5;

	$scope.pageChanged = function() {
		ProjectIdeaService.getPublishedProjectIdeasOfUser($scope.activeTag,$scope.currentPage,$scope.itemsPerPage).success(function(page){
			$scope.projectIdeas = page.data;
		});
	};

	ProjectIdeaService.getPublishedProjectIdeasOfUser($scope.activeTag,$scope.currentPage,$scope.itemsPerPage).success(function(page){
		$scope.projectIdeas = page.data;
		$scope.totalItems = page.totlaItems;
	});
})
.controller('ProjectIdeaController',
		function($scope, $stateParams,ProjectIdeaService) {

	$scope.projectIdeaId = $stateParams.id;

	$scope.projectIdea = {
	};

	ProjectIdeaService.getProjectIdea($scope.projectIdeaId).success(function(data){
		$scope.projectIdea = data;
	});

}).
controller('ProjectIdeaReviewController',function($scope, $stateParams,ProjectIdeaReviewService){
	$scope.projectIdeaId = $stateParams.id;

	$scope.projectIdeaReviews = [];

	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10;
	$scope.maxSize = 5;


	$scope.pageChanged = function() {
		ProjectIdeaReviewService.getProjectIdeaReviews($scope.projectIdeaId,$scope.currentPage,$scope.itemsPerPage).success(function(page){
			$scope.projectIdeaReviews = page.data;
		});
	};

	ProjectIdeaReviewService.getProjectIdeaReviews($scope.projectIdeaId,$scope.currentPage,$scope.itemsPerPage).success(function(page){
		$scope.projectIdeaReviews = page.data;
		$scope.totalItems = page.totlaItems;
	});


	$scope.newReview = {
			id:'',
			projectIdeaId:$scope.projectIdeaId,
			star:5,
			remark:''

	};

	$scope.clearReviewForm = function(){
		$scope.newReview = {
				id:'',
				projectIdeaId:$scope.projectIdeaId,
				star:5,
				remark:''

		};
	};

	$scope.hoveringOver = function(value) {
		$scope.overStar = value;
	};

	$scope.alerts = [];

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};


	$scope.addReview = function(newReview){
		ProjectIdeaReviewService.addProjectIdeaReview($scope.projectIdeaId,newReview).success(function(data){
			$scope.clearReviewForm();
			$scope.alerts.push({ type: 'success', msg: 'Review Added' });
		}).error(function(){
			$scope.alerts.push({ type: 'danger', msg: 'Failed to add review' });
		});
	};
})
.controller('LoginController',
		function($timeout, $location, $scope, AUTH_EVENTS,
				AuthService, message) {
	$scope.loginError = message;
	$scope.credentials = {
			username : '',
			password : ''
	};

	$scope.login = function() {
		var loginSuccess = function() {
			$timeout(function() {
				$location.path("/notes");
			}, 100);
		};

		var loginError = function() {
			$scope.loginError = "Bad Credentials";
		};
		AuthService.login($scope.credentials).then(loginSuccess,
				loginError);
	};
}).
controller('NavBarController',
		function($scope, AuthService, Session,$http,$modal) {
	$scope.isLoggedIn = function() {
		return AuthService.isAuthenticated();
	};

	$scope.authenticatedUser = function() {
		return Session.getAuthenticatedUser();
	};


	$http.get('user/'+Session.getAuthenticatedUser()).success(function(data){
		$scope.userProfile = data;
	});

	var UserProfileModalController = function($scope,$modalInstance,userProfile){
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
			$http.put('user/'+Session.getAuthenticatedUser()+'/updatePassword',$scope.updatePasswordCommand).success(function(data){
				$scope.clearUpdatePasswordCommand();
				$scope.alerts.push({ type: 'success', msg: 'Password updated' });
			}).error(function(){
				$scope.clearUpdatePasswordCommand();
				$scope.alerts.push({ type: 'danger', msg: 'Failed to update password' });
			});
		};
	};

	$scope.showProfile = function(){

		var modalInstance = $modal.open({
			templateUrl: 'partials/userProfile',
			controller: UserProfileModalController,
			resolve: {
				userProfile: function () {
					return $scope.userProfile;
				}
			}
		});
	};


}).
controller('ChatController',
		function($scope, ChatService, AuthService, Session) {
	$scope.messages = [];
	$scope.recentMessagesOfAuthor = [];

	ChatService.list().success(function(data) {
		$scope.messages = data;
	});

	ChatService.recentMessagesOfAuthor().success(function(data) {
		$scope.recentMessagesOfAuthor = data;
	});

});
