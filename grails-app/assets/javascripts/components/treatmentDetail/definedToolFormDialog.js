var flight = require('flight');
var withDialog = require('../common/withDialog');
var withForm = require('../common/withForm');

var MODELS = {
    CREATE: 'CREATE',
    EDIT: 'EDIT'
};

var SUCCESS_EVENT_NAMES = {
    CREATE: 'createDefinedToolSuccess',
    EDIT: 'editDefinedToolSuccess'
};

function definedToolFormDialog() {
    this.attributes({
        submitBtnSelector: '.create-btn',

        dialogTitleSelector: '.modal-title',

        toolTypeFieldSelector: '#defined-tool-type',
        defaultDueTimeDayFieldSelector: '[name="defaultDueTimeDay"]',
        defaultDueTimeHourFieldSelector: '[name="defaultDueTimeHour"]',
        reminderFieldSelector: '#defined-tool-reminder',

        createUrl: '/clients/{0}/treatments/{1}/tools',
        editUrl: '/clients/{0}/treatments/{1}/tools/{2}'
    });

    this.initValidation = function () {
        $.validator.addMethod('reminderCheck', function (value) {
            var regexp = /^\s*\d+\s*((,\s*\d+\s*)*)$/;

            return regexp.test(value);
        }, "Invalid reminder format.");

        $.validator.addMethod('defaultDueTimeLessReminderCheck', function () {
            var regexp = /\d+/g;
            var dueDayVal = $('#add-defined-tool-modal').find('[name="defaultDueTimeDay"]').val();
            dueDayVal = parseInt(dueDayVal, 10);

            var reminderVal = $('#defined-tool-reminder').val();

            var reminders = _.map(reminderVal.match(regexp), function (val) {
                return parseInt(val, 10);
            });

            if (reminders) {
                return _.max(reminders) <= dueDayVal - 1;
            } else {
                return false;
            }

        }, "The day of max reminder should be less 1 day than default due time.");

        this.formEl.validate({
            rules: {
                reminder: {
                    reminderCheck: true,
                    defaultDueTimeLessReminderCheck: true
                }
            }
        });
    };

    this.onDefaultDayChange = function () {
        this.select('reminderFieldSelector').valid();
    };

    this.modal = 'CREATE';

    this.onCreateModal = function (e, data) {
        this.model = MODELS.CREATE;

        this.setCreateModal(data);

        this.showDialog();
    };

    this.setCreateModal = function (data) {
        this.clearForm();

        this.formEl.data({
            loadingText: 'Loading...'
        });

        this.select('dialogTitleSelector').text('Add Tool');
        this.select('toolTypeFieldSelector').removeAttr('disabled');
        this.select('submitBtnSelector').text('Create');
        this.formEl.attr('action', this.attr.createUrl.format(data.clientId, data.treatmentId));
    };

    this.onEditModal = function (e, data) {
        this.model = MODELS.EDIT;

        this.setEditModal(data);

        this.showDialog();
    };

    this.setEditModal = function (data) {
        this.clearForm();

        this.formEl.data({
            loadingText: 'Updating...'
        });

        this.select('dialogTitleSelector').text('Edit Tool');
        this.select('toolTypeFieldSelector').attr('disabled', 'disabled');
        this.select('submitBtnSelector').text('Update');
        this.formEl.attr('action', this.attr.editUrl.format(data.clientId, data.treatmentId, data.tool.id));

        this.setEditModalFieldValue(data.tool);
    };

    this.setEditModalFieldValue = function (tool) {
        this.select('toolTypeFieldSelector').val(tool.basetoolId);
        this.select('defaultDueTimeDayFieldSelector').val(tool.defaultDueTimeDay);
        this.select('defaultDueTimeHourFieldSelector').val(tool.defaultDueTimeHour);
        this.select('reminderFieldSelector').val(tool.reminder);
    };

    this.onFormSuccess = function (e, data) {
        this.trigger(SUCCESS_EVENT_NAMES[this.model], data);

        this.hideDialog();
    };

    this.after('initialize', function () {
        this.on(document, 'showCreateDefinedToolFormDialog', this.onCreateModal);
        this.on(document, 'showEditDefinedToolFormDialog', this.onEditModal);

        this.on('formSuccess', this.onFormSuccess);

        this.on('change', {
            'defaultDueTimeDayFieldSelector': this.onDefaultDayChange
        });
    });
}

module.exports = flight.component(withDialog, withForm, definedToolFormDialog);
