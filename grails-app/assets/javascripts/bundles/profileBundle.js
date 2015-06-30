//= require share/baseBundle
//= require share/formModalBundle
//= require ../bower_components/jquery-form/jquery.form
//= require ../bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker
//= require models/profile

(function ($, undefined) {
    'use strict';

    function initClientDialogForm() {
        var modal = $('#change-password-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#change-password-modal', true);

        // Setup create button
        createBtn.click(function () {
            var button = $(this);

            if (form.valid()) {
                button.button('loading');

                form.ajaxSubmit({
                    success: function () {

                        modal.modal('hide');

                        button.button('reset');
                    },

                    error: function (jqXHR) {
                        var serverErrorEl = modal.find('.rc-server-error');

                        button.button('reset');

                        if (jqXHR.status === 403) {
                            alert('Permission denied! Please try to refresh page!');
                        } else {
                            serverErrorEl.text(jqXHR.responseJSON.error.errorMessage);
                            serverErrorEl.show();
                        }
                    }
                });
            }
        });
    }

    function initDebugScheduleForm() {
        var modal = $('#change-time-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#change-time-modal', true);
        $('#debugDate').datepicker({
            format: "yyyy-mm-dd",
            orientation: "top left"
        });

        // Setup create button
        createBtn.click(function () {
            var button = $(this);

            if (form.valid()) {
                button.button('loading');

                form.ajaxSubmit({
                    success: function () {

                        modal.modal('hide');

                        button.button('reset');
                    },

                    error: function (jqXHR) {
                        var serverErrorEl = modal.find('.rc-server-error');

                        button.button('reset');

                        if (jqXHR.status === 403) {
                            alert('Permission denied! Please try to refresh page!');
                        } else {
                            serverErrorEl.text(jqXHR.responseJSON.error.errorMessage);
                            serverErrorEl.show();
                        }
                    }
                });
            }
        });
    }



    function init() {
        // Init add client dialog form
        initClientDialogForm();
        // Init add schedule time form
        initDebugScheduleForm();
    }


    // Start processing
    init();
})
(jQuery);

