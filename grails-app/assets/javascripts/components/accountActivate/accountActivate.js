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

    this.onSubmit = function () {
        var submitBtn = this.select('submitBtnSelector');
        var that = this;
        var code = this.select('codeSelector').val();
        var newPassword = this.select('newPasswordSelector').val();
        var confirmPassword = this.select('confirmPasswordSelector').val();
        var data = {
            code: code,
            newPassword: newPassword,
            confirmPassword: confirmPassword
        };
        if (newPassword !== confirmPassword) {
            this.select('errorMsgSelector').text(this.attr.passwordNotMatchMsg);
            return false;
        } else {
            $.ajax({
                url: that.attr.urls.confirmPassword,
                type: 'POST',
                data: data
            })
                .done(function () {
                    var url = that.attr.urls.login;
                    window.location.href = url;
                });
        }
    };

    this.after('initialize', function () {
        this.on('click', {
            'submitBtnSelector': this.onSubmit
        });
    });
}

module.exports = flight.component(withPrimitiveForm, accountActivate);
