/**
 * 贷前审核列表
 */
var borrowReviewManager_Model = [[    
                      	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCustomerName},    
                    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true},    
                    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
                    	        {field:'borrowTypeName',title:'借款类型',width:'10%',align:'center',sortable:true}, 
                    	        {field:'homeTotal',title:'房产总价',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency}, 
                    	        {field:'borrowMoney',title:'借款金额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency}, 
                    	        {field:'borDeadline',title:'期限(月)',width:'8%',align:'center',sortable:true}, 
                    	        {field:'applyTime',title:'申请时间',width:'12%',align:'center',sortable:true,formatter:applyTimeFormat}, 
                    	        {field:'borrowRate',title:'年化率（%）',width:'8%',align:'center',sortable:true,formatter:rateFormat}, 
                    	        {field:'repaymentTypeName',title:'还款方式',width:'10%',align:'center',sortable:true}, 
                    	        {field:'accrualTypeName',title:'计息类型',width:'10%',align:'center',sortable:true}, 
                    	        {field:'investRewardScale',title:'投资奖励(%)',width:'10%',align:'center',sortable:true,formatter:rateFormat}, 
                    	        {field:'approveStatusName',title:'审核状态',width:'8%',align:'center',sortable:true}, 
                    	        {field:'approveUserName',title:'审核人',width:'8%',align:'center'}, 
                    	        //{field:'approveUserName',title:'审核人',width:'8%',align:'center',formatter:filterApproveUser}, 
                    	        {field:'lendOpinion',title:'风控意见',width:'8%',align:'center',formatter:filterLendOpinion}, 
                    	        {field:'borCondDesc',title:'备注',width:'15%',align:'center',sortable:true,formatter:borCondDescFormater}, 
                    	        //{field:'borCondDesc',title:'备注',width:'15%',align:'center',sortable:true,formatter:detailsFormater}, 
                    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
                    	    ]];

function rateFormat(value,row,index){
	
	if(value == '' || value == null){
		return value;
	}
	
	return value*100;
	
}

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
	if(value =='' || value == null){
		return  '<a onclick="common.viewCusDetail(\''+row.customerId+'\')" href="#">'+row.phoneNo+'</a>';
	}
	return  '<a onclick="common.viewCusDetail(\''+row.customerId+'\')" href="#">'+value+'</a>';

}

/**
 * 借款利率初始化
 * @param value
 * @param row
 * @param index
 */
function borrowRateFormat(value,row,index){
	return  value*100;
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
	// 只有审核状态为审核中的数据才能审核
	var view = 'yes';
	if(row.approveStatus == 4){
		prelim = '<a href="#" onclick="toBorrowApprvoe(\''+row.pid+'\')">审核</a>';
		view = 'no';
	}
	var view =  '<a href="#" onclick="viewBorrowInfo(\''+row.pid+'\',\''+view+'\')">查看资料</a>';
	var result = view+' | '+prelim;
	return result;
}

/**
 * 审批人过滤
 * @param value
 * @param row
 * @param index
 * @returns
 */
function filterApproveUser(value,row,index){
	if(row.approveStatus == 4){
		return "";
	}
	return value;
}

/**
 * 风控意见显示过滤
 * @param value
 * @param row
 * @param index
 * @returns
 */
function filterLendOpinion(value,row,index){
	// 借前审核，风控意见展示 担保初审填写的风控意见
	if(row.approveStatus == 4){
		return "<text title='"+row.vouchOpinion+"'>"+row.vouchOpinion+"</text>";
	}else if(row.approveStatus == 5){ // 借前拒绝 ，展示借前审核填写的风控意见
		return  "<text title='"+row.lendOpinion+"'>"+row.lendOpinion+"</text>";
	}
}

// 跳转到借款贷前审核页面
function toBorrowApprvoe(pid){
	var path = BASE_PATH+'bizBorrowApproveController/toBorrowApprvoe.shtml?pid='+pid;
	childLayout_addTab(path,"借前审核");
}

//借款贷前审核查看资料
function viewBorrowInfo(pid,view){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view='+view;
	childLayout_addTab(path,"借款贷前审核查看");
}
