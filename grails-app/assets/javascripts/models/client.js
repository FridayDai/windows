//= require ../../bower_components/underscore/underscore

(function ($, undefined) {
    'use strict';
    var models = RC.models = RC.models || {};

    // This is Client class
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
    function Client(data) {
        _.extend(this, data);
    }

    models.Client = Client;
})(jQuery);
