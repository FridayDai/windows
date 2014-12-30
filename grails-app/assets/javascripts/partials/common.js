(function ($, undefined) {
    'use strict';
    var common = RC.common = RC.common || {};

    function _init() {
        //$("#menu").menu();
    }

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
            var self = this,
                remain = remain || 2000;
            $msgDiv.fadeIn("slow").html(msg);
            setTimeout(function () {
                    $msgDiv.fadeOut("slow");
                },
                remain);
            return self;
        },
        /**
         * confirm form
         * @param element
         * @param title
         * @param message
         * @param okCallback
         * @param cancelCallback
         * @param height
         * @param width
         */
        confirmForm: function (element, title, message, okCallback, cancelCallback, height, width) {
            if (window !== window.top) {
                window.top.RC.common.confirmForm(element, title, message, okCallback, cancelCallback, height, width);
                return;
            }
            var form,
                height = height || 300,
                width = width || 350;
            var $container = $(element);
            var dialog = $container.dialog({
                autoOpen: false,
                resizable: false,
                height: height,
                width: width,
                modal: true,
                title: title,
                buttons: {
                    "CONFIRM": function (e) {
                        if ($.isFunction(okCallback)) {
                            (okCallback)(e);
                        }
                        dialog.dialog("close");
                    },
                    CANCEL: function (e) {
                        if ($.isFunction(cancelCallback)) {
                            (cancelCallback)(e);
                        }
                        dialog.dialog("close");
                    }
                },
                close: function () {
                    //form.reset();
                }
            });
            $container.removeClass('ui-hidden');
            dialog.dialog("open");
            return false;

        },
        /**
         * confirm dialogue
         * @param title
         * @param message
         * @param okCallback
         * @param cancelCallback
         */
        confirm: function (title, message, okCallback, cancelCallback, flag) {
            if (window !== window.top) {
                window.top.RC.common.confirm(title, message, okCallback, cancelCallback, flag);
                return;
            }
            var $container;
            if ($(".window-Container").length > 0) {
                $container = $(".window-Container");
            } else {
                $container = $('<div class="window-Container"><div class="window-message" ></div></div>');
                $container.find('.window-message').html('<div class="window-confirm">' + message + '</div>');
                $(document.body).append($container);
            }
            var dialog = $container.dialog();
            if (!dialog) {
                $container.dialog({
                    autoOpen: false,
                    resizable: false,
                    height: 140,
                    width: 350,
                    modal: true,
                    buttons: {
                        "Ok": function (e) {
                            if ($.isFunction(okCallback)) {
                                (okCallback)(e);
                            }
                            dialog.dialog("close");
                        },
                        Cancel: function (e) {
                            if ($.isFunction(cancelCallback)) {
                                (cancelCallback)(e);
                            }
                            dialog.dialog("close");
                        }
                    },
                    close: function () {

                    }
                });
            }
            dialog.dialog("open");
            return false;
        },

        /**
         * waring dialogue
         * @param title
         * @param message
         * @param closeCallback
         */
        warning: function (title, message, closeCallback) {
            if (window !== window.top) {
                window.top.RC.common.warning(title, message, closeCallback);
                return;
            }
            var $container;
            if ($(".window-container").length > 0) {
                $container = $(".window-container");
            } else {
                $container = $('<div class="window-container">' +
                '<div class="window-title"></div>' +
                '<div class="window-message"></div>' +
                '</div>');
                $(document.body).append($container);
            }

            var dialog = $container.dialog({
                autoOpen: false,
                resizable: false,
                height: 140,
                width: 350,
                modal: true,
                buttons: {
                    PROCEED: function (e) {
                        if ($.isFunction(closeCallback)) {
                            (closeCallback)(e);
                        }
                        dialog.dialog("close");
                    },
                    CANCEL: function (e) {
                        if ($.isFunction(closeCallback)) {
                            (closeCallback)(e);
                        }
                        dialog.dialog("close");
                    }
                }
            });
            $container.parent().find('.ui-widget-header').addClass('ui-icon-show');
            $container.parent().addClass('ui-size');
            $container.parent().find('.ui-button').addClass('btn-position');

            $('<div class="ui-icon-add"></div>').insertBefore($container.parent().find('.ui-button-text')[0]);
            var $btnCancel = $($container.parent().find('.ui-button')[1]);
            var $span = $($container.parent().find('.ui-button-text')[0]);
            $btnCancel.addClass('btn-cancel');

            $container.find('.window-title').html('<div class="window-warning-title">' + title + '</div>');
            $container.find('.window-message').html('<div class="window-warning">' + message + '</div>');
            dialog.dialog("open");
            return false;
        },

        /**
         * show error tip
         * @param errorElement
         * @param showType
         */
        showErrorTip: function (errorElement, showType) {

        },

        /**
         * hide error tip
         * @param element
         */
        hideErrorTip: function (element) {

        },

        /**
         * tooltip
         */
        tooltip: function () {

        }


    });
    _init();
})(jQuery);
