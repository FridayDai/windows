var flight = require('flight');
var withFormDialog = require('../common/withFormDialog');

function addAccountFormDialog() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withFormDialog
    ]);

    this.attributes({
        submitBtnSelector: '.create-btn'
    });

    this.onFormSuccess = function (e, data) {
        this.trigger('createAccountSuccess', data);
    };

    this.after('initialize', function () {
        this.on('formSuccess', this.onFormSuccess);
    });
}

module.exports = flight.component(withFormDialog, addAccountFormDialog);
