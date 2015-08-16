/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {
    require('plugins/charts/sparkline/jquery.sparkline');
    require('plugins/calendar/bootstrap_calendar');
    require('plugins/tinybox/tinybox');

    var theMonths = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];
    var theDays = ["日", "一", "二", "三", "四", "五", "六"];
    var $calendar = $('#calendar');
    var ajaxUrl = $calendar.attr('data-ajax-url');
    $calendar.calendar({
        months: theMonths,
        days: theDays,
        req_ajax: {
            type: 'get',
            url: ajaxUrl
        },
        popover_options: {
            placement: 'top',
            html: true
        }
    });

    var $image = $('#atds_image');
    if ($image) {
        var w = document.body.clientWidth || 800;
        var h = document.body.clientHeight || 500;
        var content = "<img width='" + (w - w / 5) + "' height='" + (h - h / 5) + "' src='" + $image.attr('href') + "' />";
        $image.click(function () {
            TINY.box.show(content, 0, 0, 0, 1);
            return false;
        });
    }

    return {};
});
