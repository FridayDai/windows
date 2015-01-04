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
            <table id="provideTable" class="display">
                <thead>
                    <tr>
                        <th></th>
                        <th>Provider</th>
                        <th>Agent</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                %{--<tbody>--}%
                    %{--<tr>--}%
                        %{--<th>1</th>--}%
                        %{--<th>222</th>--}%
                        %{--<th>3333</th>--}%
                        %{--<th>44444</th>--}%
                        %{--<th>555555</th>--}%
                    %{--</tr>--}%
                %{--</tbody>--}%

            </table>
        </div>

        <g:form class="form ui-hidden" controller="authentication" method="post" action="login">

            <div class="form-li">
                <input id="provider" name="provider" type="text" class="input-li" placeholder="Provider"/>
                <input id="agent" name="agent" type="text" class="input-li" placeholder="Agent"/>
                <input id="email" name="email" type="text" class="input-li" placeholder="Email"/>

            </div>

        </g:form>

    </div>
    </body>
    </html>
</g:applyLayout>
