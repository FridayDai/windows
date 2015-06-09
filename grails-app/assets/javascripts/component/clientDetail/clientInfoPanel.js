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
        edithClientBtnSelector: '.edit button'
    });

    this.setInfo = function (event, data) {
        data = {} || data;

        this.setClientName(data.clientName);
        this.setSubDomain(data.subDomain);
        this.setPortalName(data.portalName);
        this.setPrimaryColor(data.primaryColor);
    };

    this.sendInfo = function () {
        this.trigger('editClientFormDialogServed', {
            clientName: this.getClientName(),
            subDomain: this.getSubDomain(),
            portalName: this.getPortalName(),
            primaryColor: this.getPrimaryColor()
        });
    };

    this.after('initialize', function () {
        this.generateGetterSetter([
            'clientName',
            'subDomain',
            'portalName',
            'primaryColor'
        ], this);

        this.on(document, 'clientInfoChanged', this.setInfo);

        this.on('click', {
            'edithClientBtnSelector': this.sendInfo
        });
    });
}

module.exports = flight.component(withPanel, clientInfoPanel);
