<div class="row">
	<div class="col-md-3">
		<div class="list-group">
			<a ui-sref-active="active" ng-repeat="tag in tags"
				class="list-group-item"
				ui-sref="home({tag:tag.tag})"><span
				class="badge">{{tag.count}}</span> {{tag.tag}}</a>
		</div>
	</div>
	<div class="col-md-6">
		<tabset justified="true" type="pills"> <tab> <tab-heading>Newest
		</tab-heading> </tab> <tab> <tab-heading> Top Rated </tab-heading> </tab> </tabset>
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
	<div class="col-md-3">
		<div class="list-group">
			<a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Ad heading</h4>
				<p class="list-group-item-text">
					<img src="http://placehold.it/150x100">
				</p>
			</a>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Ad heading</h4>
				<p class="list-group-item-text">
					<img src="http://placehold.it/150x100">
				</p>
			</a>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Ad heading</h4>
				<p class="list-group-item-text">
					<img src="http://placehold.it/150x100">
				</p>
			</a>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Ad heading</h4>
				<p class="list-group-item-text">
					<img src="http://placehold.it/150x100">
				</p>
			</a>
		</div>
	</div>
</div>