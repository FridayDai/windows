<!DOCTYPE html>

<g:set var="commonScriptPath" value="dist/commons.chunk.js"/>
<g:set var="cssPath" value="hl7/failures"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>
    <div class="content">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="/hl7/reporting">Reporting</a></li>
            <li role="presentation" class="active"><a>Failures</a></li>
        </ul>
        <table class="table table-hover">
            <thead>
            <tr>
                <td width="10%">Job ID</td>
                <td width="10%">Client</td>
                <td width="10%">File Name</td>
                <td width="10%">Hashed File Name</td>
                <td width="10%">Received Date</td>
                <td width="10%">Fail Reason</td>
                <td width="30%">Fail Desc</td>
                <td width="10%"></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>2157c634-cfed-4549-9112-0ccfa5dd49cb</td>
                <td>Testing</td>
                <td>d78cf590-5de9-47c6-b9fe-1552f505e70d.anon</td>
                <td class="hash-name-cell">a2e3ce8e4a606f413bee18f0712a8aa342e9e65d69efcd698b2f032f822b2376</td>
                <td>2016-02-18T06:15:04+0000</td>
                <td>Duplicate</td>
                <td></td>
                <td><button>Retry</button></td>
            </tr>
            <tr>
                <td>26a20f7b-b875-4ed7-895b-78e20e562aa3</td>
                <td>Testing</td>
                <td>2153ade8-3b59-4c90-815f-f8dc37a96748</td>
                <td class="hash-name-cell">f1db5eab7cebb36cb68345ac3edb014776e673e34c7fc1b5f737c528f0352d63</td>
                <td>2016-02-18T02:49:12+0000</td>
                <td>FetchFile</td>
                <td>Unable to execute HTTP request: com-ratchethealh-hl7-error-proliance.s3-us-west-1.amazonaws.com failed to respond</td>
                <td><button>Retry</button></td>
            </tr>
            </tbody>
        </table>
    </div>
    </body>
    </html>
</g:applyLayout>
