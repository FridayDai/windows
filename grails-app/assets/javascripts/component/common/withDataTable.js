'use strict';

require('dataTable');

function withDataTable() {
    /* jshint validthis:true */

    /* jshint ignore:start */
    $.fn.dataTable.pipeline = function (opts) {
        // Configuration options
        var conf = $.extend({
            pages: 5,     // number of pages to cache
            url: '',      // script url
            data: null,   // function or object with parameters to send to the server
                          // matching how `ajax.data` works in DataTables
            method: 'GET' // Ajax HTTP method
        }, opts);

        // Private variables for storing the cache
        var cacheLower = -1;
        var cacheUpper = null;
        var cacheLastRequest = null;
        var cacheLastJson = null;

        return function (request, drawCallback, settings) {
            var ajax = false,
                requestStart = request.start,
                drawStart = request.start,
                requestLength = request.length,
                requestEnd = requestStart + requestLength;

            if (settings.clearCache) {
                // API requested that the cache be cleared
                ajax = true;
                settings.clearCache = false;
            } else if (cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper) {
                // outside cached data - need to make a request
                ajax = true;
            } else if (settings.aoData.length < settings._iDisplayLength ||
                settings.aoData.length > settings._iDisplayLength
            ) {
                ajax = true;
            } else if (JSON.stringify(request.order) !== JSON.stringify(cacheLastRequest.order) ||
                JSON.stringify(request.columns) !== JSON.stringify(cacheLastRequest.columns) ||
                JSON.stringify(request.search) !== JSON.stringify(cacheLastRequest.search)
            ) {
                // properties changed (ordering, columns, searching)
                ajax = true;
            }

            // Store the request for checking next time around
            cacheLastRequest = $.extend(true, {}, request);

            if (ajax) {
                // Need data from the server
                if (requestStart < cacheLower) {
                    requestStart = requestStart - (requestLength * (conf.pages - 1));

                    if (requestStart < 0) {
                        requestStart = 0;
                    }
                }

                cacheLower = requestStart;
                cacheUpper = requestStart + (requestLength * conf.pages);

                request.start = requestStart;
                request.length = requestLength * conf.pages;

                // Provide the same `data` options as DataTables.
                if ($.isFunction(conf.data)) {
                    // As a function it is executed with the data object as an arg
                    // for manipulation. If an object is returned, it is used as the
                    // data object to submit
                    var d = conf.data(request);
                    if (d) {
                        $.extend(request, d);
                    }
                } else if ($.isPlainObject(conf.data)) {
                    // As an object, the data given extends the default
                    $.extend(request, conf.data);
                }

                settings.jqXHR = $.ajax({
                    "type": conf.method,
                    "url": conf.url,
                    "data": request,
                    "dataType": "json",
                    "cache": false,
                    "success": function (json) {
                        cacheLastJson = $.extend(true, {}, json);

                        if (cacheLower !== drawStart) {
                            json.data.splice(0, drawStart - cacheLower);
                        }
                        json.data.splice(requestLength, json.data.length);

                        drawCallback(json);
                    }
                });
            } else {
                var json;
                json = $.extend(true, {}, cacheLastJson);
                json.draw = request.draw; // Update the echo for each response
                json.data.splice(0, requestStart - cacheLower);
                json.data.splice(requestLength, json.data.length);

                drawCallback(json);
            }
        };
    };
    /* jshint ignore:end */

    // Register an API method that will empty the pipelined data, forcing an Ajax
    // fetch on the next draw (i.e. `table.clearPipeline().draw()`)
    $.fn.dataTable.Api.register('clearPipeline()', function () {
        return this.iterator('table', function (settings) {
            settings.clearCache = true;
        });
    });


    this.tableIns = null;

    this.attributes({
        pageSizeField: 'pagesize',
        totalCountField: 'total'
    });

    this.init = function () {
        var that = this;

        this.tableIns = $(this.$node).DataTable({
            autoWidth: false,
            searching: false,
            lengthChange: false,
            serverSide: true,
            pageLength: this.getPageSize(),
            fnDrawCallback: _.bind(that.drawCallback, that),
            ajax: that.getPipeline(),
            deferLoading: that.getTotalCount(),
            order: [[0, 'desc']],
            rowCallback: _.bind(that.selectRow, that),
            columns: that.attr.columns
        });
    };

    this.getPageSize = function () {
        return this.$node.data(this.attr.pageSizeField);
    };

    this.getTotalCount = function () {
        return this.$node.data(this.attr.totalCountField);
    };

    this.drawCallback = function () {
        this.$node.parent().show();
    };

    this.getPipeline = function () {
        var url;

        if (!(url = this.attr.url)) {
            url = this.getUrl();
        }

        return $.fn.dataTable.pipeline({
            url: url,
            pages: 2
        });
    };

    this.selectRow = function (rowEl) {
        var that = this;

        $(rowEl)
            .click(function () {
                var data = that.getRowData(rowEl);

                location.href = that.getRowClickFormatStr(data);
            });
    };

    this.getRowData = function (rowEl) {
        return this.tableIns.row(rowEl).data();
    };

    this.addRow = function (event, data) {
        this.tableIns.row.add(data).draw();
    };

    this.after('initialize', function () {
        this.init();
    });
}

module.exports = withDataTable;
