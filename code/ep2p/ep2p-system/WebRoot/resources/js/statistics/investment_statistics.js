/**
 * 投资人统计图js
 */
var investment_statistics = {
		// 初始化
		initloadchart:function(type){
			$.post(basePath + "cusReportController/selectInvestmentStatisticsByCondition.shtml?type="+type,{},function(data){
				if (data.message.status == "200") {
			        $('#investment_statisticResult').highcharts({
			            chart: {
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: false
			            },
			            title: {
			                text: type == 1 ? REPORT_CONSTANT.TITLE_007 : REPORT_CONSTANT.TITLE_008 
			            },
			            tooltip: {
			                pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
			            },
			            plotOptions: {
			            	 pie: {
				                allowPointSelect: true,
				                cursor: 'pointer',
				                dataLabels: {
				                	enabled: true,
					                formatter: function() {
					                	if (this.percentage > 0){
					                		return '<b>' + this.point.name + '</b>: ' + this.point.y;
					                	}
					                },
				                    color: '#000000',
				                    connectorColor: '#000000'
				                },
				                showInLegend: true
			                }
			            },
			            series: [{
				            type: 'pie',
				            name: type == 1 ? REPORT_CONSTANT.TITLE_007 : REPORT_CONSTANT.TITLE_008 ,
				            data: data.datas
				        }]
			        });
				} else {
					$.messager.alert('操作提示', "统计数据失败");
				}
			},"json");
		},
		// 单击统计改变
		singleClickStatistics:function(){
			var type = $("#statisticType").combobox("getValue");
			investment_statistics.initloadchart(type);
		}
};


//界面初始化信息
$(function(){
	investment_statistics.initloadchart(1);
});