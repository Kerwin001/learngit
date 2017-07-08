// 1，用户名不能为空，最小4位长度；
// 2，密码不能为空，最小4位；
// 3，重复验证密码和密码相同；
// 4，email可以为空；
// 5，年龄必须在18-60岁之间；
$(function () {
    $("#editForm").validate({
        rules: {
            "e.name": {required: true, minlength: 4},
            "e.password":{required: true, minlength: 4},
            "repassword":{equalTo:"#password"},
            "e.email":{email: true},
            "e.age":{range:[18,60],digits:true}
        },
       /* messages: {
            "e.name": {required:"姓名不能为空", minlength:"最小长度为4"},
            "e.password":{required:"密码不能为空", minlength:"最小长度为4"},
            "repassword":{equalTo:"重复密码须和密码相同"},
            "e.email":{email:"请按正确格式输入邮箱"},
            "e.age":{range:"年龄必须在18-60岁之间",digits:"年龄必须为整数"}
        }*/
    });
});
$(function(){

    $("#select").click(function(){
        $(".all_roles option:selected").appendTo($(".selected_role"));
    });
    $("#deselect").click(function(){
        $(".selected_role option:selected").appendTo($(".all_roles"));
    });
    $("#selectAll").click(function(){
        $(".all_roles option").appendTo($(".selected_role"));
    });
    $("#deselectAll").click(function(){
        $(".selected_role option").appendTo($(".all_roles"));
    });
    //提交事件,在提交前将选中框中的选项全部设置为选中
    $("#editForm").submit(function(){
        $(".selected_role option").prop("selected",true);
    });
});
//下拉框去重
$(function(){
    var ids = $.map($(".selected_role option"),function(item,index){
        return $(item).val();
    });
    $.each($(".all_roles option"),function(index,item){
        if($.inArray($(item).val(),ids)>=0){
            $(item).remove();
        }
    });
});
