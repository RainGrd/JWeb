<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/cusBirWarnHis_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/cusBirWarnHis_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/common/commonCustomer.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right">用户名：</td>
							<td>
								<input class="easyui-textbox" name="customerName"/>
							</td>
							<td class="align_right">姓名：</td>
							<td colspan="2">
								<input class="easyui-textbox" name="sname"/>
							</td>
						</tr>
						<tr>
							<td class="align_right">手机号码：</td>
							<td>
								<input class="easyui-textbox" name="phoneNo" />
							</td>
							<td class="align_right">客服：</td>
							<td colspan="2">
							    <input name="customerServiceUser" class="easyui-combobox" panelHeight="auto" editable="false"
									 data-options="loadFilter:common.dictionaryFilter,
									 valueField:'cusServicePid',
									 textField:'cusServiceName',
									 multiple:false,
									 url:'<%=basePath%>/customerController/selectCustomer.shtml'"/>
							</td>
						</tr>
						<tr>
							<td class="align_right">是否提醒：</td>
							<td>
								<select class="easyui-combobox" name="isWran" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >已发送</option>
									<option value=2 >未发送</option>
								</select>
							</td>
							<td class="align_right">生日:</td>
							<td>
								<div class="rangDate">
								<input type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'MM-dd'})" name="beginOperTime" style="width: 70px;" />~
								<input type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'MM-dd'})"  name="endOperTime" style="width: 70px;"  /> 
								</div>
							</td>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="cusBirWarnHis.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-remind"onclick="cusBirWarnHis.openShowTemp()">立即发送提醒</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-export" onclick="cusBirWarnHis.toDownloadPage()">导出excel</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="cusBirWarnHisGrid" >
			</table>
		</div>
		
		<!-- 按钮信息 -->
		<div id="buttons_cusBirWarnHis">
		    <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-save"  onclick="cusBirWarnHis.saveCusBirWarnHis()">立即发送</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#cusBirWarnHis_dlg').dialog('close')">关闭</a>
		</div>
		<!-- 弹出VIP生日提醒短信选取窗口 -->
		<div id="cusBirWarnHis_dlg" class="easyui-dialog" title="VIP生日提醒短信选择" data-options="modal:true" fitColumns="true"
				style="width:630px;height: 400px;" buttons="#buttons_cusBirWarnHis" closed="true" >
			<form action="" method="post" id="cbwh_searcm" name="cbwh_searcm" >
				<table class="beforeloanTable formTable ">
					<input type="hidden" name="customerId" />
					<tr>
						<td class="label_right" style="width:100px;">短信编号：</td>
						<td>
							<input class="easyui-validatebox" id="tempCode" readonly="readonly" style="width: 100px;" />
							<input type="hidden" name="smsTempId" id="smsTempId" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="commonCustomer.openSmsTemplates()">选择短信模版</a>
						</td>
					</tr>
					<tr>
						<td class="label_right vertical_align_top">短信内容：</td>
						<td colspan="2">
							<input class="easyui-textbox" id="vipMessage" name="vipMessage" data-options="multiline:true" readonly="readonly"  style="width: 480px;height: 200px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	<%-- 信息管理窗口 --%>
	<%@ include file="common/smsTemplates.jsp" %>	
</body>