<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
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
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>


<script type="text/javascript">

    $(function(){

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var goEasy = new GoEasy({ appkey: "BC-4f8cfd5fff6741c6b58dc71193c5b393"});
        goEasy.subscribe({
            channel: "my_channel",
            onMessage: function (message) {
                var data=JSON.parse(message.content);
                // 指定图表的配置项和数据
                var option = {
                    //标题
                    title: {
                        text: "每月注册用户统计图"
                    },
                    tooltip: {},  //鼠标提示
                    legend: {     //选项卡
                        data:['柱状图','折线图']
                    },
                    xAxis: {   //横坐标
                        data: ["衬衫","毛衣","鞋子","裤子","袜子"]
                    },
                    yAxis: {},   //纵坐标
                    series: [{   //数据系列
                        name: "柱状图",   //选项卡名字
                        type: 'bar',  //柱状图
                        data: data.data
                    },{   //数据系列
                        name: "折线图",
                        type: 'line',  //折线图
                        data: data.data
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });
    });
</script>

</head>


<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>


