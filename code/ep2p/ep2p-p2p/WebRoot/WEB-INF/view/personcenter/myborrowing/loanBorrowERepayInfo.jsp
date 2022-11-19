<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/personcenter/myborrowing/personalCenterBorrow.js"></script>
<!-- 借款详情-->
<div class="sidebar_b fl ">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">借款详情</span>
	</div>
	<div class="borrow_info loanInfo fl f-12">
		<div class="binfo size14">
			<div class="binfotop">
				<div class="fl w200">D2015102301/标一：XXX房贷</div>
				<div class="fl "></div>
				<div class="fr w200 colorDarkBlue">还款方式：按月先息后本</div>
			</div>
			<div class="cb kong20"></div>
			<div class="ml40">
				<p class="w310">借款金额：<span class="f-24 colorc">￥5000000.00</span></p>
                            <p>年化率：<span class="f-24 colorc">16%</span></p>
                            <p>管理费率为 <span class="colorc">2.3%</span></p>
				<!-- 分页 -->
			</div>
                        <!--已结清状态-->
                        <p class="statusRota statusRota3"></p>
		</div>

	</div>
	

</div>
<div class="invest_table loanInfoTable" id="tb_01">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
        <thead>
            <tr>
                <th width="118px">待还时间</th>
                <th width="44px">期次</th>
                <th width="100px">还款金额</th>
                <th width="100px">应还本金</th>
                <th width="100px">应还利息</th>
                <th width="82px">应还管理费</th>
                <th width="78px">罚息</th>
                <th width="78px">状态</th>
                <th width="252px">备注</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>2015-01-01</td>
                <td>1/12</td> 
                <td>￥2654344.05</td>
                <td>￥2654344.05</td>
                <td>￥2654344.05</td>
                <td>￥54344.05</td>
                <td>￥54344.05</td>
                <td>已结清</td>
                <td>逾期赔付:100.00 逾期：10天</td>
            </tr>
        </tbody>
    </table>
</div>
