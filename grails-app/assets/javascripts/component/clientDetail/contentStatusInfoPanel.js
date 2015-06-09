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

    this.setActiveStaffCount = function (event, diff) {
        var current = parseInt(this.getActiveStaff(), 10);

        this.setActiveStaff(current + diff);
    };

    this.setActiveTreatmentCount = function (event, data) {
        this.setActiveTreatment(data);
    };

    this.after('initialize', function () {
        this.generateGetterSetter([
            'activeStaff',
            'activePatient',
            'activeTreatment'
        ], this);

        this.on(document, 'activeStaffCountChanged', this.setActiveStaffCount);
        this.on(document, 'activeTreatmentCountChanged', this.setActiveTreatmentCount);
    });
}

module.exports = flight.component(withPanel, contentStatusInfoPanel);
