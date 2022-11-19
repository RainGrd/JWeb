<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/borrowCusTomeManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px" > 
						<table class="beforeloanTable"  width="90%">
							<tr>
								<td class="label_right"> 客户名：</td>
								<td><input class="easyui-validatebox" name="customerName" id="customerName" /></td>
								<td class="label_right"> 姓名：</td>
								<td><input class="easyui-validatebox" name="sname" id="sname" /></td>
							
								<td class="label_right"> 手机号码：</td>
								<td><input class="easyui-validatebox" name="phoneNo" id="phoneNo" /></td>
								<td class="label_right"></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrow.searchCusTomer()">查询</a> </td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
		</div>
	
		<div class="showDataListWrap">
			<table id="cusTomerGrid"></table>
		</div>
		
		<div id="addBorrow" class ="easyui-dialog" title="借款类型" modal="true" closed="true" style="width:500px;height:280px">
			<div class="p10">
				<table  width="100%" height="220">				
					<tr>
						<td align="center" width="145"><a href="#" class="borrowtype" onclick="javascript:borrow.toAdd(1)">e抵押</a></td>
						<td>
							<div class="borrowBar">借款类型</div>
						</td>
						<td align="center" width="145"><a href="#" class="borrowtype" onclick="javascript:borrow.toAdd(2)">e首房</a></td>					
					</tr>
				</table>
				<input type="hidden" id="customerId" name="customerId">
			</div>
		</div>
	</div>
	
	<jsp:include page="/common/ipPv.jsp">
	    <jsp:param  name="path" value="business/borrow/borrow_cus_list.jsp" />
	</jsp:include>
	<script type="text/javascript">
		$(function(){
			borrow.initCusTomerDataGrid();
		})
	</script>
</body>