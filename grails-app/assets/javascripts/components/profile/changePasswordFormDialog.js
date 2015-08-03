
var flight = require('flight');
var withFormDialog = require('../common/withFormDialog');

function changePasswordFormDialog() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withFormDialog
    ]);

    this.attributes({
        submitBtnSelector: '.create-btn'
    });

    this._formSuccessProcess = function () {
        this.hideDialog();
    };

    this.after('initialize', function () {
        this.on('formSuccess', this._formSuccessProcess);
    });
}

module.exports = flight.component(withFormDialog, changePasswordFormDialog);
