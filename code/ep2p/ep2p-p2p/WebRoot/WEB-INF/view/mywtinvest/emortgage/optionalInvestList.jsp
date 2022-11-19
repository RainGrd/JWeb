<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>  
<%@include file="/common/taglibs.jsp" %>   

<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/emortgage/invest_list.js"></script> 
<div id="banner_tabs" class="flexslider">
	  
</div>	
<div class="dr_2 w1140 m_auto">
			<div class="ge_ye_tilte">
				<span class="ge_ye_tilte_nav " onclick="javascript:window.location.href='<%= basePath%>business/financialProductsManageController/index/toFinProdPage.shtml'">e计划</span> 
				<span class="  ge_ye_tilte_nav  ms_t_se" >e首房/e抵押</span> 
				<span class="  ge_ye_tilte_nav" onclick="javascript:window.location.href='<%= basePath%>mybids/transferController/index/toTransferList.shtml'">债权转让</span>
			</div>
			<div class="dr_2 mt20">
            	<div class="pt20 dr_sx">
            		<p class="c2980b9 mb5 dr_sx_p dr_sx">
            				<span class="size14 colorDarkBlue">项目类型：</span>
            				<i class="i_  ml5 dr_sx_i ml10" onclick="setFormValue('','','borrowType',this);">不限</i>
							<i class="i_ dr_sx_i_ ml10" onclick="setFormValue('2','','borrowType',this);">e首房</i>
							<i class="i_ dr_sx_i_ ml10" onclick="setFormValue('1','','borrowType',this);">e抵押</i> 
            		</p>
                    <p class="c2980b9 mb5 dr_sx_p dr_sx">
                        <span class="size14 colorDarkBlue">项目类型：</span>
                        <i class="i_  ml5 dr_sx_i ml10" onclick="setFormValue('','','borDeadline',this);">不限</i> 
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('1','3','borDeadline',this);">1-3月</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('3','9','borDeadline',this);">3-9月</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('9','12','borDeadline',this);">9-12月</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('12','','borDeadline',this);">12月以上</i>
                    </p>
                    <p class="c2980b9 mb5 dr_sx_p dr_sx">
                        <span class="size14 colorDarkBlue">年化利率：</span>
                        <i class="i_  ml5 dr_sx_i ml10" onclick="setFormValue('','','yearRate',this);">不限</i> 
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('','9','yearRate',this);">9%以下</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('9','12','yearRate',this);">9%-12%</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('12','16','yearRate',this);">12%-16%</i> 
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('16','','yearRate',this);">16%以上</i> 
                    </p>
                    <p class="c2980b9 mb5 dr_sx_p dr_sx">
                        <span class="size14 colorDarkBlue">项目状态：</span>
                        <i class="i_  ml5 dr_sx_i ml10" onclick="setFormValue('','','borStatus',this);">不限</i> 
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('2','','borStatus',this);">进行中</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('4','','borStatus',this);">已结束</i>
                        <i class="i_ dr_sx_i_ ml10" onclick="setFormValue('8','','borStatus',this);">已结清</i>
                    </p>
                </div>
                <form id="conditionForm">   
					<input type="hidden" id="borrowType" name="borrowType" /><!--借款类型 -->
					<input type="hidden" id="borrowRateMin" name="borrowRateMin" /><!--检索开始的年利率 -->
					<input type="hidden" id="borrowRateMax" name="borrowRateMax" /><!--检索结束的年利率 -->
					<input type="hidden" id="borDeadlineMin" name="borDeadlineMin" /><!--期限 -->
					<input type="hidden" id="borDeadlineMax" name="borDeadlineMax" /><!--期限 -->
					<input type="hidden" id="borStatus" name="borStatus" /><!--借款状态 -->
				</form>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t mt30"> 
		           <thead> 
		            <tr class="">
		             <th width="29%" >项目名称</th> 
		             <th width="15%">年化率</th> 
		             <th width="17%">金额</th> 
		             <th width="17%">期限</th> 
		             <th width="11%">进度</th> 
		             <th width="11%"></th> 
		            </tr>
		           </thead> 
		           <tbody class="ms_c_tabf size14" id="investList">
		             
		           </tbody>
		        </table> 
		        <div class=" tc oh kong100">  
			          <div id="page" class="m-pagination"></div>
			      </div>
			</div>  
</div>  