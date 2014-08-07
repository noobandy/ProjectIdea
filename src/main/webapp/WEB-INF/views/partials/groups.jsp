<table class="table table-striped">
	<thead>
		<tr>
			<th>Group</th>
			<th>Deny Group?</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="group in groups">
			<td ui-sref="dashboard.groups.group({groupName:group.groupName}})">{{group.groupName}}}</td>
			<td ng-if="group.denyGroup"><input type="checkbox"
				checked="checked"></td>
			<td ng-if="!group.denyGroup"><input type="checkbox"></td>
		</tr>
	</tbody>
</table>
<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
	total-items="totalItems" items-per-page="itemsPerPage"
	max-size="maxSize" class="pagination-sm" boundary-links="true"
	rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>