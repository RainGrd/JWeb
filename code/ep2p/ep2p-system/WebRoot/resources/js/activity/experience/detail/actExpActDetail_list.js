/**
 * js类
 */
var actExpActDetailList = {
	// 初始化赠送明细
	initDataGrid:function(url){
		// 初始化datagrid
		$('#actExpActDetailGrid_det').datagrid({    
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
		    columns:actExpActDetailGrid_det_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actExpActDetailGrid_det');
				if($("#actExpActDetail_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actExpActDetail_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#actExpActDetailGrid_det').datagrid("deleteRow",0);
				}
			}
		});
	},
	// 查询
	searchDataDetail:function(){
		var url = BASE_PATH + "actExpActDetailController/selectAllPageDetail.shtml";
		$('#actExpActDetailGrid_det').datagrid('options').url = url;
		jqueryUtil.ajaxSearchPage('#actExpActDetailGrid_det','#search');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#actExpActDetailGrid_det","#search",basePath + "actExpActDetailController/exportDownLoadFile.shtml");
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	var activityId = $("#activityId").val();
	// 获取明细数据的URL
	var url = BASE_PATH + "actExpActDetailController/selectAllPageDetail.shtml?activityId="+activityId;
	actExpActDetailList.initDataGrid(url);
});
