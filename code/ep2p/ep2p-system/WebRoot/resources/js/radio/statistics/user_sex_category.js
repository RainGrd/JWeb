/**
 * 电台用户性别分类统计
 */
//参数对象信息
var ProgramObject = {
		title:'',
		categories:[],
		yAxis:'',
		data:[]
}

//加载报表信息
function loadReport(obj){
	$.ajax({
		type: "POST",
    	url : BASE_PATH+"radioStatisticsController/selecUserSexTotal.shtml",
    	async:false, 
		dataType: "json",
	    success: function(data){
	    	if(data.message == 200 ){
	    		// 1 获取性别
				var nan = data.nan;
				var nv = data.nv;
				// 2 获取节目板块
				var bankuai = data.title;
	$('#detail_statisticUserSexResult').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '用户性别统计'
	        },
	        subtitle: {
	            text: ''
	        },
	        xAxis: {
	            categories: [
	                '专家讲座',
	                '金融知识',
	                '百姓理财'
	            ]
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '数量'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size: 10px;">{point.key}</span>',
	            pointFormat: '' + '',
	            footerFormat: '<table><tbody><tr><td style="padding: 0px;">{series.name}: </td><td style="padding: 0px;"><b>{point.y:.1f} </b></td></tr></tbody></table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column:{
	            	allowPointSelect: true,
	            	dataLabels: {
	                    enabled: true
	                },
	                enableMouseTracking: false,
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '男',
	            data: nan

	        }, {
	            name: '女',
	            data: nv

	        }]
	    });
	    	}
	    }
	});
}

//初始化
$(function(){
	loadReport();
});