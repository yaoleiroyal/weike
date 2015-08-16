/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require) {
    var $datepicker = require('app/datepicker');
    $datepicker.datePicker('.datepicker-input', {});

    require('plugins/parsley/parsley.min');
    require('plugins/parsley/parsley.extend');
    require('json2');

    var els = {
        vote_form : $('#vote_form')
    };

    var sex_hide = ["female", "male"];

    Handlebars.registerHelper("indexAdd", function(index) {
       return index+1;
    });

    var vote_hbs = require('text!modules/constitution/vote/index_vote.hbs');
    var items_hbs = require('text!modules/constitution/vote/question_items.hbs');
    var item_hbs = require('text!modules/constitution/vote/question_item.hbs');
    var danger_hbs = require('text!modules/constitution/vote/alert_danger.hbs');

    var vote_template = Handlebars.compile(vote_hbs);
    var items_template = Handlebars.compile(items_hbs);
    var item_template = Handlebars.compile(item_hbs);
    var danger_template = Handlebars.compile(danger_hbs);

    var url = els.vote_form.attr('data-url');

    var data;

    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        success: function(rets) {
            if(rets.status == 'OK') {
                data = rets.data;
                els.vote_form.html(vote_template(data));
            } else {
                els.vote_form.html(danger_template({message : rets.message}));
            }
        }
    });

    var question = {
        question_td: 'td[name="question_td"]',
        data_items: 'data-items',
        data_question: 'data-question',
        data_constitution: 'data-constitution',
        start : function() {
            var $td = $(this.question_td).eq(0);
            this.renderItems($td);
        },
        renderItems: function($td) {
            var questionId = $td.attr(this.data_question); //问题ID
            var constitutionId = $td.attr(this.data_constitution); //体质ID
            var items = $td.attr(this.data_items); //问题选项
            var json = $.parseJSON(items);
            json.question = questionId;
            json.constitution = constitutionId;
            $td.append(items_template(json));
        },
        itemSelect: function($this) {
            var text = $this.html();
            var val = $this.attr("data-val");
            var item = $this.attr("data-item");
            var $parent = $this.parent();

            var $input = $parent.find('input[name="question-item"]');
            $input.val(val);

            $input.attr("data-item", item);
            var $td = $parent.parent();
            var $qn = $td.find('div[name="question-name"]');

            var e = $qn.find('a[name="item-reload"]');
            var index = $('td[name="question_td"]').index($td);

            if (e && e.length > 0) {
                e.remove();
            } else {
                var scrollTop = $('.scrollable').scrollTop();
                $('.scrollable').scrollTop(scrollTop + 70);
                $next = $('td[name="question_td"]').eq(index + 1);
                if ($next && $next.length > 0) {
                    this.renderItems($next);
                }
            }
            $qn.append(item_template({text: text}));
            $parent.find('a[name="question-item"]').removeClass('btn-info');
            $this.addClass('btn-info');
            $parent.hide();
            if ($('td[name="question_td"]').length == index + 1) {
                $('#submit_btn').show();
            }
        },
        itemReload: function($this) {
            //$('#vote_form').find('div[name="question-items"]').hide();
            var $parent = $this.parent();
            var $td = $parent.parent();
            $td.find('div[name="question-items"]').show();
        },
        submit: function($this) {
            if (this.checkRequired()) {
                var url = $this.attr('data-url');
                // person
                var form_data = {
                    'vote': $('#vote').val(),
                    'person.full_name': $('input[id="person.full_name"]').val(),
                    'person.address' : $('input[id="person.address"]').val(),
                    'person.phone'   : $('input[id="person.phone"]').val(),
                    'person.gender'  : $('input[name="person.gender"]:checked').val(),
                    'person.id_card' : $('input[id="person.id_card"]').val(),
                    'person.birthday': $('input[id="person.birthday"]').val()
                };

                var data = {};
                data.vote = $('#vote').val();
                data.type = $('#type').val();
                var problems = [];
                var $questions = $('input[name="question-item"]', '#vote_form');
                $questions.each(function (idx, obj) {
                    var id = $(obj).attr('data-question');
                    var constitution = $(obj).attr('data-constitution');
                    var item = $(obj).attr('data-item');
                    var val = $(obj).val();

                    var q = {
                        id : id,
                        constitution : constitution,
                        item : item,
                        val : val
                    }
                    problems.push(q);
                });

                data.problems = problems;
                form_data['data'] = JSON.stringify(data);

                $.ajax({
                    url : g.ctx + url,
                    data: form_data,
                    type : 'POST',
                    beforeSend: function() {
                        $this.text('正在分析体质...');
                        $this.removeAttr("id");
                    },
                    success: function(rst) {
                        if (rst.status == 'OK') {
                            window.location.href = g.ctx + "health/person/constitution/" + rst.data;
                        } else {
                            $(this).html(rst.message);
                        }
                    },
                    complete: function() {
                        $this.attr("id", "submit_btn");
                    }
                });
            }
        },
        checkRequired: function() {
            var $data = $('[data-required="true"]');
            if ($data.length == 0) return true;
            var valid = false;
            $data.each(function () {
                var parsley = $(this).parsley('validate');
                if (!parsley) {
                    $(this).focus();
                }
                return (valid = parsley);
            });
            return valid;
        }
    }

    $('input[name="person.gender"]').change(function() {
        //重新生成问卷
        els.vote_form.html(vote_template(data));
        //隐藏不合法的选项
        var val = $(this).val();
        var required = sex_hide[val - 1];
        var $els = $('td[data-requiredtype="'+ required +'"]');
        $els.remove();
        //设置第一题可以选择
        question.start();
    });
    $('#vote_form').on('click', 'a[name="question-item"]', function() {
        var $this = $(this);
        question.itemSelect($this);
    });
    $('#vote_form').on('click', 'a[name="item-reload"]', function() {
        var $this = $(this);
        question.itemReload($this);
    });
    $('#vote_form').on('click', '#submit_btn', function() {
        question.submit($(this));
    });

    return {};
});