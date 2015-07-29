var flight = require('flight');
var withDialog = require('../common/withDialog');
var withServerError = require('../common/withServerError');

function deleteAnnouncementDialog () {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.delete-btn',

        loadingState: 'loading',
        resetState: 'reset',

        deleteAnnounceUrl: '/announcements/{0}'
    });

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;

        submitBtn.button(this.attr.loadingState);

        $.ajax({
            url: that.attr.deleteAnnounceUrl.format(that.announceId),
            type: 'DELETE'
        })
            .done(function () {
                that.trigger('deleteAnnouncementSuccess',that.$ele);

                that.hideDialog();
            })
            .fail(_.bind(that.serverErrorHandler, that))
            .always(function () {
                submitBtn.button(that.attr.resetState);
            });
    };

    this.onShow = function (event, data) {
        this.announceId = data.announceId;
        this.$ele = data.$ele;
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });

        this.on(document, 'showDeleteAnnounceFormDialog', this.onShow);
    });
}

module.exports = flight.component(withDialog, withServerError, deleteAnnouncementDialog);

