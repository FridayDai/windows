<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
		<asset:stylesheet src="css/common.css"/>
		<!--[if IE 8 ]>    <asset:stylesheet src="css/ie.css"/> <![endif]-->
		<g:if test="${cssPath}">
			<asset:stylesheet src="css/pages/${cssPath}"/>
		</g:if>
		<g:layoutHead/>
	</head>
	<body>
		<div role="banner" class="header">
			<div class="toolbar">
				<div class="pull-left">
					<a href="" class="logo"><img src="${assetPath(src: 'logo.png')}"><span>RATCHET</span><span>ADMIN</span></a>
				</div>
				<div class="pull-right ">
					<div class="login-info">
						<div class="set-block">
							<a class="btn icon-set"></a>
						</div>
						<ul class="profile">
							<li class="">logged in as</li>
							<li class=""><a class="name" href="#">sid</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="nav">
			<ul id="menu" class="list">
				<li>
					<span class="ui-icon icon-provider"></span>
					<a href="/ratchet-v2-admin-portal/providers" class="providers">
						<span>PROVIDERS</span>
					</a>
				</li>
				<li>
					<span class="icon-tool"></span>
					<a href="/ratchet-v2-admin-portal/tools" class="tools">
						<span>TOOLS</span>
					</a>
				</li>
				<li>
					<span class="icon-practice"></span>
					<a href="/ratchet-v2-admin-portal/practice" class="practice">
						<span>BEST PRACTICE</span>
					</a>
				</li>
				<li>
					<span class="icon-treatment"></span>
					<a href="/ratchet-v2-admin-portal/treatments" class="treatments">
						<span>TREATMENTS</span>
					</a>
				</li>
				<li>
					<span class="icon-library"></span>
					<a href="/ratchet-v2-admin-portal/library" class="library">
						<span>LIBRARY</span>
					</a>
				</li>
				<li>
					<span class="icon-account"></span>
					<a href="/ratchet-v2-admin-portal/accounts" class="account">
						<span>ACCOUNTS</span>
					</a>
				</li>
			</ul>
		</div>
		<div class="container">
			<g:layoutBody/>
		</div>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

		<g:if test="${scriptPath}">
			<asset:javascript src="bundles/${scriptPath}"/>
		</g:if>
		<g:else>
			<asset:javascript src="bundles/defaultBundle"/>
		</g:else>
	</body>
</html>
