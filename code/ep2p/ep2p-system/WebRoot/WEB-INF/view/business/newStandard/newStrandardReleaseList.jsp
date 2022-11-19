<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right">编号：</td>
								<td><input class="easyui-validatebox" name="borrowCode" id="borrowCode" /></td>
								<td class="align_right">名称：</td>
								<td><input class="easyui-validatebox" name="borrowName" id="borrowName"/></td>
							</tr>
							<tr>
								<td class="align_right">标的金额：</td>
								<td>
									<input class="easyui-validatebox" id="borrowMoneyMin" name="borrowMoneyMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowMoneyMax" name="borrowMoneyMax"  style="width: 80px;"/>
								</td>
								<td class="align_right">标签：</td>
								<td>
									<input class="easyui-validatebox" name="borrowTag" id="borrowTag"/>
								</td>
							</tr>
							<tr>
								<td class="align_right">期限范围：</td>
								<td>
									<input class="easyui-validatebox" id="borDeadlineMin" name="borDeadlineMin" style="width: 80px;" />~
									<input class="easyui-validatebox" id="borDeadlineMax" name="borDeadlineMax" style="width: 80px;" />
								</td>
								<td class="align_right">利率范围</td>
								<td>
									<input class="easyui-validatebox" id="borrowRateMin" name="borrowRateMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowRateMax" name="borrowRateMax" style="width: 80px;" />
								</td>
							</tr>
							<tr>
								<td class="align_right">标的类型：</td>
								<td>
									<input name="newStandardType" editable="false" id="newStandardType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														onLoadSuccess:setDefault,
														url:'<%=BID_TYPE %>'" />
														
								</td>
								<td class="align_right">还款方式：</td>
								<td>
									<input name="repaymentType" editable="false" id="repaymentType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=REPAYMENT_TYPE %>'" />
								</td>
							</tr>
							<tr>
								<td class="align_right"></td>
								<td>
									
								</td>
								<td class="align_right"></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="newStandard.searchCusTomer()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="newStandard.resetForm()">重置</a>
								</td>
							</tr>
						</table>							
					</div>
				</form>
			</div>	
		</div>
		
		<div class="showDataListWrap">
			<table id="new_standard_list"></table>
		</div>
		<script type="text/javascript" src="${basePath}resources/js/business/borrow/newStandard.js" charset="utf-8"></script>
		<script type="text/javascript" src="${basePath}resources/js/business/model/newStandardRelease_Model.js" charset="utf-8"></script>
		<script type="text/javascript">
			var setDefault = function () { //加载完成后,设置选中第一项
	            var val = $(this).combobox("getData");
	           if(val.length>0){
	               $(this).combobox("select", val[0].dictContCode);
	               newStandard.initDataGridRelease(val[0].dictContCode);	
	            }
	        
	        }
		</script>
	</div>
</body>