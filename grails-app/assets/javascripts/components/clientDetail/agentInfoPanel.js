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

    this.onAgentInfoChanged = function (event, data) {
        data = data || {};

        this.set('agentEmail', data.agentEmail);
        this.set('agentFirstName', data.agentFistName);
        this.set('agentLastName', data.agentLastName);

        this.$node.data('agentId', data.agentId);
    };

    this.onClear = function () {
        this.select('agentEmailSelector').empty();
        this.select('agentFirstNameSelector').empty();
        this.select('agentLastNameSelector').empty();

        this.$node.removeData('agentId');
    };

    this.onShowAgentDialog = function () {
        var agentEmail = this.get('agentEmail');

        if (agentEmail) {
            this.trigger('showEditAgentFormDialog', {
                clientId: this.$node.data('clientId'),
                agentId: this.$node.data('agentId'),
                agentEmail: agentEmail,
                agentFirstName: this.get('agentFirstName'),
                agentLastName: this.get('agentLastName')
            });
        } else {
            this.trigger('showCreateAgentFormDialog', {
                clientId: this.$node.data('clientId')
            });
        }
    };

    this.onShowDeleteAgentDialog = function () {
        this.trigger('showDeleteAgentFormDialog', {
            clientId: this.$node.data('clientId'),
            agentId: this.$node.data('agentId')
        });
    };

    this.after('initialize', function () {
        this.on(document, 'agentInfoChanged', this.onAgentInfoChanged);
        this.on(document, 'deleteAgentSuccess', this.onClear);

        this.on('click', {
            'agentTriggerBtnSelector': this.onShowAgentDialog,
            'agentDeleteBtnSelector': this.onShowDeleteAgentDialog
        });
    });
}

module.exports = flight.component(withPanel, agentInfoPanel);
