<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--资金流水 -->
<div class="sidebar_b borrow_div fl">
	<!--  交易记录 s-->

	<div class="liu m_auto">
		<div class="ms_t">
			<a class=" ms_ta size16 tc inline_block ms_t_se jyjl_" href="javascript:cashFlow.loadData(6);">交易记录</a>
		</div>
		
		<div class="ms_c_t">
				<div class="kong15"></div>
				<div class="ms_c_t_list www">
				   <form action="" id="cashFlow_">
						<input type="text" class="fl_ w110  pl10" onFocus="WdatePicker()" id="beginTimes" name="beginTimes" value="">
						<span class="fl_ pl plr5"> - </span>
						<input type="text" class="fl_ w110 pl10" onFocus="WdatePicker()" id="endTime" name="endTime" value="">
						<a class="btn_samll_ fl_  ml20 " href="javascript:cashFlow.loadData();">查询</a>	
						<a class="liu_but btn_samll_ fr_  mr10 b0b0b0 bgffffff other" href="javascript:cashFlow.loadData(101);" val="101">其它</a>	
						<a class="liu_but btn_samll_ fr_  mr10 b0b0b0 bgffffff txls" href="javascript:cashFlow.loadData(4);" val="4">提现流水</a>
						<a class="liu_but btn_samll_ fr_  mr10 b0b0b0 bgffffff czls" href="javascript:cashFlow.loadData(3);" val="3">充值流水</a>
						<a class="liu_but btn_samll_ fr_  mr10 b0b0b0 bgffffff syjl" href="javascript:cashFlow.loadData(2);" val="2">收益记录</a>
						<a class="liu_but btn_samll_ fr_  mr10 b0b0b0 bgffffff tzjl" href="javascript:cashFlow.loadData(1);" val="1">投资记录</a>
				 </form>
				</div>
		</div>
		
		<div class="">
			<!--第一个liu_c是体现流水-->
				<div class="liu_cc ">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"  > 
				         <thead> 
				          <tr class="size16">
				           <th width="20%">日期</th> 
				           <th width="13%">收入</th> 
				           <th width="13%">支出</th> 
				           <th width="13%">账户余额</th> 
				           <th width="18%">交易类型</th> 
				           <th width="23%">备注</th> 
				          </tr>
				         </thead> 
				         <tbody class="size14 cash_flow">
				         </tbody>
				        </table>
				         <div id="cashFlowList" class="page_div fr">  </div>
				</div>
		</div>
	</div>
	<!-- 交易记录 e-->
</div>
<script type="text/javascript" src="${basePath}/theme/js/personcenter/fundsflow/userRightCashflow.js"></script>