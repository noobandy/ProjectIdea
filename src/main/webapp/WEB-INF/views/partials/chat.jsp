<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="row">

	<div class="conversation-wrap col-lg-3">

		<div class="media conversation">
		
		<div ng-repeat="message in recentMessagesOfAuthor | orderBy : '-sentAt' " class="media conversation">
			<a class="pull-left" href="#"> <img class="media-object"
				src="//placehold.it/64x64" alt="64x64"
				style="width: 50px; height: 50px;">
			</a>
			<div class="media-body">
				<h5 class="media-heading">{{message.author}}</h5>
				<small>{{message.message}}</small>
			</div>
		</div>
		
		</div>
	</div>



	<div class="message-wrap col-lg-8">
		<div id="content" class="msg-wrap">
			<div ng-repeat="message in messages | orderBy : 'sentAt' " class="media msg ">
				<a class="pull-left" href="#"> <img class="media-object"
					src="//placehold.it/64x64" alt="64x64"
					style="width: 50px; height: 50px;">
				</a>
				<div class="media-body">
					<small class="pull-right time"><i
						class="glyphicon glyphicon-time"></i>
						{{message.sentAt |date:'short'}}
						 </small>
					<h5 class="media-heading">
					{{message.author}}
					</h5>
					<small class="col-lg-10">
					{{message.message}}
					 </small>
				</div>
			</div>
		</div>

		<div class="send-wrap ">

			<form id="chatMessageForm" action="#">
				<textarea id="chatMessage" class="form-control send-message"
					rows="3" placeholder="Write a reply..." required="required"
					disabled="disabled"></textarea>
			</form>
		</div>
	</div>

	<script type="text/javascript"
		src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.atmosphere.js"></script>

	<script type="text/javascript">
	$(function () {
	    
		"use strict";
	
	    var content = $('#content');
	    var input = $('#chatMessage');
	    
	    var currentUser = '<security:authentication property="principal.username" />';
	   
	    var socket = $.atmosphere;

	    // We are now ready to cut the request
	    var request = { url:'${pageContext.request.contextPath}/loadChat/chat',
	        contentType : "application/json",
	        logLevel : 'debug',
	        transport : 'websocket' ,
	        fallbackTransport: 'long-polling'};


	    request.onOpen = function(response) {
	        input.removeAttr('disabled').focus();
	    };

	    <!-- For demonstration of how you can customize the fallbackTransport based on the browser -->
	    request.onTransportFailure = function(errorMsg, request) {
	        jQuery.atmosphere.info(errorMsg);
	        if ( window.EventSource ) {
	            request.fallbackTransport = "sse";
	        }
	    };

	    request.onReconnect = function (request, response) {
	        socket.info("Reconnecting")
	    };

	    request.onMessage = function (response) {
	        var message = response.responseBody;
	        try {
	            var json = jQuery.parseJSON(message);
	        } catch (e) {
	            console.log('This doesn\'t look like a valid JSON: ', message.data);
	            return;
	        }

	            var me = json.author == currentUser;
	            var date = typeof(json.time) == 'string' ? parseInt(json.time) : json.time;
	            
	            addMessage(json.author, json.text, me ? 'blue' : 'black', new Date(date));
	    };

	    request.onClose = function(response) {
	      console.log('Closing connection');
	    }

	    request.onError = function(response) {
	        content.html($('<p>', { text: 'Sorry, but there\'s some problem with your '
	            + 'socket or the server is down' }));
	    };

	    var subSocket = socket.subscribe(request);

	    input.keydown(function(e) {
	        if (e.keyCode === 13) {
	            var msg = $(this).val();

	            subSocket.push(JSON.stringify({ author: currentUser, message: msg }));
	            $(this).val('');
	        }
	    });

	    function addMessage(author, message, color, datetime) {
	    	
	    	var hours = datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours();
	    	
	    	var minutes = datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes();
	    	
	    	var time = hours + ':' + minutes;
	    	
            var htmlString = '<div class="media msg ">'
	    	+'<a class="pull-left" href="#"> <img class="media-object"'
	    	+'src="//placehold.it/64x64" alt="64x64"'
	    	+'style="width: 50px; height: 50px;">'
	    	+'</a>'
	    	+'<div class="media-body">'
	    	+'<small class="pull-right time"><i class="glyphicon glyphicon-time"></i>'
	    	+time
	    	+'</small>'
	    	+'<h5 class="media-heading" style="color:'
	    	+color
	    	+'">'
	    	+author
	    	+'</h5>'
	    	+'<small class="col-lg-10">'
	    	+message
	    	+'</small>'
	    	+'</div>'
	    	+'</div>';
	    	
	    	content.append(htmlString);
			
	       /*  content.append('<p><span style="color:' + color + '">' + author + '</span> @ ' +
	            + (datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
	            + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())
	            + ': ' + message + '</p>'); */
	    }
	});
</script>
</div>
