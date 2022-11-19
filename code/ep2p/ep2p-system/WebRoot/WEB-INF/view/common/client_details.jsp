<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(document).ready(function(){
			var pid =$("#clientPid").val();
			if("" != pid){
				$.ajax({
					type: "POST",
			    	url : basePath + 'customerController/selectCusHistoryDetailedById.shtml',
			    	data:{data:"{pid:"+pid+"}"},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		$("#client_details_form").form('load',data.result);
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		});
	</script>
		<form id="client_details_form" name="client_details_form" method="POST">
			<input type="hidden" id="clientPid" name="clientPid" value="${clientPid}">
			<table class="percent90 formTable">
			   <tr>
			   	  <td class="align_right" style="width: 100px;">用户名：</td>
					<td>
					   <input name="customerName" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right" style="width: 100px;" >姓名：</td>
					<td>
						 <input name="sname" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right" style="width: 100px;">注册时间：</td>
					<td>
						<input name="registrationTime" class="dis_text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right">手机号码：</td>
					<td>
						 <input name="phoneNo" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >邮箱：</td>
					<td>
						 <input name="email" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >生日：</td>
					<td>
						<input name="birthday" class="dis_text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >积分：</td>
					<td>
						<input name="availablePoint" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >经验值：</td>
					<td>
						<input name="empiricalValue" class="dis_text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >推荐人：</td>
					<td>
						<input name="refereeUser" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right ">现在客服：</td>
					<td>
						<input name="customerServiceUser" class="dis_text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >黑名单：</td>
					<td>
						<input name="isBlacklist" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >VIP：</td>
					<td>
						<input name="vipName" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right "  >VIP到期时间：</td>
					<td>
						<input name="vipTime" class="dis_text_style" disabled="disabled"/>
					</td>
			   </tr>
			    <tr>
					<td class="align_right " >用户禁用：</td>
					<td>
						<input name="isFreeze" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >禁止债权转让：</td>
					<td>
						<input name="isForbidTransfer" class="dis_text_style" disabled="disabled"/>
					</td>
					<td class="align_right" >禁止提现：</td>
					<td>
						<input name="isForbidWithdraw" class="dis_text_style" disabled="disabled"/>
					</td>
			   </tr>
			</table>	
		</form>
