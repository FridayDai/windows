'use strict';

var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');

function addTreatmentFormDialog () {
    /* jshint validthis:true */

    this.attributes({
        formSelector: 'form',

        dialogCloseEvent: 'addTreatmentFormDialogClosed',

        primaryButtonSelector: '.create-btn'
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

        this.trigger('treatmentsTableAddRowServed', data);

        this.hideDialog();

        primaryBtn.button(this.attr.resetState);
    };

    this.initForm = function () {
        this.form = this.select('formSelector');

        this.setupForm();
    };

    this.after('initialize', function () {
        this.initForm();
    });
}

module.exports = flight.component(withForm, withDialog, addTreatmentFormDialog);
