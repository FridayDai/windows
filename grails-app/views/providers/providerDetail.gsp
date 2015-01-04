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
        <div class="content_left">
            <div class="content_left_top">
                <div class="inner_content">
                    <button class="btn_add color">+ Add Logo</button>

                    <p class="provider_name">GroupHealth</p>

                    <a href="#" id="edit-provider">
                        <div class="icon-edit"></div>
                    </a>
                    %{--<div class="icon-edit"></div>--}%
                </div>
            </div>

            <div class="content_left_bottom">
                <div class="content_title">
                    <p class="provider_status color">Features</p>
                </div>
                <ul class="ul_tip">
                    <li class="li_color">Notifications:<button class="btn_on">ON</button></li>
                    <li class="li_msg li_color">Messaging:
                        <button>ON</button>
                    </li>
                    <li class="li_lib li_color">Library:
                        <button>ON</button>
                    </li>
                </ul>
            </div>
        </div>

        <div class="content_right">
            <div class="content_right_top">
                <div class="inner_head">
                    <p class="p_info color">Agent Information</p>

                    <div class="div_status color">STATUS:<p class="p_status">UNIVITED</p></div>
                    <button class="btn_invite color btn_border">Invite</button>
                </div>

                <div class="inner_body">
                    <ul class="message_tip">
                        <li class="li_color">Sign-in email:</li>
                        <li class="li_first_name li_color">First name:</li>
                        <li class="li_last_name li_color">Last name:</li>
                    </ul>
                </div>
            </div>

            <div class="content_right_bottom">
                <div class="inner_head">
                    <p class="p_info color">Treatments</p>
                    <button class="btn_add_treatment color btn_border">+ Add Treatment</button>
                </div>

                <div class="accordion-body" id="accordion">

                    <div class="accordion-inner-body">
                        <div class="inner-header">
                            <h3 class="accordion-title" id="accordion-inner-title">
                                TOTAL KNEE REPLACEMENT
                            </h3>

                            <span class="treatment-id displaynone" id="span-treatment-id">ID:12345678</span>

                            <div id="navWrapper" class="actions-list displaynone">
                                <div class="li-list">
                                    <a href="#" id="a-action" class="btn-action">Actions</a>

                                    <ul class="displaynone div-position" id="ul-action">
                                        <li><a href="#" id="a-view" class="btn-view">View</a></li>

                                        <li><a href="#" id="a-remove" class="btn-remove">Remove</a></li>
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
                                <div class="li-basic-tool">Basic Tools:<p class="p-basic-number">4</p></div>

                                <div class="set-block-right">
                                </div>

                                <div class="li-outcome-tool">Outcome Tools:<p class="p-outcome-number">2</p></div>

                                <div class="set-block-left">
                                </div>

                                <div class="li-sdm-tool">SDM Tools:<p class="p-sdm-number">7</p></div>
                            </div>
                        </div>

                    </div>


                    <div class="accordion-inner-body">
                        <div class="inner-header">
                            <h3 class="accordion-title">
                                TOTAL KNEE REPLACEMENT
                            </h3>

                            <span class="treatment-id displaynone" id="span-treatment-id1">ID:12345678</span>

                            <div id="navWrapper1" class="actions-list displaynone">
                                <div class="li-list">
                                    <a href="#" id="a-action1" class="btn-action">Actions</a>

                                    <div class="displaynone div-position" id="ul-action1">
                                        <div><a href="#" id="a-view1" class="btn-view">View</a></div>

                                        <div><a href="#" id="a-remove1" class="btn-remove">Remove</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-content">
                            <div class="inner-content">
                                A total knee replacement is a surgical procedure whereby the diseased knee joint is replaced with artificial material.
                                The knee is a hinge joint which provides motion at the point where the thigh meets the lower leg.
                            </div>

                            <div class="div-tool-list">
                                <div class="li-basic-tool">Basic Tools:<p class="p-basic-number">4</p></div>

                                <div class="set-block-right">
                                </div>

                                <div class="li-outcome-tool">Outcome Tools:<p class="p-outcome-number">2</p></div>

                                <div class="set-block-left">
                                </div>

                                <div class="li-sdm-tool">SDM Tools:<p class="p-sdm-number">7</p></div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>


    <g:form class="form ui-hidden" controller="authentication" method="post" action="login">

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

    </body>
    </html>
</g:applyLayout>
