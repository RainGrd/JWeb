var  fundwater  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showFundWaterGrids').datagrid({     
				url:basePath + 'custFundWaterController/selectZiJinWater.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_fundwater,
			    onClickRow:function(rowIndex, rowData){
					$('#showFundWaterGrids').datagrid('clearChecked');
					$('#showFundWaterGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showFundWaterGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showFundWaterGrids').datagrid("deleteRow",0);
					}
				}
			});
		},
		//查询流水类型
		searchFundWater:function(){
			var pId=$("#pid").val();
			$("<div id='fundWaterDialog'/> ").dialog({
				href:basePath + "custFundWaterController/toFundWaterList.shtml?pid="+pId,
				title:"资金流水类型查询",
				method:'post',
				width:'500',
				height:'300',
				modal: true,
				buttons:[{
					text:'确定',
					iconCls:'icon-save',
					handler:fundwater.save
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#fundWaterDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//选择不同的资金流水类型事件
		 waterType:function(title,index){
			 if(title=='交易记录'){
				 $("#waterType").val(5);
			 }
			 if(title=='投资记录'){
				 $("#waterType").val(1);
			 }
			 if(title=='收益记录'){
				 $("#waterType").val(2);
			 }
			 if(title=='充值流水'){
				 $("#waterType").val(3);
			 }
			 if(title=='提现流水'){
				 $("#waterType").val(4);
			 }
			 if(title=='其它'){
				 $("#waterType").val(101);
			 }
			fundwater.searchData();
		},
		//查询
		searchData:function(){
			debugger;
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher1"));
			$('#showFundWaterGrids').datagrid({url:BASE_PATH+'custFundWaterController/selectZiJinWaterAllPage.shtml',queryParams:obj});
		},
		//确定
		save:function(){
			var rows = $("#fundWaterGrid").datagrid("getSelections")
			if(rows.length==0){
				$.messager.alert("操作提示", "请选择数据", "error");
				return;
			}
			//获取资金流水类型ID
			var typeIds = "";
			for (var i = 0; i < rows.length; i++) {
				if (i == 0) {
					typeIds += rows[i].typeId;
				} else {
					typeIds += "," + rows[i].typeId;
				}
			}
			//获取资金流水类型名称
			var typeNames = "";
			for (var i = 0; i < rows.length; i++) {
				if (i == 0) {
					typeNames += rows[i].typeName;
				} else {
					typeNames += "," + rows[i].typeName;
				}
			}
			//赋值到父窗口
			$("#waterType").val(typeNames);
			$("#waterTypeId").val(typeIds);
			//关闭当前窗口
			$("#fundWaterDialog").dialog('close');
		}
		
};