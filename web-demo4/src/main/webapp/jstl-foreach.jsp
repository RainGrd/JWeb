<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/22
  Time: 18:49:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--加入该标签手动开启el功能--%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="button" value="新增"><br>
<hr>
<table border="1" cellspacing="0" width="800">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>

    </tr>
    <%--
    <tr align="center">
        <td>1</td>
        <td>三只松鼠</td>
        <td>三只松鼠</td>
        <td>100</td>
        <td>三只松鼠，好吃不上火</td>
        <td>启用</td>
        <td><a href="#">修改</a> <a href="#">删除</a></td>
    </tr>

    <tr align="center">
        <td>2</td>
        <td>优衣库</td>
        <td>优衣库</td>
        <td>10</td>
        <td>优衣库，服适人生</td>
        <td>禁用</td>

        <td><a href="#">修改</a> <a href="#">删除</a></td>
    </tr>

    <tr align="center">
        <td>3</td>
        <td>小米</td>
        <td>小米科技有限公司</td>
        <td>1000</td>
        <td>为发烧而生</td>
        <td>启用</td>

        <td><a href="#">修改</a> <a href="#">删除</a></td>
    </tr>
--%>

    <%--获取数据
    items:容器
    var：临时变量
    varStatus:状态对象，一般用于代表序号
    这个forEach一般代表Java里面的for(对象实例：被遍历的对象){}
    --%>
    <c:forEach items="${brands}" var="brand" varStatus="status_id">
        <tr align="center">
<%--            <td>${brand.id}</td>--%>
            <td>${status_id.index}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
            <c:if test="${brand.status==1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status!=1}">
                <td>弃用</td>
            </c:if>
            <td><a href="#">修改</a> <a href="#">删除</a></td>

            <td><a href="#">修改</a> <a href="#">删除</a></td>
        </tr>
    </c:forEach>

</table>
<hr>
<%--
    普通的for循环
    begin:开始数
    end：结束数
    step：步长
    --%>
<c:forEach begin="0" end="10" step="1" var="i">
    ${i}
</c:forEach>
</body>
</html>
