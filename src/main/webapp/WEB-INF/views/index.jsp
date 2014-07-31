<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-readable.min.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/xeditable.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/ng-table.min.css"></link>
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
	min-height: 2000px;
	padding-top: 70px;
}

.select2>.select2-choice.ui-select-match {
	/* Because of the inclusion of Bootstrap */
	height: 29px;
}

.selectize-control>.selectize-dropdown {
	top: 36px;
}
</style>
<title><spring:message code="Project.title" /></title>
</head>
<body ng-cloak ng-app="ProjectIdeaApp">
	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation"
		ng-controller="NavBarController">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a ui-sref-active="active" class="navbar-brand"
					ui-sref="home.publishedProjectIdeas"> <spring:message
						code="Project.title" /> <i class="fa fa-home"></i>
				</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li ui-sref-active="active"><a
						ui-sref="myProjectIdeas.drafted.projectIdeas"> <spring:message
								code="ProjectIdea.myIdeas" /><i class="fa fa-paper-plane-o"></i>
					</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li ui-sref-active="active" ng-if="isLoggedIn()"><a
						ui-sref="chat"> <i class="fa fa-comments-o"></i>
					</a></li>

					<li ui-sref-active="active" ng-if="isLoggedIn()"><a
						ui-sref="logout"> {{authenticatedUser()}}&nbsp;<i
							class="fa fa-sign-out"></i>
					</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container-fluid" ui-view></div>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/select2.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/angular-ui-router.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	<script
		src='${pageContext.request.contextPath}/resources/js/ng-table.min.js'></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/ui-bootstrap-tpls-0.11.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/xeditable.min.js"></script>


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

	<script type="text/javascript">
		$(document).ready(function() {
			$('a').tooltip();
		});
	</script>
</body>
</html>
