require('jValidate');
require('flight');

function passwordReset() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: ".btn-reset",
        loadingState: 'Resetting password'
    });
}

module.exports = passwordReset;
