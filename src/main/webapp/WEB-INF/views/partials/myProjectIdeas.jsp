<div class="row">
	<tabset justified="true" type="pills"> <tab
		ui-sref="myProjectIdeas.drafted.projectIdeas">
		<tab-heading>
		Saved Draft <i class="fa fa-folder"></i>
		</tab-heading>
		 <ui-view /> </tab> <tab
		ui-sref="myProjectIdeas.published.projectIdeas">
		<tab-heading>
		Published <i class="fa fa-share-alt"></i>
		</tab-heading>
		 <ui-view /></tab>
	</tabset>
</div>