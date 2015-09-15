var flight = require('flight');
var withPrimitiveForm = require('../common/withPrimitiveForm');

function login() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: ".login-btn",
        loadingState: 'Signing in'
    });

}

module.exports = flight.component(withPrimitiveForm, login);
