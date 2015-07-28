var flight = require('flight');
var withDialog = require('../common/withDialog');
var withServerError = require('../common/withServerError');

function deleteAccountFormDialog() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.delete-btn',

        loadingState: 'Deleting',
        resetState: 'reset',

        deleteAccount: '/accounts/{0}/delete'
    });

    this.onSubmit = function (event, data) {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;

        submitBtn.button(this.attr.loadingState);

        $.ajax({
            url: that.attr.deleteAccount.format(that.accountId),
            type: 'DELETE'
        })
            .done(function () {
                data.$ele = that.$ele;
                that.trigger('deleteAccountSuccess', data);

                that.hideDialog();
            })
            .fail(_.bind(that.serverErrorHandler, that))
            .always(function () {
                submitBtn.button(that.attr.resetState);
            });
    };

    this.onShow = function (event, data) {
        this.accountId = data.accountId;
        this.$ele = data.$ele;
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });

        this.on(document, 'showDeleteAccountFormDialog', this.onShow);
    });
}

module.exports = flight.component(withDialog, withServerError, deleteAccountFormDialog);

