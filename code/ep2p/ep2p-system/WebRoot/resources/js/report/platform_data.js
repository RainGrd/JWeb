/**
 * 平台数据
 */
var platformReport = {
		title_01:"累计投资",
		title_02:"已还本金",
		title_03:"累计用户收益",
		title_04:"已发放收益",
		axisv:["1月","2月",'3月','4月','5月','6月','7月','8月','9月','10月','11月','12月','13月'],
		//初始化信息信息
		initpage:function(){
			var tdate = new Date();
			var year = tdate.getFullYear();
			$("#year").combobox("setValue",year);
			platformReport.postReport('1',year,platformReport.title_01);
		},
		//点击Panel事件
		bindOnclick:function(o,type){
			//设置选中样式
			$(".platform_panle").removeClass("platform_panle_hov");
			$(o).addClass("platform_panle_hov");
			//设置初始值
			$("#def_check_val").val(type);
			//进行搜索
			platformReport.searchPlant();
		},
		//进行搜索值
		searchPlant:function(){
			var ctype = $("#def_check_val").val();
			var cyear = $("#year").combobox("getValue");
			var ctitle = "";
			if('1' == ctype){
				ctitle = platformReport.title_01;
			}
			if('2' == ctype){
				ctitle = platformReport.title_02;
			}
			if('3' == ctype){
				ctitle = platformReport.title_03;
			}
			if('4' == ctype){
				ctitle = platformReport.title_04;
			}
			//进行必要条件判断提示
			if(ctype != undefined && cyear != undefined 
					&& ctype != "" && cyear != ""){
				platformReport.postReport(ctype,cyear,ctitle);
			}else{
				$.messager.alert('操作提示', "查询参数错误", 'error');
			}
		},
		//请求数据信息
		postReport:function(vtype,vyear,name){
			$.post(basePath + "report/splatReportController/getPlatLoadReport.shtml",{
				type:vtype,
				year:vyear
			},function(data){
				if (data.code == "200") {
					//进行数据处理
					var ymoney = platformReport.formatCurrency(data.amoney+'');
					if('1' == vtype){
						$("span#amc_show_title").html("年度累计投资：" + ymoney);
					}
					if('2' == vtype){
						$("span#amc_show_title").html("年度已还本息总计：" + ymoney);
					}
					if('3' == vtype){
						$("span#amc_show_title").html("年度累计用户收益总计：" + ymoney);
					}
					if('4' == vtype){
						$("span#amc_show_title").html("年度已发放收益总计：" + ymoney);
					}
					platformReport.loadReport(data.datas,name);
				} else {
					$.messager.alert('操作提示', "保存失败,请联系管理员！", 'error');
				}
			},'json');
		},
		//加载报表
		loadReport:function(data,seriesn){
			$('#amount_money_view').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: ' '
		        },
		        xAxis: {
		            categories: platformReport.axisv
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
			        			return this.y.toFixed(2) + ' 元';
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
		},
		//将数值四舍五入(保留2位小数)后格式化成金额形式
		formatCurrency:function(num) {  
		    num = num.toString().replace(/\$|\,/g,'');  
		    if(isNaN(num))  
		        num = "0";  
		    sign = (num == (num = Math.abs(num)));  
		    num = Math.floor(num*100+0.50000000001);  
		    cents = num%100;  
		    num = Math.floor(num/100).toString();  
		    if(cents<10)  
		    cents = "0" + cents;  
		    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
		    num = num.substring(0,num.length-(4*i+3))+','+  
		    num.substring(num.length-(4*i+3));  
		    return (((sign)?'':'-') + num + '.' + cents);  
		},
		//去掉空字符
		removeloadFilter:function(datas){
			var data = datas.data;
			for(var i = 0;i<data.length;i++ ){
				if(data[i].dictContName = '-- 请选择 --'){
					data.splice(i,1);
					break;
				}
			}
			return data;		
		}
		
};


//初始化年份
$(function(){
	platformReport.initpage();
});