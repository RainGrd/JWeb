<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>

<input type="hidden" id="bankCount"  value="${bankCount}">
<!--  提现 -->
<div class=" borrow_div fl w945" id="withdraw_div">

	<!--第二个tis是提现记录-->
	<div class="tis m_auto ">
		<input type="hidden" id="openStatus" value="${openStatus}">
		<div class="kong60"></div>
		<div class="tis_liu colorDarkBlue">
			<span class="fl_ w210 inline_block tis_liu_list tc">
				<span class="lh75 size24 colorc tc">
					￥<fmt:formatNumber value="${availableAmount}" pattern="#,##0.00#"/>
				</span>
				<span class="tc size14 block">可用余额</span>
			</span>
			<span class="fl_ w270 inline_block tis_liu_list tc" onclick="clickBank()">
				<span class="kong10 block"></span>
				<span class="   tc">
					<img src="<%=basePath %>theme/default/images/tis_1.png" alt="" class="" />
				</span>
				<span class="tc size14 lh40 block">
					<c:if test="${bankCount>0}">
						${bankCount}张银行卡
					</c:if>
					<c:if test="${bankCount==0}">
						未添加、请添加
					</c:if>
				</span>
			</span>
			<span class="fl_ w270 inline_block cus_p tis_liu_list tc" onclick="clickRecharge()">
				<span class="kong10 block"></span>
				<span class="tc">
					<img src="<%=basePath %>theme/default/images/tis_2.png" alt="" class="" />
				</span>
				<span class="tc size14 lh40 block">充值</span>
			</span>
			<span class="fl_ w135 inline_block cus_p tc" onclick="clickWithdraw()" >
				<span class="kong10 block"></span>
				<span class="tc">
					<img src="<%=basePath %>theme/default/images/tis_3.png" alt="" class="" />
				</span>
				<span class="tc size14 lh40 block">提现 </span>
			</span>
		</div>
		<div class="kong35"></div>
		<div class="ge_ye_tilte tis_qie">
			<span class="ge_ye_tilte_nav  ms_t_se tis_qie_ta">充值记录</span>
			<span class="tis_qie_ta ge_ye_tilte_nav  ">提现记录</span> 
			<span class="fr_ size16 inline_block lh55 leiji_js">累计充值
			<i class="i_ ml15 colorc">￥<fmt:formatNumber value="${rechargeAmount}" pattern="#,##0.00#"/></i></span>
			
			<span class="fr_ size16 inline_block lh55 leiji_js none">累计提现
			<i class="i_ ml15 colorc">￥<fmt:formatNumber value="${withdrawalAmount}" pattern="#,##0.00#"/></i></span>
		</div>
		<!--第一个tis_qie_cs是充值记录-->
		<div class="tis_qie_cs">
			<div class="ms_c_t " id="pay_index_recharge_query">
				<div class="kong15"></div>
				<div class="ms_c_t_list">
					<form action="" id="pay_index_recharge_form">
						<input type="text" id="happenBeginTime" name="happenBeginTime" onfocus="WdatePicker()" class="fl_ ms_but_d"> 
						<span class="fl_ pl plr5"> - </span> 
						<input type="text" id="happenEndTime" name="happenEndTime" onfocus="WdatePicker()" class="fl_ ms_but_d "> 
					</form>
					<a class="btn_samll fl_ ml20" href="#" onclick="loadData(1,'pay_index_recharge_form')">查询</a>
				</div>
			</div>
			<div class="liu_c">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="esc_t">
					<thead>
						<tr class="size16">
							<th width="18%">充值时间</th>
							<th width="16%">充值流水号</th>
							<th width="11%">金额</th>
							<th width="13%">方式</th>
							<th width="10%">状态</th>
						</tr>
					</thead>
					<tbody class="size14" id="pay_index_recharge_tbody">
					</tbody>
				</table>
			</div>
			<!-- 分页 --> 
  			<div id="pay_index_recharge_page" class="m-pagination page_div fr"></div>
		</div>
		<!--第二个tis_qie_cs是提现记录-->
		<div class="tis_qie_cs none">
			<div class="ms_c_t  " id="pay_index_withdrawa_query">
				<div class="kong15"></div>
				<div class="ms_c_t_list">
					<form action="" id="pay_index_withdrawa_form">
						<input type="text" name="happenBeginTime" onfocus="WdatePicker()" class="fl_ ms_but_d"> 
						<span class="fl_ pl plr5"> - </span> 
						<input type="text" name="happenEndTime" onfocus="WdatePicker()" class="fl_ ms_but_d "> 
					</form>
					<a class="btn_samll fl_ ml20" href="#" onclick="loadData(2,'pay_index_withdrawa_form')">查询</a>
				</div>
			</div>
			
			<div class="liu_c">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="esc_t">
					<thead>
						<tr class="size16">
							<th width="18%">提取时间</th>
							<th width="16%">提取流水号</th>
							<th width="11%">提取金额</th>
							<th width="13%">提取手续费</th>
							<th width="22%">银行卡号</th>
							<th width="10%">状态</th>
							<th width="10%">备注</th>
						</tr>
					</thead>
					<tbody class="size14" id="pay_index_withdrawa_tbody">
					</tbody>
				</table>
			</div>
			<!-- 分页 --> 
   			<div id="pay_index_withdrawa_page" class="m-pagination page_div fr"></div>
		</div>
	</div>
</div>

<!-- 提现 e-->
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/rechargewithdrawal/payIndex.js"></script>