'use strict';

require('jValidate');
require('jForm');

function withForm() {
    /* jshint validthis:true */

    var ERROR_SELECTOR = 'help-block',
        ERROR_CLASS = 'has-error',
        FORM_SELECTOR = '.form-group';

    $.validator.setDefaults({
        errorClass: ERROR_SELECTOR,
        highlight: function (element) {
            $(element).parents(FORM_SELECTOR).addClass(ERROR_CLASS);
        },
        unhighlight: function (element) {
            $(element).parents(FORM_SELECTOR).removeClass(ERROR_CLASS);

        }
    });

    this.attributes({
        serverErrorSelector: '.rc-server-error',

        loadingState: 'loading',
        resetState: 'reset'
    });

    this.submitForm = function () {
        this.form.ajaxSubmit({
            success: _.bind(this.formSuccess, this),
            error: _.bind(this.formError, this)
        });
    };

    this.formError = function (reqObj) {
        this.select('primaryButtonSelector').button(this.attr.resetState);

        if (reqObj.status === 403) {
            alert('Permission denied! Please try to refresh page!');
        } else {
            this.setServerError(reqObj.responseJSON.error.errorMessage);
        }
    };

    this.setupForm = function (options) {
        options = options || {};

        this.form.validate(options.validation);

        // The value in the map should be array, like: [selector, selector]
        // the first selector should be where get value, second selector is where set value
        this.form.data('fieldSelectorArray', options.fieldSelectorArray || []);
    };

    this.setServerError = function (msg) {
        this.select('serverErrorSelector').show().text(msg);
    };

    this.clearForm = function () {
        this.form.parent().find('.rc-server-error').hide();
        this.form[0].reset();
        this.form.find('.form-group').removeClass('has-error');
        this.form.find('label.help-block').remove();
    };

    this.after('initialize', function () {
        this.on(document, this.attr.dialogCloseEvent, this.clearForm);
    });
}

module.exports = withForm;
