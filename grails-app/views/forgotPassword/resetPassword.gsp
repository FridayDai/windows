<!DOCTYPE html>

<g:set var="commonScriptPath" value="dist/commons.chunk.js"/>
<g:set var="scriptPath" value="dist/passwordReset.bundle.js"/>
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

                    <div class="panel-body reset-password-form">
                        <div class="col-lg-12">
                            <g:form class="form password-form"  uri="/confirm-reset-password" method="post">

                                <div class="form-group">
                                    <label for="newPassword" class="control-label">NEW PASSWORD</label>
                                    <input name="newPassword" id="newPassword" type="password" class="form-control"
                                           placeholder="Enter Password" required/>
                                </div>

                                <div class="form-group">
                                    <label for="confirmPassword" class="control-label">CONFIRM PASSWORD</label>
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
