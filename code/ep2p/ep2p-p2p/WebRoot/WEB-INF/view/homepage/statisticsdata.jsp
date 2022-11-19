<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!-- 投资统计 5 -->
<div id="statisticsData">
  <div class="frame1000 size16">
    <ul class="staDataUl">
      <li class="pr40">
      	<span class="colorgrey">最高年化利率：</span>
      	<span class="size28 colorDarkBlue g-fft">
      		<input type="hidden" id="v-targetElemet1" value="<c:out value="${maxnumAPR * 100}" default="0"></c:out>">
	      	<font id="myTargetElement1"></font>%
      	</span>
      </li>
      <li class="pr40">
      	<span class="colorgrey">累计投资：￥</span>
      	<span class="size28 colorDarkBlue g-fft">
      		<input type="hidden" id="v-targetElemet2" value='<c:out value="${cumulativei}" default="0.00"></c:out>'>
      		<span id="myTargetElement2"></span>
      	</span>
      </li>
      <li class="pr40">
      	<span class="colorgrey">累计收益：￥</span>
      	<span class="size28 colorDarkBlue g-fft">
      		<input type="hidden" id="v-targetElemet3" value='<c:out value="${userbenefit}" default="0.00"></c:out>'>
      		<span id="myTargetElement3"></span>
      	</span>
      </li>
      <li>
      	<span class="colorgrey">安全运营时间：</span> 
      	<span id="dateDay" class="f_20 size28 colorDarkBlue g-fft">0</span> 
      	<span class="f_14 colorDarkBlue">天</span> 
      	<span id="hours" class="f_20 size28 colorDarkBlue g-fft">0</span>
      	<span class="f_14 colorDarkBlue">时</span> 
      	<span id="minutes" class="f_20 size28 colorDarkBlue g-fft">0</span> 
      	<span class="f_14 colorDarkBlue">分</span> 
      	<span id="seconds" class="f_20 size28 colorDarkBlue g-fft">1</span> 
      	<span class="f_14 colorDarkBlue">秒</span> 
      </li>
    </ul>
    <div class="clear"></div>
  </div>
</div>