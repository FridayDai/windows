'use strict';

var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');

function editClientFormDialog () {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withForm,
        withDialog
    ]);

    this.attributes({
        formSelector: 'form',

        dialogCloseEvent: 'clientFormDialogClosed',

        subDomainCheckMsg: 'SubDomain can only include letters and numbers.',
        primaryColorCheckMsg:
            "The syntax of primary color hex should be '#123afd' or '#abd', numbers in 0-9, letters in a-f.",

        clientNameSelector: '#name',
        subDomainSelector: '#subDomain',
        portalNameSelector: '#portalName',
        primaryColorSelector: '#primaryColorHex'
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

        this.formSuccessProcess(data);

        this.hideDialog();

        primaryBtn.button(this.attr.resetState);
    };

    this.initForm = function () {
        this.form = this.select('formSelector');

        $.validator.addMethod('subDomainCheck', function (value) {
            var regexp = /^[0-9a-z]+$/ig;

            return regexp.test(value);

        }, this.attr.subDomainCheckMsg);

        $.validator.addMethod('primaryColorCheck', function (value) {
            var regexp = /^#([0-9a-f]{3}|[0-9a-f]{6})$/ig;

            return regexp.test(value);

        }, this.attr.primaryColorCheckMsg);

        this.setupForm({
            validation: {
                rules: {
                    subDomain: {
                        subDomainCheck: true
                    },
                    primaryColorHex: {
                        primaryColorCheck: true
                    }
                }
            }
        });
    };

    this.setValue = function (event, data) {
        this.select('clientNameSelector').val(data.clientName);
        this.select('subDomainSelector').val(data.subDomain);
        this.select('portalNameSelector').val(data.portalName);
        this.select('primaryColorSelector').val(data.primaryColor);
    };

    this.after('initialize', function () {
        this.initForm();
    });
}

module.exports = editClientFormDialog;
