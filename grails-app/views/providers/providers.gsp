<!DOCTYPE html>

<g:set var="scriptPath" value="providersBundle"/>
<g:set var="cssPath" value="providers"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>
    <div class="content">
        <a href="#" id="add-provider" class="btn">Add Provider</a>

        <div class="provider-list-table">
            <table >

            </table>
        </div>

        <g:form class="form ui-hidden" controller="authentication" method="post" action="login">
            <g:if test="${errorMsg}">
                <p class="error">${errorMsg}</p>
            </g:if>
            <div class="login">
                <input name="username" type="text" class="form-control" placeholder="Username"/>
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

    </div>
    </body>
    </html>
</g:applyLayout>
