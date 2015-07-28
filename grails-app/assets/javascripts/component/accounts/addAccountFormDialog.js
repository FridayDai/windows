var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');

function addAccountFormDialog() {
    /* jshint validthis:true */

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

module.exports = flight.component(withForm, withDialog, addAccountFormDialog);
