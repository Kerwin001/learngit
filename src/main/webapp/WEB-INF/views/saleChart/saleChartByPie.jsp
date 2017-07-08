<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script src="/js/jquery/jquery.js"></script>
<script src="/js/highcharts/highcharts.js"></script>
<script src="/js/highcharts/highcharts-3d.js"></script>
    <title>饼图</title>
    <script>
        $(function () {
            $('#container').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: '销售报表',
                    x:-20
                },
                subtitle: {
                    text: '按<s:property value="groupType" escapeHtml="false"/>分组',
                    x: -20
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 35,
                        dataLabels: {
                            enabled: true,
                            format: '{point.name}'
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '占百分比',
                    data: <s:property value="data" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>
<div id="container" style="height: 400px"></div>
</body>
</html>
