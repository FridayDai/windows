//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require ../bower_components/moment/moment
//= require ../bower_components/moment-timezone/builds/moment-timezone-with-data
//= require models/treatment

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    // Initialize treatment list data table
    function initTreatmentList() {
        page.treatmentList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                var list = this;

                this.table = $('#treatment-table').DataTable({
                    searching: false,
                    order: [[ 0, 'desc' ]],
                    columns: [
                        {title: 'ID', data: 'id', width: '5%'},
                        {title: 'Treatment Title', data: 'title', width: '10%'},
                        {title: 'Template Title', data: 'tmpTitle', width: '10%'},
                        {title: 'Active', data: "activePatient", width: '5%'},
                        {title: 'Description', data: "description", width: '37%'},
                        {title: 'Status', data: "status", width: '8%'},
                        {
                            title: 'Last Updated',
                            data: 'lastUpdated',
                            width: '20%',
                            render: function (data) {
                                return moment(parseInt(data)).tz("America/Vancouver").format('MMM DD, YYYY  h:mm:ss A');
                            }
                        },
                        {
                            title: '',
                            data: function (row, type, set, meta) {
                                if (meta) {
                                    return '<span class="edit-btn glyphicon glyphicon-copy" ' +
                                        'aria-hidden="true" data-row="{0}"></span>'
                                            .format(meta.row);
                                }
                            },
                            width: '5%'
                        }
                    ],
                    rowCallback: function (row) {
                        var clientId = $('#client-info-panel .client-profile').data('id');

                        // Setup double click to entry specific client
                        $(row)
                            .click(function () {
                                var treatment = list.getRowData(this);

                                location.href = '/clients/{0}/treatments/{1}/{2}'.format(
                                    clientId,
                                    treatment.id,
                                    RC.utility.replaceSlashInTitle(treatment.title + '_' + treatment.tmpTitle)
                                );
                            });
                    }
                });
            },

            // Add one new row
            addRow: function (data) {
                this.table.row.add(data).draw();
            },

            // Edit one row
            editRow: function (data) {
                this.editor.setValue(data);
                this.editor.show();
            },

            // Get row data
            getRowData: function (rowEl) {
                return this.table.row(rowEl).data();
            }
        };

        page.treatmentList.init();
    }

    function initClientDialogForm() {
        var clientModal = $('#client-modal');
        var clientForm = clientModal.find('form');
        var updateBtn = clientModal.find('.update-btn');

        RC.utility.formModal.defaultConfig({
            selector: '#client-modal',
            fieldSelectorArray: [
                ['.client-profile .client-name', '#name'],
                ['.client-profile .sub-domain dd', '#subDomain'],
                ['.client-profile .portal-name dd', '#portalName'],
                ['.client-profile .primary-color dd', '#primaryColorHex']
            ]
        });

        clientModal.on('show.bs.modal', function () {
            RC.utility.formModal.getValFromFieldArray('#client-modal form');
        });

        updateBtn.click(function () {
            var button = $(this);

            if (clientForm.valid()) {
                button.button('loading');

                clientForm.ajaxSubmit({
                    success: function () {
                        RC.utility.formModal.setValFromFieldArray('#client-modal form');

                        clientModal.modal('hide');

                        button.button('reset');
                    },

                    error: function (jqXHR) {
                        var serverErrorEl = clientModal.find('.rc-server-error');

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

    function initAgentDialogForm() {
        var agentModal = $('#agent-modal');
        var agentForm = agentModal.find('form');
        var updateBtn = agentModal.find('.update-btn');

        RC.utility.formModal.defaultConfig({
            selector: '#agent-modal',
            fieldSelectorArray: [
                ['.agent .email dd', '#email'],
                ['.agent .first-name dd', '#firstName'],
                ['.agent .last-name dd', '#lastName']
            ]
        });

        var clientProfileEl = $('#client-info-panel .client-profile');
        var agentEl = $('#client-info-panel .agent');

        agentModal.on('show.bs.modal', function () {
            var clientId = clientProfileEl.data('id');
            var agentId = agentEl.data('agentId');

            if (!agentId) {
                agentModal.find('.modal-title').text('New Agent');
                agentModal.find('.update-btn').text('Create');

                agentForm.attr('action', '/clients/' + clientId + '/agents');
            } else {
                agentModal.find('modal-title').text('Edit Agent');
                agentModal.find('.update-btn').text('Update');

                agentForm.attr('action', '/clients/' + clientId + '/agents/' + agentId);

                RC.utility.formModal.getValFromFieldArray('#agent-modal form');
            }
        });

        updateBtn.click(function () {
            var button = $(this);
            var agentId = agentEl.data('agentId');

            if (agentForm.valid()) {
                if (agentId) {
                    button.button('loading');
                } else {
                    button.button('creating');
                }

                agentForm.ajaxSubmit({
                    success: function (resp) {
                        agentEl.data('agentId', resp.id);
                        RC.utility.formModal.setValFromFieldArray('#agent-modal form');

                        agentModal.modal('hide');

                        button.button('reset');
                    },

                    error: function (jqXHR) {
                        var serverErrorEl = agentModal.find('.rc-server-error');

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

    function initDeleteAgentDialogForm() {
        var agentDeleteModal = $('#agent-delete-modal');
        var deleteBtn = agentDeleteModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);
            var clientId = $('#client-info-panel .client-profile').data('id');
            var agentId = $('#client-info-panel .agent').data('agentId');

            button.button('loading');

            $.ajax({
                url: '/clients/' + clientId + '/agents/' + agentId,
                type: 'DELETE'
            })
                .done(function () {
                    $('.agent .email dd').empty();
                    $('.agent .first-name dd').empty();
                    $('.agent .last-name dd').empty();

                    agentDeleteModal.modal('hide');
                })
                .fail(function (jqXHR) {
                    var serverErrorEl = agentDeleteModal.find('.rc-server-error');

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

    function initTreatmentDialogForm() {
        var modal = $('#treatment-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#treatment-modal');

        // Setup create button
        createBtn.click(function () {
            var button = $(this);

            if (form.valid()) {
                button.button('loading');

                form.ajaxSubmit({
                    success: function (res) {
                        page.treatmentList.addRow(new RC.models.Treatment(res));

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
        // Init edit client dialog form
        initClientDialogForm();

        // Init edit agent dialog form
        initAgentDialogForm();

        // Init add treatment dialog form
        initTreatmentDialogForm();

        // Init delete agent dialog form
        initDeleteAgentDialogForm();


        // Init treatment list
        initTreatmentList();
    }


    // Start processing
    init();
})
(jQuery);
