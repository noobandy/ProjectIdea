<h4>Edit Draft</h4>
<tabset justified="true"> <tab> <tab-heading>
<i class="fa fa-edit"></i> Details </tab-heading>
<div ng-form="editProjectideaForm">
	<alert ng-repeat="alert in alerts" type="{{alert.type}}"
		close="closeAlert($index)"> {{alert.msg}} </alert>
	<div class="form-group">
		<label for="title">Title</label> <input
			ng-model="projectIdeaDraft.title" type="text" class="form-control"
			placeholder="Title" required>
	</div>
	<div class="form-group">
		<label for="description">Description</label>
		<textarea rows="3" cols="5" type="text" class="form-control"
			ng-model="projectIdeaDraft.description"
			placeholder="Brief Description" required />
	</div>
	<div class="form-group">
		<label for="tag">Tags</label>

		<div class="form-control">
			<input type="hidden" ui-select2="select2Options" placeholder="Tags"
				ng-model="projectIdeaDraft.tags" style="width: 100%;" required>

		</div>

	</div>
	<button type="button" ng-if="editProjectideaForm.$dirty"
		class="btn btn-success"
		ng-click="editProjectideaForm.$valid && updateDraft(projectIdeaDraft)">Update</button>
</div>
</tab> <tab> <tab-heading> <i class="fa fa-clock-o"></i>
Time Estimation </tab-heading>
<div ng-form="updateEstimatedTimeForm">
	<div class="form-group">
		<label for="years">Years</label> <select class="form-control"
			ng-model="projectIdeaEstimatedTime.years">
			<option value="">-</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
	</div>
	<div class="form-group">
		<label for="years">Months</label> <select class="form-control"
			ng-model="projectIdeaEstimatedTime.months">
			<option value="">-</option>
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
	<div class="form-group">
		<label for="years">Days</label> <select class="form-control"
			ng-model="projectIdeaEstimatedTime.days">
			<option value="">-</option>
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
	<div class="form-group">
		<button class="btn btn-success"
			ng-click="updateEstimatedTimeForm.$valid && updateEstimatedTime(projectIdeaEstimatedTime)">Update</button>
	</div>
</div>
</tab> <tab> <tab-heading> <i class="fa fa-file-text"></i>
Documents </tab-heading>
<div ng-form="projectIdeaDocumentForm">

	<div class="form-group">
		<label class="label">Documents</label> <input class="form-control"
			type="file" ng-file-select="onFileSelect($files)">
	</div>
	<div class="form-group">
		<button ng-if="projectIdeaDocumentForm.$dirty" class="btn btn-success"
			type="button"
			ng-click="projectIdeaDocumentForm.$valid && updateProjectIdeaDocuments()"></button>
	</div>

</div>
</tab> </tabset>