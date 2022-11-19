/**
 * js类
 */
var actInvWealthCoopDetail_list = {
		// 初始化赠送明细
		initDataGrid_det:function(url){
			// 初始化datagrid
			$('#actInvWealthCoopDetailGrid_det').datagrid({    
				url:url,
				width:'100%',
				fit:true,
				method: 'POST',
				toolbar:'#toolbar_actInvWealthCoopDetail',
				pagination: true,
			    rownumbers:true,
			    remoteSort:false,
			    fitColumns:true,
				selectOnCheck: true,
				singleSelect: false,
				checkOnSelect: true,
			    columns:actInvWealthCoopDetailGrid_det_model,
			    onLoadSuccess:function(data){
					 
				}
			});
		},
		//跳转至数据下载页面
		toDownloadPage:function(){
			var activityId = $("#activityId").val();
			$.comdownload("#actInvWealthCoopDetailGrid_det",null,
					basePath + "actInvWealthCoopDetailController/exportDownLoadFile.shtml?activityId="+activityId);
		}
	 
};

/**
 * 页面数据初始化
 */
$(function(){
	var activityId = $("#activityId").val();
	// 获取明细数据的URL
	var url = BASE_PATH + "actInvWealthCoopDetailController/selectAllPageDetail.shtml?activityId="+activityId;
	actInvWealthCoopDetail_list.initDataGrid_det(url);
});
