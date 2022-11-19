<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/helpcenter/calculator.js"></script> 
	<div class="w1200 m_auto">
		<!--计算器s-->
		<div class="kong60"></div>
		<div class=" m_auto ji_s">
			<div class="kong20"></div>
				<form id="calculatorForm" name="calculatorForm">
				    <div class="w330 fl_">
				    	<p class="size16 colorDarkBlue w120 lh33 pl15">还款方式</p>
							<span class="size16 colorDarkBlue ji_s_l fl_ inline_block bgffffff pos-r">
								<span class="oh_js">
									<ptpfm:select id="repaymentType" isdictory="true" dicKey="REPAYMENT_TYPE" optText="dictContName" optVal="dictContCode" defoption="--请选择--"  class="ji_s_l_se size16 colorDarkBlue"></ptpfm:select>
								</span>
							</span>
							<span class="kong45 inline_block"></span>
							<p class="size16 colorDarkBlue w120 lh33 pl15">投资金额</p>
							<span class="size16 colorDarkBlue ji_s_l fl_ inline_block bgffffff pos-r">
								<input type="text" class="ji_s_l_in" id="repaymentAmt" name="repaymentAmt"/>&nbsp;元
							</span>
				    </div>
				    <div class="w340 fl_ fr_bonder">
				    	<p class="size16 colorDarkBlue w120 lh33 pl15">年化率</p>
							<span class="size16 colorDarkBlue ji_s_l fl_ inline_block bgffffff pos-r">
								<input type="text" class="ji_s_l_ina"  id="repaymentRate" type="repaymentRate"/>&nbsp;%
							</span>
							<span class="kong45 inline_block"></span>
						<p class="size16 colorDarkBlue w120 lh33 pl15">投资期限</p>	
							<span class="size16 colorDarkBlue ji_s_l fl_ inline_block bgffffff pos-r">
								<input type="text" class="ji_s_l_inb" id="repaymentDeadline" type="repaymentDeadline" />&nbsp;期（月）
							</span>
					     <p class="kong20 cb"></p>
				    </div>
				    
				    
				    <div class="fr_ shouyi_r">
				    	<div class="kong40"></div>
				    	<p class="size62 lh60 colorc" id="proceeds">0.00<span class="size26">元</span></p>
				    	<p class="size14 colorDarkBlue pl10">预计收益</p>
				    	<p class="kong35"></p>
				    	<p class="">
				    		<span class="btn" onclick="calculator.execCalculator();">计算</span>
				    		<span class="btn" onclick="calculator.clearForm();">清空</span>
				    	</p>
				    </div>
			    </form>
		</div>
		<div class="kong30"></div>
		<div class="w1140 m_auto">
			<div class="ge_ye_tilte">
				<span class="ge_ye_tilte_nav  ms_t_se ">收益明细</span>
			</div>
			<div class="mt30">
				<table id="calculatorTable" width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
			         <thead> 
			          <tr>
			           <th width="11%">期次</th> 
			           <th width="17%">收款日期</th> 
			           <th width="17%">应收本息（元）</th> 
			           <th width="17%">应收本金（元）</th> 
			           <th width="17%">应收利息（元）</th> 
			           <th width="21%">剩余本金（元）</th> 
			          </tr>
			         </thead> 
			         <tbody id="calculatorTbody">
			         </tbody>
			        </table>
			</div>
			<div class="kong70"></div>
		</div>
		<!--计算器e-->
	</div>