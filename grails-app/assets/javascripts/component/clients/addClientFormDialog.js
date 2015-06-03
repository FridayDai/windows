'use strict';

var flight = require('flight');
var withClientFormDialog = require('../common/withClientFormDialog');

function addClientFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        primaryButtonSelector: '.create-btn'
    });

    this.formSuccessProcess = function (data) {
        this.trigger('clientsTableAddRow', data);
    };
}

module.exports = flight.component(withClientFormDialog, addClientFormDialog);
