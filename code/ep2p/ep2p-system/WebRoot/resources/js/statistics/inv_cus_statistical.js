/**
 * 借款人区域分布
 */
// 参数对象信息
var InvCusStatistical = {
	// 初始化可选用户列表
	initInvCusStatisticsGroupProvince: function(orderByType) {
		InvCusStatistical.orderByType = orderByType;
		InvCusStatistical.provinceTitle = "省份投资金额排行";
		if(orderByType && orderByType == 2){
			InvCusStatistical.provinceTitle = '省份投资人数排行';
		}
		$('#inv_cus_statistical_province_grid').datagrid({
			url : basePath + 'cusReportController/selectInvCusStatisticsGroupProvince.shtml',
			height : '390',
			width : '500px',
			title : InvCusStatistical.provinceTitle,
			rownumbers : false,
			singleSelect:true,
			queryParams : {"orderByType" :orderByType},
			columns: Inv_cus_statistical_province_model,
			remoteSort : false,
			onClickRow : function(rowIndex, rowData) {
				var province = rowData.province;
				InvCusStatistical.initInvCusStatisticsGroupCity(InvCusStatistical.orderByType,province);
			},
			onLoadSuccess:function(data){
				var row0 = data.rows[0];
				var province = row0.province;
				InvCusStatistical.initInvCusStatisticsGroupCity(InvCusStatistical.orderByType,province);
			}
		});
	},
	// 初始化已选用户列表
	initInvCusStatisticsGroupCity : function(orderByType,province) {
		InvCusStatistical.cityTitle = province+"、城市投资金额排行";
		if(orderByType && orderByType == 2){
			InvCusStatistical.cityTitle = province+'、城市投资人数排行';
		}
		$('#inv_cus_statistical_city_grid').datagrid({
			url : basePath + 'cusReportController/selectInvCusStatisticsGroupCity.shtml',
			height : '390',
			width : '500px',
			title : InvCusStatistical.cityTitle,
			rownumbers : false,
			singleSelect:true,
			sortOrder : 'asc',
			queryParams : {"orderByType":orderByType, "province":province},
			remoteSort : false,
			columns : Inv_cus_statistical_city_model
		});
	}
};

//初始化
$(function() {
	InvCusStatistical.initInvCusStatisticsGroupProvince(1);
});