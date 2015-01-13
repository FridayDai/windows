(function ($, undefined) {
    'use strict';
    //var login = RC.pages.login = RC.pages.login || {};



    function _forbidLogin() {
        $("#btn_login").attr("disabled", "disabled");
    }

    function _allowLogin() {
        $("#btn_login").removeAttr("disabled");
    }

    function _init() {
        var secondsValue = $("#error_login").attr("rateLimit");

        if (secondsValue) {
            var milliseconds = secondsValue * 1000;
            _forbidLogin();

            setTimeout(_allowLogin, milliseconds);
            $("#error_login").attr("rateLimit", "");
        }
    }

    _init();

})(jQuery);
