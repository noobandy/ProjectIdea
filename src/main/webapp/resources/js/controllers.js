'use strict';

/* Controllers */

angular.module('ProjectIdeaApp.controllers', []).
controller('HomeController',['$scope','$http','$stateParams',function($scope,$http,$stateParams){

	$scope.tags = [];
	$scope.projectIdeas = [];

	$scope.activeTag = $stateParams.tag;

	$scope.itemsPerPage = 10;
	$scope.currentPage = 1;
	$scope.maxSize = 5;
	$scope.totalItems = 0;


	$scope.pageChanged = function(){
		loadPublushedprojectIdeas();
	};

	function loadPublushedprojectIdeas(){
		var params = {
				page : $scope.currentPage,
				recordsPerPage: $scope.itemsPerPage ,
				tag : $scope.activeTag
		};

		$http.get('api/publishedProjectIdeas',{params:params}).success(function(data){
			$scope.totalItems = data.count;
			$scope.projectIdeas = data.items;
		}).error(function(data){
			//handle error
		});
	}

	$http.get('api/publishedProjectIdeas/tagCounts').success(function(data){
		$scope.tags = data;
	}).error(function(data){
		//handle error
	});

	//load data
	loadPublushedprojectIdeas();

}]).
controller('MyDraftedProjectIdeaController',['$scope','$http','$stateParams','AuthService',function($scope,$http,$stateParams,AuthService){

	$scope.tags = [];
	$scope.projectIdeas = [];

	$scope.activeTag = $stateParams.tag;

	$scope.author = AuthService.getAuthenticatedUser();

	$scope.itemsPerPage = 10;
	$scope.currentPage = 1;
	$scope.maxSize = 5;
	$scope.totalItems = 0;


	$scope.pageChanged = function(){
		loadMyDraftedProjectIdeas();
	};

	function loadMyDraftedProjectIdeas(){
		var params = {
				page : $scope.currentPage,
				recordsPerPage: $scope.itemsPerPage ,
				tag : $scope.activeTag,
				author: $scope.author	
		};

		$http.get('api/draftedProjectIdeas',{params:params}).success(function(data){
			$scope.totalItems = data.count;
			$scope.projectIdeas = data.items;
		}).error(function(data){
			//handle error
		});
	};

	$http.get('api/draftedProjectIdeas/tagCounts',{params:{author:$scope.author}}).success(function(data){
		$scope.tags = data;
	}).error(function(data){
		//handle error
	});

	//load data
	loadMyDraftedProjectIdeas();

}]).
controller('MyPublishedProjectIdeaController',['$scope','$http','$stateParams','AuthService',function($scope,$http,$stateParams,AuthService){

	$scope.tags = [];
	$scope.projectIdeas = [];

	$scope.activeTag = $stateParams.tag;

	$scope.author = AuthService.getAuthenticatedUser();

	$scope.itemsPerPage = 10;
	$scope.currentPage = 1;
	$scope.maxSize = 5;
	$scope.totalItems = 0;


	$scope.pageChanged = function(){
		loadMyPublishedProjectIdeas();
	};

	function loadMyPublishedProjectIdeas(){
		var params = {
				page : $scope.currentPage,
				recordsPerPage: $scope.itemsPerPage ,
				tag : $scope.activeTag,
				author: $scope.author	
		};

		$http.get('api/publishedProjectIdeas',{params:params}).success(function(data){
			$scope.totalItems = data.count;
			$scope.projectIdeas = data.items;
		}).error(function(data){
			//handle error
		});
	};

	$http.get('api/publishedProjectIdeas/tagCounts',{params:{author:$scope.author}}).success(function(data){
		$scope.tags = data;
	}).error(function(data){
		//handle error
	});

	//load data
	loadMyPublishedProjectIdeas();

}])
.controller('NewProjectIdeaController',['$scope','$http','AuthService',function($scope,$http,AuthService){
	$scope.alerts = [];

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};

	$scope.newProjectidea = {
			author: $scope.author,
			title: '',
			description: '',
			tags: [],
			years: 0,
			months: 0,
			days: 0
	};

	$scope.select2Options = {
			'multiple': true,
			'simple_tags': true,
			'tags': []   // Can be empty list.
	};

	$http.get('api/tags').success(function(data){
		angular.forEach(data,function(key,value){
			$scope.select2Options.tags.push(key.tag);
		});
	}).error(function(data){
		//handle error
	});


	$scope.saveDraft = function(projectIdeaDraft){
		$http.post('api/draftedProjectIdeas',projectIdeaDraft).success(function(data){
			$scope.newProjectidea = data;
			$scope.alerts.push({ type: 'success', msg: 'Draft Saved successfully' });
		}).error(function(data){
			$scope.alerts.push({ type: 'danger', msg: 'Failed to Save draft' });
		});
	};

}]).
controller('UpdateProjectIdeaController',['$location','$scope','$stateParams','$http','$upload','AuthService',function($location,$scope,$stateParams,$http,$upload,AuthService){

	$scope.alerts = [];

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};

	$scope.draftId = $stateParams.draftId;

	$scope.projectIdeaDraft = {
			author: $scope.author,
			title: '',
			description: '',
			tags: [],
			years: 0,
			months: 0,
			days: 0
	};

	$scope.select2Options = {
			'multiple': true,
			'simple_tags': true,
			'tags': []   // Can be empty list.
	};

	$http.get('api/draftedProjectIdeas/'+$scope.draftId).success(function(data){
		$scope.projectIdeaDraft.author =  data.author.username;
		$scope.projectIdeaDraft.title =  data.specifications.title;
		$scope.projectIdeaDraft.description =  data.specifications.description;

		angular.forEach(data.specifications.tags,function(key,value){
			$scope.projectIdeaDraft.tags.push(key.tag);
		});
	}).error(function(data){
		//handle error
	});

	$http.get('api/tags').success(function(data){
		angular.forEach(data,function(key,value){
			$scope.select2Options.tags.push(key.tag);
		});
	}).error(function(data){
		//handle error
	});

	$scope.saveDraft = function(projectIdeaDraft){
		$http.put('api/draftedProjectIdeas/'+$scope.draftId,projectIdeaDraft).success(function(data){
			$scope.newProjectidea = data;
			$scope.alerts.push({ type: 'success', msg: 'Draft updated successfully' });
		}).error(function(data){
			$scope.alerts.push({ type: 'danger', msg: 'Failed to update draft' });
		});
	};

	$scope.projectIdeaDocuments = [];

	$http.get('api/draftedProjectIdeas/'+$scope.draftId+'/attachments').success(function(data){
		$scope.projectIdeaDocuments = data.items;
	}).error(function(data){
		//handle error
	});

	$scope.deleteProjectIdeaDocument = function(projectIdeaDocument){
		$http.delete('api/draftedProjectIdeas/'+$scope.draftId+'/attachments/'+projectIdeaDocument.id).success(function(){
			$scope.alerts.push({ type: 'success', msg: 'Document deleted Successfully' });
			//remove deleted document from the view model
			var index = $scope.projectIdeaDocuments.indexOf(projectIdeaDocument);
			$scope.projectIdeaDocuments.splice(index, 1);
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
					url: 'api/draftedProjectIdeas/'+$scope.draftId+'/attachments', //upload.php script, node.js route, or servlet url
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
}]).
controller('ProjectIdeaController',['$scope','$stateParams','$http',function($scope, $stateParams,$http) {

	$scope.projectIdeaId = $stateParams.id;

	$http.get('api/publishedProjectIdeas/'+$scope.projectIdeaId).success(function(data){
		$scope.projectIdea = data;
	}).error(function(data){
		//handle error
	});

	$http.get('api/publishedProjectIdeas/'+$scope.projectIdeaId+"/attachments").success(function(data){
		$scope.projectIdeaDocuments = data;
	}).error(function(data){
		//handle error
	});

}]).
controller('ProjectIdeaReviewController',function($scope, $stateParams,ProjectIdeaReviewService){
	$scope.projectIdeaId = $stateParams.id;

	$scope.projectIdeaReviews = [];

	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10;
	$scope.maxSize = 5;


	$scope.pageChanged = function() {
		loadData();
	};

	function loadData(){
		ProjectIdeaReviewService.getProjectIdeaReviews($scope.projectIdeaId,$scope.currentPage,$scope.itemsPerPage).success(function(page){
			$scope.projectIdeaReviews = page.items;
			$scope.totalItems = page.count;
		});

	}

	loadData();

	$scope.newReview = {
			projectIdeaId:$scope.projectIdeaId,
			stars:0,
			remarks:'',
			author:'anand'

	};

	$scope.clearReviewForm = function(){
		$scope.newReview = {
				projectIdeaId:$scope.projectIdeaId,
				stars:0,
				remarks:'',
				author:'anand'

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
			$location.path('/home').replace();
		};

		var loginError = function() {
			$scope.loginError = "Bad Credentials";
		};
		AuthService.login($scope.credentials).then(loginSuccess,
				loginError);
	};
}).
controller('NavBarController',
		function($scope, AuthService,$http,$modal) {
	$scope.isLoggedIn = function() {
		return AuthService.isAuthenticated();
	};

	$scope.authenticatedUser = function() {
		return AuthService.getAuthenticatedUser();
	};


	$http.get('api/users/'+AuthService.getAuthenticatedUser()).success(function(data){
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
			$http.put('api/users/'+AuthService.getAuthenticatedUser()+'/updatePassword',$scope.updatePasswordCommand).success(function(data){
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
controller('DashBoardController',['$scope','$stateParams','$http',function($scope,$stateParams,$http){
	$http.get('api/publishedProjectIdeas/tagCounts').success(function(data){
		$scope.tags = data;
	}).error(function(data){
		//handle error
	});

}]).
controller('UserController',['$scope','$stateParams','$http',function($scope,$stateParams,$http){
	if($stateParams.username){

		$scope.username = $stateParams.username;

		$http.get('api/users/'+$scope.username).success(function(data){
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
			var params = {
					page: $scope.currentPage,
					recordsPerPage: $scope.itemsPerPage
			};
			return $http.get('api/users',{params: params}).success(function(data){
				$scope.totalItems = data.count;
				$scope.users = data.items;
			}).error(function(data){
				//handle error
			});
		};

		$scope.loadData();
	}
}]).
controller('GroupController',['$scope','$stateParams','$http',function($scope,$stateParams,$http){
	if($stateParams.groupName){

		$scope.groupName = $stateParams.groupName;

		$http.get('api/groups/'+$scope.groupName).success(function(data){
			$scope.group = data;
		});

	}else{
		$scope.groups = [];
		$http.get('api/groups').success(function(data){
			$scope.groups = data;
		});
	}
}]).
controller('AuthorityController',['$scope','$http',function($scope,$http){

	$scope.authorities = [];

	$scope.loadData = function(){
		$http.get('api/authorities').success(function(data){
			$scope.authorities = data;
		}).error(function(data){
			//handle error
		});
	};


	$scope.generateAuthorities = function(){
		$http.post('api/authorities/bootstrap').success(function(data){
			$scope.loadData();
		}).error(function(data){
			//handle error
		});
	};
	
	$scope.loadData();
	
}]).
controller('AnalyticsController',['$scope','$stateParams','$http',function($scope,$stateParams,$http){


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

	$http.get('api/publishedProjectIdeas/tagCounts').success(function(data){
		angular.forEach(data,function(key,value){
			$scope.chartData.data.push({"x":key.tag,"y":[key.count]});
		});
	}).error(function(data){
		//handle error
	});
}]).
controller('ChatController',['$scope',function($scope) {

	$scope.messages = [];


	$scope.messageReceived = function(message){
		$scope.messages.push(message);
	};
}]);
