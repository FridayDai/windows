var flight = require('flight');
var withPrimitiveForm = require('../common/withPrimitiveForm');

function passwordForget() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: ".btn-forget-password",
        loadingState: 'Resetting Password'
    });
}

module.exports = flight.component(withPrimitiveForm, passwordForget);
