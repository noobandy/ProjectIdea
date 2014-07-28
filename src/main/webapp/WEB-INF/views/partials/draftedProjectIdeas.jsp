<div class="row">
	<div class="col-md-3">
		<div class="list-group">
			<a ng-repeat="tag in tags" class="list-group-item" ui-sref="myProjectIdeas.drafted({tag:tag.tag})"><span
				class="badge">{{tag.count}}</span> {{tag.tag}}</a>
		</div>
	</div>
	<div class="col-md-6">
		<div class="list-group">
			<a ng-repeat="projectIdea in projectIdeas" ui-sref="projectIdea({id:projectIdea.id})"
				class="list-group-item">
				<h4 class="list-group-item-heading">{{projectIdea.title}}</h4>
				<p class="list-group-item-text">{{projectIdea.description}}</p>
			</a>
		</div>
		<pagination total-items=totalItems ng-model="currentPage" items-per-page="itemsPerPage"
			max-size="maxSize" class="pagination-sm" boundary-links="true"
			rotate="false" num-pages="numPages"></pagination>
		<pre>Page: {{currentPage}} / {{numPages}}</pre>
	</div>
	<div class="col-md-3">Ads here</div>
</div>