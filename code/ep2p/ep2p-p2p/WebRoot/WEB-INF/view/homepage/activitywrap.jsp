<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="<%=basePath %>theme/js/homepage/newStandardIndex.js"></script>
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/experienceborrow/info.js"></script>
<!--活动专区 7 -->
<div class="activityWrap frame1000 pt40">
  <div class="activityDiv">
    <div class="more"><a href="<%=basePath%>activityArea/acticityAreaController/index/toActivityArea.shtml">更 多(4)</a></div>
    <h3>活动专区<span>更多微信专享活动正在进行</span></h3>
    <ul class="act_infUl">
      <li id="newStandard">
        <div class="activity_inf "> 
        	<i class="xs-icon x-icon"></i>
          <h4><a href="javascript:newStandard.toNewStandard('${newStandardBorrow.pid }');" class="act_title">${newStandardBorrow.borrowCode } ${newStandardBorrow.borrowName }</a></h4>
          <div class="act_inf_money ">
            <label class="left"><span class="size36 colororg "><fmt:formatNumber value="${newStandardBorrow.borrowRate*100 }" pattern="#0.##"/> </span><span class="size26 colororg ">%</span>年化</label>
            <label class="right"><span class="size36 colorblu ">${newStandardBorrow.borDeadline }</span>天</label>
            <div class="clear"></div>
          </div>
          <div class="tc">
            <dl class="barbox barline">
              <dd class="barline">
                <div divindex="3" id="chartSlide_3" w="${newStandardBorrow.borrowProgress * 100}" style="width:0px;" class="charts"></div>
              </dd>
            </dl>
            <div class="number barline"> <span class="fr">￥<fmt:formatNumber value="${newStandardBorrow.surplusMoney }"  pattern="#,##0.00"/> <font class="digital">剩余</font></span><fmt:formatNumber value="${newStandardBorrow.borrowProgress * 100 }" pattern="#0.##"/>%<font class="digital">已完成</font> </div>
          </div>
          <div class="act_detail pd-b5 mt5">
            <p>仅限新注册用户第一次投资</p>
            <p>单笔投资不超过￥10000元</p>
          </div>
          <div class="invest_a pt8"> <a href="javascript:newStandard.toNewStandard('${newStandardBorrow.pid }')" class="invest right">立即投资</a>
            <div class="clear"></div>
          </div>
        </div>
      </li>
      <li class="activity_ty">
        <div class="activity_inf "> <i class="ty-icon x-icon"></i>
          <h4><a href="javascript:experienceBorrow.toExperienceBorrow('${experienceBorrow.pid}')" class="act_title">${experienceBorrow.borrowCode }${experienceBorrow.borrowName }</a></h4>
          <div class="act_inf_money ">
            <label class="left"><span class="size36 colororg ">${experienceBorrow.borrowRate }</span><span class="size26 colororg ">%</span>年化</label>
            <label class="right"><span class="size36 colorblu ">${experienceBorrow.borDeadline }</span>天</label>
            <div class="clear"></div>
          </div>
          <div class="tc">
            <dl class="barbox barline">
              <dd class="barline">
                <div divindex="3" id="chartSlide_" w="${experienceBorrow.borrowProgress }" style="width:${experienceBorrow.borrowProgress };" class="charts"></div>
              </dd>
            </dl>
            <div class="number barline"> <span class="fr">￥${experienceBorrow.surplusMoney }<font class="digital">剩余</font></span>${experienceBorrow.borrowProgress }%<font class="digital">已完成</font> </div>
          </div>
          <div class="act_detail pd-b5 mt5">
            <p>仅限新注册用户第一次投资</p>
            <p>仅使用体验金投资</p>
          </div>
          <div class="invest_a pt8">
          	<c:if test="${experienceBorrow.borStatus == 1 }">
          		<a href="javascript:experienceBorrow.toExperienceBorrow('${experienceBorrow.pid}')" class="invest right bor_bt_bg">待招标</a>
          	</c:if>
          	<c:if test="${experienceBorrow.borStatus == 2 }">	
            	<a href="javascript:experienceBorrow.toExperienceBorrow('${experienceBorrow.pid}')" class="invest right">立即投资</a>
            </c:if>
            <c:if test="${experienceBorrow.borStatus == 3 || experienceBorrow.borStatus == 4 || experienceBorrow.borStatus == 5}">
            	<a href="javascript:experienceBorrow.toExperienceBorrow('${experienceBorrow.pid}')" class="invest right bor_bt_bg">已结束</a>
            </c:if>
            <c:if test="${experienceBorrow.borStatus == 8 }">
            	<a href="javascript:experienceBorrow.toExperienceBorrow('${experienceBorrow.pid}')" class="invest right bor_bt_bg">已结清</a>
            </c:if>
            <div class="clear"></div>
          </div>
        </div>
      </li>
      <li>
        <div class="activity_inf">
          <div class="hong"></div>
          <h4 class="align_center size14 fontNormal">巨包！速来</h4>
          <div class="act_inf_money align_center"> <span class="size36 colororg ">￥100000</span> </div>
          <div class="tc">
            <dl class="barbox barline">
              <dd class="barline">
                <div divindex="3" id="chartSlide_3" w="20" style="width:0px;" class="charts"></div>
              </dd>
            </dl>
            <div class="number barline"> <span class="fr">389/500</span>23天03:16:35 剩余 </div>
          </div>
          <div class="act_detail pd-b5 mt5">
            <p>当月投资10000及以上可抢；</p>
            <p>VIP3及以上会员;</p>
          </div>
        <div class="invest_a pt8">  
        	<!-- 未开始 -->
        	<p class="notStarted">距开始：<span>124:50:20</span></p>          
          <!-- <a href="javascript:void(0)" class="redGift">拆红包</a>
          <a href="javascript:void(0)" class="redGiftEd">已领取</a>
          <a href="javascript:void(0)" class="redGiftEd">已结束</a> -->
          <div class="clear"></div>
        </div>
        </div>
      </li>
      <li>
        <div class="activity_inf">
          <div class="songli"></div>
          <h4 class="align_center size14 fontNormal">中秋百万加息券狂送</h4>
          <div class="act_inf_money align_center"> <span class="size36 colororg ">+1.8</span><span class="size26 colororg ">%</span> </div>
          <div class="tc">
            <dl class="barbox barline">
              <dd class="barline">
                <div divindex="3" id="chartSlide_3" w="75" style="width:0px;" class="charts"></div>
              </dd>
            </dl>
            <div class="number barline"> <span class="fr">389/500</span>23天03:16:35 剩余 </div>
          </div>
          <div class="act_detail pd-b5 mt5">
            <p>当月投资50000及以上可领取；</p>
            <p>VIP2及以上会员;</p>
          </div>
          <div class="invest_a pt11 receiveBox">             
            <a href="javascript:void(0)" class="receive"><p>立即领取</p></a>
            <a href="javascript:void(0)" class="receiveEd">已领取</a>
            <!--  <a href="javascript:void(0)" class="receiveEd">已结束</a> -->
          </div>
        </div>
      </li>
    </ul>
    <div class="clear"></div>
  </div>
</div>
<div class="popUpWrap">
    <?xml version="1.0" standalone="no"?>
    <!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
    <div style="width:600px; height:200px; background:rgba(0,0,0,0); border-radius:10px 10px 0 0; position:absolute; top:320px; left:50%; margin-left:-300px; z-index:2;">
    <svg width="100%" height="100%" viewBox="0 0 600 300" xmlns="http://www.w3.org/2000/svg">
		<g transform="scale(1)">
			<g transform="translate(0,0)" class="liehen">
				<g style="fill: rgba(0,0,0,0); stroke:#ffffff; stroke-width:15;">
					<path d="M150 150C150 150,200 50,250 150C250 150,300 50,350 150C350 150,400 50,450 150,500 50"/>
				</g>
			</g>
		</g>
	</svg>
    </div>
    <!-- 抢到红包 -->
    <div class="popUpBoxBg"></div>
    <div class="popUpBox animated">
    	<div class="redBoxTopX"></div>
        <div class="redBoxBottom"></div>
        <div class="redBoxPerson"></div>
        <div class="redBoxMoney"><p>你为何如此叼！<br />抢到￥100元红包！<span>红包金额已转入账户余额</span></p></div>
        <div class="redBoxPerson1"></div>
    	<a href="javascript:;" class="popClose">×</a>
    </div>
    
    <!-- 没抢到红包 -->
    <div class="popUpBoxCry animated">
    	<p>很遗憾~ <br />红包已被抢光了！<span>下次再接再厉</span></p>
        <!--<p>很遗憾~ <br />您目前还不符合抢此类红包的条件......</p>-->
    	<a href="javascript:;" class="popClose">×</a>
    </div>  
</div>
<div class="popUpWrapAdd">
<!-- 抢到加息券 -->
    <div class="popUpAdd">
    	<p>恭喜，您抢到一张加息券<br />加息券已经放入您的卡包里！</p>       
    	<a href="javascript:;" class="popClose">×</a>
    </div>
    
    <!-- 没抢到加息券 -->
    <div class="popUpAddCry">
    	<p>很遗憾~ 手慢了<br />加息券已经领取完了<span>下次再接再厉！</span></p>
        <!--<p>很遗憾~ <br />您目前还不符合抢此类加息券的条件......</p>-->
    	<a href="javascript:;" class="popClose">×</a>
    </div>  
</div>
