var flight = require('flight');

var ENTER_KEY = 13;

function clientsTool() {
    this.attributes({
        searchInputSelector: '#client-search-input',
        searchBtnSelector: '#client-search-btn'
    });

    this.getSearchVal = function () {
        return this.select('searchInputSelector').val();
    };

    this.onSearchInputKeyup = function (e) {
        if (e.which === ENTER_KEY) {
            this.triggerSearchWithClientName();
        }
    };

    this.onSearchBtnClick = function () {
        this.triggerSearchWithClientName();
    };

    this.triggerSearchWithClientName = function () {
        this.trigger('searchClientsWithClientName', {
            value: this.getSearchVal()
        });
    };

    this.after('initialize', function () {
        this.on('click', {
            searchBtnSelector: this.onSearchBtnClick
        });

        this.on('keyup', {
            searchInputSelector: this.onSearchInputKeyup
        })
    });
}

module.exports = flight.component(clientsTool);
