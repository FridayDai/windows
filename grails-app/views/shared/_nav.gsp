<div class="nav">
    <ul id="menu" class="list">
        <li <g:if test="${controllerName == 'providers' || controllerName == 'home'}">class="nav-li active"</g:if>
            <g:else>class="nav-li"</g:else>>
            <g:link controller="providers" action="index">
                <div class="ui-icon icon-provider"></div>
                <span>PROVIDERS</span>
            </g:link>
        </li>
        <li <g:if test="${controllerName == 'tools'}">class="nav-li active"</g:if><g:else>class="nav-li"</g:else>>
            <g:link controller="tools" action="index">
                <div class="ui-icon icon-tool"></div>
                <span>TOOLS</span>
            </g:link>
        </li>
        <li <g:if test="${controllerName == 'practice'}">class="nav-li active"</g:if> <g:else>class="nav-li"</g:else>>
            <g:link controller="practice" action="index">
                <div class="ui-icon icon-practice"></div>
                <span>BEST PRACTICE</span>
            </g:link>
        </li>
        <li <g:if test="${controllerName == 'treatments'}">class="nav-li active"</g:if> <g:else>class="nav-li"</g:else>>
            <g:link controller="treatments" action="index">
                <div class="ui-icon icon-treatment"></div>
                <span>TREATMENTS</span>
            </g:link>
        </li>
        <li <g:if test="${controllerName == 'library'}">class="nav-li active"</g:if><g:else>class="nav-li"</g:else>>
            <g:link controller="library" action="index">
                <div class="ui-icon icon-library"></div>
                <span>LIBRARY</span>
            </g:link>
        </li>
        <li <g:if test="${controllerName == 'accounts'}">class="nav-li active"</g:if><g:else>class="nav-li"</g:else>>
            <g:link controller="accounts" action="index">
                <div class="ui-icon icon-account"></div>
                <span>ACCOUNTS</span>
            </g:link>
        </li>
    </ul>
</div>
