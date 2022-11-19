/**
 * 备付金
 */
var BizEnsureIndexGrid = {
	init:function(){
		BizEnsureIndexGrid.initIndexGrid();
		BizEnsureIndexGrid.isDisabledInitBut();
		BizEnsureIndexGrid.initBizEnsureRateParam();
	},
	//加载备付金列表
	initIndexGrid:function(){
		$('#bizEnsureIndexGrid').datagrid({    
			url:basePath +"bizEnsureMoneyDetailController/selectForTypeToList.shtml",
			width:'100%',
			fit:true,
			toolbar:'#biz_ensure_index_toolbar',
			pagination: true,
			rownumbers: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:biz_ensure_money_index_model,
		    onClickRow:function(rowIndex, rowData){
				$('#bizEnsureIndexGrid').datagrid('clearChecked');
				$('#bizEnsureIndexGrid').datagrid('checkRow',rowIndex);
		    },
		    onLoadSuccess:function(data){
		    	if($("#bizEnsureIndexGridDiv .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#bizEnsureIndexGridDiv .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#bizEnsureIndexGrid').datagrid("deleteRow",0);
				}
		    }
		});
	},
	// 是否禁用初始化备付金按钮
	isDisabledInitBut:function(){
		$.ajax( {
			type : "GET",
			url : BASE_PATH+"bizEnsureMoneyDetailController/isDisabledInitBut.shtml",
			dataType : "json",
			success : function(data) {
				debugger;
				if(data.message.status == 200){
					if(data.result == true || data.result == 'true'){
						$('#initBut').linkbutton({disabled: true});
					}
				}else{
					$.messager.alert('操作提示',data.data,'error');
				}
			}
		}); 
	},
	//保存备付金参数信息
	saveBizEnsureParamClick: function(){
		
		if(!$("#biz_ensure_update_form").form('validate')){
			return ;
		}
		
		var obj = jqueryUtil.serializeObject($("#biz_ensure_update_form"));
		$.post(BASE_PATH+"bizEnsureMoneyController/updateBizEnsureRateParam.shtml",obj, 
				function(ret) {
					if(ret.message.status == 200){
						BizEnsureIndexGrid.initBizEnsureRateParam();
						$.messager.alert('操作提示','保存成功','success');
					}else{
						$.messager.alert('操作提示',ret.data,'error');
					}
				},'json'
			);
	},

	initBizEnsureRateParam:function(){
		$.ajax( {
			type : "GET",
			url : BASE_PATH+"bizEnsureMoneyController/bizEnsureRateParam.shtml",
			async : false,
			dataType : "json",
			success : function(data) {
				$("#bizEnsureCapitalPenaltyRate").textbox("setValue",data.rows[0].BIZ_ENSURE_CAPITAL_PENALTY_RATE);
				$("#bizEnsureInterestPenaltyRate").textbox("setValue",data.rows[0].BIZ_ENSURE_INTEREST_PENALTY_RATE);
				$("#bizEnsureManageRate").textbox("setValue",data.rows[0].BIZ_ENSURE_MANAGE_RATE);
				$("#bizEnsureRateOfCall").textbox("setValue",data.rows[0].BIZ_ENSURE_RATE_OF_CALL);
				$("#bizEnsureBRiskWarn").textbox("setValue",common.formatCurrency(data.rows[0].BIZ_ENSURE_B_RISK_WARN));
				$("#bizEnsureARiskWarn").textbox("setValue",common.formatCurrency(data.rows[0].BIZ_ENSURE_A_RISK_WARN));
			}
		}); 
	},
	searchData:function(){
		var obj = jqueryUtil.serializeObject($("#biz_ensure_list_form"));
		$('#bizEnsureIndexGrid').datagrid('load',obj);
	},
	// 备付金设置 弹出框 type 1 设置初始备付金 2 调减备付金 3 调整备付金 
	initBizEnsure:function(type){
		var url ="";
		var title = "";
		if(type == 1){
			title = "设置初始备付金";
		}else if ( type == 2){
			title = "调减备付金";
		}else if ( type == 3){
			title = "调增备付金";
		}
		
		$("<div id='ensure_init_dialog' /> ").dialog({
			href:BASE_PATH+"bizEnsureMoneyController/toEnsureAdjustment.shtml?type="+type,
			title:title,
			method:'post',
			width:'500px',
			height:'300px',
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					BizEnsureIndexGrid.saveEnsureAdjustment();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$("#ensure_init_dialog").dialog('close');
				}
			}],
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	},
	saveEnsureAdjustment:function(){
		var obj = jqueryUtil.serializeObject($("#role_add_or_update_form"));
		$.ajax( {
			type : "POST",
			url : BASE_PATH+"bizEnsureMoneyController/saveEnsureAdjustment.shtml",
			async : false,
			data:obj,
			dataType : "json",
			success : function(data) {
				if(data.message.status == 200){
					$.messager.alert('操作提示','保存成功','success');
					$("#ensure_init_dialog").dialog('close');
					BizEnsureIndexGrid.searchData();
				}else{
					$.messager.alert('操作提示',ret.data,'error');
				}
			}
		}); 
	},
	cleanData:function(){
		$('#offline_recharge_list_form').form('reset');
	}
}
