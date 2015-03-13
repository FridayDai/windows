//= require share/baseBundle
//= require ../bower_components/jquery/dist/jquery
//= require ../bower_components/DataTables/media/js/jquery.dataTables
(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    var opts = {
        urls: {
            query: '/getAnnouncements'
        }
    };

    function initAnnoucementsList() {
        page.annoucementsList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                var list = this;

                this.table = $('#announcements-table').DataTable({
                    autoWidth: false,
                    searching: false,
                    lengthChange: false,
                    serverSide: true,
                    pageLength: $('#announcements-table').data("pagesize"),
                    fnDrawCallback: function() {
                        $(this.parents()[1]).show();
                    },
                    ajax: $.fn.dataTable.pipeline({
                        url: opts.urls.query,
                        pages: 2, // number of pages to cache
                    }),
                    deferLoading: $('#announcements-table').data("total"),
                    order: [[ 0, 'desc' ]],
                    columns: [
                        {title: 'ID', data: 'id', width: '10%'},
                        {title: 'Announcement', data: 'announcement', width: '35%'},
                        {title: 'Status', data: 'status', width: '15%'},
                        {title: 'Background', data: 'background', width: '15%'},
                        {title: 'Created Time', data: 'timeCreated', width: '15%'},
                        {
                            data: function (row, type, set, meta) {
                                if (meta) {
                                    return '<span class="copy-btn glyphicon glyphicon-copy" ' +
                                        'aria-hidden="true" data-row="{0}"></span>'
                                            .format(meta.row);
                                }
                            }
                        }
                    ],
                    rowCallback: function (row) {
                        // Setup double click to entry specific client
                        $(row)
                            .click(function () {
                                var annoucement = list.getRowData(this);

                                location.href = '/announcements/{0}'.format(annoucement.id);
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (client) {
                this.table.row.add(client).draw();
            },

            // Get row data
            getRowData: function (rowEL) {
                return this.table.row(rowEL).data();
            }
        };

        page.annoucementsList.init();
    }

    function init() {
        initAnnoucementsList();
    }

    init();

})(jQuery);
