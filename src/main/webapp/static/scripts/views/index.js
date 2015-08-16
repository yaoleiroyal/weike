/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {

    require('plugins/calendar/bootstrap_calendar');

    require(['echarts', 'plugins/echarts/theme/green', 'echarts/chart/bar', 'echarts/chart/line', 'echarts/chart/map'],
        function (ec, green_t) {
        var sign_chart = ec.init(document.getElementById('sign_chart'), green_t);
        sign_chart.setOption({
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: ['血压', '血糖']
            },
            toolbox: {
                show: true,
                feature: {
                    saveAsImage: {show: true}
                }
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    splitArea: {show: true}
                }
            ],
            series: [
                {
                    name: '血压',
                    type: 'line',
                    data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                },
                {
                    name: '血糖',
                    type: 'line',
                    data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                }
            ]
        });
    });

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
});