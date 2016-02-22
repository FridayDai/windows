<!DOCTYPE html>

<g:set var="commonScriptPath" value="dist/commons.chunk.js"/>
<g:set var="cssPath" value="hl7/reporting"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>
    <div class="content">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a>Reporting</a></li>
            <li role="presentation"><a href="/hl7/failures">Failures</a></li>
        </ul>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Queue Status</h3>
            </div>
            <div class="panel-body">
                <div class="overview-heros message-in col-md-3 col-md-offset-3">
                    <div class="hero-text">300</div>
                    <div class="sub-text">Messages</div>
                </div>
                <div class="overview-heros message-delayed col-md-3">
                    <div class="hero-text">300</div>
                    <div class="sub-text">Messages Delayed</div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Message Status</h3>
            </div>
            <div class="panel-body">
                <div class="panel-body">
                    <div class="overview-heros started col-md-2">
                        <div class="hero-text">300</div>
                        <div class="sub-text">Started</div>
                    </div>
                    <div class="overview-heros failed col-md-2">
                        <div class="hero-text">300</div>
                        <div class="sub-text">Failed</div>
                    </div>
                    <div class="overview-heros received col-md-2">
                        <div class="hero-text">300</div>
                        <div class="sub-text">Received</div>
                    </div>
                    <div class="overview-heros parsed col-md-2">
                        <div class="hero-text">300</div>
                        <div class="sub-text">Parsed</div>
                    </div>
                    <div class="overview-heros saved col-md-2">
                        <div class="hero-text">300</div>
                        <div class="sub-text">Saved</div>
                    </div>
                    <div class="overview-heros completed col-md-2">
                        <div class="hero-text">300</div>
                        <div class="sub-text">Completed</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</g:applyLayout>
