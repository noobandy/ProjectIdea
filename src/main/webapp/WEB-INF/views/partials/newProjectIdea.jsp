<form name="newProjectideaForm" role="form"
	ng-submit="newProjectideaForm.$valid && saveDraft(newProjectidea)"
	novalidate>
	<alert ng-repeat="alert in alerts" type="{{alert.type}}"
		close="closeAlert($index)">{{alert.msg}}</alert>
	<div class="form-group">
		<label for="title">Title</label> <input
			ng-model="newProjectidea.title" type="text" class="form-control"
			placeholder="Title" required>
	</div>
	<div class="form-group">
		<label for="description">Description</label>
		<textarea rows="3" cols="5" type="text" class="form-control"
			ng-model="newProjectidea.description" placeholder="Brief Description"
			required />
	</div>
	<div class="form-group">
		<label for="tag">Tags</label>

		<div class="form-controle">
			<input type="hidden" ui-select2="select2Options" placeholder="Tags"
				ng-model="newProjectidea.tags" style="width: 100%;" required>

		</div>

	</div>
	<button class="btn btn-success">Save</button>
</form>