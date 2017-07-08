$(function () {
    $("#editForm").submit(function () {
        $(".selected_role option").prop("selected", true);
        $(".selected_menu option").prop("selected", true);
    });
    //权限部分选入
    $("#select").click(function () {
        $(".all_role option:selected").appendTo($(".selected_role"));
    });
    //权限部分移除
    $("#deselect").click(function () {
        $(".selected_role option:selected").appendTo($(".all_role"));
    });
    //权限全部选入
    $("#selectAll").click(function () {
        $(".all_role option").appendTo($(".selected_role"));
    });
    //权限全部移除
    $("#deselectAll").click(function () {
        $(".selected_role option").appendTo($(".all_role"));
    });


    //菜单部分选入
    $("#mselect").click(function () {
        $(".all_menu option:selected").appendTo($(".selected_menu"));
    });
    //菜单部分移除
    $("#mdeselect").click(function () {
        $(".selected_menu option:selected").appendTo($(".all_menu"));
    });
    //菜单全部选入
    $("#mselectAll").click(function () {
        $(".all_menu option").appendTo($(".selected_menu"));
    });
    //菜单全部移除
    $("#mdeselectAll").click(function () {
        $(".selected_menu option").appendTo($(".all_menu"));
    });
});
$(function () {
//去除权限列表重复
    var ids = $.map($(".selected_role option"),function(item,index){
        return $(item).val();
    });
    $.each($(".all_role option"),function(index,item){
        if($.inArray($(item).val(),ids)>=0){
            $(item).remove();
        }
    });
    //去除菜单列表重复
    var ids = $.map($(".selected_menu option"),function(item,index){
        return $(item).val();
    });
    $.each($(".all_menu option"),function(index,item){
        if($.inArray($(item).val(),ids)>=0){
            $(item).remove();
        }
    });
    //重置
    $("#cancelbutton").click(function(){
        $("#editForm").get(0).reset();
    });
});
