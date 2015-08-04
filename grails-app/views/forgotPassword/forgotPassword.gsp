<!DOCTYPE html>

%{--<g:set var="scriptPath" value="bundles/forgotPasswordBundle"/>--}%
<g:set var="commonScriptPath" value="dist/commons.chunk.js"/>
<g:set var="scriptPath" value="dist/passwordForget.bundle.js"/>
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
                        <div>
                            <h4 class="center">Hi!</h4>

                            <div class="help-block">
                                <p class="center">Please enter your email address to reset your password</p>
                            </div>
                        </div>
                    </div>

                    <div class="panel-body forget-password-form">
                        <div class="col-lg-12">
                            <g:form method="post" class="form password-form" novalidate="novalidate">

                                <div class="form-group">
                                    <label for="email" class="control-label">EMAIL ADDRESS</label>
                                    <input id="email" name="email" type="text" class="form-control email"
                                           placeholder="Enter E-mail"
                                           required/>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-4">
                                            <button class="btn-forget-password btn btn-primary btn-reset">Reset Password</button>
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
