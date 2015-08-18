var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');

function withFormDialog () {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withForm,
        withDialog
    ]);

    this.after('initialize', function () {
        this.on('hidden.bs.modal', this.clearForm);
    });
}

module.exports = withFormDialog;
