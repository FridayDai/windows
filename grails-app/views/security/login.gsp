<!DOCTYPE html>

<g:set var="scriptPath" value="loginBundle"/>
<g:set var="cssPath" value="login"/>
<g:applyLayout name="form">
    <html>
        <head>
            <title>Welcome to Ratchet Admin Portal</title>
        </head>
        <body>
        <div class="content">
            <h1 class="title">Ratchet Admin Portal</h1>
            <g:form class="form-horizontal" controller="authentication" method="post" action="login" name="loginForm" novalidate="novalidate">
                <g:if test="${errorMsg}">
                    <p class="error" id="error_login" rateLimit="${rateLimit}">${errorMsg}</p>
                </g:if>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                    </div>
                </div>
                <input type="hidden" name="back" value="${backUrl}"/>
                %{--<div class="form-group">--}%
                    %{--<div class="col-sm-offset-2 col-sm-10">--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox"> Remember me--}%
                            %{--</label>--}%
                        %{--</div>--}%
                    %{--</div>--}%
                %{--</div>--}%
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </g:form>
        </div>
        </body>
    </html>
</g:applyLayout>
