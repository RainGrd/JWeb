 
	var BorrowDetail = {
		// 初始化数据
		initData : function() {
			var url = BASE_PATH + "business/optionalInvestController/index/selectOptionalInvestList.shtml";
			initPage(url,$("#conditionForm").serializeArray());
		},
		// 条件查询
		queryData : function() {
			var url = BASE_PATH + "business/optionalInvestController/index/selectOptionalInvestList.shtml";
			$("#page").page('destroy');
			initPage(url,$("#conditionForm").serializeArray());
		}
	} 
	/**
	根据borrowId 查询借款详情
	*/
	function selectInfo(borrowId){
		window.location = BASE_PATH  +"business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId="+borrowId+"&pageTag=1";
	}
	
	function setData(borrowList) {
		    $("#investList").html("");
			if (borrowList == null || borrowList.length == 0) {
				return;
			}
			var html = "";
			for (var i = 0; i < borrowList.length; i++) {
				var op = ''; 
				if(borrowList[i].borStatus== '4' || borrowList[i].borStatus== '5'){
					 op = '<td><a href="javascript:void(0)" class="btn_samll_gray" onclick="selectInfo(\''+borrowList[i].pid+'\')">还款中</a></td> '
				}else if(borrowList[i].borStatus== '2'){
					op = '  <td><a class="btn_samll" href="javascript:void(0)" onclick="selectInfo(\''+borrowList[i].pid+'\')">立即购买</a></td> ';  
				}else if(borrowList[i].borStatus== '1' && borrowList[i].publishTime != null){
					op = '  <td><a class="btn_samll" href="javascript:void(0)" onclick="selectInfo(\''+borrowList[i].pid+'\')" id="'+borrowList[i].pid+'" >'+timer(borrowList[i].pid, borrowList[i].publishTime)+'</a></td> ';
				}else if(borrowList[i].borStatus=='8'){
					op = '  <td><a class="btn_samll_gray" href="javascript:void(0)" onclick="selectInfo(\''+borrowList[i].pid+'\')">已结清</a></td> ';  
				}
				var borrowProgress = borrowList[i].borrowProgress==undefined?0:(borrowList[i].borrowProgress*100).toFixed(0);
				var borrowName = borrowList[i].borrowName==undefined?"":borrowList[i].borrowName; 
				
				html +="<tr class=''>"+
				        "  <td class='pl30'>"+borrowName;
					        if(borrowList[i].investRewardScale != undefined){
					        	html+= "<span class='giftyellow'>奖励"+ borrowList[i].investRewardScale*100+"%</span>";
					        }
				        html += "</td>"+
					        "  <td class='colorc'>"+escfutil.formatCurrency(borrowList[i].borrowRate*100)+"%</td> "+
					        "  <td>"+escfutil.formatCurrency(borrowList[i].borrowMoney.toFixed(2))+"</td> "+
					        "  <td>"+borrowList[i].borDeadline+"个月</td> "+
					        "  <td>"+borrowProgress+"%</td> "+
					        op+
				           " </tr> ";  
		}	
			$("#investList").append(html);
	}
	$(function() {
		// 初始化债权购买数据
		BorrowDetail.initData();    
		// 加载e计划投资专题页 广告
		initEmortgageBanner();
	});
	// 加载e计划投资专题页 广告
	function initEmortgageBanner() {
		$
				.ajax({
					type : "POST",
					url : BASE_PATH
							+ "/business/financialProductsManageController/index/initAdvBanner.shtml",
					data : {
						webTag : "ep2p_adv_optionalInvestPage_t_1"
					},
					dataType : "json",
					success : function(data) {
						if (data.advList.length > 0) {
							var html = "<ul class='slides'>";
							for (var i = 0; i < data.advList.length; i++) {
								html += '<li> '+
		 									'<a title="" target="_blank" href="'+data.advList[i].url+'">'+
		 										'<img height="310" alt="" src="'+BASE_PATH+data.advList[i].fileUrl+'"> '+
											'</a> ' +
										'</li>';
							}
							html += "</ul>";
							if (data.advList.length > 1) {
								html += '<ul class="flex-direction-nav">'
										+ '<li><a class="flex-prev" href="javascript:void(0);">Previous</a></li>'
										+ '<li><a class="flex-next" href="javascript:void(0);">Next</a></li>'
										+ '</ul>'
										+ '<ol id="bannerCtrl" class="flex-control-nav flex-control-paging">'
										+ '<li><a>1</a></li>' + '<li><a>2</a></li>'
										+ '<li><a>2</a></li>' + '</ol>';
							}
							$("#banner_tabs").html(html);
						}else{
							//如果没有广告 广告位高度为0
							$("#banner_tabs").attr("height","0px");
						}
					},
					error : function() {
						layer.alert("");
					}
				});
	}
	/*初始化分页*/
	function  initPage(url,params){
		/*分页*/
		$("#page").page({
			pageSize : 12,  
			showInfo : false,
			showJump : true,
			showPageSizes : false,
			loadFirstPage : true,  // 自动加载第一页
			remote : {
				url : url,
				params : params,
				success : function(result, pageIndex) {
					setData(result.borrowList);
				},
				pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
				pageSizeName : 'pageSize'//请求参数，每页数量
			}
		});
	}
	/*设置查询条件值*/
	function setFormValue(startValue,endValue,type,obj){
		if(type == null || type.trim().length == 0){
			layer.alert('系统错误请联系管理员！');
			return; 
		}
		if("borrowType" == type){
			$("#borrowType").val(startValue);
		}else if("borDeadline" == type){ 
			$("#borDeadlineMin").val(startValue);
			$("#borDeadlineMax").val(endValue);
		}else if("yearRate" == type){
			$("#borrowRateMin").val(startValue);
			$("#borrowRateMax").val(endValue);
		}else if("borStatus" == type){ 
			$("#borStatus").val(startValue); 
		} 
		$(obj).siblings("i").removeClass("dr_sx_i").addClass("dr_sx_i_");
		$(obj).removeClass("dr_sx_i_").addClass("dr_sx_i");
		BorrowDetail.queryData();
	}

	function timer(contentId, publishTime) { 
		var ts = new Date(publishTime) - new Date();
		var tempStr1 = contentId.substring(0, contentId.lastIndexOf("!"));
		var tempStr2 = contentId.substring(contentId.lastIndexOf("!"));
		var contentId = tempStr1 + "\\" + tempStr2;
		if (ts > 0) {
			var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);// 计算剩余的天数
			var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);// 计算剩余的小时数
			var mm = parseInt(ts / 1000 / 60 % 60, 10);// 计算剩余的分钟数
			var ss = parseInt(ts / 1000 % 60, 10);// 计算剩余的秒数
			//dd = checkTime(dd);
			//hh = checkTime(hh);
			mm = checkTime(mm);
			ss = checkTime(ss);
			hh = (24 * dd) + hh
			setTimeout("timer('" + contentId + "','" + publishTime + "')", 1000);
			$("#" + contentId).text( hh + ":" + mm + ":" + ss );
			return hh + ":" + mm + ":" + ss ;
		} 
			$("#" + contentId).text("立即投资");
			$("#" + contentId).removeClass();
			$("#" + contentId).addClass("btn dropJionBtn width170");
			$("#" + contentId).attr("onclick",
					"selectFinProdInfoData('" + contentId + "')");
			clearTimeout(timer);

	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
 