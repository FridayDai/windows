//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require models/client

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    // Initialize client list data table
    function initClientList() {
        page.clientList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                var list = this;

                this.table = $('#client-table').DataTable({
                    autoWidth: false,
                    searching: false,
                    columns: [
                        {title: 'ID', data: 'id', width: '10%'},
                        {title: 'Client', data: 'name', width: '35%'},
                        {title: 'Active Staff', data: 'activeStaffCount', width: '15%'},
                        {title: 'Active Patient', data: "activePatientCount", width: '15%'},
                        {title: 'Active Treatment', data: "activeTreatmentCount", width: '15%'},
                        {
                            title: 'Action',
                            data: function (row, type, set, meta) {
                                if (meta) {
                                    return '<span class="copy-btn glyphicon glyphicon-copy" aria-hidden="true" data-row="{0}"></span>'.format(meta.row);
                                }
                            },
                            width: '10%'
                        }
                    ],
                    rowCallback: function (row) {
                        // Setup double click to entry specific client
                        $(row)
                            .click(function () {
                                var index = this.rowIndex - 1;

                                location.href = '/clients/' + list.getRowData(index).id + '/' + list.getRowData(index).name;
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (row) {
                this.table.row.add(row).draw();
            },

            // Get row data
            getRowData: function (index) {
                return this.table.row(index).data();
            }
        };

        page.clientList.init();
    }

    function initClientDialogForm() {
        var modal = $('#client-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#client-modal');

        // Setup create button
        createBtn.click(function () {
            var button = $(this);

            if (form.valid()) {
                button.button('loading');

                form.ajaxSubmit(function (res) {
                    page.clientList.addRow(new RC.models.Client(res));

                    modal.modal('hide');

                    button.button('reset');
                }, function () {
                    button.button('reset');
                });
            }
        });
    }

    function init() {
        // Init add client dialog form
        initClientDialogForm();

        // Init client list
        initClientList();
    }


    // Start processing
    init();
})
(jQuery);
