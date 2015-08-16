/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {
    $util = require('app/util');
    $util.chosenSelect();

    require('plugins/parsley/parsley.min');
    require('plugins/parsley/parsley.extend');

    $('#data-form').parsley();
});