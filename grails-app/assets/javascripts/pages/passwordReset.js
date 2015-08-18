require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var passwordReset = require('../components/passwordReset/passwordReset');

passwordReset.attachTo('.reset-password-form');
