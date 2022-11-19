/**
 * 所有的消息提示语信息
 */
//公共消息
var COMMON_MSG = {
		
};

//用户中心
var USERCENTER_MSG = {
		/* 自动投标  */
		ABADD_SUCCESS:"自动投标添加成功",
		ABEDIT_SUCCESS:"自动投标修改成功",
		ABDELETE_SUCCESS:"自动投标删除成功",
		ABCOMMON_OPTIONS_ERROR:"自动投标参数异常",
		ABCOMMON_OUTQUENE_FAIL:"投标信息出队失败",
		ABCOMMON_NOTUSER:"用户信息不存在",
		ABCOMMON_NOTFOUNDAB:"没有找到对应的自动投标信息",
		ABPAY_FAIL:"自动投标操作失败",
		/* 头像更改 */
		AVATER_SUCCESS:"头像保存成功，请退出重新登录",
		AVATER_FAIL:"头像保存失败"
};


//用户登录
var USERLOGIN_MSG = {
		
};


//用户注册
var USERREGISTER_MSG = {
		
};

//用户密码
var USER_PWD_MSG = {
		TRADE_ERROR_MSG:"交易密码不正确",
		TRADE_BLANK_MSG:"请输入交易密码",
		TO_SET_TRADE_PWD:"您还没有设置交易密码，<a href='"+BASE_PATH+"securityCenter/bankController/personalData.shtml' target='_parent'>去设置</a>"
};
// 协议信息
var PROTOCOL_MSG = {
		TRANSFER:"您还没有同意《e生财富债权转让协议》"
}
// 债权信息
var CLAIM_TRANSFER = {
		TRANSFER_ERROR:"出错啦，转让失败了",
		REVOKE_SUC:"撤销成功",
		REVOKE_ERROR:"撤销失败了"
		
}
// 新手标信息
var NEW_STANDARD = {
		PROTOCOL_MSG:"尊敬的用户，您还未同意《e生财富借款协议》",
		INPUT_AMOUNT:"输入投资金额",
		OVERFLOW_AMOUNT:"超出可投金额",
		NOT_SCOPE_AMOUNT:"不在投资范围",
		TO_RECHARGE:'余额不足<a href="'+basePath+'recharge/userRechargeController/toUserRecharge.shtml" class="btn_samll" style="padding:5px 10px;">充值</a>'
			
}
// 积分兑换
var POINT_EXCHANGE = {
		POINT_NOT_ENOUGH:"积分兑换失败,积分不够.",
		EXCHANGE_PASSWROD_FREEZE:"交易密码错误超过上限,为保护账户安全,系统禁止交易",
		EXCHANGE_ERROR:"出错啦,兑换失败."
}

var PREREPAYMENT= {
		PREREPAYMENT_ERROR:"出错啦，提前还款失败了",
		AVAILABLE_AMOUNT_NO:"可用余额不足，请充值",
		PLEASE_REPAYMENT_TYPE:"请选择还款方式"
}
