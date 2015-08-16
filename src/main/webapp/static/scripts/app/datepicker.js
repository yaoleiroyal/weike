define(['plugins/datepicker/bootstrap-datepicker.zh-CN'], function () {
    var datePicker = function (selector, opts) {

        var $datepicker = $(selector);
        var options = $.extend({}, opts, {
            autoclose: true,
            language: 'zh-CN'
        });

        if ($datepicker && $datepicker.length > 0) {
            $datepicker.each(function () {
                $(this).datepicker(options);
            });
        }
    }
    return {
        datePicker: datePicker
    };
});