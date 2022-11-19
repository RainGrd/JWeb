/**
 * 新增借款js 文件
 * 
 * @author Yu.zhang
 * 
 * @date 2015-09-24 
 */
var borrow={
		// 用于控制点击流程进度事件 1 只能查看担保初审信息 2 担保初审 贷前审批都可查看
		borrowViewType:'',
		// 查询客户列表
		searchCusTomer:function(){
			jqueryUtil.ajaxSearchPage("#cusTomerGrid","#searcm");
		},
		// 初始化客户列表数据table
		initCusTomerDataGrid:function(){
			$('#cusTomerGrid').datagrid({    
				url:basePath + 'customerController/selectPage.shtml?status=1&isBlacklist=1&isFreeze=1',
				width:'100%',
				fit:true,
				title:'客户列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:borrowCusTomeManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#cusTomerGrid').datagrid('clearChecked');
					$('#cusTomerGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 跳转到新增借款页面
		toAdd:function(borrowType){
			var path = BASE_PATH+"bizBorrowController/toAdd.shtml?borrowType="+borrowType+"&customerId="+$("#customerId").val();
			var title = "新增借款项目"
			if(borrowType == 1){
				title+="-抵押贷";
			}else{
				title+="-首付贷";
			}
        	childLayout_addTab(path,title);
        	$("#addBorrow").dialog("close");
		},
		// 新增借款数据初始化
		initAddBorrow:function(){
			borrow.loadCity();// 全国省市设置
			borrow.initBasicData();	//基本资料初始化
			borrow.initLoadInfo();	//借款信息初始化
			borrow.initQualificationMaterial();	//资质材料初始化
			borrow.addPageReadonyControl(); // 页面只读控制
		},
		// 借款复审查看页面初始化
		initBorrowReviewView:function(){
			borrow.borrowViewType = "1"; // 进度条能点击担保初审
			borrow.initBasicData();	// 基本信息加载
			borrow.loadBorrowReviewLoadInfo();	//借款复审查看页面借款信息 项目信息加载
			borrow.loadImage(7);	//借款复审查看页面用户材料加载
			// 页面只读控制
			borrow.reviewViewPageReadonyControl();
		},
		// 新增借款页面只读控制
		reviewViewPageReadonyControl:function(){
			var view = $("#loanInfoForm #view").val();
			if(view == 'yes'){
				borrow.disabledForm('loanInfoForm');
				borrow.disabledForm('basicDataForm');
				borrow.disabledForm('projectForm');
				borrow.disabledForm('riskControlForm');
				borrow.disabledForm('fileInfoForm');
			}
		},
		// 全国省市加载
		loadCity:function(){
			 $("#nowProvince").combobox("loadData",arrCity); 
		},
		// 选中现居省事件
		selectNowProvince:function(rec){
			$("#nowCity").combobox("clear");
			if(rec != undefined){
				$("#nowCity").combobox("loadData",rec.sub); 
			}
			
		},
		// 借款复审查看页面借款信息加载
		loadBorrowReviewLoadInfo:function(){
			var pid = $("#loanInfoForm #pid").val();
			if(null!=pid && ""!=pid && pid != undefined){
				// 获取借款信息
				var borrowInfo = borrow.getBorrowInfo(pid);
				// 默认选择允许债权转让
				if( null == borrowInfo.isEquitableAssignment || '' == borrowInfo.isEquitableAssignment ){
					borrowInfo.isEquitableAssignment = "1";
//					borrowInfo.startValue = 5;
//					borrowInfo.endValue = 24;
				}
				
				// 默认不允许使用加息劵
				if( null == borrowInfo.isJiaxiTicket || '' == borrowInfo.isJiaxiTicket ){
					borrowInfo.isJiaxiTicket = "2";
				}
				borrowInfo.accrualType = 2 ;
				
				// 借款信息
				$("#loanInfoForm").form('load',borrowInfo);
				// 项目信息
				$("#projectForm").form('load',borrowInfo);
				// 风控信息
				$("#riskControlForm").form('load',borrowInfo);
			}
		},
		// 新增借款页面只读控制
		addPageReadonyControl:function(){
			var view = $("#loanInfo #view").val();
			var isEdit = $("#loanInfo #isEdit").val();
			if(view == 'yes' || isEdit == 'yes'){
				borrow.disabledForm('loanInfoForm');                                               
				borrow.disabledForm('fileInfoForm');
				borrow.disabledForm('basicDataForm');
			}
		},
		// 禁用表单
		disabledForm:function(fromId){
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').attr('disabled','disabled');
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').attr('readOnly','readOnly');
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').addClass('l-btn-disabled');
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' a:not(.downloadImg)').removeAttr('onclick');
			$('#'+fromId+' a font').attr('color','gray');
		},
		// 表单恢复
		recoveryForm:function(fromId){
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').removeAttr('disabled');
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').removeAttr('readOnly');
			$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').removeClass('l-btn-disabled');
			$('#'+fromId+' a font').removeAttr('color');
		},
		// 基本资料表单恢复
		recoveryBasicDataForm:function(fromId){
			borrow.recoveryForm(fromId);
			$('#'+fromId+' .easyui-linkbutton ').bind("click", borrow.saveBasicData);
		},
		// 文件资料表单恢复
		recoveryFileInfoForm:function(fromId){
			borrow.recoveryForm(fromId);
			// 上传资料按钮恢复
			$('#'+fromId+' .addFileBtn').each(function(i){
				$(this).bind("click",function(){
					borrow.openFileUploadDiv((i+1));
				});
		    });
			// 重新加载图片，恢复删除按钮
			var view = $("#loanInfo #view").val("no");
			borrow.initQualificationMaterial();
		},
		// 借款信息资料表单恢复
		recoveryLoanInfoForm:function(fromId){
			borrow.recoveryForm(fromId);
			$('#'+fromId+' .easyui-linkbutton').bind("click",function(){
					borrow.saveLoadInfo('#loanInfoForm');
			});
		},
		// 新增借款基本资料初始化
		initBasicData:function(){
			var pid = $("#curId").val();
			if(null!=pid && ""!=pid){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"customerController/getByPid.shtml",
			    	data:{pid:pid},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		$("#basicDataForm").form('load',data.result);
				    		if(data.result.customerName == null || data.result.customerName == ''){
				    			$("#basicDataForm #customerName").html('<a href="#" onclick="common.viewCusDetail(\''+data.result.pid+'\')">'+data.result.phoneNo+'</a>');
				    		}else{
				    			$("#basicDataForm #customerName").html('<a href="#" onclick="common.viewCusDetail(\''+data.result.pid+'\')">'+data.result.customerName+'</a>');
				    		}
				    	}else{
				    		$.messager.alert("操作提示","基本资料数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		},
		// 新增借款资质材料初始化
		initQualificationMaterial:function(){
			// 进入页面,循环加载所有类型图片
			for(var i = 1;i < 7 ; i++){
				borrow.loadImage(i);
			}
		},
		// 根据类型加载图片
		loadImage:function(borFileRelType){
			var pid = $("#loanInfoForm #pid").val();
			if(null!=pid && ""!=pid){
				var fileList = borrow.getFileInfo(pid,borFileRelType);
				var imageStr = "";
				$("#fileDiv_"+borFileRelType+" .newImage").remove();
				var view = $("#loanInfo #view").val();
	    		for(var i = 0 ;i < fileList.length ; i++){
	    			var url = BASE_PATH+'bizBorrowController/download.shtml?path='+fileList[i].fileUrl+'&fileName=\''+fileList[i].fileName+'.'+fileList[i].fileType+'\'';
	    			//pdf
	    			if(fileList[i].fileType == 'pdf' || fileList[i].fileType == 'PDF'){
	    				imageStr+='<li class="newImage"><div class="pdfImg"></div><a class="downloadImg" href="'+url+'" title="下载">下载</a>';
	    				//imageStr+='<a class="deleteImgBtn" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')" title="删除">删除</a></li>';
	    				// 页面只读控制
	    				if(view != 'yes'){
	    					imageStr+='<a class="deleteImgBtn" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')" title="删除">删除</a></li>';
	    				}else{
	    					imageStr+='</li>';
	    				}
	    			}else{
	    				imageStr+='<li class="newImage"><img height="150" src="'+BASE_PATH+fileList[i].fileUrl+'"/> <a class="downloadImg" href="'+url+'" title="下载">下载</a>';
//	    				imageStr+='<a class="deleteImgBtn" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')" title="删除">删除</a></li>';
	    				// 页面只读控制
	    				if(view != 'yes'){
	    					imageStr+='<a class="deleteImgBtn" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')" title="删除">删除</a></li>';
	    				}else{
	    					imageStr+='</li>';
	    				}
	    			}
	    		}
	    		$("#fileDiv_"+borFileRelType).prepend(imageStr);
			}
		},
		// 获取文件信息
		getFileInfo:function(pid,borFileRelType){
			var result = {};
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"bizBorrowController/getBorrowFileRelByType.shtml",
		    	data:{borFileRelType:borFileRelType,borrowId:pid},
				dataType: "json",
				async:false,
			    success: function(data){
			    	if(data.message.status ==200){
			    		result = data.result;
			    	}else{
			    		$.messager.alert("操作提示","资质材料数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			return result;
		},
		// 新增借款信息初始化
		initLoadInfo:function(){
			var pid = $("#loanInfoForm #pid").val();
			if(null!=pid && ""!=pid && pid != undefined){
				// 获取借款信息
				var borrowInfo = borrow.getBorrowInfo(pid);
				// 加载到form中
				$("#loanInfoForm").form('load',borrowInfo);
				
				// 将目前所在城市添加到基本信息中
				$("#basicDataForm #nowProvince").combobox("select",borrowInfo.province);
	    		$("#basicDataForm #nowCity").combobox("select",borrowInfo.city);
				
				//抵押贷 需要初始化楼盘信息
	    		if(borrowInfo.homeId){
	    			if(borrowInfo.borrowType == 2){
	    				borrow.initHomes(borrowInfo.homeId);
	    			}
	    		}
			}
		},
		// 获取借款表信息
		getBorrowInfo:function(pid){
			var result = {};
			// 借款信息初始化
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"bizBorrowController/getByPid.shtml",
		    	data:{pid:pid},
				dataType: "json",
				async:false,
			    success: function(data){
			    	if(data.message.status ==200){
			    		result = data.result;
			    	}else{
			    		$.messager.alert("操作提示","基本资料数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			return result;
		},
		// 楼盘信息初始化
		initHomes:function(pid){
			// 借款信息初始化
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"bizHousesController/getByPid.shtml",
		    	data:{pid:pid},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		// $("#loanInfoForm").form('load',data.result);
			    		$("#province").combobox("select",data.result.homesProvince);
			    		$("#city").combobox("select",data.result.homesCity);
			    		$("#area").combobox("select",data.result.homesArea);
			    		$("#homesName").combobox("select",data.result.homesName);
			    		$("#homesType").combobox("select",data.result.pid);
			    	}else{
			    		$.messager.alert("操作提示","基本资料楼盘信息数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		},
		// 基本信息保存
		saveBasicData:function(){
			// 验证表单必填项
			if(!$("#basicDataForm").form('validate')){
				return ;
			}
			
			var obj = jqueryUtil.serializeObject($("#basicDataForm"));
			var identificationNo = $("#identificationNo").val();
			
			// 验证身份证信息
			if( null != identificationNo && '' != identificationNo && undefined!=identificationNo ){
				// 验证身份证号是否合法
				if(borrow.isCardNo(identificationNo)){
					// 根据身份证号获取年龄
					var age = borrow.getAgeByCard(identificationNo);
					obj.age = age;
				}else{
					return false;
				}
			}
			
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"customerController/save.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$("#addDialog").dialog('close');
			    		borrow.saveNowCity();
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			}); 
		},
		// 目前所在城市保存
		saveNowCity:function(){
			debugger;
			var pid = $("#loanInfoForm #pid").val();
			var nowProvince = $("#nowProvince").combobox("getValue");
			var nowCity = $("#nowCity").combobox("getValue");
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/save.shtml",
		    	data:{'pid':pid,'province':nowProvince,'city':nowCity},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 打开文件上传DIV
		openFileUploadDiv:function(borFileRelType){
			$("#fileUploadForm #borFileRelType").val(borFileRelType);
			// 清空文件路径
			$("#fileUploadForm #borrowFile").val('');
			$("#fileUploadForm #txt2").val('');
			$("#uploadFile").dialog("open");
		},
		// 文件上传
		uploadFile:function()
		{
			var file = $("#fileUploadForm #borrowFile").val();
			if(null!=file && ""!=file){
				var fileType = file.split(".")[file.split(".").length-1]
				var borFileRelType = $("#fileUploadForm #borFileRelType").val();
				if(borFileRelType != 1 ){
					// 验证上传文件格式
					if("png"!=fileType && "PNG"!=fileType && "jpg"!=fileType && "JPG"!=fileType &&"gif"!=fileType && "GIF"!=fileType &&"pdf"!=fileType && "PDF"!=fileType){
						$.messager.alert('操作提示',"文件格式只支持JPG PNG GIF、PDF，请检查！",'info');
						return false;
					}
				}
			}
			$("#fileUploadForm").form('submit', {
				url:BASE_PATH+'bizBorrowController/uploadFile.shtml',
				onSubmit : function() {return $(this).form('validate');},
				success : function(data) {
					var result = eval("("+data+")");
					if(result.message == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$("#uploadFile").dialog('close');
			    		borrow.loadImage($("#fileUploadForm #borFileRelType").val());
			    	}else{
			    		$.messager.alert('操作提示',result.message,'error');
			    	}
				}
			});
		},
		// 文件删除
		deleteFile:function(fileRefPid,borFileRelType){
			$.messager.confirm("操作提示", "您确定要删除此文件吗？", function (data) {
				if (data) {
					$.ajax({
						type: "POST",
				        async:false, 
				    	url : BASE_PATH+"bizBorrowController/deleteFile.shtml",
				    	data:{pid:fileRefPid},
						dataType: "json",
					    success: function(data){
					    	if(data.message.status == 200 ){
					    		$.messager.alert('操作提示',"删除成功！",'success');
					    		borrow.loadImage(borFileRelType);
					    	}else{
					    		$.messager.alert('操作提示',"删除失败,请联系管理员！",'error');
					    	}
						}
					}); 
			    }
	        });
		},
		// 借款信息保存
		saveLoadInfo:function(formId){
			// 验证表单必填项
			if(!$(formId).form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($(formId));
			
			// 验证是否允许债权转让
//			if(obj.isEquitableAssignment!=''&& null != obj.isEquitableAssignment){
//				if(obj.isEquitableAssignment == 1){
//					if(null == obj.startValue || '' == obj.startValue || null == obj.endValue || ''==obj.endValue ){
//						$.messager.alert('操作提示',"请填写债权转让允许的利率！",'info');
//						return false;
//					}else{
//						obj.startValue = obj.startValue/100;
//						obj.endValue = obj.endValue/100;
//					}
//				}
//			}
			
			if(null!=obj.investRewardScale && undefined!=obj.investRewardScale && ''!= obj.investRewardScale ){
				obj.investRewardScale=obj.investRewardScale/100;
			}
			
			if(null!=obj.manageExpenseRate && undefined!=obj.manageExpenseRate && ''!= obj.manageExpenseRate ){
				obj.manageExpenseRate=obj.manageExpenseRate/100;
			}
			
			if(null!=obj.borrowRate && undefined!=obj.borrowRate && ''!= obj.borrowRate ){
				obj.borrowRate=obj.borrowRate/100;
			}
			
			if(null!=obj.borrowMoney && undefined!=obj.borrowMoney && ''!= obj.borrowMoney ){
				obj.surplusMoney=obj.borrowMoney;
			}
			
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/save.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 提交担保初审
		submitAudit:function(){
			// 验证页面值是否提交
			if(!borrow.validateSubmitAudit()){
				return false;
			}
			
			$.messager.confirm("操作提示", "您确定要此流程到担保初审吗？", function (data) {
			  if (data) {
				  	var pid = $("#loanInfoForm #pid").val();	// 借款ID
				  	var approveId = $("#loanInfoForm #approveId").val();	// 审批ID
				  	var approveStatus = $("#loanInfoForm #approveStatus").val();	// 审批ID
				  	if(null == approveStatus || '' == approveStatus ){
				  		approveStatus = 1 ;
				  	}
					$.ajax({
						type: "POST",
				    	url : BASE_PATH+"bizBorrowApproveController/submitAudit.shtml",
				    	data:{pid:pid,approveId:approveId,approveStatus:approveStatus,approveNode:'0'},
						dataType: "json",
					    success: function(data){
					    	if(data.message.status ==200){
					    		$.messager.alert("操作提示","提交成功!","success",function(){
					    			parent.$('#centerFrame').tabs('close',parent.$('#centerFrame').tabs('getSelected').panel('options').title);
					    		});
					    	}else{
					    		$.messager.alert("操作提示","基本资料数据加载失败,请联系管理员!","error");
					    	}
						}
					});
	            }
	        });
		},
		validateSubmitAudit:function(){
			var returnValu = false;
			// 个人基本资料是否填写完整
			if(!$("#basicDataForm").form('validate')){
				return returnValu;
			}
			
			// 借款信息是否填写完整
			if(!$("#loanInfoForm").form('validate')){
				return returnValu;
			}
			
			
			// 是否保存借款信息
			var borrowId = $("#loanInfoForm #pid").val();
			var borrowInfo = borrow.getBorrowInfo(borrowId);
			// 借款时间为空,则默认未保存借款信息
			if(null==borrowInfo.borrowUse || ""==borrowInfo.borrowUse || borrowInfo.borrowUse.length == 0){
				$.messager.alert("操作提示","请保存借款信息!","info");
				return returnValu;
    		}
			
			// 目前所在城市是否保存
			if(null==borrowInfo.province || ""==borrowInfo.province || borrowInfo.province.length == 0){
				$.messager.alert("操作提示","请保存个人基本资料!","info");
				return returnValu;
    		}
			
			// 文件是否上传
			var fileTypeLength = 7;
			var borrowTypw = $("#borrowType").val();
			if(borrowTypw == 1 ){
				fileTypeLength = 6;
			}
			for(var i = 1 ;i < fileTypeLength ;i ++){
				var fileList = borrow.getFileInfo(borrowId,i);
				// 获取文件信息集合长度为0,文件未上传
				if( fileList.length == 0){
					if(i == 1){
						$.messager.alert("操作提示","请上传身份证明+婚姻登记证明!","info");
					}else if(i == 2){
						$.messager.alert("操作提示","请上传个人征信报告!","info");
					}else if(i == 3){
						$.messager.alert("操作提示","请上传收入证明!","info");
					}else if(i == 4){
						$.messager.alert("操作提示","请上传近半年银行流水!","info");
					}else if(i == 5){
						$.messager.alert("操作提示","请上传社保查询单!","info");
					}else if(i == 6){
						$.messager.alert("操作提示","请上传房产证材料!","info");
					}
					return false;
				}
			}
			
			return true;
		},
		// 选中楼盘省信息事件
		selectProvince:function(data){
			// 楼盘 省 信息变动,清空 市,区,楼盘名称,房型
			$("#city").combobox("clear");
			$("#area").combobox("clear");
			$("#homesName").combobox("clear");
			$("#homesType").combobox("clear");
			
			var province = $('#province').combobox('getValue');
			$('#city').combobox('reload',BASE_PATH+"bizHousesController/selectDistinctCityByProvince.shtml?homesProvince="+province);
		},
		// 选中楼盘市信息事件
		selectCity:function(){
			// 楼盘 市 信息变动,清空 区,楼盘名称,房型
			$("#area").combobox("clear");
			$("#homesName").combobox("clear");
			$("#homesType").combobox("clear");
			
			var province = $('#province').combobox('getText');
			var city = $('#city').combobox('getText');
			
			$("#area").combobox("reload",BASE_PATH+"bizHousesController/selectDistinctAreaByCity.shtml?homesProvince="+province+"&homesCity="+city);
		},
		// 选中楼盘区信息事件
		selectArea:function(){
			// 楼盘 区 信息变动,清空 ,楼盘名称,房型
			$("#homesName").combobox("clear");
			$("#homesType").combobox("clear");
			
			var province = $('#province').combobox('getText');
			var city = $('#city').combobox('getText');
			var area = $('#area').combobox('getText');
			
			$("#homesName").combobox("reload",BASE_PATH+"bizHousesController/selectDistinctHomesNameByAddress.shtml?homesProvince="+province+"&homesCity="+city+"&homesArea="+area);
		},
		// 选中楼盘名称信息事件
		selectHousesName:function(){
			// 楼盘 楼盘名称 信息变动,清空 房型
			$("#homesType").combobox("clear");
			
			var province = $('#province').combobox('getText');
			var city = $('#city').combobox('getText');
			var area = $('#area').combobox('getText');
			var homesName = $('#homesName').combobox('getText');
			$("#homesType").combobox("reload",BASE_PATH+"bizHousesController/selectDistinctHomesTypeByHomesName.shtml?homesProvince="+province+"&homesCity="+city+"&homesArea="+area+"&homesName="+homesName);
		},
		// 借款贷前审核查看资料
		viewBorrowInfo: function(pid){
			if(borrow.borrowViewType == 2){
				var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
				childLayout_addTab(path,"借款贷前审核查看");
			}
		},
		// 借款担保审核查看资料
		viewBorrowVouchInfo:function (pid){
			if(borrow.borrowViewType == 1 || borrow.borrowViewType == 2){
				var path = BASE_PATH+'bizBorrowController/view.shtml?pid='+pid+'&view=yes&isEdit=no';
				childLayout_addTab(path,"借款担保初审查看");
			}
		},
		//查看发布确认信息
		viewBorrowReleaseInfo:function(pid){
			var path = BASE_PATH+'bizBorrowController/releaseConfirmView.shtml?pid='+pid;
			childLayout_addTab(path,"借款发布确认信息查看");
		},
		// 验证身份证号是否何方
		isCardNo :function (card)  
		{  
		   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
		   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		   if(reg.test(card) === false)  
		   {  
			   $.messager.alert("操作提示","身份证输入不合法,请检查!","info");
		       return  false;  
		   }  
		   return true;
		},
		// 根据身份证号获取年龄
		getAgeByCard:function(card){
			//获取出生日期
			card.substring(6, 10) + "-" + card.substring(10, 12) + "-" + card.substring(12, 14);
			var myDate = new Date();
			var month = myDate.getMonth() + 1;
			var day = myDate.getDate();
			var age = myDate.getFullYear() - card.substring(6, 10) - 1;
			if (card.substring(10, 12) < month || card.substring(10, 12) == month && card.substring(12, 14) <= day) {
				age++;
			}
			return age;
		}
}