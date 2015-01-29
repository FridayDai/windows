<!DOCTYPE html>

<g:set var="scriptPath" value="clientsBundle"/>
<g:set var="cssPath" value="clients"/>
<g:applyLayout name="main">
	<html>
	<head>
		<title>Welcome to ratchet</title>
	</head>

	<body>
	<div class="content">
		<div class="tool-bar clearfix">
			<button type="button" id="add-client" class="add-client pull-right rc-line-space btn btn-primary"
					data-toggle="modal" data-target="#client-modal">New Client</button>
		</div>

		<div class="client-list-table">
			<table id="client-table" class="display">
				<thead>
					<tr>
						<td>ID</td>
						<td>Client</td>
						<td>Active Staff</td>
						<td>Active Patient</td>
						<td>Active Treatment</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
				<g:each var="client" in="${clientList.items}" status="i">
					<tr data-is-dom-data="true"
						data-sub-domain="${client.subDomain}"
						data-portal-name="${client.portalName}"
						data-primary-color-hex="${client.primaryColorHex}"
						data-client-staff='{
							"id": ${client.clientStaff?.id ?: 0},
							"email":"${client.clientStaff?.email}",
							"firstName": "${client.clientStaff?.firstName}",
							"lastName": "${client.clientStaff?.lastName}"
						}'
					>
						<td>${client.id}</td>
						<td>${client.name}</td>
						<td>${client.activeStaffCount}</td>
						<td>${client.activePatientCount}</td>
						<td>${client.activeTreatmentCount}</td>
						<td><span class="copy-btn glyphicon glyphicon-copy" aria-hidden="true" data-row="${i}"></span></td>
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>

		%{-- Modal dialog --}%
		<div class="modal fade" id="client-modal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">New Client</h4>
					</div>
					<div class="modal-body">
						<g:uploadForm controller="clients" method="post" name="tableForm"
								class="form form-horizontal" novalidate="novalidate">
							<div class="form-group">
								<label for="name" class="col-sm-5 control-label">* Client Name:</label>

								<div class="col-sm-6">
									<input id="name" name="name" type="text" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="subDomain" class="col-sm-5 control-label">* Subdomain:</label>

								<div class="col-sm-6">
									<input id="subDomain" name="subDomain" type="text" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="portalName" class="col-sm-5 control-label">* Patient Portal Name:</label>

								<div class="col-sm-6">
									<input id="portalName" name="portalName" type="text" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="primaryColorHex" class="col-sm-5 control-label">* Primary Color Hex:</label>

								<div class="col-sm-6">
									<input id="primaryColorHex" name="primaryColorHex" type="text" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="logo" class="col-sm-5 control-label">* Logo:</label>

								<div class="col-sm-6">
									<input type="file" id="logo" name="logo" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="agentFirstName" class="col-sm-5 control-label">* Agent First Name:</label>

								<div class="col-sm-6">
									<input type="text" id="agentFirstName" name="agentFirstName" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="agentLastName" class="col-sm-5 control-label">* Agent Last Name:</label>

								<div class="col-sm-6">
									<input type="text" id="agentLastName" name="agentLastName" class="form-control" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="agentEmail" class="col-sm-5 control-label">* Agent Email:</label>

								<div class="col-sm-6">
									<input type="email" id="agentEmail" name="agentEmail" class="form-control" required/>
								</div>
							</div>
						</g:uploadForm>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" type="button" data-dismiss="modal">Cancel</button>
						<button class="create-btn btn btn-primary" type="button" data-loading-text="Creating">Create</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
	</html>
</g:applyLayout>
