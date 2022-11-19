<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<script type="text/javascript" src="${basePath}resources/js/report/borrow_report.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="repaymentDataReport"></div>
	</div>
	
	<script type="text/javascript">
		$(function () {
			// 获取数据
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"report/bizBorrowReportController/getRepaymentDataReport.shtml",
				dataType: "json",
				async:false,
			    success: function(data){
			    	if(data.message.status ==200){
			    		loadReport(data.result);
			    	}else{
			    		$.messager.alert("操作提示","还款数据统计报表数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		});
		
		// 加载报表
		function loadReport(data){
				for(var i =0 ;i<data.length;i++){
					data[i][1] = new Number(data[i][1]);
				}
			    $('#repaymentDataReport').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: '还款数据统计'
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
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '比例',
			            data: data
			        }]
			    });
		}
	</script>
</body>