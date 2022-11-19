/**
*首页js
*
*/

var welcome = {
		// 初始化方法
		init:function(){
			//加载近七日新增客户数据
			welcome.loadAddCustomer();
			// 加载IP,pv 访问量数据
			welcome.loadIPPV();
		},
		// 加载近七日新增客户数据
		loadAddCustomer:function(){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"customerController/selectNearlySevenNewCustomers.shtml",
		    	async:false, 
				dataType: "json",
			    success: function(data){
			    	if(data.message == 200 ){
			    		// 1 获取近七日日期
						var day = data.days;
						// 2 获取近七日新增客户数据
						var newCustomer = data.result;
						// 3 报表加载
					    $('#container').highcharts({
					        chart: {
					            type: 'line'
					        },
					        title: {
					            text: '近7日新增客户',
					            align:'left'
					        },
					        xAxis: {
					            categories:day
					        },
					        yAxis: {
					            title: {
					                text: '数量'
					            }
					        },
					        tooltip: {
					            enabled: false,
					            formatter: function() {
					                return '<b>'+ this.series.name +'</b><br>'+this.x +': '+ this.y +'°C';
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
					        series: [{
					            name: '新增客户',
					            color:'#408cd8',
					            data: newCustomer
					        }]
					    });
			    	}else{
			    		$.messager.alert('操作提示',"发布失败,请联系管理员！",'error');
			    	}
				}
			});
		},
		// 加载IP,pv 访问量数据
		loadIPPV:function(){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"sysIpPvCountController/selectNearlySevenData.shtml",
		    	async:false, 
				dataType: "json",
			    success: function(data){
			    	if(data.message == 200 ){
			    		// 1 获取近七日日期
						var day = data.days;
						
						// 2 获取近七日访问量数据
						var ipCount = data.ipCount;
						var pvCount = data.pvCount;
						
					    $('#ipPv').highcharts({
					        chart: {
					            type: 'column'
					        },
					        title: {
					            text: '访问量报表',
					            align:'left'
					        },
					        xAxis: {
					            categories: day,
					            crosshair: true
					        },
					        yAxis: {
					            min: 0,
					            title: {
					                text: '数量'
					            }
					        },
					        tooltip: {
					            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
					            footerFormat: '</table>',
					            shared: true,
					            useHTML: true
					        },
					        plotOptions: {
					            column: {
					                pointPadding: 0,
					                borderWidth: 0
					            }
					        },
					        series: [{
					            name: 'IP访问',
					            color: '#f39c12',
					            data: ipCount
			
					        }, {
					            name: 'PV浏览',
					            color: '#2980b9',
					            data: pvCount
			
					        }]
					    });
			    	}
			    }
			});
		}
}