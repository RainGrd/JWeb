<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar" class="noPadding ">
			<input type="hidden" id="pid" name="pid" value="${pid}">
			<form id="baseInfos" name="baseInfos" method="POST">
			<table class="cus_table formTable">
			  <tr>
			       <td>
<!-- 			         <img alt="" src="tt" width="100px;" height="40px;"> -->
						<img id="cus_busLicCert_url_img" alt="头像"  name="" width="100px" height="50px">
			       </td>
					<td colspan="1">
					   <input name="customerName" class="text_style" disabled="disabled" value="${customerName}"/>
					</td>
					<td class="align_right" style="width: 150px;">姓名：</td>
					<td>
						 <input name="sname" class="text_style" disabled="disabled" value="${result.sname}"/>
					</td>
					<td class="align_right">手机号码：</td>
					<td>
						 <input name="phoneNo" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >电子邮箱：</td>
					<td>
						 <input name="email" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
<!-- 					<td class="align_right " >vip级别图标：</td> -->
					<td colspan="2">
					<img id="cus_busLicCert_url_img" alt="头像"  name="" width="30px" height="25px">
					</td>
					<td class="align_right " >性别：</td>
					<td>
						 <input name="sex" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >生日：</td>
					<td>
						<input name="birthday" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr >
			   
			     <td class="align_right " >VIP到期时间：</td>
					<td>
						<input name="vipTimeTwo" class="text_style" disabled="disabled"/>
					</td>
				    <td colspan="4"></td>
			   </tr>
			    <tr>
			     <td class="align_right " >注册时间：</td>
					<td>
						<input name="registrationTime" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
			    <tr>
			     <td class="align_right " >跟踪客服：</td>
					<td>
						<input name="customerServiceUser" class="text_style" disabled="disabled"/>
					</td>
				 <td class="align_right " >推荐人：</td>
					<td>
						<input name="refereeUser" class="text_style" disabled="disabled"/>
					</td>	
					<td class="align_right " >黑名单：</td>
					<td>
						<input name="isBlacklist" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >用户禁用：</td>
					<td>
						<input name="isFreeze" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >积分：</td>
					<td>
					    <a href="#" onclick="bank.selectavailablePointDetails();"><input name="availablePoint" class="text_style" disabled="disabled" style="color: blue;  text-decoration: underline;"/></a>
					</td>
					<td class="align_right " >经验值：</td>
					<td>
						<a href="#" onclick="bank.selectExperienceDetails();"><input name="empiricalValue" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
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
			   <tr>
					
					<td class="align_right " width="auto">投资奖励：</td>
					<td colspan="7">
						<a onclick="viewPointDetail(1)" href="javascript:void(0);"><input name="point" class="blueLink" disabled="disabled" style="width:70px;color: green"/></a>
						<a onclick="viewPointDetail(2)" href="javascript:void(0);"><input name="experience" class="blueLink" disabled="disabled" style="width:70px;color: blue"/></a>
						<a onclick="viewPointDetail(0)" href="javascript:void(0);"><input name="increaseInterest" class="blueLink" disabled="disabled" style="width:70px;color:red"/></a>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >可用余额：</td>
					<td>
						<input name="availableBalance" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right " >冻结金额：</td>
					<td>
						<a href="#" onclick="bank.selectFrozenAmountDetails();"><input name="frozenAmount" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right "  >账户总额：</td>
					<td>
						<a href="#" onclick="bank.selectAccountTotalDetails(1);"><input name="totalAccount" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right "  >体验金：</td>
					<td>
						<a href="#" onclick="bank.selectTiyanGoldDetails();"><input name="experienceGold" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
			   </tr>
			    <tr>
			        <td class="align_right ">待收本金：</td>
					<td>
						<a href="#" onclick="bank.selectTotalStayDetails(1);"><input name="totalStay" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right">待收利息：</td>
					<td>
						<a href="#" onclick="bank.selectCollectInterestDetails(2);"><input name="receivedInterest" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right " colspan="">投资利息：</td>
					<td>
						<a href="#" onclick="bank.selectJinZhuangInterestDetails(5);"><input name="netInterest" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right " colspan="">加息收益：</td>
					<td>
						<a href="#"><input name="jxInterest" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
			   </tr>
			   <tr>
					<td class="align_right "  >投资奖励：</td>
					<td>
						<a href="#" onclick="bank.selectTouBiaoDetails(3);"><input name="investmentReward" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right "  >红包收益：</td>
					<td>
						<a onclick="viewPointDetail(3)" href="javascript:void(0);"><input name="receivedRedEnvelope" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right "  >推荐奖励：</td>
					<td>
						<a href="#" onclick="bank.selectTuiJianRewardDetails(4);"><input name="recommendReward" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
			   </tr>
			   <tr>
					<td class="align_right "  >充值总额：</td>
					<td>
						<a href="#" onclick="bank.selectRechargeTotalDetails(6);"><input name="rechargeTotal" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right">提现总额：</td>
					<td>
						<a href="#" onclick="bank.selectWithdrawalsTotalDetails(7);"><input name="withdrawalsTotal" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
					<td class="align_right "  >投资总额：</td>
					<td>
						<a href="#" onclick="bank.selectTouZiDetails(2);"><input name="investmentAccount" class="text_style" disabled="disabled" style="color: blue; text-decoration: underline;"/></a>
					</td>
			   </tr>
			   <tr>
					<td class="align_right ">客户证件：</td>
					<td>
						
						<input name="certType" class="text_style" disabled="disabled"/>
					</td>
					<td class="align_right">证号：</td>
					<td>
						<input name="identificationNo" class="text_style" disabled="disabled"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right ">证件正面：</td>
					<td>
						<input name="certType" class="text_style" disabled="disabled"/>
					<td class="align_right">证件反面：</td>
					</td>
					<td>
						<a href="" name="identificationNo"></a>
					</td>
			   </tr>
			    <tr>
					 <td colspan="3">
<%-- 					  	<c:if test="${busLicCertUrl||busLicCertUrl=='' }"> --%>
						   		<img id="cus_busLicCert_url_img" alt="头像"  name="busLicCertUrl" width="260px" height="120px">
<%-- 						</c:if> --%>
<%-- 					  	<c:if test="${busLicCertUrl && busLicCertUrl !='' }"> --%>
<!-- 						   		<img id="cus_busLicCert_url_img" alt="头像" name="busLicCertUrl" width="160px" height="120px"> -->
<%-- 						</c:if> --%>
					  </td>
					  <td colspan="2">
<%-- 					    <c:if test="${busLicCertUrl||busLicCertUrl=='' }"> --%>
						   		<img id="cus_busLicCert_url_img" alt="头像" name="busLicCertUrl" width="260px" height="120px">
<%-- 						</c:if> --%>
					  	<c:if test="${busLicCertUrl && busLicCertUrl !='' }">
						   		<img id="cus_busLicCert_url_img" alt="头像" name="busLicCertUrl" width="160px" height="120px">
						</c:if>
	  				  </td>
	  			</tr>
			</table>	
			</form>
			<div class="panel-header topBorder pt10">
					<div class="panel-title">绑定银行账号：</div>
					<div class="panel-tool">
						<a href="javascript:void(0)" class="panel-tool-collapse" onclick="foldDatagrid()"></a>
						<a href="javascript:void(0)" class="panel-tool-expand" onclick="openDatagrid()"></a>
					</div>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="showCusBankGrids"></table>
		</div>
	</div> 
<%-- <script type="text/javascript" src="${basePath}resources/js/custom/selectCusDetailsById.js"></script> --%>
<script type="text/javascript" src="${basePath}resources/js/custom/model/selectCusDetailsByIdModel.js"></script>
<script type="text/javascript">
// bank.loadData();
// bank.initDataGrid();
	$(document).ready(function(){
	});
	function viewPointDetail(flag){
		var path = BASE_PATH+"customerController/viewPoint.shtml?flag="+flag+'&pid='+${pid};
		$("<div id='pointDetails' /> ").dialog({
			href:path,
			title:"投资奖励",
			method:'post',
			width:'600',
			height:'430',
			modal: true,
			buttons:[{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){
					$("#pointDetails").dialog('close');
				}
			}],
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	}
</script>
	
</body>