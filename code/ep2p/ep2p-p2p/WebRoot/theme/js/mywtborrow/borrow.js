/**
 * 新增借款js 文件
 * 
 * @author Yu.zhang
 * 
 * @date 2015-09-24 
 */
var borrow={
		fileDiv:null,
		// 下一步
		next:function(borrowType,step){
			// 楼盘信息，下一步跳转到个人资料页面
			if(borrowType == 2 && step == 1){
				// 表达必填验证
				if(!validate.validateForm("loanInfoForm")){
					var pid = $("#borrowId").val();
					// 保存楼盘信息
					borrow.saveLoadInfo("#loanInfoForm");
					// 加载個人資料页面
					$("#borrowContent").load(BASE_PATH+"business/borrowController/toBaseInfoPage.shtml",{borrowType:borrowType,pid:pid});
				}
			}else{
				// e抵押要比e首房操作少一步
				if(borrowType == 2 && step > 1){
					step = step-1;
				}
				var pid = $("#borrowId").val();
				if(step == 1){
					// 验证个人基本信息是否为空表单
					if(!validate.validateForm("basicDataForm")){
						// 身份证号验证
						var identificationNo = $("#identificationNo").val();
						// 验证身份证号是否合法
						if(borrow.isCardNo(identificationNo)){
							// 根据身份证号获取年龄
							var age = borrow.getAgeByCard(identificationNo);
							$("#age").val(age);
						}else{
							return false;
						}
						
						// 保存借款个人基本信息
						borrow.saveBasicData();
						// 加载下一步页面
						$("#borrowContent").load(BASE_PATH+"business/borrowController/toFileInfoPage.shtml",{borrowType:borrowType,pid:pid});
					}
				}else if(step == 2){
					// 验证材料是否全部上传完
					if(!borrow.validateFileInfo()){
						return false;
					}
					// 加载下一步页面
					$("#borrowContent").load(BASE_PATH+"business/borrowController/toBorrowInfoPage.shtml",{borrowType:borrowType,pid:pid});
				}else if(step == 3){
					if(!validate.validateForm("loanInfoForm")){
						// 完成
						borrow.complete();
					}
				}
			}
		},
		// 上一步
		Last:function(borrowType,step){
			// e抵押要比e首房操作少一步
			if(borrowType == 2 && step > 1){
				step = step-1;
			}
			var pid ="";
			if(step == 1){
				pid = $("#borrowId").val();
				// 保存借款个人基本信息
				borrow.saveBasicData();
				$("#borrowContent").load(BASE_PATH+"business/borrowController/toBorrowHouseInfoPage.shtml",{borrowType:borrowType,pid:pid});
			}else if(step == 2){
				pid = $("#pid").val();
				// 加载個人資料页面
				$("#borrowContent").load(BASE_PATH+"business/borrowController/toBaseInfoPage.shtml",{borrowType:borrowType,pid:pid});
			}else if(step == 3){
				// 保存借款信息
				borrow.saveLoadInfo("#loanInfoForm");
				pid = $("#pid").val();
				// 加载资质材料页面
				$("#borrowContent").load(BASE_PATH+"business/borrowController/toFileInfoPage.shtml",{borrowType:borrowType,pid:pid});
			}
		},
		// 完成
		complete:function(){
			// 保存借款信息
			borrow.saveLoadInfo("#loanInfoForm");
			
			// 提交至担保初审
			borrow.submitAudit();
		},
		// 新增借款数据初始化
		initAddBorrow:function(){
			//radio 单选效果调用,提交父容器参数
			escfutil.radios_login(".select_js",".list_a_p1");
		    
		    $(".select_js .radio_s").click(function(){
		    	var eq =$(this).index(".select_js .radio_s");
		    	$(".select_c").removeClass("c2980b9").eq(eq).addClass("c2980b9");
		    })
		    
			borrow.loadCity();// 全国省市设置
			borrow.initBasicData();	//基本资料初始化
		},
		// 全国省市加载
		loadCity:function(){
			for(var i=0;i<arrCity.length;i++){
			    $("#nowProvince").append("<option value='"+arrCity[i].name+"'>"+arrCity[i].name+"</option>");
			}
		},
		// 选中现居省事件
		selectNowProvince:function(){
			var nowProvince = $("#nowProvince").val();
			$("#nowCity").empty();
			var cityArr = [];
			// 获取选中省下的城市值
			for(var i=0;i<arrCity.length;i++){
				if(arrCity[i].name == nowProvince){
					cityArr = arrCity[i].sub;
					break;
				}
			}
			// 将城市信息加载到数组中
			for(var i=0;i<cityArr.length;i++){
			    $("#nowCity").append("<option value='"+cityArr[i].name+"'>"+cityArr[i].name+"</option>");
			}
		},
		// 新增借款基本资料初始化
		initBasicData:function(){
			var pid = $("#curId").val();
			if(null!=pid && ""!=pid){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"business/borrowController/getCusTomerByPid.shtml",
			    	data:{pid:pid},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		var customer = data.result;
				    		common.loadData("basicDataForm",customer);
				    		// 设置手机号码为加密
				    		var phoneNoEncry = common.phoneNoEncryption(customer.phoneNo);
				    		$("#phoneNoEncry").val(phoneNoEncry);
				    		
				    		// 设置性别选中
				    		if(customer.sex=='女'){
				    			$(".i_ select_c .c2980b9").removeClass("c2980b9");
				    			$(".radio_s[val='1']").html("");
//								$(a +" .radio_s").attr("val","0");
								$(".radio_s[val='2']").addClass("c2980b9");
								$(".radio_s[val='2']").html('<img src="'+ basePath + 'theme/default/images/radio_x.png" class="block" />');
				    		}
				    		
				    		var borrowInfo = borrow.getBorrowInfo($("#borrowId").val());
				    		
							// 将目前所在城市添加到基本信息中
				    		if(null != borrowInfo.province){
								$("#basicDataForm #nowProvince").val(borrowInfo.province);
								borrow.selectNowProvince();
					    		$("#basicDataForm #nowCity").val(borrowInfo.city);
				    		}
				    		
				    		// 设置
//				    		$("#basicDataForm #customerName").html('<a href="#" onclick="javascript:0;">'+(data.result.customerName==null?data.result.phoneNo:data.result.customerName)+'</a>');
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
			var pid = $("#pid").val();
			if(null!=pid && ""!=pid){
				var fileList = borrow.getFileInfo(pid,borFileRelType);
				var imageStr = "";
				$("#fileDiv"+borFileRelType+"_").html("");
	    		for(var i = 0 ;i < fileList.length ; i++){
	    			var url = BASE_PATH+'business/borrowController/download.shtml?path='+fileList[i].fileUrl+'&fileName=\''+fileList[i].fileName+'.'+fileList[i].fileType+'\'';
	    			//pdf
	    			var url = BASE_PATH+'business/borrowController/download.shtml?path='+fileList[i].fileUrl+'&fileName=\''+fileList[i].fileName+'.'+fileList[i].fileType+'\'';
	    			if(fileList[i].fileType == 'pdf' || fileList[i].fileType == 'PDF'){
	    				imageStr+='<li class="newImage"><div class="pdfImg"></div><a class="downloadImg" href="'+url+'" title="下载">下载</a>';
	    				imageStr+='<a class="deleteImgBtn" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')" title="删除">删除</a></li>';
	    			}else{
	    				imageStr+='<i class="i_ bo_zizhi_list_img pos-r"><img src="'+BASE_PATH+fileList[i].fileUrl+'" /><i class="i_ bo_zizhi_list_img_sc pos-a"><a href="#" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')"><img src="'+BASE_PATH+'theme/default/images/bo_7.png" /></a></i></i>';
//	    				imageStr+='<li class="newImage"><img height="150" src="'+BASE_PATH+fileList[i].fileUrl+'"/> <a class="downloadImg" href="'+url+'" title="下载">下载</a>';
//	    				imageStr+='<a class="deleteImgBtn" href="#" onclick="borrow.deleteFile(\''+fileList[i].pid+'\',\''+borFileRelType+'\')" title="删除">删除</a></li>';
	    			}
	    		}
	    		imageStr+='<i class="i_ bo_zizhi_list_tj"  onclick="borrow.openFileUploadDiv('+borFileRelType+')"></i>';
	    		$("#fileDiv"+borFileRelType+"_").html(imageStr);
			}
		},
		// 获取文件信息
		getFileInfo:function(pid,borFileRelType){
			var result = {};
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"business/borrowController/getBorrowFileRelByType.shtml",
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
				common.loadData("loanInfoForm",borrowInfo);
				
				// 样式控制 start
				if(null!=borrowInfo.homeDesc && borrowInfo.homeDesc!=''){
					$("#homeDesc").removeClass("cd5d5d5").addClass("colorDarkBlue");
				}
				
				if(null!=borrowInfo.borrowUse && borrowInfo.borrowUse!=''){
					$("#borrowUse").removeClass("cd5d5d5").addClass("colorDarkBlue");
				}
				
				if(null!=borrowInfo.payment && borrowInfo.payment!=''){
					$("#payment").removeClass("cd5d5d5").addClass("colorDarkBlue");
				}
				
				if(null!=borrowInfo.other && borrowInfo.other!=''){
					$("#other").removeClass("cd5d5d5").addClass("colorDarkBlue");
				}
				// 样式控制 end
			}
			escfutil.se(".bo_f_m");
			escfutil.se(".bo_y_m"); 
			escfutil.se(".bo_h_m"); 
		},
		// 获取借款表信息
		getBorrowInfo:function(pid){
			var result = {};
			// 借款信息初始化
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"business/borrowController//getByPid.shtml",
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
			var obj = jqueryUtil.serializeObject($("#basicDataForm"));
			var sex = $(".c2980b9").prev().attr("val");
			obj.sex = sex;
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"business/borrowController/saveCusTomer.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		borrow.saveNowCity();
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			}); 
		},
		// 目前所在城市保存
		saveNowCity:function(){
			var pid = $("#borrowId").val();
			var nowProvince = $("#nowProvince").val();
			var nowCity = $("#nowCity").val();
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"business/borrowController/save.shtml",
		    	data:{pid:pid,province:nowProvince,city:nowCity},
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
			$("#borrowFile").val("");
			$("#borrowFile").click();
//			borrow.fileDiv = layer.open({
//			    type: 1,
//			    offset: ['30%', '35%'],
//			    area: ['500px', '30%'],
//			    content: $('#uploadFile')
//			});
		},
		// 文件上传
		uploadFile:function(){
			$("#fileUploadForm").ajaxSubmit({
                type:'post',
                dataType:'json',
                url:BASE_PATH+'business/borrowController/uploadFile.shtml',
                success:function(data){
                	if(data.message== 200){
                		layer.close(borrow.fileDiv);
                		layer.alert("上传成功！",{icon: 1,offset: ['30%', '35%'],area: ['100px', '20%']});
                		borrow.loadImage($("#fileUploadForm #borFileRelType").val());
                	}else{
                		
                	}
                }
            });
		},
		// 文件删除
		deleteFile:function(fileRefPid,borFileRelType){
			layer.confirm('您确定要删除此文件吗？', {icon: 3, title:'提示',offset: ['30%', '40%'],area: ['100px', '20%']}, function(index){
				layer.close(index);
				$.ajax({
					type: "POST",
			        async:false, 
			    	url : BASE_PATH+"business/borrowController/deleteFile.shtml",
			    	data:{pid:fileRefPid},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status == 200 ){
				    		layer.alert("删除成功！",{icon: 1,offset: ['30%', '35%'],area: ['100px', '20%']});
				    		borrow.loadImage(borFileRelType);
				    	}else{
				    		layer.alert("删除失败,请联系管理员！",{icon: 5});
				    	}
					}
				}); 
			});
		},
		// 借款信息保存
		saveLoadInfo:function(formId){
			var obj = jqueryUtil.serializeObject($(formId));
			
			if(null!=obj.borrowMoney && undefined!=obj.borrowMoney && ''!= obj.borrowMoney ){
				obj.surplusMoney=obj.borrowMoney;
			}
			
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"business/borrowController/save.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    	}else{
			    		layer.alert("借款信息保存失败!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
			    	}
			    	
				}
			}); 
		},
		// 提交担保初审
		submitAudit:function(){
			borrow.fileDiv = layer.confirm('您确定完成吗？', {icon: 3, title:'提示',offset: ['30%', '40%'],area: ['100px', '20%']}, function(index){
			  	var pid = $("#loanInfoForm #pid").val();	// 借款ID
			  	var approveId = $("#loanInfoForm #approveId").val();	// 审批ID
			  	var approveStatus = $("#loanInfoForm #approveStatus").val();	// 审批ID
			  	if(null == approveStatus || '' == approveStatus ){
			  		approveStatus = 1 ;
			  	}
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"business/borrowController/submitAudit.shtml",
			    	data:{pid:pid,approveId:approveId,approveStatus:approveStatus,approveNode:'0'},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		layer.close(borrow.fileDiv);
				    		// 提交审核成功后，跳转到完成页面
							$("#borrowContent").load(BASE_PATH+"business/borrowController/toBorrowCompletePage.shtml");
				    	}else{
				    		layer.alert("提交担保初审失败!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
				    	}
					}
				});
	        });
		},
		validateFileInfo:function(){
			// 文件是否上传
			var fileTypeLength = 7;
			var borrowTypw = $("#borrowType").val();
			if(borrowTypw == 1 ){
				fileTypeLength = 6;
			}
			var borrowId = $("#pid").val();	// 借款ID 
			for(var i = 1 ;i < fileTypeLength ;i ++){
				var fileList = borrow.getFileInfo(borrowId,i);
				// 获取文件信息集合长度为0,文件未上传
				if( fileList.length == 0){
					if(i == 1){
						layer.alert("请上传身份证明+婚姻登记证明!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
					}else if(i == 2){
						layer.alert("请上传个人征信报告!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
					}else if(i == 3){
						layer.alert("请上传收入证明!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
					}else if(i == 4){
						layer.alert("请上传近半年银行流水!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
					}else if(i == 5){
						layer.alert("请上传社保查询单!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
					}else if(i == 6){
						layer.alert("请上传房产证材料!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
					}
					return false;
				}
			}
			return true;
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
		getData:function(url){
			var obj = {};
			// 获取楼盘省信息
			$.ajax({
				type: "POST",
		    	url : url,
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		obj = data.data;
			    	}else{
			    		layer.alert(" 初始化楼盘信息失败!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
			    	}
				}
			});
			return obj;
		},
		// 初始化楼盘信息
		initHouse:function(){
			// 获取楼盘省信息
			var arr = borrow.getData(BASE_PATH+"business/borrowController/selectDistinctProvince.shtml");
			$("#homesProvince").append("<option value=''>--请选择--</option>");
			for(var i=0;i<arr.length;i++){
			    $("#homesProvince").append("<option value='"+arr[i].homesProvince+"'>"+arr[i].homesProvince+"</option>");
			}
			// 查询借款信息
			var homeId = $("#loanInfoForm #homePid").val();
			if(null!=homeId && ''!=homeId){
				// 是否存在楼盘信息，存在则加载
				var houseInfo = borrow.getData(BASE_PATH+"business/borrowController/getHouseByPid.shtml?pid="+homeId);
				$("#homesProvince").val(houseInfo.homesProvince);
				borrow.selectProvince();
				$("#homesCity").val(houseInfo.homesCity);
				borrow.selectCity();
				$("#homesArea").val(houseInfo.homesArea);
				borrow.selectArea();
				$("#homesName").val(houseInfo.homesName);
				borrow.selectHousesName();
				$("#homeId").val(houseInfo.pid);
			}
		},
		// 选中楼盘省信息事件
		selectProvince:function(){
			// 楼盘 省 信息变动,清空 市,区,楼盘名称,房型
			$("#homesCity").empty();
			$("#homesArea").empty();
			$("#homesName").empty();
			$("#homeId").empty();
			
			var province = $('#homesProvince').val();
			var arr = borrow.getData(BASE_PATH+"business/borrowController/selectDistinctCityByProvince.shtml?homesProvince="+province);
			$("#homesCity").append("<option value=''>--请选择--</option>");
			for(var i=0;i<arr.length;i++){
			    $("#homesCity").append("<option value='"+arr[i].homesCity+"'>"+arr[i].homesCity+"</option>");
			}
		},
		// 选中楼盘市信息事件
		selectCity:function(){
			// 楼盘 市 信息变动,清空 区,楼盘名称,房型
			$("#homesArea").empty();
			$("#homesName").empty();
			$("#homeId").empty();
			
			var province = $('#homesProvince').val();
			var city = $('#homesCity').val();
			var arr = borrow.getData(BASE_PATH+"business/borrowController/selectDistinctAreaByCity.shtml?homesProvince="+province+"&homesCity="+city);
			$("#homesArea").append("<option value=''>--请选择--</option>");
			for(var i=0;i<arr.length;i++){
			    $("#homesArea").append("<option value='"+arr[i].homesArea+"'>"+arr[i].homesArea+"</option>");
			}
		},
		// 选中楼盘区信息事件
		selectArea:function(){
			// 楼盘 区 信息变动,清空 ,楼盘名称,房型
			$("#homesName").empty();
			$("#homeId").empty();
			
			var province = $('#homesProvince').val();
			var city = $('#homesCity').val();
			var area = $('#homesArea').val();
			var arr = borrow.getData(BASE_PATH+"business/borrowController/selectDistinctHomesNameByAddress.shtml?homesProvince="+province+"&homesCity="+city+"&homesArea="+area);
			$("#homesName").append("<option value=''>--请选择--</option>");
			for(var i=0;i<arr.length;i++){
			    $("#homesName").append("<option value='"+arr[i].homesName+"'>"+arr[i].homesName+"</option>");
			}
		},
		// 选中楼盘名称信息事件
		selectHousesName:function(){
			// 楼盘 楼盘名称 信息变动,清空 房型
			$("#homeId").empty();
			
			var province = $('#homesProvince').val();
			var city = $('#homesCity').val();
			var area = $('#homesArea').val();
			var homesName = $('#homesName').val();
			var arr = borrow.getData(BASE_PATH+"business/borrowController/selectDistinctHomesTypeByHomesName.shtml?homesProvince="+province+"&homesCity="+city+"&homesArea="+area+"&homesName="+homesName);
			$("#homeId").append("<option value=''>--请选择--</option>");
			for(var i=0;i<arr.length;i++){
			    $("#homeId").append("<option value='"+arr[i].pid+"'>"+arr[i].homesType+"</option>");
			}
		},
		// 验证身份证号是否何方
		isCardNo :function (card){  
		   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
		   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		   if(reg.test(card) === false){  
			   layer.alert("身份证号输入不合法,请检查!",{icon: 5,offset: ['30%', '35%'],area: ['100px', '20%']});
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