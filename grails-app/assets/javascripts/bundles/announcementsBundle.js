//= require share/baseBundle
//= require ../../bower_components/underscore/underscore
//= require share/formModalBundle
//= require ../bower_components/DataTables/media/js/jquery.dataTables
//= require ../bower_components/jquery-form/jquery.form
//= require models/announcement

(function ($, undefined) {
    'use strict';

    // Init page object
    var page = {};

    var ANNOUNCEMENT_COLOR = {
        "#fdddde": "Red"
    };

    var ANNOUNCEMENT_COLOR_REVERSE = {
        "Red": "#fdddde"
    };

    var ANNOUNCEMENT_STATUS = {
        "2": "Inactive",
        "1": "Active"
    };

    var ANNOUNCEMENT_STATUS_REVERSE = {
        "Inactive": "2",
        "Active": "1"
    };

    function initAnnouncementsList() {
        page.annoucementsList = {
            // Editor for this table
            editor: null,

            // DataTable instance for this table
            table: null,

            // Initialize table
            init: function () {
                var list = this;

                this.table = $('#announcements-table').DataTable({
                    autoWidth: false,
                    searching: false,
                    lengthChange: false,
                    serverSide: true,
                    pageLength: $('#announcements-table').data("pagesize"),
                    fnDrawCallback: function () {
                        $(this.parents()[1]).show();
                    },
                    ajax: {
                        url: '/getAnnouncements'
                    },
                    deferLoading: $('#announcements-table').data("total"),
                    order: [[0, 'desc']],
                    columns: [
                        {title: 'ID', data: 'id', width: '5%'},
                        {title: 'Announcement', data: 'content', width: '55%'},
                        {
                            title: 'Status',
                            data: 'status',
                            width: '15%',
                            render: function (data) {
                                return ANNOUNCEMENT_STATUS[data];
                            }
                        },
                        {
                            title: 'Background',
                            data: 'colorHex',
                            width: '15%',
                            render: function (data) {
                                return ANNOUNCEMENT_COLOR[data];
                            }
                        },
                        {
                            title: '',
                            data: function () {
                                return [
                                    '<span class="edit-btn glyphicon glyphicon-edit" ',
                                    'aria-hidden="true"></span>',
                                    '&nbsp;',
                                    '<span class="delete-btn glyphicon glyphicon-trash" ',
                                    'aria-hidden="true"></span>'
                                ].join('');
                            },
                            width: '20px'
                        }
                    ],
                    rowCallback: function (row, data) {
                        var edithBtnEl =  $(row).find('.edit-btn');
                        var deleteBtnEl = $(row).find('.delete-btn');

                        if (ANNOUNCEMENT_STATUS[data.status] === 'Active') {
                            edithBtnEl
                                .click(function () {
                                    var index = list.table.row(row).index();

                                    list.editRow(index, list.getRowData(row));
                                });

                            deleteBtnEl
                                .click(function () {
                                    var index = list.table.row(row).index();

                                    list.showDeleteModal(index, list.getRowData(row));
                                });
                        } else {
                            edithBtnEl.hide();
                            deleteBtnEl.hide();
                        }
                    }
                });
            },

            // Add one new row
            addRow: function (announcement) {
                this.table.row.add(announcement).draw();
            },

            // Edit one row
            editRow: function (index, announcement) {
                this.editor.setEditModal(index, announcement);
                this.editor.setValue(announcement);
                this.editor.show();
            },

            showDeleteModal: function (index, announcement) {
                var modal = $('#announcement-delete-modal');

                modal.find('.row-index').val(index);
                modal.find('.announcement-id').text(announcement.id);
                modal.find('.announcement-content').text(announcement.content);
                modal.find('.announcement-background').text(ANNOUNCEMENT_COLOR[announcement.colorHex]);
                modal.find('.announcement-status').text(ANNOUNCEMENT_STATUS[announcement.status]);

                modal.modal('show');
            },

            deleteRow: function (index) {
                this.table.row(index).remove().draw();
            },

            getRowData: function (rowEl) {
                return this.table.row(rowEl).data();
            },

            reload: function () {
                this.table.ajax.reload();
            }
        };

        page.annoucementsList.init();
    }

    function initAnnouncementDialogForm() {
        var modal = $('#announcement-add-modal');
        var form = modal.find('form');
        var createBtn = modal.find('.create-btn');

        RC.utility.formModal.defaultConfig('#announcement-add-modal');

        // Setup create button
        createBtn.click(function () {
            var button = $(this);

            if (form.valid()) {
                button.button('loading');

                form.ajaxSubmit({
                    success: function () {
                        modal.modal('hide');

                        button.button('reset');

                        page.annoucementsList.reload();
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

        var idField = form.find('[name="id"]');
        var contentField = form.find('[name="content"]');
        var colorHexField = form.find('[name="colorHex"]');
        var statusField = form.find('[name="status"]');
        var titleEl = modal.find('.modal-title');
        var primaryBtnEl = modal.find('.btn-primary');
        var url = '/announcements';

        var addEditAnnouncementEditor = {
            modal: 'ADD',
            editingRow: 0,

            setEditModal: function (index, announcement) {
                this.modal = 'EDIT';
                this.editingRow = index;

                form[0].reset();

                primaryBtnEl.data({
                    loadingText: 'Updating...'
                });

                titleEl.text('Edit Tool');
                primaryBtnEl.text('Update');
                form.attr('action', url + '/' + announcement.id);
            },

            setCreateModal: function () {
                this.modal = 'ADD';

                form[0].reset();

                primaryBtnEl.data({
                    loadingText: 'Loading...'
                });

                titleEl.text('Add Tool');
                primaryBtnEl.text('Create');
                form.attr('action', url);
                statusField.val('1');
                idField.val('');
            },

            setValue: function (announcement) {
                contentField.val(announcement.content);
                colorHexField.val(announcement.colorHex);
                statusField.val(announcement.status);
                idField.val(announcement.id);
            },

            show: function () {
                modal.modal('show');
            }
        };

        $('#add-announcement-btn').click(function () {
            addEditAnnouncementEditor.setCreateModal();
        });

        page.annoucementsList.editor = addEditAnnouncementEditor;
    }

    function initDeleteAnnouncementDialogForm() {
        var deleteModal = $('#announcement-delete-modal');
        var deleteBtn = deleteModal.find('.delete-btn');

        deleteBtn.click(function () {
            var button = $(this);
            var announcementId = deleteModal.find('.announcement-id').text();
            var contentEl = deleteModal.find('.announcement-content');
            var backgroundEl = deleteModal.find('.announcement-background');

            button.button('loading');

            var data = {
                "id": announcementId,
                "content": contentEl.text(),
                "colorHex": ANNOUNCEMENT_COLOR_REVERSE[backgroundEl.text()],
                "status": ANNOUNCEMENT_STATUS_REVERSE.Inactive
            };

            $.ajax({
                url: '/announcements/' + announcementId,
                data: data,
                type: 'POST'
            })
                .done(function () {
                    deleteModal.find('.announcement-id').empty();
                    deleteModal.find('.announcement-content').empty();
                    deleteModal.find('.announcement-background').empty();
                    deleteModal.find('.announcement-status').empty();

                    deleteModal.modal('hide');

                    page.annoucementsList.reload();
                })
                .fail(function (jqXHR) {
                    var serverErrorEl = deleteModal.find('.rc-server-error');

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
        initAnnouncementsList();
        initAnnouncementDialogForm();
        initDeleteAnnouncementDialogForm();
    }

    init();

})(jQuery);
