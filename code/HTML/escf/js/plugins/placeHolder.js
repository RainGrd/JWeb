//判断浏览器是否支持 placeholder属性  
function isPlaceholder(){  
    var input = document.createElement('input');  
    return 'placeholder' in input;  
}  
  
if (!isPlaceholder()) {//不支持placeholder 用jquery来完成  
    $(document).ready(function() {  
        if(!isPlaceholder()){  
            $("input").not("input[type='password']").each(//把input绑定事件 排除password框  
                function(){  
                    if($(this).val()=="" && $(this).attr("placeholder")!=""){  
                        $(this).val($(this).attr("placeholder"));  
                        $(this).focus(function(){  
                            if($(this).val()==$(this).attr("placeholder")) $(this).val("");  
                        });  
                        $(this).blur(function(){  
                            if($(this).val()=="") $(this).val($(this).attr("placeholder"));  
                        });  
                    }  
            });  
            //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换  
            var pwdField    = $("input[type=password]"); 
            pwdField.each(function(i,ele){
                var pwdVal = $(ele).attr('placeholder');
                $(ele).before('<input id="pwdPlaceholder" type="text" value='+pwdVal+' />');
                var pwdPlaceholder = $(ele).prev();  
                pwdPlaceholder.show();  
                $(ele).hide();  
                //var $this = $(ele);
                pwdPlaceholder.focus(function(){  
                    pwdPlaceholder.hide();  
                    $(this).next().show();  
                    $(this).next().focus();  
                });  
                  
                $(ele).blur(function(){  
                    if($(this).val() == '') {  
                        $(this).prev().show();  
                        $(this).hide();  
                    }  
                });  
            }); 
        }  
    });  
      
}  

