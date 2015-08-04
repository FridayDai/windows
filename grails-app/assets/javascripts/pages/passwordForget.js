require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var passwordForget = require('../component/passwordForget/passwordForget');

passwordForget.attachTo('.forget-password-form');
