<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<div id="show_auto_bidding" class="sidebar_b borrow_div fl">
	<!--  自动投标 s-->
	<!--第一个zi是自动标列表s-->
	<div class="zi_ m_auto">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">自动投标记录</span>
			<c:if test="${fn:length(autolist) >= 3}">
				<a class="btn_samll_gray fr_ mt20 zi_tian" href="javascript:void(0)">添加</a>
			</c:if>
			<c:if test="${fn:length(autolist) < 3}">
				<a class="btn_samll fr_ mt20 zi_tian" href="javascript:void(0)">添加</a>
			</c:if>
		</div>

		<div class="kong30"></div>
		<div class="zi_tab">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
				<thead>
					<tr class="size16">
						<th width="15%">状态</th>
						<th width="11%">投资类型</th>
						<th width="22%">投资比例</th>
						<th width="11%">投资期限</th>
						<th width="15%">投资利率</th>
						<th width="7%">排名</th>
						<th width="19%">操作</th>
					</tr>
				</thead>
				<tbody id="auto_list" class="size14" listsize='<c:out value="${fn:length(autolist)}"></c:out>'>
				<c:forEach items="${autolist}" var="items">
					<tr>
						<td class="pl30">
							<c:if test="${items.autostatus == 1}">
								启用
							</c:if>
							<c:if test="${items.autostatus != 1}">
								禁用
							</c:if>
						</td>
						
						<td class="">
							<c:if test="${(items.amount > 0 && items.balanceratio <= 0) || (items.amount != null && items.balanceratio == null)}">
								固定金额
							</c:if>
							<c:if test="${items.amount <= 0 && items.balanceratio > 0}">
								固定比例
							</c:if>
						</td>
						
						<td>
						    <c:if test="${(items.amount > 0 && items.balanceratio <= 0) || (items.amount != null && items.balanceratio == null)}">
								固定 <fmt:formatNumber value="${items.amount}" pattern="#,##0.00#"></fmt:formatNumber>元
							</c:if>
							<c:if test="${items.amount <= 0 && items.balanceratio > 0}">
								余额<c:out value="${items.balanceratio}"></c:out>%
							</c:if>
						</td>
						
						<td>
							<c:if test="${(items.minborrowmonth == null && items.maxborrowmonth  == null) || (items.minborrowmonth == 0 && items.maxborrowmonth  == 0)}">
								不限
							</c:if>
							<c:if test="${(items.minborrowmonth != null && items.minborrowmonth != 0) || (items.maxborrowmonth != null && items.maxborrowmonth != 0)}">
								<c:out value="${items.minborrowmonth }"></c:out>
								月-
								<c:out value="${items.maxborrowmonth }"></c:out>月
							</c:if>
						</td>
						
						<td>
							
							<c:if test="${(items.minborrowrate <= 0 && items.maxborrowrate <= 0) || (items.minborrowrate == null && items.maxborrowrate == null)}">
								不限
							</c:if>
							<c:if test="${items.minborrowrate > 0 || items.maxborrowrate > 0}">
								<fmt:formatNumber value="${items.minborrowrate}" pattern="#,##0.00#"></fmt:formatNumber>
								%-
								<fmt:formatNumber value="${items.maxborrowrate}" pattern="#,##0.00#"></fmt:formatNumber>%
							</c:if>
						</td>
						
						<td><c:out value="${items.ranking}" default="-"></c:out></td>
						
						<td class="2980b9">
						<a href="javascript:void(0);" onclick="automaticbidList.updateAutoBidding('<c:out value="${items.pid}"></c:out>');" class="2980b9">修改</a>
						|<a href="javascript:void(0);" onclick="automaticbidList.deleteAutoBidding('<c:out value="${items.pid}"></c:out>');" class="2980b9">删除</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="kong40"></div>
		<div class="vip_shuo m_auto">
			<p class="p_t">
				<img src="<%=basePath %>theme/default/images/zi_1.png" class="fl_ mr10" />温馨提示
			</p>
			<p>
				1.自动投标最多允许添加1个规则,2个备用，有仅有一个规则能启用。
			</p>
			<p>
				2.当判断到有符合条件的规则时即为您自动投标，而后续的规则则不予采用。
			</p>
			<p>
				3.失效定义：如果设定的条件符合某一项目，但用户金额不够，则该值会加1，表明您错失了这次机会。当该值超过3次， 您的排名会在凌晨调整到当前队列最后一名。
			</p>
			<div class="kong70"></div>
		</div>
	</div>
	<!--第一个zi是自动标列表e-->
	<!--第二个zi是自动标设置s-->
	<div class="zi_ m_auto none">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">自动投标设置</span>
			<a class="btn_samll_gray fr_ mt20" href="javascript:void(0)" onclick="automaticbidList.addAutoBidding('0');">禁用</a>
			<a class="btn_samll fr_ mt20 mr20" href="javascript:void(0)" onclick="automaticbidList.addAutoBidding('1');">开启</a>
		</div>
		<form id="auto_bidding_add_form" action="">
		<div class="zi_sz_list size14 colorDarkBlue">
			<div class="kong30"></div>
			<div class="zi_sz_list_c zi_sz_list_a1">
				<span class="fl_ inline_block tc zi_sz_list_c_s1 ">
					<img src="<%=basePath %>theme/default/images/zi_5.png" class="" />
					<p class="zi_sz_list_cp1">
						每次投标金额
					</p>
				</span>
				<span class="fl_ ml25 zi_sz_list_c_s2">
					<p class="list_a_p1 c2980b9">
						<i class="radio_s radio_xiu1 m5 yu_g" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>按固定金额
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10">* </i>
						<input id="amount" name="amount" type="text" class="mr10">
						<i class="i_">元(最少50元)</i>	
					</p>
					<p class="list_a_p1">
						<i class="radio_s radio_xiu1 m5 yu_b" val="0"></i>按余额比例投资
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 none">* </i>
						<select id="balanceratio" name="balanceratio" class="mr10 none">
							<option value="10">10%</option> 
							<option value="20">20%</option> 
							<option value="30">30%</option> 
							<option value="40">40%</option> 
							<option value="50">50%</option>
							<option value="60">60%</option> 
							<option value="70">70%</option> 
							<option value="80">80%</option>
							<option value="90">90%</option> 
							<option value="100">100%</option> 
						</select>
					</p>
				</span>

			</div>
		</div>
		<div class="zi_sz_list size14 colorDarkBlue">
			<div class="kong30"></div>
			<div class="zi_sz_list_c zi_sz_list_b1">
				<span class="fl_ inline_block tc zi_sz_list_c_s1 ">
					<img src="<%=basePath %>theme/default/images/zi_4.png" class="" />
					<p class="zi_sz_list_cp1">
						投资期限
					</p>
				</span>
				<span class="fl_ ml25 zi_sz_list_c_s2">
					<p class="list_a_p2 c2980b9">
						<i id="qix_a" class="radio_s radio_xiu1 m5" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>不限
					</p>
					<p class="list_a_p2">
						<i id="qix_b" class="radio_s radio_xiu1 m5" val="0"></i>
						<i class="i_ yue_f">按月标范围</i>
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 none">* </i>
						<select id="minborrowmonth" name="minborrowmonth" class="none">
							<c:forEach var="minmthitems" varStatus="status" begin="1" end="36">
							 	<option value="${status.index }">${status.index }月</option> 
							</c:forEach>
						</select>
						<i class="i_ zi_sz_list_i inline_block mr5 none"> ~ </i>
						<select id="maxborrowmonth" name="maxborrowmonth" class="mr10 none">
							<c:forEach var="maxmthitems" varStatus="status" begin="1" end="36">
							 	<option value="${status.index }">${status.index }月</option> 
							</c:forEach> 
						</select>
						<span class="none"></span>
					</p>
				</span>

			</div>
		</div>
		<div class="zi_sz_list size14 colorDarkBlue">
			<div class="kong30"></div>
			<div class="zi_sz_list_c zi_sz_list_c1">
				<span class="fl_ inline_block tc zi_sz_list_c_s1 ">
					<img src="<%=basePath %>theme/default/images/zi_3.png" class="" />
					<p class="zi_sz_list_cp1">
						收益率
					</p>
				</span>
				<span class="fl_ ml25 zi_sz_list_c_s2">
					<p class="list_a_p3 c2980b9 ">
						<i id="sy_a" class="radio_s radio_xiu1 m5" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>不限
					</p>
					<p class="list_a_p3">
						<i id="sy_b" class="radio_s radio_xiu1 m5" val="0"></i>
						<i class="i_ shou_f">
							收益率范围
						</i>
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 none">* </i>
						<select id="minborrowrate" name="minborrowrate" class="none">
							<c:forEach var="minboritems" varStatus="status" begin="1" end="24">
							 	<option value="${status.index }">${status.index }%</option> 
							</c:forEach> 
						</select>
						<i class="i_ zi_sz_list_i inline_block mr5 none"> ~ </i>
						<select id="maxborrowrate" name="maxborrowrate" class="mr10 none">
							<c:forEach var="maxboritems" varStatus="status" begin="1" end="24">
							 	<option value="${status.index }">${status.index }%</option> 
							</c:forEach> 
						</select>
					</p>
				</span>

			</div>
		</div>
		<div class="zi_sz_list size14 colorDarkBlue">
			<div class="kong30"></div>
			<div class="zi_sz_list_c zi_sz_list_d1">
				<span class="fl_ inline_block tc zi_sz_list_c_s1 ">
					<img src="<%=basePath %>theme/default/images/zi_2.png" class="" />
					<p class="zi_sz_list_cp1">
						项目类型
					</p>
				</span>
				<span class="fl_ ml25 zi_sz_list_c_s2">
				<p class="list_a_p5">
					<span class="inline_block kong30"></span>
					<i class="i_" ><span id="tz_a" class="gou_s fl_" val="0"></span><span class="fl_ mr30 ml10">e计划</span></i>
					<i class="i_" ><span id="tz_b" class="gou_s fl_" val="0"></span><span class="fl_ mr30 ml10">e首房</span></i>
					<i class="i_" ><span id="tz_c" class="gou_s fl_" val="0"></span><span class="fl_ mr30 ml10">e抵押</span></i>
				</p>
				</span>

			</div>
		</div>
		<div class="kong45"></div>
		
		
		<div class="vip_shuo m_auto">
			<p class="p_t">
				<img src="<%=basePath %>theme/default/images/zi_1.png" class="fl_ mr10" />温馨提示
			</p>
			<p>
				1.当前规则满足时系统将为您自动投标，标的额度，投标金额和比例都只能为大于0的正整数。
			</p>
			<p>
				2.当按比例投标时，根据所设定的比例算得金额少于100元时，以100元进行投标。
			</p>
			<p>
				3.当按比例投标时，根据所设定的比例算得金额大于标的最大投标金额时，以最大投标金额进行投标。
			</p>
			<p class="">
				4.当按比例投标时，根据所设定的比例算得金额大于项目可投余额时，以项目可投余额进行投标。
			</p>
			<p class="">
				5.当按比例投标时，根据所设定的比例算得金额取整数位进行投标。
			</p>
			<p class="">
				6.自动投标排队规则：用户开启设定后，系统会分配排队号。
			</p>
			<p class="">
				7.用户设定规则投标成功后，则重新排队。
			</p>
			<p class="">
				8.规则从禁用到开启后会重新排队。
			</p>
			<div class="kong70"></div>
		</div>
		</form>
		<div class="kong150"></div>
	</div>
	<!--第二个zi是自动标设置s-->
	<!-- 自动投标 e-->
</div>

<%-- 脚本区 --%>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/accountoverview/autoabidding/automaticbidList.js"></script>