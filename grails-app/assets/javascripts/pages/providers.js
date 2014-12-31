;
(function ($, undefined) {
    'use strict';
    //var provider = RC.pages.provider = RC.pages.provider || {};
    $("#add-provider").bind("click",
        function (e) {
            e.preventDefault();
            var confirmFormArguments = {
                element: ".form",
                title: RC.constants.confirmTitle,
                content: RC.constants.confirmContent,
                okCallback: function () {
                },
                cancelCallback: function () {
                },
                height: 200,
                width: 400
            };
            RC.common.confirmForm(confirmFormArguments);

            //var warningArguments = {
            //    title: RC.constants.warningTipTitle,
            //    message: RC.constants.warningTip,
            //    closeCallback: function () {
            //    }
            //};
            //RC.common.warning(warningArguments);
        });
    ////new record
    //$('#add-provider').on('click', function (e) {
    //    e.preventDefault();
    //
    //    editor
    //        .title('Create new record')
    //        .buttons({
    //            "label": "Add",
    //            "fn": function () {
    //                editor.submit();
    //            }
    //        })
    //        .create();
    //});
    //
    //// Edit record
    //$('#provideTable').on('click', 'a.editor_edit', function (e) {
    //    e.preventDefault();
    //
    //    editor
    //        .title('Edit record')
    //        .buttons({
    //            "label": "Update", "fn": function () {
    //                editor.submit();
    //            }
    //        })
    //        .edit($(this).closest('tr'));
    //});
    //
    //// Delete a record
    //$('#provideTable').on('click', 'a.editor_remove', function (e) {
    //    e.preventDefault();
    //
    //    editor
    //        .message('Are you sure you wish to remove this record?')
    //        .buttons({
    //            "label": "Delete", "fn": function () {
    //                editor.submit();
    //            }
    //        })
    //        .remove($(this).closest('tr'));
    //});



  var loadData = function(){
      $.ajax({
          dataType: 'json',
          url: ajaxUrl
      })
          .done(function (data) {

              alert(data);

              _initTable(data);

          })
          .fail(function () {
              alert("failed");
          });
  };
    loadData();

    function _initTable(data){

        $("#provideTable").dataTable({
            paging: false,
            searching: false,
            ordering: false,
            info: false,
            data: data,
            //ajax: {
            //    url: ajaxUrl,
            //    "dataSrc": providerData
            //},
            //ajax: providerData,
            columns: [
                {"data": "image"},
                //{ data: null, render: function ( data, type, row ) {
                //    // Combine the first and last names into a single table field
                //    return data.first_name+' '+data.last_name;
                //} },
                {"data": "name"},
                {"data": "agent"},
                {"data": "email"},
                {
                    data: null,
                    className: "center",
                    defaultContent: '<a href="" class="editor_edit">Edit</a>  <a href="" class="editor_remove">Remove</a>'
                }
            ]
        });


    }


})(jQuery);
