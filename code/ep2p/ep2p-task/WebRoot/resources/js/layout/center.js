$(function() {
	
	$('#layout_center_tabsMenu').menu({
		onClick : function(item) {
			var curTabTitle = $(this).data('tabTitle');
			var type = $(item.target).attr('type');

			if (type === 'refresh') {
				layout_refreshTab();
				return;
			}

			if (type === 'close') {
				var t = $('#centerFrame').tabs('getTab', curTabTitle);
				if (t.panel('options').closable) {
					$('#centerFrame').tabs('close', curTabTitle);
				}
				return;
			}

			var allTabs = $('#centerFrame').tabs('tabs');
			var closeTabsTitle = [];

			$.each(allTabs, function() {
				var opt = $(this).panel('options');
				if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === 'closeAll') {
					closeTabsTitle.push(opt.title);
				}
			});
			for ( var i = 0; i < closeTabsTitle.length; i++) {
				$('#centerFrame').tabs('close', closeTabsTitle[i]);
			}
		}
	});
	$('#centerFrame').tabs({
		fit : true,
		plain:true,
		border : false,
		closable:false,
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#layout_center_tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		},
		tools : [ {
			iconCls : 'icon-reload',
			handler : function() {
				layout_refreshTab();
			}
		}, {
			iconCls : 'icon-cancel',
			handler : function() {
				var index = $('#centerFrame').tabs('getTabIndex', $('#centerFrame').tabs('getSelected'));
				var tab = $('#centerFrame').tabs('getTab', index);
				if(tab.panel('options').closable){
					$('#centerFrame').tabs('close', index);
				}else{
					//warningMessage('[' + tab.panel('options').title + ']不允许关闭');
				}
			}
		}],
		onSelect:function(title,index){
			if(title=='首页'){
				 layout_refreshTab();
			}
		}
	});
});