'use strict';

/* Directives */


angular.module('ProjectIdeaApp.directives', []).
directive('appVersion', ['version', function(version) {
	return function(scope, elm, attrs) {
		elm.text(version);
	};
}]).
directive('projectIdeaSummary',function(){
	return {
		restrict: 'E',
		scope: {
			projectIdea: '=projectIdea'
		},
		templateUrl: 'partials/projectIdeaSummary'
	};
}).
directive('notification',function(AuthService){
	return {
		restrict: 'E',
		template: '<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">'+
		'<i class="fa fa-globe"></i></a>'+
		'<ul id="notifications" class="dropdown-menu">'+
		'<li ng-repeat="notification in notifications"><a>{{notification.message}}</a></li>'+
		'</ul>'+
		'</li>',
		replace: true,
		link: function(scope,element,attrs){
			scope.notifications = [];
			var socket = $.atmosphere;
			var username = AuthService.getAuthenticatedUser();
			var request = {
					url : 'notifications/'+username,
					contentType : 'application/json',
					logLevel : 'debug',
					transport : 'websocket',
					fallbackTransport : 'long-polling',
					onTransportFailure: function(errorMsg, request){
						$.atmosphere.info(errorMsg);
						if ( window.EventSource ) {
							request.fallbackTransport = "sse";
						}
					},
					onReconnect: function(){
						socket.info("Reconnecting");
					},
					onMessage: function(response){
						console.log(response);
						var responseBody = response.responseBody;
						try{
							var event = JSON.parse(responseBody);
							scope.notifications.push(event);
						} catch(e){
							console.log(e);
						}
					},
					onClose: function(response) {
						console.log('Closing connection');
					},
					onError: function(response) {
						console.log(response);
					}
			};

			var subSocket = socket.subscribe(request);

		}
	};
}).
directive('chat',function(AuthService){
	return {
		restrict:'E',
		template: '<textarea ng-model="message.message" class="form-control send-message" rows="3" placeholder="Write a reply..." required></textarea>',
		replace: true,
		link: function(scope,element,attrs){
			var socket = $.atmosphere;
			scope.message = {
					author : AuthService.getAuthenticatedUser(),
					message: ''
			};

			var request = { url:'/apps/chat',
					contentType : "application/json",
					logLevel : 'debug',
					transport : 'websocket' ,
					fallbackTransport: 'long-polling',
					onOpen: function(response){

					},
					onTransportFailure: function(errorMsg, request){
						$.atmosphere.info(errorMsg);
						if ( window.EventSource ) {
							request.fallbackTransport = "sse";
						}
					},
					onReconnect: function(){
						socket.info("Reconnecting");
					},
					onMessage: function(response){
						console.log(response);
						var responseBody = response.responseBody;
						try{
							var message = JSON.parse(responseBody);

							scope.messageReceived(message);
							scope.$apply();
						} catch(e){
							console.log(e);
						}
					},
					onClose: function(response) {
						console.log('Closing connection');
					},
					onError: function(response) {
						console.log(response);
					}
			};

			var subSocket = socket.subscribe(request);

			element.bind("keydown keypress", function (event) {
				if(event.which === 13) {
					subSocket.push(JSON.stringify(scope.message));
					scope.message.message = '';
					scope.$apply();
					event.preventDefault();
				}
			});
		}
	};
});
