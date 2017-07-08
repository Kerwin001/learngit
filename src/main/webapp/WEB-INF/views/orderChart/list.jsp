<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-订货报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $("[name='qo.beginDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate: $("[name='qo.endDate']").val() || new Date()
                });
            });
            $("[name='qo.endDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    minDate: $("[name='qo.beginDate']").val()
                });
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/msg.jsp" %>
<s:form id="searchForm" action="orderChart" namespace="/" method="post">
<div id="container">
    <div class="ui_content">
        <div class="ui_text_indent">
            <div id="box_border">
                <div id="box_top">搜索</div>
                <div id="box_center">
                    业务时间
                    <s:date name="qo.beginDate" format="yyyy-MM-dd" var="beginDate"/>
                    <s:textfield name="qo.beginDate" value="%{#beginDate}" class="ui_input_txt02"/>
                    ~
                    <s:date name="qo.endDate" format="yyyy-MM-dd" var="endDate"/>
                    <s:textfield name="qo.endDate" value="%{#endDate}" class="ui_input_txt02"/>
                    货品
                    <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                    供应商
                    <s:select list="#suppliers" listKey="id" listValue="name" name="qo.supplierId"
                              headerKey="-1" headerValue="全部" class="ui_select01"/>
                    品牌
                    <s:select list="#brands" listKey="id" listValue="name" headerKey="-1" headerValue="全部"
                              class="ui_select01" name="qo.brandId"/>
                    分组
                    <s:select list="#groupTypes" class="ui_select01" name="qo.groupType"/>
                </div>
                <div id="box_bottom">
                    <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                </div>
            </div>
        </div>
    </div>
    <div class="ui_content">
        <div class="ui_tb">
            <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                <tr>
                    <th width="30"><input type="checkbox" id="all"/></th>
                    <th>分组类型</th>
                    <th>采购总数量</th>
                    <th>采购总金额</th>
                </tr>
                <tbody>
                <s:iterator value="#list">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                        </td>
                        <td><s:property value="groupType"/></td>
                        <td><s:property value="totalNumber"/></td>
                        <td><s:property value="totalAmount"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
    </s:form>
</body>
</html>
