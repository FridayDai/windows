<!DOCTYPE html>

<g:set var="scriptPath" value="profileBundle"/>
<g:set var="cssPath" value="profile"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>

    <div class="content">
        <div class="tool-bar">
            <button type="button" id="update-password" class="rc-line-space btn btn-primary"
                    data-toggle="modal" data-target="#change-password-modal">Change Password</button>
        </div>

        <g:link controller="authentication" action="logout" class="btn btn-danger">Log Out</g:link>

    </div>

    %{-- Modal dialog --}%
    <div class="modal fade" id="change-password-modal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Change Password</h4>
                </div>

                <div class="modal-body">
                    <div class="alert alert-danger rc-server-error" role="alert"></div>

                    <g:form controller="profile" action="updatePassword" method="POST" class="form form-horizontal">
                        <div class="form-group">
                            <label for="old-password" class="col-sm-5 control-label">* OLD PASSWORD:</label>

                            <div class="col-sm-6">
                                <input id="old-password" name="old-password" type="password" class="form-control"
                                       placeholder="Enter old password" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="new-password" class="col-sm-5 control-label">* NEW PASSWORD:</label>

                            <div class="col-sm-6">
                                <input id="new-password" name="new-password" type="password" class="form-control"
                                       placeholder="Enter new password" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="confirm-password" class="col-sm-5 control-label">* CONFIRM PASSWORD:</label>

                            <div class="col-sm-6">
                                <input id="confirm-password" name="confirm-password" type="password" class="form-control"
                                       placeholder="Enter new password again" required/>
                            </div>
                        </div>

                        <div class="error-area error hide error-password" id="confirmPass-error">
                            Passwords do not match, please retype.
                        </div>
                    </g:form>


                </div>

                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">Cancel</button>
                    <button class="create-btn btn btn-primary" type="button"
                            data-loading-text="Creating">Create</button>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</g:applyLayout>
