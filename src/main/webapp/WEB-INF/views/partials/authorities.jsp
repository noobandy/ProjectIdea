<button ng-click="generateAuthorities()" type="button" class="btn btn-primary">Generate</button>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Authority</th>
			<th>Expired At</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="authority in authorities">
			<td><a
				ui-sref="dashboard.authorities.authority({authority:authority.authority})">{{authority.authority}}</a></td>
			<td>{{authority.expiredAt | date}}</td>
		</tr>
	</tbody>
</table>
<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
	total-items="totalItems" items-per-page="itemsPerPage"
	max-size="maxSize" class="pagination-sm" boundary-links="true"
	rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>