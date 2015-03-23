//= require ../../bower_components/underscore/underscore

(function ($, undefined) {
    'use strict';
    var models = RC.models = RC.models || {};

    function Announcement(data) {
        _.extend(this, data);
    }

    models.Announcement = Announcement;
})(jQuery);
