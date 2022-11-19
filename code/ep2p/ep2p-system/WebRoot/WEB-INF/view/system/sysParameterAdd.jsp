<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/sysParam.js" charset="utf-8"></script> 
<body class="easyui-layout">
<div data-options="region:'center',border:false">
<div id="scroll-bar-div">
	<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
	<form action="loadSysParams.shtml" method="post" id="searchFrom" name="searchFrom" >
		<!-- 查询条件 -->
		<div style="padding:5px">
			<table class="formTable">
			<tr>
				<td class="label_right">系统参数</td>
				<td>
				<input type="hidden"  name="pid" value="${params.pid}" id="pid"/>
				</td> 
			</tr> 
			<tr>
				<td class="label_right"><span class="red">*</span>参数主键：</td>
				<td><input type="text" class="easyui-validatebox easyui-textbox" name="paramKey" value="${params.paramKey}" id="paramKey" required="true" data-options="validType:'length[0,20]'" missingMessage="请输入参数主键!" /></td> 
			</tr>
			<tr>
				<td class="label_right"><span class="red">*</span>参数值：</td>
				<td><input type="text" class="easyui-validatebox easyui-textbox" name="paramValue"  value="${params.paramValue}" id="paramValue" required="true" data-options="validType:'length[0,50]'" missingMessage="请输入参数值!" /></td> 
			</tr>
			<tr>
				<td class="label_right"><span class="red">*</span>描述：</td>
				<td>
				<input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="paramDesc" id="paramDesc" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入描述!" value="${params.paramDesc}"/>
				 </td> 
			</tr>
			<tr>
				<td></td>			
				<td>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addParam()" data-options="iconCls:'icon-save'">保存</a>  
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="retPage()" data-options="iconCls:'icon-back'">返回</a>  
				</td> 
			</tr>
			</table>
		</div>
	</form> 
	</div> 
</div>
</div>
</body>




 