<form name="newProjectideaForm" role="form" novalidate>
	<div class="form-group">
		<label for="title">Title</label> <input
			ng-model="newProjectidea.title" type="text" class="form-control"
			placeholder="Title">
	</div>
	<div class="form-group">
		<label for="description">Description</label>
		<textarea rows="3" cols="5" type="text" class="form-control"
			ng-model="newProjectidea.description" placeholder="Brief Description" />
	</div>
	<div class="form-group">
		<label for="description">Tags</label> <select id="tagSelect"  multiple="multiple" class="form-control">
			<option ng-repeat="tag in tags" ng-selected="newProjectidea.tags.selected"  value="tag.tag">
			{{tag.tag}}
			</option>
		</select>
	</div>

	<div class="form-group">
		<label for="exampleInputFile">File input</label> <input type="file"
			id="exampleInputFile">
		<p class="help-block">Example block-level help text here.</p>
	</div>
	<div class="checkbox">
		<label> <input type="checkbox"> Check me out
		</label>
	</div>
	<button type="submit" class="btn btn-default">Save</button>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$('#tagSelect').select2();
})
</script>