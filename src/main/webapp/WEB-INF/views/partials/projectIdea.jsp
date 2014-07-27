<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				{{projectIdea.title}} <span
					ng-repeat-start="tag in projectIdea.tags" ng-bind="tag"
					class="label label-primary"></span> <span ng-repeat-end></span>
			</div>
			<div class="panel-body">
				<div class="col-md-10">
					<p>{{projectIdea.description}}</p>
				</div>
				<div class="col-md-2">
					<a class="thumbnail" href="#"> <img
						ng-src="//placehold.it/64x64" alt="avtar" />
						<h3 class="caption">{{projectIdea.author}}</h3>
					</a>
				</div>
				<section>
					<ul class="nav nav-pills">
						<li ng-class="{ active:isSelected(1)}"><a href
							ng-click="selectTab(1)">Documents</a></li>
						<li ng-class="{ active:isSelected(2)}"><a href
							ng-click="selectTab(2)">Reviews</a></li>
					</ul>
					<div class="panel" ng-show="isSelected(1)">
						<h4>Description</h4>
						<p>{{projectIdea.documents}}</p>
					</div>
					<div class="panel" ng-show="isSelected(2)">
						<h4>Reviews</h4>
						<blockquote ng-repeat="review in projectIdea.reviews">
							<b>Stars: {{review.stars}}</b> {{review.remark}} <cite>by
								: {{review.author}}</cite>
						</blockquote>

						<form class="form-horizontal" name="reviewForm"
							ng-submit="reviewForm.$valid && addReview(projectIdea)"
							novalidate>
							<blockquote>
								<b>Stars: {{review.stars}}</b> {{review.remark}} <cite>by
									: {{review.author}}</cite>
							</blockquote>

							<select class="form-control" ng-model="review.stars"
								required>
								<option value="1">One Star</option>
								<option value="2">Two Stars</option>
								<option value="3">Three Stars</option>
								<option value="4">Four Stars</option>
								<option value="5">Five Stars</option>
							</select>
							<textarea class="form-control"
								ng-model="review.remark" placeholder="Remark"
								required></textarea>
							<input class="form-control" type="email"
								ng-model="review.author"
								placeholder="someone@gmail.com" required></input> <input
								class="pull-right btn btn-primary" type="submit" />
						</form>
					</div>
				</section>
			</div>
		</div>
	</div>
</div>