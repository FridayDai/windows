var flight = require('flight');
var withDialog = require('../common/withDialog');
var withServerError = require('../common/withServerError');

function deleteAgentFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.delete-btn',

        loadingState: 'loading',
        resetState: 'reset',

        deleteAgentUrl: '/clients/{0}/agents/{1}'
    });

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;

        submitBtn.button(this.attr.loadingState);

        $.ajax({
            url: that.attr.deleteAgentUrl.format(that.clientId, that.agentId),
            type: 'DELETE'
        })
        .done(function () {
            that.trigger('deleteAgentSuccess');

            that.hideDialog();
        })
        .fail(_.bind(that.serverErrorHandler, that))
        .always(function () {
            submitBtn.button(that.attr.resetState);
        });
    };

    this.onShow = function (event, data) {
        this.clientId = data.clientId;
        this.agentId = data.agentId;
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });

        this.on(document, 'showDeleteAgentFormDialog', this.onShow);
    });
}

module.exports = flight.component(withDialog, withServerError, deleteAgentFormDialog);
