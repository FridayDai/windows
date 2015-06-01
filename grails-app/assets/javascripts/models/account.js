//= require ../../bower_components/underscore/underscore

(function ($, undefined) {
    'use strict';
    var models = RC.models = RC.models || {};

    function Account(data) {
        _.extend(this, data);
    }

    models.Account = Account;
})(jQuery);
