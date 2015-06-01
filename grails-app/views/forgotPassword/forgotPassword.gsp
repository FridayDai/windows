<!DOCTYPE html>

<g:set var="scriptPath" value="forgotPasswordBundle"/>
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

                    <div class="panel-body">
                        <div class="col-lg-12">
                            <form action="/forgot-password" method="post" class="form password-form"
                                  novalidate="novalidate">

                                <div class="form-group">
                                    <label for="email">EMAIL ADDRESS</label>
                                    <input id="email" name="email" type="text" class="form-control email"
                                           placeholder="Enter E-mail"
                                           required/>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-4">
                                            <button type="submit"
                                                    class="btn btn-primary btn-reset">Reset Password</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    </body>
    </html>
</g:applyLayout>
