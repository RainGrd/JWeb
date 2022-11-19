/**
 * 借款人区域分布
 */
// 参数对象信息
var BorCusStatistical = {
	// 初始化可选用户列表
	initBorCusStatisticsGroupProvince: function(orderByType) {
		BorCusStatistical.orderByType = orderByType;
		BorCusStatistical.provinceTitle = "省份借款金额排行";
		if(orderByType && orderByType == 2){
			BorCusStatistical.provinceTitle = '省份借款人数排行';
		}
		$('#bor_cus_statistical_province_grid').datagrid({
			url : basePath + 'cusReportController/selectBorCusStatisticsGroupProvince.shtml',
			height : '390',
			width : '500px',
			title : BorCusStatistical.provinceTitle,
			queryParams : {"orderByType" :orderByType},
			columns: Bor_cus_statistical_province_model,
			singleSelect:true,
			remoteSort : false,
			onClickRow : function(rowIndex, rowData) {
				var province = rowData.province;
				BorCusStatistical.initBorCusStatisticsGroupCity(BorCusStatistical.orderByType,province);
			},
			onLoadSuccess:function(data){
				var row0 = data.rows[0];
				var province = row0.province;
				BorCusStatistical.initBorCusStatisticsGroupCity(BorCusStatistical.orderByType,province);
			}
		});
	},
	// 初始化已选用户列表
	initBorCusStatisticsGroupCity : function(orderByType,province) {
		BorCusStatistical.cityTitle = province+"、城市借款金额排行";
		if(orderByType && orderByType == 2){
			BorCusStatistical.cityTitle = province+'、城市借款人数排行';
		}
		$('#bor_cus_statistical_city_grid').datagrid({
			url : basePath + 'cusReportController/selectBorCusStatisticsGroupCity.shtml',
			height : '390',
			width : '500px',
			title : BorCusStatistical.cityTitle,
			singleSelect:true,
			sortOrder : 'asc',
			queryParams : {"orderByType":orderByType, "province":province},
			remoteSort : false,
			columns : Bor_cus_statistical_city_model
		});
	}
};

//初始化
$(function() {
	BorCusStatistical.initBorCusStatisticsGroupProvince(1);
});