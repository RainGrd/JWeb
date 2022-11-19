/**
 * 投资管理
 */
var invest_manage_model = [[    
    {field:'pid',checkbox:true},
    {field:'borrowCode',title:'借款编号',width:'10%',align:'center',sortable:true,formatter:getBorrowCode},    
    {field:'borrowName',title:'借款名称',width:'10%',align:'center',sortable:true,formatter:getBorrowName},    
    {field:'customerName',title:'投资人',width:'10%',align:'center',sortable:true,formatter:common.getCustomerName},    
    {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true,formatter:common.getSName}, 
    {field:'investmentTime',title:'投资时间',width:'12%',align:'center',sortable:true},
    {field:'interestTime',title:'计息时间',width:'12%',align:'center',sortable:true},
    {field:'investmentAmount',title:'投资金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    {field:'borDeadline',title:'期限（月）',width:'5%',align:'center',sortable:true,formatter:getBorDeadline},
    {field:'borrowRate',title:'年化率（%）',width:'8%',align:'center',sortable:true,formatter:getBorrowApr},
    {field:'investAwardValue',title:'加息收益',width:'8%',align:'center',sortable:true,formatter:getInvestAwardValue},
    {field:'awardAmount',title:'投资奖励',width:'8%',align:'center',sortable:true},
    {field:'interest',title:'利息',width:'5%',align:'center',sortable:true,formatter:common.formatCurrency},
    {field:'borStatus',title:'状态',width:'5%',align:'center',sortable:true,formatter:getBorStatus}
]];

/**
 * 获取借款编码
 * @param value 当前列值
 * @param row 行对象
 * @param i 行数
 */
function getBorrowCode(value,row,i){
	//编码格式化
	return '<a href="#" onclick="viewBorrowInfo(\''+row.borrowId+'\')">'+row.bizBorrow.borrowCode+'</a>';
}

function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
	childLayout_addTab(path,"借款信息查看");
}

/**
 * 获取借款名称
 * @param v
 * @param row
 * @param i
 */
function getBorrowName(v,row,i){
	if(row.bizBorrow){
		return row.bizBorrow.borrowName
	}
	return "";
}

/**
 * 获取 期限（月）
 * @param v
 * @param row
 * @param i
 */
function getBorDeadline(v,row,i){
	if(row.bizBorrow){
		return row.bizBorrow.borDeadline
	}
	return "";
}

/**
 * 获取年利率（%）
 * @param v
 * @param row
 * @param i
 */
function getBorrowApr(v,row,i){
	if(row.bizBorrow){
		return row.bizBorrow.borrowRate
	}
	return "";
}

/**
 * 获取 投资奖励（%）
 * @param v
 * @param row
 * @param i
 */
function getInvestAwardValue(v,row,i){
	if(row.pubInvestAward){
		var investAwardValue = row.pubInvestAward.investAwardValue == null || row.pubInvestAward.investAwardValue == "" ? 
				0 : parseFloat(row.pubInvestAward.investAwardValue) ;
		return (parseFloat(row.investmentAmount) * investAwardValue).toFixed(2);
	}
	return "";
}

/**
 * 获取借款状态
 * @param v
 * @param row
 * @param i
 */
function getBorStatus(v,row,i){
	if(row.bizBorrow){
		var status = row.bizBorrow.borStatus;
		if(status == '0'){
			return "已撤销";
		}else if(status == '2'){
			return "招标中";
		}else if(status == '3'){
			return "已流标";
		}else if(status == '4'){
			return "已满标";
		}else if(status == '5'){
			return "收款中";
		}else if(status == '8'){
			return "已结清";
		}
	}
}

