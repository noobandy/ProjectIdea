<div class="row">
	<div class="col-md-4">
		<!--left col-->

		<ul class="list-group">
			<li class="list-group-item text-muted">Profile</li>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Joined</strong></span>
				2.13.2014</li>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Last
						seen</strong></span> Yesterday</li>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Real
						name</strong></span> {{user.completeName}}</li>

		</ul>

		<ul class="list-group">

			<li class="list-group-item text-muted">Ideas <i
				class="fa fa-paper-plane-o"></i></li>
			<li ng-repeat="tag in tagCounts" class="list-group-item"><span
				class="badge">{{tag.count}}</span> {{tag.tag}}</li>
		</ul>


	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">{{user.completeName}}</div>
			<div class="panel-body">
				<img class="thumbnail" ng-src="//placehold.it/64x64" alt="avtar">
			</div>
		</div>
		<ul class="list-group">
			<li class="list-group-item list-group-item-info">
				<h4 class="list-group-item-heading">Email</h4>
				<p class="list-group-item-text">{{user.emailId}}</p>
			</li>
			<li ng-if="user.enabled"
				class="list-group-item list-group-item-success">
				<h4 class="list-group-item-heading">Status</h4>
				<p class="list-group-item-text">Enabled</p>
			</li>
			<li ng-if="!user.enabled"
				class="list-group-item list-group-item-warning">
				<h4 class="list-group-item-heading">Status</h4>
				<p class="list-group-item-text">Disabled</p>
			</li>
			<li ng-if="!user.credentialExpiredAt"
				class="list-group-item list-group-item-success">
				<h4 class="list-group-item-heading">Credentials</h4>
				<p class="list-group-item-text">Valid</p>
			</li>
			<li ng-if="user.credentialExpiredAt"
				class="list-group-item list-group-item-warning">
				<h4 class="list-group-item-heading">Credentials</h4>
				<p class="list-group-item-text">Expired</p>
			</li>

		</ul>
	</div>
	<div class="col-md-4">
		<form name="grantAuthorityForm" class="form-inline" novalidate>
			<div class="form-group">
				<select class="form-control" ng-model="authorityToGrant" required>
					<option value="">Select</option>
					<option ng-repeat="authority in authorities"
						value="{{authority.authority}}">{{authority.authority}}</option>
				</select>
			</div>
			<button class="btn btn-primary"
				ng-click="grantAuthorityForm.$valid && grantAuthority(authorityToGrant)">
				<i class="fa fa-plus"></i>
			</button>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>Authority</th>
					<th>Revoke</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="authority in grantedAuthorities">
					<td>{{authority.authority}}</td>
					<td><button type="button"
							ng-click="revokeAuthority(authority.authority)"
							class="btn btn-danger">&times;</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>