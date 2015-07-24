'use strict';

var flight = require('flight');
var withPanel = require('../common/withPanel');

function contentStatusInfoPanel() {
    /* jshint validthis:true */

    this.attributes({
        'activeStaffSelector': '.active-staff dd',
        'activePatientSelector': '.active-patient dd',
        'activeTreatmentSelector': '.active-treatment dd'
    });

    this.onActiveStaffCountChanged = function (event, diff) {
        var current = parseInt(this.get('activeStaff'), 10);

        this.set('activeStaff', current + diff);
    };

    this.onActiveTreatmentCountChanged = function (event, data) {
        this.set('activeTreatment', data);
    };

    this.after('initialize', function () {
        this.on(document, 'activeStaffCountChanged', this.onActiveStaffCountChanged);
        this.on(document, 'activeTreatmentCountChanged', this.onActiveTreatmentCountChanged);
    });
}

module.exports = flight.component(withPanel, contentStatusInfoPanel);
