<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/custAuthenticationHis_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/custAuthenticationHis_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
		<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
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
							<td class="align_right">手机号码：</td>
							<td>
								<input class="easyui-textbox" name="phoneNo" />
							</td>
							<td class="align_right">推荐人：</td>
							<td>
								<input class="easyui-textbox" name="refereeUser" />
							</td>
						</tr>
						<tr>
							
							<td class="align_right">注册时间：</td>
							<td colspan="3">
								<input class="easyui-datebox" name="happenBeginTime"  editable="false" />~
								<input class="easyui-datebox" name="happenEndTime" editable="false" /> 
							</td>
							
						</tr>
						<tr>
							<td class="align_right">认证状态：</td>
							<td>
								<select class="easyui-combobox" name="attestationStatus" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >审核中</option>
									<option value=2 >已同意</option>
									<option value=3 >已拒绝</option>
								</select>
							</td>
							<td colspan="2">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="custAuthenticationHis.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
		</div>
		
		<div class="showDataListWrap">
			<table id="custAuthenticationHisGrid" >
			</table>
		</div>
</body>