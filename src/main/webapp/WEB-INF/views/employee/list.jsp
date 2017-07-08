<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
<%@include file="/WEB-INF/views/common/msg.jsp"%>
</head>
<body>
<s:debug/>
<s:actionerror/>
<s:form id="searchForm" namespace="/" action="employee.action" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        所属部门
                        <s:select list="#depts" name="qo.deptId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <s:url namespace="/" action="employee_input" var="inputurl"/>
                        <s:url namespace="/" action="employee_batchDelete" var="batchDeleteurl"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="<s:property value="#inputurl"/>"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete" data-url="<s:property value="#batchDeleteurl"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>用户名</th>
                        <th>EMAIL</th>
                        <th>年龄</th>
                        <th>所属部门</th>
                        <th>角色</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <S:iterator value="#page.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="email"/></td>
                            <td><s:property value="age"/></td>
                            <td><s:property value="dept.name"/></td>
                            <td><s:property value="roleNames"/> </td>
                            <td>
                                <s:a namespace="/" action="employee_input">
                                    <s:param name="e.id" value="id"/>
                                    编辑</s:a>
                                <s:url namespace="/" action="employee_delete" id="deleteurl"><s:param name="e.id"
                                                                                                      value="id"/></s:url>
                                <a href="javascript:" name="delete" data-url="<s:property value="deleteurl"/>">
                                    删除</a>
                            </td>
                        </tr>
                    </S:iterator>
                    </tbody>
                </table>
            </div>
           <%--分页条--%>
            <%@include file="/WEB-INF/views/common/page.jsp"%>
        </div>
    </div>
</s:form>
</body>
</html>
