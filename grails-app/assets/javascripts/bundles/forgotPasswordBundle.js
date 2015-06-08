//= require share/baseBundle
//= require share/formModalBundle

(function ($, undefined) {
    'use strict';

    $(".password-form").validate({
        rules: {
            email: {
                email: true
            }
        }
    });

})(jQuery);


