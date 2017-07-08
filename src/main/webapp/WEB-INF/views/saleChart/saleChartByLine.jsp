<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/highcharts/highcharts.js"></script>
    <title>折线图</title>
    <script>
        $(function () {
            $('#container').highcharts({
                title: {
                    text: '销售报表',
                    x: -20 //center
                },
                subtitle: {
                    text: '按<s:property value="groupType" escapeHtml="false"/>分组',
                    x: -20
                },
                xAxis: {
                    categories: <s:property value="groupData" escapeHtml="false"/>
                },
                yAxis: {
                    title: {
                        text: '销售总额 (￥)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '销售额',
                    data: <s:property value="amounts" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
