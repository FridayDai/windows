'use strict';

var flight = require('flight');
var utility = require('../common/utility');
var withDataTable = require('../common/withDataTable');

function clientsTable() {
    /* jshint validthis:true */

    this.attributes({
        url: '/getClients',

        rowClickFormatStr: '/clients/{0}/{1}',

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

    this.getRowClickFormatStr = function (data) {
        return this.attr.rowClickFormatStr.format(data.id, utility.replaceSlashInTitle(data.name));
    };

    this.after('initialize', function () {
        this.on(document, 'clientsTableAddRow', this.addRow);
    });
}

module.exports = flight.component(withDataTable, clientsTable);
