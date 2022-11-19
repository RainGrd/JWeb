<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<br>
		<div style="font-size:16px;"> 总标数：<span id="totalCount" style="font-weight: 700"></span> 笔</div>
			<br>
		<div style="font-size:16px;"> 满标数：<span id="fullScale" style="font-weight: 700"></span> 笔</div>
			<br>
		<div style="font-size:16px;"> 流标数：<span id="flowMark" style="font-weight: 700"></span> 笔</div>
			<br>
		<div style="font-size:16px;"> 撤销数：<span id="revoke" style="font-weight: 700"></span> 笔</div>
		<div id="repaymentDataReport"></div>
		
	</div>
	
	<script type="text/javascript">
		$(function () {
			// 获取数据
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"report/bizBorrowReportController/findByBorrowCountData.shtml",
				dataType: "json",
				async:false,
			    success: function(data){
			    	if(data.message.status ==200){
			    		loadData(data);
			    		loadReport(data);
			    	}else{
			    		$.messager.alert("操作提示","借款项目数量占比加载失败,请联系管理员!","error");
			    	}
				}
			});
		});
		
		
		function loadData(data){
			$("#totalCount").text(data.totalCount);
			$("#fullScale").text(data.fullScale);
			$("#flowMark").text(data.flowMark);
			$("#revoke").text(data.revoke);
		}
		
		// 加载报表
		function loadReport(data){
				var totalCount = data.totalCount;
				var fullScale = data.fullScale;
				var flowMark = data.flowMark;
				var revoke = data.revoke;
				
				var fullScaleRate=0;
				var flowMarkRate=0;
				var revokeRate=0;
				if(totalCount != 0){
					fullScaleRate = new Number((fullScale/totalCount).toFixed(4));
					flowMarkRate = new Number((flowMark/totalCount).toFixed(4));
					revokeRate =  new Number((revoke/totalCount).toFixed(4));
				}
				
				
				var data = [
			                ['流标',   flowMarkRate],
			                {
			                    name: '满标',
			                    y: fullScaleRate,
			                    sliced: true,
			                    selected: true
			                },
			                ['撤销',   revokeRate]
			            ];
				
			    $('#repaymentDataReport').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: '借款数量占比'
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