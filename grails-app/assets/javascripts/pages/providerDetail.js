(function ($, undefined) {
    'use strict';

    $("#edit-provider").bind("click",
        function (e) {
            e.preventDefault();
            var confirmFormArguments = {
                element: ".edit-form",
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
        });

    //$('#a-remove').on('click', function (e) {
    //    e.preventDefault();
    //
    //    var warningArguments = {
    //        element: ".warning",
    //        title: RC.constants.warningTipTitle,
    //        message: RC.constants.warningTip,
    //        closeCallback: function () {
    //        },
    //        cancelCallback: function () {
    //
    //        }
    //    };
    //    RC.common.warning(warningArguments);
    //
    //});

    function _init() {
        $("#accordion").accordion({
            header: ".inner-header",
            collapsible: true,
            active: false
        });

        $("#accordion1").click(function (e) {
            e.preventDefault();
            e.stopPropagation();

            if ($("#span-treatment-id").is(':visible')) {
                $('#accordion-inner-title').removeClass('accordion-title-change').addClass('accordion-title');
                $("#span-treatment-id").removeClass('displayblock').addClass('displaynone');

            } else {
                $('#accordion-inner-title').removeClass('accordion-title').addClass('accordion-title-change');
                $("#span-treatment-id").removeClass('displaynone').addClass('displayblock');
            }

            if ($("#navWrapper").is(':visible')) {
                $("#navWrapper").removeClass('displayblock').addClass('displaynone');
            } else {
                $("#navWrapper").removeClass('displaynone').addClass('displayblock');
            }

        });

        $("#a-action").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            if ($("#ul-action").is(':visible')) {
                $("#ul-action").removeClass('displayblock').addClass('displaynone');
            } else {
                $("#ul-action").removeClass('displaynone').addClass('displayblock');
            }
        });

        $("#a-view").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            $("#ul-action").removeClass('displayblock').addClass('displaynone');
        });

        $("#a-remove").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            $("#ul-action").removeClass('displayblock').addClass('displaynone');
        });


    }

    _init();
})(jQuery);
