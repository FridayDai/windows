function withServerError() {
    /* jshint validthis:true */

    this.attributes({
        serverErrorSelector: '.rc-server-error'
    });

    this.serverErrorHandler = function (reqObj) {
        if (reqObj.status === 403) {
            alert('Permission denied! Please try to refresh page!');
        } else {
            this.showServerError(reqObj.responseJSON.error.errorMessage);
        }
    };

    this.showServerError = function (msg) {
        this.select('serverErrorSelector').show().text(msg);
    };
}

module.exports = withServerError;
