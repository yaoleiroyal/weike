/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {
    require('plugins/raty/jquery.raty');
    
    var $dataItems = $('#data_items');
    var $slimScroll = $('.slim-scroll');

    var item_tpl = Handlebars.compile($('#item_template').html());
    var end_tpl = Handlebars.compile($('#end_template').html());
    var evaluate_tpl = Handlebars.compile($('#evaluate_template').html());

    //处理滚动条
    $('.no-touch .slim-scroll').each(function(){
        var $self = $(this), $data = $self.data(), $slimResize;
        $self.slimScroll($data);
        $(window).resize(function(e) {
            clearTimeout($slimResize);
            $slimResize = setTimeout(function(){$self.slimScroll($data);}, 500);
        });
        $(document).on('updateNav', function(){
            $self.slimScroll($data);
        });
    });

    //加载最新数据
    function newest() {
        var condition = $('#condition').val();
        var $li = $dataItems.find('li:first');
        var currentId = 0;
        if ($li) {
            currentId = $li.attr('data-id');
        }
        $.post(g.ctx + 'life/care/newest', {currentId : currentId, condition : condition}, function(rets) {
            if (rets.status == 'OK') {
                var data = rets.data;
                if (data && data.length > 0 ) {
                    $dataItems.prepend(item_tpl(data));
                    //处理滚动条，加载最新，到最顶
                    $slimScroll.scrollTop(0);
                }
            }
        });
    }

    function end() {
        var $lis = $dataItems.find('li[data-end!="true"]');

        if ($lis && $lis.length > 0) {
            $($lis).each(function(i, o) {
                var id = $(o).attr('data-id');
                $.post(g.ctx + 'life/care/end', {id : id}, function(rets) {
                    if (rets.status == 'OK') {
                        var data = rets.data;
                        $(o).find('.m-t-sm').html(end_tpl(data));
                    }
                });
            });
        }
    }

    //正常加载
    function list() {
        var condition = $('#condition').val();
        var $li = $dataItems.find('li:last');
        var currentId = 0;
        if ($li) {
            currentId = $li.attr('data-id');
        }
        $.post(g.ctx + 'life/care/list', {currentId : currentId, condition : condition}, function(rets) {
            if (rets.status == 'OK') {
                var data = rets.data;
                if (data && data.length > 0 ) {
                    $dataItems.append(item_tpl(data));
                    //处理滚动条，正常加载，到最底部
                    var diH = $dataItems.css('height');
                    var ssH = $slimScroll.css('height');
                    if (diH > ssH) {
                        $slimScroll.scrollTop(parseInt(diH) - parseInt(ssH));
                    }
                }
            }
        });
    }
    list();
    $('#load_more').click(function() {
        list();
    });
    $('#condition').keyup(function() {
        $dataItems.empty();
        setTimeout(list, 50);
    });
    $dataItems.on('click', 'a[name="comment"]', function() {
        var id = $(this).attr('data-id');
        $('#dataId').val(id);
        $('#hint').val(3);
        $('#score').raty({
            path: g.ctx + 'static/scripts/plugins/raty/images',
            score: 3,
            target     : '#hint',
            targetType : 'score',
            targetKeep : true
        });
    });

    $('#evaluate_btn').click(function() {
        var id = $('#dataId').val();
        var score = $('#hint').val();
        var evaluate = $('#evaluate').val();
        if (id && score) {
            $.post(g.ctx + 'life/care/evaluate', {id : id, score : score, evaluate : evaluate}, function(rets) {
                if (rets.status == 'OK') {
                    var data = rets.data;
                    $('li[data-id="'+ id +'"]').find('a[name="comment"]').remove();
                    $('li[data-id="'+ id +'"]').find('div.m-t-sm').append(evaluate_tpl(data));
                }
                $('#modal-form').modal('hide');
            });
        }
    });

    setInterval(newest, 5000);
    setInterval(end, 5000);
});