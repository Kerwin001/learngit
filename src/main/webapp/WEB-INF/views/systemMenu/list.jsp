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
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-系统菜单管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/msg.jsp"%>
 <s:form id="searchForm" action="systemMenu" namespace="/" method="post">
     <s:hidden name="qo.parentId"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
 <%--                   <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        所属部门
                        <s:select list="#depts" listKey="id" listValue="name" name="qo.deptId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                    </div>--%>
                    <div id="box_bottom">
<%--
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"  data-page="1"/>
--%>                    <s:url var="inputUrl" namespace="/" action="systemMenu_input">
                            <s:param name="qo.parentId" value="qo.parentId"/>
                        </s:url>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="inputUrl"/> "/>
              <%--          <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="systemMenu_batchDelete"/>"/>--%>
                    </div>
                    当前 : <a href="/systemMenu.action">根目录</a>
                    <s:iterator value="#parents">
                        >
                        <s:a namespace="/" action="systemMenu">
                            <s:param name="qo.parentId" value="id"/>
                            <s:property value="name"/>
                        </s:a>
                    </s:iterator>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>编号</th>
                                    <th>菜单编码</th>
                                    <th>菜单名称</th>
                                    <th>上级菜单</th>
                                    <th>URL</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#page.listData">
                        <tr>
                            <td><input type="checkbox" class="acb" data-oid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                                <td><s:property value="sn"/> </td>
                                <td><s:property value="name"/> </td>
                                <td><s:if test="parent==null">根目录</s:if> <s:property value="parent.name"/></td>
                                <td><s:property value="url"/> </td>
                            <td>
                                <s:a namespace="" action="systemMenu_input">
                                    <s:param name="qo.parentId" value="qo.parentId"></s:param>
                                    <s:param name="systemMenu.id" value="id"></s:param>
                                    编辑</s:a>
                                <s:url namespace="" action="systemMenu_delete" var="deleteUrl">
                                    <s:param name="systemMenu.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" name="delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除
                                </a>
                                <s:a namespace="/" action="systemMenu">
                                    <s:param name="qo.parentId" value="id"></s:param>
                                    查看子菜单</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/WEB-INF/views/common/page.jsp" %>
    </div>
</s:form>
</body>
</html>
