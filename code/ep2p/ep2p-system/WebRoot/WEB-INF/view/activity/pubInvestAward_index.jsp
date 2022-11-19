<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/pubInvestAward_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/pubInvestAward_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right">投资奖励编号：</td>
							<td>
								<input class="easyui-textbox" name="actCode"/>
							</td>
							<td class="align_right">适用开始日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="beginApplyStartData" editable="false" />~
								<input class="easyui-datebox" name="endApplyStartData" editable="false" /> 
								</div>
							</td>
							
							<td></td>
						</tr>
						<tr>
							<td class="align_right">投资奖励名称：</td>
							<td>
								<input class="easyui-textbox" name="investAwardName" />
							</td>
							<td class="align_right">适用结束日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="beginApplyFinishData" editable="false" />~
								<input class="easyui-datebox" name="endApplyFinishData" editable="false" />
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="align_right">标签：</td>
							<td>
								<input class="easyui-textbox" name="actTag"/>
							</td>
							<td class="align_right">状态：</td>
							<td>
								<select class="easyui-combobox" name="status" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >启用</option>
									<option value=2 >禁用</option>
								</select>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="pubInvestAward.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="pubInvestAward.openAdd()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="pubInvestAward.remove()">删除</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="pubInvestAwardGrid" >
			</table>
		</div>
</body>