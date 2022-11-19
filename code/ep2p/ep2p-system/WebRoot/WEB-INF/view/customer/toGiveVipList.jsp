<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/toGiveVipList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/toGiveVipListModel.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar" class="pt10 noPadding">
			<form action="" id="toGiveVipFrom">
<%-- 				<input type="hidden" id="pid" name="pid" value="${pid}"> --%>
				<%@ include file="../common/customer_details.jsp" %>
				<input type="hidden" id="vip_InfoPid" name="vipInfoPid" >
			   <table class="cus_table formTable percent90" >
			       <tr>
			   		<td class="align_right" width="105"><span class="cus_red">*</span>赠送VIP时长(天)：</td>
    				<td><input type="text"  class="text_style easyui-numberbox" id="largessValue" name="largessValue" data-options="required:true" /></td>
			    </tr>
			    <tr>
			   		<td class="align_right">备注：</td>
    				<td>
    					<textarea class="percent70" rows="2" cols="100" id="larVipWatDesc" name="larVipWatDesc" ></textarea>
    				</td>
			    </tr>
			     <tr>
				   	<td class="align_center" colspan="2">
				   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="giveVip.save();">保存</a>		
				
				   		 <input type="hidden" name="page" id="page" value="1">
						 <input type="hidden" name="rows" id="rows">
					</td>		 
			    </tr>
			   </table>
			</form>		
			<!-- 操作按钮 -->
			<div class="panel-header topBorder pt10">
				<div class="panel-title">VIP获得历史：</div>
				<div class="panel-tool">
					<a href="javascript:void(0)" class="panel-tool-collapse" onclick="foldDatagrid()"></a>
					<a href="javascript:void(0)" class="panel-tool-expand" onclick="openDatagrid()"></a>
				</div>
			</div>	
		</div>
		
		<div class="showDataListWrap">
			<table id="showCusGrids"></table>
		</div>
	</div> 
<script type="text/javascript">
	
	$(document).ready(function(){
		giveVip.loadData();
		
		giveVip.initDataGrid();
	});
</script>
	
</body>