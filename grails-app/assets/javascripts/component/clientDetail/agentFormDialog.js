'use strict';

var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');


function agentFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        dialogCloseEvent: 'agentFormDialogClosed',

        loadingState: 'loading',
        resetState: 'reset',

        formSelector: 'form',
        agentEmailSelector: '#email',
        agentFirstNameSelector: '#firstName',
        agentLastNameSelector: '#lastName',
        modalTitleSelector: '.modal-title',
        primaryButtonSelector: '.update-btn',

        createUrlFormatStr: '/clients/{0}/agents',
        edithUrlFormatStr: '/clients/{0}/agents/{1}'
    });

    this.primaryButtonClicked = function () {
        var primaryBtn = this.select('primaryButtonSelector');

        if (this.form.valid()) {
            primaryBtn.button(this.attr.loadingState);

            this.submitForm();
        }
    };

    this.formSuccess = function (data) {
        var primaryBtn = this.select('primaryButtonSelector');

        data.agentId = data.id;
        data.agentEmail = data.email;
        data.agentFistName = data.firstName;
        data.agentLastName = data.lastName;

        this.trigger('agentInfoChanged', data);

        if (this.model === 'create') {
            this.trigger('activeStaffCountChanged', 1);
        }

        this.hideDialog();

        primaryBtn.button(this.attr.resetState);
    };

    this.setValue = function (data) {
        this.select('agentEmailSelector').val(data.agentEmail);
        this.select('agentFirstNameSelector').val(data.agentFirstName);
        this.select('agentLastNameSelector').val(data.agentLastName);
    };

    this.setEditModal = function () {
        this.model = 'edit';

        this.select('agentEmailSelector').attr('readonly', 'readonly');
        this.select('modalTitleSelector').text('Edit Agent');

        this.select('primaryButtonSelector').text('Update');

        this.form.attr('action', this.attr.edithUrlFormatStr.format(this.clientId, this.agentId));
    };

    this.setCreateModal = function () {
        this.model = 'create';

        this.select('agentEmailSelector').removeAttr('readonly');
        this.select('modalTitleSelector').text('New Agent');

        this.select('primaryButtonSelector').text('Create');

        this.form.attr('action', this.attr.createUrlFormatStr.format(this.clientId));
    };

    this.editModalServed = function (event, data) {
        this.setValue(data);
        this.clientId = data.clientId;
        this.agentId = data.agentId;

        this.setEditModal();
    };

    this.createModalServed = function (event, data) {
        this.clientId = data.clientId;

        this.setCreateModal();
    };

    this.initForm = function () {
        this.form = this.select('formSelector');
        this.setupForm();
    };

    this.after('initialize', function () {
        this.initForm();

        this.on(document, 'editAgentFormDialogServed', this.editModalServed);
        this.on(document, 'createAgentFormDialogServed', this.createModalServed);
    });
}

module.exports = flight.component(withForm, withDialog, agentFormDialog);
