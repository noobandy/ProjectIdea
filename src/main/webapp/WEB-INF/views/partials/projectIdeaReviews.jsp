<form name="reviewForm" ng-submit="reviewForm.$valid addReview(newReview)" novalidate>
	<alert ng-repeat="alert in alerts" type="{{alert.type}}"
		close="closeAlert($index)">{{alert.msg}}</alert>
	<div class="form-group">
		<rating ng-model="newReview.star" max="5"  on-hover="hoveringOver(value)" on-leave="overStar = null" state-on="'fa fa-star'"
			state-off="'fa fa-star-empty'" readonly="true"></rating>
		<b>(<i>Stars:</i> {{newReview.star}})
		</b>
	</div>
	<div class="form-group">
		<textarea rows="3" cols="5" type="text" class="form-control"
			ng-model="newReview.remark" placeholder="Remark" required />
	</div>
	<div class="form-group">
		<button class="btn btn-primary">Save</button>
		<button ng-if="reviewForm.$dirty" type="button"
			ng-click="clearReviewForm()" class="btn btn-warning">Clear</button>
	</div>
</form>
<div class="list-group">
	<a ng-repeat="review in projectIdeaReviews" href="#"
		class="list-group-item">
		<div class="list-group-item-heading">
			<rating ng-model="review.star" max="5" state-on="'fa fa-star'"
				state-off="'fa fa-star-empty'" readonly="true"></rating>
			<b>(<i>Stars:</i> {{review.star}})
			</b>
		</div>
		<p class="list-group-item-text">{{review.remark}}</p>
	</a>
</div>
<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
	total-items="totalItems" items-per-page="itemsPerPage"
	max-size="maxSize" class="pagination-sm" boundary-links="true"
	rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>