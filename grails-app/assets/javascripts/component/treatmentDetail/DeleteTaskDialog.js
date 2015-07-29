var flight = require('flight');
var withDialog = require('../common/withDialog');
var withPanel = require('../common/withPanel');
var withServerError = require('../common/withServerError');

var AdminPortalConstants = require('../../constants/AdminPortalConstants');

function deleteTaskDialog() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.delete-btn',
        idLabelSelector: 'dd.id',
        toolTitleLabelSelector: 'dd.tool-title',
        toolTypeLabelSelector: 'dd.tool-type',
        sendTimeLabelSelector: 'dd.send-time',

        loadingState: 'loading',
        resetState: 'reset',

        deleteToolUrl: '/clients/{0}/treatments/{1}/tasks/{2}'
    });

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;

        submitBtn.button(this.attr.loadingState);

        $.ajax({
            url: that.attr.deleteToolUrl.format(that.clientId, that.treatmentId, that.taskId),
            type: 'DELETE'
        })
            .done(function () {
                that.trigger('deleteTaskSuccess');

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
        this.taskId = data.task.id;

        this.set('idLabel', data.task.id);
        this.set('toolTitleLabel', data.task.toolTitle);
        this.set('toolTypeLabel', AdminPortalConstants.TOOL_TYPE[data.task.toolType]);
        this.set('sendTimeLabel', data.task.sendTimeStr);

        this.showDialog();
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });

        this.on(document, 'showDeleteTaskDialog', this.onShow);
    });
}

module.exports = flight.component(withPanel, withServerError, withDialog, deleteTaskDialog);
