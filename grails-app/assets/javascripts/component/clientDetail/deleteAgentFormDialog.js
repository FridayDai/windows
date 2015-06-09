'use strict';

var flight = require('flight');
var withDialog = require('../common/withDialog');
var withForm = require('../common/withForm');

function deleteAgentFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        primaryButtonSelector: '.delete-btn',

        deleteAgentUrlFormatStr: '/clients/{0}/agents/{1}'
    });

    this.primaryButtonClicked = function () {
        var primaryBtn = this.select('primaryButtonSelector');
        var that = this;

        $.ajax({
            url: that.attr.deleteAgentUrlFormatStr.format(that.clientId, that.agentId),
            type: 'DELETE'
        })
            .done(function () {
                that.select('primaryButtonSelector')
                    .button(that.attr.resetState);

                that.trigger('agentPanelClearRequested');
                that.trigger('activeStaffCountChanged', -1);

                that.hideDialog();
            })
            .fail(_.bind(that.formError, that));

        primaryBtn.button(this.attr.loadingState);
    };

    this.dialogServed = function (event, data) {
        this.clientId = data.clientId;
        this.agentId = data.agentId;
    };

    this.after('initialize', function () {
        this.on(document, 'deleteAgentFormDialogServed', this.dialogServed);
    });
}

module.exports = flight.component(withDialog, withForm, deleteAgentFormDialog);
