(function ($, undefined) {
    'use strict';
    //var provider = RC.pages.provider = RC.pages.provider || {};
    $("#add-provider").bind("click",
        function (e) {
            e.preventDefault();
            var confirmFormArguments = {
                element: ".form",
                title: RC.constants.confirmTitle,
                content: RC.constants.confirmContent,
                okCallback: function () {
                },
                cancelCallback: function () {
                },
                height: 200,
                width: 400
            };
            RC.common.confirmForm(confirmFormArguments);

            //var warningArguments = {
            //    title: RC.constants.warningTipTitle,
            //    message: RC.constants.warningTip,
            //    closeCallback: function () {
            //    }
            //};
            //RC.common.warning(warningArguments);
        });
})(jQuery);
