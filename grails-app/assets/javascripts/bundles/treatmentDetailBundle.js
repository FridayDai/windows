//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require models/tool
//= require models/task

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    function getClientAndTreatmentId() {
        var data = $('#treatment-info-panel').data();

        page.clientId = data.clientId;
        page.treatmentId = data.treatmentId;
    }

    // Initialize tool list data table
    function initToolList() {
        page.toolList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                var list = this;

                this.table = $('#tool-pool-table').DataTable({
                    searching: false,
                    ajax: {
                        url: '{0}/clients/{1}/treatments/{2}/tools'.format(RC.constants.baseUrl, page.clientId, page.treatmentId),
                        dataSrc: function (response) {
                            return _.map(response.items, function (obj) {
                                return new RC.models.Tool(obj)
                            })
                        }
                    },
                    columns: [
                        {title: 'ID', data: 'id'},
                        {title: 'Tool Title', data: 'title'},
                        {title: 'Tool Description', data: 'description'},
                        {title: 'Tool Type', data: "type"},
                        {title: 'Last Updated', data: "lastUpdated"},
                        {
                            title: '',
                            data: function (row, type, set, meta) {
                                return '<span class="edit-btn glyphicon glyphicon-edit" aria-hidden="true" data-row="{0}"></span>'.format(meta.row);
                            }
                        }
                    ],
                    initComplete: function () {
                        var table = this;

                        // Setup edit button
                        $(this)
                            .find('.edit-btn')
                            .click(function () {
                                var index = $(this).attr('data-row');

                                list.editRow(list.getRowData(index));
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (row) {
                this.table.row.add(row).draw();
            },

            // Edit one row
            editRow: function (row) {
                this.editor.setValue(row);
                this.editor.show();
            },

            // Get row data
            getRowData: function (index) {
                return this.table.row(index).data();
            }
        };

        page.toolList.init();
    }

    // Initialize task list data table
    function initTaskList() {
        page.taskList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                var list = this;

                this.table = $('#task-table').DataTable({
                    searching: false,
                    ajax: {
                        url: '{0}/clients/{1}/treatments/{2}/tasks'.format(RC.constants.baseUrl, page.clientId, page.treatmentId),
                        dataSrc: function (response) {
                            return _.map(response.items, function (obj) {
                                return new RC.models.Task(obj)
                            })
                        }
                    },
                    columns: [
                        {title: 'ID', data: 'id'},
                        {title: 'Tool Title', data: 'title'},
                        {title: 'Tool Description', data: 'description'},
                        {title: 'Tool Type', data: "type"},
                        {title: 'Send Time', data: "sendTime"},
                        {title: 'Notify', data: "notify"},
                        {title: 'Last Updated', data: "lastUpdated"},
                        {
                            title: '',
                            data: function (row, type, set, meta) {
                                return '<span class="edit-btn glyphicon glyphicon-edit" aria-hidden="true" data-row="{0}"></span>'.format(meta.row);
                            }
                        }
                    ],
                    initComplete: function () {
                        var table = this;

                        // Setup edit button
                        $(this)
                            .find('.edit-btn')
                            .click(function () {
                                var index = $(this).attr('data-row');

                                list.editRow(list.getRowData(index));
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (row) {
                this.table.row.add(row).draw();
            },

            // Edit one row
            editRow: function (row) {
                this.editor.setValue(row);
                this.editor.show();
            },

            // Get row data
            getRowData: function (index) {
                return this.table.row(index).data();
            }
        };

        page.taskList.init();
    }

    function initEditTreatmentDialogForm() {
        var editTreatmentModal = $('#edit-treatment-modal');
        var editTreatmentForm = editTreatmentModal.find('form');
        var updateBtn = editTreatmentModal.find('.update-btn');

        RC.utility.formModal.defaultConfig({
            selector: '#edit-treatment-modal',
            fieldSelectorArray: [
                ['.main-info .title strong', '#edit-treatment-title'],
                ['.main-info .template-title .text', '#edit-treatment-tmpTitle'],
                ['#treatment-info-panel .description', '#edit-treatment-description']
            ]
        });

        editTreatmentModal.on('show.bs.modal', function () {
            var isSurgeryRequired = $('.main-info .surgery-time-required .text').text();
            var surgeryRequiredField = $('#edit-treatment-surgeryTimeRequired');

            if (isSurgeryRequired === 'Yes') {
                surgeryRequiredField.val('true');
            } else {
                surgeryRequiredField.val('false');
            }

            RC.utility.formModal.getValFromFieldArray('#edit-treatment-modal form');
        });

        updateBtn.click(function () {
            var button = $(this);

            if (editTreatmentForm.valid()) {
                button.button('loading');

                editTreatmentForm.ajaxSubmit(function () {
                    RC.utility.formModal.setValFromFieldArray('#edit-treatment-modal form');

                    var surgeryRequiredLabel = $('.main-info .surgery-time-required .text');
                    var isSurgeryRequired = $('#edit-treatment-surgeryTimeRequired').val();

                    if (isSurgeryRequired === "true") {
                        surgeryRequiredLabel.text('Yes');
                    } else {
                        surgeryRequiredLabel.text('No');
                    }

                    editTreatmentModal.modal('hide');

                    button.button('reset');
                }, function () {
                    button.button('reset');
                });
            }
        });
    }

    function initCloseTreatmentDialogForm() {
        var closeTreatmentModal = $('#close-treatment-modal');
        var deleteBtn = closeTreatmentModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);

            button.button('loading');

            $.ajax({
                url: '/clients/' + page.clientId + '/treatments/' + page.treatmentId,
                type: 'DELETE'
            }).done(function () {
                $('.main-info .status .text').text('Closed');

                closeTreatmentModal.modal('hide');
            }).always(function () {
                button.button('reset');
            });
        });
    }

    function init() {
        // Get client id and treatment id
        getClientAndTreatmentId();

        // Init edit treatment dialog form
        initEditTreatmentDialogForm();

        // Init close treatment dialog form
        initCloseTreatmentDialogForm();

        // Init treatment list
        initToolList();

        initTaskList();
    }


    // Start processing
    init();
})
(jQuery);
