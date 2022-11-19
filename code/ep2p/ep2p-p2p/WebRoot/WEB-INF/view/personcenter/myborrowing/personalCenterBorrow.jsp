<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/personcenter/myborrowing/personalCenterBorrow.js"></script>
<div id="borrow_info" class="sidebar_b borrow_div fl"> 
	<div class="fl title_div" id="borrow_area">
		<ul id="typeUl" class="type_title fl">
			<li class="change_li" onclick="myBorrow.loadData('1',null)">
				<a href="javascript:void(0)">待还款</a>
			</li>
			<li onclick="myBorrow.loadData('2',null)">
				<a href="javascript:void(0)">已还款</a>
			</li>
			<li onclick="myBorrow.loadData('3',null)">
				<a href="javascript:void(0)">招标中</a>
			</li>
			<li onclick="myBorrow.loadData('4',null)">
				<a href="javascript:void(0)">申请进度</a>
			</li>
		</ul>
	</div>
	<%--待还款表格--%>
	<div class="invest_table" id="tb_01">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="13%">编号</th>
					<th width="15%">项目名称</th>
					<th width="12%">借款金额</th>
					<th width="12%">已还金额</th>
					<th width="12%">待还金额</th>
					<th width="10%">已还/总期次</th>
					<th width="8%">状态</th>
					<th width="12%">下期待还时间</th>
					<th width="12%">操作</th>
				</tr>
			</thead>
			<tbody id="dhkTbody">
			</tbody>
		</table>
		<%-- 分页 --%>
		<div id="dhkTbodyPage" class="page_div fr"> </div>
	</div>
	
	<%--已还款表格--%>
	<div class="invest_table" id="tb_02">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="15%">编号</th>
					<th width="20%">项目名称</th>
					<th width="15%">借款金额</th>
					<th width="15%">应还金额</th>
					<th width="15%">已还金额</th>
					<th width="10%">状态</th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody id="yhkTbody">
			</tbody>
		</table>
		<%-- 分页 --%>
		<div id="yhkTbodyPage" class="page_div fr"> </div>
	</div>
	
	<%--招标中表格---%>
	<div class="invest_table" id="tb_03">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="15%">借款名称</th>
					<th width="12%">编号</th>
					<th width="12%">借款金额</th>
					<th width="8%">期限</th>
					<th width="8%">年化率</th>
					<th width="8%">管理费率</th>
					<th width="12%">总利息</th>
					<th width="10%">还款方式</th>
					<th width="5%">状态</th>
					<th width="5%">操作</th>
				</tr>
			</thead>
			<tbody id="zbzTbody">
			</tbody>
		</table>
		<%-- 分页 --%>
		<div id="zbzTbodyPage" class="page_div fr"> </div>
	</div>
	
	<%--申请进度表格--%>
	<div class="invest_table" id="tb_04">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
			<thead>
				<tr>
					<th width="18%">申请时间</th>
					<th width="14%">编号</th>
					<th width="15%">借款名称</th>
					<th width="13%">借款金额</th>
					<th width="6%">期限</th>
					<th width="10%">状态</th>
					<th width="10%">备注</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody id="sqjdTbody">
			</tbody>
		</table>
		<%-- 分页 --%>
		<div id="sqjdTbodyPage" class="page_div fr"> </div>
	</div>
	
	<div class="clearfix"></div>
	
</div>


