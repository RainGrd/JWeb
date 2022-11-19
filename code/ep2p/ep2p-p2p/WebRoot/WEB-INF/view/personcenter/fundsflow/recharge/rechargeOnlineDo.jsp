<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<div class="tc fl w940">
	<div class="w900 lrauto">
		<div class="tl w1">
			返回重新<a href="<%=basePath%>recharge/userRechargeController/toUserRecharge.shtml?recharegeAmount=${recharegeAmount }" class="i_color2">指定充值数据>></a>
		</div>
		<div class="w1 height60">
	   		<div class="w160 re_input">
	   			<img alt="财付通" src="<%=basePath%>theme/default/images/recharge/tenPay.jpg">
	   			<input id="rechargePlatform" name="rechargePlatform" value="1" hidden="true" />
	   		</div>
	   	</div>
	   	<div class="tl w1">
	   		充值金额￥${recharegeAmount }
	   	</div>
	   	<form action="${rechargeUrl}">
		   	<c:forEach items="${formParams}" var="items">
		   		<input type="hidden" name="${items.key}" value="${items.value}" />
		   	</c:forEach>
		   	<div class="mt20 w1 tl">
		    	<input class="re_input bdnone bcolor1 w135 color_ff" type="submit" value="马上充值" ></input>
		    </div>
	    </form>
    </div>
</div>