<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/houseManager/houseManager.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="<%=basePath%>sysDistionaryController/create.shtml" id="houseAdd" name="houseAdd" method="post" >
	<div class="formWrap">
		<input type="hidden" id="pid" name="pid" value="${pid}">
		<table class="beforeloanTable formTable">
			<tr>
				<td class="label_right">
					<font color="red">*</font> 楼盘地址：
				</td>
				<td >
					<input class="easyui-textbox" name="homesProvince" id="homesProvince" required="true" data-options="validType:'length[0,10]'" missingMessage="请输入省份!" />
				</td>
			</tr>
			<tr>
				<td class="label_right">
				</td>
				<td >
					<input class="easyui-textbox" name="homesCity" id="homesCity" required="true" data-options="validType:'length[0,10]'" missingMessage="请输入直辖市!" />
				</td>
			</tr>
			<tr>
				<td class="label_right">
				</td>
				<td >
					<input class="easyui-textbox" name="homesArea" id="homesArea" required="true" data-options="validType:'length[0,10]'" missingMessage="请输入直辖区!" />
				</td>
			</tr>
			<tr>
				<td class="label_right">
					<font color="red">*</font> 楼盘：
				</td>
				<td >
					<input class="easyui-textbox" name="homesName" id="homesName" required="true" style="width:300px" data-options="validType:'length[0,10]'" missingMessage="请输入楼盘信息!"/>
				</td>
			</tr>
			<tr>
				<td class="label_right">
					<font color="red">*</font> 户型：
				</td>
				<td >
					<input class="easyui-textbox" name="homesType" id="homesType" required="true" style="width:300px" data-options="validType:'length[0,10]'" missingMessage="请输入户型信息!"/>
				</td>
			</tr>
		</table>	
	</div>
</form>

<script type="text/javascript" >
$(function(){
	houseManager.loadData();	
})
</script>

</body>