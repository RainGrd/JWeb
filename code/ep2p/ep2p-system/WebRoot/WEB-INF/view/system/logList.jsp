<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/model/logManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/log.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right">
									用户账号： 									
								</td>
								<td><input class="easyui-textbox" name="accountNo" id="accountNo" /></td>
								<td class="label_right">
									真实姓名： 									
								</td>
								<td><input class="easyui-textbox" name="name" id="name" /></td>
								<td class="label_right">
									创建日期： 								
								</td>
								<td><input class="easyui-datebox" name="createTime" id="createTime" value="${sysDate}" /></td>
							</tr>
							<tr>
								<td class="label_right">
									结果类型： 									
								</td>
								<td><input class="easyui-textbox" name="status"  id="status" /></td>
								<td class="label_right">
									操作类型：									
								</td>
								<td> <input class="easyui-textbox" name="operType" id="operType" /></td>
								<td></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="log.searchData()">查询</a>
									
								</td>
							</tr>
							
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<!-- a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="log.add()">新增</a> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="log.batchRemove()">删除</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="logGrid">
			</table>
		</div>
	</div>
</body>