<!DOCTYPE html>

<g:set var="scriptPath" value="treatmentDetailBundle"/>
<g:set var="cssPath" value="treatmentDetail"/>
<g:applyLayout name="main">
	<html>
	<head>
		<title>Welcome to ratchet treatment detail</title>
	</head>

	<body>
	<div class="content">
		<div id="treatment-info-panel" class="panel row" data-client-id="${clientId}"
			 data-treatment-id="${treatment.id}">
			<div class="main-info col-sm-3">
				<div class="id"><strong>ID:</strong> ${treatment.id}</div>

				<div class="title rc-line-space"><strong>${treatment.title}</strong></div>

				<div class="last-update rc-line-space"><strong>Last Update:</strong><br><g:formatDate
						date="${new java.util.Date(treatment.lastUpdated)}"
						format="MMM d, yyyy h:mm:ss a"></g:formatDate></div>

				<div class="template-title rc-line-space"><strong>Template Title:</strong> <span
						class="text">${treatment.tmpTitle}</span></div>

				<div class="surgery-time-required rc-line-space"><strong>Surgery Time Required:</strong> <span
						class="text">${treatment.surgeryTimeRequired ? 'Yes' : 'No'}</span></div>

				<div class="status rc-line-space"><strong>Status:</strong> <span
						class="text">${treatment.active ? 'Active' : 'Closed'}</span></div>

				<div class="active-patient rc-line-space"><strong>Active Patient:</strong> 100</div>
			</div>

			<div class="description col-sm-6">${treatment.description}</div>

			<div class="action col-sm-3">
				<div class="clearfix">
					<button type="button" class="btn btn-default pull-right" data-toggle="modal"
							data-target="#edit-treatment-modal">
						<span class="edit-btn glyphicon glyphicon-edit"></span>
					</button>
				</div>

				<div class="rc-line-space clearfix">
					<button type="button" class="btn btn-default pull-right" data-toggle="modal"
							data-target="#close-treatment-modal">
						Close Treatment
					</button>
				</div>
			</div>
		</div>

		<div id="edit-treatment-modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Edit Treatment</h4>
					</div>

					<div class="modal-body">
						<form action="/clients/${clientId}/treatments/${treatment.id}" method="post"
							  class="form form-horizontal" enctype="multipart/form-data" novalidate="novalidate">
							<div class="form-group">
								<label for="edit-treatment-title"
									   class="col-sm-5 control-label">* Treatment Title:</label>

								<div class="col-sm-6">
									<input id="edit-treatment-title" name="title" type="text" class="form-control"
										   required/>
								</div>
							</div>

							<div class="form-group">
								<label for="edit-treatment-tmpTitle"
									   class="col-sm-5 control-label">* Template Title:</label>

								<div class="col-sm-6">
									<input id="edit-treatment-tmpTitle" name="tmpTitle" type="text" class="form-control"
										   required/>
								</div>
							</div>

							<div class="form-group">
								<label for="edit-treatment-surgeryTimeRequired"
									   class="col-sm-5 control-label">* Surgery Time Required:</label>

								<div class="col-sm-6">
									<select name="surgeryTimeRequired" id="edit-treatment-surgeryTimeRequired">
										<option value="false">No</option>
										<option value="true">Yes</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="edit-treatment-description"
									   class="col-sm-5 control-label">* Description:</label>

								<div class="col-sm-6">
									<textarea class="form-control" name="description" id="edit-treatment-description"
											  cols="30" rows="10" required></textarea>
								</div>
							</div>
						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="button" class="update-btn btn btn-primary"
								data-loading-text="Updating">Update</button>
					</div>
				</div>
			</div>
		</div>

		<div id="close-treatment-modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Close Treatment</h4>
					</div>

					<div class="modal-body">
						Are you sure to close this treatment?
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="button" class="delete-btn btn btn-primary" data-loading-text="Deleting"
								data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<div id="tool-pool-list-panel" class="panel">
			<div class="tool-bar clearfix">
				<h3 class="title sub-title pull-left">Tool Pool</h3>

				<div class="action pull-right">
					<div class="btn-group">
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false">
							Add Tool <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" class="basic" data-toggle="modal"
								   data-target="#add-basic-tool-modal">Basic</a></li>
							<li><a href="#">Outcome</a></li>
							<li><a href="#">SDM</a></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="table-container">
				<table id="tool-pool-table"></table>
			</div>
		</div>

		<div id="add-basic-tool-modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Add Tool</h4>
					</div>

					<div class="modal-body">
						<form action="/clients/${clientId}" method="post" class="form form-horizontal"
							  enctype="multipart/form-data" novalidate="novalidate">
							<div class="form-group">
								<label for="treatment-title" class="col-sm-4 control-label">* Tool:</label>

								<div class="col-sm-6">
									<div class="dropdown">
										<button id="addToolLabel" type="button" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="false">
											Select One Tool <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu" aria-labelledby="addToolLabel">
											<li><a href="#">DASH</a></li>
										</ul>
									</div>
								</div>
							</div>
						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="button" class="create-btn btn btn-primary" data-loading-text="Creating"
								data-dismiss="modal">Create</button>
					</div>
				</div>
			</div>
		</div>

		<div id="task-list-panel" class="panel">
			<div class="tool-bar clearfix">
				<h3 class="title sub-title pull-left">Task</h3>

				<div class="action pull-right">
					<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#add-task-modal">Add</button>
				</div>
			</div>

			<div class="table-container">
				<table id="task-table"></table>
			</div>
		</div>

		<div id="add-task-modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Add Task</h4>
					</div>

					<div class="modal-body">
						<form action="/clients/${clientId}" method="post" class="form form-horizontal"
							  enctype="multipart/form-data" novalidate="novalidate">
							<div class="form-group">
								<label for="treatment-title" class="col-sm-5 control-label">* Tool Title:</label>

								<div class="col-sm-6">
									<input id="treatment-title" name="treatment-title" type="text" class="form-control"
										   required/>
								</div>
							</div>

							<div class="form-group">
								<label for="template-title" class="col-sm-5 control-label">* Template Title:</label>

								<div class="col-sm-6">
									<input id="template-title" name="template-title" type="text" class="form-control"
										   required/>
								</div>
							</div>

							<div class="form-group">
								<label for="description" class="col-sm-5 control-label">* Description:</label>

								<div class="col-sm-6">
									<textarea class="form-control" name="description" id="description" cols="30"
											  rows="10"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="content" class="col-sm-5 control-label">* Content:</label>

								<div class="col-sm-6">
									<textarea class="form-control" name="content" id="content" cols="30"
											  rows="10"></textarea>
								</div>
							</div>
						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="button" class="create-btn btn btn-primary" data-loading-text="Creating"
								data-dismiss="modal">Create</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
	</html>
</g:applyLayout>
