/**
 * 实名认证列表字段
 */
var custExchangeDetailStatistics_Model = [[    
                {field:'exchangeType',title:'类别',width:'15%',align:'center',sortable:true,formatter:formatExchangeType},    
    	        {field:'pensNumber',title:'笔数',width:'10%',align:'center',sortable:true},    
    	        {field:'pensNumberProportion',title:'笔数占比',width:'10%',align:'center'},    
    	        {field:'total',title:'总额',width:'15%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	    ]];

/**
 * 格式化兑换类别
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatExchangeType(value,row,index){
	if(row.exchangeType == 1 ){
		// 如果是兑换话费
		if(row.exchangePoint == 20000 ){
			// 如果是兑换积分为20000分,说明是兑换话费20元
			return "兑换话费-20元";
		}else if(row.exchangePoint == 50000 ){
			// 如果是兑换积分为50000分,说明是兑换话费50元
			return "兑换话费-50元";
		}else if(row.exchangePoint == 90000 ){
			// 如果是兑换积分为90000分,说明是兑换话费100元
			return "兑换话费-100元";
		}
	}else if(row.exchangeType == 2 ){
		// 如果是兑换加息劵
		if(row.exchangePoint == 90000 ){
			// 如果是兑换积分为90000分,说明是兑换加息劵0.2%
			return "兑换加息劵-0.2%";
		}else if(row.exchangePoint == 190000 ){
			// 如果是兑换积分为190000分,说明是兑换加息劵0.5%
			return "兑换加息劵-0.5%";
		}else if(row.exchangePoint == 380000 ){
			// 如果是兑换积分为380000分,说明是兑换加息劵1.0%
			return "兑换加息劵-1.0%";
		}
	}else if(row.exchangeType == 3 ){
		// 如果是兑换VIP
		if(row.exchangePoint == 120000 ){
			// 如果是兑换积分为120000分,说明是兑换VIP12月
			return "兑换VIP-12月";
		}else if(row.exchangePoint == 200000 ){
			// 如果是兑换积分为200000分,说明是兑换VIP24月
			return "兑换VIP-24月";
		}else if(row.exchangePoint == 320000 ){
			// 如果是兑换积分为320000分,说明是兑换VIP36月
			return "兑换VIP-36月";
		}
	}else if(row.exchangeType == 4 ){
		// 如果是兑换现金
		if(row.exchangePoint == 20000 ){
			// 如果是兑换积分为20000分,说明是兑换现金
			return "兑换现金-20元";
		}else if(row.exchangePoint == 50000 ){
			// 如果是兑换积分为50000分,说明是兑兑换现金
			return "兑换现金-50元";
		}else if(row.exchangePoint == 100000 ){
			// 如果是兑换积分为100000分,说明是兑换现金
			return "兑换现金-100元";
		}
	}
}
