'use strict';

var flight = require('flight');
var withClientFormDialog = require('../common/withClientFormDialog');

function addClientFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.update-btn',

        clientNameSelector: '#name',
        subDomainSelector: '#subDomain',
        portalNameSelector: '#portalName',
        primaryColorSelector: '#primaryColorHex'
    });

    this.onShow = function (event, data) {
        this.select('clientNameSelector').val(data.clientName);
        this.select('subDomainSelector').val(data.subDomain);
        this.select('portalNameSelector').val(data.portalName);
        this.select('primaryColorSelector').val(data.primaryColor);
    };

    this.onFormSuccess = function () {
        location.reload();
    };

    this.after('initialize', function () {
        this.on(document, 'showEditClientFormDialog', this.onShow);
        this.on('formSuccess', this.onFormSuccess)
    });
}

module.exports = flight.component(withClientFormDialog, addClientFormDialog);
