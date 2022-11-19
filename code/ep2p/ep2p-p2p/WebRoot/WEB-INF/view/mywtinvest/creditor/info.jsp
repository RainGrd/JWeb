<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>
	<!--内容区-->
        <div class="content mt40">
			<div class="frame1000 bgc oh">
            	<!--头部灰色框-->
                <div class="dropConTop">
                	<div class="dropConTopLeft">
                    	<div class="dropClaimsLeft">
                            <p class="size16 colorDarkBlue">债权转让编号：<c:out value="${vo.transferCode }" /></p>
                            <p class="size14 dropBlue"><span class="btn_samllss mr10"><c:out value="${vo.borrowTypeName }"></c:out></span>
                            	<%--e首房，e抵押 --%>
                            	<c:if test="${vo.borrowType == '1' || vo.borrowType == '2' }">
                            		<a  href="<%=basePath %>business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=${vo.borrowId}"><c:out value="${vo.borrowName }" /> <c:out value="${vo.borrowCode }" /></a>
                            	</c:if>
                            	<%--e计划 --%>
                            	<c:if test="${vo.borrowType == '3'}">
                            		<a  href="<%=basePath %>business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=${vo.borrowId}"><c:out value="${vo.borrowName }" /> <c:out value="${vo.borrowCode }" /></a>
                            	</c:if>
                            </p>
                        </div>
                        <div class="dropClaimsRight" style="margin-top:-4px;">
                            <p class="colorc size26 lineHeight62"><span class="size62"><fmt:formatNumber value="${vo.profitRate*100 }" pattern="##0.##"/> </span><i class="size26">%</i></p>
                            <p class="colorDarkBlue size14">年化利率</p>
                        </div>
                        <div class="clear"></div>
                        <ul class="dropEPlanUl dropEPlanUl3" style="margin:-6px 0 37px 0;">
                            <li class="mt30 dropEPlanLi4">
                            	<p class="colorc size20">￥<fmt:formatNumber value="${empty(vo.projectValue)?0:vo.projectValue }" pattern="#,##0.00"/> </p>
                                <p class="colorDarkBlue size14">项目价值</p>
                            </li>
                            <li class="mt30 dropEPlanLi4">
                            	<p class="colorc size20">￥<fmt:formatNumber value="${empty(vo.successAmount)?0:vo.successAmount }" pattern="#,##0.00"/></p>
                                <p class="colorDarkBlue size14">转让价格</p>
                            </li>
                            <li class="mt30 dropEPlanLi4">
                            	<p class="colorc size20">${empty(vo.timeRemaining)?0:vo.timeRemaining }个月</p>
                                <p class="colorDarkBlue size14">期限</p>
                            </li>
                            <li class="mt30 dropEPlanLi5">
                            	<p class="colorc size20"><fmt:formatDate value="${vo.interestData }" pattern="yyyy-MM-dd" /></p>
                                <p class="colorDarkBlue size14">起息日</p>
                            </li>
                        </ul>
                        <div class="fl dropBlue size12 mt010">
                        	<p class="tyb_a_l_i1 bgffffff">本息保障</p>
                            <!--<p class="tyb_a_l_i1 bgffffff ml10">免服务费</p>-->
                        </div>
                        <p class="repayment size14 mt20">还款方式：<c:out value="${vo.repayTypeName }" /></p>
                        <p class="dropTime size14 mt20">转让人：<c:out value="${vo.createUserName }"/></p>
                    </div>
                	<div class="dropConTopRight">
                	   <c:if test="${vo.status == '1' }">
                    	   <p class="colorDarkBlue size14 mt50">剩余时间: <span id="countDown">00时00分00秒</span></p>
                     
	                        <c:if test="${empty(user) }">
	                    	 <p class="colorDarkBlue size12" id="btn">
	                     	<a class="btn dropJionBtn mt50" href="<%=basePath %>login/loginController/toLogin.shtml">立即购买</a>
	                     	</p>
		                     </c:if>
		                     <c:if test="${!empty(user) }">
	                     	 <p class="colorDarkBlue size12" id="btn">可用余额:
		                     <c:if test="${(empty(ca.availableAmount)?0:ca.availableAmount) < vo.successAmount }">
		                		<span class="colororg">￥<fmt:formatNumber value="${empty(ca.availableAmount)?0:ca.availableAmount  }" pattern="#,##,##0.00" /></span> 余额不足<a href="<%=basePath %>recharge/userRechargeController/toUserRecharge.shtml" class="btn_samll" style="padding:3px 7px;margin-left:10px; font-size:12px;">充值</a>
		                        <a class="btn_gray  dropJionBtn mt50" href="javascript:void(0)">立即购买</a>
		                     </c:if>
		                     <c:if test="${ca.availableAmount >= vo.successAmount }">
		                    	<span class="colororg">￥<fmt:formatNumber value="${empty(ca.availableAmount)?0:ca.availableAmount  }" pattern="#,##,##0.00" /></span>
		                        <a class="btn dropJionBtn mt50" href="javascript:okBuy()">立即购买</a>
		                     </c:if>
		                     </p>
		                 	</c:if>
	                 	</c:if>
	                 	<c:if test="${vo.status != '1' }">
	                 	 	<p class="colorDarkBlue size14 mt50">&nbsp;</p>
	                 		<a class="btn_gray  dropJionBtn mt50" href="javascript:void(0)">已转让</a>
	                 	</c:if>
                        <div class="dropAgreeBox colorDarkBlue c2980b9">
                        	<div class="gou_s" val="1"></div>
                            <div class=" size14 dropAgree"><a target="_blank" href="<%=basePath %>mybids/contractController/transfer/tempcontract.shtml" >同意《e生财富债权转让协议》</a></div>
                        </div>
                    </div>
                </div>
                <div class="dropConBox">
                	<div class="ge_ye_tilte">
                        <span class="ge_ye_tilte_nav  ms_t_se ">转让介绍</span><span class=" ge_ye_tilte_nav  ">还款计划</span>
                    </div>
                    <div class="dropTab">
                        <p class="size14 eRoomInfo mt40">
                            <span class="textalignR gray">说明</span>
                            <span class="textalignL colorDarkBlue">e生财富平台债权转让交易系统是为解决用户投资流动性需求而设立，旨在帮助广大e生财富用户解决资金的流动性，增加用户资金体验而设计，用户可以通过e生财富转让交易系统出售所拥有的符合相应条件的债权项目给其他投资人，从而完成债权转让，获得流动资金。<a href="javascript:;" class="dropblue">查看更多&gt;&gt;</a></span>
                        </p>
                        <p class="size14 eRoomInfo">
                            <span class="textalignR gray">担保机构</span>
                            <span class="textalignL colorDarkBlue">${vo.org }</span>
                        </p>
                        <p class="size14 eRoomInfo">
                            <span class="textalignR gray">更多</span>
                            <span class="textalignL colorDarkBlue">
                            <%--e首房，e抵押 --%>
                            	<c:if test="${vo.borrowType == '1' || vo.borrowType == '2' }">
                            		<a  class="standard" href="<%=basePath %>business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=${vo.borrowId}">查看原标情况</a>
                            	</c:if>
                            	<%--e计划 --%>
                            	<c:if test="${vo.borrowType == '3'}">
                            		<a  class="standard" href="<%=basePath %>business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=${vo.borrowId}">查看原标情况</a>
                            	</c:if>
                            </span>
                        </p>
                    </div>
                    <div class="dropTab none">
                    	<p class="investTableP f-14"><span>总计：<i><c:out value="${empty(list)?0:list.size() }" />个月</i></span><span>应收本息：<i>￥<fmt:formatNumber value="${empty(benxi)?0:benxi}" pattern="#,##0.00"/></i></span><span>应收本金：<i>￥<fmt:formatNumber value="${empty(benjin)?0:benjin}" pattern="#,##0.00"/></i></span><span>应收利息：<i>￥<fmt:formatNumber value="${empty(lixi)?0:lixi}" pattern="#,##0.00"/></i></span></p>
                    	<div class="invest_table"> 
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t mt30 f-12"> 
                           <thead> 
                            <tr>
                             <th width="17%">期次</th> 
                             <th width="17%">还款时间</th> 
                             <th width="17%">应收本金</th> 
                             <th width="17%">应收利息</th> 
                             <th width="17%">应收本息</th> 
                             <th width="15%">剩余本金</th>
                            </tr>
                           </thead> 
                           <tbody>
                            <c:forEach var="r" items="${list }" varStatus="s">
	                            <tr> 
		                             <td>${s.count }</td>
		                             <td><c:out value="${r.RDate }"/></td> 
		                             <td>￥<fmt:formatNumber value="${empty(r.capital)?0:r.capital}" pattern="#,##0.00"/> </td> 
		                             <td>￥<fmt:formatNumber value="${empty(r.interest)?0:r.interest}" pattern="#,##0.00"/></td> 
		                             <td>￥<fmt:formatNumber value="${(empty(r.capital)?0:r.capital)+(empty(r.interest)?0:r.interest)}" pattern="#,##0.00"/></td> 
		                             <td>￥<fmt:formatNumber value="${empty(r.remainingCapital)?0:r.remainingCapital}" pattern="#,##0.00"/></td>
	                            </tr> 
                            </c:forEach>
                           </tbody>
                          </table> 
                         </div>
                    </div>
                </div>
            </div>
        </div>
        <!--内容区End-->
     <div class="dropPopupBox" id="okBuy" style="top:0px">
     	<div class="dropPopupH4">
     		<h4 class="size16 dropBlue">购买确认</h4>
        </div>
        <div class="dropPopupTab">
        	<div class="dropPopupTabCon">
            	<p class="size16 colorDarkBlue">债权转让编号：<c:out value="${vo.transferCode }" /></p>
                <p class="size14 dropBlue"><span class="backgroundBlue mr10"><c:out value="${vo.borrowTypeName }" /></span>
                	<%--e首房，e抵押 --%>
                   	<c:if test="${vo.borrowType == '1' || vo.borrowType == '2' }">
                   		<a  href="<%=basePath %>business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=${vo.borrowId}"><c:out value="${vo.borrowName }" /> <c:out value="${vo.borrowCode }" /></a>
                   	</c:if>
                   	<%--e计划 --%>
                   	<c:if test="${vo.borrowType == '3'}">
                   		<a  href="<%=basePath %>business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=${vo.borrowId}"><c:out value="${vo.borrowName }" /> <c:out value="${vo.borrowCode }" /></a>
                   	</c:if>
                </p>
                <table class="dropPopupTable">
                	<tr>
                        <td>年化利率：</td>
                        <td><fmt:formatNumber value="${vo.profitRate*100 }" pattern="##0.##"/> </span>%</td>
                        <td>期限：</td>
                        <td>${empty(vo.timeRemaining)?0:vo.timeRemaining }个月</td>
                    </tr>
                    <tr>
                    	<td>起息日：</td>
                        <td><fmt:formatDate value="${vo.interestData }" pattern="yyyy-MM-dd" /></td>
                        <td>还款方式：</td>
                        <td>按月分期付款</td>
                    </tr>
                	<tr>
                    	<td>项目价值：</td>
                        <td>￥<fmt:formatNumber value="${empty(vo.projectValue)?0:vo.projectValue }" pattern="#,##0.00"/></td>
                        <td>转让价格</td>
                        <td>￥<fmt:formatNumber value="${empty(vo.successAmount)?0:vo.successAmount }" pattern="#,##0.00"/></td>
                    </tr>
                </table>
                <div class="transfer_info dropPopupInfo fl size14">
                  <form id="buyTransferForm">
					<input type="hidden" name="pid" value="${vo.pid }">
                    <div class="ti1">
                        <div style="width:50%;" class="fl">可用余额：<span class="colororg">￥<fmt:formatNumber value="${ca.availableAmount  }" pattern="#,##,##0.00" /></span></div>
                    </div>
                   <div class="ti2" style="margin-top:16px;">
                        <div>交易密码：<input type="password" id="pwd" maxlength="6" style="width:116px;" class="pl10"/>
                        <input type="hidden" id="rsaPwd" name="pwd" style="width:116px;" class="pl10"/>
						<span id="tradeError" class="input_tis ts_re  mt5" style="margin-left: 5px;padding:0 5px ;display: none;">
						</span>
                        </div>
                    </div>
                    <div class="kong5"></div>
                    <div class="ti4" style="padding-left: 70px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" id="dropPopupSure" style="margin-right: 20px;" href="javascript:buy();">确定</a> <a class="btn_samll_gray" href="javascript:layer.closeAll()" id="dropPopupDone">取消</a> </div>
				</form>
				</div>
            </div>
        </div>
     </div>
<!-- 成功 -->
<div class="dropPopupBox2" style="top:0px" id="buyClaimSuc">
  	<div class="dropPopupH4">
  		<h4 class="size16 dropBlue">投资成功</h4>
     </div>
     <div class="dropPopupSuessBox">
     	<div class="dropPopupSuess">
         	<h4 class="size18 colorDarkBlue"><span class="yes"></span>恭喜，债权转让编号：<c:out value="${vo.transferCode }" /></h4>
             <h2 class="size24 colororg">购买成功！</h2>
             <p><span id="sc">10</span>秒后自动跳转到系统首页</p>
         </div>
         <div class="dropPopupSuessInfo">
<!--          	<ul> -->
<%--                  <li>转让价格：<span class="colororg">￥<fmt:formatNumber value="${vo.successAmount }" pattern="#,##0.00"/></span></li> --%>
<%--                  <li>收益金额：<span class="colororg">￥<fmt:formatNumber value="${(empty(vo.amount)?0:vo.amount) - (empty(vo.successAmount)?0:vo.successAmount)  }" pattern="#,##0.00"/></span></li> --%>
<%--                  <li>可获积分：<span class="colororg"><span><c:out value="${integral}" /></span></li> --%>
<!--              </ul> -->
             <div class="ti4" style="padding-left:10px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" style="margin-right: 20px;" href="<%=basePath%>index/homepController/toIndex.shtml">再逛逛</a> <a class="btn_samll" href="<%=basePath%>mybids/transferController/toTransferManage.shtml">我的投资</a> </div>
         </div>
     </div>
  </div>
  
  
<!-- 失败 -->
<div class="dropPopupBox2" id="buyClaimError" style="top:0px">
  	<div class="dropPopupH4">
  		<h4 class="size16 dropBlue">购买债权失败</h4>
     </div>
     <div class="dropPopupSuessBox">
     	<div class="dropPopupSuess">
         	<h4 class="size18 colorDarkBlue"><span class="yes"></span>很遗憾，债权转让编号：<c:out value="${vo.transferCode }" /></h4>
             <h2 class="size24 colororg">购买失败，很可能别人抢先了一步！</h2>
             <p><span id="sc2">10</span>秒后自动跳转到系统首页</p>
         </div>
         <div class="dropPopupSuessInfo">
<!--          	<ul> -->
<%--              	 <li>债权总价：<span class="colororg">￥<fmt:formatNumber value="${vo.amount }" pattern="#,##0.00"/></span></li> --%>
<%--                  <li>转让价格：<span class="colororg">￥<fmt:formatNumber value="${vo.successAmount }" pattern="#,##0.00"/></span></li> --%>
<%--                  <li>收益金额：<span class="colororg">￥<fmt:formatNumber value="${(empty(vo.amount)?0:vo.amount) - (empty(vo.successAmount)?0:vo.successAmount)  }" pattern="#,##0.00"/></span></li> --%>
<%--                  <li>可获积分：<span class="colororg"><span><c:out value="${integral}" /></span></li> --%>
<!--              </ul> -->
             <div class="ti4" style="padding-left:10px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" style="margin-right: 20px;" href="<%=basePath%>index/homepController/toIndex.shtml">再逛逛</a> <a class="btn_samll" href="<%=basePath%>mybids/transferController/toTransferManage.shtml">我的投资</a> </div>
         </div>
     </div>
  </div>
<script type="text/javascript">
	var sysDate = "${sysDate}";
	var expireDate = "${expireDate}";
	var d = parseInt((expireDate - sysDate)/1000);
	// 弹窗index
	var index = -1;
	$(document).ready(function(){
		$(".gou_s").html('<img src="'+ basePath + 'theme/default/images/gou_b.png" class="block" />');
		$(".gou_s").attr("val","1");
		$(".gou_s").parent().addClass("c2980b9");
		escfutil.gougou(".dropAgreeBox",'');
		
		$(".ge_ye_tilte_nav").click(function(){
			var eq = $(this).index(".ge_ye_tilte_nav");
			$(".ge_ye_tilte_nav").removeClass("ms_t_se").eq(eq).addClass("ms_t_se");
			$(".dropTab").addClass("none").eq(eq).removeClass("none");
			$(".leiji_js").addClass("none").eq(eq).removeClass("none");
		});
		
		//倒计时
		countDown(d);
		
		
	});
</script>
<script src="<%=basePath %>theme/js/mywtinvest/creditor/info.js" type="text/javascript"></script>