<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/custAuthenticationHis_auditing.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/custAuthenticationHis_auditing_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="overflow-x:hidden;">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<form action="" method="post" id="baseInfo" name="baseInfo">
				<input type="hidden" name="pid" id="pid" value="${pid}" />
				<input type="hidden" name="version" value="${version}" />
				<table class="formTable percent90">
					<tr>
						<td class="align_right" style="width: 105px;">用户名：</td>
						<td>
							<input class="text_style" disabled="disabled" name="customerName"/>
						</td>
						<td class="align_right" style="width: 170px;">姓名：</td>
						<td>
							<input class="text_style" disabled="disabled" name="sname"/>
						</td>
						<td class="align_right" style="width: 130px;">注册时间：</td>
						<td>
							<input class="text_style" disabled="disabled" name="registrationTime"/>
						</td>
					</tr>
					<tr>
						<td class="align_right">手机号码：</td>
						<td>
							<input class="text_style" disabled="disabled" name="phoneNo" />
						</td>
						<td class="align_right">邮箱：</td>
						<td>
							<input class="text_style" disabled="disabled" name="email" />
						</td>
						<td class="align_right">生日：</td>
						<td>
							<input class="text_style" disabled="disabled" name="birthday" />
						</td>
					</tr>
					<tr>
						<td class="align_right">推荐人：</td>
						<td>
							<input class="text_style" disabled="disabled" name="refereeUser" />
						</td>
						<td class="align_right">现在客服：</td>
						<td>
							<input class="text_style" disabled="disabled" name="customerServiceUser"/>
						</td>
						<td class="align_right">性别：</td>
						<td>
							<input class="text_style" disabled="disabled" name="sex" />
						</td>
					</tr>
					<tr>
						<td class="align_right">黑名单：</td>
						<td>
							<input class="text_style" disabled="disabled" name="isBlacklist" />
						</td>
						<td class="align_right">VIP：</td>
						<td>
							<input class="text_style" disabled="disabled" name="vipName"/>
						</td>
						<td class="align_right">VIP到期时间：</td>
						<td>
							<input class="text_style" disabled="disabled" name="vipTime" />
						</td>
					</tr>
					<tr>
						<td class="align_right">用户禁用：</td>
						<td>
							<input class="text_style" disabled="disabled" name="isFreeze" />
						</td>
						<td class="align_right">禁止债权转让：</td>
						<td>
							<input class="text_style" disabled="disabled" name="isForbidTransfer"/>
						</td>
						<td class="align_right">禁止提现：</td>
						<td>
							<input class="text_style" disabled="disabled" name="isForbidWithdraw" />
						</td>
					</tr>
					<tr>
						<td class="align_right">客户证件：</td>
						<td>
							<input class="text_style" disabled="disabled" value="身份证"/>
						</td>
						<td class="align_right">证件号：</td>
						<td colspan="3">
							<input class="text_style" disabled="disabled" name="identificationNo"/>
						</td>
					</tr>
					<tr>
						<td class="align_right">证件正面：</td>
						<td colspan="2"></td>
						<td colspan="3">证件反面：</td>
					</tr>
					<tr>
						<td colspan="3" style="height: 200px;"></td>
						<td colspan="3" style="height: 200px;"></td>
					</tr>
					<tr>
						<td  style="padding-right: 10px;" class="align_right">操作：</td>
						<td colspan="5">
							<input type="radio" name="statusRadio" value="1" /> 同意
							<input type="radio" name="statusRadio" value="2" checked="checked"/> 拒绝
							<input type="hidden" name="status" />
						</td>
					</tr>
					<tr>
						<td style="padding-right: 10px;" class="align_right vertical_align_top" >操作备注：</td>
						<td colspan="5">
							<input class="easyui-textbox" name="autHisDesc" data-options="multiline:true,validType:'length[0,200]'" style="width: 500px;height: 80px;" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="custAuthenticationHis.save()">提交</a>
						</td>
						<td colspan="4" ></td>
					</tr>
					<tr>
						<td class="align_right">认证记录：</td>
						<td colspan="5" ></td>
					</tr>
				</table>	
			</form>
			
		</div>
		
		<div class="showDataListWrap">
			<table id="custAuthenticationHisGrid_detail" >
			</table>
		</div>
	</div>
</body>