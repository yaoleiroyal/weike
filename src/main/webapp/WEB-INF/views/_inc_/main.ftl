<#compress>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <title><@block name="title"></@block> | demo</title>
    <meta name="description" content="健康,养老,居家"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="${ctx}/static/css/h2.css" type="text/css"/>
    <style type="text/css">
        body {
            color: #000000;
        }

        .bg-mov-highbg {
            background-color: #DFF7D9
        }
    </style>
    <@block name="moreCss"></@block>
    <!--[if lt IE 9]>
    <script src="${ctx}/static/scripts/ie/html5shiv.js"></script>
    <script src="${ctx}/static/scripts/ie/respond.min.js"></script>
    <script src="${ctx}/static/scripts/ie/excanvas.js"></script>
    <![endif]-->
</head>
<body class="">
<section class="vbox">
    <#include 'head.ftl'>
    <section>
        <section class="hbox stretch">

            <section id="content">
                <@block name="viewbody"></@block>
            </section>
        </section>
    </section>
</section>

<script type="text/javascript">
    var g = {};
    g.ctx = '${ctx}/';

        <@block name="viewscripts"></@block>

    var require = {
        baseUrl: g.ctx + 'static/scripts/libs',
        paths: {
            'text': 'text/text',
            'app': '../app',
            'plugins': '../plugins',
            'modules': '../modules',
            'views': '../views',
            'echarts': '../plugins/echarts/echarts',
            'echarts/chart/bar': '../plugins/echarts/echarts-map',
            'echarts/chart/line': '../plugins/echarts/echarts-map',
            'echarts/chart/map': '../plugins/echarts/echarts-map'

        }
    };

</script>
<script type="text/javascript" src="${ctx}/static/scripts/libs/handlebars.js"></script>
<script type="text/javascript" src="${ctx}/static/scripts/app/h2.js"></script>
    <@block name="moreJs"></@block>
<script charset="utf-8" data-main="../views/<@block name="viewmodule"></@block>"
        src="${ctx}/static/scripts/libs/require.js"></script>
</body>
</html>
</#compress>