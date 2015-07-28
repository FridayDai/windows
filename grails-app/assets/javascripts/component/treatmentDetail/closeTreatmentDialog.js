var flight = require('flight');
var withDialog = require('../common/withDialog');
var withServerError = require('../common/withServerError');

function closeTreatmentDialog() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.delete-btn',

        loadingState: 'loading',
        resetState: 'reset',

        closeTreatmentUrl: '/clients/{0}/treatments/{1}'
    });

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;

        submitBtn.button(this.attr.loadingState);

        $.ajax({
            url: that.attr.closeTreatmentUrl.format(that.clientId, that.treatmentId),
            type: 'DELETE'
        })
            .done(function () {
                that.trigger('closeTreatmentSuccess');

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
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });

        this.on(document, 'showCloseTreatmentDialog', this.onShow);
    });
}

module.exports = flight.component(withServerError, withDialog, closeTreatmentDialog);
