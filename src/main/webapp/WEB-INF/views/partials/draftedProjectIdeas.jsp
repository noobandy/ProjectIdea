
<a class="btn" ui-sref="myProjectIdeas.drafted.new"> <i
	class="fa fa-plus"></i> New Project Idea
</a>

<div class="list-group">
	<a ng-repeat="projectIdea in projectIdeas"
		ui-sref="myProjectIdeas.drafted.edit({draftId:projectIdea.id})"
		class="list-group-item">
		<h4 class="list-group-item-heading">{{projectIdea.title}}</h4>
		<p class="list-group-item-text">{{projectIdea.description}}</p>
	</a>
</div>
<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
	total-items="totalItems" items-per-page="itemsPerPage"
	max-size="maxSize" class="pagination-sm" boundary-links="true"
	rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>
