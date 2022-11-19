<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/radio/programList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/radio/model/programList_Model.js"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar">
			<!-- 查询条件 -->
				<form action="getProgramSearchList.shtml" method="post" id="searcher" 
					name="searcher">
					<div style="padding: 5px">
						<table class="userTable percent100 formTable">
						  <tr>
							<td class="align_right">标题：</td>
							<td><input class="easyui-textbox"  id="programTitle" name="programTitle"/> </td>
							<td class="align_right">板块选择：</td>
							<td>
						        <select name="programPlateId" editable="false" id="programPlateId"
									  class="easyui-combobox">
								<option  value="0">--全部--</option>
								<option  value="1">专家讲座</option>
								<option  value="2">百姓理财</option>
								<option  value="3">金融知识</option>
								</select> 
							</td>
						  </tr>
						  <tr>
							<td class="align_right">上传时间：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" id="upload_Begin_Time" name="uploadBeginTime" data-options="editable:false" />至
								<input class="easyui-datebox" id="upload_end_Time" name="uploadEndTime" data-options="editable:false" />
								</div>
							</td>
							<td class="align_right">状态选择：</td>
							<td>
						        <select name="status" editable="false" id="status"
									  class="easyui-combobox">
								<option value="0">--全部--</option>
								<option value="1">已发布</option>
								<option value="2">预发布</option>
								<option value="3">未发布</option>
								</select> 
							</td>
						</tr>
						<tr>
							<td class="align_right">发布时间：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" id="publish_Begin_Time" name="publishBeginTime" data-options="editable:false" />至
								<input class="easyui-datebox" id="publish_End_Time" name="publishEndTime" data-options="editable:false" />
								</div>
							</td>
							<td class="align_right">收听数区间：</td>
							<td>
								<div class="rangDate">
							  		<input class="easyui-textbox" id="listen_Begin_Num" name="listenBeginNum" />至
							  		<input class="easyui-textbox" id="listen_End_Num" name="listenEndNum"/>
							  	</div>
							</td>
					  </tr>
					  <tr>
					  	<td class="align_right">获赞数区间：</td>
							<td>
								<div class="rangDate">
							  		<input class="easyui-textbox" id="praise_Begin_Num" name="praiseBeginNum" />至
							  		<input class="easyui-textbox" id="praise_End_Num" name="praiseEndNum" />
							  	</div>
							</td>
					  	<td colspan="5">
					  		<div class="check_box pt10">
							   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							   <a href="#" onclick="javascript:$('#searcher').form('reset');" iconCls="icon-clear" class="easyui-linkbutton">重置</a>
					  		</div>
					  	</td>
					  </tr>
					</table>
				</div>
				</form>
			
			<!-- 操作按钮 -->
			<div>	
			<table>
				<tr>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="radio.beatchDel();">批量删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  onclick="radio.uploadProgram();">上传节目</a>
					</td>
				</tr>
			</table>
			</div>
		</div>
		<!--  数据列表 -->		
		<div class="programListDiv" style="height:100%">
			<table id="programListGrid">
			</table>
		</div>
		
		<script type="text/javascript">
		radio.initDataGrid();
		$(document).ready(function(){
			//customerList.initDataGrid();	
		});
		//查询
		function searchData(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			$('#programListGrid').datagrid('load',obj);
		}
	</script>
	</div>
</body>