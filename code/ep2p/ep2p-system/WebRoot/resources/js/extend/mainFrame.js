
/**
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};






//格式化菜单连接
function formatterNote(node){
	if(node.attributes){
		return '<a href="javascript:void(0)" perm="'+(node.attributes["perm"]?node.attributes["perm"]:"ALLOW")+'" url="'+basePath+node.attributes["url"]+'" tabTitle="'+node.text+'">'+node.text+'</a>';
	}
	return node.text;
}

function layout_refreshTab() {
	var href = $('#centerFrame').tabs('getSelected').panel('options').href;
	if (href) {/*说明tab是以href方式引入的目标页面*/
		var index = $('#centerFrame').tabs('getTabIndex', $('#centerFrame').tabs('getSelected'));
		$('#centerFrame').tabs('getTab', index).panel('refresh');
	} else {/*说明tab是以content方式引入的目标页面*/
		var panel = $('#centerFrame').tabs('getSelected').panel('panel');
		var frame = panel.find('iframe');
		try {
			if (frame.length > 0) {
				for ( var i = 0; i < frame.length; i++) {
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
					frame[i].src = frame[i].src;
				}
				if ($.browser.msie) {
					CollectGarbage();
				}
			}
		} catch (e) {
		}
	}
}
function layout_refreshHomeTab(url) {
	var href =  $('#centerFrame').tabs('getTab', 0).panel('options').href;
	$('#centerFrame').tabs('select', '首页');
	if (href) {/*说明tab是以href方式引入的目标页面*/
		$('#centerFrame').tabs('getTab', 0).panel('refresh');
	} else {/*说明tab是以content方式引入的目标页面*/
		var panel = $('#centerFrame').tabs('getSelected').panel('panel');
		var frame = panel.find('iframe');
		try {
			if (frame.length > 0) {
				for ( var i = 0; i < frame.length; i++) {
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
					frame[i].src = url;
				}
				if ($.browser.msie) {
					CollectGarbage();
				}
			}
		} catch (e) {
		}
	}
}
//添加新窗口页面Tab
function layout_addTab(url,title){
	if ($('#centerFrame').tabs('exists', title)) {
		$('#centerFrame').tabs('select', title);
		if(url){
			var index = $('#centerFrame').tabs('getTabIndex', $('#centerFrame').tabs('getSelected'));
			$('#centerFrame').tabs('close', index);
			$('#centerFrame').tabs('add',{
				title: title,
				fit:true,
				content: '<iframe scrolling="yes" frameborder="0"  src="' + url + '" style="width:100%;height:100%"></iframe>',
				closable: true
			});
		}else{
			layout_refreshTab();
		}
	} else {
		$('#centerFrame').tabs('add',{
			title: title,
			fit:true,
			content: '<iframe scrolling="yes" frameborder="0"  src="' + url + '" style="width:100%;height:100%"></iframe>',
			closable: true
		});
	}
}

//添加新Home窗口页面Tab
function layout_addHomeTab(url,title){
	if ($('#centerFrame').tabs('exists', title)) {
		$('#centerFrame').tabs('select', title);
		layout_refreshTab();
	} else {
		$('#centerFrame').tabs('add',{
			title: title,
			fit:true,
			content: '<iframe scrolling="yes" frameborder="0"  src="' + url + '" style="width:100%;height:100%"></iframe>',
			closable: false
		});
	}
}
//子页面 在父页面添加新窗口页面Tab
function childLayout_addTab(url,title){
	if (parent.$('#centerFrame').tabs('exists', title)) {
		var t = parent.$('#centerFrame').tabs('getTab', title);
		if (t.panel('options').closable) {
			parent.$('#centerFrame').tabs('close', title);
		}
	}
	if(title=='首页'){
	
	}else{
		parent.$('#centerFrame').tabs('add',{
			title: title,
			fit:true,
			content: '<iframe scrolling="yes" frameborder="0"  src="' + url + '" style="width:100%;height:100%"></iframe>',
			closable: true
		});
	}
	
}
//顶部主菜单项的点击事件
function modelChange(modelType) {
	if (modelType == 1) { // 点击首页
		$('#west').panel('refresh',basePath+'layout/search.jsp');
		var mapUrl =  basePath+"map/mapListController/initMap.shtml";
		layout_addHomeTab(mapUrl,'首页')
	} else{
		$('#west').panel('refresh',basePath+'layout/west.jsp?v='+(modelType-1));
	}
	$('.north-groud4 a').removeClass('active').removeClass('active1').removeClass('active2').removeClass('active3').removeClass('active4');
	$('.north-groud4 a').eq(modelType-1).addClass('active');
	$('.north-groud4 a').eq(modelType-1).addClass('active'+modelType);
}
/**
 * 修改页面风格
 */
function setStyle(){
	$('#styleDialog').dialog('open');
}
/**
 * 修改密码
 */
function updatePwd(){
	$('#updatePwdDialog').dialog('open');
}

/**
 * 修改密码 保存 
 */
function modifyPassword() {
	$('#updatePwdDialog').find('#modifyPassword').form('submit', {
		url : "../sysUserFormController/modifyPassword.shtml",
		onSubmit : function() {
			if ($('#updatePwdDialog').find('#password').val() == '') {
				alertMessage("请输入密码！");
				return false;
			}
			if ($('#updatePwdDialog').find('#newPassword').val() == '') {
				alertMessage("请输入新密码！");
				return false;
			}
			if ($('#updatePwdDialog').find('#newPasswordCfm').val() == '') {
				alertMessage("请输入复核密码！");
				return false;
			}
			if ($('#updatePwdDialog').find('#newPassword').val() != $('#updatePwdDialog').find('#newPasswordCfm').val()) {
				alertMessage("新密码和复核密码不相同！");
				return false;
			}
		},
		success : function(result) {
			if (result != '') {
				if (result == '1') {
					alertMessage("密码输入有误！");
				} else {
					alertMessage(result);
				}
			} else {
				alertMessage("修改密码成功！");
			}
			$('#updatePwdDialog').dialog('close');
		}
	});
}
/**
 * 注销  
 */
function logout(){
	$.post(basePath + "sys/sysUserFormController/logout.shtml",
		function(data){
			window.location.href=basePath;
	});
}

//关闭 Dialog 
function closeDialog(dialogId){
	$(dialogId).find('form').form('reset');
	$(dialogId).dialog('close');
}

//打开 Dialog 
function showDialog(dialogId){
	$(dialogId).dialog('open');
}

/**
 * width:width-40,
	height:height-40
* dialog resize change Map tab size
*/
function changMapTab(tabId,width,height){
	if($('#'+tabId).length){
		$('#'+tabId).tabs({
			width:width,
			height:height-40
			});
		$('#'+tabId).tabs('getSelected').panel({
			width:width,
			height:height-40
		})
		if($('#'+tabId).tabs('getSelected').panel('options').title=='地图'){
			var panel = $('#'+tabId).tabs('getSelected').panel('panel');
			var frame = panel.find('iframe');
			try {
				if (frame.length > 0) {
					for ( var i = 0; i < frame.length; i++) {
						frame[i].contentWindow.document.write('');
						frame[i].contentWindow.close();
						frame[i].src = frame[i].src;
					}
					if ($.browser.msie) {
						CollectGarbage();
					}
				}
			} catch (e) {
			}
		}
	
	}
}
function changeViewDatagrid(viewdatagrid,height){
	$('#'+viewdatagrid).datagrid({ 
		height:height-60
	})
}
function changeViewDatagridSize(viewdatagrid,width,height){
	$('#'+viewdatagrid).datagrid({ 
		width:width-34,
		height:height-60
	})
}
function foldDatagrid(){
	$('.datagrid-view,.datagrid-pager').slideUp()
}
function openDatagrid(){
	$('.datagrid-view,.datagrid-pager').slideDown()
}