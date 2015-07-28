'use strict';

var flight = require('flight');
var withDataTable = require('../common/withDataTable');

function announcementsTable() {
    /* jshint validthis:true */

    var ANNOUNCEMENT_COLOR = {
        "#fdddde": "Red"
    };

    var ANNOUNCEMENT_STATUS = {
        "2": "Inactive",
        "1": "Active",
        "0": "undefined"
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
                        '<span class="edit-btn glyphicon glyphicon-edit hide" ',
                        'aria-hidden="true"></span>',
                        '&nbsp;',
                        '<span class="delete-btn glyphicon glyphicon-trash hide" ',
                        'aria-hidden="true"></span>'
                    ].join('');
                },
                width: '20px'
            }
        ]
    });

    this.after('initialize', function () {
        this.on(document, 'createAnnouncementSuccess', this.addRow);
    });
}

module.exports = flight.component(withDataTable, announcementsTable);
