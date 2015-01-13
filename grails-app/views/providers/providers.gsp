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
        <a href="#" id="add-provider" class="btn add-provider">Add Provider</a>
        %{--<a href="providers/detail">View</a>--}%

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

        <g:form class="form ui-hidden" id="table-form" name="table-form">

            <div class="form-group">
                <input id="provider" name="provider" type="text" class="input-group" placeholder="Provider" required/>
            </div>

            <div class="form-group">
                <input id="agent" name="agent" type="text" class="input-group" placeholder="Agent" required/>
            </div>

            <div class="form-group">
                <input id="email" name="email" type="email" class="input-group" placeholder="Email" required/>
            </div>

        </g:form>

        <div class="selector ui-hidden">
            <div class="selector-list">
                <a href="#" class="selector-header" id="selector-head">Actions</a>

                <ul class="selector-body displaynone" id="selector-choice">
                    <li><a href="#" class="btn-selector btn-color btn-separate">View</a></li>

                    <li><a href="#" class="btn-selector btn-color">Remove</a></li>
                </ul>
            </div>
        </div>

        <g:form class="warn ui-hidden">

        </g:form>

    </div>
    </body>
    </html>
</g:applyLayout>
