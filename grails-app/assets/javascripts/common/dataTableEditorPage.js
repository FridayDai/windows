//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formBundle
//= require share/dialogBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = RC.pages = RC.pages || {};

    // Initialize data table
    function initDataTable() {
        var page = this;

        var dataTable = {
            // DataTable instance for this table
            table: null,

            // Initialize data table
            init: function () {
                if (!page.tableSel) {
                    throw 'DataTable need tableSelector property.';
                }

                // default table options
                var defaultOps = {
                    searching: false
                };

                // Specific page can using tableOps to override defaulg dataTable options
                var ops = _.extend({}, defaultOps, page.tableOps)

                this.table = $(!page.tableSel).DataTable(ops);
            },

            // Add one new row
            addRow: function (row) {
                this.table.row.add(row).draw();
            },

            // Edit one row
            editRow: function (row) {
                this.editor.setValue(row);
                this.editor.show();
            }
        };

        dataTable.init();

        return dataTable;
    }

    function initDialogForm() {
        var page = this;

        var dialogForm = {
            // ui-Dialog instance of this dialog form
            dialog: null,

            // form instance of this dialog form
            form: null,

            init: function () {
                var that = this,
                    buttonsTemp;

                // Init dialog
                if (!page.dialogSel) {
                    throw 'DialogForm need tableSelector property.';
                }

                var defaultOps = {
                    autoOpen: false,
                    height: 'auto',
                    width: 'auto',
                    modal: true,
                    buttons: [
                        {
                            text: 'Create',
                            click: function (e) {
                                var dialog = this;

                                if (page.beforeFormValidate && page.beforeFormValidate.call(this, e)) {
                                    if (that.form.valid()) {
                                        that.form.ajaxSubmit(function (res) {
                                            page.ajaxSubmit && page.ajaxSubmit(res);

                                            $(dialog).dialog("close");
                                        });
                                    }
                                }
                            }
                        }
                    ],
                    close: function () {
                        that.form[0].reset();
                        that.form.find('.form-group').removeClass('has-error');
                        that.form.find('label.help-block').remove();
                    }
                };

                // Specific page can using tableOps to override defaulg dataTable options
                var ops = _.extend({}, defaultOps, page.dialogOps);

                this.dialog = $(page.dialogSel).dialog(ops);

                // Init form
                this.form = $(this.dialog).find('form');
                this.form.validate({
                    errorClass: 'help-block',
                    highlight: function (element) {
                        $(element).parents('.form-group').addClass('has-error');
                    },
                    unhighlight: function (element) {
                        $(element).parents('.form-group').removeClass('has-error');

                    }
                });
            },

            show: function () {
                this.dialog.dialog('open');
            },

            close: function () {
                this.dialog.dialog('close');
            },

            setValue: function (data) {
                $('#name').val(data.name);
            }
        };

        dialogForm.init();

        return dialogForm;
    }

    function init() {
        // Init add/edit dialog form
        this.editor = initDialogForm.call(this);

        // Init data table
        this.dataTable = initDataTable.call(this);
    }


    page.DataTablePage = {
        // data table of this page
        dataTable: null,

        // Editor of this page
        editor: null,

        // Run this page
        run: function () {
            init.call(this);

            if (this.init) {
                this.init();
            }
        }
    }
})
(jQuery);
