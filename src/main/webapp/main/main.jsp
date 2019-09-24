<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="../login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../login/assets/css/form-elements.css">
    <link rel="stylesheet" href="../login/assets/css/style.css">
    <link rel="shortcut icon" href="../login/assets/ico/favicon.png">
    <link rel="icon" href="../img/shouye.jpg">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="../login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="../login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="../login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../login/assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../login/assets/js/jquery.backstretch.min.js"></script>
    <script src="../login/assets/js/scripts.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js">

    </script>
</head>

<body>
<nav class="navbar-default" style="background-color: #a6e1ec">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">持名法州管理系统</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">
                    欢迎${sessionScope.admin.name}
                </a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/logout"><span
                            class="glyphicon glyphicon-log-out">退出登录</span></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-md-2" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-primary">
            <div class="panel-heading" role="tab">
                <h3 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                       aria-expanded="true" aria-controls="collapseOne">
                        用户管理
                    </a>
                </h3>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                 aria-labelledby="headingOne">
                <div class="panel-body">
                   <button class="btn-danger"><a href="javascript:$('#changeImage').load('${pageContext.request.contextPath}/main/user.jsp')">用户信息</a></button>
                     </br>
                    <button class="btn-default"><a href="javascript:$('#changeImage').load('${pageContext.request.contextPath}/main/china.jsp')">用户分布</a>
                    </button>
                    </br>
                     <button class="btn-warning"><a href="javascript:$('#changeImage').load('${pageContext.request.contextPath}/main/echart.jsp')">用户统计</a>
                     </button>
                </div>
            </div>
        </div>
        <hr>
        <div class="panel panel-warning">
            <div class="panel-heading" role="tab">
                <h3 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                       href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        上师管理
                    </a>
                </h3>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                <div class="panel-body">
                    <li>
                        <a href="javascript:$('#changeImage').load('guru.jsp')">上师详情</a>
                    </li>
                </div>
            </div>
        </div>
        <hr>
        <div class="panel panel-success">
            <div class="panel-heading" role="tab">
                <h3 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                       href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        文章管理
                    </a>
                </h3>
            </div>
            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                 aria-labelledby="headingThree">
                <div class="panel-body">
                    <li>
                        <a href="javascript:$('#changeImage').load('article.jsp')">文章列表</a>
                    </li>
                </div>
            </div>
        </div>
        <hr>
        <div class="panel panel-info">
            <div class="panel-heading" role="tab">
                <h3 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                       href="#collapsefour" aria-expanded="false" aria-controls="collapsefour">
                        专辑管理
                    </a>
                </h3>
            </div>
            <div id="collapsefour" class="panel-collapse collapse" role="tabpanel"
                 aria-labelledby="headingfour">
                <div class="panel-body">
                    <li><a href="javascript:$('#changeImage').load('album.jsp')">专辑与章节信息</a></li>
                </div>
            </div>
        </div>
        <hr>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab">
                <h3 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                       href="#collapsefive" aria-expanded="false" aria-controls="collapsefive">
                        轮播图管理
                    </a>
                </h3>
            </div>
            <div id="collapsefive" class="panel-collapse collapse" role="tabpanel"
                 aria-labelledby="headingfive">
                <div class="panel-body">
                    <li><a href="javascript:$('#changeImage').load('banner.jsp')">轮播图详情信息</a></li>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-10" role="tablist" id="changeImage">
        <div class="jumbotron">
            <h4>欢迎来到驰名法州后台管理系统</h4>
        </div>

        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="../img/shouye.jpg" width="1100">
                    <div class="carousel-caption">
                        热爱国家
                    </div>
                </div>
                <div class="item ">
                    <img src="../img/1.jpg" width="1100">
                    <div class="carousel-caption">
                        献身使命
                    </div>
                </div>
                <div class="item ">
                    <img src="../img/2.jpg" width=1100">
                    <div class="carousel-caption">
                        崇尚科学
                    </div>
                </div>
                <div class="item ">
                    <img src="../img/3.jpg" width=1100">
                    <div class="carousel-caption">
                        荣辱与共
                    </div>
                </div>
                ...
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
            <nav class="navbar-default">
                <div class="container">
                    <center>www.magicboy赚大钱.com</center>
                </div>
            </nav>
        </div>
    </div>
</div>
</body>

</html>