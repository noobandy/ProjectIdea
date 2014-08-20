<div class="row">
	<div class="col-md-3">
		<div class="list-group">
			<a ui-sref-active="active" ng-repeat="tag in tags"
				class="list-group-item"
				ui-sref="myProjectIdeas.published({tag:tag.tag})"><span
				class="badge">{{tag.count}}</span> {{tag.tag}}</a>
		</div>
	</div>
	<div class="col-md-9">
		<div class="list-group">
			<project-idea-summary ng-repeat="projectIdea in projectIdeas"
				project-idea="projectIdea"> </project-idea-summary>
		</div>
		<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
			total-items="totalItems" items-per-page="itemsPerPage"
			max-size="maxSize" class="pagination-sm" boundary-links="true"
			rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
		<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>
	</div>
</div>