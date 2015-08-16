/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

define(function (require, exports, module) {
    require('plugins/charts/easypiechart/jquery.easy-pie-chart');

    // easypie
    var easypie = function(){
        $('.easypiechart').each(function(){
            var $this = $(this),
                $data = $this.data(),
                $step = $this.find('.step'),
                $target_value = parseInt($($data.target).text()),
                $value = 0;
            $data.barColor || ( $data.barColor = function($percent) {
                $percent /= 100;
                return "rgb(" + Math.round(200 * $percent) + ", 200, " + Math.round(200 * (1 - $percent)) + ")";
            });
            $data.onStep =  function(value){
                $value = value;
                $step.text(parseInt(value));
                $data.target && $($data.target).text(parseInt(value) + $target_value);
            };
            $data.onStop =  function(){
                $target_value = parseInt($($data.target).text());
                $data.update && setTimeout(function() {
                    $this.data('easyPieChart').update(100 - $value);
                }, $data.update);
            };
            $(this).easyPieChart($data);
        });
    };
    easypie();


    return {};
});
