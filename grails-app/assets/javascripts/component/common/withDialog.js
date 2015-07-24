'use strict';

function withDialog() {
    /* jshint validthis:true */

    this.hideDialog = function () {
        this.$node.modal('hide');
    };

    this.after('initialize', function () {
        if (_.isFunction(this.onHideDialog)) {
            this.on('hidden.bs.modal', this.onHideDialog);
        }
    });
}

module.exports = withDialog;
