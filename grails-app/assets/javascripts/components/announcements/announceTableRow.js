var flight = require('flight');

function announceTableRow() {
    /* jshint validthis:true */

    this.attributes({
        announceDeleteBtn: 'td .remove-btn'
    });


    this.onShowDeleteAnnounceDialog = function () {
        this.trigger('showDeleteAnnounceFormDialog', {
            announceId: this.select('announceDeleteBtn').data('announceId'),
            $ele: this.select('announceDeleteBtn').closest("tr")
        });
    };

    this.onClear = function (event, data) {
        this.$node.$ele = data;
        this.$node.$ele.remove();
    };


    this.after('initialize', function () {
        this.on(document, 'deleteAnnouncementSuccess', this.onClear);
        this.on('click', {
            'announceDeleteBtn': _.bind(this.onShowDeleteAnnounceDialog, this)
        });
    });
}

module.exports = flight.component(announceTableRow);
