require('jValidate');
require('jForm');
var flight = require('flight');
var withServerError = require('../common/withServerError');

function withPrimitiveForm() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withServerError
    ]);

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
        formSelector: 'form'
    });

    this.initForm = function () {
        this.formEl = this.select('formSelector');

        if (_.isFunction(this.initValidation)) {
            this.initValidation();
        }
    };

    this.onCheckConsistency = function (e) {

        if (this.formEl.valid()) {
            var $this = this.$node;
            var originValue = $this.find(this.attr.originSelector).val();
            var confirmValue = $this.find(this.attr.confirmSelector).val();

            if (originValue && confirmValue) {
                if (originValue !== confirmValue) {
                    e.preventDefault();
                    $this.find(this.attr.errorMsgSelector).text(this.attr.errorMsg);
                    return false;
                } else {
                    $this.submit();
                }
            } else {
                $this.submit();
            }

        }
    };

    this.after('initialize', function () {
        this.initForm();

        this.on('click', {
            'submitBtnSelector': this.onCheckConsistency
        });
    });
}

module.exports = withPrimitiveForm;
