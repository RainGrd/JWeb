<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<div class="sidebar_b borrow_div fl">
				<input type="text" id="vip_level" hidden="true" name="vipLevel" value="${vipLevel}"/>
				
					<!--  购买vip s-->
					<div class="vip_">
						<div class="kong55">
						</div>
						<div class="vip_t">
							<div class="vip_t_l530 vip_a">
								<ul>
									<li>
										<p class="p1 level_">${vipLevel}</p>
										<p class="p2">当前等级</p>
									</li>
									<li>
										<p class="p1 jyz"></p>
										<p class="p2">当前经验值</p>
									</li>
									<li>
										<p class="p1">5点/一天</p>
										<p class="p2">当前成长速度</p>
									</li>
								</ul>
							</div>
							<div class="vip_t_r380 vip_b">
								<ul>
									<li>
										<p class="p1 info_servcie">10%</p>
										<p class="p2">当前信息管理服务费</p>
									</li>
									<li>
										<p class="p2 dqsj"></p>
										<p class="p3"><a class="btn_samll g_vip" href="javascript:vipInfo.shopping();">购买</a></p>
									</li>
								</ul>
							</div>
						</div>
						<div class="kong45"></div>
						<div class="vip_j">
							<div class="chuan">
								<img src="/ep2p/theme/default/images/vip_1.png" class="chuan_a" />
								<img src="/ep2p/theme/default/images/vip_2.png" class="chuan_b" />
								<img src="/ep2p/theme/default/images/vip_3.png" class="chuan_c" />
							</div>
							<div class="vip_jd">
								<span class="jd_h"></span>
								<!--下面i元素 是vip2 vip3 vip4 的刻度,经验值到达那个 就显示那个 默认隐藏-->
								<i class="vip_sa" style="display: inline-block;"></i>
								<i class="vip_sb"></i>
								<i class="vip_sc"></i>
							</div>

							<div class="vip_w">
								<span class="vip_w_a">VIP1</span>
								<span class="vip_w_b">VIP2</span>
								<span class="vip_w_c">VIP3</span>
								<span class="vip_w_d">VIP4</span>
								<span class="vip_w_e">VIP5</span>
							</div>

						</div>

						<div class="kong95"></div>
						<div class="vip_hui"></div>
						<div class="kong35"></div>

						<div class="vip_shuo m_auto ss_">
							
							<div class="kong70"></div>
						</div>
						
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/vipInfo/vipLevelInfo.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	vipInfo.loadData();
});
</script>