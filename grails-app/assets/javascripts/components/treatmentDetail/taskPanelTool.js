var flight = require('flight');
var treatmentStorage = require('./treatmentStorage');

function toolPoolPanelTool() {
    this.attributes({
        addTaskBtnSelector: '#add-item-btn'
    });

    this.onAddTaskBtnClick = function () {
        this.trigger('showCreateTaskFormDialog', {
            clientId: treatmentStorage.get('clientId'),
            treatmentId: treatmentStorage.get('treatmentId')
        });
    };

    this.after('initialize', function () {
        this.on('click', {
            addTaskBtnSelector: this.onAddTaskBtnClick
        });
    });
}

module.exports = flight.component(toolPoolPanelTool);
