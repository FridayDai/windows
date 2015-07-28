var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');
var withPanel = require('../common/withPanel');

function accountTableRow() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.update-btn',
        accountEmailSelector: '.email',
        accountEnabledSelector: '.isEnabled',
        accountEditBtnSelector: 'td .btn-edit',
        accountDeleteBtnSelector: 'td .btn-remove'
    });

    this.onAccountInfoChanged = function (event, data) {
        data = data || {};
        this.$node.$ele = data.$ele;
        this.$node.$ele.find('.isEnabled').text(data.enabled);
    };

    this.onShowEditAccountDialog = function () {
        this.trigger('showEditAccountFormDialog', {
            accountEmail: this.get('accountEmail'),
            isEnabled: this.get('accountEnabled'),
            accountId: this.getData('accountEditBtn', 'accountId'),
            $ele: this.select('accountEditBtnSelector').closest("tr")
        })
    };

    this.onShowDeleteAccountDialog = function () {
        this.trigger('showDeleteAccountFormDialog', {
            accountId: this.getData('accountDeleteBtn', 'accountId'),
            $ele: this.select('accountEditBtnSelector').closest("tr")
        });
    };

    this.onClear = function (event, data) {
        this.$node.$ele = data.$ele;
        this.$node.$ele.remove();
    };


    this.after('initialize', function () {
        this.on(document, 'accountInfoChanged', this.onAccountInfoChanged);
        this.on(document, 'deleteAccountSuccess', this.onClear);

        this.on('click', {
            'accountEditBtnSelector': _.bind(this.onShowEditAccountDialog, this),
            'accountDeleteBtnSelector': _.bind(this.onShowDeleteAccountDialog, this)
        });
    });
}

module.exports = flight.component(withForm, withDialog, withPanel, accountTableRow);
