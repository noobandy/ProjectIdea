<button ng-click="generateAuthorities()" type="button"
	class="btn btn-primary">Generate</button>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Authority</th>
			<th>Expired At</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="authority in authorities">
			<td>{{authority.authority}}</td>
			<td>{{authority.expiredAt | date}}</td>
		</tr>
	</tbody>
</table>