require('jValidate');
require('flight');

function passwordForget() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: ".btn-forget-password",
        loadingState: 'Resetting Password'
    });
}

module.exports = passwordForget;
