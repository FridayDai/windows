'use strict';

var flight = require('flight');
var withClientFormDialog = require('../common/withClientFormDialog');

function addClientFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.create-btn'
    });

    this.onFormSuccess = function (e, data) {
        this.trigger('createClientSuccess', data);
    };

    this.after('initialize', function () {
        this.on('formSuccess', this.onFormSuccess)
    });
}

module.exports = flight.component(withClientFormDialog, addClientFormDialog);
