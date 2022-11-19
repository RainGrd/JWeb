<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/bizEnsureMoney/toList.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/model/bizEnsureMoney/toList_model.js" charset="utf-8"></script>

	<style type="text/css">
		.set_value_table_class td a{margin-left:20px}
	</style>
	<div data-options="region:'center',border:false">
		<div id="biz_ensure_update_toolbar" class="easyui-panel" title="设定信息">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="biz_ensure_update_form" name="biz_ensure_update_form">
					<div class="p5">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right" >备付金垫付罚息利率：</td>
								<td><input class="easyui-numberbox" precision="2" groupSeparator="," required="true"  name="bizEnsureCapitalPenaltyRate" id="bizEnsureCapitalPenaltyRate" />&nbsp;%&nbsp;日</td>
									
								<td class="label_right" style="color:#FF9900">B级风险预警：</td>
								<td><input class="easyui-textbox" name="bizEnsureBRiskWarn" id="bizEnsureBRiskWarn" />&nbsp;&nbsp;备付金小于等于设定数量时，系统提示预警</td>
							</tr>
							<tr>
								<td class="label_right">备付金管理费收取比例：</td>
								<td><input class="easyui-numberbox" precision="2" groupSeparator="," required="true"  name="bizEnsureManageRate" id="bizEnsureManageRate" />&nbsp;%</td>
									
								<td class="label_right" style="color:#FF0000">A级风险预警：</td>
								<td><input class="easyui-textbox" name="bizEnsureARiskWarn" id="bizEnsureARiskWarn" />&nbsp;&nbsp;备付金小于等于设定数量时，系统提示预警</td>
							</tr>
							<tr>
								<td class="label_right">备付金活期利率：</td>
								<td><input class="easyui-numberbox" precision="2" groupSeparator=","  name="bizEnsureRateOfCall" id="bizEnsureRateOfCall" />&nbsp;&nbsp;%日</td>
							</tr>
							<tr>
								<td>
								</td>
							</tr>
						</table>
							<a href="javascript:void(0)" style="margin-left:40%" class="easyui-linkbutton"
								data-options="iconCls:'icon-save'"
								onclick="BizEnsureIndexGrid.saveBizEnsureParamClick()">保存</a>
					</div>
				</form>
			</div>
			<!-- 操作按钮 -->
		</div>

		<p style="margin-bottom:10px"></p>
		<div data-options="region:'center',border:false">
		<div id="biz_ensure_index_toolbar" class="easyui-panel" title="备付金设置">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="biz_ensure_list_form" name="biz_ensure_list_form">
					<div class="p5">
						<table class="beforeloanTable formTable">
							<tr>
								<td class="align_right" width="100">日期：</td>
								<td>	
									<input class="easyui-datebox" name="startDate" editable="false" />~
									<input class="easyui-datebox" name="endDate" editable="false" /> 
								</td>							
								<td class="label_right">类型：</td>
								<td >
									<input id="ensMonDetType" name="ensMonDetType" class="easyui-combobox"
										 panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 multiple:false,
	           							 url:'<%=ENS_MON_DET_TYPE%>'" />
								</td>								
								<td>
									<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'"
									onclick="BizEnsureIndexGrid.searchData()">查询</a>
								</td>
							</tr>
							
						</table>
					</div>
				</form>
			</div>
			<!-- 操作按钮 -->
			<a href="javascript:void(0)" plain="true" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="BizEnsureIndexGrid.initBizEnsure(2007);" id="initBut" >设置初始备付金</a>
			<a href="javascript:void(0)" plain="true" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="BizEnsureIndexGrid.initBizEnsure(2010);">调减备付金</a>
			<a href="javascript:void(0)" plain="true" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="BizEnsureIndexGrid.initBizEnsure(2009);">调增备付金</a>
		</div>
		<div class="showDataListWrap" id="bizEnsureIndexGridDiv">
			<table id="bizEnsureIndexGrid" >
			</table>
		</div>
	</div>
	</div>
</body>
	<script type="text/javascript">
		 $(document).ready(function(){
			 BizEnsureIndexGrid.init();	
		}); 
	</script>