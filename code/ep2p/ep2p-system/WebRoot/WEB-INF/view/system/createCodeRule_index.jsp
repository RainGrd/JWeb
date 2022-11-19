<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/createCodeRule_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/model/createCodeRule_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable">
						<tr>
							<td>编号前缀： </td>
							<td>
								<input class="easyui-textbox" name="rulePrefix" id="rulePrefix" />
							</td>
							<td>编号类型： </td>
							<td>
								<select class="easyui-combobox" name="ruleType" id="ruleType" panelHeight="auto" editable="false" >
									<option value=-1  selected="selected" >--请选择--</option>
									<option value=1 >系统管理</option>
									<option value=2 >财务管理</option>
									<option value=3 >统计管理</option>
									<option value=4 >论坛管理</option>
									<option value=5 >内容管理</option>
									<option value=6 >电台管理</option>
									<option value=7 >活动管理</option>
									<option value=8 >业务管理</option>
									<option value=9 >客户管理</option>
								</select>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="createCodeRule.searchData()">查询</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
		</div>
	
		
		<div class="showDataListWrap">
			<table id="createCodeRuleGrid" >
			</table>
		</div>
	</div>
</body>