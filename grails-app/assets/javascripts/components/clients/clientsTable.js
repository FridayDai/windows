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
                width: '10%',
                searchable: false,
                orderable: false
            }, {
                title: 'Client',
                data: 'name',
                width: '35%',
                name: 'portalName',
                orderable: false
            }, {
                title: 'Active Staff',
                data: 'activeStaffCount',
                width: '15%',
                searchable: false,
                orderable: false
            }, {
                title: 'Active Patient',
                data: 'activePatientCount',
                width: '15%',
                searchable: false,
                orderable: false
            }, {
                title: 'Active Treatment',
                data: 'activeTreatmentCount',
                width: '15%',
                searchable: false,
                orderable: false
            }, {
                searchable: false,
                orderable: false,
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

    this.onCreateClientSuccess = function (e, data) {
        this.addRow(data);
    };

    this.onSearchClientName = function (e, data) {
        this.tableEl.columns('portalName:name').search(data.value).draw();
    };

    this.after('initialize', function () {
        this.on(document, 'createClientSuccess', this.onCreateClientSuccess);
        this.on(document, 'searchClientsWithClientName', this.onSearchClientName);
    });
}

module.exports = flight.component(withDataTable, clientsTable);
