'use strict';

var flight = require('flight');
var withAnnouncementFormDialog = require('./withAnnouncementFormDialog');

function addAnnouncementFormDialog() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.create-btn'
    });

    this.onFormSuccess = function (e, data) {
        this.trigger('createAnnouncementSuccess', data);
    };

    this.after('initialize', function () {
        this.on('formSuccess', this.onFormSuccess);
    });
}

module.exports = flight.component(withAnnouncementFormDialog, addAnnouncementFormDialog);
