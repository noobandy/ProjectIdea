<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="modal-header">
	<button type="button" class="close" ng-click="cancel()">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
	<h4 class="modal-title">{{userProfile.username}}</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-6">
			<!--left col-->

			<ul class="list-group">
				<li class="list-group-item text-muted">Profile</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Joined</strong></span>
					2.13.2014</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Last
							seen</strong></span> Yesterday</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Real
							name</strong></span> {{userProfile.completeName}}</li>

			</ul>

			<ul class="list-group">

				<li class="list-group-item text-muted">Ideas <i
					class="fa fa-paper-plane-o"></i></li>
				<li ng-repeat="tag in userProfile.tagCounts" class="list-group-item"><span
					class="badge">{{tag.count}}</span> {{tag.tag}}</li>
			</ul>


		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">{{userProfile.completeName}}</div>
				<div class="panel-body">
					<img class="thumbnail" ng-src="//placehold.it/64x64" alt="avtar">
				</div>


			</div>
			<!-- tabs -->
			<tabset ng-if="isAuthenticated() && userProfile.username == authenticatedUser()" justified="true"> <tab heading="Contact">Contact
			Details</tab> <tab> <tab-heading> <i
				class="fa fa-security"></i>Security</tab-heading>
			<div ng-form name="updatePasswordForm">


				<alert ng-repeat="alert in alerts" type="{{alert.type}}"
					close="closeAlert($index)">{{alert.msg}}</alert>
				<div class="form-group">
					<input ng-model="updatePasswordCommand.oldPassword" type="password"
						class="form-control" placeholder="current password" required>
				</div>
				<div class="form-group">

					<input ng-model="updatePasswordCommand.newPassword" type="password"
						class="form-control" placeholder="new password" required>
				</div>
				<div class="form-group">
					<input ng-model="updatePasswordCommand.confirmPassword"
						type="password" class="form-control"
						placeholder="confirm password" required>

				</div>
				<div class="form-group">
					<button type="button" ng-click="updatePasswordForm.$valid && updatePassword()"
						class="btn btn-primary">Save</button>
					<button ng-if="updatePasswordForm.$dirty" type="button" ng-click="clearUpdatePasswordCommand()"
						class="btn btn-warning">Clear</button>
				</div>
			</div>
			</tab> </tabset>

		</div>

	</div>

</div>
<div class="modal-footer"></div>