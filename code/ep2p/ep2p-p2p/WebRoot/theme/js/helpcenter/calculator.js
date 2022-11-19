/**
 * 投资计算器js处理类
 */
var calculator = {
		// 打开投资计算器页面
		openCalcPage:function(){
			window.open(BASE_PATH+'/help/calculatorController/toCalculatorPage.shtml');
		},
		// 清空
		clearForm:function(){
			common.clearForm("#calculatorForm");
		},
		// 计算
		execCalculator:function(){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/help/calculatorController/execCalculator.shtml",
		    	data:{"repaymentAmt":$("#repaymentAmt").val(),"repaymentRate":$("#repaymentRate").val(),"repaymentDeadline":$("#repaymentDeadline").val(),"repaymentType":$("#repaymentType").val()},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		var resultData = data.data;
			    		var html = "";
			    		var sumCapital = 0 ; 	// 本金总计
			    		var sumInterest = 0 ;	// 利息总计
			    		for(var i = 0 ;i < resultData.length ; i++){
			    			
			    			sumInterest+= resultData[i].interest;
			    			sumCapital+= resultData[i].capital;
			    			
			    			html+="<tr>";
			    			html += "<td class='pl30'>"+resultData[i].planIndex+"</td>";
			    			html += "<td>"+resultData[i].expireTime.split(" ")[0]+"</td>";
			    			html += "<td>"+common.formatCurrency(resultData[i].capital+resultData[i].interest)+"</td>";
			    			html += "<td>"+common.formatCurrency(resultData[i].capital)+"</td>";
			    			html += "<td>"+common.formatCurrency(resultData[i].interest)+"</td>";
			    			html += "<td>"+common.formatCurrency(resultData[i].remainingCapital)+"</td>";
			    			html+="</tr>";
			    		}
			    		// 添加总计
			    		html+="<tr>";
		    			html += "<td>总计</td><td></td>";
		    			html += "<td>"+common.formatCurrency(sumInterest+sumCapital)+"</td>";
		    			html += "<td>"+common.formatCurrency(sumCapital)+"</td>";
		    			html += "<td>"+common.formatCurrency(sumInterest)+"</td>";
		    			html += "<td></td>";
		    			html+="</tr>";
		    			
		    			// 表格赋值
			    		$("#calculatorTable #calculatorTbody").html(html);
			    		
			    		// 预期收益赋值
			    		$("#proceeds").html(common.formatCurrency(sumInterest));
			    	}else{
			    		layer.msg('投资计算失败,请联系管理员!', {icon: 5});
			    	}
				}
			});
		}
		
}