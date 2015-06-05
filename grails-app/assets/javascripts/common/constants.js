(function ($, undefined) {
    'use strict';
    var constants = RC.constants = RC.constants || {};

    $.extend(constants, {
        // URLs
        //baseUrl: "http://localhost:8080"
        treatmentLimit: 3,
        confirmPassword: "Your passwords don't match. Please enter them again.",
        passwordNotMatch: "Your passwords don't match. Please try again."
    });
})(jQuery);
