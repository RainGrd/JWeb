//客户历史查看
function customerHistoryView(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/customerHistoryView.shtml?pid="+pid;
	childLayout_addTab(path,'客服历史查看');
};
//赠送VIP
function giveVip(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/toGiveVipList.shtml?pid="+pid;
	childLayout_addTab(path,'赠送VIP');
};
//冻结/启用该客户
function frozenOrEnableCustomer(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/toDisableOrEnableCusStatusList.shtml?pid="+pid;
	childLayout_addTab(path,'禁止/启用该客户');
};
//禁止/允许提现
function banOrAllowWithdrawals(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/toDisableOrAllowWithdrawalList.shtml?pid="+pid;
	childLayout_addTab(path,'禁止/允许提现');
};
//启用/撤销黑名单
function enableOrRevokeBlacklist(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/toDisableOrAllowBlacklistList.shtml?pid="+pid;
	childLayout_addTab(path,'禁止/允许黑名单');
};
//禁止/允许投标
function banOrAllowBid(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/toDisableOrAllowBidList.shtml?pid="+pid;
	childLayout_addTab(path,'禁止/允许投标');
};
//禁止/允许债权转让
function banOrAllowZQZR(){
	var pid = $("#pid").val();
	var path = BASE_PATH+"customerController/toDisableOrAllowCredRightList.shtml?pid="+pid;
	childLayout_addTab(path,'禁止/允许债权转让');
};