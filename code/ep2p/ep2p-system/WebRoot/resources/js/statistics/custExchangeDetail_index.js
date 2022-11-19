/**
 * 积分兑换js类
 */
var custExchangeDetail = {
	//初始化
	initDataGrid:function(){
		$('#custExchangeDetailGrid').datagrid({    
			url:basePath + 'custExchangeDetailController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'积分兑换列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:custExchangeDetail_Model,
		    onLoadSuccess:function(data){
				clearSelectRows('#custExchangeDetailGrid');
				if( data.total > 0){
					// 设计总计表头
					$("#custExchangeDetailGrid .datagrid-cell-rownumber:last").html("");
				}else{
					$('#custExchangeDetailGrid').datagrid("deleteRow",0);
				}
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#custExchangeDetailGrid','#searcm');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#custExchangeDetailGrid","#searcm",basePath + "custExchangeDetailController/exportDownLoadFile.shtml");
	}
};

/**
 * 页面数据初始化
 */
$(function(){
	custExchangeDetail.initDataGrid();	
});
