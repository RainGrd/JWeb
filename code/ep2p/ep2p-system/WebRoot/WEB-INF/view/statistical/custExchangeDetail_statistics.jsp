<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/statistics/custExchangeDetail_statistics.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/statistics/model/custExchangeDetailStatistics_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right" style="width: 150px;">用户名：</td>
							<td style="width: 300px;">
								<input class="easyui-textbox" name="customerName"/>
							</td>
							<td class="align_right" style="width: 150px;">姓名：</td>
							<td>
								<input class="easyui-textbox" name="sname"/>
							</td>
						</tr>
						<tr>
							<td class="align_right">兑换时间：</td>
							<td  colspan="3">
								<input class="easyui-datebox" name="exchangeBeginTime"  editable="false" />~
								<input class="easyui-datebox" name="exchangeEndTime" editable="false" /> 
							</td>
						</tr>
						<tr>
							<td class="align_right">手机号码：</td>
							<td>
								<input class="easyui-textbox" name="phoneNo" />
							</td>
							<td colspan="2">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="custExchangeDetailStatistics.searchData()">统计</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" onclick="custExchangeDetailStatistics.toDownloadPage()">导出excel</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="custExchangeDetailStatisticsGrid" >
			</table>
		</div>
</body>