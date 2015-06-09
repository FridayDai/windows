//= require share/baseBundle

(function ($, undefined) {
    'use strict';

    function validatePassword() {
        $("#joinRat").click(function () {
            var newPassword = $("#newPassword").val();
            var confirmPassword = $("#confirmPassword").val();
            if (newPassword !== confirmPassword) {
                $(".error-area").text(RC.constants.passwordNotMatch);
                return false;
            }
        });
    }


    function init() {
        // validate password
        validatePassword();
    }


    // Start processing
    init();
})(jQuery);
