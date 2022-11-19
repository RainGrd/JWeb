/**
 * 投资人统计图js
 */
var investment_statistics_terminal = {
		// 初始化
		initloadchart:function(type,year,month,title){
			$.post(basePath + "cusReportController/selectSysIpPvHisStatistics.shtml",{type:type,year:year,month:month},function(data){
				if (data.message.status == "200") {
			        $('#investment_statistic_terminalResult').highcharts({
			            chart: {
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: false
			            },
			            title: {
			                text: title
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
				            name: title,
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
			var year = "";
			var month = "";
			var title = "";
			if(type == 1){
				year = $('#year').val();
				title = year+"年使用终端占比例图";
			}else if(type == 2){
				year = $('#year').val();
				month = $("#month").combobox("getValue");
				title = year+"年"+month+"月使用终端占比例图";
			}else {
				title = "使用终端占比例图";
			}
			investment_statistics_terminal.initloadchart(type,year,month,title);
		},
		// 选择项改变时
		selectType:function(record){
			// 判断选择的是什么类型
			if(record.value == 1){
				$('#yearDiv').removeClass('none');
				$('#monthDiv').addClass('none');
				$('#year').val(new Date().getFullYear());// 赋值当前年份
			}else if(record.value == 2){
				$('#yearDiv').removeClass('none');
				$('#year').val(new Date().getFullYear());// 赋值当前年份
				$('#monthDiv').removeClass('none');
			}else{
				$('#yearDiv').addClass('none');
				$('#monthDiv').addClass('none');
			}

		}
};


//界面初始化信息
$(function(){
	investment_statistics_terminal.initloadchart(0,0,0,'使用终端占比例图');
});