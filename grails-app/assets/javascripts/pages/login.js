;
(function ($, undefined) {
    'use strict';
    var login = RC.pages.login = RC.pages.login || {};

    function _init() {
        RC.common.progress(true);
    }

    function forbidLogin() {
        $("#btn_login").attr("disabled", "disabled")
    }

    function allowLogin() {
        $("#btn_login").removeAttr("disabled")
    }

    if ($("#error_login").attr("rateLimit")) {
        var milliseconds = $("#error_login").attr("rateLimit") * 1000
        forbidLogin();
        setTimeout(function () {
            allowLogin();
        }, milliseconds)
        $("#error_login").attr("rateLimit", "");
    }

})(jQuery);
