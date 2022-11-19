<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/finance/model/custFundWaterManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/custFundWater.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right">客户名：</td>
								<td><input class="easyui-textbox" name="customerName" id="customerName" /></td>
								<td class="label_right">姓名：</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
								<td class="label_right">手机号码：</td>
								<td><input class="easyui-textbox" name="phoneNo" id="phoneNo"/></td>
								<td></td>
							</tr>
							<tr>
								<td class="label_right">日期： </td>
								<td>
									<input class="easyui-datebox" id="happenTime" name="happenTime" />
								</td>
								<td class="label_right">流水类型：</td>
								<td>
									<input id="businessType" name="businessType" class="easyui-combobox"  
				            				data-options="loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=basePath%>sysDistionaryContentController/selectByDisctCode.shtml?dictCode=CUST_FUND_WATER_TYPE'" />
								</td>
								<td class="label_right">收入/支出：</td>
								<td>
									<input id="fundType" name="fundType" class="easyui-combobox" 
				            				data-options="loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=basePath%>sysDistionaryContentController/selectByDisctCode.shtml?dictCode=CUST_FUND_BUSINESS_TYPE'" />
								</td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="custFundWater.searchData()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="custFundWater.cleanData()">重置</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
				
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export',plain:'true'" onclick="custFundWater.toDownloadPage()">导出EXCEL</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="custFundWaterGrid">
			</table>
		</div>
	</div>
</body>