<!DOCTYPE html>

<g:set var="scriptPath" value="loginBundle"/>
<g:set var="cssPath" value="login"/>
<g:applyLayout name="form">
    <html>
        <head>
            <title>Welcome to ratchet</title>
        </head>
        <body>
            <g:form class="form" controller="authentication" method="post" action="login" name="loginForm" novalidate="novalidate">
                <g:if test="${errorMsg}">
                    <p class="error" id="error_login" rateLimit="${rateLimit}">${errorMsg}</p>
                </g:if>
                <div>
                    <input name="email" type="email" class="form-control" placeholder="Email" required/>
                </div>

                <div>
                    <input name="password" type="password" class="form-control" placeholder="Password" required/>
                </div>

                <div>
                    <input type="submit" class="btn-submit" id='btn_login' value="Log In"/>
                </div>
            </g:form>
        </body>
    </html>
</g:applyLayout>
