var flight = require('flight');
var withFormDialog = require('../common/withFormDialog');

function editAccountFormDialog() {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withFormDialog
    ]);

    this.attributes({
        accountEmailSelector: '#account-email',
        isEnabledSelector: '#isEnabled',
        submitBtnSelector: '.update-btn',

        loadingState: 'Updating',
        updateAccount: '/accounts/{0}/update'
    });

    this.onEditModal = function (event, data) {
        this.setValue(data);
        this.accountId = data.accountId;
        this.$ele = data.$ele;

        this.formEl.attr('action', this.attr.updateAccount.format(this.accountId));
    };

    this.setValue = function (data) {
        this.select('accountEmailSelector').val(data.accountEmail);

        if (data.isEnabled === "true") {
            this.select('isEnabledSelector').prop("checked", true);
        } else {
            this.select('isEnabledSelector').prop("checked", false);
        }
    };

    this.onFormSuccess = function (e, data) {
        data.$ele = this.$ele;
        data.enabled = data.enabled;

        this.trigger('accountInfoChanged', data);

        this.hideDialog();
    };

    this.after('initialize', function () {
        this.on(document, 'showEditAccountFormDialog', this.onEditModal);
        this.on('formSuccess', this.onFormSuccess);
    });
}

module.exports = flight.component(withFormDialog, editAccountFormDialog);
