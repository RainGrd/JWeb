/**
 * 担保初审列表
 */
var borrowPrelimManager_Model = [[    
                      	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCustomerName},    
                    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true},    
                    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
                    	        {field:'borrowTypeName',title:'借款类型',width:'10%',align:'center',sortable:true}, 
                    	        {field:'homeTotal',title:'房产总价',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency}, 
                    	        {field:'borrowMoney',title:'借款金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency}, 
                    	        {field:'borDeadline',title:'期限(月)',width:'10%',align:'center',sortable:true}, 
                    	        {field:'applyTime',title:'申请时间',width:'15%',align:'center',sortable:true,formatter:applyTimeFormat}, 
                    	        {field:'approveStatusName',title:'审核状态',width:'10%',align:'center',sortable:true}, 
                    	        {field:'approveUserName',title:'审核人',width:'10%',align:'center',sortable:true}, 
                    	        {field:'vouchOpinion',title:'风控意见',width:'15%',align:'center',sortable:true,formatter:filterVouchOpinion}, 
                    	        {field:'borCondDesc',title:'备注',width:'15%',align:'center',sortable:true,formatter:borCondDescFormater}, 
                    	        //{field:'borCondDesc',title:'备注',width:'15%',align:'center',sortable:true,formatter:detailsFormater}, 
                    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
                    	    ]];



/**
 * 客户名称格式化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatCustomerName(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	debugger;
	
	if(value =='' || value == null){
		return  '<a onclick="common.viewCusDetail(\''+row.customerId+'\')" href="#">'+row.phoneNo+'</a>';
	}
	return  '<a onclick="common.viewCusDetail(\''+row.customerId+'\')" href="#">'+value+'</a>';

}

function applyTimeFormat(value,row,index){
	if(value == null || value == undefined ){
		return "";
	}
	return value.split(".")[0];
}

function borCondDescFormater(value,row,index){
	if(value == null || value == undefined ){
		return "";
	}
	return value;
}

//操作 格式化
function formatOperation(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	var prelim = '<font color="grey"> 审核 </font> ';
	// 只有审核状态为担保初审 贷前拒绝 的数据才能审核
	var isEdit = 'no';
	if(row.approveStatus == 2 || row.approveStatus == 5){
		isEdit = 'yes';
		prelim = '<a href="#" onclick="toBorrowApprvoe(\''+row.pid+'\')">审核</a>';
	}
	var view =  '<a href="#" onclick="viewBorrowVouchInfo(\''+row.pid+'\',\''+isEdit+'\')">查看资料</a>';
	var result = view+' | '+prelim;
	return result;
}

/**
 * 风控意见显示过滤
 * @param value
 * @param row
 * @param index
 * @returns
 */
function filterVouchOpinion(value,row,index){
	// 担保拒绝，风控意见展示 担保初审填写的风控意见
	var result = "<text>"
	if(row.approveStatus == 3){
		return "<text title='"+row.vouchOpinion+"'>"+row.vouchOpinion+"</text>";
	}else if(row.approveStatus == 5){ // 借前拒绝 ，展示借前审核填写的风控意见
		return  "<text title='"+row.lendOpinion+"'>"+row.lendOpinion+"</text>";
	}
}

// 跳转到借款担保审核页面
function toBorrowApprvoe(pid){
	var path = BASE_PATH+'bizBorrowApproveController/toBorrowApprvoe.shtml?pid='+pid+'&approveNode=2';
	childLayout_addTab(path,"借款审核");
}

//借款担保审核查看资料
function viewBorrowVouchInfo (pid,isEdit){
	var path = BASE_PATH+'bizBorrowController/view.shtml?pid='+pid+'&approveNode=2&view=yes&isEdit='+isEdit;
	childLayout_addTab(path,"借款担保初审查看");
}