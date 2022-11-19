<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp"%>
<%@include file="/common/taglibs.jsp"%>

<!-- 全部项目明细 --> 
<div class="sidebar_b fl "> 
      <div class="fl title_div"> 
	      <div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">项目明细</span>
		  </div>
	  </div>
	<div class="xiangmu_mx cb">
		<p class="kong40"></p>
		<p class=" pl20">
			<span class="mingxi_t">项目信息</span>
		</p>
		<p class="kong20"></p>
		<div class="invest_table"> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
	         <thead> 
	          <tr>
	           <th width="20%" style="padding-left: 30px;">编号</th> 
	           <th width="25%">项目名称</th> 
	           <th width="15%">金额</th> 
	           <th width="15%">年化率</th> 
	           <th width="10%">期限</th> 
	           <th width="15%">还款方式</th> 
	          </tr>
	         </thead> 
	         <tbody>
	          <tr> 
	           <td class="pl30" style="padding-left: 60px;">${borrow.borrowCode }
	           </td><td class=""><c:out value="${borrow.borrowName }" /></td> 
	           <td>￥<fmt:formatNumber value="${borrow.borrowMoney }" pattern="#,##0.00"/> </td> 
	           <td><fmt:formatNumber value="${borrow.borrowRate*100 }" pattern="#0.##" /> %</td> 
	           <td><c:out value="${borrow.borDeadline }" />月</td> 
	           <td><c:out value="${borrow.repaymentTypeName }" /></td> 
	          </tr> 
	         </tbody>
	        </table> 
	    </div>
		<p class="kong40 cb"></p>
		<p class=" pl20">
			<span class="mingxi_t fl">投资信息</span><span class="mingxi_t1 fl"></span><a href="#" class="cus_p c2980b9 ml5">借款协议</a>
		</p>
		<p class="kong20 cb"></p>
		<div class="invest_table"> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
	         <thead> 
	          <tr>
	           <th width="24%" style="padding-left: 30px;">投资时间</th> 
	           <th width="19%">投资金额</th> 
	           <th width="19%">利息</th> 
	           <th width="19%">投资奖励</th> 
	           <th width="19%">加息收益</th> 
	          </tr>
	         </thead> 
	         <tbody>
	          <tr> 
	           <td class="pl30" style="padding-left: 60px;"><c:out value="${investModel.investmentTime }" />
	           </td><td class="">￥<fmt:formatNumber value="${investModel.investmentAmount }"  pattern="#,##0.00"/> </td> 
	           <td>￥<fmt:formatNumber value="${investModel.interest }"  pattern="#,##0.00"/> </td> 
	           <td>￥<fmt:formatNumber value="${investModel.investmentReward }"  pattern="#,##0.00"/> </td> 
	           <td>￥<fmt:formatNumber value="${investModel.hike }"  pattern="#,##0.00"/> </td> 
	          </tr> 
	         </tbody>
	        </table> 
	    </div>
		<p class="kong40 cb"></p>
		<p class=" pl20">
			<span class="mingxi_t">收益明细</span>
		</p>
		<p class="kong20"></p>
		<div class="invest_table"> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
	         <thead> 
	          <tr>
	           <th width="25%" style="padding-left: 30px;">时间</th> 
	           <th width="10%">期次</th> 
	           <th width="18%">已收金额</th> 
	           <th width="18%">已收本金</th> 
	           <th width="18%">已收利息</th> 
	           <th width="18%">加息收益</th> 
	           <th width="18%">收取罚息</th> 
	           <th width="10%">状态</th> 
	           <th width="10%">备注</th> 
	          </tr>
	         </thead> 
	         <tbody>
	         <c:forEach var="r" items="${brpList }">
		          <tr> 
		           <td class="pl30" style="padding-left: 60px;"><c:out value="${r.RDate }" /></td>
		           <td class="">${r.planIndex }/${brpList.size() }</td> 
		           <td>￥<fmt:formatNumber value="${r.repaidCapital }"  pattern="#,##0.00"/></td> 
		           <td>￥<fmt:formatNumber value="${r.capital }"  pattern="#,##0.00"/></td> 
		           <td>￥<fmt:formatNumber value="${r.netInterest }"  pattern="#,##0.00"/></td> 
		           <td>￥<fmt:formatNumber value="${r.netHike }"  pattern="#,##0.00"/></td> 
		           <td>￥<fmt:formatNumber value="${r.lateFee }"  pattern="#,##0.00"/></td> 
		           <td><c:out value="${r.receiptStatusName }" /></td> 
		           <td class="colorc"> <c:out value="${r.recPlaDesc }" /> </td> 
		          </tr> 
	          </c:forEach>
	         </tbody>
	        </table> 
	    </div>
	    
	    <c:if test="${!empty(brt) }">
		    <p class="kong40 cb"></p>
			<p class=" pl20">
				<span class="mingxi_t fl">转让信息</span><span class="mingxi_t1 fl"></span><a href="#" class="cus_p c2980b9 ml5">转让协议</a>
			</p>
			<p class="kong20"></p>
			<div class="invest_table"> 
		        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
		         <thead> 
		          <tr>
		           <th width="25%" style="padding-left: 30px;">转让时间</th> 
		           <th width="19%">债权编号</th> 
		           <th width="19%">转让本金</th> 
		           <th width="19%">转让价格</th> 
		           <th width="18%">转让手续费</th> 
		          </tr>
		         </thead> 
		         <tbody>
		          <tr> 
		           <td class="pl30" style="padding-left: 60px;"><fmt:formatDate value="${brt.successTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		           </td><td class="c2980b9"><c:out value="${brt.transferCode }"/></td> 
		           <td>￥<fmt:formatNumber value="${brt.residualPrincipal }"  pattern="#,##0.00"/> </td> 
		           <td>￥<fmt:formatNumber value="${brt.successAmount }"  pattern="#,##0.00"/> </td> 
		           <td>￥<fmt:formatNumber value="${brt.fee }"  pattern="#,##0.00"/> </td> 
		          </tr> 
		         </tbody>
		        </table> 
		      </div>
	      </c:if>
	</div>
    <div class="clearfix"></div> 
</div>