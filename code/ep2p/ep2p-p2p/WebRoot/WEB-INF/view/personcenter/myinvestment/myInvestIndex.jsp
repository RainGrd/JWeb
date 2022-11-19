<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<div class="sidebar_b invest_div fl">
	<div class="my_tips fl mt20">
		<ul>
			<li>
				<fieldset>
					<legend>投资总额</legend>
					<div class="colororg f-24">￥<fmt:formatNumber value="${investmoney}" pattern="#,##0.00#"/></div>
				</fieldset>
			</li>
			<li>
				<fieldset>
					<legend>待收总额</legend>
					<div class="colororg f-24">￥<fmt:formatNumber value="${idueinmoney}" pattern="#,##0.00#"/></div>
				</fieldset>
			</li>
			<li>
				<fieldset>
					<legend>净收利息</legend>
					<div class="colororg f-24">￥<fmt:formatNumber value="${netmargin}" pattern="#,##0.00#"/></div>
				</fieldset>
			</li>
		</ul>
	</div>
	<div class="recharge fr">
		<div class="rank fl">
			<c:if test="${rankInfo.position <= 1000}">
				<div class="fl text_blue2 f-16">太棒了</div>
				<div class="fl colororg">当前排名第<c:out value="${rankInfo.position}"></c:out>位</div>
			</c:if>
			<c:if test="${rankInfo.position > 1000 || rankInfo.position == null}">
				<div class="fl text_blue2 f-16">请加油</div>
				<div class="fl colororg">当前排名在1000名以外</div>
			</c:if>
			<img src="<%=basePath %>theme/default/images/rank.png" width="98" height="42" alt="排名" />
		</div>
	</div>
	<div class="fl title_div">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav ms_t_se" thistype="1" >待收中</span> 
			<span class="ge_ye_tilte_nav" thistype="2" >招标中</span> 
			<span class="ge_ye_tilte_nav" thistype="3" >转让</span> 
			<span class="ge_ye_tilte_nav" thistype="4" >已完结</span> 
		</div>
		<div id="invest_panel_times1" class="find_area fl invest_panel_times">
			<img src="<%=basePath %>theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" />
			<input id="startInvestmentTime1" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> <i class="i_ ml5 mr5">-</i> <input id="endInvestmentTime1" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> 
			<a class="btn_samll" href="javascript:void(0)" onclick="userinvestment.onSerachItems(1);">查询</a>
		</div>
		<div id="invest_panel_times2" class="find_area fl none invest_panel_times">
			<img src="<%=basePath %>theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" />
			<input id="startInvestmentTime2" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> <i class="i_ ml5 mr5">-</i> <input id="endInvestmentTime2" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> 
			<a class="btn_samll" href="javascript:void(0)"  onclick="userinvestment.onSerachItems(2);">查询</a>
		</div>
		<div id="invest_panel_times3" class="find_area fl none invest_panel_times">
			<img src="<%=basePath %>theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" />
			<input id="startInvestmentTime3" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> <i class="i_ ml5 mr5">-</i> <input id="endInvestmentTime3" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> 
			<a class="btn_samll" href="javascript:void(0)" onclick="userinvestment.onSerachItems(3);">查询</a>
		</div>
		<div id="invest_panel_times4" class="find_area fl none invest_panel_times">
			<img src="<%=basePath %>theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" />
			<input id="startInvestmentTime4" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> <i class="i_ ml5 mr5">-</i> <input id="endInvestmentTime3" type="text" class="pl10" onClick="WdatePicker()" readonly="readonly"/> 
			<a class="btn_samll" href="javascript:void(0)" onclick="userinvestment.onSerachItems(4);">查询</a>
		</div>
	</div>
	<%-- 待收中 --%>
	<div class="invest_table inv_js ">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="25%">投资时间</th>
					<th width="13%">编号</th>
					<th width="13%">投资金额</th>
					<th width="13%">已收本息</th>
					<th width="13%">待收本息</th>
					<th width="13%">投资奖励</th>
					<th width="13%">加息收益</th>
					<th width="9%">状态</th>
					<th width="9%">转让</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody id="duein_list_tr">
				<%-- <tr>
					<td class="pl30">
						<p class="cchu">质押贷款借款HY34质押贷款借款HY34</p>2015-01-01 08:08:08
					</td>
					<td class="">D15102301</td>
					<td>￥2001334.01</td>
					<td>￥2001334.01</td>
					<td>￥2001334.01</td>
					<td>￥2001334.01</td>
					<td>￥1234.05</td>
					<td>待收中</td>
					<td>可转让</td>
					<td>
						<a class="btn_samllss" href="javascript:void(0)">明细</a>
						<img class="btn_img" src="<%=basePath%>theme/default/images/operate.png" alt="还款">
						<!--<a class="btn_samllss ml10" href="javascript:void(0)">合同</a>-->
					</td>
				</tr> --%>
			</tbody>
		</table>
		<!-- 分页 -->
		<div id="duein_page"  class="page_div fr">
			<!-- <ul class="page_ul">
				<li>&lt;</li>
				<li class="nowpage">3</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				<li>5</li>
				<li>6</li>
				<li>&gt;</li>
			</ul> -->
		</div>
	</div>
	
	<%-- 招标中 --%>
	<div class="invest_table inv_js none">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="25%">投资时间</th>
					<th width="13%">编号</th>
					<th width="13%">投资金额</th>
					<th width="13%">投资利息</th>
					<th width="13%">投资奖励</th>
					<th width="13%">加息收益</th>
					<th width="10%">状态</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody id="invite_tenders_list_rt">
				<%-- <tr>
					<td class="pl30">
						<p class="cchu">质押贷款借款HY34质押贷款借款HY34</p>2015-01-01 08:08:08</td>
					<td class="">D15102301</td>
					<td>￥2001334.01</td>
					<td>￥2001334.01</td>
					<td>￥2001334.01</td>
					<td>￥2334.01</td>
					<td>招标中</td>
					<td><img class="btn_img fl" src="<%=basePath%>theme/default/images/operate.png" alt="还款"></td>
				</tr> --%>
				
			</tbody>
		</table>
		<!-- 分页 -->
		<div id="invite_tenders_page" class="page_div fr">
			
		</div>
	</div>
	
	<%-- 转让中 --%>
	<div class="invest_table inv_js none">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="22%">发布时间</th>
					<th width="14%">债权编号</th>
					<th width="10%">期限</th>
					<th width="14%">项目价值</th>
					<th width="14%">转让价格</th>
					<th width="13%">状态</th>
					<th width="13%">操作</th>
				</tr>
			</thead>
			<tbody id="transfer_posse_list">
				<%-- <tr>
					<td class="pl30">
						<p class="cchu">质押贷款借款HY34质押贷款借款HY34</p>2015-01-01 08:08:08</td>
					<td class="">d1513246</td>
					<td>12个月</td>
					<td>￥1234.02</td>
					<td>￥2234.02</td>
					<td>转让中</td>
					<td><img class="btn_img fl" src="<%=basePath%>theme/default/images/operate.png" alt="还款"></td>
				</tr> --%>

			</tbody>
		</table>
		<!-- 分页 -->
		<div id="transfer_posse_page" class="page_div fr">
		</div>
	</div>
	
	<%-- 已结清 --%>
	<div class="invest_table inv_js none">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="25%">投资时间</th>
					<th width="15%">编号</th>
					<th width="15%">投资金额</th>
					<th width="10%">期限</th>
					<th width="10%">年化率</th>
					<th width="10%">利息</th>
					<th width="15%">投资奖励</th>
					<th width="13%">加息收益</th>
					<th width="13%">状态</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody id="closed_account_list">
				<%-- <tr>
					<td class="pl30">
						<p class="cchu">质押贷款借款HY34质押贷款借款HY34</p>2015-01-01 08:08:08</td>
					<td class="">D15102301</td>
					<td>￥201111.02</td>
					<td>12个月</td>
					<td>14%</td>
					<td>￥20.02</td>
					<td>￥123.04</td>
					<td>￥223.04</td>
					<td>已结清</td>
					<td><a class="btn_samllss fl" href="javascript:void(0)">明细</a><img class="btn_img fl" src="<%=basePath%>theme/default/images/operate.png" alt="还款"></td>
				</tr> --%>
	
			</tbody>
		</table>
		<!-- 分页 -->
		<div id="closed_account_page" class="page_div fr">
		</div>
	</div>
	
	<div class="clearfix"></div>
</div>


<%-- 脚本区 --%>
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/myinvestment/myInvestIndex.js"></script>