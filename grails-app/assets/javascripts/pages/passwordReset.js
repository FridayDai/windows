require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var passwordReset = require('../component/passwordReset/passwordReset');

passwordReset.attachTo('.reset-password-form');
