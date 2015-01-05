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

            <div class="form-group">
                <input name="username" type="text" class="input-group" placeholder="Name"/>
            </div>

        </g:form>

    </div>
    </body>
    </html>
</g:applyLayout>
