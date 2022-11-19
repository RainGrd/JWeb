/**
 * 客户查询列表
 */
var customerManager_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'customerName',title:'用户名',width:'8%',align:'center',sortable:true},    
    	        {field:'sname',title:'姓名',width:'8%',align:'center',sortable:true},    
    	        {field:'phoneNo',title:'手机号码',width:'8%',align:'center',sortable:true}, 
    	        {field:'registrationTime',title:'注册时间',width:'12%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'refereeUser',title:'推荐人',width:'8%',align:'center',sortable:true}, 
    	        {field:'customerServiceUser',title:'客服',width:'8%',align:'center',sortable:true}, 
    	        {field:'oldcusServiceId',title:'客服id',width:'8%',align:'center', hidden:'true', sortable:true}, 
    	        {field:'totalStay',title:'总待收',width:'8%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'availableBalance',title:'可用余额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'availablePoint',title:'可用积分',width:'8%',align:'center',sortable:true}, 
    	        {field:'empiricalValue',title:'经验值',width:'8%',align:'center',sortable:true}, 
    	        {field:'vipLevel',title:'VIP',width:'8%',align:'center',sortable:true}, 
    	        {field:'isBlacklist',title:'黑名单',width:'8%',align:'center',sortable:true}, 
    	        {field:'isForbidWithdraw',title:'提现',width:'8%',align:'center',sortable:true}, 
    	        {field:'isForbidTransfer',title:'债权转让',width:'8%',align:'center',sortable:true}, 
    	        {field:'bid',title:'投标',width:'8%',align:'center',sortable:true}, 
    	        {field:'status',title:'用户状态',width:'8%',align:'center',sortable:true}, 
    	        {field:'isAttestation',title:'认证状态',width:'8%',align:'center',sortable:true}, 
    	        {field:'yy',title:'操作',width:'15%',align:'center',sortable:true,formatter:formatOperation}
    	    ]];
//操作格式化
function formatOperation (value,row,index){
	// 操作格式化
	   if(row.pid !="" && row.pid != null){
		   var result = '<a onclick="common.viewCusDetail(\''+row.pid+'\')" href="#">查看客户资料</a>';
			var result2 = '<a onclick="viewDetail1(\''+row.pid+'\',\''+row.oldcusServiceId+'\')" href="#">客户操作</a>';
			return result+" | "+result2;
	   }
};

function viewDetail1(pid,oldcusServiceId){
	var path = BASE_PATH+"customerController/toCustomerOperationPage.shtml?pid="+pid+'&oldcusServiceId='+oldcusServiceId;
	//childLayout_addTab(path,'客户操作');
	$("<div id='customerOperationDialog' /> ").dialog({
		href:path,
		title:"客户操作",
		method:'post',
		width:'400',
		height:'250',
		modal: true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				$("#customerOperationDialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
	
}