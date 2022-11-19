/**
 * 自动投标主页面
 */
/*自动投标参数状态*/
var automaticParam = {
		pid:'',
		amount:0,
		balanceratio:0,
		minborrowmonth:0,
		maxborrowmonth:0,
		minborrowrate:0,
		maxborrowrate:0,
		borrowType:'',
		autostatus:0,
		rstatus:0  //0表示没有错误 其他表示有错误
};

var automaticbidList = {
		/* 初始函数 */
		initfunc:function(){
			$(".zi_tian").click(function(){
				var itemlist = $("tbody#auto_list").attr("listsize");
				if(itemlist >= 3){
					layer.msg("自动投标列表已经达到上限数量", {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
				}else{
					$(".zi_").addClass("none").eq(1).removeClass("none");
				}
		    })
		    //radio 单选效果调用,提交父容器参数
		    escfutil.radios_(".zi_sz_list_a1",".list_a_p1");
		    escfutil.radios_(".zi_sz_list_b1",".list_a_p2");
		    escfutil.radios_(".zi_sz_list_c1",".list_a_p3");
		    escfutil.radios_(".zi_sz_list_d1",".list_a_p4");
		     //勾选 效果调用,提交父容器参数 和全部勾选按钮参数
		    escfutil.gougou(".list_a_p5");
		    
		    //设置显示效果
		    automaticbidList.radios_n(".zi_sz_list_a1 .radio_s",".yu_b",".yu_g");
		    automaticbidList.radios_n(".zi_sz_list_b1 .radio_s",".yue_f");
		    automaticbidList.radios_n(".zi_sz_list_c1 .radio_s",".shou_f");
		    $("title").html("自动投标- e生财富 yscf.com");
		},
		
		/* 单选按钮 */
		radios_n:function (a,b,c){
	    	$(a).click(function(){
	    		if ($(this).index(a)==1) {
	    			$(a).eq(1).siblings().show();
	    			$(c).eq(0).siblings().hide();
	    		}else{
	    			$(c).eq(0).siblings().show();
	    			$(a).eq(1).siblings().hide();
	    		    $(b).show();
	    		}
	    	})
	    },
	    
	    /*点击修改修改功能*/
	    updateAutoBidding:function(id){
	    	$("#show_auto_bidding").load(basePath + "userinfo/centerController/toUpdateAutomaticBidPage.shtml",{pid:id},function(){
	    	});
	    },
	    
	    /*添加自动投标功能*/
	    addAutoBidding:function(abstatus){
	    	var addparam = {};
	    	addparam = automaticbidList.getParamInfo("#auto_bidding_add_form");
	    	//必须校验通过才进行保存数据
	    	if(addparam.rstatus == 0){
	    		addparam.autostatus = abstatus;
		    	$.post(basePath + "userinfo/centerController/addAutoBiddingInfo.shtml",addparam,function(data){
		    		if(data.code == 200){
		    			if(data.message == 0){
		    				layer.msg(USERCENTER_MSG.ABADD_SUCCESS, {icon:1,time:1000,offset:(document.body.clientHeight/2 - 50) + "px"},function(){
		    					window.location.href = basePath + "userinfo/centerController/toAutomaticBidPage.shtml";
		    				});
		    			}
		    		}else{
		    			if(data.message == 1){
		    				layer.msg(USERCENTER_MSG.ABCOMMON_OPTIONS_ERROR, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
		    			}else if(data.message == 2){
		    				layer.msg(USERCENTER_MSG.ABCOMMON_OUTQUENE_FAIL, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
		    			}else{
		    				layer.msg(USERCENTER_MSG.ABPAY_FAIL, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
		    			}
		    			
		    		}
		    	},"json");
	    	}else{
	    		//提示错误信息
	    		var ttmsg = "";
	    		switch (addparam.rstatus) {
				case 1:
					ttmsg = "投资金额不能为空或最少不得少于50元";
					break;
				case 2:
					ttmsg = "投资金额比例不能为空";
					break;
				case 3:
					ttmsg = "投资期限设置有误";
					break;
				case 4:
					ttmsg = "收益率设置有误";
					break;
				case 5:
					ttmsg = "选择的类型不能为空";
					break;
				default:
					break;
				}
	    		layer.msg(ttmsg, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
	    	}
	    },
	    
	    /*修改自动投标信息*/
	    editAutoBidding:function(stype){
	    	var upparam = {};
			upparam = automaticbidList.getParamInfo("#auto_bidding_edit_form");
			var vpid = $("#auto_bidding_edit_form #pid").val();
			var vsions = $("#auto_bidding_edit_form #version").val();
			if(upparam.rstatus == 0){
				upparam.pid = vpid;
				upparam.version = vsions;
				upparam.autostatus = stype;
				$.post(basePath + "userinfo/centerController/editAutoBiddingInfo.shtml",upparam,function(data){
		    		if(data.code == 200){
		    			if(data.message == 0){
		    				layer.msg(USERCENTER_MSG.ABEDIT_SUCCESS, {icon:1,time:1000,offset:(document.body.clientHeight/2 - 50) + "px"},function(){
		    					window.location.href = basePath + "userinfo/centerController/toAutomaticBidPage.shtml";
		    				});
		    			}
		    		}else{
		    			if(data.message == 1){
		    				layer.msg(USERCENTER_MSG.ABCOMMON_OPTIONS_ERROR, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
		    			}else if(data.message == 2){
		    				layer.msg(USERCENTER_MSG.ABCOMMON_OUTQUENE_FAIL, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
		    			}else{
		    				layer.msg(USERCENTER_MSG.ABPAY_FAIL, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
		    			}
		    		}
		    	},"json");
			}else{
				//提示错误信息
				var ttmsg = "";
	    		switch (upparam.rstatus) {
				case 1:
					ttmsg = "投资金额不能为空或最少不得少于50元";
					break;
				case 2:
					ttmsg = "投资金额比例不能为空";
					break;
				case 3:
					ttmsg = "投资期限设置有误";
					break;
				case 4:
					ttmsg = "收益率设置有误";
					break;
				case 5:
					ttmsg = "选择的类型不能为空";
					break;
				default:
					break;
				}
	    		layer.msg(ttmsg, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
			}
		},
		
		/* 删除自动投标 */
		deleteAutoBidding:function(id){
			if("" != id){
				layer.confirm('确认删除选中数据？', {icon: 3,offset:(document.body.clientHeight/2 - 50) + "px"},function(index){
					layer.close(index);
					$.post(basePath + "userinfo/centerController/deleteAutoBiddingInfo.shtml",{pid:id},function(data){
			    		if(data.code == 200){
			    			if(data.message == 0){
			    				layer.msg(USERCENTER_MSG.ABDELETE_SUCCESS, {icon:1,time:1000,offset:(document.body.clientHeight/2 - 50) + "px"},function(){
			    					location.reload(false);
			    				});
			    			}
			    		}else{
			    			if(data.message == 1){
			    				layer.msg(USERCENTER_MSG.ABCOMMON_OPTIONS_ERROR, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
			    			}else if(data.message == 2){
			    				layer.msg(USERCENTER_MSG.ABCOMMON_NOTFOUNDAB, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
			    			}else{
			    				layer.msg(USERCENTER_MSG.ABPAY_FAIL, {icon: 2,offset:(document.body.clientHeight/2 - 50) + "px"});
			    			}
			    		}
			    	},"json");
				});
				
			}
		},
		
	    /* 获取页面参数属性  */
	    getParamInfo:function(idform){
	    	var param = automaticParam;
	    	param.rstatus = 0;
	    	//获取投资金额类型
	    	var bd_mtype_a = $(idform + " .zi_sz_list_a1 i.yu_g").attr("val");
	    	var bd_mtype_b = $(idform + " .zi_sz_list_a1 i.yu_b").attr("val");
	    	if(bd_mtype_a == 1){
	    		var vamount = $(idform + " #amount").val();
	    		if(vamount >= 50 ){
	    			param.amount = vamount;
	    			param.balanceratio = 0;
	    		}else{
	    			//1. 提示错误信息
	    			param.rstatus = 1;
	    			return param;
	    		}
	    	}
	    	if(bd_mtype_b == 1){
	    		var vbalanceratio = $(idform + " #balanceratio option:selected").val();
	    		if(vbalanceratio > 0){
	    			param.amount = 0;
	    			param.balanceratio = vbalanceratio;
	    		}else{
	    			//2. 提示错误信息
	    			param.rstatus = 2;
	    			return param;
	    		}
	    	}
	    	
	    	//获取投资期限
	    	var bd_qxtype_a = $(idform + " .zi_sz_list_b1 i#qix_a").attr("val");
	    	var bd_qxtype_b = $(idform + " .zi_sz_list_b1 i#qix_b").attr("val");
	    	if(bd_qxtype_a == 1){
	    		param.minborrowmonth = 0;
	    		param.maxborrowmonth = 0;
	    	}
	    	if(bd_qxtype_b == 1){
	    		var vminborrowmonth = $(idform + " #minborrowmonth option:selected").val();
	    		var vmaxborrowmonth = $(idform + " #maxborrowmonth option:selected").val();
	    		if((vminborrowmonth > 0 || vmaxborrowmonth > 0) && (parseInt(vminborrowmonth) <= parseInt(vmaxborrowmonth))){
	    			param.minborrowmonth = vminborrowmonth;
		    		param.maxborrowmonth = vmaxborrowmonth;
	    		}else{
	    			//3. 提示错误
	    			param.rstatus = 3;
	    			return param;
	    		}
	    	}
	    	
	    	//收益率
	    	var bd_sytype_a = $(idform + " .zi_sz_list_c1 i#sy_a").attr("val");
	    	var bd_sytype_b = $(idform + " .zi_sz_list_c1 i#sy_b").attr("val");
	    	if(bd_sytype_a == 1){
	    		param.minborrowrate = 0;
	    		param.maxborrowrate = 0;
	    	}
	    	if(bd_sytype_b == 1){
	    		var vminborrowrate = $(idform + " #minborrowrate option:selected").val();
	    		var vmaxborrowrate = $(idform + " #maxborrowrate option:selected").val();
	    		if((vminborrowrate > 0 || vmaxborrowrate > 0) && (parseInt(vminborrowrate) <= parseInt(vmaxborrowrate))){
	    			param.minborrowrate = vminborrowrate;
		    		param.maxborrowrate = vmaxborrowrate;
	    		}else{
	    			//4. 提示错去
	    			param.rstatus = 4;
	    			return param;
	    		}
	    	}
	    	
	    	//投资类型
	    	var touzi_typea = $("#tz_a").attr("val") == 1 ? "3" : ""; 
	    	var touzi_typeb = $("#tz_b").attr("val") == 1 ? "2" : ""; 
	    	var touzi_typec = $("#tz_c").attr("val") == 1 ? "1" : "";
	    	var tz_type = "";
	    	if(touzi_typea != ""){
	    		tz_type = touzi_typea;
	    	}
	    	if(touzi_typeb != ""){
	    		tz_type = tz_type != "" ? tz_type + "," + touzi_typeb : touzi_typeb;
	    	}
	    	if(touzi_typec != ""){
	    		tz_type = tz_type != "" ? tz_type + "," + touzi_typec : touzi_typec;
	    	}
	    	param.borrowType = tz_type;
	    	if("" == tz_type){
	    		//4. 提示错去
    			param.rstatus = 5;
    			return param;
	    	}
	    		
	    	//返回信息
	    	return param;
	    }
	    
};
    

$(function(){
	automaticbidList.initfunc();
});
