var TransferManage = {
		initData:function(flag){
			if(flag==null || flag == ''){
				flag = "1";
			}
			loadData(flag,null);
		}
		
}


/*加载数据*/
function loadData(flag,elId){
	var url = BASE_PATH+"mybids/transferController/findTransferCenterListData.shtml?flag="+flag;
	var pageElId = "";
	if(flag == '1'){
		pageElId = 'holdClaimPage';
	}else if(flag == '2'){
		pageElId = 'transferingClaimPage';
	}else if(flag == '3'){
		pageElId = 'transferedClaimPage';
	}
	var params;
	if(elId!=null && elId != '' ){
		params = $("#"+elId).serializeArray();
		$("#"+pageElId).page( 'destroy' );
	}
	initPage(url,flag,params,pageElId);
}


/*初始化分页*/
function  initPage(url,flag,params,pageElId){
	/*分页*/
	$("#"+pageElId).page({
		pageSize : 10,    // 测试设置为1
		showInfo : false,
		showJump : false,
		showPageSizes : false,
		loadFirstPage : true,  // 自动加载第一页
		remote : {
			url : url,
			params : params, //条件查询
			success : function(result, pageIndex) {
				if(result.message.status == 200 ){
		    		if(flag == '1'){
		    			// 持有债权
		    			htmlHoldClaimList(result.vos);
		    		}else if(flag == '2'){
		    			// 转让中债权
		    			htmlTransferingList(result.vos);
		    		}else if(flag == '3'){
		    			// 已转让债权
		    			htmlTransferedList(result.vos);
		    		}
		    	}
			},
			pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
			pageSizeName : 'pageSize'//请求参数，每页数量
		}
	});
}

/*持有中*/
function htmlHoldClaimList(vos){
	var html = "";
	if(vos != null && vos.length>0){
		for(var i=0;i<vos.length;i++){
			var amount = vos[i].amount==null?0.00:accounting.formatNumber(vos[i].amount,2);
			var capital = vos[i].capital==null?0.00:accounting.formatNumber(vos[i].capital,2);
			var interest = vos[i].interest==null?0.00:accounting.formatNumber(vos[i].interest,2);
			var lateFee = accounting.formatNumber(vos[i].lateFee,2);
			var lateFeeDate = vos[i].lateFeeDate;
			var lf = '';
			var ld = '';
			if(vos[i].lateFee > 0){
				lf = '<p><span class="tipsSpan30">逾期赔付</span><span class="tipsSpan70">￥'+lateFee+'</span></p>';
				ld = '<p><span class="tipsSpan30">逾期</span><span class="tipsSpan70">'+lateFeeDate+'天</span></p>';
			}
			html += '<tr>'+
				 '<td class="pos-r"> '+vos[i].receiptTime+ '</td>'+ 
				 '<td class="colororg"> '+vos[i].transferCode+' </td>'+ 
				 '<td><a target="_blank" href="'+basePath+'business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowName+'</a> </td>'+
				 '<td> '+vos[i].planIndex+'/'+vos[i].deadline+'  </td>'+
				 '<td><span class="tipsBtn">￥'+amount+
	        			'<div class="tipsWrap">'+
	                     '<p><span class="bottom">￥'+amount+'</span></p>'+
	                     '<p class="background"><span class="tipsSpan30">类型</span><span class="tipsSpan70">金额</span></p>'+
	                     '<p><span class="tipsSpan30">本金</span><span class="tipsSpan70">￥'+capital+'</span></p>'+
	                     '<p><span class="tipsSpan30">利息</span><span class="tipsSpan70">￥'+interest+'</span></p>'+
	                     lf+ld+
	            		'</div>'+
	        		'</span></td>'+
				 '<td> '+(vos[i].receiptPlanStatus == '4'?'待收中': vos[i].receiptStatusName)+' </td>'+ 
				 '<td> '+vos[i].transferTime+' </td>'+ 
				 '<td> <a target="_blank" href="'+BASE_PATH+'mybids/contractController/transfer/contract.shtml?transferId='+vos[i].transferId+'"><img class="btn_img" src="'+BASE_PATH+'/theme/default/images/operate.png" alt="转让合同" /> </td></a>'+ 
				 '</tr> ';
		}
	}
	$("#holdClaim").html(html);
}

/*转让中*/
function htmlTransferingList(vos){
	var html = "";
	if(vos != null && vos.length>0){
		for(var i=0;i<vos.length;i++){
			
			var amount = vos[i].amount==null?0.00:accounting.formatNumber(vos[i].amount,2);
			var successAmount = vos[i].successAmount==null?0.00:accounting.formatNumber(vos[i].successAmount,2);
			var capital = vos[i].capital==null?0.00:accounting.formatNumber(vos[i].capital,2);
			var interest = vos[i].interest==null?0.00:accounting.formatNumber(vos[i].interest,2);
			var yearRate = vos[i].yearRate==null?0.00:(vos[i].yearRate*100).toFixed(2);
			var netReceiptAmount = vos[i].netReceiptAmount==null?0.00:accounting.formatNumber(vos[i].netReceiptAmount,2);
			var lateFee = accounting.formatNumber(vos[i].lateFee,2);
			var lateFeeDate = vos[i].lateFeeDate;
			var lf = '';
			var ld = '';
			if(lateFee > 0){
				lf = '<p><span class="tipsSpan30">逾期赔付</span><span class="tipsSpan70">￥'+lateFee+'</span></p>';
				ld = '<p><span class="tipsSpan30">逾期</span><span class="tipsSpan70">'+lateFeeDate+'天</span></p>';
			}
			html += '<tr>'+
				 '<td class="pos-r"> '+vos[i].receiptTime+ '</td>'+ 
				 '<td class="colororg"> '+vos[i].transferCode+' </td>'+ 
				 '<td> <a target="_blank" href="'+basePath+'business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowName+'</a></td>'+
				 '<td> '+vos[i].planIndex+'/'+vos[i].deadline+'  </td>'+
				 '<td> <span class="tipsBtn">￥'+amount+
     			'<div class="tipsWrap">'+
                  '<p><span class="bottom">￥'+amount+'</span></p>'+
                  '<p class="background"><span class="tipsSpan30">类型</span><span class="tipsSpan70">金额</span></p>'+
                  '<p><span class="tipsSpan30">本金</span><span class="tipsSpan70">￥'+capital+'</span></p>'+
                  '<p><span class="tipsSpan30">利息</span><span class="tipsSpan70">￥'+interest+'</span></p>'+
                  lf+ld+
                  '</div></span></td>'+
				 '<td> '+vos[i].returnedDate+' </td>'+ 
				 '<td> 转让中 </td>'+ 
				 '<td> ￥'+successAmount+' </td>'+ 
				 '<td> '+yearRate+'% </td>'+ 
				 ' <td> <a class="btn_samll" href="javascript:void(0)" onclick="TransferRevoke.openWin(\''+vos[i].transferId+'\',\''+amount+'\',\''+successAmount+'\',\''+vos[i].returnedDate+'\',\''+yearRate+'\',\''+netReceiptAmount+'\')">撤消</a> </td>'+ 
				 '</tr> ';
		}
	}
	$("#transferingClaim").html(html);
	
}

/*已转让*/
function htmlTransferedList(vos){
	var html = "";
	if(vos != null && vos.length>0){
		for(var i=0;i<vos.length;i++){
			
			var amount = vos[i].amount==null?0.00:accounting.formatNumber(vos[i].amount,2);
			var successAmount = vos[i].successAmount==null?0.00:accounting.formatNumber(vos[i].successAmount,2);
			var capital = vos[i].capital==null?0.00:accounting.formatNumber(vos[i].capital,2);
			var interest = vos[i].interest==null?0.00:accounting.formatNumber(vos[i].interest,2);
			var yearRate = vos[i].yearRate==null?0:parseFloat(vos[i].yearRate*100);
			var lateFee = accounting.formatNumber(vos[i].lateFee,2);
			var lateFeeDate = vos[i].lateFeeDate;
			var lf = '';
			var ld = '';
			if(lateFee > 0){
				lf = '<p><span class="tipsSpan30">逾期赔付</span><span class="tipsSpan70">￥'+lateFee+'</span></p>';
				ld = '<p><span class="tipsSpan30">逾期</span><span class="tipsSpan70">'+lateFeeDate+'天</span></p>';
			}
			
			html += '<tr>'+
			'<td class="pos-r"> '+vos[i].receiptTime+ '</td>'+ 
			'<td class="colororg"> '+vos[i].transferCode+' </td>'+ 
			'<td> <a target="_blank" href="'+basePath+'business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowName+'</a>  </td>'+
			'<td> '+vos[i].planIndex+'/'+vos[i].deadline+'  </td>'+
			 '<td> <span class="tipsBtn">￥'+amount+
 			'<div class="tipsWrap">'+
              '<p><span class="bottom">￥'+amount+'</span></p>'+
              '<p class="background"><span class="tipsSpan30">类型</span><span class="tipsSpan70">金额</span></p>'+
              '<p><span class="tipsSpan30">本金</span><span class="tipsSpan70">￥'+capital+'</span></p>'+
              '<p><span class="tipsSpan30">利息</span><span class="tipsSpan70">￥'+interest+'</span></p>'+
              lf+ld+
              '</div>'+
 		'</span></td>'+
			'<td> 已转让 </td>'+ 
			'<td> '+successAmount+' </td>'+ 
			'<td> '+yearRate+'% </td>'+ 
			'<td> '+vos[i].transferTime+' </td>'+ 
			 '<td> <a target="_blank" href="'+BASE_PATH+'mybids/contractController/transfer/contract.shtml?transferId='+vos[i].transferId+'"><img class="btn_img" src="'+BASE_PATH+'/theme/default/images/operate.png" alt="转让合同" /> </td></a>'+  
			'</tr> ';
		}
	}
	$("#transferedClaim").html(html);
	
}

