<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- 引入 ECharts 文件 -->
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../Echarts/echarts.min.js"></script>
    <script src="../Echarts/china.js"></script>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
</head>

 <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

 <script type="text/javascript">
        var goEasy = new GoEasy({
                appkey: "BC-4f8cfd5fff6741c6b58dc71193c5b393"
        });
        goEasy.subscribe({
                 channel:"cmfz_jeff",
        onMessage:function(message){
                   alert(message.content);
                }
        });
     </script>
<body>
hello world
</body>

