/**
* 个人中心---头像设置
*/
var jcrop_api = "";

var accountAvatar = {
		//图片选择之后触发
		fileOnchangeImage:function(){
			//
			if(jcrop_api != ""){
				jcrop_api.destroy();
			}
			//然后异步加载图片
			$.ajaxFileUpload({
				url: basePath + 'userinfo/centerController/saveTemporaryAvatar.shtml',  
                secureuri: false,  
                fileElementId: 'avatarfile',  
                dataType: 'HTML',  
                beforeSend: function() {  
                
                },  
                complete: function() {  
                    
                },  
                success: function(data, status) {  
                	var dataMsg = data.replace(/<[^>]+>/g,"");
                	var dataObj = eval('(' + dataMsg + ')');
                	if(dataObj.code == 200){
                		var imageURl = basePath + dataObj.message;
                		//图片裁剪初始化
                		avatarCut.jcropFunc();
                		//图片设置参数
                		jcrop_api.setImage(imageURl + "?randm=" + Math.random());
                		jcrop_api.setSelect([0,0,98,98]);
                	}
                },  
                error: function(data, status, e) {  
                   
                }
			});
		},
		
		//保存裁剪后的图片信息进行后台裁剪
		saveCutImage:function(){
			var vimgleft = $('#imgleft').val();
			var vimgtop = $('#imgtop').val();
			var vimgwidth =  $('#imgwidth').val();
			var vimgheight = $('#imgheight').val();
			$.post(basePath + "userinfo/centerController/saveCurrentlyAvatar.shtml",{imgleft:vimgleft,imgtop:vimgtop,imgwidth:vimgwidth,imgheight:vimgheight},function(data){
				if(data.code == 200){
					layer.msg(USERCENTER_MSG.AVATER_SUCCESS, {icon:1,time:1000,offset:(document.body.clientHeight/2 - 50) + "px"},function(){
    					location.reload(false);
    				});
				}else{
					layer.msg(USERCENTER_MSG.AVATER_FAIL, {icon:2,time:1000,offset:(document.body.clientHeight/2 - 50) + "px"});
				}
			},"json")
		}
};


//进行处理图片裁剪信息
var avatarCut = {
	//操作方法
	jcropFunc:function() {
		$('#avatarimage').Jcrop({
			//参数设置
			//allowResize:false,
			aspectRatio:1,
			maxSize:[400,400],
			onChange : avatarCut.showCoords,
			onSelect : avatarCut.showCoords,
			onRelease : avatarCut.clearCoords
		}, function() {
			jcrop_api = this;
		});
	},
	
	//清空数据信息
	clearCoords:function(){
		 $('#cutimage_info_form input').val('');
	},
	
	//显示值
	showCoords:function(c){
		$('#imgleft').val(c.x);
		$('#imgtop').val(c.y);
		$('#imgwidth').val(c.w);
		$('#imgheight').val(c.h);
	}
};

