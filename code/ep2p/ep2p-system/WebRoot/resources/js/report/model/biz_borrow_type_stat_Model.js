var bizBorrowTypeStat_model = [[    
    	        {field:'borrowType',title:'类别',width:'10%',align:'center',sortable:true,formatter:typeFormart},    
    	        {field:'totalAmount',title:'金额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},    
    	        {field:'totalCount',title:'笔数',width:'10%',align:'center',sortable:true},
    	        {field:'amountPercent',title:'金额占比（%）',width:'10%',align:'center',sortable:true,formatter:amtProportionFormart}, 
    	        {field:'countPercent',title:'笔数占比（%）',width:'10%',align:'center',sortable:true,formatter:amtProportionFormart}
    	    ]];
/**
 * 类别格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function typeFormart(value,row,index){
	if(value == "1"){
		return "e首房";
	}else if(value == "2"){
		return "e抵押";
	}else if(value == "3"){
		return "e计划";
	}else if(value == "4"){
		return "新手标";
	}else if(value == "5"){
		return "体验标";
	}
	
	return "";
}

/**
 * 金额占比格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function amtProportionFormart(value,row,index){
	if(value!='' && value!=null){
		return (value*100).toFixed(2)+"%";
	}
	return "";
}