var bank={
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusBankGrids').datagrid({     
				url:basePath + 'customerController/selectBankInfoById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#batchToolbar',
			    sortOrder:'asc',
			    rownumbers:true,
			    remoteSort:false,
			    columns:bank_account,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusBankGrids').datagrid('clearChecked');
					$('#showCusBankGrids').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//加载客户详情
		loadData:function(){
			debugger;
			var pid =$("#pid").val();
			if("" != pid){
				$.ajax({
					type: "POST",
			    	url : basePath + 'customerController/loadCustomerInfo.shtml',
			    	data:{data:"{pid:"+pid+"}"},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		data.result.withdrawalsTotal=common.formatCurrency(data.result.withdrawalsTotal);
				    		if(data.result.availableBalance ==''|| data.result.availableBalance ==0){
				    			data.result.availableBalance = "0.00";
				    		}else{
				    			data.result.availableBalance=common.formatCurrency(data.result.availableBalance);
				    		}
				    		data.result.frozenAmount=common.formatCurrency(data.result.frozenAmount);
				    		if(data.result.receivedRedEnvelope=='' || data.result.receivedRedEnvelope==0){
				    			data.result.receivedRedEnvelope="0.00";
				    		}else{
				    			data.result.receivedRedEnvelope=common.formatCurrency(data.result.receivedRedEnvelope);
				    		}
				    		data.result.experienceGold=common.formatCurrency(data.result.experienceGold);
				    		data.result.totalAccount=common.formatCurrency(data.result.totalAccount);
				    		data.result.investmentAccount=common.formatCurrency(data.result.investmentAccount);
				    		data.result.investmentReward=common.formatCurrency(data.result.investmentReward);
				    		if(data.result.recommendReward=='' || data.result.recommendReward == 0){
				    			data.result.recommendReward="0.00";
				    		}else{
				    			data.result.recommendReward=common.formatCurrency(data.result.recommendReward);
				    		}
				    		if(data.result.netInterest=='' || data.result.netInterest==0){
				    			data.result.netInterest="0.00";
				    		}else{
				    			data.result.netInterest=common.formatCurrency(data.result.netInterest);
				    		}
				    		data.result.rechargeTotal=common.formatCurrency(data.result.rechargeTotal);
				    		if(data.result.totalStay ==''|| data.result.totalStay == 0){
				    			data.result.totalStay="0.00";
				    		}else{
				    			data.result.totalStay=common.formatCurrency(data.result.totalStay);
				    		}
				    		data.result.receivedInterest=common.formatCurrency(data.result.receivedInterest);
				    		if(data.result.jxInterest == '' || data.result.jxInterest == 0){
				    			data.result.jxInterest="0.00";
				    		}else{
					    		data.result.jxInterest=common.formatCurrency(data.result.jxInterest);
				    		}
				    		$("#baseInfos").form('load',data.result);
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		},
		//保存
		save:function(){
			var pid = $("#pid").val();
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#baseInfo1"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"customerController/saveBlacklistCustomerAllowStatus.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		window.location.href=BASE_PATH+"customerController/toDisableOrAllowBlacklistList.shtml?pid="+pid;
			    	}else{
			    		$.messager.alert('操作提示',"保存失败！",'error');
			    	}
				}
			}); 
		},
		loadRadio:function(){
			$.post(BASE_PATH+"sysDistionaryContentController/selectByDisctCodeNoPlease.shtml",{"dictCode":"CUS_BLACK_STATUS"}, 
				function(ret) {
					if(ret.message.status == 200){
						common.createRadioButtons("cusStatus_td","cusStatus",ret.data);
					}else{
						$.messager.alert('操作提示',"删除失败！",'error');
					}
				},'json'
			);
		},
		//客户积分明细
		selectavailablePointDetails:function(){
			var pid =$("#pid").val();
			var path = BASE_PATH+"customerController/toAvailablePointDetailsList.shtml?pid="+pid;
			$("<div id='selectavailablePointDetailsDialog' /> ").dialog({
				href:path,
				title:"客户积分明细",
				method:'post',
				width:'650',
				height:'350',
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectavailablePointDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//客户经验明细
		selectExperienceDetails:function(){
			var pid =$("#pid").val();
			var path = BASE_PATH+"customerController/toExperienceDetailsList.shtml?pid="+pid;
			$("<div id='selectExperienceDetailsDialog' /> ").dialog({
				href:path,
				title:"客户经验明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectExperienceDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//客户冻结余额明细
		selectFrozenAmountDetails:function(){
			var pid =$("#pid").val();
			var path = BASE_PATH+"customerController/toFrozenAmountDetailsList.shtml?pid="+pid;
			$("<div id='selectFrozenAmountDetailsDialog' /> ").dialog({
				href:path,
				title:"客户冻结余额明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectFrozenAmountDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户体验金资金流水明细
		selectTiyanGoldDetails:function(){
			var pid =$("#pid").val();
			var path = BASE_PATH+"customerController/toTiYanGoldDetailsList.shtml?pid="+pid;
			$("<div id='selectTiYanGoldDetailsDialog' /> ").dialog({
				href:path,
				title:"客户体验金资金流水明细",
				method:'post',
				width:'850',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectTiYanGoldDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//客户账户总额流水明细
		selectAccountTotalDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectAccountTotalDetailsDialog' /> ").dialog({
				href:path,
				title:"客户账户总额流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectAccountTotalDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户投资总额流水明细
		selectTouZiDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectTouZiDetailsDialog' /> ").dialog({
				href:path,
				title:"客户投资总额流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectTouZiDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户投标奖励流水明细
		selectTouBiaoDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectTouBiaoDetailsDialog' /> ").dialog({
				href:path,
				title:"客户投标奖励流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectTouBiaoDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户 推荐奖励流水明细
		selectTuiJianRewardDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectTuiJianRewardDetailsDialog' /> ").dialog({
				href:path,
				title:"客户推荐奖励流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectTuiJianRewardDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户净赚利息流水明细
		selectJinZhuangInterestDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectJinZhuangInterestDetailsDialog' /> ").dialog({
				href:path,
				title:"客户净赚利息流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectJinZhuangInterestDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//客户充值总额流水明细
		selectRechargeTotalDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectRechargeTotalDetailsDialog' /> ").dialog({
				href:path,
				title:"客户充值总额流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectRechargeTotalDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户提现总额流水明细
		selectWithdrawalsTotalDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"custFundWaterController/toAccountTotalDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectWithdrawalsTotalDetailsDialog' /> ").dialog({
				href:path,
				title:"客户提现总额流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectWithdrawalsTotalDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户总共待收流水明细
		selectTotalStayDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"customerController/toTotalStayDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectTotalStayDetailsDialog' /> ").dialog({
				href:path,
				title:"客户总共待收流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectTotalStayDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		
		//客户待收利息明细
		selectCollectInterestDetails:function(flag){
			var pid =$("#pid").val();
			var path = BASE_PATH+"customerController/toTotalStayDetailsList.shtml?pid="+pid+'&flag='+flag;
			$("<div id='selectCollectInterestDetailsDialog' /> ").dialog({
				href:path,
				title:"客户待收利息流水明细",
				method:'post',
				width:'650',
				height:'350',
				pagination: true,
			    rownumbers:true,
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#selectCollectInterestDetailsDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
}
