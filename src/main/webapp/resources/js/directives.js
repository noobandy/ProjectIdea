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
		 template: '<a ui-sref="projectIdea({id:projectIdea.id})" class="list-group-item">'
			+'<h4 class="list-group-item-heading">{{projectIdea.title}}</h4>'
			+'<p class="list-group-item-text">{{projectIdea.description}}</p>'
		+'</a>'
	 };
  }).
  directive('chat',function(Session){
	  return {
		  restrict:'E',
		  template: '<textarea ng-model="message.message" class="form-control send-message" rows="3" placeholder="Write a reply..." required></textarea>',
		  replace: true,
		  link: function(scope,element,attrs){
			  var socket = $.atmosphere;
			  scope.message = {
					  author : Session.getAuthenticatedUser(),
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
