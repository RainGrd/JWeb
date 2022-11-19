/**
 * 借款统计报表js类
 */
var borrowReport = {
		// 数据查询
		searchData:function(){
			var formData = jqueryUtil.serializeObject($("#searcm"),true);
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"report/bizBorrowReportController/getBorrowReport.shtml",
				dataType: "json",
				async:false,
				data:formData,
			    success: function(data){
			    	debugger;
			    	if(data.message ==200){
			    		$("#allApproveCount").html(data.allApprove.approveCount);
			    		$("#allApproveViaCount").html(data.allApprove.approveViaCount);
			    		$("#allApproveViaRate").html(data.allApprove.approveViaRate);
			    		
			    		$("#mortgageApproveCount").html(data.mortgageApprove.approveCount);
			    		$("#mortgageApproveViaCount").html(data.mortgageApprove.approveViaCount);
			    		$("#mortgageApproveViaRate").html(data.mortgageApprove.approveViaRate);
			    		
			    		$("#downLoanApproveCount").html(data.downLoanApprove.approveCount);
			    		$("#downLoanApproveViaCount").html(data.downLoanApprove.approveViaCount);
			    		$("#downLoanApproveViaRate").html(data.downLoanApprove.approveViaRate);
			    		
			    		$("#newStandardCount").html(data.newStandardCount);
			    		$("#experienceStandardCount").html(data.experienceStandardCount);
			    	}else{
			    		$.messager.alert("操作提示","借款统计报表数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			
		}
}