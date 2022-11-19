/**
 * 客户注册 与 实名制认证  趋势图
 */
//参数对象信息
var CusObject = {
		title:'',
		categories:[],
		yAxis:'',
		data:[],
		//初始化数据信息
		initData:function(){
			var po = CusObject;
			//设置头部信息
			var date = new Date();
			var fully = date.getFullYear();
			var ppid = $("#prgpid").val();
			CusObject.reportPost('1',fully);
		},
		selectType:function(record){
			$('.typeItem').addClass('none');
			$('.timeType'+record.value).removeClass('none');
		},
		
		selectTypeInfo:function(record){
			$('.typeItem_info').addClass('none');
			$('.timeType_info'+record.value).removeClass('none');
		},
		//单行收索
		serarLineReport:function(){
			var type = $('#system_search_from #timeType_info').combobox('getValue');
			if('1' == type){
				var year = $('#system_search_from #year2').combobox('getValue');
				if(year != undefined && year != "" ){
					CusObject.reportPost('1',year);
				}else{
					$.messager.alert('操作提示', "时间没有选择，请选择", 'error');
				}
			}
			if("2" == type){
				var year = $('#system_search_from #year3').combobox('getValue');
				var month = $('#system_search_from #month1').combobox('getValue');
				if(year != undefined && year != "" && month != undefined && month != ""){
					var ym = year + "-" + (month < 10 ? '0' + month : month);
					CusObject.reportPost('2',ym);
				}else{
					$.messager.alert('操作提示', "时间没有选择，请选择", 'error');
				}
			}
		},
		//一部加载数据信息
		reportPost:function(type, param) {
			$.post(basePath + "cusReportController/userStatistical.shtml", {
				"timeType" : type,
				"param" : param
			}, function(res) {
				if (res.message.status == "200") {
					var initd = CusObject;
					initd.data = res.data.datas;
					var years = param + "";
					//按时间统计用户总数
					var userCount = res.data.userCount;
					//按时间统计实名认证总数
					var attestationCount = res.data.attestationCount;
					initd.title = years + CUS_REPORT.TITLE_001+"<br/>新增用户总数:<span >"+userCount+"&nbsp;&nbsp;</span>  新增实名认证用户总数:"+attestationCount;
					initd.categories = res.data.categories;
					initd.yAxis = REPORT_CONSTANT.TITLE_002;
					CusObject.loadReport(initd);
				} else {
					$.messager.alert('操作提示', "保存失败,请联系管理员！", 'error');
				}
			}, "json");

		},
		// 加载报表信息
		loadReport:function(obj){
			$('#system_detail_statisticResult').highcharts({
				chart: {
		            type: 'line'
		        },
		        title: {
		            text: obj.title,
		            useHTML:true
		        },
		        subtitle: {
		            text: ''
		        },
		        xAxis: {
		            categories:obj.categories
		        },
		        yAxis: {
		            title: {
		                text: obj.yAxis
		            }
		        },
		        tooltip: {
		            enabled: false,
		            formatter: function() {
		                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'';
		            }
		        },
		        plotOptions: {
		            line: {
		                dataLabels: {
		                    enabled: true
		                },
		                enableMouseTracking: false
		            }
		        },
		        series:obj.data
		    });
		}
};







//初始化
$(function(){
	CusObject.initData();
});