//= require ../bower_components/DataTables/media/js/jquery.dataTables
(function ($, undefined) {
    'use strict';
    var RC = window.RC = window.RC || {};

    function getWindowSize() {
        var windowEI = $(window),
            documentEI = $(document);
        return {
            windowWidth: windowEI.width(),
            windowHeight: windowEI.height(),
            documentWidth: documentEI.width(),
            documentHeight: documentEI.height()
        };
    }

    // Set window and document width and height in RC namespace.
    $.extend(RC, getWindowSize());

    $(window).on('resize', function () {
        // Set window and document width and height in RC namespace when window resize.
        $.extend(RC, getWindowSize());
    });

    /**
     * global ajax set up
     */
    $.ajaxSetup({
        beforeSend: function () {

        },
        complete: function () {

        },
        success: function () {

        },
        global: true,
        error: function (jqXHR) {
            if (jqXHR.status === 404) {
            } else if (jqXHR.status === 403) {
                alert('Permission denied! Please try to refresh page!');
            } else if (jqXHR.status === 0) {
            }
            else {
            }
        }
    });


    /**
     * string format
     * **/
    String.prototype.format = function () {
        var str = this;
        if (arguments.length === 0) {
            return str;
        }

        for (var i = 0; i < arguments.length; i++) {
            var re = new RegExp('\\{' + i + '\\}', 'gm');
            if (arguments[i] !== undefined || arguments[i] !== null) {
                str = str.replace(re, arguments[i]);
            } else {
                str = str.replace(re, '');
            }
        }
        return str;
    };

    RC.pages = (function (self) {
        return self;
    }({}));

    // Add serializeObject function to jQuery. Fetching and serialize all form key and value as json format.
    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

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
            var ajax = false;
            var requestStart = request.start;
            var drawStart = request.start;
            var requestLength = request.length;
            var requestEnd = requestStart + requestLength;

            if (settings.clearCache) {
                // API requested that the cache be cleared
                ajax = true;
                settings.clearCache = false;
            }
            else if (cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper) {
                // outside cached data - need to make a request
                ajax = true;
            }
            else if (JSON.stringify(request.order) !== JSON.stringify(cacheLastRequest.order) ||
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
                }
                else if ($.isPlainObject(conf.data)) {
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
            }
            else {
                var json;
                json = $.extend(true, {}, cacheLastJson);
                json.draw = request.draw; // Update the echo for each response
                json.data.splice(0, requestStart - cacheLower);
                json.data.splice(requestLength, json.data.length);

                drawCallback(json);
            }
        };
    };

    // Register an API method that will empty the pipelined data, forcing an Ajax
    // fetch on the next draw (i.e. `table.clearPipeline().draw()`)
    $.fn.dataTable.Api.register('clearPipeline()', function () {
        return this.iterator('table', function (settings) {
            settings.clearCache = true;
        });
    });

})(jQuery);
