<g:set var="scriptPath" value="announcementsBundle"/>
<g:set var="cssPath" value="announcements"/>
<g:applyLayout name="main">
	<html>
		<head>
			<title>Welcome to ratchet</title>
		</head>

		<body>
			<div class="content">
				<div class="tool-bar clearfix">
					<button type="button" id="add-client" class="add-client pull-right rc-line-space btn btn-primary"
							data-toggle="modal" data-target="#client-modal">New Annoucement</button>
				</div>

				<div class="announcements-list-table">
					<table id="announcements-table" class="display" data-total="${annouceList.recordsTotal}" data-pagesize="${pagesize}">
						<thead>
							<tr>
								<td>ID</td>
								<td>Annoucement</td>
								<td>Status</td>
								<td>Background</td>
								<td>Created Time</td>
								<td></td>
							</tr>							
						</thead>
						<tbody>
						<g:each var="annouce" in="${annouceList.data}" status="i">
							<tr data-is-dom-data="true">
								<td>${annouce.id}</td>
								<td>${annouce.announcement}</td>
								<td>${annouce.status}</td>
								<td>${annouce.background}</td>
								<td>${annouce.timeCreated}</td>
								<td><span class="copy-btn glyphicon glyphicon-copy" aria-hidden="true" data-row="${i}"></span></td>
							</tr>
						</g:each>
						</tbody>						
					</table>
				</div>
			</div>
		</body>
	</html>
</g:applyLayout>
