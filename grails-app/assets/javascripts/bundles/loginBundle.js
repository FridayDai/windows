//= require share/formBundle

(function ($, undefined) {
    'use strict';

    // Disable login button
    function forbidLogin() {
        $("#btn_login").attr("disabled", "disabled");
    }

    // Enable login button
    function allowLogin() {
        $("#btn_login").removeAttr("disabled");
    }

    // Check rate limit for entering wrong username and password many times
    function checkRateLimit() {
        var secondsValue = $("#error_login").attr("rateLimit");

        if (parseInt(secondsValue)) {
            forbidLogin();
            setTimeout(allowLogin, secondsValue * 1000);
        }
    }

    // Initialization
    function init() {
        // set Jquery validation
        $("#loginForm").validate();

        checkRateLimit();
    }

    // Start processing
    init();
})(jQuery);
