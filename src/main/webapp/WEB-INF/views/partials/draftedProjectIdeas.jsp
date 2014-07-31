<div class="list-group">
	<a ng-repeat="projectIdea in projectIdeas"
		ui-sref="projectIdea({id:projectIdea.id})" class="list-group-item">
		<h4 class="list-group-item-heading">{{projectIdea.title}}</h4>
		<p class="list-group-item-text">{{projectIdea.description}}</p>
	</a>
</div>
<pagination ng-if="totalItems > 0" ng-model="currentPage" total-items="totalItems" ng-change="pageChanged(page)"
	 items-per-page="itemsPerPage" max-size="maxSize"
	class="pagination-sm" boundary-links="true" rotate="false"
	num-pages="numPages">
	
	</pagination>
<pre ng-if="totalItems > 0">Page: {{currentPage}} / {{numPages}}</pre>
