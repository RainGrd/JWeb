/**
 * 备用金总览
 */
var golddeposit = {
		Axisx:["1月","2月",'3月','4月','5月','6月','7月','8月','9月','10月','11月','12月','13月'],
		serarname:"备付金",
		initGoldReport:function(){
			var tdate = new Date();
			var year = tdate.getFullYear();
			$("#year").val(year);
			golddeposit.postGoldReport(year,golddeposit.serarname);
		},
		//点击搜索框
		onclickReport:function(){
			var getyear = $("#year").val();
			if("" != getyear){
				golddeposit.postGoldReport(getyear,golddeposit.serarname);
			}else{
				$.messager.alert('操作提示', "查询参数错误", 'error');
			}
		},
		//请求数据信息
		postGoldReport:function(vyear,name){
			$.post(basePath + "report/splatReportController/getGoldDepositReport.shtml",{
				year:vyear
			},function(data){
				if (data.code == "200") {
					golddeposit.loadGoldReport(data.datas,name);
				} else {
					$.messager.alert('操作提示', "保存失败,请联系管理员！", 'error');
				}
			},'json');
		},
		//加载报表
		loadGoldReport:function(data,seriesn){
			$('#rep_golddeposit_preview').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: ' '
		        },
		        xAxis: {
		            categories: golddeposit.Axisx
		        },
		        yAxis:{
		        	min: 0,
		            title: {
		                text: '金额'
		            }
		        },
		        colors:['#F08080'],
		        tooltip: {
		            pointFormat: seriesn + ':{point.y} 元',
		        },
		        plotOptions: {
		        	column: {
		                dataLabels: {
		                    enabled: true,
		                    formatter: function () {
			        			return this.y + ' 元';
			        		}
		                },
		                enableMouseTracking: true
		            }
		        },
		        series: [{
		            name: seriesn,
		            data: data
		        }]
		    });
		}
};

//初始化
$(function(){
	golddeposit.initGoldReport();
});