/**
 * 用户量统计报表信息
 */
//参数对象信息
var usercountObject = {
		title:'',
		type:'line',//line column
		categories:[],
		yAxis:'',
		data:[],
		//初始化数据信息
		initData:function(){
			var po = usercountObject;
			//设置头部信息
			var date = new Date();
			var datetime = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
			$('#usercountd').datebox('setValue', datetime);
			reportPost(datetime,'line');
		}
};

function lineSearch(){
	var datet = $('#usercountd').datebox('getValue');
	reportPost(datet,"line");
}

function columnSearch(){
	var datet = $('#usercountd').datebox('getValue');
	reportPost(datet,"column");
}

//一部加载数据信息
function reportPost(days,type) {
	$.post(basePath + "radioStatisticsController/getUserCountData.shtml", {
		"timedata" : days
	}, function(data) {
		if (data.code == "200") {
			var initd = usercountObject;
			initd.type = type;
			initd.data = data.datas;
			initd.title = REPORT_CONSTANT.TITLE_003;
			initd.categories = data.categories;
			initd.yAxis = REPORT_CONSTANT.TITLE_004;
			loadReport(initd);
		} else {
			$.messager.alert('操作提示', "保存失败,请联系管理员！", 'error');
		}
	}, "json");

}

// 加载报表信息
function loadReport(obj){
	$('#users_statisticResult').highcharts({
		chart: {
            type: obj.type
        },
        title: {
            text: obj.title
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
        	headerFormat: '<span style="font-size:10px">时间：{point.key}</span>',
            pointFormat: '' + '',
            footerFormat: '<table><tbody><tr><td style="color:{series.color};padding:0">{series.name}: </td><td style="padding:0"><b>{point.y}</b></td></tr></tbody></table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            },
        	column:{
        		dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
        	}
        },
        series:obj.data
    });
}

//初始化
$(function(){
	usercountObject.initData();
});