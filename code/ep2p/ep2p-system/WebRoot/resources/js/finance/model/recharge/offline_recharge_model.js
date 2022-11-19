/**
 * 线下充值查询首先列表Model
 */
var offline_index_client_model = [[    
    {field:'customerName',title:'客户名称',width:'20%',align:'center',formatter:common.getCustomerName2},    
    {field:'sname',title:'真实姓名',width:'20%',align:'center',sortable:true}, 
    {field:'phoneNo',title:'手机号码',width:'20%',align:'center',sortable:true}, 
    {field:'operation',title:'操作',width:'20%',align:'center',formatter:formatOperation}
]];

/**
 * 操作 格式化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOperation(value,row,index){
	var strBut = ""
	var clientPid = row.pid;
	strBut += '<a onclick="openClientRecharge(\''+clientPid+'\')" href="#">充值</a>';
	return strBut;
}

/**
 * 客户详情
 * @param value
 * @param row
 * @param index
 */
function clientDetails(value,row,index){
	var strBut = ""
		var pid = row.pid;
		var roleName= row.roleName;
		var roleCode = row.roleCode;
		strBut += '<a onclick="openUserAssign(\''+pid+'\')" href="#">'+value+'</a>  ';
		return strBut;
}

/**
 * 打开客户充值页面
 * @param clientPid 客户ID
 */
function openClientRecharge(clientPid){
	var url = BASE_PATH + "bizRechargeOfflineController/openClientRecharge.shtml?clientPid="+clientPid;
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	//url = encodeURI(encodeURI(url));
	$("<div id='client_rechagre_dialog' /> ").dialog({
		href:url,
		title:"客户充值",
		method:'post',
		width:'800px',
		height:'400px',
		modal: true,
		buttons:[{
			text:'提交审核',
			iconCls:'icon-save',
			handler:function(){
				OfflineRecharge.rechargeSave();
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

/*******************************************客户充值列表*************************************************/

/**
 * 客户充值列表数据模型
 */
var offline_recharge_list_model = 
    [[    
       {field:'pid',checkbox:true},
       {field:'customerName',title:'客户名称',width:'10%',align:'center',formatter:common.getCustomerName},    
       {field:'sname',title:'真实姓名',width:'10%',align:'center',formatter:common.getSName}, 
       {field:'phoneNo',title:'手机号码',width:'10%',align:'center',formatter:common.getPhoneNo}, 
       
       {field:'recTime',title:'充值时间',width:'14%',align:'center',sortable:true},    
       {field:'amount',title:'充值金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency}, 
       {field:'recStatus',title:'审核状态',width:'6%',align:'center',sortable:true,formatter:formatCheckStatus}, 
       {field:'lastUpdateUser',title:'处理人',width:'8%',align:'center',sortable:true}, 
       {field:'recOffDesc',title:'备注',width:'10%',align:'center'}, 
       {field:'operation',title:'操作',width:'10%',align:'center',formatter:formatOfflineRechargeOperation}
    ]];

/**
 * 格式化审核状态
 * @param value
 * @param row
 * @param index
 */
function formatCheckStatus(value,row,index){
	if(value=="1"){
		return "审核中";
	}else if(value=="2"){
		return "成功";
	}else if(value=="3"){
		return "拒绝";
	}
}
/**
 *  格式化客户线下充值列表操作
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOfflineRechargeOperation(value,row,index){
	var strBut = ""
	if("1"==row.recStatus){
		//客户ID
		var clientPid = row.customer.pid;
		//客户下线充值表ID
		var offRhgId = row.pid;
		//充值时间
		var recTime = row.recTime;
		//充值金额
		var amount = row.amount;
		strBut += '<a onclick="openCheckRechargePage(\''+clientPid+'\',\''+offRhgId+'\',\''+recTime+'\',\''+amount+'\')" href="#">审核</a>';
	}else if(!row.pid){
		return strBut;
	}else{
		strBut += "审核";
	}
	return strBut;
}

/**
 * 打开客户线下充值审核页面
 * @param clientPid 客户ID
 * @param offRhgId 线下充值ID
 */
function openCheckRechargePage(clientPid,offRhgId,recTime,amount){
	var url = BASE_PATH + "bizRechargeOfflineController/openCheckRechargePage.shtml?clientPid="+
		clientPid+"&offRhgId="+offRhgId+"&recTime="+recTime+"&amount="+amount;
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	//url = encodeURI(encodeURI(url));
	$("<div id='client_rechagre_check_dialog' /> ").dialog({
		href:url,
		title:"客户充值审核",
		method:'post',
		width:'800px',
		height:'470px',
		modal: true,
		buttons:[{
			text:'提交',
			iconCls:'icon-save',
			handler:function(){
				OfflineRecharge.offlineRechargeCheck();
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}


