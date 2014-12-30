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

                    <div class="icon-edit"></div>
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

                <ul class="accordion_body" id="accordion">
                    <li class="accordion_li">
                        <div class="triangle">

                        </div>

                        <h3 class="accordion_title">TOTAL KNEE REPLACEMENT</h3>

                        <div>
                            <p>
                                AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                            </p>
                        </div>
                    </li>


                    <li class="accordion_li">
                        <div class="triangle">

                        </div>

                        <h3 class="accordion_title">CORONARY ARTERY TREATMENT</h3>

                        <div>
                            <p>
                                BBB
                            </p>
                        </div>
                    </li>

                    <li class="accordion_li">
                        <div class="triangle">

                        </div>

                        <h3 class="accordion_title">BURSECTOMY</h3>

                        <div>
                            <p>
                                BBB
                            </p>
                        </div>
                    </li>

                    <li class="accordion_li">
                        <div class="triangle">

                        </div>

                        <h3 class="accordion_title">VERTEBRECTOMY</h3>

                        <div>
                            <p>
                                BBB
                            </p>
                        </div>
                    </li>

                    <li class="accordion_li">
                        <div class="triangle">

                        </div>

                        <h3 class="accordion_title">ARTHROTOMY</h3>

                        <div>
                            <p>
                                BBB
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

    </div>
    </body>
    </html>
</g:applyLayout>
