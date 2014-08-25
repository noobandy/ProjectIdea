<div class="row">
	<div class="col-md-1"></div>
	<div class="conversation-wrap col-md-3">

		<div class="media conversation">
			<a class="pull-left" href="#"> <img class="media-object"
				src="//placehold.it/64x64" alt="64x64"
				style="width: 50px; height: 50px;">
			</a>
			<div class="media-body">
				<h5 class="media-heading">Author</h5>
				<small>Message</small>
			</div>
		</div>

	</div>



	<div class="message-wrap col-md-7">
		<div id="content" class="msg-wrap">
			<div ng-repeat="message in messages | orderBy : 'sentAt' "
				class="media msg ">
				<a class="pull-left" href="#"> <img class="media-object"
					src="//placehold.it/64x64" alt="64x64"
					style="width: 50px; height: 50px;">
				</a>
				<div class="media-body">
					<small class="pull-right time"><i class="fa fa-clock-o"></i>
						{{message.sentAt | date:'short'}} </small>
					<h5 class="media-heading">{{message.author}}</h5>
					<small class="col-lg-10"> {{message.message}} </small>
				</div>
			</div>
		</div>

		<div class="send-wrap ">

			<chat></chat>
		</div>
	</div>
</div>
