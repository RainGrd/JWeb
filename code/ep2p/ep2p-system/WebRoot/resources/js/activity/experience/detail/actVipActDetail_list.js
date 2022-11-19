/**
 * 编号生成规则内容js类
 */
var actVipActDetailList = {
	// 初始化赠送明细
	initDataGrid:function(url){
		// 初始化datagrid
		$('#actVipActDetailGrid_det').datagrid({    
			url:url,
			width:'100%',
			method: 'POST',
			toolbar:'#toolbar',
			fit:true,
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actVipActDetailGrid_det_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actVipActDetailGrid_det');
			}
		});
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#actVipActDetailGrid_det","#actVipActDetailForm",basePath + "actVipActDetailController/exportDownLoadFile.shtml");
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	
	var activityId = $("#activityId").val();
	var url = BASE_PATH + "actVipActDetailController/selectAllPageDetail.shtml?activityId="+activityId;
	actVipActDetailList.initDataGrid(url);
	
});
