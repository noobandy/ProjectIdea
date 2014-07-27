<div class="row">
<div class="col-md-4 col-md-offset-4">
	<form name="loginForm"  class="form-horizontal" method="post" ng-submit="loginForm.$valid &&  login()" novalidate>
		<p class="text-danger">{{loginError}}</p>
		<div class="form-group">
			<input ng-model="credentials.username" type="text"
				class="form-control" placeholder="username" required/>
			<span class="help-block">username issued by your administrator.</span>	
		</div>
		<div class="form-group">
			<input ng-model="credentials.password" type="password"
				class="form-control" required/>
		</div>
		<div class="form-group">
			<button class="btn btn-primary">Login</button>
		</div>
	</form>
</div>
</div>