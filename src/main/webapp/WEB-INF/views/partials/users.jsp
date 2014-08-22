<div class="row">
	<div class="col-md-3">
		<ul class="nav nav-pills nav-stacked">
			<li ng-class="{ 'active': activeUser == user.username }" ng-click="activateUser(user.username)" ng-repeat="user in users"><a
				ui-sref="dashboard.users.user({username:user.username})">{{user.username}}</a>
			</li>
		</ul>
		<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
			total-items="totalItems" items-per-page="itemsPerPage"
			max-size="maxSize" class="pagination-sm" boundary-links="true"
			rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
		<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>
	</div>
	<div class="col-md-9">
		<ui-view></ui-view>
	</div>
</div>


<!-- <table class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>Complete Name</th>
			<th>User Name</th>
			<th>Email</th>
			<th>Enabled</th>
			<th>Credentials Expired</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="user in users">
			<td>{{user.completeName}}</td>
			<td><a ui-sref="dashboard.users.user({username:user.username})">{{user.username}}</a></td>
			<td>{{user.emailId}}</td>
			<td ng-if="user.enabled"><input ng-disabled="true"
				type="checkbox" checked="checked"></td>
			<td ng-if="!user.enabled"><input ng-disabled="true"
				type="checkbox"></td>
			<td>{{user.credentialExpiredAt | date}}</td>
		</tr>
	</tbody>
</table>

<hr> -->

