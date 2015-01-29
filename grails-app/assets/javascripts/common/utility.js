(function ($, undefined) {
    'use strict';
    var common = RC.utility = RC.utility || {};

    $.extend(common, {
        /**
         * show progress on pages
         * @param hide
         */
        progress: function (hide) {
            if (window !== window.top) {
                window.top.RC.common.progress(hide);
                return;
            }
            if (hide === undefined || hide === false) {
                if ($("#msg-process").length > 0) {
                    $("#msg-process").hide();
                }
            } else {
                var remain = 1000,
                    msg = RC.constants.loadingContent;
                var $msgDiv = $('<div id="msg-process" class="ui-tips ui-tips-center"></div>');
                if ($("#msg-process").length > 0) {
                    $msgDiv = $("#msg-process");
                } else {
                    $(document.body).append($msgDiv);
                }
                $msgDiv.html(msg).show();
                setTimeout(function () {
                        $msgDiv.hide();
                        $msgDiv.trigger("timeout");
                    },
                    remain);
            }
        },

        /**
         * show remind message
         * @param msg
         * @param remain
         */
        showMsg: function (msg, remain) {
            if (window !== window.top) {
                window.top.RC.common.showMsg(msg, remain);
                return;
            }
            var $msgDiv = $('<div id="msg-info" class="ui-hide ui-tips ui-tips-center"></div>');
            if ($("#msg-info").length > 0) {
                $msgDiv = $("#msg-info");
            } else {
                $(document.body).append($msgDiv);
            }
            var self = this;

            remain = remain || 2000;

            $msgDiv.fadeIn("slow").html(msg);
            setTimeout(function () {
                    $msgDiv.fadeOut("slow");
                },
                remain);

            return self;
        },

        /**
         * show error tip
         * @param errorElement
         * @param showType
         */
        showErrorTip: function (errorElement) {
            var element = $(errorElement.element);
            var errorMessage = errorElement.message;
            element.attr("data-error-msg", errorMessage);
            var className = "error-msg-right";
            if (element.is("[data-class]")) {
                className = element.attr("data-class");
            }
            var position;
            switch (className) {
                case 'error-msg-top':
                    position = {my: 'center bottom', at: 'center top-10'};
                    break;
                case 'error-msg-bottom':
                    position = {my: 'center top', at: 'center bottom+10'};
                    break;
                case 'error-msg-left':
                    position = {my: 'right center', at: 'left-10 center'};
                    break;
                case 'error-msg-right':
                    position = {my: 'left center', at: 'right+10 center'};
                    break;
            }
            position.collision = 'none';
            var errorContent = $('<div class="validation-error-text">' +
            '<i class="misc-icon ui-validation-error"></i>' + errorMessage + '</div>');
            var tooltips = element.tooltip({
                tooltipClass: className,
                position: position,
                items: "[data-error-msg], [title]",
                content: function () {
                    if (element.is("[data-error-msg]")) {
                        return errorContent;
                    }
                    if (element.is("[title]")) {
                        return element.attr("title");
                    }
                    return errorContent;
                }
            });
            tooltips.tooltip("open");
        },

        /**
         * hide error tip
         * @param element
         */
        hideErrorTip: function (errorElement) {
            var element = $(errorElement);
            if ($(element).tooltip()) {
                $(element).tooltip("destroy");
                $(element).removeAttr("data-error-msg");
            }
        },

        /**
         * tooltip
         */
        tooltip: function () {

        }
    });
})(jQuery);
