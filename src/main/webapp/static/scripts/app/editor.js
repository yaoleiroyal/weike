/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {
    require('plugin/kindeditor/kindeditor-all-min');
    require('plugin/kindeditor/lang/zh_CN');

    KindEditor.options.cssData = 'body { font-size: 14px; } .ke-content img.ke-emoticons {width: auto;}';

    var defaultOptions = {
        minHeight            : '480px',
        height               : '480px',
        fontSizeTable        : ['12px', '14px', '16px', '18px', '24px', '32px'],
        resizeType           : 1,
        allowPreviewEmoticons: false,
        allowImageUpload     : false,
        uploadJson           : g.ctx + 'file/editor',
        items                : [
            'source','|','undo', 'redo', '|', 'bold', 'italic', 'underline','plainpaste', 'wordpaste',
            '|', 'link','unlink', 'justifyleft', 'justifycenter', 'justifyright','insertorderedlist', 'insertunorderedlist',
            '|', 'emoticons', 'multiimage', 'baidumap',
            '|', 'removeformat', 'forecolor', 'hilitecolor','preview']
    };

    return {
        initEditor: function(editor, options){

            var opts = $.extend(defaultOptions, options);
            return  KindEditor.create(editor, opts);
        }
    };
});