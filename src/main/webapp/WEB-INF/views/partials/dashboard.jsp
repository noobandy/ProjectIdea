<div class="row">
	<div class="col-md-6">
		<div class="panel panel-success">
			<!-- Default panel contents -->
			<div class="panel-heading">Published Ideas</div>
			<div class="panel-body">
				<a ui-sref-active="active" ng-repeat="tag in tags"
					ui-sref="home({tag:tag.tag})"><span
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
	<div class="col-md-2">
		<ul class="nav nav-pills nav-stacked">
			<li ui-sref-active="active"><a ui-sref="dashboard.users"><i class="fa fa-user"></i> User</a></li>
			<li ui-sref-active="active"><a ui-sref="dashboard.groups"><i class="fa fa-users"></i> Group</a></li>
			<li ui-sref-active="active"><a ui-sref="dashboard.authorities"><i class="fa fa-key"></i> Authorities</a></li>
			<li ui-sref-active="active"><a ui-sref="dashboard.analytics"><i class="fa fa-bar-chart-o"></i> Analytics</a></li>
		</ul>
	</div>
	<div class="col-md-10">
		<ui-view></ui-view>
	</div>

</div>