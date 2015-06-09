//= require share/baseBundle
//= require ../../bower_components/lodash/lodash
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require ../bower_components/moment/moment
//= require ../bower_components/moment-timezone/builds/moment-timezone-with-data
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
                    order: [[0, 'desc']],
                    ajax: {
                        url: '/clients/{0}/treatments/{1}/tools'
                            .format(page.clientId, page.treatmentId),
                        dataSrc: function (response) {
                            return _.map(response.items, function (obj) {
                                return new RC.models.Tool(obj);
                            });
                        },
                        error: function (jqXHR) {
                            if (jqXHR.status === 403) {
                                alert('Permission denied! Please try to refresh page!');
                            }
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
                                return moment(row.lastUpdated).tz("America/Vancouver").format('MMM DD, YYYY  h:mm A');
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
                            var duration = RC.utility.getTimeInterval(data.defaultDueTime);

                            _.extend(data, {
                                defaultDueTimeDay: duration.totalDays,
                                defaultDueTimeHour: duration.hours
                            });
                        }

                        $(row)
                            .find('.edit-btn')
                            .click(function () {
                                var index = list.table.row(row).index();

                                list.editRow(index, list.getRowData(row));
                            });

                        $(row)
                            .find('.delete-btn')
                            .click(function () {
                                var index = list.table.row(row).index();

                                list.showDeleteModal(index, list.getRowData(row));
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
            getRowData: function (rowEl) {
                return this.table.row(rowEl).data();
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
                    order: [[0, 'desc']],
                    ajax: {
                        url: '/clients/{0}/treatments/{1}/tasks'
                            .format(page.clientId, page.treatmentId),
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
                                var sendTime = row.sendTimeOffset,
                                    timeStr = '',
                                    direction = 0,
                                    duration = {};

                                if (row.immediate) {
                                    timeStr = 'Immediate';
                                } else if (sendTime === 0) {
                                    direction = 1;
                                    timeStr = 'At Surgery';
                                } else {
                                    duration = RC.utility.getTimeInterval(sendTime);

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
                                        direction = 1;
                                        timeStr += ' After Surgery';
                                    }
                                }

                                _.extend(row, {
                                    sendTimeDirection: 0,
                                    sendTimeWeeks: 0,
                                    sendTimeDays: 0,
                                    sendTimeHours: 0,
                                    sendTimeMinutes: 0
                                }, {
                                    sendTimeDirection: direction,
                                    sendTimeWeeks: duration.weeks,
                                    sendTimeDays: duration.days,
                                    sendTimeHours: duration.hours,
                                    sendTimeMinutes: duration.minutes,
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
                                    duration = RC.utility.getTimeInterval(dueTime);

                                    timeStr = '{0}D {1}H'.format(
                                        duration.totalDays,
                                        duration.hours
                                    );
                                }

                                return timeStr;
                            }
                        },
                        {
                            title: 'Last Updated',
                            data: function (row) {
                                return moment(row.lastUpdated).tz("America/Vancouver").format('MMM DD, YYYY  h:mm A');
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
                                var index = list.table.row(row).index();

                                list.editRow(index, list.getRowData(row));
                            });

                        $(row)
                            .find('.delete-btn')
                            .click(function () {
                                var index = list.table.row(row).index();

                                list.showDeleteModal(index, list.getRowData(row));
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
            getRowData: function (rowEl) {
                return this.table.row(rowEl).data();
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
                        var serverErrorEl = editTreatmentModal.find('.rc-server-error');

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

    function initCloseTreatmentDialogForm() {
        var closeTreatmentModal = $('#close-treatment-modal');
        var deleteBtn = closeTreatmentModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);

            button.button('loading');

            $.ajax({
                url: '/clients/{0}/treatments/{1}'.format(page.clientId, page.treatmentId),
                type: 'DELETE'
            })
                .done(function () {
                    $('.main-info .status .text').text('Closed');

                    closeTreatmentModal.modal('hide');
                })
                .fail(function (jqXHR) {
                    var serverErrorEl = closeTreatmentModal.find('.rc-server-error');

                    if (jqXHR.status === 403) {
                        alert('Permission denied! Please try to refresh page!');
                    } else {
                        serverErrorEl.text(jqXHR.responseJSON.error.errorMessage);
                        serverErrorEl.show();
                    }
                })
                .always(function () {
                    button.button('reset');
                });
        });
    }

    function initAddDefinedToolDialogForm() {
        var addDefinedToolModal = $('#add-defined-tool-modal');
        var addDefinedToolForm = addDefinedToolModal.find('form');
        var createBtn = addDefinedToolModal.find('.create-btn');

        addDefinedToolModal.find('[name="defaultDueTimeDay"]').on('change', function() {
            $('#defined-tool-reminder').valid();
        });

        setAddDefinedToolFormValidator(addDefinedToolForm);

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
                            page.taskList.editor.addToolToList(new RC.models.Tool(res));
                        } else {
                            page.toolList.updateRow(addEditToolEditor.editingRow, new RC.models.Tool(res));
                            page.taskList.reload();
                        }

                        addDefinedToolModal.modal('hide');

                        button.button('reset');
                    },

                    error: function (jqXHR) {
                        var serverErrorEl = addDefinedToolModal.find('.rc-server-error');

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

        var idField = addDefinedToolForm.find('[name="id"]'),
         defaultDueTimeDayField = addDefinedToolForm.find('[name="defaultDueTimeDay"]'),
         defaultDueTimeHourField = addDefinedToolForm.find('[name="defaultDueTimeHour"]'),
         reminderField = addDefinedToolForm.find('[name="reminder"]'),
         titleEl = addDefinedToolModal.find('.modal-title'),
         primaryBtnEl = addDefinedToolModal.find('.btn-primary'),
         url = '/clients/{0}/treatments/{1}/tools';

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
                idField.val(tool.basetoolId);
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

    function setAddDefinedToolFormValidator(form) {
        $.validator.addMethod('reminderCheck', function (value) {
            var regexp = /^\s*\d+\s*((,\s*\d+\s*)*)$/;

            return regexp.test(value);
        }, "Invalid reminder format.");

        $.validator.addMethod('defaultDueTimeLessReminderCheck', function () {
            var regexp = /\d+/g;
            var dueDayVal = $('#add-defined-tool-modal').find('[name="defaultDueTimeDay"]').val();
            dueDayVal = parseInt(dueDayVal, 10);

            var reminderVal = $('#defined-tool-reminder').val();

            var reminders = _.map(reminderVal.match(regexp), function (val) {
                return parseInt(val, 10);
            });

            if (reminders) {
                return _.max(reminders) <= dueDayVal - 1;
            } else {
                return false;
            }

        }, "The day of max reminder should be less 1 day than default due time.");

        form.validate({
            rules: {
                reminder: {
                    reminderCheck: true,
                    defaultDueTimeLessReminderCheck: true
                }
            }
        });
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
            })
                .done(function () {
                    page.toolList.deleteRow(rowIndex);
                    page.taskList.reload();
                    page.taskList.editor.deleteToolFromList(toolId);

                    deleteToolModal.modal('hide');
                })
                .fail(function (jqXHR) {
                    var serverErrorEl = deleteToolModal.find('.rc-server-error');

                    if (jqXHR.status === 403) {
                        alert('Permission denied! Please try to refresh page!');
                    } else {
                        serverErrorEl.text(jqXHR.responseJSON.error.errorMessage);
                        serverErrorEl.show();
                    }
                })
                .always(function () {
                    button.button('reset');
                });
        });
    }

    function initAddTaskDialogForm() {
        var addTaskModal = $('#add-item-modal'),
        addTaskForm = addTaskModal.find('form'),
        createBtn = addTaskModal.find('.create-btn');

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
                        var serverErrorEl = addTaskModal.find('.rc-server-error');

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

        var toolIdField = addTaskForm.find('[name="toolId"]'),
         sendTimeDirectionField = addTaskForm.find('[name="sendTimeDirection"]'),
         sendTimeWeeksField = addTaskForm.find('[name="sendTimeWeeks"]'),
         sendTimeDaysField = addTaskForm.find('[name="sendTimeDays"]'),
         sendTimeHoursField = addTaskForm.find('[name="sendTimeHours"]'),
         sendTimeMinutesField = addTaskForm.find('[name="sendTimeMinutes"]'),
         titleEl = addTaskModal.find('.modal-title'),
         primaryBtnEl = addTaskModal.find('.btn-primary'),
         url = '/clients/{0}/treatments/{1}/tasks';

        sendTimeDirectionField
            .change(function () {
                var selected = $(this).find('option:selected');
                if (selected.val() === "0") {
                    sendTimeWeeksField.val(0).attr('disabled', 'disabled');
                    sendTimeDaysField.val(0).attr('disabled', 'disabled');
                    sendTimeHoursField.val(0).attr('disabled', 'disabled');
                    sendTimeMinutesField.val(0).attr('disabled', 'disabled');
                } else {
                    sendTimeWeeksField.removeAttr('disabled');
                    sendTimeDaysField.removeAttr('disabled');
                    sendTimeHoursField.removeAttr('disabled');
                    sendTimeMinutesField.removeAttr('disabled');
                }
            })
            .change();

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

                toolIdField.attr('disabled', 'disabled');
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

                toolIdField.removeAttr('disabled');
                titleEl.text('Add Item');
                primaryBtnEl.text('Create');
                addTaskForm.attr('action', url.format(page.clientId, page.treatmentId));
            },

            setValue: function (task) {
                toolIdField.val(task.toolId);
                sendTimeDirectionField.val(task.sendTimeDirection).change();
                sendTimeWeeksField.val(task.sendTimeWeeks);
                sendTimeDaysField.val(task.sendTimeDays);
                sendTimeHoursField.val(task.sendTimeHours);
                sendTimeMinutesField.val(task.sendTimeMinutes);
            },

            show: function () {
                addTaskModal.modal('show');
            },

            addToolToList: function (tool) {
                var listEl = $('#add-item-tool-id');

                listEl.append('<option value="{0}">{1}</option>'.format(tool.id, tool.title));
            },

            deleteToolFromList: function (toolId) {
                var listEl = $('#add-item-tool-id');

                listEl.find('[value="{0}"]'.format(toolId)).remove();
            }
        };

        $('#add-item-btn').click(function () {
            addEditTaskEditor.setCreateModal();
            sendTimeDirectionField.change();
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
            })
                .done(function () {
                    page.taskList.deleteRow(rowIndex);

                    deleteItemModal.modal('hide');
                })
                .fail(function (jqXHR) {
                    var serverErrorEl = deleteItemModal.find('.rc-server-error');

                    if (jqXHR.status === 403) {
                        alert('Permission denied! Please try to refresh page!');
                    } else {
                        serverErrorEl.text(jqXHR.responseJSON.error.errorMessage);
                        serverErrorEl.show();
                    }
                })
                .always(function () {
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
