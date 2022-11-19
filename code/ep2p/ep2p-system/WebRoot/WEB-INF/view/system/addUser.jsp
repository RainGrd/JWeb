<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
<form id="baseInfo" class="formWrap" name="baseInfo" action="<%=basePath%>sysUserController/create.shtml" method="POST">
<table class="cus_table formTable" >
    <tr>
      <td class="align_right" ><span class="cus_red">*</span>用户账号：</td>
      <td><input type="text"  class="text_style easyui-textbox" id="account_No" value="" name="accountNo" data-options="required:true,validType:'checkUsername'" /></td>
    </tr>
    <c:if test="${empty pid}">
    <tr>  
      <td class="align_right"><span class="cus_red">*</span>密码：</td>
      <td><input type="text"  class="text_style easyui-textbox" id="pass_Word" name="password" value="${password}" data-options="required:false,editable:false" /></td>
    </tr> 
    </c:if>
    <tr>  
      <td class="align_right"><span class="cus_red">*</span>真实姓名：</td>
      <td><input type="text"  class="text_style easyui-textbox" id="name" name="name" value="" data-options="required:true,validType:'cleanSpelChar'" /></td>
    </tr>
    <tr>
      <td class="align_right" >微信号：</td>
      <td><input type="text"  class="text_style easyui-textbox" id="webchat_No" name="webchatNo" value="" data-options=""/></td>
    </tr>
    <tr> 
      <td class="align_right" ><span class="cus_red">*</span>手机号码：</td>
      <td><input type="text"  class="text_style easyui-numberbox" id="phone_No" name="phoneNo" value="" data-options="required:true,validType:'telephone'" /></td> 
    </tr>
    <tr>
      <td class="align_right" >个人QQ：</td>
      <td><input type="text"  class="text_style easyui-numberbox" id="qq_No" name="qqNo" value="" /></td>
    </tr>
    <tr>
   	  <td class="align_right" >用户状态：</td>
      <td>
      	  <input type="radio" id="status" name="status" value="1" <c:if test="${status==1 || empty status}">checked </c:if>/>启用
      	  <input type="radio" id="status" name="status" value="0" <c:if test="${status==0}">checked</c:if>/> 禁用
      </td>	
    </tr>
    <tr>
   	 <td colspan="2"><input type="hidden" id="pid" name="pid" value="${pid}"></td>
    </tr>
</table>	
</form>	   
<script type="text/javascript" src="${basePath}resources/js/system/user/addUser.js"></script>
<script type="text/javascript">
userAdd.loadData();
</script> 
</div>
</body>