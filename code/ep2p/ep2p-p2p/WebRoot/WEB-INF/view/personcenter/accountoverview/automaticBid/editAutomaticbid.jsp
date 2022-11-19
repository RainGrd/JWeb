<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<!--第二个zi是自动标设置s-->
<div class="zi_ m_auto">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">自动投标设置</span>
		<a class="btn_samll_gray fr_ mt20" href="javascript:void(0)" onclick="automaticbidList.editAutoBidding('0');">禁用</a>
		<a class="btn_samll fr_ mt20 mr20" href="javascript:void(0)" onclick="automaticbidList.editAutoBidding('1');">开启</a>
	</div>
	<form id="auto_bidding_edit_form" action="">
	<input type="hidden" id="pid" name="pid" value="${abmodel.pid}">
	<input type="hidden" id="version" name="version" value="${abmodel.version}">
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
				<!-- 固定比率 -->
				<c:if test="${(abmodel.amount > 0 && abmodel.balanceratio <= 0) || (abmodel.amount != null && abmodel.balanceratio == null)}">
					<p class="list_a_p1 c2980b9">
						<i class="radio_s radio_xiu1 m5 yu_g" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>按固定金额
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10">* </i>
						<input id="amount" name="amount" type="text" class="mr10" value='<c:out value="${abmodel.amount}"></c:out>'>
						<i class="i_">元(最少50元)</i>	
					</p>
					<p class="list_a_p1">
						<i class="radio_s radio_xiu1 m5 yu_b" val="0"></i>按余额比例投资
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 none">* </i>
						<select id="balanceratio" name="balanceratio"  class="mr10 none">
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
				</c:if>
				<!-- 固定比例 -->
				<c:if test="${abmodel.amount <= 0 && abmodel.balanceratio > 0}">
					<p class="list_a_p1 ">
						<i class="radio_s radio_xiu1 m5 yu_g" val="0"></i>按固定金额
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 none">* </i>
						<input id="amount" name="amount" type="text" class="mr10 none" value=''>
						<i class="i_ none">元(最少50元)</i>	
					</p>
					<p class="list_a_p1 c2980b9">
						<i class="radio_s radio_xiu1 m5 yu_b" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>按余额比例投资
						<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 ">* </i>
						<select id="balanceratio" name="balanceratio"  class="mr10 ">
							<option value="10" <c:if test="${abmodel.balanceratio == 10}"> selected </c:if>>10%</option> 
							<option value="20" <c:if test="${abmodel.balanceratio == 20}"> selected </c:if>>20%</option> 
							<option value="30" <c:if test="${abmodel.balanceratio == 30}"> selected </c:if>>30%</option> 
							<option value="40" <c:if test="${abmodel.balanceratio == 40}"> selected </c:if>>40%</option> 
							<option value="50" <c:if test="${abmodel.balanceratio == 50}"> selected </c:if>>50%</option>
							<option value="60" <c:if test="${abmodel.balanceratio == 60}"> selected </c:if>>60%</option> 
							<option value="70" <c:if test="${abmodel.balanceratio == 70}"> selected </c:if>>70%</option> 
							<option value="80" <c:if test="${abmodel.balanceratio == 80}"> selected </c:if>>80%</option>
							<option value="90" <c:if test="${abmodel.balanceratio == 90}"> selected </c:if>>90%</option> 
							<option value="100" <c:if test="${abmodel.balanceratio == 100}"> selected </c:if>>100%</option> 
						</select>
					</p>
				</c:if>
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
			<!-- 固定 -->
			<c:if test="${(abmodel.minborrowmonth == null && abmodel.maxborrowmonth  == null) || (abmodel.minborrowmonth == 0 && abmodel.maxborrowmonth  == 0)}">
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
							 <option value="${status.index}" >${status.index }月</option> 
						</c:forEach> 
					</select>
					<i class="i_ zi_sz_list_i inline_block mr5 none"> ~ </i>
					<select id="maxborrowmonth" name="maxborrowmonth" class="mr10 none">
						<c:forEach var="maxmthitems" varStatus="status" begin="1" end="36">
							 <option value="${status.index }" >${status.index }月</option> 
						</c:forEach> 
					</select>
					<span class="none"></span>
				</p>
			</span>
			</c:if>
			
			<!-- 余额 -->
			<c:if test="${(abmodel.minborrowmonth != null && abmodel.minborrowmonth != 0) || (abmodel.maxborrowmonth != null && abmodel.maxborrowmonth != 0)}">
			<span class="fl_ ml25 zi_sz_list_c_s2">
				<p class="list_a_p2 ">
					<i id="qix_a" class="radio_s radio_xiu1 m5" val="0"></i>不限
				</p>
				<p class="list_a_p2 c2980b9">
					<i id="qix_b" class="radio_s radio_xiu1 m5" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>
					<i class="i_ yue_f">按月标范围</i>
					<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10" ">* </i>
					<select id="minborrowmonth" name="minborrowmonth" >
						<option value="0">不选</option> 
						<c:forEach var="minmthitems" varStatus="status" begin="1" end="36">
							 <option value="${status.index}" <c:if test="${abmodel.minborrowmonth == status.index}"> selected </c:if>>${status.index }月</option> 
						</c:forEach> 
					</select>
					<i class="i_ zi_sz_list_i inline_block mr5 " > ~ </i>
					<select id="maxborrowmonth" name="maxborrowmonth" class="mr10">
						<c:forEach var="maxmthitems" varStatus="status" begin="1" end="36">
							 <option value="${status.index }" <c:if test="${abmodel.maxborrowmonth == status.index}"> selected </c:if> >${status.index }月</option> 
						</c:forEach> 
					</select>
					<span class="" ></span>
				</p>
			</span>
			</c:if>
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
			<c:if test="${(abmodel.minborrowrate <= 0 && abmodel.maxborrowrate <= 0) || (abmodel.minborrowrate == null && abmodel.maxborrowrate == null)}">
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
							<option value="${status.index}" >${status.index}%</option>
						</c:forEach> 
					</select>
					<i class="i_ zi_sz_list_i inline_block mr5 none"> ~ </i>
					<select id="maxborrowrate" name="maxborrowrate" class="mr10 none">
						<c:forEach var="maxboritems" varStatus="status" begin="1" end="24">
							<option value="${status.index}" >${status.index}%</option>
						</c:forEach>
					</select>
				</p>
			</span>
			</c:if>
			
			<c:if test="${abmodel.minborrowrate > 0 || abmodel.maxborrowrate > 0}">
			<span class="fl_ ml25 zi_sz_list_c_s2">
				<p class="list_a_p3  ">
					<i id="sy_a" class="radio_s radio_xiu1 m5" val="0"></i>不限
				</p>
				<p class="list_a_p3 c2980b9">
					<i id="sy_b" class="radio_s radio_xiu1 m5" val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>
					<i class="i_ shou_f">
						收益率范围
					</i>
					<i class="i_ colorc zi_sz_list_i inline_block mr5 ml10 ">* </i>
					<select id="minborrowrate" name="minborrowrate" class="">
						<c:forEach var="minboritems" varStatus="status" begin="1" end="24">
							<option value="${status.index}" 
								<c:if test="${not ((abmodel.minborrowrate < status.index) or (abmodel.minborrowrate > status.index))}"> selected </c:if> >
							${status.index}%
							</option>
						</c:forEach> 
					</select>
					<i class="i_ zi_sz_list_i inline_block mr5 "> ~ </i>
					<select id="maxborrowrate" name="maxborrowrate" class="mr10 ">
						<c:forEach var="maxboritems" varStatus="status" begin="1" end="24">
							<option value="${status.index}" 
								<c:if test="${not ((abmodel.maxborrowrate < status.index) or (abmodel.maxborrowrate > status.index))}"> selected </c:if> >
							${status.index}%
							</option>
						</c:forEach>
					</select>
				</p>
			</span>
			</c:if>

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
				<c:if test="${abmodel.borrowType != null}">
					<c:if test='${fn:contains(abmodel.borrowType,"1")}'>
					<i class="i_ c2980b9" >
						<span id="tz_a" class="gou_s fl_" val="1">
						<img src="<%=basePath %>theme/default/images/gou_b.png" class="block">
						</span>
						<span class="fl_ mr30 ml10">e计划</span>
					</i>
					</c:if>
					<c:if test='${!fn:contains(abmodel.borrowType,"1")}'>
					<i class="i_">
						<span id="tz_a" class="gou_s fl_" val="0"></span>
						<span class="fl_ mr30 ml10">e计划</span>
					</i>
					</c:if>
					
					<c:if test='${fn:contains(abmodel.borrowType,"2") }'>
					<i class="i_ c2980b9" >
						<span id="tz_b" class="gou_s fl_" val="1">
						<img src="<%=basePath %>theme/default/images/gou_b.png" class="block">
						</span>
						<span class="fl_ mr30 ml10">e首房</span>
					</i>
					</c:if>
					<c:if test='${!fn:contains(abmodel.borrowType,"2") }'>
					<i class="i_">
						<span id="tz_b" class="gou_s fl_" val="0"></span>
						<span class="fl_ mr30 ml10">e首房</span>
					</i>
					</c:if>
					
					<c:if test='${fn:contains(abmodel.borrowType,"3") }'>
					<i class="i_ c2980b9" >
						<span id="tz_c" class="gou_s fl_" val="1">
						<img src="<%=basePath %>theme/default/images/gou_b.png" class="block">
						</span>
						<span class="fl_ mr30 ml10">e抵押</span>
					</i>
					</c:if>
					<c:if test='${!fn:contains(abmodel.borrowType,"3") }'>
					<i class="i_">
						<span id="tz_c" class="gou_s fl_" val="0"></span>
						<span class="fl_ mr30 ml10">e抵押</span>
					</i>
					</c:if>
				</c:if>
				<c:if test="${abmodel.borrowType == null}">
					<i class="i_" ><span id="tz_a" class="gou_s fl_" val="0"></span><span class="fl_ mr30 ml10">e计划</span></i>
					<i class="i_" ><span id="tz_b" class="gou_s fl_" val="0"></span><span class="fl_ mr30 ml10">e首房</span></i>
					<i class="i_" ><span id="tz_c" class="gou_s fl_" val="0"></span><span class="fl_ mr30 ml10">e抵押</span></i>
				</c:if>
			</p>
			</span>

		</div>
	</div>
	</form>
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

	<div class="kong150"></div>
</div>
<!--第二个zi是自动标设置s-->

<%-- 局部样式区域 --%>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/accountoverview/autoabidding/automaticbidList.js"></script>