var flight = require('flight');
var treatmentStorage = require('./treatmentStorage');

function toolPoolPanelTool() {
    this.attributes({
        definedBtnSelector: '#add-defined-tool-btn'
    });

    this.onDefinedBtnClick = function () {
        this.trigger('showCreateDefinedToolFormDialog', {
            clientId: treatmentStorage.get('clientId'),
            treatmentId: treatmentStorage.get('treatmentId')
        });
    };

    this.after('initialize', function () {
        this.on('click', {
            definedBtnSelector: this.onDefinedBtnClick
        });
    });
}

module.exports = flight.component(toolPoolPanelTool);
