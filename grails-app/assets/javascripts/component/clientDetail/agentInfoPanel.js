'use strict';

var flight = require('flight');
var withPanel = require('../common/withPanel');

function agentInfoPanel() {
    /* jshint validthis:true */

    this.attributes({
        agentEmailSelector: '.email dd',
        agentFirstNameSelector: '.first-name dd',
        agentLastNameSelector: '.last-name dd',
        agentTriggerBtnSelector: '.edit .trigger',
        agentDeleteBtnSelector: '.edit .delete'
    });

    this.setInfo = function (event, data) {
        data = data || {};

        this.setAgentEmail(data.agentEmail);
        this.setAgentFirstName(data.agentFistName);
        this.setAgentLastName(data.agentLastName);

        this.$node.data('agentId', data.agentId);
    };

    this.clear = function () {
        this.select('agentEmailSelector').empty();
        this.select('agentFirstNameSelector').empty();
        this.select('agentLastNameSelector').empty();

        this.$node.removeData('agentId');
    };

    this.triggerAgent = function () {
        var agentEmail = this.getAgentEmail();

        if (agentEmail) {
            this.trigger('editAgentFormDialogServed', {
                clientId: this.$node.data('clientId'),
                agentId: this.$node.data('agentId'),
                agentEmail: agentEmail,
                agentFirstName: this.getAgentFirstName(),
                agentLastName: this.getAgentLastName()
            });
        } else {
            this.trigger('createAgentFormDialogServed', {
                clientId: this.$node.data('clientId')
            });
        }
    };

    this.deleteAgent = function () {
        this.trigger('deleteAgentFormDialogServed', {
            clientId: this.$node.data('clientId'),
            agentId: this.$node.data('agentId')
        });
    };

    this.after('initialize', function () {
        this.generateGetterSetter([
            'agentEmail',
            'agentFirstName',
            'agentLastName'
        ], this);

        this.on(document, 'agentInfoChanged', this.setInfo);
        this.on(document, 'agentPanelClearRequested', this.clear);

        this.on('click', {
            'agentTriggerBtnSelector': this.triggerAgent,
            'agentDeleteBtnSelector': this.deleteAgent
        });
    });
}

module.exports = flight.component(withPanel, agentInfoPanel);
