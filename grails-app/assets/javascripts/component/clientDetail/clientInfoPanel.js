'use strict';

var flight = require('flight');
var withPanel = require('../common/withPanel');

function clientInfoPanel() {
    /* jshint validthis:true */

    this.attributes({
        clientNameSelector: '.client-name strong',
        subDomainSelector: '.sub-domain dd',
        portalNameSelector: '.portal-name dd',
        primaryColorSelector: '.primary-color dd',
        editClientBtnSelector: '.edit button'
    });

    this.onClientInfoChanged = function (event, data) {
        data = {} || data;

        this.set('clientName', data.clientName);
        this.set('subDomain', data.subDomain);
        this.set('portalName', data.portalName);
        this.set('primaryColor', data.primaryColor);
    };

    this.onEditClientBtnClick = function () {
        this.trigger('showEditClientFormDialog', {
            clientName: this.get('clientName'),
            subDomain: this.get('subDomain'),
            portalName: this.get('portalName'),
            primaryColor: this.get('primaryColor')
        });
    };

    this.after('initialize', function () {
        this.on(document, 'clientInfoChanged', this.onClientInfoChanged);

        this.on('click', {
            'editClientBtnSelector': this.onEditClientBtnClick
        });
    });
}

module.exports = flight.component(withPanel, clientInfoPanel);
