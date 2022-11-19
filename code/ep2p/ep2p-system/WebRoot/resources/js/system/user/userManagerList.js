var  userList  = {
		// 操作格式化
		formatOperation:function(value,row,index){
				var func = 'userList.toUpdate("'+row.pid+'")';
				return "<a href='javascript:"+func+"'>修改</a>";
			return "";
		},
		// 新增
		add:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "sysUserController/toAdd.shtml",
				title:"系统用户-新增",
				method:'post',
				width:'400',
				height:'340',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:userList.save
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#addDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//弹窗保存
		save:function(){
			   if(!$("#baseInfo").form('validate')){
					return ;
				}
				//序列化表单 
				var obj = jqueryUtil.serializeObject($("#baseInfo"));
				var objStr = JSON.stringify(obj);
				var accountNo =obj.accountNo;
				var pid =obj.pid;
				$.ajax({
					type: "POST",
			        async:false, 
			        dataType: "json",
			    	url : BASE_PATH+"sysUserController/validateUserAccount.shtml?accountNo="+accountNo+'&pid='+pid,
				    success: function(data){
				    	if(data.count == '2' ){
				    		$.ajax({
								type: "POST",
						        async:false, 
						    	url : BASE_PATH+"sysUserController/create.shtml",
						    	data:{"data":objStr},
								dataType: "json",
							    success: function(data){
							    	if(data.message.status == 200 ){
							    		$.messager.alert('操作提示',"保存成功！",'success');
							    		window.location.href=BASE_PATH+"sysUserController/toList.shtml";
							    	}else{
							    		$.messager.alert('操作提示',"保存失败！",'error');
							    	}
								}
							}); 
				    	}else{
				    		$.messager.alert('操作提示',"系统已存在此账号！",'error');
				    	}
				    }
				}); 
				},
				//修改
				toUpdate:function(pid){
					$("<div id='updateDialog' /> ").dialog({
						href:BASE_PATH+"sysUserController/toAdd.shtml?pid="+pid,
						title:"用户内容-修改",
						method:'post',
						width:'400',
						height:'340',
						modal: true,
						buttons:[{
							text:'保存',
							iconCls:'icon-save',
							handler:userList.save
						},{
							text:'关闭',
							iconCls:'icon-cancel',
							handler:function(){
								$("#updateDialog").dialog('close');
							}
						}],
						onClose : function() {
								$(this).dialog('destroy');
							}
					});
				},
				
		// 删除
		batchRemove:function(){
			var rows = $('#grid').datagrid('getSelections');
		 	if ( rows.length == 0 ) {
		 		$.messager.alert("操作提示","请选择数据","error");
				return;
			}
		 	// 获取选中pid
		 	var pId = "";
			for(var i=0;i<rows.length;i++){
		  		if(i==0){
		  			pId+=rows[i].pid;
		  		}else{
		  			pId+=","+rows[i].pid;
		  		}
		  	}
			$.messager.confirm("操作提示","确定删除选择项?",
				function(r) {
		 			if(r){
						$.post(BASE_PATH+"sysUserController/deleteBtach.shtml",{pid:pId,status:0}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#grid").datagrid('reload');
								}else{
									$.messager.alert('操作提示',"删除失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
		},
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			var objStr = JSON.stringify(obj);
			$('#grid').datagrid('load',{data:objStr});
		},
		/**
		 * 重置密码
		 */
		passwordReset:function(){
			var rows = $('#grid').datagrid('getSelections');
			if ( rows.length == 0 ) {
		 		$.messager.alert("操作提示","请选择数据","error");
				return;
			}
		 	// 获取选中pid
		 	var pId = rows[0].pid;
		 	var pwd = $("#pass_word").val();
//			for(var i=0;i<rows.length;i++){
//		  		if(i==0){
//		  			pId+=rows[i].pid;
//		  		}else{
//		  			pId+=","+rows[i].pid;
//		  		}
//		  	}
			$.messager.confirm("操作提示","后台用户密码重置成功!<br/>密码重置为"+pwd,
					function(r) {
			 			if(r){
							$.post(BASE_PATH+"sysUserController/resetPassword.shtml",{pids:pId,passWord:pwd}, 
								function(ret) {
									if(ret.message.status == 200){
										$.messager.alert('操作提示',"重置成功！",'success');
										$("#grid").datagrid('reload');
									}else{
										$.messager.alert('操作提示',"重置失败！",'error');
									}
								},'json');
			 			}
					}
			 	);
		}
};