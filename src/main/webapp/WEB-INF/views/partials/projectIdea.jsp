<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>{{projectIdea.specifications.title}}</h4>
				<span ng-repeat-start="tag in projectIdea.specifications.tags" ng-bind="tag.tag"
					class="label label-primary"></span> <span ng-repeat-end></span>
			</div>
			<div class="panel-body">
				<div class="col-md-10">
					<p>{{projectIdea.specifications.description}}</p>
				</div>
				<div class="col-md-2">
					<a class="thumbnail" ui-sref="userProfile({username:projectIdea.author.username})"> <img
						ng-src="//placehold.it/64x64" alt="avtar" />
						<h3 class="caption">{{projectIdea.author.username}}</h3>
					</a>
				</div>
			</div>
		</div>
		<tabset type="pills"> <tab> <tab-heading>
		<i class="fa fa-file-text"></i> Documents </tab-heading> 
		<!--  Documents Table -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Size</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="document in projectIdeaDocuments">
					<td>{{document.path}}</td>
					<td>{{document.sizeInBytes}} bytes</td>
					<td>
					<a target="_blank"
						href="api/draftedProjectIdeas/{{document.projectIdea.id}}/attachments/{{document.id}}"
						class="btn btn-primary"> <i class="fa fa-download"></i>
					</a>
					</td>
				</tr>
			</tbody>
		</table>
		</tab> <tab ui-sref="projectIdea.reviews({id:projectIdea.id})"> <tab-heading>
		<i class="fa fa-star"></i>
		<i class="fa fa-star"></i>
		<i class="fa fa-star"></i> Reviews </tab-heading> <ui-view></tab> </tabset>
	</div>
</div>