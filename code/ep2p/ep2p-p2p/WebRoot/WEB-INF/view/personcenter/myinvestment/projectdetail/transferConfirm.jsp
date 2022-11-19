<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp"%>
<%@include file="/common/taglibs.jsp"%>



<script type="text/javascript">
	var minValue = "${minValue}";
	var maxValue = "${maxValue}";
	
	var bizTransferFee = "${bizTransferFee}";
	
	
	$(document).ready(function(){
		$(".gou_s").html('<img src="'+ basePath + 'theme/default/images/gou_b.png" class="block" />');
		$(".gou_s").attr("val","1");
		$(".gou_s").parent().addClass("c2980b9");
		escfutil.gougou(".dropAgreeBox",'');
	});
</script>
<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>
<script  type="text/javascript" src="<%=basePath%>theme/js/personcenter/myinvestment/projectdetail/transferConfirm.js"></script>
<!-- 债权转让-->
<div class="sidebar_b fl " id="transferPage">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">债权转让</span>
	</div>
	<div class="borrow_info fl f-12">
		<div class="binfo size14">
			<div class="binfotop colorDarkBlue">
				<div class="fl" style="width: 600px;text-align: left;padding-left: 10px">借款：
			    	<%--e首房，e抵押 --%>
                   	<c:if test="${borrow.borrowType == '1' || borrow.borrowType == '2' }">
                   		<a href="<%=basePath %>business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=${borrow.borrowId}"><c:out value="${borrow.borrowCode }"/>/<c:out value="${borrow.borrowName }" /></a>
                   	</c:if>
                   	<%--e计划 --%>
                   	<c:if test="${borrow.borrowType == '3'}">
                   		<a href="<%=basePath %>business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=${borrow.borrowId}"><c:out value="${borrow.borrowCode }"/>/<c:out value="${borrow.borrowName }" /></a>
                   	</c:if>
				</div>
				<div class="fl "></div>
				<div class="fr w200">待收时间：<c:out value="${currentExpireTime }" /></div>
			</div>
			<div class="cb kong30"></div>
			<div class="ml40" style="width: 800px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="">
					<thead>
						<tr class="c95a1a6" style="span-weight:normal;">
							<th width="17%" style="span-weight:normal;">投资本金</th>
							<th width="15%" style="span-weight:normal;">已收/总期次</th>
							<th width="17%" style="span-weight:normal;">已收本息</th>
							<th width="17%" style="span-weight:normal;">剩余本金</th>
							<th width="17%" style="span-weight:normal;">当期至今利息</th>
							<th width="17%" style="span-weight:normal;">当期全部利息</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="">　
							</td>
							<td class="">　</td>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<td>　</td>
						</tr>
						<tr>
							<td class="">￥<fmt:formatNumber value="${investCapital }" pattern="#,##0.00"/>
							</td>
							<td class=""><c:out value="${surperDead }" />/<c:out value="${totalDead }" /></td>
							<td>￥<fmt:formatNumber value="${alreadyBenxi }" pattern="#,##0.00"/></td>
							<td>￥<fmt:formatNumber value="${surperCapital }" pattern="#,##0.00"/></td>
							<td>￥<fmt:formatNumber value="${currentInterest }" pattern="#,##0.00"/></td>
							<td>￥<fmt:formatNumber value="${currentAllInterest }" pattern="#,##0.00"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<div class="transfer_info fl size14">
		<div class="ti1">
			<div style="width: 40%;" class="fl">　<span class="colororg">　</span><span class="">
                    	<div class="tipsWrap">
                            <p>债权总价即为借款对应期次的待收本息</p>
                        </div></span></div>
			<div style="width: 60%;" class="fl">　<span class="colororg">　</span></div>
		</div>
		<div class="ti2">
			<div>转让价格：
				<input type="text" id="transferAmount" style="width: 230px;"  onblur="(value=isNaN(parseFloat(value))?value:parseFloat(value).toFixed(2));inputTransferEvent();"   onkeyup="value=value.replace(/[^\d.]/g,'');inputTransferEvent();" /><span id="errorMsg" class="input_tis ts_lh w210 mb5 " style="margin-left: 5px;display: none;"></span>

			</div>
		</div>
		
		<div class="ti3" style="padding-left: 80px;">有效价格区间: ￥<fmt:formatNumber value="${minValue }" pattern="#,##0.00"/>~ ￥<fmt:formatNumber value="${maxValue }" pattern="#,##0.00"/></div>
		<div class="pt10 pb10">
			<div>转让时效：<i class="i_ colorc">24小时内</i>
				<br />
				<span class="c999999 ml65">
					如无人购买，系统将自动下架转让
				</span>

			</div>
		</div>
		<div class="kong5"></div>
		<div class="kong20"></div>
		<div class="ti4" style="padding-left: 70px;"><a class="btn_samll zhuangr_key_js" style="margin-right: 20px;" href="javascript:next()">下一步</a> <a class="btn_samll_gray" href="javascript:window.history.go(-1)">取消</a> </div>
	</div>

</div>
<!-- 确认债权转让 s-->
<div id="confirmWin" class="goumai_vip_d none tan_a" style="z-index: 0">

	<div class="vip_g_b"></div>
	
	<div class="vip_g_d tan_s_a m_auto " style="height: 715px">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">转让确认</span>
		</div>
		<div class="vip_g_lists m_auto colorDarkBlue size14 goumai_vip_d_meial ">
			<!--债权转让成功提示信息-->
			<div class="success_div fl m_auto" >
				<div class="suc_content m_auto">
					<div class="kong100"></div>
					<div class="suctitle1">确认转让信息</div>
				</div>

				<div class="suc_info f-14 m_auto">
					<div class="suc_info_m">
						<div>转让本金：<span class="colororg">￥<fmt:formatNumber value="${surperCapital }" pattern="#,##0.00" /> </span></div>
						<div>转让价格：<span class="colororg" id="transferMoney"></span></div>
						<div>转让手续费：<span class="colororg" id="transferFee"></span></div>
						<div class="c999999">如无人购买，系统将自动下架转让</div>
					</div>

				</div>
				<form id="transferForm">
					<input type="hidden" name="borrowId" value="${borrow.pid }">
					<input type="hidden" name="transferId" value="${transferId}">
					<input type="hidden" name="transferMoney" id="z_transferAmount">
					<input type="hidden" name="pwd" id="pwd">
					<p class="kong40"></p>
					<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_jiaxi_m">
						<span class="inline_block  w300 tl" style="line-height: 30px;margin-top:7px;margin-bottom:7px">
			    			交易密码：<input type="password" id="tradePassword" maxlength="6" class="w205 inline_block size14 000000_ fui_juan_chong">
			    			<br>
			    			<span id="tradeError" class="input_tis ts_lh w210 mb5 fl " style="margin-left: 70px;display: none;"></span>
						</span>
					</p>
					<div class="dropAgreeBox c2980b9 oh tc w200 m_auto">
						<div class="gou_s  mr10 fl" id="gou" ></div>
						<div class="colorDarkBlue size14 dropAgree  ">同意《e生财富借款协议》</div>
					</div>
					<div class="kong20"></div>
					<div class="tc m_auto">
						<a class="btn_samll " href="javascript:void(0)" onclick="transferSubmit();">确定</a>
						<a class="btn_samll_gray ml20 " href="javascript:void(0)" onclick="closeConfirmWin()">取消</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>



<!-- 债权转让发布成功-->
<div class="sidebar_b fl none " id="suc_msg">

	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">债权转让</span>
	</div>

	<div class="vip_g_lists m_auto colorDarkBlue size14 goumai_vip_d_meial " >
		<!--债权转让成功提示信息-->
		<div class="success_div fl m_auto" style="width: 100%;">
			<div class="suc_content m_auto">
				<div class="suctitle">恭喜，债权转让编号：<span id="transferCode">XXXXXXXXXX</span></div>
				<div class="colororg f-20 tc">债权已转让中!</div>
				<div class="suc_c">
						24小时内超过有效时间无人购买，系统将自动下架转让
				</div>
			</div>
			<div class="suc_info f-14 m_auto">
				<div class="suc_info_m">
					<div>转让本金：<span class="colororg">￥<fmt:formatNumber value="${surperCapital }" pattern="#,##0.00" /></span></div>
					<div>转让价格：<span class="colororg" id="transferMoney2"></span></div>
					<div>转让手续费：<span class="colororg" id="transferFee2"></span></div>
				</div>
			</div>
			<div class="tc m_auto">
					<a class="btn_samll" style="width: 160px;" href="javascript:void(0)">查看转让中的债权</a> 
			</div>
		</div>
	</div>
</div>	
