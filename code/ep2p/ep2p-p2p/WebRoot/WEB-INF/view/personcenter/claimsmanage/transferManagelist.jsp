<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/personcenter/claimsmanage/transferManage.js"></script>
<!-- 债权管理，个人中心页面 -->
 <div class="sidebar_b borrow_div fl"> 
     <div class="fl title_div" id="credit_area"> 
     <input type="hidden" id="flag" value="${flag }">
      <ul id="typeUl" class="type_title fl"> 
       <li class="change_li" onclick="loadData('1',null);"> <a href="javascript:void(0)">已持有债权</a> </li> 
       <li onclick="loadData('3',null);"> <a href="javascript:void(0)" >已转让债权</a> </li> 
       <li class="fr" style="width:450px;height:24px;"></li> 
      </ul> 
    
     </div> 
       <!--已持有债权表格--> 
       <div class="invest_table" id="tb_01"> 
	      <div class="find_area fl"> 
		     <form id="holdClaimForm">
			       <img src="<%=basePath%>/theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" /> 
			       <input type="text" name="startReceiptTime" readonly="readonly" onFocus="WdatePicker()" /><i class="i_ ml5 mr5">-</i><input type="text" readonly="readonly" name="endReceiptTime" onFocus="WdatePicker()" />  <a class="btn_samll" onclick="loadData('1','holdClaimForm');" href="javascript:void(0)"> 查询 </a>
		     </form>
	      </div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t" > 
         <thead> 
	          <tr> 
		           <th width="15%"> 待收时间 </th> 
		           <th width="10%"> 债权编号 </th> 
		           <th width="15%"> 项目名称 </th> 
		           <th width="10%"> 期次 </th> 
		           <th width="10%"> 待收本息 </th> 
		           <th width="10%"> 状态 </th> 
		           <th width="10%"> 购买时间 </th> 
		           <th width="20%"> 操作 </th> 
	          </tr> 
         </thead> 
         <tbody id="holdClaim" > 
         </tbody> 
        </table> 
        <!-- 分页 --> 
        <div id="holdClaimPage" class="m-pagination page_div fr"></div>
        
       </div> 
       
       
       <!--已转让债权表格---> 
       <div class="invest_table" id="tb_03"> 
          <div class="find_area fl"> 
		     <form id="transferedClaimForm">
			       <img src="<%=basePath%>/theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" /> 
			       <input type="text" name="startReceiptTime" readonly="readonly" onFocus="WdatePicker()" /><i class="i_ ml5 mr5">-</i><input type="text" name="endReceiptTime" readonly="readonly" onFocus="WdatePicker()"/>  <a class="btn_samll" onclick="loadData('3','transferingClaimPage');" href="javascript:void(0)"> 查询 </a> </em> </em> 
		     </form>
	      </div> 

       
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
         <thead> 
          <tr> 
           <th width="10%"> 待收时间 </th> 
           <th width="10%"> 债权编号 </th> 
           <th width="10%"> 项目名称 </th> 
           <th width="10%"> 期次 </th> 
           <th width="10%"> 待收本息 </th> 
           <th width="10%"> 状态 </th> 
           <th width="10%"> 转让价格 </th> 
           <th width="10%"> 年化利率 </th> 
           <th width="10%"> 转让时间 </th> 
           <th width="10%"> 操作 </th> 
          </tr> 
         </thead> 
         <tbody id="transferedClaim"> 
         </tbody> 
        </table> 
        <!-- 分页 --> 
          <div id="transferedClaimPage" class="m-pagination"></div>
       </div> 
       <div class="clearfix"> 
       </div>
       <div class="bannerBox">
        	<img src="${bannerUrl}" />
        </div>
    </div> 
    
    
<!-- 撤消弹层 s-->
<div id="revoke" class="goumai_vip none credito_t_js size16">
	<form id="revokeForm" action="">
	<input type="hidden" id="transferId" name="transferId">
		<div>
			<div class="kong15"></div> 
			<div class="vip_g_tc">
				 <p class="vip_g_t size16 tc">撤消债权</p>
			</div>
			<div class="kong25"></div>
			<div class="vip_g_lists m_auto colorDarkBlue size14">
				<p class="vip_g_list">
					债权总价:<span class="colorc vip_w_145 inline_block plr10" id="totalAmount"></span>剩余待收天数:<span class="colorc inline_block plr10"  id="returnDate"></span>
				</p>
				<p class="vip_g_list">
				            转让价格:<span class="colorc plr10" id="transferAmount"></span>
				</p>
				<p class="vip_g_list">
				            转让净收:<span class="colorc plr10" id="investAmount"></span>
				</p>
				<p class="vip_g_list">
				            年利率:<span class="colorc plr10" id="yr"></span>
				</p>
				<p class="vip_g_list">
					交易密码:<input type="password" id="pwd" name="pwd" maxlength="10" onkeyup="clearErrorMsg()"  class="ml10"><span id="tradeError"></span>
				</p>
				<br />
				<p class="vip_g_list size14 line_height15 size12">
					提示：债权转让撤销后，债权撤回到我的投资中，若要再次转让， 请到我的投资中的项目明细执行债权转让操作。
				</p>
				<div class="kong25"></div>
			</div>
			<div class="vip_g_but">
				<div class="w195 m_auto">
					<a class="btn_samll " href="javascript:void(0)" onclick="TransferRevoke.revoke()">确定</a><a class="btn_samll_gray ml30 credito_qu_js" href="javascript:void(0)" onclick="layer.closeAll()">取消</a>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- 撤消弹层弹层 e-->
<!-- 撤消失败弹窗 s-->
<div id="revokeFailure" class="goumai_vip none credito_t_js size16">
		<div class="vip_g m_auto" style="z-index: 0">
			<div class="kong15"></div> 
			<div class="vip_g_tc">
				 <p class="vip_g_t size16 tc">撤消债权</p>
			</div>
			<div class="kong25"></div>
			<div class="vip_g_lists m_auto colorDarkBlue size14">
				撤销出问题了！
			</div>
		</div>
</div>
<!-- 撤消弹层弹层 e-->
	
<script type="text/javascript">

$(document).ready(function(){
	// 债权转让管理加载,1.持有中  2.转让中  3.已转让
	var flag = $("#flag").val();
	
	if(flag == '1'){
		$("#typeUl li").eq(1).addClass("change_li");
		$("#typeUl li").eq(0).removeClass("change_li");
		$("#tb_02").show();
		$("#tb_01").hide();
		TransferManage.initData("2");
	}else{
		TransferManage.initData("1");
	}
	
});


</script>
