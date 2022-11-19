<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>  
<%@include file="/common/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=basePath%>theme/default/css/drop.css"> 
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/simpleMath.js"></script>
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/eplan/financialProductManage.js"></script>

<div id="banner_tabs" class="flexslider">
	  
</div>
 <div class="dr_2 w1140 m_auto">
			<div class="ge_ye_tilte">
				<span class="ge_ye_tilte_nav ms_t_se" >e计划</span> 
				<span class="ge_ye_tilte_nav"  onclick="javascript:window.location.href='<%= basePath%>business/optionalInvestController/index/toOptionalInvestList.shtml'">e首房/e抵押</span> 
				<span class="ge_ye_tilte_nav" onclick="javascript:window.location.href='<%= basePath%>mybids/transferController/index/toTransferList.shtml'">债权转让</span>
			</div>
			<div class="dr_2_bd mt20">
					<div class="pt20 dr_sx">
			                    <p class="c2980b9 mb5 dr_sx_p">
			                        <span class="size14 colorDarkBlue">项目期限：</span>
			                        <i class="i_  ml5 dr_sx_i"  onclick="setFormValue('','','borDeadline',this);">不限</i> 
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('','3','borDeadline',this);">1-3月</i>
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('3','9','borDeadline',this);">3-9月</i>
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('9','12','borDeadline',this);">9-12月</i>
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('12','','borDeadline',this);">12月以上</i>
			                    </p>
			                    <p class="c2980b9 mb5 dr_sx_p">
			                        <span class="size14 colorDarkBlue">年化利率：</span>
			                        <i class="i_  ml5 dr_sx_i"  onclick="setFormValue('','','borrowRate',this);">不限</i> 
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('','9','borrowRate',this);">9%以下</i>
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('9','12','borrowRate',this);">9%-12%</i>
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('12','16','borrowRate',this);">12%-16%</i> 
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('16','','borrowRate',this);">16%以上</i> 
			                    </p>
			                    <p class="c2980b9 mb5 dr_sx_p">
			                        <span class="size14 colorDarkBlue">项目状态：</span>
			                        <i class="i_  ml5 dr_sx_i" onclick="setFormValue('','','borStatus',this);">不限</i> 
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('2','','borStatus',this);">进行中</i>
			                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('4','','borStatus',this);">已结束</i>
			                    </p>
			           </div>
			           <form id="conditionForm">    
							<input type="hidden" id="borrowRateMin" name="borrowRateMin" /><!--检索开始的年利率 -->
							<input type="hidden" id="borrowRateMax" name="borrowRateMax" /><!--检索结束的年利率 -->
							<input type="hidden" id="borDeadlineMin" name="borDeadlineMin" /><!--期限 -->
							<input type="hidden" id="borDeadlineMax" name="borDeadlineMax" /><!--期限 -->
							<input type="hidden" id="borStatus" name="borStatus" /><!--借款状态 -->
						</form>
			</div>
				<ul class="etabConUl" id="brrowDetailList"> 
				 
                </ul>
                <div class=" tc oh kong100">  
			          <div id="page" class="m-pagination"></div>
			      </div>
               
</div>   