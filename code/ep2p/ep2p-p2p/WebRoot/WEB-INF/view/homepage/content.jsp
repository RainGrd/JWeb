<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<script type="text/javascript" src="<%=basePath %>theme/js/homepage/transferIndex.js" ></script>
<script type="text/javascript" src="<%=basePath %>theme/js/homepage/borrowIndex.js" ></script> 
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/simpleMath.js"></script>
<!--投资专区8 -->
<div class="content_gray mt50">
 
  <!--e计划 8.1 -->
  <div class="e_plan frame1000 clearfix">
    <div class="fl_blue">
      <div class="in_top"></div>
      <img src="<%=basePath %>theme/default/images/home.item1.icon.png" class="mt10">
      <h4 class="mt10">简单投资  坐享高收益</h4>
      <h4 class="mt10">e计划</h4>
      <p class="mt40 f-16 color_ff">100%本息保障 · 奖励加息送不停</p>
      <a href="#" onclick="ePlanInfoPage()">了解详情></a> 
    </div>
    <p id="eplanData">
    	
    </p>
    <div class="more" onclick="toFinProdPage()"><a href="javascript:void(0)"  id="eplanCount"></a></div>
  </div>
  
  
  <!--投资项目 首房/e抵押 8.2 -->
  <div class="inves_projects frame1000 clearfix">
    <div class="projects_gray">
      <div class="in_top"></div>
      <p><img src="<%=basePath %>theme/default/images/home.item2.icon.png" class="mt30"></p>
      <h4 class="mt10">多重风控  安全保障</h4>
      <h4 class="mt10">投资项目</h4>
      <h5 class="mt10 color_ff f-16">e首房/e抵押</h5>
      <p class="mt70 f-16 color_ff">100%本息保障 · 奖励加息送不停</p>
      <a href="###" onclick="emortgagePage()">了解详情></a> </div>
    <div class="projects_table">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t" id="eMortgageData">
        
      </table>
    </div>
    <div class="more"  onclick="toEmortgagePage()"><a id="eMortgageCount" href="javascript:void(0)"> </a></div>
  </div>
  
  
   <!--债权转让 首房/e抵押 8.3 -->
   <!-- 转让按钮-->
  
  <div class="transfer_zone">
  <div class="inves_projects frame1000 clearfix ">
    <div class="independent">
      <div class="in_top"></div>
      <p><img src="<%=basePath %>theme/default/images/home.item3.icon.png" class="mt30"></p>
      <h4 class="mt10">自主投资  灵活期限</h4>
      <h4 class="mt10">债权转让</h4>
      <h5 class="mt10 color_ff f-16">e首房/e抵押</h5>
      <p class="mt70 f-16 color_ff">100%本息保障 · 奖励加息送不停</p>
      <a href="${transferColumnContent.url}">了解详情></a> </div>
    <div class="projects_table">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
        <thead >
          <th width="10%">年化率</th>
          <th width="40%">债权项目</th>
          <th width="14%">剩余期限</th>
          <th width="10%">项目价值</th>
          <th width="10%">转让价格</th>
          <th width="13%">&nbsp;</th>
        </thead>
        <tbody id="transferData">
        </tbody>
      </table>
    </div>
    <div class="more"><a href="<%=basePath %>mybids/transferController/index/toTransferList.shtml">更 多(<font id="transferCount"></font>)</a></div>
  </div>
  </div>
  <!--  <p class="tz_bt down "><a href="#"></a></p>-->
</div>

<script type="text/javascript">
	$(document).ready(function(){
		selectEplanData();
		selectEmortgageData();
		TransferIndex.initData();
	});
</script>