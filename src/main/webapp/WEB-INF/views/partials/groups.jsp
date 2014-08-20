<table class="table table-striped">
	<thead>
		<tr>
			<th>Group</th>
			<th>Deny Group?</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="group in groups">
			<td><a ui-sref="dashboard.groups.group({groupName:group.groupName})">{{group.groupName}}</a></td>
			<td ng-if="group.denyGroup"><input ng-disabled="true" type="checkbox"
				checked="checked"></td>
			<td ng-if="!group.denyGroup"><input ng-disabled="true" type="checkbox"></td>
		</tr>
	</tbody>
</table>