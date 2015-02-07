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
                    lengthChange: false,
                    columns: [
                        {title: 'ID', data: 'id', width: '10%'},
                        {title: 'Client', data: 'name', width: '35%'},
                        {title: 'Active Staff', data: 'activeStaffCount', width: '15%'},
                        {title: 'Active Patient', data: "activePatientCount", width: '15%'},
                        {title: 'Active Treatment', data: "activeTreatmentCount", width: '15%'},
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
                                var client = list.getRowData(this);

                                location.href = '/clients/{0}/{1}'.format(client.id, client.name);
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

                form.ajaxSubmit({
                    success: function (res) {
                        page.clientList.addRow(new RC.models.Client(res));

                        modal.modal('hide');

                        button.button('reset');
                    },

                    error: function (jqXHR) {
                        button.button('reset');

                        if (jqXHR.status === 403) {
                            alert('Permission denied! Please try to refresh page!');
                        }
                    }
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
