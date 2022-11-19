<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/23
  Time: 15:27:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--加入该标签手动开启el功能--%>
<%@page isELIgnored="false" %>
<script src="js/JQuery.min.jS"></script>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>鼠标移过显示图片和数据</title>
</head>
<style>
    #content {
        width: 500px;
        margin: 0 auto;
    }

    ul, li {
        margin: 0px;
        padding: 0px;
        list-style: none;
        display: inline-block;
    }

    .info {
        width: 300px;
        height: 400px;
        display: none;
        background: skyblue;
        text-align: center;
    }
</style>
<body>
<div id="content">
    <ul>
        <c:forEach items="${books}" var="book" varStatus="book_id">
            <li>
                <img class="img" src="images/img_${book_id.index+1}.png" width="200">
                <div class="introduction">
                    <p class="bookName">书名:<span>${book.bookName}&nbsp;</span></p>
                </div>
                <div class="info">
                    <img src="" width="200">
                    <p>书名:</p>
                    <p>作者:</p>
                    <p>出版社:</p>
                    <p>图书描述:</p>
                </div>
            </li>
        </c:forEach>

        <%--<li>
            <div>
                <img src="images/img_0.png" width="100">
            </div>
            <div>${introduction}</div>
        </li>
        <li>
            <div>
                <img src="images/img_0.png" width="100">
            </div>
            <div>${introduction}</div>
        </li>
        <li>
            <div>
                <img src="images/img_0.png" width="100">
            </div>
            <div>${introduction}</div>
        </li>
        <li>
            <div>
                <img src="images/img_0.png" width="100">
            </div>
            <div>${introduction}</div>
        </li>
        <li>
            <div>
                <img src="images/img_0.png" width="100">
            </div>
            <div>${introduction}</div>
        </li>--%>
    </ul>
</div>
</body>
<script>
    /*鼠标移开事件*/
    $(function () {
        var xhr = new XMLHttpRequest();
        /*获取书名*/
        $('.img').mouseover(function () {
            /*书名*/
            var bookName = $(this).siblings('.introduction').children('p').children('span').text().trim();
            /*发送ajax请求*/
            xhr.open("POST", 'http://localhost:5050/ajax-demo/imgHoverServlet');
            xhr.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
            var _this = $(this);
            /*拼接数据*/
            var data = "bookName=" + bookName;
            xhr.send(data);
            /*xhr.onload = function () {
                /!*隐藏图片*!/
                _this.css('display', 'none');
                _this.siblings('.introduction').css('display', 'none')
                /!*图片放大*!/
                _this.siblings('.info').css('display', 'block');
                var parse = JSON.parse(this.responseText);
                _this.siblings('.info').children('p').eq(1).text(parse[0].bookName);
                console.log(parse[0].id);
                _this.siblings('.info').children('p').eq(0).attr('src','images/img_'+parse[0].id+'.png')
                _this.siblings('.info').children('p').eq(2).text(parse[0].author);
                _this.siblings('.info').children('p').eq(3).text(parse[0].press);
                _this.siblings('.info').children('p').eq(4).text(parse[0].description);
                return false;
            }*/
            /*4.获取服务端响应到客户端的数据*/
            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    /*图片放大*/
                    _this.css('display', 'none');
                    _this.siblings('.introduction').css('display', 'none')
                    /*图片放大*/
                    _this.siblings('.info').css('display', 'block');
                    var parse = JSON.parse(this.responseText);
                    _this.siblings('.info').children('p').eq(1).text(parse[0].bookName);
                    console.log(parse[0].id);
                    _this.siblings('.info').children('img').attr('src', 'images/img_' + parse[0].id + '.png')
                    _this.siblings('.info').children('p').eq(2).text(parse[0].author);
                    _this.siblings('.info').children('p').eq(3).text(parse[0].press);
                    _this.siblings('.info').children('p').eq(4).text(parse[0].description);
                    return false;
                }
            }
        })
        $('.img').mouseout(function () {
            $(this).css('display', 'block');
            $(this).siblings().css('display', 'block');
            $('.info').css('display', 'none')
        })
    })
</script>
</html>