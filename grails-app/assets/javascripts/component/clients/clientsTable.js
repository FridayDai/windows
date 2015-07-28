'use strict';

var flight = require('flight');
var util = require('../../utils/utility');
var withDataTable = require('../common/withDataTable');

function clientsTable() {
    /* jshint validthis:true */

    this.attributes({
        url: '/getClients',

        rowClickUrl: '/clients/{0}/{1}',

        columns: [
            {
                title: 'ID',
                data: 'id',
                width: '10%'
            }, {
                title: 'Client',
                data: 'name',
                width: '35%'
            }, {
                title: 'Active Staff',
                data: 'activeStaffCount',
                width: '15%'
            }, {
                title: 'Active Patient',
                data: 'activePatientCount',
                width: '15%'
            }, {
                title: 'Active Treatment',
                data: 'activeTreatmentCount',
                width: '15%'
            }, {
                data: function (row, type, set, meta) {
                    if (meta) {
                        return ('<span class="copy-btn glyphicon glyphicon-copy" aria-hidden="true" data-row="{0}">' +
                            '</span>').format(meta.row);
                    }
                }
            }
        ]
    });

    this.getRowClickUrl = function (data) {
        return this.attr.rowClickUrl.format(data.id, util.replaceSlashInTitle(data.name));
    };

    this.onCreateClientSuccess = function (event, data) {
        this.addRow(data);
    };

    this.after('initialize', function () {
        this.on(document, 'createClientSuccess', this.onCreateClientSuccess);
    });
}

module.exports = flight.component(withDataTable, clientsTable);
