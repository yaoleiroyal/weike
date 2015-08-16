/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {
    var $form = $('#serch-form');

    $('#pagination').find('a').click(function() {
        var n = $(this).attr('data-pagenumber');
        if(n) { //有页面
            $form.find('input[name="n"]').val(n);
            $form.submit();
        }
    });
});