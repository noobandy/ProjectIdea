<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-3">
		<div class="panel panel-success">
			<!-- Default panel contents -->
			<div class="panel-heading">Published Ideas</div>
			<div class="panel-body">
				<a ui-sref-active="active" ng-repeat="tag in tags"
					ui-sref="home.publishedProjectIdeas({tag:tag.tag})"><span
					class="badge">{{tag.tag}} &times; {{tag.count}}</span></a>
			</div>
		</div>
	</div>
	<div class="col-md-3">
		<div class="panel panel-warning">
			<!-- Default panel contents -->
			<div class="panel-heading">Disabled Users</div>
			<div class="panel-body">
				<a href="#"> <span class="badge alert-warning"><i
						class="fa fa-lg fa-bell"></i> 1500</span>
				</a>

			</div>
		</div>
	</div>
	<div class="col-md-3">
		<div class="panel panel-danger">
			<!-- Default panel contents -->
			<div class="panel-heading">Security Voilations</div>
			<div class="panel-body">
				<a href="#"> <span class="badge alert-danger"><i
						class="fa fa-lg fa-warning"></i> 1500</span>
				</a>

			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-3">
		<ul class="nav nav-pills nav-stacked">
			<li ui-sref-active="active"><a ui-sref="dashboard.users">User</a></li>
			<li ui-sref-active="active"><a ui-sref="dashboard.groups">Group</a></li>
			<li ui-sref-active="active"><a ui-sref="dashboard.analytics">Analytics</a></li>
		</ul>
	</div>
	<div class="col-md-9">
		<ui-view></ui-view>
	</div>

</div>