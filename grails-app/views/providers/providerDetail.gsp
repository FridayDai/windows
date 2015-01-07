<!DOCTYPE html>

<g:set var="scriptPath" value="providerDetailBundle"/>
<g:set var="cssPath" value="providerDetail"/>
<g:applyLayout name="main">
    <html>
    <head>
        <title>Welcome to ratchet provider detail</title>
    </head>

    <body>
    <div class="content">
        <div class="content-left">
            <div class="content-left-top">
                <div class="inner-content">
                    <button class="btn-add color">+ Add Logo</button>

                    <p class="provider-name">GroupHealth</p>

                    <a href="#" id="edit-provider">
                        <div class="icon-edit"></div>
                    </a>
                </div>
            </div>

            <div class="content-left-bottom">
                <div class="content-title border-color">
                    <p class="provider-status color">Features</p>
                </div>
                <ul class="ul-tip">
                    <li class="li-color">Notifications:<button class="btn-on">ON</button></li>
                    <li class="li-msg li-color">Messaging:
                        <button>ON</button>
                    </li>
                    <li class="li-lib li-color">Library:
                        <button>ON</button>
                    </li>
                </ul>
            </div>
        </div>

        <div class="content-right">
            <div class="content-right-top">
                <div class="inner-head border-color">
                    <p class="p-info color">Agent Information</p>

                    <div class="div-status color">STATUS:<p class="p-status">UNIVITED</p></div>
                    <button class="btn-invite color btn-border">Invite</button>

                    %{--<div class="div_status color">STATUS:<p class="p_status">ACCEPTED</p></div>--}%
                    %{--<div id="navWrapper1" class="actions-list displaynone">--}%
                    %{--<div class="li-list">--}%
                    %{--<a href="#" id="a-actio" class="btn_invite color btn_border">Actions</a>--}%

                    %{--<div class="displaynone div-position" id="ul-actio">--}%
                    %{--<div><a href="#" id="a-vie" class="btn-view">View</a></div>--}%

                    %{--<div><a href="#" id="a-remov" class="btn-remove">Remove</a></div>--}%
                    %{--</div>--}%
                    %{--</div>--}%
                    %{--</div>--}%
                </div>


                <div class="inner-body">
                    <ul class="message-tip">
                        <li class="li-color">Sign-in email:<p>tferguson@ghc.org</p></li>
                        <li class="li-space li-color">First name:<p>Thomas</p></li>
                        <li class="li-space li-color">Last name:<p>Ferguson</p></li>
                    </ul>
                </div>
            </div>

            <div class="content-right-bottom">
                <div class="inner-head border-color">
                    <p class="p-info color">Treatments</p>
                    <button class="btn-add-treatment color btn-border">+ Add Treatment</button>
                </div>

                <div class="accordion-body" id="accordion">

                    <div class="accordion-inner-body" id="accordion1">
                        <div class="inner-header">
                            <h3 class="accordion-title" id="accordion-inner-title">
                                TOTAL KNEE REPLACEMENT
                            </h3>

                            <span class="treatment-id displaynone" id="span-treatment-id">ID:12345678</span>

                            <div id="navWrapper" class="actions-list displaynone">
                                <div class="li-list">
                                    <a href="#" id="a-action" class="btn-action">Actions</a>

                                    <ul class="displaynone div-position" id="ul-action">
                                        <li><a href="#" id="a-view" class="btn-view btn-color">View</a></li>

                                        <li><a href="#" id="a-remove" class="btn-remove btn-color">Remove</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-content">
                            <div class="inner-content">
                                A total knee replacement is a surgical procedure whereby the diseased knee joint is replaced with artificial material.
                                The knee is a hinge joint which provides motion at the point where the thigh meets the lower leg.
                            </div>

                            <div class="div-tool-list">
                                <div class="li-basic-tool tool-color">Basic Tools:<p
                                        class="p-basic-number p-color">4</p></div>

                                <div class="li-outcome-tool tool-color">Outcome Tools:<p
                                        class="p-outcome-number p-color">2</p></div>

                                <div class="li-sdm-tool tool-color">SDM Tools:<p class="p-sdm-number p-color">7</p>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="accordion-inner-body" id="accordion2">
                        <div class="inner-header">
                            <h3 class="accordion-title" id="accordion-inner-title1">
                                CORONARY ARTERY TREATMENT
                            </h3>
                        </div>

                        <div class="accordion-content">
                            <div class="inner-content">
                                BBB
                            </div>
                        </div>

                    </div>

                    <div class="accordion-inner-body" id="accordion3">
                        <div class="inner-header">
                            <h3 class="accordion-title" id="accordion-inner-title2">
                                CORONARY ARTERY TREATMENT
                            </h3>
                        </div>

                        <div class="accordion-content">
                            <div class="inner-content">
                                BBB
                            </div>
                        </div>

                    </div>

                    <div class="accordion-inner-body" id="accordion4">
                        <div class="inner-header">
                            <h3 class="accordion-title" id="accordion-inner-title3">
                                VERTEBRECTOMY
                            </h3>
                        </div>

                        <div class="accordion-content">
                            <div class="inner-content">
                                CCC
                            </div>
                        </div>

                    </div>

                    <div class="accordion-inner-body" id="accordion5">
                        <div class="inner-header">
                            <h3 class="accordion-title" id="accordion-inner-title4">
                                ARTHROTOMY
                            </h3>
                        </div>

                        <div class="accordion-content">
                            <div class="inner-content">
                                DDD
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>


    <g:form class="edit-form ui-hidden" controller="authentication" method="post" action="login">

        <div class="form-group">
            <input name="email" type="text" class="input-group" placeholder="Email"/>
        </div>

        <div class="form-group">
            <input name="firstName" type="text" class="input-group" placeholder="First Name"/>
        </div>

        <div class="form-group">
            <input name="lastName" type="text" class="input-group" placeholder="Last Name"/>
        </div>

    </g:form>

    <g:form class="warning ui-hidden">

    </g:form>

    </body>
    </html>
</g:applyLayout>
