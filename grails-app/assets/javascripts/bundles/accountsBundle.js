//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require models/account

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    var opts = {
        table: {
            id: "#account-table"
        },
        urls: {
            query: '/getAccounts',
            deleteAccount: '/accounts/{0}/delete'
        }
    };

    // Initialize account list data table
    function initAccountList() {
        page.accountList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                //var list = this;

                this.table = $(opts.table.id).DataTable({
                    autoWidth: false,
                    searching: false,
                    lengthChange: false,
                    serverSide: true,
                    pageLength: $(opts.table.id).data("pagesize"),
                    fnDrawCallback: function () {
                        $(this.parents()[1]).show();
                    },
                    ajax: $.fn.dataTable.pipeline({
                        url: opts.urls.query,
                        pages: 2// number of pages to cache
                    }),
                    deferLoading: $(opts.table.id).data("total"),
                    order: [[0, 'desc']],
                    columns: [
                        {title: 'ID', data: 'id', width: '10%'},
                        {title: 'Email Address', data: 'email', width: '35%'},
                        {title: 'Status', data: 'status', width: '15%'},
                        {title: 'Enabled', data: "enabled", width: '15%'},
                        {
                            data: function (row, type, set, meta) {
                                if (meta) {
                                    return '<a href="#" class="btn-remove glyphicon glyphicon-trash" ' +
                                        'aria-hidden="true" data-row="{0}" data-account-id="{1}"></a>'
                                            .format(meta.row, row.id);
                                }
                            }
                        }
                    ]
                });
            },

            // Add one new row
            addRow: function (account) {
                this.table.row.add(account).draw();
            },

            //Delete one row
            deleteRow: function (ele) {
                this.table.row.remove(ele).draw();
            }

            ////Get row data
            //getRowData: function (rowEL) {
            //    return this.table.row(rowEL).data();
            //}
        };

        page.accountList.init();
    }

    function initAccountDialogForm() {
        var modal = $('#account-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#account-modal', true);

        // Setup create button
        createBtn.click(function () {
            var button = $(this);

            if (form.valid()) {
                button.button('loading');

                form.ajaxSubmit({
                    success: function (res) {
                        page.accountList.addRow(new RC.models.Account(res));

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

    function initDeleteAccount() {
        $(opts.table.id).on('click', 'tr .btn-remove', function () {
            var $ele = $(this).closest("tr");
            var accountId = $(this).data("accountId");

            $.ajax({
                url: opts.urls.deleteAccount.format(accountId),
                type: "delete",
                success: function () {
                    page.accountList.deleteRow($ele);
                }
            });
        });
    }

    function init() {
        // Init delete account
        initDeleteAccount();

        // Init add account dialog form
        initAccountDialogForm();

        // Init account list
        initAccountList();
    }


    // Start processing
    init();
})
(jQuery);
