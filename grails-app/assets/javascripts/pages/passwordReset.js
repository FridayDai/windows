'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');

var passwordReset = require('../component/passwordReset/passwordReset');

passwordReset.attachTo('.reset-password-form');
