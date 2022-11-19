/**
 * 个人中心_我的借款JS类
 */
var myBorrow = {
	// 初始化
	initData:function(flag){
		if(flag==null || flag == ''){
			flag = "1";
		}
		myBorrow.loadData(flag,null);
	},
	// 加载数据
	loadData:function(flag,elId){
		var url = "";
		var pageElId = "";
		if(flag == '1'){
			url = BASE_PATH + "business/myLoanController/selectPendingRepayment.shtml";
			pageElId = 'dhkTbodyPage';
		}else if(flag == '2'){
			url = BASE_PATH + "business/myLoanController/selectRepayment.shtml";
			pageElId = 'yhkTbodyPage';
		}else if(flag == '3'){
			url = BASE_PATH + "business/myLoanController/selectReceptionTender.shtml";
			pageElId = 'zbzTbodyPage';
		}else if(flag == '4'){
			url = BASE_PATH + "business/myLoanController/selectReceptionApplication.shtml";
			pageElId = 'sqjdTbodyPage';
		}
//		var params;
//		if(elId!=null && elId != '' ){
//			params = $("#"+elId).serializeArray();
//		}
		// 注销
		$("#"+pageElId).page( 'destroy' );
		myBorrow.initPage(url,flag,null,pageElId);
	},
	// 初始化分页
	initPage:function(url,flag,params,pageElId){
		$("#"+pageElId).page({
			pageSize : 10,    // 测试设置为1
			showInfo : false,
			showJump : false,
			showPageSizes : false,
			loadFirstPage : true,  // 自动加载第一页
			remote : {
				url : url,
				// params : params, //条件查询
				success : function(result, pageIndex) {
					if(result.message.status == 200 ){
			    		if(flag == '1'){
			    			// 1.待还款 
			    			myBorrow.htmlDhkTbody(result.data);
			    		}else if(flag == '2'){
			    			// 2.已还款 
			    			myBorrow.htmlYhkTbody(result.data);
			    		}else if(flag == '3'){
			    			// 3.招标中  
			    			myBorrow.htmlZbzTbody(result.data);
			    		}else if(flag == '4'){
			    			// 4.申请进度 
			    			myBorrow.htmlSqjdTbody(result.data);
			    		}
			    	}
				},
				pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
				pageSizeName : 'pageSize'//请求参数，每页数量
			}
		});
	},
	// 待还款 
	htmlDhkTbody:function(data){
		var html = " ";
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0;i<data.length;i++){
				html += '<tr>'+
					 '<td class="c2980b9"> '+data[i].borrowCode+' </td>'+ 
					 '<td> '+data[i].borrowName+'  </td>'+
					 '<td><span class="">￥'+(data[i].borrowMoney == null ? "0" : common.formatCurrency(data[i].borrowMoney))+'</span></td>'+
					 '<td><span class="">￥'+(data[i].repaidamount == null ? "0" : common.formatCurrency(data[i].repaidamount))+'</span></td>'+ 
					 '<td><span class="">￥'+(data[i].borTotalAmount == null ? "0" : common.formatCurrency(data[i].borTotalAmount))+' </td>'+
					 '<td> '+data[i].planindex+'/'+data[i].borDeadline+'  </td>'+ 
					 '<td> '+data[i].borrowStatus+'  </td>'+ 
					 '<td class="pos-r c2980b9"> '+data[i].expireTime+ '</td>'+ 
					 '<td> <a class="btn_samllss fl" href="javascript:void(0)" onclick="myBorrow.toPRepayBorrowInfo('+"'"+data[i].pid+"'"+')" >明细</a>'+
					 '<img class="btn_img" src="'+BASE_PATH+'theme/default/images/operate.png" alt="借款协议" /> </td>'+ 
					 '</tr> ';
			}
		}
		$("#dhkTbody").html(html);
	},
	// 已还款
	htmlYhkTbody:function(data){
		var html = " ";
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0;i<data.length;i++){
				html += '<tr>'+
				 	'<td class="c2980b9"> '+data[i].borrowCode+' </td>'+ 
					'<td> '+data[i].borrowName+'  </td>'+
					'<td><span class="">￥'+(data[i].borrowMoney == null ? "0" : common.formatCurrency(data[i].borrowMoney))+'</span></td>'+
					'<td><span class="">￥'+(data[i].repaidamount == null ? "0" : common.formatCurrency(data[i].repaidamount))+'</span></td>'+
					'<td><span class="">￥'+(data[i].repaidamount == null ? "0" : common.formatCurrency(data[i].repaidamount))+'</span></td>'+ 
					'<td> '+data[i].borrowStatus+'  </td>'+ 
					'<td> <img class="btn_img" src="'+BASE_PATH+'theme/default/images/operate.png" alt="借款协议" /> </td>'+ 
					'</tr> ';
			}
		}
		$("#yhkTbody").html(html);
	},
	// 招标中
	htmlZbzTbody:function(data){
		var html = " ";
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0;i<data.length;i++){
				// 进度条显示,判断是否开始
				var borrowProgress = data[i].borrowProgress == null ? 0 : data[i].borrowProgress;
				// 拼接%
				borrowProgress += "%";
				// 总利息
				var totalInterest = (data[i].borrowRate+data[i].manageExpenseRate)*data[i].borrowMoney*data[i].borDeadline/12;
				html += '<tr>'+
					 '<td class="pos-r"> '+data[i].borrowName+" "+data[i].publishTime+ '</td>'+ 
					 '<td class=""> '+data[i].borrowCode+' </td>'+  
					 '<td> <span class="tips">￥'+common.formatCurrency(data[i].borrowMoney)+'</span> </td>'+ 
					 '<td> '+data[i].borDeadline+'月  </td>'+ 
					 '<td> '+data[i].borrowRate*100+'%  </td>'+ 
					 '<td> '+data[i].manageExpenseRate*100+'%  </td>'+ 
					 '<td> ￥'+common.formatCurrency(totalInterest)+'  </td>'+ 
					 '<td> '+data[i].repaymentTypeName+'  </td>'+ 
					 '<td> '+( data[i].borStatus == 2 ? borrowProgress : data[i].borStatusName )+' </td>'+ 
					 '<td><img class="btn_img" src="'+BASE_PATH+'theme/default/images/operate.png" alt="借款协议" /></td>'+ 
					 '</tr> ';
			}
		}
		$("#zbzTbody").html(html);
	},
	// 申请进度
	htmlSqjdTbody:function(data){
		var html = " ";
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0; i<data.length; i++){
				// 编辑跳转路径
				var url = BASE_PATH + "business/borrowController/toAdd.shtml?pid="+data[i].pid;
				// 图标颜色
				var but = "";
				if(data[i].approveStatus == 1 ){
					// 如果是 完善资料阶段 = 1 ，是可删除，可编辑
					but = '<td><i > <img class="btn_img" style="margin-left:5px;" src="'+BASE_PATH+'theme/default/images/ac_4.png" onclick="myBorrow.openSqjdEdit('+"'"+url+"'"+')" /> </i> '+ 
					 '<i > <img class="btn_img" style="margin-left:5px;" src="'+BASE_PATH+'theme/default/images/ac_5.png"  onclick="myBorrow.deleteEdit('+"'"+data[i].pid+"'"+')" /> </i> </td>';
				}else if(data[i].approveStatus == 3 ){
					// 如果是 担保初审拒绝 = 3 ，可编辑，不可删除
					but = '<td><i > <img class="btn_img" style="margin-left:5px;" src="'+BASE_PATH+'theme/default/images/ac_4.png" onclick="myBorrow.openSqjdEdit('+"'"+url+"'"+')" /> </i> '+ 
					 '<i > <img class="btn_img_" style="margin-left:5px;" src="'+BASE_PATH+'theme/default/images/clear.png" /> </i> </td>';
				}else{
					// 其他状态，不可编辑，不可删除
					but = '<td><i > <img class="btn_img_" style="margin-left:5px;" src="'+BASE_PATH+'theme/default/images/ac_6.png" /> </i> '+ 
					 '<i > <img class="btn_img_" style="margin-left:5px;" src="'+BASE_PATH+'theme/default/images/clear.png" /> </i> </td>';
				}
				html += '<tr>'+ 
					 '<td class="pos-r"> '+( data[i].applyTime == null ? "" : data[i].applyTime )+ '</td>'+ 
					 '<td class=""> '+data[i].borrowCode+' </td>'+  
					 '<td> '+( data[i].borrowName == null ? "" : data[i].borrowName )+'  </td>'+ 
					 '<td> <span class="tips">￥'+common.formatCurrency(data[i].borrowMoney)+'</span> </td>'+ 
					 '<td> '+data[i].deadline+'月  </td>'+ 
					 '<td> '+( data[i].approveStatusName == "申请中" ? "完善资料" : data[i].approveStatusName )+' </td>'+ 
					 '<td> '+( data[i].borCondDesc == null ? "" : data[i].borCondDesc )+' </td>'+ 
					 but +  '</tr> ';
			}
		}
		$("#sqjdTbody").html(html);
	},
	// 打开申请进度里面的编辑
	openSqjdEdit:function(url){
		window.location.href = url ;
	},
	// 删除申请进度信息
	deleteEdit:function(pid){
		layer.confirm('你确定删除此条申请记录吗？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var url = BASE_PATH + "business/myLoanController/deleteApplicationProgress.shtml?pid="+pid;
			$.ajax({
				type: "POST",
		    	url : url,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		layer.alert('删除成功!', {
			    		    skin: 'layui-layer-molv' //样式类名
			    		});
			    		// 调用查询,重置数据
			    		
			    	}else{
			    		layer.alert('删除失败!', {
			    		    skin: 'layui-layer-molv' //样式类名
			    		});
			    	}
				}
			});
		});
		
	},
	//跳转到待还款详情
	toPRepayBorrowInfo:function(pid){
		$("#borrow_info").load( BASE_PATH+'business/myLoanController/toPRepayBorrowInfo.shtml?pid='+pid);
	},
	toRecharge:function(pid){
		$("#borrow_info").load( BASE_PATH+'recharge/userRechargeController/toRecharge.shtml');
	}
}

$(document).ready(function(){
	// 我的借款 1.待还款  2.已还款  3.招标中  4.申请进度  
	myBorrow.initData("1");
	
});