'use strict';

var flight = require('flight');
var util = require('../../utils/utility');
var withDataTable = require('../common/withDataTable');

function announcementsTable() {
    /* jshint validthis:true */

    var ANNOUNCEMENT_COLOR = {
        "#fdddde": "Red"
    };

    var ANNOUNCEMENT_COLOR_REVERSE = {
        "Red": "#fdddde"
    };

    var ANNOUNCEMENT_STATUS = {
        "2": "Inactive",
        "1": "Active"
    };

    var ANNOUNCEMENT_STATUS_REVERSE = {
        "Inactive": "2",
        "Active": "1"
    };

    this.attributes({
        url: '/getAnnouncements',

        columns: [
            {title: 'ID', data: 'id', width: '5%'},
            {title: 'Announcement', data: 'content', width: '55%'},
            {
                title: 'Status',
                data: 'status',
                width: '15%',
                render: function (data) {
                    return ANNOUNCEMENT_STATUS[data];
                }
            },
            {
                title: 'Background',
                data: 'colorHex',
                width: '15%',
                render: function (data) {
                    return ANNOUNCEMENT_COLOR[data];
                }
            },
            {
                title: '',
                data: function () {
                    return [
                        '<span class="edit-btn glyphicon glyphicon-edit" ',
                        'aria-hidden="true"></span>',
                        '&nbsp;',
                        '<span class="delete-btn glyphicon glyphicon-trash" ',
                        'aria-hidden="true"></span>'
                    ].join('');
                },
                width: '20px'
            }
        ]
    });

    this.getRowClickUrl = function (data) {
        return this.attr.rowClickUrl.format(data.id, util.replaceSlashInTitle(data.name));
    };

    this.after('initialize', function () {
        this.on(document, 'createClientSuccess', this.addRow);
    });
}

module.exports = flight.component(withDataTable, announcementsTable);
