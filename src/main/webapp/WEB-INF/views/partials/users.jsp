
<table class="table table-striped table-bordered">
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
<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
	total-items="totalItems" items-per-page="itemsPerPage"
	max-size="maxSize" class="pagination-sm" boundary-links="true"
	rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>

