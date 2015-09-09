<!DOCTYPE html>
<g:set var="cssPath" value="backup"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>

    <div class="content">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Backup Date</td>
            </tr>
            </thead>
            <tbody>

            <g:if test="${links}">
                <g:each in="${links}" var="link">
                    <% def linkDate = link?.split("/dump_") %>
                    <% def date = linkDate[1].split(".sql")%>
                    <tr>
                        <td class="col-md-4">
                            <a href="${link}" target="_blank">${date[0]}</a>
                        </td>
                    </tr>
                </g:each>
            </g:if>

            </tbody>
        </table>

    </div>
    </body>
    </html>
</g:applyLayout>
