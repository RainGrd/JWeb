<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String basePath1 = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + request.getContextPath() + "/";
%>
<div class="pt10"></div>

<!-- 资金交易流水表 -->
<div class="showDataListWrap1">
	<table id="fundBusinessStateTable" class="easyui-datagrid" data-options="
			url:'<%=basePath1 %>/bizFundBusinessStateController/selectAll.shtml',
			pagination:false,
			showFooter:false,
			fitColumns:true,
			sortOrder:'asc',
			remoteSort:false,
			title:'资金交易流水表'">
		<thead>   
	        <tr>   
	            <th data-options="field:'createTime',width:'100',align:'center',sortable:true,">日期</th>   
	            <th data-options="field:'borAmount',width:'100',align:'right',sortable:true,formatter:common.formatCurrency">贷款金额</th>   
	            <th data-options="field:'investAmount',width:'100',align:'right',sortable:true,formatter:common.formatCurrency">投资金额</th>   
	            <th data-options="field:'dueAmount',width:'100',align:'right',sortable:true,formatter:common.formatCurrency">待收金额</th>   
	            <th data-options="field:'receivedAmount',width:'100',align:'right',sortable:true,formatter:common.formatCurrency">已收金额</th>   
	            <th data-options="field:'customerIncome',width:'100',align:'right',sortable:true,formatter:common.formatCurrency">用户收益</th>   
	            <th data-options="field:'provideIncome',width:'100',align:'right',sortable:true,formatter:common.formatCurrency">发放收益</th>   
	        </tr>   
	    </thead>  
	</table>
</div>

<div class="clear"></div>


