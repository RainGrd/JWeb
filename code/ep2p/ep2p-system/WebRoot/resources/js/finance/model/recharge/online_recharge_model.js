
/**
 * 客户充值列表数据模型
 */
var online_recharge_list_model = 
    [[    
       {field:'pid',checkbox:true},
       {field:'customerName',title:'客户名称',width:'10%',align:'center',formatter:common.getCustomerName},    
       {field:'sname',title:'真实姓名',width:'10%',align:'center',formatter:common.getSName}, 
       {field:'phoneNo',title:'手机号码',width:'10%',align:'center',formatter:common.getPhoneNo},
       
       {field:'recTime',title:'充值时间',width:'12%',align:'center',sortable:true},    
       {field:'payName',title:'充值渠道',width:'10%',align:'center',sortable:true},    
       {field:'amount',title:'充值金额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency}, 
       {field:'recOrderNo',title:'订单号',width:'10%',align:'center',sortable:true}, 
       {field:'recStatus',title:'审核状态',width:'5%',align:'center',sortable:true,formatter:formatRechargeStatus}, 
       {field:'lastUpdateUser',title:'处理人',width:'8%',align:'center',sortable:true}, 
       {field:'recOnlDesc',title:'备注',width:'10%',align:'center'}, 
       {field:'operation',title:'操作',width:'5%',align:'center',formatter:formatOnlineRechargeOperation}
    ]];

/**
 * 格式化充值状态
 * @param value
 * @param row
 * @param index
 */
function formatRechargeStatus(value,row,index){
	if(value=="1"){
		return "待付款";
	}else if(value=="2"){
		return "付款失败";
	}else if(value=="3"){
		return "充值成功";
	}else if(value=="4"){
		return "待充值";
	}else if(value=="5"){
		return "系统充值";
	}else if(value=="6"){
		return "手动补单";
	}else if(value=="7"){
		return "错误修正";
	}
}
/**
 *  格式化客户线上充值列表操作
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOnlineRechargeOperation(value,row,index){
	var strBut = "";
	if ("1" == row.recStatus) {
		// 客户线上充值表ID
		var onRhgId = row.pid;
		//客户ID
		var customerId = row.customerId;
		//客户名
		var customerName = row.customer.customerName;
		//真实姓名
		var sname = row.customer.sname;
		// 充值时间
		var recTime = row.recTime;
		// 充值金额
		var amount = row.amount;
		// 支付平台
		var payName = row.payName;
		// 订单号
		var recOrderNo = row.recOrderNo;
		
		strBut += '<a onclick="openReplacementrderPage(\'' + onRhgId
				+ '\',\'' + customerName + '\',\'' + sname + '\',\'' + recTime
				+ '\',\'' + amount + '\',\'' + payName
				+ '\',\'' + recOrderNo + '\',\'' + customerId
				+'\')" href="#">补单</a>';
	}else if(!row.pid){
		return strBut;
	} else {
		strBut += "补单";
	}
	return strBut;
}

/**
 * 打开客户线上补单页面
 * @param clientPid 客户ID
 * @param offRhgId 线下充值ID
 */
function openReplacementrderPage(onRhgId, customerName, sname, recTime, amount,
		payName, recOrderNo, customerId) {
	var url = BASE_PATH
			+ "bizRechargeOnlineController/openReplacementrderPage.shtml?onRhgId="
			+ onRhgId + "&customerName=" + customerName + "&sname=" + sname
			+"&recTime="+recTime+ "&amount=" + amount + "&payName=" + payName
			+ "&recOrderNo=" + recOrderNo + "&customerId=" + customerId;
	// 因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	url = encodeURI(encodeURI(url));
	$("<div id='replacement_order_dialog' /> ").dialog({
		href:url,
		title:"线上充值补单",
		method:'post',
		width:'500px',
		height:'420px',
		modal: true,
		buttons:[{
			text:'补单',
			iconCls:'icon-save',
			handler:function(){
				OnlineRecharge.replacementOrder();
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

