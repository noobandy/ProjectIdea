<h4>New Draft</h4>
<form class="form-horizontal" name="newProjectideaForm" role="form"
	ng-submit="newProjectideaForm.$valid && saveDraft(newProjectidea)"
	novalidate>
	<alert ng-repeat="alert in alerts" type="{{alert.type}}"
		close="closeAlert($index)">{{alert.msg}}</alert>
	<div class="form-group">
		<label for="title" class="col-md-2 control-label">Title</label>
		<div class="col-sm-10">
			<input ng-model="newProjectidea.title" type="text"
				class="form-control" placeholder="Title" required>
		</div>
	</div>
	<div class="form-group">
		<label for="description" class="col-md-2 control-label">Description</label>
		<div class="col-md-10">
			<textarea rows="3" cols="5" type="text" class="form-control"
				ng-model="newProjectidea.description"
				placeholder="Brief Description" required />
		</div>
	</div>
	<div class="form-group">
		<label for="tag" class="col-md-2 control-label">Tags</label>
		<div class="col-md-10">
			<input class="form-control" ui-select2="select2Options"
				placeholder="Tags" ng-model="newProjectidea.tags"
				style="width: 100%;" required>
		</div>
	</div>
	<div class="form-group">
		<label for="years" class="col-md-2 control-label">Years</label>
		<div class="col-md-10">
			<select class="form-control" ng-model="newProjectidea.years">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="years" class="col-md-2 control-label">Months</label>
		<div class="col-md-10">
			<select class="form-control" ng-model="newProjectidea.months">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="years" class="col-md-2 control-label">Days</label>
		<div class="col-md-10">
			<select class="form-control" ng-model="newProjectidea.days">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-offset-2 col-md-10">
			<button type="submit" class="btn btn-success">Save</button>
		</div>
	</div>
</form>