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
controller('UpdateProjectIdeaController',function($scope,$stateParams,$upload,ProjectIdeaService,TagService){

	$scope.draftId = $stateParams.draftId;

	$scope.alerts = [];

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};

	$scope.projectIdeaDraft = {
			id:'',
			title:'',
			description:'',
			tags: []
	};

	$scope.projectIdeaEstimatedTime = {
			years: '',
			months: '',
			days: ''
	};

	$scope.select2Options = {
			'multiple': true,
			'simple_tags': true,
			'tags': []   // Can be empty list.
	};


	ProjectIdeaService.getProjectIdea($scope.draftId).success(function(data){
		$scope.projectIdeaDraft.id = data.id;
		$scope.projectIdeaDraft.title = data.title;
		$scope.projectIdeaDraft.description = data.description;
		angular.forEach(data.tags,function(key,value){
			$scope.projectIdeaDraft.tags.push(key.tag);
		});
	});

	TagService.getAllTags().success(function(data){
		angular.forEach(data,function(key,value){
			$scope.select2Options.tags.push(key.tag);
		});
	});


	$scope.updateDraft = function(projectIdeaDraft){
		ProjectIdeaService.draftProjectIdea(projectIdeaDraft).success(function(data){
			$scope.newProjectidea = data;
			$scope.alerts.push({ type: 'success', msg: 'Draft Saved successfully' });
		}).error(function(data){
			$scope.alerts.push({ type: 'danger', msg: 'Failed to Save draft' });
		});
	};


	$scope.updateEstimatedTime = function(projectIdeaEstimatedTime){
		ProjectIdeaService.updateEstimatedTime($scope.draftId,projectIdeaEstimatedTime).success(function(){

		});
	};


	$scope.onFileSelect = function($files) {
		//$files: an array of files selected, each file has name, size, and type.
		for (var i = 0; i < $files.length; i++) {
			var file = $files[i];
			$scope.upload = $upload.upload({
				url: 'projectIdea/'+$scope.draftId+'/documents', //upload.php script, node.js route, or servlet url
				method:'PUT',
				headers: {'Content-Type': 'multipart/form-data'},
				//withCredentials: true,
				//data: {myObj: $scope.myModelObj},
				file: file, // or list of files ($files) for html5 only
				//fileName: 'doc.jpg' or ['1.jpg', '2.jpg', ...] // to modify the name of the file(s)
				// customize file formData name ('Content-Desposition'), server side file variable name. 
				//fileFormDataName: myFile, //or a list of names for multiple files (html5). Default is 'file' 
				// customize how data is added to formData. See #40#issuecomment-28612000 for sample code
				//formDataAppender: function(formData, key, val){}
			}).progress(function(evt) {
				console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
			}).success(function(data, status, headers, config) {
				// file is uploaded successfully
				console.log(data);
			});
			//.error(...)
			//.then(success, error, progress); 
			// access or attach event listeners to the underlying XMLHttpRequest.
			//.xhr(function(xhr){xhr.upload.addEventListener(...)})
		}
		/* alternative way of uploading, send the file binary with the file's content-type.
	       Could be used to upload files to CouchDB, imgur, etc... html5 FileReader is needed. 
	       It could also be used to monitor the progress of a normal http post/put request with large data*/
		// $scope.upload = $upload.http({...})  see 88#issuecomment-31366487 for sample code.
	};
})
.
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
			stars:0,
			remark:'',
			author:''

	};

	$scope.clearReviewForm = function(){
		$scope.newReview = {
				id:'',
				projectIdeaId:$scope.projectIdeaId,
				stars:0,
				remark:'',
				author:''

		};
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
