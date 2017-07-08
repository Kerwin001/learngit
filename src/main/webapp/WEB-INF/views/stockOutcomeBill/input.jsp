<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css"/>
    <link href="/style/common_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.validate/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/artDialog/iframeTools.js"></script>
    <script>
        $(function () {
            //绑定日期控件
            $("[name='stockOutcomeBill.vdate']").addClass("Wdate").click(function () {
                WdatePicker();
            });
            //选择子页面绑定事件及删除明细事件
            $("#edit_table_body").on("change", "[tag=salePrice],[tag=number]", function () {
                var currentTr = $(this).closest("tr");
                var salePrice = currentTr.find("[tag=salePrice]").val();
                var number = currentTr.find("[tag=number]").val();
                var amount = salePrice * number;
                currentTr.find("[tag=amount]").html(amount.toFixed(2));
            }).on("click", ".searchproduct", function () {
                var currentTr = $(this).closest("tr");
                var url = "/product_selectProduct.action";
                $.dialog.open(url, {
                    title: "商品选择",
                    width: 950,
                    height: 600,
                    close: function () {
                        var productjson = $.dialog.data("productjson");
                        currentTr.find("[tag=pid]").val(productjson.id);
                        currentTr.find("[tag=brand]").html(productjson.brand);
                        currentTr.find("[tag=salePrice]").val(productjson.salePrice);
                        currentTr.find("[tag=name]").val(productjson.name);
                    }
                });
            }).on("click",".removeItem",function(){
                if($("#edit_table_body tr").size()>1){
                    $(this).closest("tr").remove();
                }else{
                    var currentTr = $(this).closest("tr");
                    currentTr.find(":text").val("");
                    currentTr.find("span").html("");
                }
            });
            //绑定添加明细事件
            $(".appendRow").click(function(){
                var cloneTr = $("#edit_table_body tr:first").clone();
                cloneTr.find(":text").val("");
                cloneTr.find("span").html("");
                cloneTr.appendTo($("#edit_table_body"));
            });
            //绑定提交事件
            $("#editForm").submit(function(){
                $.each($("#edit_table_body tr"),function(index,item){
                    $(item).find("[tag=pid]").prop("name","stockOutcomeBill.items["+index+"].product.id");
                    $(item).find("[tag=salePrice]").prop("name","stockOutcomeBill.items["+index+"].salePrice");
                    $(item).find("[tag=number]").prop("name","stockOutcomeBill.items["+index+"].number");
                    $(item).find("[tag=remark]").prop("name","stockOutcomeBill.items["+index+"].remark");
                });
            });
        });
    </script>
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/msg.jsp" %>
<s:form name="editForm" namespace="/" action="stockOutcomeBill_saveOrUpdate" method="post" id="editForm">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购入库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="stockOutcomeBill.id"></s:hidden>
                <tr>
                    <td class="ui_text_rt" width="140">入库单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.sn" cssClass="ui_input_txt02"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <s:select list="#depots" listKey="id" listValue="name" name="stockOutcomeBill.depot.id"
                                  cssClass="ui_select03"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <s:select list="#clients" listKey="id" listValue="name" name="stockOutcomeBill.client.id"
                                  cssClass="ui_select03"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.vdate" cssClass="ui_input_txt02"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <s:if test="stockOutcomeBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="stockOutcomeBill.items.product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="salePrice" name="stockOutcomeBill.items.salePrice"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><s:textfield tag="number" name="stockOutcomeBill.items.number"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="stockOutcomeBill.items.remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>

                            </s:if>
                            <s:else>
                                <s:iterator value="stockOutcomeBill.items">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <s:textfield disabled="true" readonly="true"
                                                         name="product.name" cssClass="ui_input_txt02" tag="name"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <s:hidden name="product.id" tag="pid"/>
                                        </td>
                                        <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield tag="salePrice" name="salePrice"
                                                         cssClass="ui_input_txt02"/></td>
                                        <td><s:textfield tag="number" name="number"
                                                         cssClass="ui_input_txt02"/></td>
                                        <td><span tag="amount"><s:property value="amount"/></span></td>
                                        <td><s:textfield tag="remark" name="remark"
                                                         cssClass="ui_input_txt02"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>