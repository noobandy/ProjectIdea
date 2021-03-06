<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-cerulean.min.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/select2.css"></link>

<style type="text/css">
/* Forms */
.ng-invalid.ng-dirty {
	border-color: #FA787E;
}

.ng-valid.ng-dirty {
	border-color: #78FA89;
}

body {
	padding-top: 70px;
}

/* Sticky footer styles
-------------------------------------------------- */
html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 60px;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 60px;
	background-color: #f5f5f5;
}

.select2>.select2-choice.ui-select-match {
	/* Because of the inclusion of Bootstrap */
	height: 29px;
}

.selectize-control>.selectize-dropdown {
	top: 36px;
}

.chart {
	height: 400px;
	width: 800px;
}

[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak
	{
	display: none !important;
}

#spinner {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 1000;
	background-color: white;
	opacity: .8;
}

.ajax-loader {
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -32px; /* -1 * image width / 2 */
	margin-top: -32px; /* -1 * image height / 2 */
	display: block;
}
</style>
<title><spring:message code="Project.title" /></title>
</head>
<body ng-cloak class="ng-cloak" ng-app="ProjectIdeaApp"
	ng-controller="ApplicationController">

	<img id="spinner" class="ajax-loader"
		ng-src="resources/img/loading1.gif" style="display: none;">

	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a ui-sref-active="active" class="navbar-brand"
					ui-sref="home.publishedProjectIdeas"> <i class="fa fa-home"></i>
					<spring:message code="Project.title" />
				</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li ui-sref-active="active"><a
						ui-sref="myProjectIdeas.drafted.projectIdeas"> <i
							class="fa fa-paper-plane-o"></i> <spring:message
								code="ProjectIdea.myIdeas" />
					</a></li>
					<li ui-sref-active="active"><a ui-sref="dashboard"> <i
							class="fa fa-dashboard"></i> Dashboard
					</a></li>
				</ul>
				<ul ng-if="isAuthenticated()" class="nav navbar-nav navbar-right">
					<notification></notification>
					<li ui-sref-active="active"><a ui-sref="chat"> <i
							class="fa fa-comments-o"></i>
					</a></li>

					<li class="dropdown"><a href="javascript();"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="fa fa-gears"></i> {{authenticatedUser()}} <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a ui-sref="userProfile({username:authenticatedUser()})">
									<i class="fa fa-user"></i> Profile
							</a></li>

							<li ui-sref-active="active"><a ui-sref="logout"> <i
									class="fa fa-sign-out"></i> Log Out
							</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div ncy-breadcrumb></div>
			</div>
		</div>
	</div>
	<div class="container-fluid" ui-view></div>
	<div class="footer">
		<div class="container">
			<p class="text-muted">
				&copy;<a href="//anandm.in" target="_blank">Anand Mohan</a>
			</p>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.atmosphere.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/angular-file-upload-shim.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/d3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/angular-charts.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/angular-file-upload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/select2.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/angular-ui-router.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/ui-bootstrap-tpls-0.11.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/angular-breadcrumb.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/app.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/directives.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/factories.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/filters.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/services.js"></script>
</body>
</html>
