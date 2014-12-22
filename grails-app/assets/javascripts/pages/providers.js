/**
 * Created by John on 12/11/14.
 */
;
(function ($, undefined) {
    'use strict';
    var provider = RC.pages.provider = RC.pages.provider || {};
    $("#add-provider").on("click",
    function (e) {
       RC.common.confirmForm(".form","confirm title","are you sure to add provider?",function(){
           alert("ok!")
       },function(){
           alert(("cancel!"))
       },300,350);
    });
})(jQuery);
