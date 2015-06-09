'use strict';

function withPanel() {
    /* jshint validthis:true */

    this.generateGetterSetter = function (fields, target) {
        if (!_.isArray(fields)) {
            fields = [fields];
        }

        _.each(fields, function (field) {
            var capitalized = _.capitalize(field);
            var selectorStr = field + 'Selector';
            var getterKey = 'get' + capitalized;
            var setterKey = 'set' + capitalized;

            target[getterKey] = function () {
                return target.select(selectorStr).text();
            };

            target[setterKey] = function (data) {
                target.select(selectorStr).text(data);
            };
        });
    };
}

module.exports = withPanel;
