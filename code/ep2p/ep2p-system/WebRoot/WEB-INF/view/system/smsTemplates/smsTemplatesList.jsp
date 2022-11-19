<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/model/smsTemplatesManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/smsTemplates.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="beforeloanTable formTable percent90">
							<tr>
								<td class="label_right">
									短信编号： 
								</td>
								<td>
									<input class="easyui-textbox" name="tempCodeLike" id="tempCodeLike" />
								</td>
								<td class="label_right">
									短信名称： 
								</td>
								<td>
									<input class="easyui-textbox" name="smsTempNameLike" id="smsTempNameLike" />
								</td>
								<td class="label_right">
									创建人： 
								</td>
								<td>
									<input class="easyui-textbox" name="createUserLike" id="createUserLike" />
								</td>
								<td class="label_right">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="smsTemplates.searchData()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="smsTemplates.resetInput()">重置</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="smsTemplates.add()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="smsTemplates.batchRemove()">删除</a>
		</div>
		<div class="showDataListWrap">
		<table id="smsTemplatesGrid"></table>
	</div>
	</div>
		
	
</body>