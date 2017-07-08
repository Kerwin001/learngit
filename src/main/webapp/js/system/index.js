var title = "业务模块";
$(function () {
    $("#nav_resource a").click(function () {
        var url = $(this).data("url");
        var h = $(this).html();
    });
    //设置时间
    window.setInterval(function () {
        var date = new Date();
        $("#day_day").html(date.toLocaleString());
    }, 1000);
});
$(function () {
    $("#TabPage2 li").click(function () {
        $("#TabPage2 li").removeClass("selected");
        $(this).addClass("selected");
        $.each($("#TabPage2 img"), function (index, item) {
            $(item).prop("src", "images/common/" + (index + 1) + ".jpg");
        });
        $(this).find("img").prop("src", "images/common/" + ($(this).index() + 1) + "_hover.jpg");
        $("#nav_module img").prop("src", "images/common/module_" + ($(this).index() + 1) + ".png");
        title = $(this).prop("title");
        $("#here_area").html(title)
        $.fn.zTree.init($("#dleft_tab1"), setting, zTreeMap[title]);
    });
});
var setting = {
    async: {
        enable: true,
        url: "/systemMenu_getMenusBySn.action",
        autoParam: ["sn=qo.sn"],
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event, treeId, tNode) {
            var action = tNode.action;
            if (action) {
                $("#rightMain").prop("src", "/" + action + ".action");
                $("#here_area").html(title + " > " + tNode.name);
            }
        }
    }
};
var zNode1 = [
    {id: 1, pId: 0, name: "业务模块", sn: "business", open: false, isParent: true}
];
var zNode2 = [
    {id: 1, pId: 0, name: "系统管理", sn: "system", open: false, isParent: true}
];
var zNode3 = [
    {id: 1, pId: 0, name: "报表", sn: "chart", open: false, isParent: true}
];
zTreeMap = {
    "业务模块": zNode1,
    "系统管理": zNode2,
    "报表": zNode3
}
$(function () {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNode1);
});