var flight = require('flight');
var withDialog = require('../common/withDialog');
var withServerError = require('../common/withServerError');
var withPanel = require('../common/withPanel');

var AdminPortalConstants = require('../../constants/AdminPortalConstants');

function deleteToolDialog() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.delete-btn',
        idLabelSelector: 'dd.id',
        toolTitleLabelSelector: 'dd.tool-title',
        toolTypeLabelSelector: 'dd.tool-type',

        loadingState: 'loading',
        resetState: 'reset',

        deleteToolUrl: '/clients/{0}/treatments/{1}/tools/{2}'
    });

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;

        submitBtn.button(this.attr.loadingState);

        $.ajax({
            url: that.attr.deleteToolUrl.format(that.clientId, that.treatmentId, that.toolId),
            type: 'DELETE'
        })
            .done(function () {
                that.trigger('deleteToolSuccess', {
                    toolId: that.toolId
                });

                that.hideDialog();
            })
            .fail(_.bind(that.serverErrorHandler, that))
            .always(function () {
                submitBtn.button(that.attr.resetState);
            });
    };

    this.onShow = function (event, data) {
        this.clientId = data.clientId;
        this.treatmentId = data.treatmentId;
        this.toolId = data.tool.id;

        this.set('idLabel', data.tool.id);
        this.set('toolTitleLabel', data.tool.title);
        this.set('toolTypeLabel', AdminPortalConstants.TOOL_TYPE[data.tool.type]);

        this.showDialog();
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });

        this.on(document, 'showDeleteToolDialog', this.onShow);
    });
}

module.exports = flight.component(withPanel, withServerError, withDialog, deleteToolDialog);
