require('jValidate');
require('jForm');

var flight = require('flight');
var withServerError = require('./withServerError');

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

    flight.compose.mixin(this, [
        withServerError
    ]);

    this.attributes({
        formSelector: 'form',
        loadingState: 'loading',
        resetState: 'reset'
    });

    this.initForm = function () {
        this.formEl = this.select('formSelector');

        if (_.isFunction(this.initValidation)) {
            this.initValidation();
        }
    };

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');

        if (this.formEl.valid()) {
            submitBtn.button(this.attr.loadingState);

            this.submitForm();
        }
    };

    this.submitForm = function () {
        this.formEl.ajaxSubmit({
            success: _.bind(this.formSuccess, this),
            error: _.bind(this.serverErrorHandler, this),
            complete: _.bind(this.resetSubmitBtnState, this)
        });
    };

    this.formSuccess = function (data) {
        this.trigger('formSuccess', data);

        this.clearForm();
    };

    this.clearForm = function () {
        this.formEl.parent().find('.rc-server-error').hide();
        this.formEl[0].reset();
        this.formEl.find('.form-group').removeClass('has-error');
        this.formEl.find('label.help-block').remove();
    };

    this.resetSubmitBtnState = function () {
        this.select('submitBtnSelector').button(this.attr.resetState);
    };

    this.after('initialize', function () {
        this.initForm();

        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });
    });
}

module.exports = withForm;
