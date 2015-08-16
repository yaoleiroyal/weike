/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define([ 'plugin/dataTables/jquery.datatables.min'], function () {
    /* Default class modification */
    $.extend($.fn.dataTableExt.oStdClasses, {
        "sWrapper": "dataTables_wrapper form-inline"
    });

    /* API method to get paging information */
    $.fn.dataTableExt.oApi.fnPagingInfo = function (oSettings) {
        return {
            "iStart"        : oSettings._iDisplayStart,
            "iEnd"          : oSettings.fnDisplayEnd(),
            "iLength"       : oSettings._iDisplayLength,
            "iTotal"        : oSettings.fnRecordsTotal(),
            "iFilteredTotal": oSettings.fnRecordsDisplay(),
            "iPage"         : Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
            "iTotalPages"   : Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
        };
    };

    /* Sangoma style pagination control */
    $.extend($.fn.dataTableExt.oPagination, {
        "sangoma": {
            "fnInit": function (oSettings, nPaging, fnDraw) {
                var oLang = oSettings.oLanguage.oPaginate;
                var fnClickHandler = function (e) {
                    e.preventDefault();
                    if (oSettings.oApi._fnPageChange(oSettings, e.data.action)) {
                        fnDraw(oSettings);
                    }
                };

                $(nPaging).addClass('pagination-right').append(
                        '<ul class="pagination">' +
                        '<li class="prev disabled"><a href="#">&larr; ' + oLang.sPrevious + '</a></li>' +
                        '<li class="next disabled"><a href="#">' + oLang.sNext + ' &rarr; </a></li>' +
                        '</ul>'
                );
                var els = $('a', nPaging);
                $(els[0]).bind('click.DT', { action: "previous" }, fnClickHandler);
                $(els[1]).bind('click.DT', { action: "next" }, fnClickHandler);
            },

            "fnUpdate": function (oSettings, fnDraw) {
                var iListLength = 5;
                var oPaging = oSettings.oInstance.fnPagingInfo();
                var an = oSettings.aanFeatures.p;
                var i, j, sClass, iStart, iEnd, iHalf = Math.floor(iListLength / 2);

                if (oPaging.iTotalPages < iListLength) {
                    iStart = 1;
                    iEnd = oPaging.iTotalPages;
                }
                else if (oPaging.iPage <= iHalf) {
                    iStart = 1;
                    iEnd = iListLength;
                } else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
                    iStart = oPaging.iTotalPages - iListLength + 1;
                    iEnd = oPaging.iTotalPages;
                } else {
                    iStart = oPaging.iPage - iHalf + 1;
                    iEnd = iStart + iListLength - 1;
                }

                for (i = 0, iLen = an.length; i < iLen; i++) {
                    // Remove the middle elements
                    $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                    // Add the new list items and their event handlers
                    for (j = iStart; j <= iEnd; j++) {
                        sClass = (j == oPaging.iPage + 1) ? 'class="active"' : '';
                        $('<li ' + sClass + '><a href="#">' + j + '</a></li>')
                            .insertBefore($('li:last', an[i])[0])
                            .bind('click', function (e) {
                                e.preventDefault();
                                oSettings._iDisplayStart = (parseInt($('a', this).text(), 10) - 1) * oPaging.iLength;
                                fnDraw(oSettings);
                            });
                    }

                    // Add / remove disabled classes from the static elements
                    if (oPaging.iPage === 0) {
                        $('li:first', an[i]).addClass('disabled');
                    } else {
                        $('li:first', an[i]).removeClass('disabled');
                    }

                    if (oPaging.iPage === oPaging.iTotalPages - 1 || oPaging.iTotalPages === 0) {
                        $('li:last', an[i]).addClass('disabled');
                    } else {
                        $('li:last', an[i]).removeClass('disabled');
                    }
                }
            }
        }
    });

    // language setting.
    var defaultTableOptions = {
        'bLengthChange'  : false,
        'bFilter'        : false, //搜索栏
        'bSort'          : true, //是否支持排序功能
        'bInfo'          : true, //显示表格信息
        'bStateSave'     : true, //保存状态到cookie
        'bAutoWidth'     : false,
        'bPaginate'      : true,
        'sPaginationType': 'sangoma',
        'bProcessing'    : false,
        'bServerSide'    : true,
        'iDisplayLength' : 10,
        'oLanguage'      : {
            'sLengthMenu'  : "<span>每页显示</span> _MENU_",
            'sZeroRecords' : "对不起，查询不到任何相关数据",
            'sInfo'        : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            'sInfoEmpty'   : "没有任何数据可以显示",
            'sInfoFiltered': "(数据表中共为 _MAX_ 条记录)",
            'sProcessing'  : "正在加载中...",
            'sSearch'      : "<span>搜索：</span> _INPUT_",
            'oPaginate'    : {
                'sFirst'   : "首页",
                'sPrevious': "< ",
                'sNext'    : "> ",
                'sLast'    : "尾页 "
            }
        }, //多语言配置
        // 序号最终实现
        'fnRowCallback'  : function (nRow, aData, iDisplayIndex) {
            var oSettings = this.fnSettings();
            // data-index with in data dom.
            var $table = $('#' + oSettings.sTableId);
            var index = $table.data('index');
            if (index) {
                $('td:eq(' + index + ')', nRow).html(oSettings._iDisplayStart + iDisplayIndex + 1);
            }
            return nRow;
        },
        'fnDrawCallback' : function (oSettings) {
            var $table = $('#' + oSettings.sTableId);

        }
    };

    var initDatatable = function (domId, options) {
        if (domId) {
            var $dt = $('#' + domId);
            if (!options['sAjaxSource']) {
                options['sAjaxSource'] = $dt.attr('data-sAjaxSource');
            }
            var opts = $.extend(defaultTableOptions, options);
            return $dt.dataTable(opts);
        }
    };

    return {
        initDatatable: initDatatable,
        autono       : { //自增长序号
            'mDataProp'      : 'id',
            'sDefaultContent': 1,
            'bSortable'      : false
        }, checkall  : {
            'sTitle'         : "<input type='checkbox' class='checkall'/>",
            'mDataProp'      : null,
            'sDefaultContent': "<input type='checkbox'/>",
            'bSortable'      : false
        }
    }
});