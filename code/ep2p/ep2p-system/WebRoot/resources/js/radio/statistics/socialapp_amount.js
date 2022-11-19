/**
 * 社交软件转发统计图
 */

var socialapp_amount = {
	initloadchart:function(){
		$.post(basePath + "radioStatisticsController/getSocialAppAmount.shtml",{},function(data){
			if (data.code == "200") {
				$('#socialapp_statisticResult').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: REPORT_CONSTANT.TITLE_006
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    color: '#000000',
			                    connectorColor: '#000000',
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
			                },
			                showInLegend: true
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: REPORT_CONSTANT.TITLE_005,
			            data: data.datas
			        }]
			    });
			} else {
				$.messager.alert('操作提示', "保存失败,请联系管理员！", 'error');
			}
		},"json");
	}		
};


//界面初始化信息
$(function(){
	socialapp_amount.initloadchart();
});