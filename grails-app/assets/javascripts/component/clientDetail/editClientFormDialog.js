'use strict';

var flight = require('flight');
var withClientFormDialog = require('../common/withClientFormDialog');

function addClientFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        primaryButtonSelector: '.update-btn'
    });

    this.formSuccessProcess = function () {
        location.reload();
    };

    this.after('initialize', function () {
        this.on(document, 'editClientFormDialogServed', this.setValue);
    });
}

module.exports = flight.component(withClientFormDialog, addClientFormDialog);
