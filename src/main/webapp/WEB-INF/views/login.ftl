<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8" />
    <title>[健康&养老]~平台 | 系统登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="${ctx}/static/css/h2.css" type="text/css" />
</head>
<body class="">
<section id="content" class="m-t-lg wrapper-md animated fadeInUp">
    <div class="container aside-xl">
        <a class="navbar-brand block" href="${ctx}/">H2Double[健康&养老]~平台</a>
        <section class="m-b-lg">
            <header class="wrapper text-center"> <strong>平台登录</strong>
            </header>
            <form action="${ctx}/login" method="post">
                <div class="list-group">
                    <div class="list-group-item">
                        <input type="text" placeholder="登录帐号" name="username" class="form-control no-border" value="${username!}"></div>
                    <div class="list-group-item">
                        <input type="password" placeholder="帐号密码" name="password" class="form-control no-border" value=""></div>
                </div>
                <button type="submit" class="btn btn-lg btn-primary btn-block">登录</button>
            </form>
        </section>
    </div>
</section>
<footer id="footer">
    <div class="text-center padder">
        <p>
            <small>H2Double @ Aetc<br>&copy; 2013</small>
        </p>
    </div>
</footer>
</body>
</html>