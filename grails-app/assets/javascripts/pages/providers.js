(function ($, undefined) {
    'use strict';
    //var provider = RC.pages.provider = RC.pages.provider || {};
    var provideT;
    var ajaxUrl = RC.constants.baseUrl + "/getProvider";
    var provideData;

    function _init() {
        $("#table-form").validate({
                messages: {
                    provider: RC.constants.waringMessageProvider,
                    agent: RC.constants.waringMessageAgent,
                    email: RC.constants.waringMessageEmail

                }
            }
        );

        loadData();
    }

    _init();

    // new a record
    $("#add-provider").bind("click", function (e) {
        e.preventDefault();
        $(".form")[0].reset();


        var confirmFormArguments = {
            element: ".form",
            title: RC.constants.confirmTitle,
            content: RC.constants.confirmContent,
            okCallback: function () {
                if ($("#table-form").valid()) {
                    _addRow();
                    return true;
                }
                return false;
            },
            cancelCallback: function () {
            },
            height: 200,
            width: 400
        };

        RC.common.confirmForm(confirmFormArguments);
    });


// Edit record
    $('#provideTable').on('click', 'a.editor_edit', function (e) {
        e.preventDefault();

        var dataId = $(this).data('id').toString();
        var rowData = _.findWhere(provideData, {id: dataId});
        $("#provider").val(rowData.name);
        $("#agent").val(rowData.agent);
        $("#email").val(rowData.email);

        var thisRow = provideT.row($(this).closest('tr'));

        var confirmFormArguments = {
            element: ".form",
            title: RC.constants.confirmTitle,
            content: RC.constants.confirmContent,
            okCallback: function () {
                _editRow(thisRow);
                return true;
            },
            cancelCallback: function () {
            },
            height: 200,
            width: 400
        };
        RC.common.confirmForm(confirmFormArguments);

    });

// Delete a record
    $('#provideTable').on('click', 'a.editor_remove', function (e) {
        e.preventDefault();

        var dataId = $(this).data('id').toString();
        var tr = provideT.row($(this).closest('tr'));

        var warningArguments = {
            element: ".warn",
            title: RC.constants.warningTipTitle,
            message: RC.constants.warningTip,
            closeCallback: function () {
                _deleteRow(dataId, tr);
            },
            cancelCallback: function () {

            }
        };
        RC.common.warning(warningArguments);

    });

//load Data from server side
    function loadData() {
        $.ajax({
            dataType: 'json',
            url: ajaxUrl
        })
            .done(function (data) {
                provideData = data;
                _initTable(provideData);

            })
            .fail(function () {
            });
    }

//init table with the data which loaded
    function _initTable(data) {

        provideT = $("#provideTable").DataTable({
            paging: false,
            searching: false,
            ordering: false,
            info: false,
            data: data,
            columns: [
                {data: "image"},
                {
                    data: function (source) {
                        return '<label class="tr-label"> ' + source.name + '</label>';
                    }
                },
                {data: "agent"},
                {data: "email"},
                {
                    data: function (source) {
                        return '<a  href="" data-id ="' + source.id + '" class="editor_edit">Edit</a>'
                            + '&nbsp;&nbsp;<a href="" data-id ="' + source.id + '" class="editor_remove">Remove</a>';
                    },
                    className: "center"
                }
            ]
        });

    }

//new a row
    function _addRow() {
        var name = $("#provider").val();
        var agent = $("#agent").val();
        var email = $("#email").val();
        var id = Math.floor((Math.random() * 1000) + 1).toString();

        provideT.row.add({
            "image": name,
            "name": name,
            "agent": agent,
            "email": email,
            "id": id
        }).draw();

        provideData.push({
            "image": name,
            "name": name,
            "agent": agent,
            "email": email,
            "id": id
        });

    }

//edit a row
    function _editRow(thisRow) {
        var d = thisRow.data();
        var name = $("#provider").val();
        var agent = $("#agent").val();
        var email = $("#email").val();
        d.name = name;
        d.agent = agent;
        d.email = email;
        provideT.row(thisRow).data(d).draw();

    }

//delete a row
    function _deleteRow(dataId, tr) {
        var rowData = _.findWhere(provideData, {id: dataId});
        provideData = _.without(provideData, rowData);
        tr.remove().draw();
    }

})
(jQuery);
