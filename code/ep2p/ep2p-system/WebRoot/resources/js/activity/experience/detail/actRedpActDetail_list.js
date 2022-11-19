/**
 * 编号生成规则内容js类
 */
var actRedpActDetailList = {
	// 初始化赠送明细
	initDataGrid:function(url){
		// 初始化datagrid
		$('#actRedpActDetailGrid_det').datagrid({    
			url:url,
			width:'100%',
			fit:true,
			method: 'POST',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actRedpActDetailGrid_det_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actRedpActDetailGrid_det');
				if($("#actRedpActDetail_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actRedpActDetail_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#actRedpActDetailGrid_det').datagrid("deleteRow",0);
				}
			}
		});
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#actRedpActDetailGrid_det","#search",basePath + "actRedpActDetailController/exportDownLoadFile.shtml");
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	
	var activityId = $("#activityId").val();
	var url = BASE_PATH + "actRedpActDetailController/selectAllPageDetail.shtml?activityId="+activityId;
	actRedpActDetailList.initDataGrid(url);
	
});
