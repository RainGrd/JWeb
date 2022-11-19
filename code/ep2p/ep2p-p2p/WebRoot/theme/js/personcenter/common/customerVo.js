/**
 * 处理用户信息的js脚本
 */
var customerO = {
		//获取用户信息头像
		getCustomerVoInFo:function() {
			$.ajax({
				type : "POST",
				url : basePath + "login/userController/getCustomerVo.shtml",
				data : "",
				success : function(data) {
					//当用户登录之后，进行时时刷新数据
					if(undefined != data.result && null != data.result ){
						//加息卷
						var ticketcount = data.result.interestTicketCount;
						$("#cust_amenu_ticke_num").html(ticketcount + "张");
						$("#cust_smenu_ticke_num").html(ticketcount + "张");
						//未读消息
						var nmsgnum = data.result.messRecordCount;
						$("#cust_amenu_nmsg_num").html(nmsgnum);
						$("#cust_bmenu_nmsg_num").html(nmsgnum);
						//连续签到
						var sigin = data.result.signday;
						$("#cust_amenu_sing_num").html("连续" + sigin + "天" );
						$("#cust_bmenu_sing_num").html("连续" + sigin + "天" );
						//vip等级
//						var viplev = data.result.vipLevel;
//						$("#cust_amenu_viplev_num").html(viplev);
//						$("#cust_bmenu_viplev_num").html(viplev);
						
						
					}
				}
			});
		}
};

$(function(){
	customerO.getCustomerVoInFo();
});