<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/pubRedpacket_song_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/pubRedpacket_song_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right">红包编号：</td>
							<td>
								<input class="easyui-textbox" name="actCode"/>
							</td>
							<td class="align_right">适用开始日期：</td>
							<td>
								<input class="easyui-datebox" name="beginApplyStartData" style="width: 100px;"  editable="false" />~
								<input class="easyui-datebox" name="endApplyStartData" style="width: 100px;" editable="false" /> 
							</td>
							<td class="align_right">标签：</td>
							<td>
								<input class="easyui-textbox" name="actTag"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="align_right">红包名称：</td>
							<td>
								<input class="easyui-textbox" name="repName" />
							</td>
							<td class="align_right">适用结束日期：</td>
							<td>
								<input class="easyui-datebox" name="beginApplyFinishData" style="width: 100px;"  editable="false" />~
								<input class="easyui-datebox" name="endApplyFinishData" style="width: 100px;"  editable="false" /> 
							</td>
							<td class="align_right">状态：</td>
							<td>
								<select class="easyui-combobox" name="status"  panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >启用</option>
									<option value=2 >禁用</option>
								</select>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="pubRedpacket_song.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="pubRedpacket_song.openAdd()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="pubRedpacket_song.remove()">删除</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="pubRedpacketGrid" >
			</table>
		</div>
</body>