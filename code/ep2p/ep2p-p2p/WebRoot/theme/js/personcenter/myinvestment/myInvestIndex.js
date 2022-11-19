/**
 * 个人中心-我的投资-首页
 */
var checktype = 1;

//借款静态访问路径
var static_html = {
		borrowDetailPath:basePath +"business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=",
		borrowCodeDtilPath:basePath + "usercenter/borrowDetailController/toReceiptPlanList.shtml?borrowId=",
};
var userinvestment = {
		
		//初始化列表
		initPanel:function(){
			var typev = "1";
			var startTimev = $("#startInvestmentTime1").val();
			var endTimev = $("#endInvestmentTime1").val();
			//参数对象
			var paramv = {
			  type:typev,
			  startInvestmentTime:startTimev,
			  endInvestmentTime:endTimev
		   };
			var finc_URL = basePath + "userinfo/centerController/getUserInvestInfo.shtml";
			//初始化分页行
			userinvestment.initialisePageTag(finc_URL,paramv,typev);
		},
		//点击事件入口
		onclickItems:function(t){
			//取值
			var startTimev = "";
			var endTimev = "";
			//----------------------------------------------
			var typev = $(t).attr("thistype");
			if(typev != checktype){
				//清空数据
				if(typev == 1){
					$(".invest_panel_times").addClass("none");
					$("#invest_panel_times1").removeClass("none");
					//赋值信息
					startTimev = $("#startInvestmentTime1").val();
					endTimev = $("#endInvestmentTime1").val();
					if($("#duein_page").attr("pageclick") == 'true'){
						$("#duein_page").page('destroy');
					}
				}
				if(typev == 2){
					$(".invest_panel_times").addClass("none");	
					$("#invest_panel_times2").removeClass("none");
					//赋值信息
					startTimev = $("#startInvestmentTime2").val();
					endTimev = $("#endInvestmentTime2").val();
					if($("#invite_tenders_page").attr("pageclick") == 'true'){
						$("#invite_tenders_page").page('destroy');
					}
				}
				if(typev == 3){
					$(".invest_panel_times").addClass("none");
					$("#invest_panel_times3").removeClass("none");
					//赋值信息
					startTimev = $("#startInvestmentTime3").val();
					endTimev = $("#endInvestmentTime3").val();
					if($("#transfer_posse_page").attr("pageclick") == 'true'){
						$("#transfer_posse_page").page('destroy');
					}
				}
				if(typev == 4){
					$(".invest_panel_times").addClass("none");
					$("#invest_panel_times4").removeClass("none");
					//赋值信息
					startTimev = $("#startInvestmentTime4").val();
					endTimev = $("#endInvestmentTime4").val();
					if($("#closed_account_page").attr("pageclick") == 'true'){
						$("#closed_account_page").page('destroy');
					}
				}
				checktype = typev;
			}
			
			//参数对象
			var paramv = {
					type:typev,
					startInvestmentTime:startTimev,
					endInvestmentTime:endTimev
			};
			var finc_URL = basePath + "userinfo/centerController/getUserInvestInfo.shtml?rand="+randm;
			userinvestment.initialisePageTag(finc_URL,paramv,typev + "");
		},
		//点击事件入口
		onSerachItems:function(ty){
			//取值
			var startTimev = "";
			var endTimev = "";
			//----------------------------------------------
			var typev = ty
			if(typev == checktype){
				//清空数据
				if(typev == 1){
					//赋值信息
					startTimev = $("#startInvestmentTime1").val();
					endTimev = $("#endInvestmentTime1").val();
					$("#duein_page").page('destroy');
				}
				if(typev == 2){
					//赋值信息
					startTimev = $("#startInvestmentTime2").val();
					endTimev = $("#endInvestmentTime2").val();
					$("#invite_tenders_page").page('destroy');
				}
				if(typev == 3){
					//赋值信息
					startTimev = $("#startInvestmentTime3").val();
					endTimev = $("#endInvestmentTime3").val();
					$("#transfer_posse_page").page('destroy');
				}
				if(typev == 4){
					//赋值信息
					startTimev = $("#startInvestmentTime4").val();
					endTimev = $("#endInvestmentTime4").val();
					$("#closed_account_page").page('destroy');
				}
			}
			//参数对象
			var paramv = {
					type:typev,
					startInvestmentTime:startTimev,
					endInvestmentTime:endTimev
			};
			var finc_URL = basePath + "userinfo/centerController/getUserInvestInfo.shtml?rand="+randm;
			userinvestment.initialisePageTag(finc_URL,paramv,typev + "");
		},
		//分页插件
		initialisePageTag:function(url,params,types){
			switch (types) {
				//  待收中
				case "1":
					$("#duein_page").page({
						pageSize : 10,    // 测试设置为1
						showInfo : false,
						showJump : false,
						showPageSizes : false,
						loadFirstPage : true,
						remote : {
							url : url,
							params : params,
							success : function(result, pageIndex) {
								//var data = eval('(' + data + ')');
								if(result.code == "200" ){
						    			userinvestment.dueinList(result.list);
						    	}else{
						    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
						    	}
							},
							pageIndexName : 'page', //请求参数，当前页数，索引从0开始
							pageSizeName : 'pageRows'//请求参数，每页数量
						}
					});
					$("#duein_page").attr("pageclick","true");
					break;
				case "2":
					$("#invite_tenders_page").page({
						pageSize : 10,    // 测试设置为1
						showInfo : false,
						showJump : false,
						showPageSizes : false,
						loadFirstPage : true,
						remote : {
							url : url,
							params : params,
							success : function(result, pageIndex) {
								//var data = eval('(' + data + ')');
								if(result.code == "200" ){
						    			userinvestment.inviteTendersList(result.list);
						    	}else{
						    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
						    	}
							},
							pageIndexName : 'page', //请求参数，当前页数，索引从0开始
							pageSizeName : 'pageRows'//请求参数，每页数量
						}
					});
					$("#invite_tenders_page").attr("pageclick","true");
					break;
				case "3":
					$("#transfer_posse_page").page({
						pageSize : 10,    // 测试设置为1
						showInfo : false,
						showJump : false,
						showPageSizes : false,
						loadFirstPage : true,
						remote : {
							url : url,
							params : params,
							success : function(result, pageIndex) {
								//var data = eval('(' + data + ')');
								if(result.code == "200" ){
						    			userinvestment.transferPosseList(result.list);
						    	}else{
						    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
						    	}
							},
							pageIndexName : 'page', //请求参数，当前页数，索引从0开始
							pageSizeName : 'pageRows'//请求参数，每页数量
						}
					});
					$("#transfer_posse_page").attr("pageclick","true");
					break;
				case "4":
					$("#closed_account_page").page({
						pageSize : 10,    // 测试设置为1
						showInfo : false,
						showJump : false,
						showPageSizes : false,
						loadFirstPage : true,
						remote : {
							url : url,
							params : params,
							success : function(result, pageIndex) {
								//var data = eval('(' + data + ')');
								if(result.code == "200" ){
						    			userinvestment.closedAccountList(result.list);
						    	}else{
						    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
						    	}
							},
							pageIndexName : 'page', //请求参数，当前页数，索引从0开始
							pageSizeName : 'pageRows'//请求参数，每页数量
						}
					});
					$("#closed_account_page").attr("pageclick","true");
					break;
				default:
					console.warn("没有找到对应类型的查询类型");
					break;
			}
		},
		//待收中的显示列表
		dueinList:function(rs){
			if(rs == null || rs.length == 0){
				$("#duein_list_tr").html("");
				return;
			}
			//拼接html
			var vhtml = "";
			for(var i=0;i<rs.length;i++){
				//访问路径
				var borname_href = userinvestment.borURLProNameFormat(rs[i].pid,rs[i].borrowType);
				var borcode_href = userinvestment.borURLCodeFormat(rs[i].pid,rs[i].transfId,rs[i].borrowType);
				var transfer_html = userinvestment.borTranStatusFmat(rs[i].transfStatus,rs[i].pid,rs[i].transfId);
				var deltail_href = static_html.borrowCodeDtilPath + rs[i].pid;
				
				
				var hetong = " <a target=_blank' href='"+basePath+"mybids/contractController/borrow/contract.shtml?borrowId="+rs[i].pid+"'><img class='btn_img fl' src='"+basePath+"theme/default/images/operate.png' alt='查看合同'></a>";
				if(rs[i].transfId!=null){
					hetong = " <a target=_blank' href='"+basePath+"mybids/contractController/srcborrow/contract.shtml?borrowId="+rs[i].pid+"&transferId="+rs[i].transfId+"'><img class='btn_img fl' src='"+basePath+"theme/default/images/operate.png' alt='查看合同'></a>";
					hetong += " <a target=_blank' href='"+basePath+"mybids/contractController/transfer/contract.shtml?transferId="+rs[i].transfId+"'><img class='btn_img fl' src='"+basePath+"theme/default/images/operate.png' alt='查看合同'></a>";
				}
				
				
				//展示内容
				var rowv = "" +  
						"<tr> " +
						"	<td class='pl30'> " +
						"		<p class='cchu'><a href=\""+ borname_href +"\">" + rs[i].borrowName + "</a></p>" + rs[i].investmentTime + " " +
						"	</td> "+
						"	<td class=''><a href=\"" + borcode_href	+ "\">" + userinvestment.jugdeNullFmat(rs[i].borrowCode) + "</a></td> " +
						"	<td>￥" + escfutil.formatCurrency(rs[i].investmentAmount+"") + "</td> " +
						"	<td>￥" + escfutil.formatCurrency(rs[i].interestReceived+"") + "</td> " +
						"	<td>￥" + escfutil.formatCurrency(rs[i].collectInterest+"") + "</td> " +
						"	<td>￥" + escfutil.formatCurrency(rs[i].investmentReward+"") + "</td> " +
						"	<td>￥" + escfutil.formatCurrency(rs[i].hike+"") + "</td> " +
						"	<td>待收中</td> " +
						"	<td>" + transfer_html + "</td> " +
						"	<td> " +
						"		<a class='btn_samllss' href='" + deltail_href + "'>明细</a> " +
						hetong+
						"	</td> " +
						"</tr> " ;
				vhtml = vhtml + rowv;
			}
			//添加信息
			$("#duein_list_tr").html(vhtml);
		},
		
		//招标中
		inviteTendersList:function(rs){
			if(rs == null || rs.length == 0){
				$("#invite_tenders_list_rt").html("");
				return;
			}
			//拼接html
			var vhtml = "";
			for(var i=0;i<rs.length;i++){
				//访问路径
				var borname_href = userinvestment.borURLProNameFormat(rs[i].pid,rs[i].borrowType);
				var borcode_href = userinvestment.borURLCodeFormat(rs[i].pid,"",rs[i].borrowType);
				//展示内容
				var rowv = "" + 
						"<tr> " + 
						"	<td class='pl30'> " + 
						"		<p class='cchu'><a href=\""+ borname_href +"\">" + rs[i].borrowName + "</a></p>"+ rs[i].investmentTime +"</td> " + 
						"	<td class=''><a href=\"" + borcode_href	+ "\">" + userinvestment.jugdeNullFmat(rs[i].borrowCode) + "</a></td> " + 
						"	<td>￥"+ escfutil.formatCurrency(rs[i].investmentAmount+"") +"</td> " + 
						"	<td>￥"+ escfutil.formatCurrency(rs[i].interest+"") +"</td> " + 
						"	<td>￥"+ escfutil.formatCurrency(rs[i].investmentReward+"") +"</td> " + 
						"	<td>￥"+ escfutil.formatCurrency(rs[i].hike+"") +"</td> " + 
						"	<td>招标中</td> " + 
						"	<td><img class='btn_img fl' src='" + basePath + "theme/default/images/operate.png' alt='查看合同'></td> " + 
						"</tr>";
				vhtml = vhtml + rowv;
			}
			//添加信息
			$("#invite_tenders_list_rt").html(vhtml);
		},
		
		//转让中列表
		transferPosseList:function(rs){
			if(rs == null || rs.length == 0){
				$("#transfer_posse_list").html("");
				return;
			}
			//拼接html
			var vhtml = "";
			for(var i=0;i<rs.length;i++){
				//访问路径
				var borname_href = userinvestment.borURLProNameFormat(rs[i].pid,rs[i].borrowType);
				var borcode_href = userinvestment.borURLCodeFormat(rs[i].pid,"",rs[i].borrowType);
				var transHtml = "";
				if(rs[i].trandStatus == '3'){
					transHtml = "转让中";
				}
				if(rs[i].trandStatus == '4'){
					transHtml = "已转让";
				}
				//展示内容
				var rowv = "" + 
					"<tr>" +
					"	<td class='pl30'>" +
					"		<p class='cchu'><a href=\""+ borname_href +"\">" + rs[i].borrowName + "</a></p>" +	rs[i].investmentTime + "</td>" +
					"	<td class=''><a href=\"" + borcode_href	+ "\">" + userinvestment.jugdeNullFmat(rs[i].borrowCode) + "</a></td>" +
					"	<td>"+ rs[i].timeRemaining +"月</td>" + 
					"	<td>￥"+ escfutil.formatCurrency(rs[i].projectValue+"") +"</td>" + 
					"	<td>￥"+ escfutil.formatCurrency(rs[i].successAmount+"") +"</td>" + 
					"	<td>"+ transHtml + "</td>" + 
					"	<td><a target=_blank' href='mybids/contractController/transfer/contract.shtml?transferId="+rs[i].pid+"'><img class='btn_img fl' src='"+ basePath +"theme/default/images/operate.png' alt='查看债权合同'></a></td>" + 
					"</tr>";
				vhtml = vhtml + rowv;
			}
			//添加信息
			$("#transfer_posse_list").html(vhtml);
		},
		
		//已结清列表
		closedAccountList:function(rs){
			if(rs == null || rs.length == 0){
				$("#closed_account_list").html("");
				return;
			}
			//拼接html
			var vhtml = "";
			for(var i=0;i<rs.length;i++){
				//访问路径
				var borname_href = userinvestment.borURLProNameFormat(rs[i].pid,rs[i].borrowType);
				var borcode_href = userinvestment.borURLCodeFormat(rs[i].pid,"",rs[i].borrowType);
				var overd_a = basePath + "usercenter/borrowDetailController/projectInfo.shtml?borrowId=";
				var overd_b = "&transferId=";
				var overdtail_href = overd_a + rs[i].pid + overd_b + (rs[i].transferId==null?"":rs[i].transferId);
				
				var hetong = " <a target=_blank' href='"+basePath+"mybids/contractController/borrow/contract.shtml?borrowId="+rs[i].pid+"'><img class='btn_img fl' src='"+basePath+"theme/default/images/operate.png' alt='查看合同'></a>";
				if(rs[i].transferId!=null){
					hetong = " <a target=_blank' href='"+basePath+"mybids/contractController/srcborrow/contract.shtml?borrowId="+rs[i].pid+"&transferId="+rs[i].transferId+"'><img class='btn_img fl' src='"+basePath+"theme/default/images/operate.png' alt='查看合同'></a>";
					hetong += " <a target=_blank' href='"+basePath+"mybids/contractController/transfer/contract.shtml?transferId="+rs[i].transferId+"'><img class='btn_img fl' src='"+basePath+"theme/default/images/operate.png' alt='查看合同'></a>";
				}
				//展示内容
				var rowv = "" + 
						"<tr>" + 
						"	<td class='pl30'>" + " <p class='cchu'><a href=\""+ borname_href +"\">" + rs[i].borrowName + "</a></p>" + rs[i].investmentTime + "</td>"+
						"	<td class=''><a href=\"" + borcode_href	+ "\">" + userinvestment.jugdeNullFmat(rs[i].borrowCode) + "</a></td>"+
						"	<td>￥"+ escfutil.formatCurrency(rs[i].investmentAmount+"") +"</td>"+
						"	<td>"+ rs[i].borDeadline +"</td>"+
						"	<td>"+ rs[i].borrowApr * 100 +"%</td>"+
						"	<td>￥"+ escfutil.formatCurrency(rs[i].interest+"") +"</td>"+
						"	<td>￥"+ escfutil.formatCurrency(rs[i].investmentReward+"") +"</td>"+
						"	<td>￥"+ escfutil.formatCurrency(rs[i].hike+"") +"</td>"+
						"	<td>已结清</td>"+
						"	<td><a class='btn_samllss fl' href='" + overdtail_href + "'>明细</a>"+
						hetong
						+"</td>"+
						"</tr>";
				vhtml = vhtml + rowv;
			}
			//添加信息
			$("#closed_account_list").html(vhtml);
		},
		//判断状态
		jugdeStatus:function(status){
			if(status == 2 ){
				return "招标中";
			}else if(status == 4){
				return "待收中";
			}else if(status == 6){
				return "待收中";
			}else if(status == 5){
				return "待收中";
			}else if(status == 7){
				return "已结清";
			}else if(status == 8){
				return "已结清";
			}else{
				return "";
			}
			
		},
		borURLProNameFormat:function(pid,borrowType){
			var eshouf_href = basePath + "business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=";
			var ejihua_href = basePath + "business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=";
			var xins_href = basePath + "mybids/borrowNewStandardController/index/toBorrowStandardInfo.shtml?pid=";
			var tiyan_href = basePath + "mybids/experienceBorrowController/index/toBorrowStandardInfo.shtml?pid=";
			var re_href = "";
			//判断是不是转让
			if(undefined != borrowType && "" != borrowType){
				//e首房，e抵押
				if(borrowType == '1' || borrowType == '2'){
					re_href = eshouf_href + pid;
				}
				//e计划
				if(borrowType == '3'){
					re_href = ejihua_href + pid;
				}
				//新手
				if(borrowType == '4'){
					re_href = xins_href + pid;
				}
				//体验标
				if(borrowType == '5'){
					re_href = tiyan_href + pid;
				}
			}
			//返回
			return re_href;
		},
		//借款编码URL格式化
		borURLCodeFormat:function(pid,transfId,borrowType){
			var eshouf_href = basePath + "business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=";
			var ejihua_href = basePath + "business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=";
			var xins_href = basePath + "mybids/borrowNewStandardController/index/toBorrowStandardInfo.shtml?pid=";
			var tiyan_href = basePath + "mybids/experienceBorrowController/index/toBorrowStandardInfo.shtml?pid=";
			var zquan_href = basePath + "mybids/transferController/index/toTransferInfo.shtml?pid=";
			var re_href = "";
			//判断是不是转让
			if(undefined != transfId && "" != transfId){
				re_href = zquan_href + transfId;
			}else{
				if(undefined != borrowType && "" != borrowType){
					//e首房，e抵押
					if(borrowType == '1' || borrowType == '2'){
						re_href = eshouf_href + pid;
					}
					//e计划
					if(borrowType == '3'){
						re_href = ejihua_href + pid;
					}
					//新手
					if(borrowType == '4'){
						re_href = xins_href + pid;
					}
					//体验标
					if(borrowType == '5'){
						re_href = tiyan_href + pid;
					}
				}
			}
			//返回
			return re_href;
		},
		//项目转让表示
		borTranStatusFmat:function(tranftatus,pid,tranid){
			var reHTML = "";
			var tranURLa = basePath + "usercenter/borrowDetailController/findReceiptPlanById.shtml?borrowId=";
			var tranURLb = "&transferId=";
			if(tranftatus == 0){
				reHTML = "<a href=\"" + tranURLa + pid + tranURLb + tranid + "\">债权装让</a>";
			}
			if(tranftatus > 0){
				reHTML = tranftatus + "天后可转让";
			}
			return reHTML;
		},
		jugdeNullFmat:function(instr){
			if(null == instr || "" == instr || undefined == instr){
				return "-";
			}else{
				return instr;
			}
		}
		
};


//初始化
$(function(){
	//--------我的投资信息
	userinvestment.initPanel();
	
	//4个表格切换
	$(".ge_ye_tilte_nav").click(function(){
		var eq = $(this).index(".ge_ye_tilte_nav");
		$(".ge_ye_tilte_nav").removeClass("ms_t_se").eq(eq).addClass("ms_t_se");
		$(".inv_js").addClass("none").eq(eq).removeClass("none");
		//处理对应body中的信息
		userinvestment.onclickItems($(this));
	});
});