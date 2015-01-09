<!DOCTYPE html>

<g:set var="scriptPath" value="toolsBundle"/>
<g:set var="cssPath" value="tools"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet</title>
    </head>

    <body>
    <div class="content">
        <div>
            <h1 class="h1">Tools</h1>
            <div class="btn-group form-list">
                <button class="btn btn-dropdown">Choose Treatment
                    <span class="caret"></span>
                </button>

                <ul class="dropdown-list">
                        <li class="form-li"><input type="checkbox">Ratator Cuff</li>
                        <li class="form-li"><input type="checkbox">Total Knee Replacement</li>
                        <li class="form-li"><input type="checkbox">Microdiscectomy</li>
                        <li class="center">
                            <button class="btn">Cancel</button>
                            <button class="btn">OK</button>
                        </li>
                </ul>
            </div>
        </div>

        <div class="btn-group">
            <button class="btn btn-dropdown">+ New Tool</button>
            <ul class="dropdown-list">
                <li>Basic</li>
                <li class="divider"></li>
                <li>SDM</li>
            </ul>
        </div>

        <g:each in="${["SDM", "Basic", "Outcome"]}" var="type">
            <div class="item">

                <div class="item-header">
                    <div class="tool-type">
                        <span class="span-left" value="${type}">Type: ${type}</span>
                        <span class="span-right">ID: 1234567</span>
                    </div>

                    <div class="select-action">
                        <select>Action
                            <option>View</option>
                            <option>Copy</option>
                            <option>Delete</option>
                        </select>
                    </div>
                </div>

                <div class="item-content">
                    <h3>Rotator Cuff Simplified</h3>

                    <p>This SDM tool is designed to teach you about the treatment options of an injuired rotator cuff,
                    Copy and the risk and benefit of each of the options. It contains 20 questions and should take about 15 minutes to complete.
                    </p>
                </div>

                <div class="item-footer">
                    <div class="vertical">level: <strong>System</strong></div>
                    <span>Treatment Tags: <strong>Rotator Cuff</strong></span>
                </div>
            </div>
        </g:each>

    </div>
    </body>
    </html>
</g:applyLayout>
