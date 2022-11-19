$.extend($.fn.tree.methods, {
	getLevel : function(jq, target) {
		var l = $(target).parentsUntil("ul.tree", "ul");
		return l.length + 1;
	}
});

/**
 * 菜单对象
 */
var sysMenu = {
	/** 构建菜单树节点 */
	buildTreeNode:function(data,isFalg) {
		var nodes = [];
		var m_menus = {};
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			var node = {
				id : item.pid,
				text : item.menuName,
				attributes : {
					url : item.menuUrl,
					pid : item.parentAuthId
				}
			};
			if(isFalg == true){
				if (item.menuLevel == 2) {
					nodes.push(node);
				}
				var parentNode = m_menus[item.parentAuthId];
				m_menus[item.pid] = node;
				if(item.menuLevel != 2){
					if (item.parentAuthId && parentNode) {
						// 只展开一级菜单
						parentNode.state = 'closed';
						if (parentNode.attributes.parentAuthId) {
							parentNode.state = 'closed';
						}
						var children = parentNode['children'];
						if (!children) {
							children = parentNode['children'] = new Array();
						}
						children.push(node);
					}
				}
			}else{
				if (!item.parentAuthId) {
					nodes.push(node);
				}
				var parentNode = m_menus[item.parentAuthId];
				m_menus[item.pid] = node;
				if (item.parentAuthId && parentNode) {
					parentNode.state = 'closed';
					// 只展开一级菜单
					if (parentNode.attributes.parentAuthId) {
						parentNode.state = 'closed';
					}
					var children = parentNode['children'];
					if (!children) {
						children = parentNode['children'] = new Array();
					}
					children.push(node);
				}
			}
		}
		delete m_menus;
		return nodes;
	},
	/**
	 * 初始化菜单树
	 * @param elementId 页面元素ID
	 * @param id pid
	 * @param id isParame是否需要参数
	 */
	initTree:function(elementId,id,isParame) {
		var data;
		var url = BASE_PATH+'sysMenuController/selectSelective.shtml';
		if(isParame == true){
			data = {"data":'{'+"parentAuthId"+':"'+id+'"}'};
		}
		if("menu-index-tree" == elementId){
			url = BASE_PATH+'sysMenuController/queryAllMenu.shtml';
		}
		$.ajax({
			url : url,
			data:data,
			success : function(result) {
				if (result.message.message!="200") {
					alert(result.msg);
					return;
				}
				var nodes;
				if("west-tree" == elementId){
					if($('.layout-panel-west').hasClass('none')){
						$('.layout-panel-west').removeClass('none');
						 $('#bodyLayout').layout('expand','west');
					}
				}
				/*
				 *这里需要注意  为了适应二级菜单加载   如有不明之处  请联系：戴静宇 
				 */
				if(isParame == true){
					nodes = sysMenu.buildTreeNode(result.data,isParame);
				}else{
					nodes = sysMenu.buildTreeNode(result.data);
				}
				$("#"+elementId).tree({
					animate : true,
					data : nodes,
					onClick : function(node) {
						if("west-tree" == elementId){
							if(node.children){
								if(node.children.length){
									if($('#'+node.domId+' > .tree-hit').hasClass('tree-collapsed')){
										if($('#'+node.domId+' .tree-icon').hasClass('check-nav-level0')){
											$('#west-tree').tree('collapseAll');
										}
										$('#west-tree').tree('expand',$('#'+node.domId));
									}else{
										$('#west-tree').tree('collapse',$('#'+node.domId));						
									}
								}
							}
							sysMenu.pageTurn(node);
						}else if("menu-index-tree" == elementId){
							sysMenu.loadDetails(node.id);
							$('#menu_button_div .easyui-linkbutton').linkbutton('enable');
						}
					},
					onLoadSuccess:function(node,data){
						if(!isParame){
							if(id){
								node = $('#menu-index-tree').tree('find',id);
								$('#'+elementId).tree('select', node.target);
							}
						}
					}
				});
			}
		});
	},
	/**
	 * 新增菜单
	 */
	addModule:function(){
		//序列化表单 
		var obj = jqueryUtil.serializeObject($("#menu_add_module"));
		var jsonStr = JSON.stringify(obj);
		$.ajax({
			type: "POST",
	        async:false, 
	    	url : BASE_PATH+"sysMenuController/create.shtml",
	    	data:{"data":jsonStr},
			dataType: "json",
		    success: function(data){
		    	if(data.message.message == "200" ){
		    		//重新加载菜单
		    		sysMenu.initTree('menu-index-tree');
		    		$("#addMenuModuleDialog").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败！",'error');
		    	}
			}
		}); 
	},
	/**
	 * 新增子节点
	 */
	addChildNode:function(){
		//序列化表单 
		var obj = jqueryUtil.serializeObject($("#menu_add_child_node_form"));
		var jsonStr = JSON.stringify(obj);
		$.ajax({
			type: "POST",
	        async:false, 
	    	url : BASE_PATH+"sysMenuController/create.shtml",
	    	data:{"data":jsonStr},
			dataType: "json",
		    success: function(data){
		    	if(data.message.message == "200" ){
		    		//重新加载菜单
		    		sysMenu.initTree('menu-index-tree');
		    		$("#openChildNodeDialog").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败！",'error');
		    	}
			}
		}); 
	},
	/**
	 * 新增修改菜单
	 * @param formId form表单ID
	 * @param status 状态码（1：新增模块、2：新增子菜单、3：修改）
	 */
	addOrEditMenu:function(formId,status){
		//排列顺序
		var menuOrder = $("form[id='"+formId+"'] input[id='menuOrder']").val();
		//表单验证
		if(!$("#"+formId).form('validate')){
			return;
		}else if(isNaN(menuOrder)){
			$.messager.alert('操作提示',"显示顺序！ 只能输入数字",'error');
			return;
		}
		if(status == 2){
			//获取Tree选择节点
			var node = $("#menu-index-tree").tree("getSelected");
			//取得叶子节点
			var lv = $().tree("getLevel", node.target);
			//给新增子菜单添加菜单层级
			$("form[id=menu_add_child_node_form] input[id=menuLevel]").val(lv+1);
		}
		//序列化表单 
		var obj = jqueryUtil.serializeObject($("#"+formId));
		var jsonStr = JSON.stringify(obj);
		$.ajax({
			type: "POST",
	        async:false, 
	    	url : BASE_PATH+"sysMenuController/create.shtml",
	    	data:{"data":jsonStr},
			dataType: "json",
		    success: function(data){
		    	if(data.message.message == "200" ){
		    		var node = $("#menu-index-tree").tree("getSelected");
		    		$.messager.alert('操作提示',data.data,'info');
		    		if(status == 3){
		    			//隐藏修改时的按钮
		    			$("#menu_edit_button_div").hide();
		    			//显示菜单首页按钮
		    			$("#menu_button_div").show();
		    			//表单只读
		    			common.readonlyOrEnabledForm("menu_index_form",true);
		    		}else if(status == 2){
		    			$("#openChildNodeDialog").dialog('close');
		    		}else if(status == 1){
		    			$("#addMenuModuleDialog").dialog('close');
		    		}
		    		if(node){
		    			//重新加载菜单
			    		sysMenu.initTree('menu-index-tree',node.id);
		    		}
		    		//重新加载菜单
		    		sysMenu.initTree('menu-index-tree');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败！",'error');
		    	}
			}
		}); 
	},
	/**
	 * 打开新增模块
	 */
	openAddModule:function(){
		$("<div id='addMenuModuleDialog' /> ").dialog({
			href:BASE_PATH + "sysMenuController/openAddModule.shtml",
			title:"新增模块",
			method:'post',
			width:'500px',
			height:'300px',
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					sysMenu.addOrEditMenu("menu_add_module",1)
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$("#addMenuModuleDialog").dialog('close');
				}
			}],
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	},
	/**
	 * 打开新增子节点页面
	 */
	openAddChildNode:function(){
		//序列化表单 
		var obj = jqueryUtil.serializeObject($("#menu_index_form"));
		var jsonStr = JSON.stringify(obj);
		$("<div id='openChildNodeDialog' /> ").dialog({
			href:BASE_PATH + "sysMenuController/openAddChildNode.shtml",
			title:"新增子菜单",
			method:'post',
			width:'500px',
			height:'360px',
			queryParams:{"data":jsonStr},
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					sysMenu.addOrEditMenu("menu_add_child_node_form",2)
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$("#openChildNodeDialog").dialog('close');
				}
			}],
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	},
	/**
	 * 删除菜单根据pid
	 * @param pid 菜单ID
	 */
	meunDelete:function(){ 
		//获取Tree选择节点
		var node = $("#menu-index-tree").tree("getSelected");
		//判断是否是叶子节点
		var isLeaf  =$('#menu-index-tree').tree('isLeaf',node.target)
		//取得叶子节点
		//var lv = $().tree("getLevel", node.target);
		if(!isLeaf){
			$.messager.alert('提示','父级节点不能删除！');   
			return;
		}
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){
		    	var pid = $("#menu_index_form input[id=pid]").val();
				$.ajax({
					type: "POST",
			        async:false, 
			    	url : BASE_PATH+"sysMenuController/delete.shtml",
			    	data:{"data":'{'+"pid"+':"'+pid+'"}'},
					dataType: "json",
				    success: function(result){
				    	if(result.message.message == "200" ){
				    		//重新加载菜单
				    		sysMenu.initTree('menu-index-tree');
				    	}else{
				    		$.messager.alert('操作提示',result.message.message,'error');
				    	}
					}
				}); 
		    }    
		}); 
	},
	/**
	 * 加载详情 根据pid
	 * @param pid 菜单ID
	 */
	loadDetails:function(pid){
		$.ajax({
			type: "POST",
	        async:false, 
	    	url : BASE_PATH+"sysMenuController/getSysMenuByPK.shtml",
	    	data:{"data":'{'+"pid"+':"'+pid+'"}'},
			dataType: "json",
		    success: function(result){
		    	if(result.message.message == "200" ){
		    		$('#menu_index_form').form('load', result.data); 
		    	}else{
		    		$.messager.alert('操作提示',"加载失败！",'error');
		    	}
			}
		}); 
	},
	/**
	 * 页面跳转
	 */
	pageTurn:function(node){
		var url = node.attributes.url;
		if (node.children) {
			//$("#"+elementId).tree('toggle', node.target);
			return;
		}
		if (!url)
			return;
		if(url.indexOf("http")<0){
			//加上菜单ID  按钮权限需要用到
			url = BASE_PATH + url+"?menuId="+node.id;
		}
		layout_addTab(url,node.text);
	},
	/**
	 * 菜单修改按钮click事件
	 */
	menuEditClick:function(){
		//显示修改时的按钮
		$("#menu_edit_button_div").show();
		//隐藏菜单首页按钮
		$("#menu_button_div").hide();
		//表单取消
		common.readonlyOrEnabledForm("menu_index_form",false);
		
	},
	/**
	 * 菜单修改页面保存按钮click事件
	 */
	menuEditSaveClick:function(){
		sysMenu.addOrEditMenu("menu_index_form",3);
	},
	/**
	 * 菜单修改页面取消存按钮click事件
	 */
	menuEditCancelClick:function(){
		//隐藏修改时的按钮
		$("#menu_edit_button_div").hide();
		//显示菜单首页按钮
		$("#menu_button_div").show();
		//重置表单
		//$('#menu_index_form')[0].reset() 
		$('#menu_index_form').form('reset');
		//获取Tree选择节点
		var node = $("#menu-index-tree").tree("getSelected");
		//重新加载
		sysMenu.loadDetails(node.id);
		//选中指定节点
		$('#menu-index-tree').tree('select', node.target);  
		//表单只读
		common.readonlyOrEnabledForm("menu_index_form",true);
	},
	openButtonAssign:function(){
		//获取Tree选择节点
		var node = $("#menu-index-tree").tree("getSelected");
		var menuId = node.id;
		var url = BASE_PATH + "sysMenuController/openButtonAssign.shtml?menuId="+menuId;
		//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
		url = encodeURI(encodeURI(url));
		$("<div id='role_user_assign_dialog' /> ").dialog({
			href:url,
			title:"菜单-按钮分配",
			method:'post',
			width:'800px',
			height:'470px',
			modal: true,
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	}
};

$(document).ready(function() {
	//隐藏修改时的按钮
	$("#menu_edit_button_div").hide();
});
