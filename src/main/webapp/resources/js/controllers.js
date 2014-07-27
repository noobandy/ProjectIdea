'use strict';

/* Controllers */

angular.module('ProjectIdeaApp.controllers', []).controller('HomeController',function($scope,$stateParams){
	console.log($stateParams.tag);
	$scope.tags = [{
		tag: 'java',
		count: '100'
	},
	{
		tag: 'spring',
		count: '50'
	},
	{
		tag: 'hibernate',
		count: '25'
	},
	{
		tag: 'jpa',
		count: '26'
	},
	{
		tag: 'spring security',
		count: '23'
	},
	{
		tag: 'angular js',
		count: '20'
	},
	{
		tag: 'Quartz',
		count: '10'
	},
	{
		tag: 'Activiti',
		count: '15'
	}
	];
	
	$scope.projectIdeas = [];
	for (var i = 0; i < 10; i++) {
		$scope.projectIdeas.push({
			id : i,
			title : 'title ' + i,
			description : "This is a hack. In my opinion, it's justifiable and relatively safe.",
				estimatedTimeInMilliseconds : 4512459,
				author : 'author ' + 1,
				tags : [ 'java', 'spring', 'hibernate','spring security','jpa','angular js' ]
		});
	}
	
	
	 $scope.totalItems = 50;
	  $scope.currentPage = 1;
	  $scope.itemsPerPage = 5;

	  $scope.setPage = function (pageNo) {
	    $scope.currentPage = pageNo;
	  };

	  $scope.pageChanged = function() {
	    console.log('Page changed to: ' + $scope.currentPage);
	  };

	  $scope.maxSize = 5;
})
.controller('ProjectIdeaController',
		function($scope, $stateParams) {
		$scope.projectIdea = {
			id : 1,
			title : 'title ',
			description : "This is a hack. In my opinion, it's justifiable and relatively safe."

				+ "Only Gecko is treats fieldsets this way, so we are using a proprietary Gecko feature to target a well-understood Gecko behaviour as a workaround to another well-understood Gecko behaviour which is both unique and undesirable."

				+ "The Gecko targeting hack has been around for a while. Given its widespread use as a means to target Gecko issues� I believe Mozilla will be conservative about removing it."

				+ "Changes to fieldset behaviour tend to meet resistance out of concern for legacy support."

				+ "Given how fundamental table layout is, I strongly doubt that the codepath for calculating the dimensions of internal table elements will be significantly changed in the near future. ",
				estimatedTimeInMilliseconds : 4512459,
				author : 'author ',
				tags : [ 'java', 'spring', 'hibernate','spring security','jpa','angular js' ]
		};

	$scope.tab = 1;

	$scope.selectTab = function(tab){
		$scope.tab = tab;
	};

	$scope.isSelected = function(tab){
		return $scope.tab === tab;
	};


	$scope.review = {

	};

	$scope.addReview = function(projectIdea){

	};

}).controller(
		'LoginController',
		function($timeout, $location, $scope, $cookies, AUTH_EVENTS,
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
		}).controller('NavBarController',
				function($scope, AuthService, Session) {
			$scope.isLoggedIn = function() {
				return AuthService.isAuthenticated();
			};

			$scope.authenticatedUser = function() {
				return Session.getAuthenticatedUser();
			};
		}).controller('ChatController',
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
