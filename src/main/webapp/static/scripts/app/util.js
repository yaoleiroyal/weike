define(function (require, exports, module) {

    var checkboxSelectAll = function() {
        $(document).on('change', 'table thead [type="checkbox"]', function(e){
            e && e.preventDefault();
            var $table = $(e.target).closest('table'), $checked = $(e.target).is(':checked');
            $('tbody [type="checkbox"]',$table).prop('checked', $checked);
        });
    }

    var chosenSelect = function() {
        var $chosen = $(".chosen-select");
        if ($chosen && $chosen.length > 0) {
            require('plugins/chosen/chosen.jquery.min');
            $chosen.chosen();
        }

    }

    return {
        checkboxSelectAll: checkboxSelectAll,
        chosenSelect: chosenSelect
    };
});