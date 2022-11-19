$(function() {
	//logo 点击事件
	$('.navbar-brand').click(function(){
		$('#centerFrame').tabs('select', '首页');
		$('#centerFrame').tabs('getTab', 0).panel('refresh');
	});
	//折叠菜单 
	$('#collspanBar').click(function(){
		if($('.layout-panel-west').hasClass('none')){
			$('.layout-panel-west').removeClass('none');
			 $('#bodyLayout').layout('expand','west');
		}else{			
			 $('.layout-panel-west').addClass('none');
			 $('#bodyLayout').layout('collapse','west');
		}		
	});
	//向左的箭头
	$('#slideMenuLeft').click(function(){
		var c=Number($(this).attr('c'));
		if(c>0){
			return false;
		}else{
			$(this).attr('c','1')
		}
		$('#north_div').stop().animate({
            left: 0
        }, 500,function(){
        	$('#slideMenuLeft').attr('c','0')
			$('#slideMenuLeft').css('display','none');
			$('#slideMenuLi').css('display','block');
        });
		
	});
	//向右的箭头
	$('#slideMenuLi').click(function(){
		var l=$("#north_div li").length;
		var c=Number($(this).attr('c'));
		if(c>0){
			return false;
		}else{
			$(this).attr('c','1')
		}
		$('#north_div').stop().animate({
            left: '-=' + 83 * (l - 7)
        }, 500,function(){
        	$('#slideMenuLi').attr('c','0')
			$('#slideMenuLi').css('display','none');
			$('#slideMenuLeft').css('display','block');
        });
	});
	
	$.ajax({
		url : BASE_PATH+'sysMenuController/selectSelective.shtml',
		data:{"data":'{'+"menuLevel"+':"1"}'},
		success : function(result) {
			if (result.message.message!="200") {
				$.messager.alert("加载错误！");
				return;
			}
			var data = sysMenu.buildTreeNode(result.data);
			var htmlStr = "";
			//下面使用each进 行遍历  
		    $.each(data,function(n,element) { 
		    		 htmlStr += '<li class="grey"><a href="#" onclick="initTreeNav(this,\''+element.id+'\')"><span class="badge badge-grey">';  
		    		 htmlStr += element.text;
		    		 htmlStr += '</span></a></li>';
		     });  
		   
		    if(data.length>7){
		    	$('#slideMenuLi').css('display','block');
		    }
		     $("#north_div").prepend(htmlStr);
		     $('#north_div').css('width',$("#north_div li").length*83);
		}
	});
});
function initTreeNav(t,id){
	$('#north_div li').removeClass('active');
	$(t).parent().addClass('active');
	sysMenu.initTree("west-tree",id,true);
}
function logoutFun() {		
	$.messager.confirm('友情提示','确定退出系统?',function(r){
	    if (r){
	    	// 调用论坛退出。
	    	$.ajax({
	    		url : BASE_PATH+'sysUserController/bbsLogout.shtml',
	    		async:false,
	    		dataType: "json",
	    		success : function(data) {
	    			if (data.message=="200") {
	    				var result = data.result;
	    				$("#bbsLogout").html(result);
	    			}
	    		}
	    	});
	    	window.location = BASE_PATH+'sysUserController/logout.shtml';
	    	
	    }
	});	
}

/**
 * 打开修改密码窗口
 */
function openChangePassword(){
	url = BASE_PATH + "sysUserController/openChangePassword.shtml"
	title = "密码-修改";
	$("<div id='change_passworde_dialog' /> ").dialog({
		href:url,
		title:title,
		method:'post',
		width:'300px',
		height:'150px',
		modal: true,
		buttons:[{
			text:'修改',
			iconCls:'icon-save',
			handler:function(){
				changePassword();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$("#change_passworde_dialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

/**
 * 修改密码
 */
function changePassword(){
    if(!$("#change_password_form").form('validate')){
		return ;
    }
	//序列化表单 
	var obj = jqueryUtil.serializeObject($("#change_password_form"),true);
	$.ajax({
		type: "POST",
        async:false, 
    	url : BASE_PATH+"sysUserController/changePassword.shtml",
    	data:obj,
		dataType: "json",
	    success: function(data){
	    	if (data.message.status=="200") {
	    		if(data.data){
	    			$.messager.alert('操作提示',"修改成功！",'success');
	    			$("#change_passworde_dialog").dialog('close');
	    		}else{
	    			$.messager.alert('操作提示',"旧密码错误，请确认！",'error');
	    		}
			} else {
				$.messager.alert('操作提示',"保存失败，请联系管理员！",'error');
			}
		}
	});
}