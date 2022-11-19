<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
 <div class="sidebar_b borrow_div fl">
	<!--  消息中心 s-->
	<div class="ms m_auto">
		<div class="ms_t">
			<span class=" ms_ta size16 tc inline_block ms_t_se">消息中心</span>
		</div>
		<!--第一个ms_c是系统消息-->
		<div class="ms_c">
			<div class="ms_c_t">
				<div class="kong15"></div>
				<div class="ms_c_t_list">
				   <form id="mgsList_">
						<span class="btn_samll inline_block tc size16 fl_" onclick="msgCenter.markRead();">标记为已读</span>
						<a class="btn_samll fr_ " href="javascript:msgCenter.loadData('1','mgsList_');">查询</a>
						<input type="text" class="fr_ ms_but_b mr18 " value="" name="msgConten" id="msgContent">
						<span class="inline_block fr_ ms_but_c tc size16">消息内容</span>	
						<input type="text" class="fr_ pl10 w100" name="endTime" onFocus="WdatePicker()" id="endTime" value="">
						<span class="fr_ pl plr5"> - </span>
						<input type="text" class="fr_ pl10 w100" name="beginTimes" onFocus="WdatePicker()" id="beginTimes" value="">
						<span class="fr_ tc size16 "> 时间:</span>
					</form>
				</div>
			</div>
			<div class="ms_c_tab">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
			           <thead> 
			            <tr class="ms_c_ta size16 tc">
			             <th width="7%" class="pl30">
			             	<div class="kong5"></div>
			             	<span class="gou_s gou_s_js"></span>
			             </th> 
			             <th width="13%">序号</th> 
			             <th width="20%">日期</th> 
			             <th width="42%">消息内容</th> 
			             <th width="18%" class="pl30">状态</th> 
			            </tr>
			           </thead> 
			           <tbody class="ms_c_tabf size14 mesList">
			           </tbody>
			          </table>
			        <div id="messageList" class="page_div fr">  </div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/messagecenter/userRightMessage.js"></script>
