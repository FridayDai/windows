var flight = require('flight');
var withPrimitiveForm = require('../common/withPrimitiveForm');

function accountActivate() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: "#joinRat",
        codeSelector: "input[name=code]",
        originSelector: "#newPassword",
        confirmSelector: "#confirmPassword",
        errorMsgSelector: ".error-area",
        errorMsg: "Your passwords don't match. Please try again."

    });
}

module.exports = flight.component(withPrimitiveForm, accountActivate);
