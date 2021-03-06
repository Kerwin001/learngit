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
<s:form id="searchForm" namespace="/" action="role.action" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <s:url namespace="/" action="role_input" var="inputurl"/>
                        <s:url namespace="/" action="role_batchDelete" var="batchDeleteurl"/>
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
                        <th>角色名称</th>
                        <th>角色代码</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <S:iterator value="#page.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td>
                                <s:a namespace="/" action="role_input">
                                    <s:param name="role.id" value="id"/>
                                    编辑</s:a>
                                <s:url namespace="/" action="role_delete" id="deleteurl"><s:param name="role.id"
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
