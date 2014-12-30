(function ($, undefined) {
    'use strict';
    //var provider = RC.pages.provider = RC.pages.provider || {};
    $("#add-provider").on("click",
    function (e) {
       RC.common.confirmForm(".form", RC.constants.confirmTitle, RC.constants.confirmContent, function(){
       }, function(){
       }, 300, 350);
    });
})(jQuery);
