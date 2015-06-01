<!DOCTYPE html>

<g:set var="scriptPath" value="accountsBundle"/>
<g:set var="cssPath" value="activateAccount"/>
<g:applyLayout name="form">
    <html>
    <head>
        <title>Welcome to Ratchet Health</title>
    </head>

    <body>

    <div class="site-wrapper">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-border">

                    <div class="panel-heading">

                        <div class="greeting color-black align-center">
                            %{--<span>Hi</span>--}%
                            %{--<g:if test="${staff.doctor == true}"><span>Dr.</span></g:if>--}%
                            %{--<span>${staff.firstName} ${staff.lastName}!</span>--}%
                        </div>

                        <div class="help-block">
                            <p class="center">Welcome to Ratchet Health!</p>
                        </div>

                    </div>

                    <div class="panel-body">
                        <div class="col-lg-12">
                            <g:form action="/confirm-password" method="post" class="form create-password-form">

                                <div class="form-group">
                                    <label for="newPassword">NEW PASSWORD</label>
                                    <input name="newPassword" id="newPassword" type="password" class="form-control"
                                           placeholder="Enter Password" required/>
                                </div>

                                <div class="form-group">
                                    <label for="confirmPassword">CONFIRM PASSWORD</label>
                                    <input name="confirmPassword" id="confirmPassword" type="password"
                                           class="form-control" placeholder="Confirm Password" required/>
                                </div>

                                <div class="error-area">
                                </div>

                                <input type="hidden" name="code" value="${code}"/>
                                %{--<input type="hidden" name="hasProfile" value="${staff.hasProfile}"/>--}%

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-4">
                                            <button type="submit" class="btn btn-primary btn-submit"
                                                    id='joinRat'>Activate Account</button>
                                        </div>
                                    </div>
                                </div>

                            </g:form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    </body>
    </html>
</g:applyLayout>
