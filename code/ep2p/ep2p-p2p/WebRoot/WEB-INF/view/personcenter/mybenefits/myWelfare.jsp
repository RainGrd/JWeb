<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<div class="sidebar_b borrow_div fl">
	<%--  我的福利 --%>
	<div class="fui m_auto">
		<div class="fui_t">
			<input type="hidden" id="welfareType" value="${welfareType}" />
			<span class=" fui_ta size16 tc inline_block ms_t_se" onclick="" >我的赠劵</span>
			<span class="ml20 fui_ta size16 tc inline_block" onclick="" >我的积分</span>
		</div>
		<%--第一个fui_c是我的赠卷--%>
		<div class="fui_c">
			<div class="kong40"></div>
			<div class="kong40 po_relative size12 ffffff fui_juan_nav " >
				<span class="pos-a fui_juan_nav_a fui_juan_nav_se  bg297FB9" onclick="myWelfare.canUse(1);">可使用</span>
				<span class="pos-a fui_juan_nav_b fui_juan_nav_se " onclick="myWelfare.canUse(2);">已失效</span>
			</div>
			<div class="fui_zj m_auto">
			<div id="div_zj">
			<!--fui_zj_jia是加息卷 fui_zj_jia_是已经使用过或已过期的加息卷
				fui_zj_ti是体验金 fui_zj_ti_是已经使用过或已过期的体验金
				fui_zj_list_a是未使用的a标签样式 fui_zj_list_a_是已使用或过期后的a标签样式
				使用条件静态显示
				-->
				<!-- <div class="fuiBox">
					<span class="fui_zj_list inline_block fl_ fui_zj_jia mt20 pos-r">
						<p class=" size30 fffe80 fui_zj_listp2 tc mt40">+1.6%</p>
						<p class=" size12 ffffff fui_zj_listp3 tc">仅限使用于允许使用加息券的投资项目</p>
						<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">还有11天过期</p>
						<a href="#" class="inline_block fui_zj_list_a pos-a">未使用</a>
					</span>
					<span class="fui_zj_list inline_block fl_ fui_zj_ti mt20 pos-r">
						<p class=" size30 fffe80 fui_zj_listp2 tc mt40">￥10000</p>
						<p class=" size12 ffffff fui_zj_listp3 tc">仅限使用于体验标</p>
						<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">还有11天过期</p>
						<a href="#" class="inline_block fui_zj_list_a pos-a">未使用</a>
					</span>
				</div>
				<div class="fuiBox none">
					<span class="fui_zj_list inline_block fl_ fui_zj_jia_ mt20 pos-r">
						<p class=" size30 ffffff fui_zj_listp2 tc mt40">+1.6%</p>
						<p class=" size12 ffffff fui_zj_listp3 tc">仅限使用于允许使用加息券的投资项目</p>
						<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">还有0天过期</p>
						<a href="JavaScript:;" class="inline_block fui_zj_list_a_ pos-a">已过期</a>
					</span>
					<span class="fui_zj_list inline_block fl_ fui_zj_ti_ mt20 pos-r">
						<p class=" size30 ffffff fui_zj_listp2 tc mt40">￥10000</p>
						<p class=" size12 ffffff fui_zj_listp3 tc">仅限使用于体验标</p>
						<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">还有0天过期</p>
						<a href="JavaScript:;" class="inline_block fui_zj_list_a_ pos-a">已过期</a>
					</span>
					<span class="fui_zj_list inline_block fl_ fui_zj_jia_ mt20 pos-r">
						<p class=" size30 ffffff fui_zj_listp2 tc mt40">+1.6%</p>
						<p class=" size12 ffffff fui_zj_listp3 tc">仅限使用于允许使用加息券的投资项目</p>
						<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">还有0天过期</p>
						<a href="JavaScript:;" class="inline_block fui_zj_list_a_ pos-a">已使用</a>
					</span>
					<span class="fui_zj_list inline_block fl_ fui_zj_ti_ mt20 pos-r">
						<p class=" size30 ffffff fui_zj_listp2 tc mt40">￥10000</p>
						<p class=" size12 ffffff fui_zj_listp3 tc">仅限使用于体验标</p>
						<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">还有0天过期</p>
						<a href="JavaScript:;" class="inline_block fui_zj_list_a_ pos-a">已使用</a>
					</span>
				</div>-->
			</div>
				
				<!--span标签 的个数能被3整除 就不用添加"ml20" 否则添加"ml20" -->
				 
<!-- 				<span class="fui_zj_list inline_block fl_ fui_zj_ti ml20 mt20 pos-r"> -->
<!-- 					<p class=" size14 ffffff fui_zj_listp1 pl10">　</p> -->
<!-- 					<p class=" size30 fffe80 fui_zj_listp2 tc amount">$10000</p> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tc"></p> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tc"></p> -->
<!-- 					<div class="inline_block kong15"></div> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tl pl15 dateTime">还有11天过期</p> -->
<!-- 				</span> -->
<!-- 				<span class="fui_zj_list inline_block fl_ fui_zj_jia_ mt20 ml20 pos-r"> -->
<!-- 					<p class=" size14 ffffff fui_zj_listp1 pl10">No.03344543</p> -->
<!-- 					<p class=" size30 fffe80 fui_zj_listp2 tc">+1.6%</p> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tc">单笔投资满5000元可用</p> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tc">限定使用比例1.00%</p> -->
<!-- 					<div class="inline_block kong15"></div> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tl pl15">还有11天过期</p> -->
<!-- 				</span> -->
<!-- 				<span class="fui_zj_list inline_block fl_ fui_zj_ti_  mt20 pos-r"> -->
<!-- 					<p class=" size14 ffffff fui_zj_listp1 pl10">No.03344543</p> -->
<!-- 					<p class=" size30 fffe80 fui_zj_listp2 tc">$10000</p> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tc">单笔投资满5000元可用</p> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tc">限定使用比例1.00%</p> -->
<!-- 					<div class="inline_block kong15"></div> -->
<!-- 					<p class=" size12 ffffff fui_zj_listp3 tl pl15">还有11天过期</p> -->
<!-- 				</span> -->
			</div>	
			 <div id="my_zj" class="page_div fr">  </div>				
		</div>
		
		<%--第二个fui_c是我的积分--%>
		<div class="fui_c none">
			<div class="fui_ji">
				<div class="kong60"></div>
				<div class="kong60 ">
					<div id="div_point">
					</div>
					<span class="fui_ji_b fl_ inline_block">
						<a href="#" class="btn_samll fui_ji_cha_ fui_j_x">兑换</a>
						<br />
						<%--下划线fui_ji_cha--%>
						<a class="colorDarkBlue size14  fui_j_x btn_samll mt10" href="javascript:void(0)">查询积分明细</a>
						
						
					</span>
				</div>
			</div>
			
			<%--第一个fui_juan是积分兑换--%>
			<div class="fui_juan fui_top_l">
				<div class="kong40 po_relative size12 ffffff fui_juan_nav " >
					<span class="pos-a fui_juan_nav_a fui_juan_nav_se  bg297FB9">兑换话费</span>
					<span class="pos-a fui_juan_nav_b fui_juan_nav_se ">兑换加息卷</span>
					<span class="pos-a fui_juan_nav_c fui_juan_nav_se ">兑换VIP</span>
					<span class="pos-a fui_juan_nav_d fui_juan_nav_se ">兑换现金</span>
					<input type="hidden" id="exchangeType" value="" />
				</div>
				
				<%--第一个fui_juan_lists_div是兑换话费--%>
				<div class="fui_juan_lists_div">
					<div class="fui_juan_list m_auto" id="exchangeTelephoneFare">
						<!-- <span class="inline_block fui_juan_lists cus_p tc fl_" v_="1" style="background: url(/ep2p/theme/default/images/fu_2.png) no-repeat;">
							<p class="fui_juan_list_p1 size14 ffffff">20000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">￥20<i class="size14 i_">话费</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">支持移动、联通、电信</p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20"  v_="2">
							<p class="fui_juan_list_p1 size14 ffffff">50000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">￥50<i class="size14 i_">话费</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">支持移动、联通、电信</p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20"  v_="3">
							<p class="fui_juan_list_p1 size14 ffffff">90000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">￥100<i class="size14 i_">话费</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">支持移动、联通、电信</p>
						</span> -->
				    </div>
				    <div class="kong65"></div>
				    <div class="fui_juan_bd">
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_chongzhi_xm">
				    		<span class="inline_block  w300 tl">
				    			兑换项目：<span class="colorc fui_juan_xuan_js"> </span>
				    		</span>
				    		
				    	</p>
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_chongzhi">
				    		<span class="inline_block  w300 tl" style="line-height: 30px;margin-top:7px;margin-bottom:7px">
				    			充值号码：<input type="text" id="phoneNo1" class="w100 inline_block size14 000000_ fui_juan_chong">
				    			<br/>
					    		<span id="promptError_1_1" class="input_tis ts_re w100 mb5 block none_" style="margin-left: 72px;">
								<%-- ts_re 是红色警告背景   ts_lh 是黄色警告背景 ts_lv 是绿色正确提示背景 --%>
									手机号码不能为空
								</span>
				    		</span>
				    	</p> 
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_chongzhi">
				    		<span class="inline_block  w160 mt15 s_btn">
				    			<a class="btn_samll fl_ duihuan_chongzhi_qr" href="javascript:void(0)" onclick="myWelfare.checkPhoneNo()">兑换</a>
				    		</span>
				    	</p>
				    	
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_chongzhi_q none">
				    		<span class="inline_block  w300 tl">
				    			充值号码：<span id="phoneNo2" ></span>
				    		</span>
				    	</p> 
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_chongzhi_q none_">
				    		<span class="inline_block  w300 tl" style="line-height: 30px;margin-top:7px;margin-bottom:7px">
				    			交易密码：<input type="password" id="tradePassword_1" class="w205 inline_block size14 000000_ fui_juan_chong">
				    			<br/>
				    		<span id="promptError_1" class="input_tis ts_lh w360 mb5 fl none_" style="margin-left: 72px;"></span>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_chongzhi_q none_">
				    		<span class="kong15 inline_block"></span>
				    		<span class="inline_block  w160 duihuan_chongzhi_q_qr">
				    			<a class="btn_samll fl_" href="javascript:void(0)" onclick="myWelfare.exchangeTelephoneFare()">确定兑换</a>
				    		</span>
				    	</p> 
				    	<p class=" size18 colorDarkBlue tc  none_ chongzhi_y">
				    		<span class="w300"><img class="mr5" src="/ep2p/theme/default/images/dr_6.png" />恭喜您,兑换成功!</span>
				    		<br/>
				    		<span class="size12 w300 c798383">实际到账时间取决通信运营商</span>
				    	</p> 
				    </div>
				</div>
				
				<%--第2个fui_juan_lists_div是兑换加息卷--%>
				<div class="fui_juan_lists_div none">
					<div class="fui_juan_list m_auto" id="exchangeInterestTicket">
						<!-- <span class="inline_block fui_juan_lists cus_p tc fl_" v_="1" style="background: url(/ep2p/theme/default/images/fu_2.png) no-repeat;">
							<p class="fui_juan_list_p1 size14 ffffff">90000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80"><i class="size14 i_">+</i>0.2%<i class="size14 i_">加息卷</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">有效期6个月</p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="2">
							<p class="fui_juan_list_p1 size14 ffffff">190000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80"><i class="size14 i_">+</i>0.5%<i class="size14 i_">加息卷</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">有效期6个月</p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20"  v_="3">
							<p class="fui_juan_list_p1 size14 ffffff">380000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80"><i class="size14 i_">+</i>1%<i class="size14 i_">加息卷</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">有效期6个月</p>
						</span> -->
				    </div>
				    <div class="kong65"></div>
				     <div class="fui_juan_bd">
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_jiaxi_y">
				    		<span class="inline_block  w300 tl ">
				    			兑换项目：<span class="colorc fui_juan_xuan_js"> </span>
				    		</span>
				    		
				    	</p>
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_jiaxi_m none">
				    		<span class="inline_block  w300 tl" style="line-height: 30px;margin-top:7px;margin-bottom:7px">
				    			交易密码：<input type="password" id="tradePassword_2" class="w205 inline_block size14 000000_ fui_juan_chong">
				    			<br/>
				    			<span id="promptError_2" class="input_tis ts_lh w360 mb5 fl none_"  style="margin-left: 72px;"></span>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_jiaxi_">
				    		<span class="inline_block  w160 mt15 s_btn">
				    			<a class="btn_samll fl_ duihuan_jiaxi" href="javascript:void(0)">兑换</a>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_jiaxi_qr none">
				    		<span class="kong15 inline_block"></span>
				    		<span class="inline_block  w160  duihuan_jiaxi_qr_">
				    			<a class="btn_samll fl_" href="javascript:void(0)" onclick="myWelfare.exchangeInterestTicket()">确定兑换</a>
				    		</span>
				    	</p>
				    	<p class=" size18 colorDarkBlue tc  none duihuan_jiaxi_y_">
				    		<span class="w300"><img class="mr5" src="/ep2p/theme/default/images/dr_6.png" />恭喜您,兑换成功!</span>
				    		<br/>
				    		<span class="size12 w300 c798383">加息券已发放至您的账户，请尽快使用</span>
				    	</p> 
				    </div>
				    
				</div>
				
				<%--第三个fui_juan_lists_div是兑换vip--%>
				<div class="fui_juan_lists_div none">
					<div class="fui_juan_list m_auto" id="exchangeVIP">
						<!-- <span class="inline_block fui_juan_lists cus_p tc fl_" v_="1" style="background: url(/ep2p/theme/default/images/fu_2.png) no-repeat;">
							<p class="fui_juan_list_p1 size14 ffffff">120000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">12<i class="size14 i_">月VIP</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">可获时长</p>
						</span> 
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="2">
							<p class="fui_juan_list_p1 size14 ffffff">200000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">24<i class="size14 i_">月VIP</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">可获时长</p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="3">
							<p class="fui_juan_list_p1 size14 ffffff">320000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">36<i class="size14 i_">月VIP</i></p>
							<p class="fui_juan_list_p3 size12 ffffff">可获时长</p> -->
						</span>
				    </div>
				    <div class="kong65"></div>
				     <div class="fui_juan_bd">
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_vip_y">
				    		<span class="inline_block  w300 tl">
				    			兑换项目：<span class="colorc fui_juan_xuan_js"> </span>
				    		</span>
				    		
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_vip">
				    		<span class="inline_block  w160 mt15 s_btn">
				    			<a class="btn_samll fl_ duihuan_vip_qr" href="javascript:void(0)">兑换</a>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_vip_ none">
				    		<span class="inline_block  w300 tl" style="line-height: 30px;margin-top:7px;margin-bottom:7px">
				    			交易密码：<input type="password" id="tradePassword_3" class="w205 inline_block size14 000000_ fui_juan_chong">
				    			<br/>
				    		<span id="promptError_3" class="input_tis ts_lh w360 mb5 fl none_" style="margin-left: 72px;"></span>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_vip_ duihuan_vip_a none">
				    		<span class="kong15 inline_block"></span>
				    		<span class="inline_block  w160  duihuan_jiaxi_qr_">
				    			<a class="btn_samll fl_" href="javascript:void(0)" onclick="myWelfare.exchangeVIP()">确定兑换</a>
				    		</span>
				    	</p>
				    	<p class=" size18 colorDarkBlue tc  none duihuan_vip_y_">
				    		<span class="w300"><img class="mr5" src="/ep2p/theme/default/images/dr_6.png" />恭喜您,兑换成功!</span>
				    		<br/>
				    		<span class="size12 w300 c798383">您的VIP到期时间为:<i class="i_ colorc"></i></span>
				    	</p> 
				    </div>
				</div>
				
				<%--第四个fui_juan_lists_div是兑换现金--%>
				<div class="fui_juan_lists_div none">
					<div class="fui_juan_list m_auto" id="exchangeCash">
						<!-- <span class="inline_block fui_juan_lists cus_p tc fl_" v_="1" style="background: url(/ep2p/theme/default/images/fu_2.png) no-repeat;">
							<p class="fui_juan_list_p1 size14 ffffff">20000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">20<i class="size14 i_">元</i></p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="2">
							<p class="fui_juan_list_p1 size14 ffffff">50000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">50<i class="size14 i_">元</i></p>
						</span>
						<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="3">
							<p class="fui_juan_list_p1 size14 ffffff">100000积分</p>
							<p class="fui_juan_list_p2 size30 fffe80">100<i class="size14 i_">元</i></p>
						</span> -->
				    </div>
				    <div class="kong65"></div>
				     <div class="fui_juan_bd">
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_xianjin_y">
				    		<span class="inline_block  w300 tl">
				    			兑换项目：<span class="colorc fui_juan_xuan_js"> </span>
				    		</span>
				    		
				    	</p>
				    	<p class="fui_juan_list_p4 size14 colorDarkBlue tc duihuan_xianjin_q none">
				    		<span class="inline_block  w300 tl" style="line-height: 30px;margin-top:7px;margin-bottom:7px">
				    			交易密码：<input type="password" id="tradePassword_4" class="w205 inline_block size14 000000_ fui_juan_chong">
				    			<br/>
				    			<span id="promptError_4" class="input_tis ts_lh w360 mb5 fl none_" style="margin-left: 72px;"></span>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_xianjin_">
				    		<span class="inline_block  w160 mt15 s_btn">
				    			<a class="btn_samll fl_ duihuan_xianjin" href="javascript:void(0)">兑换</a>
				    		</span>
				    	</p>
				    	<p class="fui_juan_list_p5 size14 colorDarkBlue tc duihuan_xianjin_q none">
				    		<span class="kong15 inline_block"></span>
				    		<span class="inline_block  w160  duihuan_xianjin_qr">
				    			<a class="btn_samll fl_" href="javascript:void(0)" onclick="myWelfare.exchangeCash()">确定兑换</a>
				    		</span>
				    	</p>
				    	<p class=" size18 colorDarkBlue tc  none duihuan_xianjin_y_">
				    		<span class="w300"><img class="mr5" src="/ep2p/theme/default/images/dr_6.png" />恭喜您,兑换成功!</span>
				    		<br/>
				    		<span class="size12 w300 c798383">可用余额已发送至您的账户,请查收</span>
				    	</p> 
				    </div>
				    
				</div>
			</div>
			
		    <%--第二个fui_juan是积分查询--%>
		    <div class="fui_juan none">
				<div class="find_area fl"> 
					<form id="pointDetailForm">
			        	<img src="<%=basePath %>theme/default/images/dateicon.png" width="30" height="29" alt="日期选择"> 
						<input type="text" class="pl10" readonly="readonly" onFocus="WdatePicker()" name="happenBeginTime" />
						<i class="i_ m10 mr10">-</i>
						<input type="text" class="pl10"  readonly="readonly" onFocus="WdatePicker()" name="happenEndTime" />
						<input type="hidden" name="pointType" id="pointType"  />
			        	<a class="btn_samll" onclick="myWelfare.pointDetail('0');" >查询</a>
			    	</form>
		        </div>
		        <div class="fui_c_tab">
		        	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
				    	<thead> 
				        	<tr>
				           		<th width="10%">积分</th> 
				           		<th width="10%">可用积分</th> 
				           		<th width="20%">发生时间</th> 
				           		<th width="10%">类型</th> 
				           		<th width="34%">备注</th> 
				          	</tr>
				   		</thead> 
				        <tbody id="pointDetail_div">
				        </tbody>
				  	</table>
				    <%-- 分页 --%>
					<div id="pointDetailPage" class="page_div fr"> </div>
			        </div>
				</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${basePath}theme/js/personcenter/mybenefits/my_welfare.js"></script>