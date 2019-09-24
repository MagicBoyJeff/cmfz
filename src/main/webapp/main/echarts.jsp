<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
    <!-- 引入 ECharts 文件 -->
    <script src="../Echarts/echarts.min.js"></script>
    <script src="../Echarts/china.js"></script>
   <script src="../boot/js/jquery-2.2.1.min.js"></script>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户统计'
        },
        tooltip: {},
        legend: {
            data:['注册人数']
        },
        xAxis: {
            data: ["0-15岁","16-30岁","30-55岁","56以上"]
        },
        yAxis: {},
        series: [{
            name: '季度',
            type: 'bar',
            data: [100,88,66,70]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
