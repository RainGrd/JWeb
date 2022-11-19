
/**
 * vip生日特权js类
 */
var birPri_addOrUpdate = {
		// 保存
		save:function(){
			// URL 
			var url = BASE_PATH + "actActivityController/saveOrUpdatePir.shtml";
			// 验证表单必填项
			if(!$("#baseInfo").form('validate')){
				return ;
			}
			// 时间验证
			if($("#startDate").datetimebox("getValue") != "" && $("#endDate").datetimebox("getValue") != ""){
				if($("#startDate").datetimebox("getValue") > $("#endDate").datetimebox("getValue")){
					$.messager.alert("操作提示","适用结束日期必须大于适用开始日期");
					return;
				}
			}
			if($("#actName").val().trim().length <= 0){
				$.messager.alert("操作提示","特权名称项为必输项!");
				return;
			} 
			if($("#priLeftSelect").find("option").length <=0){
				$.messager.alert("操作提示","必须选择一项特权!");
				return;
			}
			// 检查各项输入问题  》》 1表示有生日特权那一个选项
			commonActivity.checkActivitypPri();
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
					if(result.message == 200){
					$.messager.alert('操作提示',"操作成功！",'success');  
					// 刷新页面
					reloadTabGrid("vip生日特权设置");
					// 关闭当前页面
					parent.$('#centerFrame').tabs('close',"新增/编辑VIP生日特权");
					
//					window.location.href = BASE_PATH
//							+ "actActivityController/toBirthdayPrivilegePage.shtml"; 
					}else{
						$.messager.alert('操作提示',"操作失败！",'error');  
					} 
				}
			});
		},
		// 返回
		revert:function(){
			// 刷新页面
			reloadTabGrid("vip生日特权列表");
			// 关闭当前页面
			parent.$('#centerFrame').tabs('close',"新增/编辑VIP生日特权");
//			window.location.href = BASE_PATH
//			+ "actActivityController/toBirthdayPrivilegePage.shtml"; 
			 
		},
		// 初始化VIP生日特权新增
		initBriPri_add:function(){
			$("#baseInfo").form("reset");
			commonActivity.generatNoRuleTem("TQ","7",4);
			// 初始化条件列表
			commonActivity.initCondition_add();
			//初始化特权列表
			commonActivity.initPrivilege_add();
		},
		// 初始化VIP编辑
		initDataGrid_edit:function(objectId,activityType){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"actActivityController/getActivityByPid.shtml?pid="+objectId,
				async : false,
				dataType : "json",
				success : function(data) {
					$("#baseInfo").form("reset");
			 		$("#baseInfo").form('load',data.actActivity);
				}
			}); 

			commonActivity.generatNoRuleTem("TQ","7",4);
			// 初始化条件列表
			commonActivity.initCondition_edit(objectId,activityType); 
			commonActivity.initCheckActivity(0);
			//commonActivity.initPrivilege_add();
		}
}

/**
 * 页面数据初始化
 */
$(function(){
	var pid = $("#baseInfo input[name=pid]").val();  
	// 判断是新增or编辑
	if(pid == -1){
		// 如果是新增  == -1
		birPri_addOrUpdate.initBriPri_add();
	}else{
		// 如果是编辑  == 其他
		birPri_addOrUpdate.initDataGrid_edit(pid,7);
	}
});