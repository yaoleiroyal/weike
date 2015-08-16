<@override name="title">首页</@override>
<@override name="moreCss">
<link rel="stylesheet" href="${ctx}/static/scripts/plugins/calendar/bootstrap_calendar.css" type="text/css"/>
</@override>

<@override name="viewbody">
<section class="vbox">
<header class="header bg-white b-b b-light"><p>我的健康中心</p></header>

<section class="scrollable wrapper">
<div class="row">
    <div class="col-lg-2">
        <section class="panel panel-default">
            <header class="panel-heading bg-light no-border">
                <div class="clearfix">
                    <a href="#" class="pull-left thumb-md avatar b-3x m-r">
                        <img src="${ctx}/static/images/a0.jpg">
                    </a>

                    <div class="clear">
                        <div class="h3 m-t-xs m-b-xs">
                            ${person.full_name!}
                            <i class="fa fa-circle text-success pull-right text-xs m-t-sm"></i>
                        </div>
                        <small class="text-muted">普通会员</small>
                    </div>
                </div>
            </header>
            <div class="list-group no-radius alt">
                <a class="list-group-item" href="${ctx}/me/report">
                    <i class="fa fa-comment icon-muted"></i> 我的健康报告
                </a>
                <a class="list-group-item" href="${ctx}/me/sign">
                    <i class="fa fa-comment icon-muted"></i> 我的健康体征
                </a>
                <a class="list-group-item" href="${ctx}/me/tongue">
                    <i class="fa fa-eye icon-muted"></i> 我的舌诊评测
                </a>
            </div>
        </section>

        <section class="panel panel-info portlet-item">
            <header class="panel-heading">体质问卷评测入口</header>
            <div class="list-group bg-white">
                <#list votes as vote>
                    <a href="${ctx}/constitution/vote/${vote.id!}" class="list-group-item">
                        <i class="fa fa-fw fa-envelope"></i>
                        ${vote.title!}
                    </a>
                </#list>
            </div>
        </section>
    </div>
    <div class="col-lg-7">
        <div class="panel b-a">
            <div class="row m-n">
                <div class="col-md-6 b-b b-r">
                    <a href="#" class="block padder-v hover">
                        <span class="i-s i-s-2x pull-left m-r-sm">
                            <i class="i i-hexagon2 i-s-base text-danger hover-rotate"></i>
                            <i class="i i-plus2 i-1x text-white"></i>
                        </span>
                        <span class="clear">
                            <#if bloodPressure??>
                                <span class="h3 block m-t-xs text-danger">
                                    舒张压:${bloodPressure.diastolicpressure!}<span class="text-sm">mmHg</span> ,
                                    收缩压:${bloodPressure.systolicpressure!}<span class="text-sm">mmHg</span>
                                </span>
                                <small class="text-muted">上次(${bloodPressure.collectdate?string('yyyy-MM-dd')})测量血压，单位：mmHg(毫米汞柱)</small>
                            <#else>
                                <span class="h3 block m-t-xs text-danger">暂无数据</span>
                                <small class="text-muted">尚未进行血压测量，建议定期进行测量</small>
                            </#if>
                        </span>
                    </a>
                </div>
                <div class="col-md-6 b-b">
                    <a href="#" class="block padder-v hover">
                        <span class="i-s i-s-2x pull-left m-r-sm">
                            <i class="i i-hexagon2 i-s-base text-success-lt hover-rotate"></i>
                            <i class="i i-users2 i-sm text-white"></i>
                        </span>
                        <span class="clear">
                            <#if bloodSugar??>
                                <span class="h3 block m-t-xs text-success">${bloodSugar.bloodsugar!}<span class="text-sm">mmol/L</span></span>
                                <small class="text-muted text-u-c">上次(${bloodSugar.collectdate?string('yyyy-MM-dd')})测量血糖，单位：mmol/L(毫摩尔/每升)</small>
                            <#else>
                                <span class="h3 block m-t-xs text-success">暂无数据</span>
                                <small class="text-muted text-u-c">尚未进行血糖测量，建议定期进行测量</small>
                            </#if>
                        </span>
                    </a>
                </div>
                <div class="col-md-6 b-b b-r">
                    <a href="#" class="block padder-v hover">
                        <span class="i-s i-s-2x pull-left m-r-sm">
                            <i class="i i-hexagon2 i-s-base text-info hover-rotate"></i>
                            <i class="i i-location i-sm text-white"></i>
                        </span>
                        <span class="clear">
                            <#if bloodOxygen??>
                                <span class="h3 block m-t-xs text-info">
                                    ${bloodOxygen.oxygen!} <span class="text-sm">%</span>
                                </span>
                                <small class="text-muted text-u-c">上次(${bloodOxygen.collectdate?string('yyyy-MM-dd')})测量血氧</small>
                            <#else>
                                <span class="h3 block m-t-xs text-info">暂无数据</span>
                                <small class="text-muted text-u-c">尚未进行血氧测量，建议定期进行测量</small>
                            </#if>
                        </span>
                    </a>
                </div>


                <div class="col-md-6 b-b">
                    <a href="#" class="block padder-v hover">
                        <span class="i-s i-s-2x pull-left m-r-sm">
                            <i class="i i-hexagon2 i-s-base text-warning hover-rotate"></i>
                            <i class="i i-alarm i-sm text-white"></i>
                        </span>
                        <span class="clear">
                            <#if constitutionResult??>
                                <span class="h3 block m-t-xs text-warning">${constitutionResult.result!}</span>
                                <small class="text-muted text-u-c">最近(${constitutionResult.create_time?string('yyyy-MM-dd')})体质问卷结果</small>
                            <#else>
                                <span class="h3 block m-t-xs text-warning">暂无结果</span>
                                <small class="text-muted text-u-c">尚未进行体质问卷调查</small>
                            </#if>
                        </span>
                    </a>
                </div>

                <div class="col-md-12 b-b">
                    <a href="#" class="block padder-v hover">
                        <span class="i-s i-s-2x pull-left m-r-sm">
                            <i class="i i-hexagon2 i-s-base text-primary hover-rotate"></i>
                            <i class="i i-graph i-sm text-white"></i>
                        </span>
                        <#if ecg??>
                            <span class="clear">
                                <span class="h3 block m-t-xs text-primary">
                                    ${ecg.waveform!},
                                    ${ecg.heartrate!},
                                    ${ecg.stisnormal!}
                                    ${ecg.wholewave!},
                                    ${ecg.isarrhythmia!}
                                </span>
                                <small class="text-muted text-u-c">上次(${ecg.collectdate?string('yyyy-MM-dd')})心电测量信息</small>
                            </span>
                        <#else>
                            <span class="clear">
                                <span class="h3 block m-t-xs text-primary">暂无数据</span>
                                <small class="text-muted text-u-c">尚未进行心电测量，建议定期进行测量</small>
                            </span>
                        </#if>
                    </a>
                </div>
            </div>
        </div>

        <section class="panel panel-default">
            <header class="panel-heading font-bold">健康提醒</header>
            <div class="panel-body">
                <ul class="list-group">
                    <#if constitutionResult??>
                        <li class="list-group-item">
                            <p>
                                你的体质为 <a href="#" class="text-info">${constitutionResult.constitution_name!}</a>
                                <#if constitutionResult.inappropriate??>
                                不宜食用：${constitutionResult.inappropriate}
                                </#if>
                            </p>
                        </li>
                    </#if>
                    <#if bloodPressure??>
                    <li class="list-group-item">
                        <p>
                            您的血压值为
                            <a href="#" class="text-info">
                                舒张压:${bloodPressure.diastolicpressure!}<span class="text-sm">mmHg</span> ,
                                收缩压:${bloodPressure.systolicpressure!}<span class="text-sm">mmHg</span>
                            </a>
                            ${bloodPressure.tip!}
                        </p>
                    </li>
                    </#if>
                    <#if constitutionResult?? && constitutionResult.principle??>
                        <li class="list-group-item">
                            <p>
                                ${constitutionResult.principle!}.
                            </p>
                        </li>
                    </#if>
                </ul>
            </div>
        </section>
    </div>
    <div class="col-lg-3">
        <section class="panel b-light">
            <header class="panel-heading">
                <strong>日历</strong>
            </header>
            <div id="calendar" class="bg-light dker m-l-n-xxs m-r-n-xxs"></div>
            <div class="list-group">
                <a href="#" class="list-group-item text-ellipsis">
                    <span class="badge bg-success">平和体质</span>
                    中医体质：
                </a>
                <a href="#" class="list-group-item text-ellipsis">
                    <span class="badge bg-success">220</span>
                    今日血压：
                </a>
                <a href="#" class="list-group-item text-ellipsis">
                    <span class="badge bg-success">120</span>
                    今日血糖：
                </a>
                <a href="#" class="list-group-item text-ellipsis">
                    <span class="badge bg-warning">还未测量呢,点击这里查看如何进行测量</span>
                    今日血氧
                </a>
            </div>
        </section>
    </div>
</div>

<div class="row">
    <div class="col-lg-6">
        <section class="panel panel-default portlet-item">
            <header class="panel-heading">
                健康体征一周走向图 &nbsp;&nbsp;
            </header>
            <section class="panel-body">
                <div id="sign_chart" style="height:300px;padding:5px;"></div>
            </section>
        </section>
    </div>
    <div class="col-lg-6">

        <section class="panel panel-default portlet-item">
            <header class="panel-heading">
                健康资讯 &nbsp;&nbsp;
            </header>
            <section class="panel-body">
                <#list feeds as feed>
                    <article class="media">
                        <div class="media-body">
                            <a href="#" class="h4">${feed.title!}</a>
                            <small class="block m-t-xs">
                            ${feed.content!}
                            </small>
                            <em class="text-xs">发布日期: <span class="text-danger">${feed.publish_time?string('yyyy-MM-dd')}</span></em>
                        </div>
                    </article>
                <#if feed_has_next>
                    <div class="line pull-in"></div>
                </#if>
                </#list>
            </section>
        </section>
    </div>
</div>
</section>
</section>
</@override>

<@override name="viewmodule">index</@override>

<@extends name="../_inc_/main.ftl"></@extends>