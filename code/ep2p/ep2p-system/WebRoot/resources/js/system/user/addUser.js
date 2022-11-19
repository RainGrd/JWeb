$(function(){
	userAdd.init();
});
var userAdd = {
	pid:"",	
	init:function(){
		userAdd.pid = $("#pid").val();		 
	},
//初始化数据
 loadData:function(){
	var pid = $("#pid").val();
	if("" != pid){
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"sysUserController/getByPid.shtml",
	    	data:{data:"{pid:"+pid+"}"},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		$("#baseInfo").form('load',data.result);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	}
}
};