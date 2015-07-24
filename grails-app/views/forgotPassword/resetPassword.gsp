<!DOCTYPE html>

<g:set var="scriptPath" value="bundles/resetPasswordBundle"/>
<g:set var="cssPath" value="resetPassword"/>
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

                        <div class="help-block">
                            <p class="center">Please Reset your Password</p>
                        </div>

                    </div>

                    <div class="panel-body">
                        <div class="col-lg-12">
                            <g:form class="form password-form"  uri="/confirm-reset-password" method="post">

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

                                <g:hiddenField name="code" value="${code}"></g:hiddenField>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-4">
                                            <button type="submit" id="btn-reset"
                                                    class="btn btn-primary btn-reset">Reset Password</button>
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
