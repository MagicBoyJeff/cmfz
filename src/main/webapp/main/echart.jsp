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
</head>
<body>
    <script type="text/javascript">
        $(function() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
           $.ajax({
              url:"${pageContext.request.contextPath}/user/showUserBySex",
              datajson:"json",
              type:"GET",
              success:function (data) {
                  // 指定图表的配置项和数据
                  var option = {
                      title: {
                          text: '用户注册量趋势图',  //标题内容
                          show:true,
                          subtarget:"self"
                      },
                      tooltip: {},  //鼠标提示
                      legend: {
                          data:['men','women']   //选项卡
                      },
                      xAxis: {
                          data: data.months  //横轴展示
                      },
                      yAxis: {},   //纵轴展示   自适应
                      series: [{
                          name: 'men',
                          type: 'bar',
                          data: data.men  //数据
                      },{
                          name: 'women',
                          type: 'bar',
                          data: data.women  //数据
                      }]
                  };
                  // 使用刚指定的配置项和数据显示图表。
                  myChart.setOption(option);
              } 
           });
        });
    </script>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>


