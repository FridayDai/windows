<!DOCTYPE html>

<g:set var="scriptPath" value="loginBundle"/>
<g:set var="cssPath" value="login"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>

    <h1>This is Login Page</h1>

        <g:form class="form" controller="authentication" method="post" action="login">
            <g:if test="${errorMsg}">
               <p class="error">${errorMsg}</p>
            </g:if>
            <div class="login">
                <input name="email" type="text" class="form-control" placeholder="Email"/>
            </div>

            <div class="login">
                <input name="password" type="password" class="form-control" placeholder="Password"/>
            </div>

            <div class="login">
                <input type="submit" class="btn_submit" id='btn_login' value="Log In"/>
                %{--<g:if test="${errorMsg}">--}%
                    %{--${errorMsg}--}%
                %{--</g:if>--}%
            </div>
        </g:form>

    </body>
    </html>
</g:applyLayout>
