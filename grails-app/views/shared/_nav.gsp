<div class="nav">
	<ul id="menu" class="list">
		<li class="nav-li<g:if test="${controllerName == 'clients' || controllerName == 'home'}"> active</g:if>">
			<g:link controller="clients" action="index">
				<div class="ui-icon icon-client"></div>
				<span>CLIENTS</span>
			</g:link>
		</li>
		<li class="nav-li<g:if test="${controllerName == 'accounts'}"> active</g:if>">
			<g:link controller="accounts" action="index">
				<div class="ui-icon icon-account"></div>
				<span>ACCOUNTS</span>
			</g:link>
		</li>
	</ul>
</div>
