<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-readable.min.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/xeditable.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/ng-table.min.css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/chat.css"></link>
<style type="text/css">
/* Forms */
.ng-invalid.ng-dirty {
	border-color: #FA787E;
}

.ng-valid.ng-dirty {
	border-color: #78FA89;
}
</style>
<title><spring:message code="Project.title" /></title>
</head>
<body ng-cloak ng-app="ProjectIdeaApp">
	<nav class="navbar navbar-default" role="navigation"
		ng-controller="NavBarController">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" ui-sref="home"><spring:message
						code="Project.title" /></a>
			</div>

			<ul class="nav navbar-nav">
				<li><a ui-sref="myProjectIdeas.drafted"><spring:message
							code="ProjectIdea.myIdeas" /> </a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li ng-if="isLoggedIn()"><a data-toggle="tooltip"
					data-placement="bottom" title="chat" ui-sref="chat"><i
						class="glyphicon glyphicon-comment"></i></a></li>

				<li ng-if="isLoggedIn()"><a data-toggle="tooltip"
					data-placement="bottom" title="log out" ui-sref="logout">{{authenticatedUser()}}&nbsp;<i
						class="glyphicon glyphicon-log-out"></i></a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
		<!-- /.container-fluid -->
	</nav>
	<div class="container-fluid" ui-view></div>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>

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
