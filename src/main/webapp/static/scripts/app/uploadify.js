/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
define(function (require, exports, module) {
    require('plugin/uploadify/jquery.uploadify');

    var defaultUploaderOptions = {
        'debug'           : false,
        'height'          : 38,
        'auto'            : true,
        'swf'             : g.ctx + 'static/js/plugins/uploadify/uploadify.swf',
        'uploader'        : g.ctx + 'file/up',
        'width'           : 60,
        'buttonText'      : '附件',
        'fileTypeExts'    : '*.jpg;*.jpge;*.gif;*.png',
        'fileTypeDesc'    : '图片',
        'sizeLimit'       : '3MB',
        'wmode'           : 'transparent',
        'hideButton'      : true,
        'itemTemplate'    : '<div id="${fileID}" class="uploadify-queue-item"></div>',
        'queueSizeLimit'  : 1,
        //每次更新上载的文件的进展
        'onUploadProgress': function (file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#upload_loading').show();
        }, //选择上传文件后调用
        'onSelect'        : function (file) {
//                console.log(file)
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError'   : function (file, errorCode, errorMsg) {
            var $fileupload = $('#file_upload');
            switch (errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的" + $fileupload.uploadify('settings', 'queueSizeLimit') + "个文件！");
                    break;
                case -110:
                    alert("文件 [" + file.name + "] 大小超出系统限制的" + $fileupload.uploadify('settings', 'fileSizeLimit') + "大小！");
                    break;
                case -120:
                    alert("文件 [" + file.name + "] 大小异常！");
                    break;
                case -130:
                    alert("文件 [" + file.name + "] 类型不正确！");
                    break;
            }
        },
        //检测FLASH失败调用
        'onFallback'      : function () {
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        'onUploadComplete': function () {
            $('#upload_loading').hide();
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess' : function (file, data, response) {
            var current_dom = $('#' + file.id);
            var jsonData = eval('(' + data + ')');
            if (jsonData['status'] == 'OK') {
                var $fileinput = current_dom.closest('div.fileinput');
                var $input_val = $fileinput.find('input:hidden');

                var attachement_path = jsonData['data'];
                $input_val.val(attachement_path);

                var $preview = $fileinput.find('div.fileinput-preview');
                var img_html = '<a href="' + g.ctx + attachement_path + '" class="thumbnail fancybox-button">' +
                    '<img src="' + g.ctx + attachement_path.replace('.', '.thumbnail.') + '" alt=""></a>';
                $preview.html(img_html);

                var $btns = $fileinput.find('div.fileinput-btn');
                var $remove_btn = $btns.find('a.fileinput-remove');
                if ($remove_btn) {
                    $remove_btn.remove();
                }
                $btns.append('<a href="#" data-data="' + attachement_path + '" class="btn default fileinput-exists fileinput-remove">删除图片</a>');

            }
        }
    };

    return {
        initUploadify: function(uploaderId, options){

            var opts = $.extend(defaultUploaderOptions, options);
            return  $("#" + uploaderId).uploadify(opts);
        }
    };
});