<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<form id="baseInfo" name="baseInfo" method="POST">
			<input type="hidden" id="pid" name="pid" value="${pid}">
			<table class="formTable percent90">
			   <tr>
			   	  <td class="align_right" style="width: 105px;">用户名：</td>
					<td>
					   <input name="customerName" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right" style="width: 170px;">姓名：</td>
					<td>
						 <input name="sname" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right" style="width: 130px;">注册时间：</td>
					<td>
						<input name="registrationTime" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right">手机号码：</td>
					<td>
						 <input name="phoneNo" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >邮箱：</td>
					<td>
						 <input name="email" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >生日：</td>
					<td>
						<input name="birthday" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >积分：</td>
					<td>
						<input name="availablePoint" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >经验值：</td>
					<td>
						<input name="empiricalValue" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >推荐人：</td>
					<td>
						<input name="refereeUser" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right ">现在客服：</td>
					<td>
						<input name="customerServiceUser" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >黑名单：</td>
					<td>
						<input name="isBlacklist" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >VIP：</td>
					<td>
						<input name="vipName" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right "  >VIP到期时间：</td>
					<td>
						<input name="vipTime" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			    <tr>
					<td class="align_right " >用户禁用：</td>
					<td>
						<input name="isFreeze" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >禁止债权转让：</td>
					<td>
						<input name="isForbidTransfer" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right" >禁止提现：</td>
					<td>
						<input name="isForbidWithdraw" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			</table>	
		</form>
