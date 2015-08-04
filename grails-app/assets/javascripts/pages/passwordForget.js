require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var passwordForget = require('../components/passwordForget/passwordForget');

passwordForget.attachTo('.forget-password-form');
