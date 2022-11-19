<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/5/16
  Time: 16:24:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>教师列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/JQuery.min.jS"></script>
    <script src="assets/layui/layui.js" charset="utf-8"></script>
    <script src="assets/js/jquery.cookie.js"></script>
    <script src="assets/js/axios.js"></script>
    <script src="assets/layui/jquery.particleground.min.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<style>
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
        top: 5px;
        padding: 0;
    }
</style>
<table class="layui-hide" id="table" lay-filter="test"></table>
<%--头部工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>
<%--操作--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#table',
            height: 500,
            page: true  //开启分页
            , url: 'teacherAllServlet'//数据接口
            , method: "POST"
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '教师表'
            , cols: [[
                {type: 'checkbox', fixed: 'left', width: 80,},
                {field: 'teacherId', title: '教师编号', width: 120, unresize: false, sort: true, align: 'center'}
                , {field: 'teacherAge', title: '年龄', width: 80, edit: 'text', sort: true, align: 'center'}
                , {field: 'teacherName', title: '姓名', width: 80, edit: 'text', align: 'center'}
                , {field: 'teacherSex', title: '性别', align: 'center', width: 50, edit: 'text',}
                , {field: 'teacherDept', title: '所属部门', width: 100, align: 'center'}
                , {field: 'teacherPhone', title: '手机号', width: 120, align: 'center'}
                , {field: 'qq', title: 'QQ', width: 150, align: 'center'}
                , {field: 'professional', width: 100, title: '职称', align: 'center',}
                , {
                    field: 'email', title: '邮箱', width: 180, align: 'center', edit: 'text', templet: function (res) {
                        return '<em>' + res.email + '</em>';
                    }
                }
                , {
                    field: 'offerStatus', title: '就职状态', width: 100, align: 'center', templet: function (res) {
                        if (res.offerStatus === 1) {
                            return '就职'
                        } else {
                            return '离职'
                        }
                    }
                }
                , {field: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'}
            ]],
            done:function (res) {
                console.log(res)
                /**/
            }
        });
        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.prompt({
                    formType: 2
                    , value: data.email
                }, function (value, index) {
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });
</script>

</body>
</html>
