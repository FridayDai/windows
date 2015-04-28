//= require ../../bower_components/jquery/dist/jquery
//= require ../../bower_components/underscore/underscore
//= require ../../bower_components/bootstrap/dist/js/bootstrap
//= require ../../bower_components/jquery-validation/dist/jquery.validate

(function ($, undefined) {
    'use strict';

    $.validator.setDefaults({
        errorClass: 'help-block',
        highlight: function (element) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).parents('.form-group').removeClass('has-error');

        }
    });

    var utility = RC.utility = RC.utility || {};

    utility.formModal = {};

    _.extend(utility.formModal, {
        /**
         * Helper function for the configuration of form dialog
         * @param selector
         * @param
         */
        defaultConfig: function (options, noValidate) {
            if (_.isString(options)) {
                options = {
                    selector: options
                };
            }

            var selector = options.selector;
            var modal = $(selector);
            var form = modal.find('form');

            // Setup validator for form
            if (!noValidate)
                form.validate();

            // Setup clear form after modal hidden
            modal.on('hidden.bs.modal', function () {
                modal.find('.rc-server-error').hide();
                form[0].reset();
                form.find('.form-group').removeClass('has-error');
                form.find('label.help-block').remove();
            });

            // The value in the map should be array, like: [selector, selector]
            // the first selector should be where get value, second selector is where set value
            form.data('fieldSelectorArray', options.fieldSelectorArray || []);
        },

        setValFromFieldArray: function (formSelector) {
            var form = $(formSelector);
            var arr = form.data('fieldSelectorArray');

            _.each(arr, function (item) {
                $(item[0]).text(form.find(item[1]).val());
            });
        },

        getValFromFieldArray: function (formSelector) {
            var form = $(formSelector);
            var arr = form.data('fieldSelectorArray');

            _.each(arr, function (item) {
                form.find(item[1]).val($(item[0]).text());
            });
        }
    });
})(jQuery);
