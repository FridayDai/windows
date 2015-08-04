'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');

var login = require('../component/login/login');

login.attachTo('.login-form');
