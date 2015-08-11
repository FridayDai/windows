var flight = require('flight');
var withFormDialog = require('../common/withFormDialog');

function addAnnouncementFormDialog() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withFormDialog
    ]);

    this.attributes({
        submitBtnSelector: '.create-btn',
        hiddenStatus: 'form input[name="status"]'
    });

    this.onFormSuccess = function (e, data) {
        this.trigger('createAnnouncementSuccess', data);
        this.hideDialog();
    };

    this.initAnnouncementFormDialog = function() {
        this.select('hiddenStatus').val('1');
    };

    this.after('initialize', function () {
        this.initAnnouncementFormDialog();

        this.on('formSuccess', this.onFormSuccess);
    });
}

module.exports = flight.component(withFormDialog, addAnnouncementFormDialog);
