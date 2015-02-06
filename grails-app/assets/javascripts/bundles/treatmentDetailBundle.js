//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require ../bower_components/moment/moment
//= require ../lib/countdown
//= require models/tool
//= require models/task

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    var TOOL_TYPE = {
        1: 'Basic',
        2: 'Outcome',
        3: 'SDM'
    };

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
                        url: '{0}/clients/{1}/treatments/{2}/tools'
                            .format(RC.constants.baseUrl, page.clientId, page.treatmentId),
                        dataSrc: function (response) {
                            return _.map(response.items, function (obj) {
                                return new RC.models.Tool(obj);
                            });
                        }
                    },
                    columns: [
                        {title: 'ID', data: 'id'},
                        {title: 'Tool Title', data: 'title'},
                        {title: 'Tool Description', data: 'description'},
                        {
                            title: 'Tool Type',
                            data: function (row) {
                                return TOOL_TYPE[row.type];
                            }
                        },
                        {
                            title: 'Last Updated',
                            data: function (row) {
                                return moment(row.lastUpdated).format('MMM/DD/YYYY  h:mm:ssA');
                            }
                        },
                        {
                            title: '',
                            data: function (row, type, set, meta) {
                                return [
                                    '<span class="edit-btn glyphicon glyphicon-edit" ',
                                    'aria-hidden="true" data-row="{0}"></span>',
                                    '&nbsp;',
                                    '<span class="delete-btn glyphicon glyphicon-trash" ',
                                    'aria-hidden="true" data-row="{0}"></span>'
                                ].join('').format(meta.row);
                            },
                            width: '20px'
                        }
                    ],
                    rowCallback: function (row, data) {
                        if (_.isUndefined(data.defaultDueTimeDay) && data.defaultDueTime) {
                            var duration = countdown(
                                null,
                                new Date().getTime() + data.defaultDueTime,
                                countdown.DAYS | countdown.HOURS
                            );

                            _.extend(data, {
                                defaultDueTimeDay: duration.days,
                                defaultDueTimeHour: duration.hours
                            });
                        }

                        $(row)
                            .find('.edit-btn')
                            .click(function () {
                                var index = $(this).attr('data-row');

                                list.editRow(index, list.getRowData(index));
                            });

                        $(row)
                            .find('.delete-btn')
                            .click(function () {
                                var index = $(this).attr('data-row');

                                list.showDeleteModal(index, list.getRowData(index));
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (tool) {
                this.table.row.add(tool).draw();
            },

            // Edit one row
            editRow: function (index, tool) {
                this.editor.setEditModal(index, tool);
                this.editor.setValue(tool);
                this.editor.show();
            },

            showDeleteModal: function (index, tool) {
                var modal = $('#delete-tool-modal');

                modal.find('.row-index').val(index);
                modal.find('.id').text(tool.id);
                modal.find('.tool-title').text(tool.title);
                modal.find('.tool-type').text(TOOL_TYPE[tool.type]);

                modal.modal('show');
            },

            deleteRow: function (index) {
                this.table.row(index).remove().draw();
            },

            // Get row data
            getRowData: function (index) {
                return this.table.row(index).data();
            },

            updateRow: function (index, task) {
                this.table.row(index).data(task).draw();
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
                        url: '{0}/clients/{1}/treatments/{2}/tasks'
                            .format(RC.constants.baseUrl, page.clientId, page.treatmentId),
                        dataSrc: function (response) {
                            return _.map(response.items, function (obj) {
                                return new RC.models.Task(obj);
                            });
                        }
                    },
                    columns: [
                        {title: 'ID', data: 'id'},
                        {title: 'Tool Title', data: 'toolTitle'},
                        {title: 'Tool Description', data: 'toolDescription'},
                        {
                            title: 'Tool Type',
                            data: function (row) {
                                return TOOL_TYPE[row.toolType];
                            }
                        },
                        {
                            title: 'Send Time',
                            data: function (row) {
                                var sendTime = row.sendTimeOffset;
                                var timeStr = '';
                                var direction = 1;
                                var duration = {};

                                if (sendTime === 0) {
                                    timeStr = 'Immediate';
                                } else {
                                    duration = countdown(
                                        null,
                                        new Date().getTime() + sendTime,
                                        countdown.WEEKS | countdown.DAYS | countdown.HOURS | countdown.MINUTES
                                    );

                                    timeStr = '{0}W {1}D {2}H {3}M'.format(
                                        duration.weeks,
                                        duration.days,
                                        duration.hours,
                                        duration.minutes
                                    );

                                    if (sendTime < 0) {
                                        direction = -1;
                                        timeStr += ' Before Surgery';
                                    } else {
                                        timeStr += ' After Surgery';
                                    }
                                }

                                _.extend(row, {
                                    sendTimeDirection: direction || 1,
                                    sendTimeWeeks: duration.weeks || 0,
                                    sendTimeDays: duration.days || 0,
                                    sendTimeHours: duration.hours || 0,
                                    sendTimeMinutes: duration.minutes || 0,
                                    sendTimeStr: timeStr
                                });

                                return timeStr;
                            }
                        },
                        {
                            title: 'Due Time',
                            data: function (row) {
                                var dueTime = row.defaultDueTime;
                                var timeStr = '';
                                var duration = {};

                                if (dueTime === 0) {
                                    timeStr = 'NA';
                                } else {
                                    duration = countdown(
                                        null,
                                        new Date().getTime() + dueTime,
                                        countdown.DAYS | countdown.HOURS
                                    );

                                    timeStr = '{0}D {1}H'.format(
                                        duration.days,
                                        duration.hours
                                    );
                                }

                                return timeStr;
                            }
                        },
                        {
                            title: 'Last Updated',
                            data: function (row) {
                                return moment(row.lastUpdated).format('MMM/DD/YYYY  h:mm:ssA');
                            }
                        },
                        {
                            title: '',
                            data: function (row, type, set, meta) {
                                return [
                                    '<span class="edit-btn glyphicon glyphicon-edit" ',
                                    'aria-hidden="true" data-row="{0}"></span>',
                                    '&nbsp;',
                                    '<span class="delete-btn glyphicon glyphicon-trash" ',
                                    'aria-hidden="true" data-row="{0}"></span>'
                                ].join('').format(meta.row);
                            },
                            width: '20px'
                        }
                    ],
                    rowCallback: function (row) {
                        $(row)
                            .find('.edit-btn')
                            .click(function () {
                                var index = $(this).attr('data-row');

                                list.editRow(index, list.getRowData(index));
                            });

                        $(row)
                            .find('.delete-btn')
                            .click(function () {
                                var index = $(this).attr('data-row');

                                list.showDeleteModal(index, list.getRowData(index));
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (task) {
                this.table.row.add(task).draw();
            },

            // Update row
            updateRow: function (index, task) {
                this.table.row(index).data(task).draw();
            },

            // Edit one row
            editRow: function (index, task) {
                this.editor.setEditModal(index, task);
                this.editor.setValue(task);
                this.editor.show();
            },

            showDeleteModal: function (index, task) {
                var modal = $('#delete-item-modal');

                modal.find('.row-index').val(index);
                modal.find('.id').text(task.id);
                modal.find('.tool-title').text(task.toolTitle);
                modal.find('.tool-type').text(TOOL_TYPE[task.toolType]);
                modal.find('.send-time').text(task.sendTimeStr);

                modal.modal('show');
            },

            deleteRow: function (index) {
                this.table.row(index).remove().draw();
            },

            // Get row data
            getRowData: function (index) {
                return this.table.row(index).data();
            },

            reload: function () {
                this.table.ajax.reload();
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

                editTreatmentForm.ajaxSubmit({
                    success: function () {
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

    function initCloseTreatmentDialogForm() {
        var closeTreatmentModal = $('#close-treatment-modal');
        var deleteBtn = closeTreatmentModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);

            button.button('loading');

            $.ajax({
                url: '/clients/{0}/treatments/{1}'.format(page.clientId, page.treatmentId),
                type: 'DELETE'
            }).done(function () {
                $('.main-info .status .text').text('Closed');

                closeTreatmentModal.modal('hide');
            }).always(function () {
                button.button('reset');
            });
        });
    }

    function initAddDefinedToolDialogForm() {
        var addDefinedToolModal = $('#add-defined-tool-modal');
        var addDefinedToolForm = addDefinedToolModal.find('form');
        var createBtn = addDefinedToolModal.find('.create-btn');

        //RC.utility.formModal.defaultConfig({
        //    selector: '#add-defined-tool-modal'
        //});

        $.validator.addMethod('defaultDueTimeEmptyCheck', function (value, element) {
            var total = 0;

            $(element).parent().find('select').each(function () {
                total += parseInt($(this).val(), 10);
            });

            return total !== 0;
        }, "Default due time can't be 0!");

        addDefinedToolForm.validate({
            groups: {
                defaultDueTime: "defaultDueTimeDay defaultDueTimeHour"
            },
            rules: {
                defaultDueTimeDay: {
                    defaultDueTimeEmptyCheck: true
                },
                defaultDueTimeHour: {
                    defaultDueTimeEmptyCheck: true
                }
            },
            errorPlacement: function (error, element) {
                if (element.attr("name") === "defaultDueTimeDay" || element.attr("name") === "defaultDueTimeHour") {
                    addDefinedToolModal.find('.default-due-time').append(error);
                } else {
                    error.insertAfter(element);
                }
            }
        });

        addDefinedToolModal.on('hidden.bs.modal', function () {
            addDefinedToolForm[0].reset();
            addDefinedToolForm.find('.form-group').removeClass('has-error');
            addDefinedToolForm.find('label.help-block').remove();
        });

        createBtn.click(function () {
            var button = $(this);

            if (addDefinedToolForm.valid()) {
                button.button('loading');

                addDefinedToolForm.ajaxSubmit({
                    success: function (res) {
                        if (addEditToolEditor.modal === 'ADD') {
                            page.toolList.addRow(new RC.models.Tool(res));
                        } else {
                            page.toolList.updateRow(addEditToolEditor.editingRow, new RC.models.Tool(res));
                            page.taskList.reload();
                        }

                        addDefinedToolModal.modal('hide');

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

        var idField = addDefinedToolForm.find('[name="id"]');
        var defaultDueTimeDayField = addDefinedToolForm.find('[name="defaultDueTimeDay"]');
        var defaultDueTimeHourField = addDefinedToolForm.find('[name="defaultDueTimeHour"]');
        var reminderField = addDefinedToolForm.find('[name="reminder"]');
        var titleEl = addDefinedToolModal.find('.modal-title');
        var primaryBtnEl = addDefinedToolModal.find('.btn-primary');
        var url = '/clients/{0}/treatments/{1}/tools';

        var addEditToolEditor = {
            modal: 'ADD',
            editingRow: 0,

            setEditModal: function (index, tool) {
                this.modal = 'EDIT';
                this.editingRow = index;

                addDefinedToolForm[0].reset();

                primaryBtnEl.data({
                    loadingText: 'Updating...'
                });

                idField.attr('disabled', 'disabled');
                titleEl.text('Edit Tool');
                primaryBtnEl.text('Update');
                addDefinedToolForm.attr('action', url.format(page.clientId, page.treatmentId) + '/' + tool.id);
            },

            setCreateModal: function () {
                this.modal = 'ADD';

                addDefinedToolForm[0].reset();

                primaryBtnEl.data({
                    loadingText: 'Loading...'
                });

                idField.removeAttr('disabled');
                titleEl.text('Add Tool');
                primaryBtnEl.text('Create');
                addDefinedToolForm.attr('action', url.format(page.clientId, page.treatmentId));
            },

            setValue: function (tool) {
                defaultDueTimeDayField.val(tool.defaultDueTimeDay);
                defaultDueTimeHourField.val(tool.defaultDueTimeHour);
                reminderField.val(tool.reminder);
            },

            show: function () {
                addDefinedToolModal.modal('show');
            }
        };

        $('#add-defined-tool-btn').click(function () {
            addEditToolEditor.setCreateModal();
        });

        page.toolList.editor = addEditToolEditor;
    }

    function initDeleteDefinedToolDialogForm() {
        var deleteToolModal = $('#delete-tool-modal');
        var deleteBtn = deleteToolModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);
            var toolId = deleteToolModal.find('.id').text();
            var rowIndex = deleteToolModal.find('.row-index').val();

            button.button('loading');

            $.ajax({
                url: '/clients/{0}/treatments/{1}/tools/{2}'.format(page.clientId, page.treatmentId, toolId),
                type: 'DELETE'
            }).done(function () {
                page.toolList.deleteRow(rowIndex);
                page.taskList.reload();

                deleteToolModal.modal('hide');
            }).always(function () {
                button.button('reset');
            });
        });
    }

    function initAddTaskDialogForm() {
        var addTaskModal = $('#add-item-modal');
        var addTaskForm = addTaskModal.find('form');
        var createBtn = addTaskModal.find('.create-btn');

        RC.utility.formModal.defaultConfig({
            selector: '#add-item-modal'
        });

        createBtn.click(function () {
            var button = $(this);

            if (addTaskForm.valid()) {
                button.button('loading');

                addTaskForm.ajaxSubmit({
                    success: function (res) {
                        if (addEditTaskEditor.modal === 'ADD') {
                            page.taskList.addRow(new RC.models.Task(res));
                        } else {
                            page.taskList.updateRow(addEditTaskEditor.editingRow, new RC.models.Task(res));
                        }

                        addTaskModal.modal('hide');

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

        var toolIdField = addTaskForm.find('[name="toolId"]');
        var sendTimeDirectionField = addTaskForm.find('[name="sendTimeDirection"]');
        var sendTimeWeeksField = addTaskForm.find('[name="sendTimeWeeks"]');
        var sendTimeDaysField = addTaskForm.find('[name="sendTimeDays"]');
        var sendTimeHoursField = addTaskForm.find('[name="sendTimeHours"]');
        var sendTimeMinutesField = addTaskForm.find('[name="sendTimeMinutes"]');
        var titleEl = addTaskModal.find('.modal-title');
        var primaryBtnEl = addTaskModal.find('.btn-primary');
        var url = '/clients/{0}/treatments/{1}/tasks';

        var addEditTaskEditor = {
            modal: 'ADD',
            editingRow: 0,

            setEditModal: function (index, task) {
                this.modal = 'EDIT';
                this.editingRow = index;

                addTaskForm[0].reset();

                primaryBtnEl.data({
                    loadingText: 'Updating...'
                });

                titleEl.text('Edit Item');
                primaryBtnEl.text('Update');
                addTaskForm.attr('action', url.format(page.clientId, page.treatmentId) + '/' + task.id);
            },

            setCreateModal: function () {
                this.modal = 'ADD';

                addTaskForm[0].reset();

                primaryBtnEl.data({
                    loadingText: 'Loading...'
                });


                titleEl.text('Add Item');
                primaryBtnEl.text('Create');
                addTaskForm.attr('action', url.format(page.clientId, page.treatmentId));
            },

            setValue: function (task) {
                toolIdField.val(task.toolId);
                sendTimeDirectionField.val(task.sendTimeDirection);
                sendTimeWeeksField.val(task.sendTimeWeeks);
                sendTimeDaysField.val(task.sendTimeDays);
                sendTimeHoursField.val(task.sendTimeHours);
                sendTimeMinutesField.val(task.sendTimeMinutes);
            },

            show: function () {
                addTaskModal.modal('show');
            }
        };

        $('#add-item-btn').click(function () {
            addEditTaskEditor.setCreateModal();
        });

        page.taskList.editor = addEditTaskEditor;
    }

    function initDeleteTaskDialogForm() {
        var deleteItemModal = $('#delete-item-modal');
        var deleteBtn = deleteItemModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);
            var taskId = deleteItemModal.find('.id').text();
            var rowIndex = deleteItemModal.find('.row-index').val();

            button.button('loading');

            $.ajax({
                url: '/clients/{0}/treatments/{1}/tasks/{2}'.format(page.clientId, page.treatmentId, taskId),
                type: 'DELETE'
            }).done(function () {
                page.taskList.deleteRow(rowIndex);

                deleteItemModal.modal('hide');
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

        // Init add defined tool dialog form
        initAddDefinedToolDialogForm();

        initDeleteDefinedToolDialogForm();

        // Init add task dialog form
        initAddTaskDialogForm();

        initDeleteTaskDialogForm();
    }


    // Start processing
    init();
})
(jQuery);
