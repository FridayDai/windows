'use strict';

var flight = require('flight');
var withForm = require('../common/withForm');
var withDialog = require('../common/withDialog');

function withClientFormDialog () {
    /* jshint validthis:true */

    flight.compose.mixin(this, [
        withForm,
        withDialog
    ]);

    this.attributes({
        subDomainCheckMsg: 'SubDomain can only include letters and numbers.',
        primaryColorCheckMsg:
            "The syntax of primary color hex should be '#123afd' or '#abd', numbers in 0-9, letters in a-f."
    });

    this.initValidation = function () {
        $.validator.addMethod('subDomainCheck', function (value) {
            return /^[0-9a-z]+$/ig.test(value);

        }, this.attr.subDomainCheckMsg);

        $.validator.addMethod('primaryColorCheck', function (value) {
            return /^#([0-9a-f]{3}|[0-9a-f]{6})$/ig.test(value);

        }, this.attr.primaryColorCheckMsg);

        this.formEl.validate({
            rules: {
                subDomain: {
                    subDomainCheck: true
                },
                primaryColorHex: {
                    primaryColorCheck: true
                }
            }
        });
    };

    this._formSuccessProcess = function () {
        this.hideDialog();
    };

    this.after('initialize', function () {
        this.on('formSuccess', this._formSuccessProcess)
    });
}

module.exports = withClientFormDialog;
