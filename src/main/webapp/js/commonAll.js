jQuery.ajaxSettings.traditional = true;

/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
});
//分页相关
$(function () {
		//删除
		$("[name='delete']").click(function () {
			var url = $(this).data("url");
			$.dialog({
				title: "温馨提示",
				content: "确定要删除数据吗?",
				ok: function () {
					$.get(url, function (data) {
						$.dialog({
							title: "温馨提示",
							content: data,
							ok: function () {
								window.location.reload();
							}
						});
					});
				},
				cancel: true
			});
		});
	//新增
	$(".btn_input").click(function () {
		var url = $(this).data("url");
		window.location.href = url;
	});
	//翻页
	$(".btn_page").click(function () {
		var pageNo = $(this).data("page") || $("[name='qo.currentPage']").val();
		$("[name='qo.currentPage']").val(pageNo);
		$("#searchForm").submit();
	});
	//页面大小
	$(".ui_select02").change(function () {
		$("#searchForm").submit();
	});
	//批量删除
	$("#all").click(function(){
		$(".acb").prop("checked",$(this).prop("checked"));
	});
	$(".btn_batchDelete").click(function(){
		var url = $(this).data("url");
		if($(".acb:checked").size()==0){
			$.dialog({
				title:"温馨提示",
				content:"请选择要删除的数据",
				ok:true
			});
			return;
		}
		$.dialog({
			title:"温馨提示",
			content:"确定要删除数据吗?",
			ok:function(){
				var ids = $.map($(".acb:checked"),function(ele){
					return $(ele).data("oid");
				});
				$.post(url,{
					ids:ids
				},function(data){
					$.dialog({
						title:"温馨提示",
						content:data,
						ok:function(){window.location.reload();}
					});
				});
			},
			cancel:true
		});
	});

	//审核
	$("[name=audit]").click(function(){
		var url = $(this).data("url");
		$.dialog({
			title:"温馨提示",
			content:"亲,确定要进行审核操作吗?",
			ok:function(){
				$.post(url,function(data){
					$.dialog({
						title:"温馨提示",
						content:data,
						ok:function(){
							window.location.reload();
						}
					});
				});
			},
			cancel:true
		});
	});
});