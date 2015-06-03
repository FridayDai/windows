//= require ../../bower_components/lodash/lodash

(function ($, undefined) {
    'use strict';
    var models = RC.models = RC.models || {};

    // This is Tool class
    // data should be one object as following:
    // {
    //      id: {number},
    //      logo: {string},
    //      name: {string},
    //      activeStaff: {number},
    //      activePatient: {number},
    //      activeTreatment: {number},
    //      primaryColor: {string}
    // }
    function Tool(data) {
        _.extend(this, data);
    }

    models.Tool = Tool;
})(jQuery);
