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

    var $signsdata = $("#signsdata");
    var $signdatabody = $("#signdata-body");
    var signsDataUrl = $signsdata.attr("data-url");
    $.post(signsDataUrl, {}, function(data) {
        $signdatabody.empty();
        $signdatabody.append(data);
        $signdatabody.find(".tool-tip").tooltip();
        var $sparkline = $signdatabody.find('.sparkline');

        $sparkline.each(function () {
            var $data = $(this).data();
            ($data.type == 'pie') && $data.sliceColors && ($data.sliceColors = eval($data.sliceColors));
            ($data.type == 'bar') && $data.stackedBarColor && ($data.stackedBarColor = eval($data.stackedBarColor));
            $data.valueSpots = {'0:': $data.spotColor};
            $(this).sparkline('html', $data);
        });
    });

    var $ecg = $('#ecg');
    if($ecg.length > 0){
        var ecg_data_url = $ecg.attr('data-url');
        $.get(ecg_data_url, {} , function(rst){
            var $ecg_body = $ecg.find('.panel-body');
            $ecg_body.empty();
            $ecg_body.append(rst);

            var w = document.body.clientWidth || 800;
            var h = document.body.clientHeight || 500;
            var $image = $('#ecg_image');
            var content = "<img width='"+ (w - w/5) +"' height='"+ (h - h/5) +"' src='"+ $image.attr('href') +"' />";
            $image.click(function(){
                TINY.box.show(content,0,0,0,1);
                return false;
            });
        });
    }

    return {};
});
