//= require ../../bower_components/lodash/lodash

(function ($, undefined) {
    'use strict';
    var models = RC.models = RC.models || {};

    // This is Tool class
    // data should be one object as following:
    //
    function Task(data) {
        _.extend(this, data);
    }

    models.Task = Task;
})(jQuery);
