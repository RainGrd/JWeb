/**
 * 积分兑换js类
 */
var custExchangeDetailStatistics = {
	//初始化
	initDataGrid:function(){
		$('#custExchangeDetailStatisticsGrid').datagrid({    
			url:basePath + 'custExchangeDetailController/selectAllStatistics.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'积分兑换统计列表',
			toolbar:'#toolbar',
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: true,
			checkOnSelect: true,
		    columns:custExchangeDetailStatistics_Model,
		    onLoadSuccess:function(data){
				clearSelectRows('#custExchangeDetailStatisticsGrid');
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#custExchangeDetailStatisticsGrid','#searcm');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#custExchangeDetailStatisticsGrid","#searcm",basePath + "custExchangeDetailController/exportDownLoadFileStatistics.shtml");
	}
};

/**
 * 页面数据初始化
 */
$(function(){
	custExchangeDetailStatistics.initDataGrid();	
});
