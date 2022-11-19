<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/5/23
  Time: 12:23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 管理系统大布局 - Layui</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layui/css/layui.css">
<link
        rel="shortcut icon"
        type="images-icon"
        href="${pageContext.request.contextPath}/images/icon/doctor.ico"
/>
<link rel="stylesheet" href="assets/editor.md-master/css/editormd.min.css">
<link rel="stylesheet" href="assets/editor.md-master/css/editormd.preview.min.css">
<!--
引入markdow编辑器内容
-->

<head>
    <title>发布新闻</title>
</head>
<style>
    #content>h1{
        text-align: center;
        vertical-align: middle;
    }
</style>
<body>
<div id="content" style="width: 80%; margin: 0 auto;  height: 100%">
    <h1>发布新闻</h1>
    <form class="layui-form layui-form-pane"  method="post" id="insert" style="margin: 100px 0px 0px 0px">
        <div class="layui-form-item">
            <label class="layui-form-label">新闻标题</label>
            <div class="layui-input-block">
                <input type="text" required lay-verify="required" name="pressHead" value="" id="insertHead"
                       placeholder="请输入新闻标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <%--<div class="layui-form-item" style="display: none">
            <label class="layui-form-label">创建人</label>
            <div class="layui-input-block">
                <input type="text" required lay-verify="required" name="pressPerson" id="insertPerson"
                       placeholder="请输入你的名字或者笔号" autocomplete="off" class="layui-input">
            </div>
        </div>--%>
        <div class="layui-form-item layui-form-text ">
            <label class="layui-form-label">摘要</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入一段不少于五十个字的内容" required lay-verify="required" id="insertAbstract"
                          class="layui-textarea"
                          name="pressAbstract"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入一段不少大于五十个字的内容" required lay-verify="required" id="insertContent" class="layui-textarea"
                          name="pressContent"></textarea>
            </div>
        </div>
        <%--<div class="layui-fluid" style="margin: 100px 200px;">
            <textarea id="detail"></textarea>
        </div>--%>
        <%--<div class="layui-form-item">
            <div id="myEditor">
                <!-- 富文本编辑器 -->
                <div id="testEditorMd"></div>
            </div>
            <div id="markdownToHTML" style="margin-left: 100px">
                <textarea id="markdowContent" style="display:none;" placeholder="markdown语言"></textarea>
            </div>
        </div>--%>

        <div class="layui-form-item">
            <label class="layui-form-label">新闻板块</label>
            <div class="layui-input-inline">
                <select name="motifId" required lay-verify="required" class="layui-select" id="insertMotifId"
                        lay-verify="" lay-search="">
                    <option value=""></option>
                    <option value="0">互联网</option>
                    <option value="1">军事</option>
                    <option value="2">国内</option>
                    <option value="3">国际</option>
                    <option value="4">体育</option>
                    <option value="5">娱乐</option>
                    <option value="6">财经</option>
                    <option value="7">科技</option>
                    <option value="8">其他</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item" style="text-align:center;">
            <input class="layui-btn" type="submit" lay-filter="insert" lay-submit="" value="发布新闻"/>
        </div>
    </form>
    <%--<!-- 富文本编辑器 -->
    <div id="test-editormd"></div>--%>

</div>
<!-- 页面解析markdown为HTML显示 -->


<%--<div class="layui-btn-container" style="margin: 10px">
    <button id="showEditor" class="layui-btn">显示编辑器</button>
    <button id="getMarkdownContent" class="layui-btn">获取Markdown源码</button>
    <button id="getHtmlContent" class="layui-btn">获取Html源码</button>
    <button id="showHTML" class="layui-btn">Markdown解析成HTML</button>
</div>--%>
<script src="${pageContext.request.contextPath}/assets/js/JQuery.min.jS"></script>
<script src="${pageContext.request.contextPath}/assets/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/axios.js"></script>
<script src="assets/editor.md-master/editormd.min.js"></script>
<script src="assets/editor.md-master/lib/marked.min.js"></script>
<script src="assets/editor.md-master/lib/prettify.min.js"></script>
<script src="assets/editor.md-master/lib/raphael.min.js"></script>
<script src="assets/editor.md-master/lib/underscore.min.js"></script>
<script src="assets/editor.md-master/lib/sequence-diagram.min.js"></script>
<script src="assets/editor.md-master/lib/flowchart.min.js"></script>
<script src="assets/editor.md-master/lib/jquery.flowchart.min.js"></script>
<script type="text/javascript">
    /*非空函数判断*/
    function isNull(value) {
        if (value == null || value.length <= 0 || value === '') {
            return true;
        }
        return false;
    }
    /*时间工具类*/
    function packDate() {
        var date = new Date();
        var month = (date.getMonth() + 1);
        month = month < 10 ? '0' + month : month;
        var day = date.getDate();
        day = day < 10 ? '0' + day : day;
        var hour = date.getHours();
        hour = hour < 10 ? '0' + hour : hour;
        var minute = date.getMinutes();
        minute = minute < 10 ? '0' + minute : minute;
        var second = date.getSeconds();
        second = second < 10 ? '0' + second : second;
        return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second
    }
    layui.use(['layer', 'form','jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;
        var testEditor;
        /*$('#showEditor').on('click', function(){
            // 弹出框
            layer.open({
                type: 1
                ,content: $('#myEditor')
                ,btn: '关闭全部'
                ,btnAlign: 'c' //按钮居中
                ,shade: 0 //不显示遮罩
                ,area: ['1600px', '800px']
                ,yes: function(){
                    layer.closeAll();
                },
                success:function () {
                    testEditor = editormd("testEditorMd", {
                        width: "90%",
                        height: 740,
                        path : 'assets/editor.md-master/lib/',
                        theme : "default",
                        previewTheme : "default",
                        editorTheme : "default",
                        //markdown : md,             // 初始化编辑区的内容
                        codeFold : true,
                        //syncScrolling : false,
                        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
                        searchReplace : true,
                        //watch : false,                // 关闭实时预览
                        htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
                        //toolbar  : false,             //关闭工具栏
                        previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                        emoji : true,
                        taskList : true,
                        tocm            : true,         // Using [TOCM]
                        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
                        flowChart : true,             // 开启流程图支持，默认关闭
                        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
                        //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                        //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                        //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                        //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                        //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                        imageUpload : true,
                        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                        imageUploadURL : "./php/upload.php", // 文件上传路径，返回值为图片加载的路径
                        onload : function() {
                            // 加载后富文本编辑器成功后的回调
                            console.log('onload', this);
                            //this.fullscreen();
                            //this.unwatch();
                            //this.watch().fullscreen();
                            //this.setMarkdown("#PHP");
                            //this.width("100%");
                            //this.height(480);
                            //this.resize("100%", 640);
                            // 异步请求md文件，用于编辑时的数据回显
                            $.get('test.md', function(md) {
                                testEditor.setMarkdown(md);
                            });
                        }
                    });
                }
            });
        });*/
        testEditor = editormd("testEditorMd", {
            width: "100%",
            height: 740,
            path : 'assets/editor.md-master/lib/',
            theme : "default",
            previewTheme : "default",
            editorTheme : "default",
            //markdown : md,             // 初始化编辑区的内容
            codeFold : true,
            //syncScrolling : false,
            saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
            searchReplace : true,
            //watch : false,                // 关闭实时预览
            htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
            //toolbar  : false,             //关闭工具栏
            previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
            emoji : true,
            taskList : true,
            tocm            : true,         // Using [TOCM]
            tex : true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart : true,             // 开启流程图支持，默认关闭
            sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
            //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
            //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
            //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
            //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
            //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "./php/upload.php", // 文件上传路径，返回值为图片加载的路径
            onload : function() {
                // 加载后富文本编辑器成功后的回调
                console.log('onload', this);
                //this.fullscreen();
                //this.unwatch();
                //this.watch().fullscreen();
                //this.setMarkdown("#PHP");
                //this.width("100%");
                //this.height(480);
                //this.resize("100%", 640);
                // 异步请求md文件，用于编辑时的数据回显
                $.get('test.md', function(md) {
                    testEditor.setMarkdown(md);
                });
            }
        });
        // 获取markdown源码
        $('#getMarkdownContent').on('click', function () {
            var mdContent = $('.editormd-markdown-textarea').val();
            console.log(mdContent);
            var content = testEditor.getMarkdown();
            console.log(content);
        });
        // 获取解析后的html
        $('#getHtmlContent').on('click', function () {
            var content = testEditor.getHTML();
            console.log(content);
        });
        // 页面解析markdown为html进行显示
        $('#showHTML').on('click', function () {
            // 模拟从数据库中取内容
            $.get('test.md', function(md) {
                // 给textarea赋值
                $('#markdowContent').val(md);
                // 解析
                editormd.markdownToHTML("markdownToHTML", {
                    htmlDecode      : "style,script,iframe",
                    emoji           : true,  // 解析表情
                    taskList        : true,  // 解析列表
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true  // 默认不解析
                });
            });
        });
        /* 表单数据*/
        var $formData = {
            pressId: '',
            pressPerson: '',
            pressHead: '',
            pressTop: '',
            pressCreateDate: '',
            pressStatus: '',
            motifId: '',
            pressAbstract: '',
            pressContent: ''
        };
        /*将数据封装*/
        function formDataPack(data) {
            $formData.pressPerson = data.pressPerson;
            $formData.pressStatus = data.pressStatus;
            $formData.pressCreateDate = data.pressCreateDate;
            $formData.pressTop = data.pressTop;
            $formData.motifId = data.motifId;
            $formData.pressHead = data.pressHead;
            $formData.pressAbstract = data.pressAbstract;
            $formData.pressContent = data.pressContent;
        }
        /*新增页面表单提交事件*/
        form.on("submit(insert)", function (data) {
            let field = data.field;
            var reg = /^[1-9]\d*$/;
            if (reg.test(field.pressHead)) {
                layer.msg("标题只能为汉字或者字母")
                return false;
            }
            if (reg.test(field.pressPerson)) {
                layer.msg("创建人只能为汉字或者字母")
                return false;
            }
            if(field.pressAbstract.length<50){
                layer.msg('新闻摘要不少于50个汉字');
                return  false;
            }
            if(field.pressContent.length<50){
                layer.msg('新闻内容不少于50个汉字');
                return  false;
            }
            /*设置创建时间*/
            field.pressCreateDate = packDate();
            /*封装数据*/
            formDataPack(field);
            /*发送ajax请求*/
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/Press/press/insertPress",
                data: JSON.stringify($formData),
                success: function (resp) {
                    console.log(resp)
                    if (resp === 'insertSuccess') {
                        /*显示成功信息*/
                        layer.msg('发布成功！请等待管理员的审核');
                        /*跳转页面*/
                        location.href='http://localhost:8080/Press/allPress.jsp';
                        // location.reload()
                    }
                }, error: function (resp) {
                    console.log('出错了')
                }
            })
            /*防止表单提交*/
            return false;
        })
    });
</script>
</body>
</html>
