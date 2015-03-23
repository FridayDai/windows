<div class="nav">
	<ul id="menu" class="list">
		<li class="nav-li<g:if test="${controllerName == 'clients' || controllerName == 'home'}"> active</g:if>">
			<g:link controller="clients" action="index">
				<div class="ui-icon icon-client"></div>
				<span>CLIENTS</span>
			</g:link>
		</li>
		<li class="nav-li<g:if test="${controllerName == 'announcements'}"> active</g:if>">
			<g:link controller="announcements" action="index">
				<div class="ui-icon icon-announcements"></div>
				<span>ANNOUNCE</span>
			</g:link>
		</li>
		<li class="nav-li<g:if test="${controllerName == 'accounts'}"> active</g:if>">
			<g:link controller="authentication" action="logout">
				<div class="ui-icon icon-account"></div>
				<span>LOGOUT</span>
			</g:link>
		</li>
	</ul>
</div>
