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
controller('UpdateProjectIdeaController',function($location,$scope,$stateParams,$upload,ProjectIdeaService,ProjectIdeaDocumentService,TagService){

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


	$scope.projectIdeaDocuments = [];

	$scope.select2Options = {
			'multiple': true,
			'simple_tags': true,
			'tags': []   // Can be empty list.
	};


	$scope.publishProjectIdea = function(){
		ProjectIdeaService.publishProjectIdea($scope.draftId).success(function(){
			$location.path('/myProjectIdeas/drafted').replace();
		});
	};


	ProjectIdeaService.getProjectIdea($scope.draftId).success(function(data){
		$scope.projectIdeaDraft.id = data.id;
		$scope.projectIdeaDraft.title = data.title;
		$scope.projectIdeaDraft.description = data.description;
		angular.forEach(data.tags,function(key,value){
			$scope.projectIdeaDraft.tags.push(key.tag);
		});
	});

	ProjectIdeaService.getEstimatedTime($scope.draftId).success(function(data){
		$scope.projectIdeaEstimatedTime = data;
	});

	TagService.getAllTags().success(function(data){
		angular.forEach(data,function(key,value){
			$scope.select2Options.tags.push(key.tag);
		});
	});


	ProjectIdeaDocumentService.getProjectIdeaDocuments($scope.draftId).success(function(data){
		$scope.projectIdeaDocuments = data;
	});

	$scope.deleteProjectIdeaDocument = function(projectIdeaDocument){
		ProjectIdeaDocumentService.deleteProjectIdeaDocument($scope.draftId,projectIdeaDocument.id).success(function(){
			$scope.alerts.push({ type: 'success', msg: 'Document deleted Successfully' });
			//remove deleted document from the view model
			var index = $scope.projectIdeaDocuments.indexOf(projectIdeaDocument);
			$scope.projectIdeaDocuments.splice(index, 1);
		});
	};

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
			$scope.alerts.push({ type: 'success', msg: 'Estimated Time update successfully.' });
		});
	};

	$scope.maxProgress = 100;

	$scope.onFileSelect = function($files) {
		$scope.currentProgress = 0;
		//$files: an array of files selected, each file has name, size, and type.
		for (var i = 0; i < $files.length; i++) {
			var file = $files[i];
			if(file.size <= 1000000){
				$scope.upload = $upload.upload({
					url: 'projectIdea/'+$scope.draftId+'/documents', //upload.php script, node.js route, or servlet url
					method:'POST',
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
					$scope.currentProgress = parseInt(100.0 * evt.loaded / evt.total);
				}).success(function(data, status, headers, config) {
					// file is uploaded successfully
					$scope.alerts.push({ type: 'success', msg: 'File successfully uploaded' });
					angular.forEach(data,function(key,value){
						$scope.projectIdeaDocuments.push(key);
					});
				}).error(function(data){
					$scope.alerts.push({ type: 'danger', msg: 'Failed to upload file' });
				});
				//.error(...)
				//.then(success, error, progress); 
				// access or attach event listeners to the underlying XMLHttpRequest.
				//.xhr(function(xhr){xhr.upload.addEventListener(...)})

			}else{
				$scope.alerts.push({ type: 'warning', msg: 'Only files of size less than 1MB are allowed' });
			}
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
		function($scope, $stateParams,ProjectIdeaService,ProjectIdeaDocumentService) {

	$scope.projectIdeaId = $stateParams.id;

	ProjectIdeaDocumentService.getProjectIdeaDocuments($scope.projectIdeaId).success(function(data){
		$scope.projectIdeaDocuments = data;
	});

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
controller('DashBoardController',function($scope,$stateParams,ProjectIdeaService){
	ProjectIdeaService.getPublishedTagBadges().success(function(data){
		$scope.tags = data;
	});
}).
controller('UserController',function($scope,$stateParams,$http){
	if($stateParams.username){

		$scope.username = $stateParams.username;

		$http.get('user/'+$scope.username).success(function(data){
			$scope.user = data;
		});
	}else{
		$scope.totalItems = 0;
		$scope.currentPage = 1;
		$scope.itemsPerPage = 10;
		$scope.maxSize = 5;


		$scope.pageChanged = function() {
			$scope.loadData();
		};

		$scope.loadData = function(){
			var page = {
					page: $scope.currentPage,
					itemsPerPage: $scope.itemsPerPage
			};
			return $http.get('user/users',{params: page}).success(function(page){
				$scope.totalItems = page.totalItems;
				$scope.users = page.data;
			});
		};

		$scope.loadData();
	}
}).
controller('GroupController',function($scope,$stateParams,$http){
	if($stateParams.groupName){

		$scope.groupName = $stateParams.groupName;

		$http.get('group/'+$scope.groupName).success(function(data){
			$scope.group = data;
		});
	}else{
		$scope.totalItems = 0;
		$scope.currentPage = 1;
		$scope.itemsPerPage = 10;
		$scope.maxSize = 5;


		$scope.pageChanged = function() {
			$scope.loadData();
		};

		$scope.loadData = function(){
			var page = {
					page: $scope.currentPage,
					itemsPerPage: $scope.itemsPerPage
			};
			return $http.get('group/groups',{params: page}).success(function(page){
				$scope.totalItems = page.totalItems;
				$scope.groups = page.data;
			});
		};

		$scope.loadData();
	}
}).
controller('AuthorityController',function($scope,$stateParams,$http){
	if($stateParams.authority){

		$scope.authorityName = $stateParams.authority;

		$http.get('authority/'+$scope.authorityName).success(function(data){
			$scope.authority = data;
		});
	}else{
		$scope.totalItems = 0;
		$scope.currentPage = 1;
		$scope.itemsPerPage = 10;
		$scope.maxSize = 5;


		$scope.pageChanged = function() {
			$scope.loadData();
		};

		$scope.loadData = function(){
			var page = {
					page: $scope.currentPage,
					itemsPerPage: $scope.itemsPerPage
			};
			return $http.get('authority/authorities',{params: page}).success(function(page){
				$scope.totalItems = page.totalItems;
				$scope.authorities = page.data;
			});
		};

		$scope.loadData();

		$scope.generateAuthorities = function(){
			$http.post('authority/bootstrap').success(function(){
				$scope.loadData();
			});
		};
	}
}).
controller('AnalyticsController',function($scope,$stateParams,ProjectIdeaService){


	$scope.selectedChartType = 'pie';

	$scope.chartConfig = {
			title: 'Project Ideas',
			tooltips: false,
			labels: false,
			mouseover: function() {},
			mouseout: function() {},
			click: function() {},
			legend: {
				display: true,
				//could be 'left, right'
				position: 'right'
			},
			innerRadius: 0, // applicable on pieCharts, can be a percentage like '50%'
			lineLegend: 'lineEnd' // can be also 'traditional'
	};

	$scope.chartData = {
			"series": [
			           "Project Ideass"
			           ],
			           "data": []          

	};

	ProjectIdeaService.getPublishedTagBadges().success(function(tagCounts){
		angular.forEach(tagCounts,function(key,value){
			$scope.chartData.data.push({"x":key.tag,"y":[key.count]});
		});
	});

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
