var flight = require('flight');
var withPrimitiveForm = require('../common/withPrimitiveForm');

function passwordReset() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: ".btn-reset",
        loadingState: 'Resetting password',
        originSelector: "#newPassword",
        confirmSelector: "#confirmPassword",
        errorMsgSelector: ".error-area",
        errorMsg: "Your passwords don't match. Please try again."
    });
}

module.exports = flight.component(withPrimitiveForm, passwordReset);
