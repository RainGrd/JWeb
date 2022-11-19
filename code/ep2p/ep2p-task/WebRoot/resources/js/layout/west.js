 $(function(){
		$('#west-tree').tree({
			onClick: function(node){
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
				}else{
					layout_addTab($('#'+node.domId).find('a').attr('url'),$('#'+node.domId).find('a').attr('tabTitle'));
				}
			}
		});
		//菜单加载
		//sysMenu.initTree('west-tree');
	}); 