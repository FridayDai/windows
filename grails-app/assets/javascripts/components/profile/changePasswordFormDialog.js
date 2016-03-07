
var flight = require('flight');
var withFormDialog = require('../common/withFormDialog');
var WithElementValidation = require('../common/WithElementValidation');
var PasswordValidation = require('../share/validation/PasswordValidation');

function changePasswordFormDialog() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withFormDialog
    ]);

    this.attributes({
        submitBtnSelector: '.create-btn',
        newPassWordFieldSelector: '#new-password'
    });

    this.initPasswordValidation = function () {
        this.setElementValidation(
            this.select('newPassWordFieldSelector'),
            PasswordValidation.rules
        );
    };

    this._formSuccessProcess = function () {
        this.hideDialog();
    };

    this.after('initialize', function () {
        this.initPasswordValidation();

        this.on('formSuccess', this._formSuccessProcess);
    });
}

module.exports = flight.component(WithElementValidation, changePasswordFormDialog, withFormDialog);
