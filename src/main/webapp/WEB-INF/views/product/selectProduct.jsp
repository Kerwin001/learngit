<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/js/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="/js/artDialog/iframeTools.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $('.fancybox').fancybox();
            $(".select_product").click(function () {
                //获取父代元素中的第一个tr(所在的行)
                var currentTr = $(this).closest("tr");
                var productjson = currentTr.data("productjson");
                $.dialog.data("productjson", productjson);
                $.dialog.close();
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/msg.jsp" %>
<s:form id="searchForm" action="product_selectProduct" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        编码/名称
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="qo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
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
                        <th>货品图片</th>
                        <th>货品名称</th>
                        <th>货品编码</th>
                        <th>货品品牌</th>
                        <th>成本价格</th>
                        <th>销售价格</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#page.listData">
                        <tr data-productjson="<s:property value="productJson"/>">
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                            </td>
                            <td>
                                <a class="fancybox" href="<s:property value="imagePath"/>">
                                    <img src="<s:property value="smallImgPath"/>" width="60px"/>
                                </a>
                            </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td>
                                <input type="button" value="选择该商品" class="ui_input_btn01 select_product"/>
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
