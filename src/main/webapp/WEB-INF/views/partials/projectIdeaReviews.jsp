<form ng-if="isAuthenticated()" name="reviewForm"
	ng-submit="reviewForm.$valid && addReview(newReview)" novalidate>
	<alert ng-repeat="alert in alerts" type="{{alert.type}}"
		close="closeAlert($index)">{{alert.msg}}</alert>
	<div class="form-group">
		<rating ng-model="newReview.stars" max="5" state-on="'fa fa-star'"
			state-off="'fa fa-star-o'" readonly="false"></rating>
		<b>(<i>Stars:</i> {{newReview.stars}})
		</b>
	</div>
	<div class="form-group">

		<textarea rows="3" cols="5" type="text" class="form-control"
			ng-model="newReview.remarks" placeholder="Remarks" required />
	</div>
	<div class="form-group">
		<button class="btn btn-primary">Save</button>
		<button ng-if="reviewForm.$dirty" type="button"
			ng-click="clearReviewForm()" class="btn btn-warning">Clear</button>
	</div>
</form>

<div class="list-group">
	<div ng-repeat="review in projectIdeaReviews" class="list-group-item">
		<div class="list-group-item-heading">
			<rating ng-model="review.star" max="5" state-on="'fa fa-star'"
				state-off="'fa fa-star-o'" readonly="true"></rating>
		</div>
		<blockquote class="list-group-item-text">
			<p>{{review.remark}}</p>
			<footer>
				<a ui-sref="userProfile({username:review.user.username})"> <cite
					title="{{review.user.username}}">{{review.user.username}}</cite>
				</a>
			</footer>
		</blockquote>
	</div>
</div>
<pagination ng-show="totalItems > itemsPerPage" ng-model="currentPage"
	total-items="totalItems" items-per-page="itemsPerPage"
	max-size="maxSize" class="pagination-sm" boundary-links="true"
	rotate="false" num-pages="numPages" ng-change="pageChanged()"></pagination>
<pre ng-show="totalItems > itemsPerPage">Page: {{currentPage}} / {{numPages}}</pre>