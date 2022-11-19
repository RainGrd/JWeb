<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body class="easyui-layout">
	<form id="baseInfo1" name="baseInfo1" method="POST">
			<input type="hidden" id="pid" name="pid" value="${pid}">
			<table class="formTable percent90">
			   <tr>
			   		<td class="align_right">客户状态：</td>
    				<td id="cusStatus_td">
    				</td>
			    </tr>
<!-- 			    <tr> -->
<!-- 			   		<td class="align_right">生效日期：</td> -->
<!--     				<td> -->
<!--     					<input class="easyui-textbox easyui-datebox" id="" name="forceDate" data-options="required:true,editable:false" /> -->
<!--     				</td> -->
<!-- 			    </tr> -->
			    <tr>
			   		<td class="align_right">失效日期：</td>
    				<td>
    					<input class="easyui-textbox easyui-datebox" id="" name="invalidDate" data-options="required:true,editable:false" />
    				</td>
			    </tr>
			     <tr>
			      <td class="align_right" >客户状态变更说明：</td>
			       <td>
			      	 <textarea class="percent70" rows="2" cols="100" name="cusStatusRemark" ></textarea>
			       </td>
			   </tr>
			     <tr>
				   	<td>
				   		 <input type="hidden" name="page" id="page" value="1">
						 <input type="hidden" name="rows" id="rows">
					</td>		 
			    </tr>
			</table>	
			</form>
</body>