var flight = require('flight');
var flight = require('flight');
var withFormDialog = require('../common/withFormDialog');

function withAnnouncementFormDialog() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withFormDialog
    ]);

    this.attributes({
        hiddenStatus: 'form input[name="status"]'
    });

    this.initValidation = function () {

        this.formEl.validate({
            rules: {
                content: {
                    required: true
                },
                ColorHex: {
                    required: true
                }
            }
        });
    };

    this._initFormDialog = function () {
        this.select('hiddenStatus').val('1');
    };

    this._formSuccessProcess = function () {
        this.hideDialog();
    };

    this.after('initialize', function () {
        this._initFormDialog();

        this.on('formSuccess', this._formSuccessProcess);
    });
}

module.exports = withAnnouncementFormDialog;

