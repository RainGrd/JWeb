<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户新增</title>
	<style type="text/css">
		label{
			width:120px;
			display:block;
		}
	</style>
	<script type="text/javascript">
		function onSub(){
			var accountNo = $("#accountNo").val();
			var name = $("#name").val();
			var phoneNo = $("#phoneNo").val();
			var qqNo = $("#qqNo").val();
			var webchatNo = $('#webchatNo').val();
			$.ajax({
				type: "POST",
		        url: BASE_PATH+"sysUserController/create.shtml",
		        data: {"data":'{"accountNo":"11","name":"11","phoneNo":"11", "qqNo":"11","webchatNo":"11"}'},
		        dataType: "json",
		        success: function(data){
		        	$('#grid').datagrid("reload");
		        },
		        error:function(){
		        	alert("修改失败"); 
		        }
			});
		}
	</script>
</head>
<body>
	<div style="background:#fafafa;padding:10px;width:300px;height:300px;">
	    <form id="ff" method="post">
	        <div>
	            <label for="name">用户账号:</label>
	            <input class="easyui-validatebox " type="text" id="accountNo" name="accountNo" data-options="required:true">
	        </div>
	        <div>
	            <label for="name">用户名:</label>
	            <input class="easyui-validatebox " type="text" id="name" name="name" data-options="required:true">
	        </div>
	        <div>
	            <label for="email">手机号:</label>
	            <input class="easyui-validatebox " type="text" id="phoneNo" name="phoneNo" data-options="validType:'email'">
	        </div>
	        <div>
	            <label for="subject">QQ号:</label>
	            <input class="easyui-validatebox " type="text" id="qqNo" name="qqNo">
	        </div>
	        <div>
	            <label for="message">微信号:</label>
	            <input class="easyui-validatebox " type="text" id="webchatNo" name="webchatNo">
	        </div>
	        <div>
	            <input type="submit" onclick="onSub()" value="提交">
	        </div>
	    </form>
	 </div>     
</body>	        
</html>
	        
	        