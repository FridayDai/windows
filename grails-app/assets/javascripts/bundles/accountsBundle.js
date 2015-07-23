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
                deleteAccount: '/accounts/{0}/delete',
                updateAccount: '/accounts/{0}/update'
            }
        },
        accountStatus = ["Active", "Inactive"];

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
                        {title: 'ID', data: 'id', width: '15%'},
                        {title: 'Email Address', data: 'email', width: '35%', className: "email"},
                        {
                            title: 'Status',
                            "render": function (data, type, full) {
                                var status = data? data - 1 : full.status ? full.status - 1 : 1;
                                return accountStatus[status];
                            },
                            width: '15%'
                        },
                        {title: 'Enabled', data: "enabled", width: '15%', className: "isEnabled"},
                        {
                            data: function (row, type, set, meta) {
                                if (meta) {
                                    return '<a href="#" class="btn-edit glyphicon glyphicon-pencil" ' +
                                        'data-toggle="modal" data-target="#edit-account-modal" ' +
                                        'aria-hidden="true" data-row="{0}" data-account-id="{1}"></a>'
                                            .format(meta.row, row.id);
                                }
                            }
                        },
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
            }
        };

        page.accountList.init();
    }

    function initAccountDialogForm() {
        var modal = $('#add-account-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#add-account-modal', true);

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
                success: function (data) {
                    if (data.resp === true) {
                        $ele.remove();
                    }
                }
            });
        });
    }


    function initUpdateAccountDialog(accountId, $ele) {
        var modal = $('#edit-account-modal');
        //var form = modal.find('form');
        var updateBtn = modal.find('.update-btn');

        RC.utility.formModal.defaultConfig('#edit-account-modal', true);

        // Setup update button
        updateBtn.click(function () {
            var button = $(this);
            button.button('loading');

            var email = $("#account-email").val();
            var isEnabled = $("#isEnabled").prop("checked");

            var data = {
                email: email,
                enabled: isEnabled
            };

            $.ajax({
                url: opts.urls.updateAccount.format(accountId),
                type: "post",
                data: data,
                success: function (res) {
                    if (res.enabled === true) {
                        $ele.find(".isEnabled").text("true");
                        modal.modal('hide');
                        button.button('reset');
                    } else {
                        $ele.find(".isEnabled").text("false");
                        modal.modal('hide');
                        button.button('reset');
                    }
                }
            });
        });
    }

    function initUpdateAccount() {
        $(opts.table.id).on('click', 'tr .btn-edit', function () {
            var $ele = $(this).closest("tr");
            var accountId = $(this).data("accountId");
            var email = $ele.find(".email").text();
            var isEnable = $ele.find(".isEnabled").text();
            $("#account-email").val(email);
            if (isEnable === "true") {
                $("#isEnabled").prop("checked", true);
            } else {
                $("#isEnabled").prop("checked", false);
            }

            initUpdateAccountDialog(accountId, $ele);
        });
    }


    function init() {
        // Init update account
        initUpdateAccount();

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
