'use strict';

function withDialog() {
    /* jshint validthis:true */

    this.hideDialog = function () {
        this.$node.modal('hide');
    };

    this.after('initialize', function () {

        if (this.attr.dialogCloseEvent) {
            this.on('hidden.bs.modal', this.attr.dialogCloseEvent);
        }

        if (this.attr.primaryButtonSelector) {
            this.on('click', {
                'primaryButtonSelector': this.primaryButtonClicked
            });
        }
    });
}

module.exports = withDialog;
