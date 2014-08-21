<div class="row">
	<div class="col-md-3">
		<div class="list-group">
			<a ui-sref-active="active" ng-repeat="tag in tags"
				class="list-group-item" ui-sref="home.publishedProjectIdeas({tag:tag.tag})"><span
				class="badge">{{tag.count}}</span> {{tag.tag}}</a>
		</div>
	</div>
	<div class="col-md-6" ui-view>
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