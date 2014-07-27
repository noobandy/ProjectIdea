<div class="row">
	<div class="col-md-12">
		<div ng-repeat="projectIdea in projectIdeas" class="panel panel-default">
			<div class="panel-heading">
				{{projectIdea.title}}
				<span ng-repeat-start="tag in projectIdea.tags" ng-bind="tag" class="label label-primary"></span> <span ng-repeat-end></span>
			</div>
			<div class="panel-body">
				<div class="col-md-10">
					<p>{{projectIdea.description}}</p>
				</div>
				<div class="col-md-2">
					<a class="thumbnail" href="#"> <img
						ng-src="//placehold.it/64x64" alt="avtar" />
						<h3 class="caption">{{projectIdea.author}}</h3>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>