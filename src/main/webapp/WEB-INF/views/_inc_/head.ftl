<header class="bg-primary header header-md navbar navbar-fixed-top-xs box-shadow">
    <div class="navbar-header aside-md dk">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen" data-target="#nav">
            <i class="fa fa-bars"></i>
        </a>
        <a href="${ctx}/" class="navbar-brand">
            <img src="${ctx}/static/images/logo.png" class="m-r-sm">居家养老
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
            <i class="fa fa-cog"></i>
        </a>
    </div>
    <ul class="nav navbar-nav hidden-xs">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="i i-file-pdf"></i>
            </a>
            <section class="dropdown-menu aside-lg bg-white on animated fadeInLeft">
                <div class="row m-l-none m-r-none m-t m-b text-center">
                    <div class="col-xs-4">
                        <div class="padder-v">
                            <a href="${ctx}/health/list/vote">
                                <span class="m-b-xs block">
                                    <i class="i i-health i-2x text-primary-lt"></i>
                                </span>
                                <small class="text-muted">体质问卷报告</small>
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="padder-v">
                            <a href="${ctx}/health/list/tongue">
                                <span class="m-b-xs block">
                                    <i class="i  i-stats i-2x text-danger-lt"></i>
                                </span>
                                <small class="text-muted">舌诊评测报告</small>
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="padder-v">
                            <a href="#">
                                <span class="m-b-xs block">
                                    <i class="i i-clock i-2x text-warning-lter"></i>
                                </span>
                                <small class="text-muted">健康周期</small>
                            </a>
                        </div>
                    </div>
                </div>
            </section>
        </li>
        <li class="nav navbar-nav">
            <a href="${ctx}/constitution/vote/1" >
                <i class="i i-question"></i>
            </a>
        </li>
    </ul>
    <ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user user">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="thumb-sm avatar pull-left">
                    <img src="${ctx}/static/images/a0.jpg">
                </span>
                <@shiro.principal/> <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
                <span class="arrow top"></span>
                <li>
                    <a href="#">系统设置</a>
                </li>
                <li>
                    <a href="${ctx}/profile">个人资料</a>
                </li>
                <li>
                    <a href="#">
                        <span class="badge bg-danger pull-right">3</span>
                        消息通知
                    </a>
                </li>
                <li>
                    <a href="${ctx}/help">系统帮助</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="${ctx}/logout">注销退出</a>
                </li>
            </ul>
        </li>
    </ul>
</header>