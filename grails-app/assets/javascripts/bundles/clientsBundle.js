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

    var opts = {
        urls: {
            query: '/getClients'
        }
    };

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
                    serverSide: true,
                    pageLength: $('#client-table').data("pagesize"),
                    fnDrawCallback: function() {
                        $(this.parents()[1]).show();
                    },
                    ajax: $.fn.dataTable.pipeline({
                        url: opts.urls.query,
                        pages: 2 // number of pages to cache
                    }),
                    deferLoading: $('#client-table').data("total"),
                    order: [[ 0, 'desc' ]],
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

                                location.href =
                                    '/clients/{0}/{1}'.format(client.id, RC.utility.replaceSlashInTitle(client.name));
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

        RC.utility.formModal.defaultConfig('#client-modal', true);

        $.validator.addMethod('subdomainCheck', function (value) {
            var regexp = /^[0-9a-z]+$/ig;

            return regexp.test(value);

        }, "Subdomain can only include letters and numbers.");

        $.validator.addMethod('primaryColorCheck', function (value) {
            var regexp = /^#([0-9a-f]{3}|[0-9a-f]{6})$/ig;

            return regexp.test(value);

        }, "The syntax of primary color hex should be '#123afd' or '#abd', numbers in 0-9, letters in a-f.");

        form.validate({
            rules: {
                subDomain: {
                    subdomainCheck: true
                },
                primaryColorHex: {
                    primaryColorCheck: true
                }
            }
        });

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
                        var serverErrorEl = modal.find('.rc-server-error');

                        button.button('reset');

                        if (jqXHR.status === 403) {
                            alert('Permission denied! Please try to refresh page!');
                        } else {
                            serverErrorEl.text(jqXHR.responseJSON.error.errorMessage);
                            serverErrorEl.show();
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
