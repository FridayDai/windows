<!DOCTYPE html>
<g:set var="cssPath" value="testData"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>

    <div class="content">
        <g:if test="${links}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Test Data</td>
            </tr>
            </thead>
            <tbody>
                <g:each in="${links}" var="link">
                    <% def pattern = ~/(\d{4}\-\d{2}\-\d{2})/ %>
                    <% def linkDate = pattern.matcher(link) %>
                    <tr>
                        <td class="col-md-4">
                            <a href="${link}" target="_blank">${linkDate[0][0]}</a>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        </g:if>
        <g:else>
            <p> <strong>There is no test data.</strong></p>
        </g:else>
        <form method="post">
            <button class="btn btn-default btn-primary" type="submit">Generate anonymized snapshot</button>
            <input type="hidden" name="isDataAnonymized" value="true" />
        </form>

        <form method="post">
            <button class="btn btn-default btn-primary" type="submit">Generate snapshot</button>
            <input type="hidden" name="isDataAnonymized" value="false" />
        </form>

    </div>
    </body>
    </html>
</g:applyLayout>
