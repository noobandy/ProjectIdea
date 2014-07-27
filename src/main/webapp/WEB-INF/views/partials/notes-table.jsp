<div class="row">
	<div class="col-md-3">
		<form name="addNoteForm" ng-if="hasPermission('ROLE_ADMIN')" ng-submit="addNoteForm.$valid && addNote(inputNote)" novalidate>
			<div class="form-group">
				<textarea class="form-control" ng-model="inputNote" required></textarea>
			</div>
			<button type="submit" class="btn btn-primary">Add Note</button>
		</form>
	</div>
	<div class="col-md-9">
		<div class="row">
			<div class="col-md-6">
				<form>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="glyphicon glyphicon-search"></i>
							</div>
							<input ng-model="search" class="form-control" type="text"
								placeholder="Search">
						</div>
					</div>
				</form>
			</div>
		</div>
		<table  ng-table="tableParams" class="table table-striped">
			<thead>
				<tr>
					<th>Note</th>
					<th>Date</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<tr
					ng-repeat="note in notes">
					<td ng-if="hasPermission('ROLE_ADMIN')">
					<a href="#" editable-text="note.note"
						onbeforesave="updateNote(note.id,note.createdAt,$data)">{{note.note}}</a>
						
					</td>
					<td ng-if="!hasPermission('ROLE_ADMIN')" filter="{ 'name': 'text' }">
					{{note.note}}
					</td>
					<td>{{note.createdAt | date:'short' }}</td>
					<td>
						<button ng-if="hasPermission('ROLE_ADMIN')" class="btn btn-danger" ng-click="deleteNote(note)"><i class="glyphicon glyphicon-trash"></i></button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>