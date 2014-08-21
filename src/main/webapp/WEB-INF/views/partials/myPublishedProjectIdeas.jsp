<div class="row">
	<div class="col-md-3">
		<div class="list-group">
			<a ui-sref-active="active" ng-repeat="tag in tags"
				class="list-group-item"
				ui-sref="myProjectIdeas.published.projectIdeas({tag:tag.tag})"><span
				class="badge">{{tag.count}}</span> {{tag.tag}}</a>
		</div>
	</div>
	<div class="col-md-9" ui-view></div>
</div>