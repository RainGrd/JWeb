//禁用“确认重新提交表单”
window.history.replaceState(null, null, window.location.href);

/*非空函数判断*/
function isNull(value) {
    if (value == null || value.length <= 0 || value === '') {
        return true;
    }
    return false;
}

function setCookie(name, value, iDay) {
    var oDate = new Date();
    oDate.setDate(oDate.getDate() + iDay);
    document.cookie = name + '=' + value + ';expires=' + oDate;
}

//JS
layui.use(['element', 'layer', 'util', 'form'], function () {
    var element = layui.element
        , layer = layui.layer
        , util = layui.util
        , $ = layui.$
        , form = layui.form;
    var $data = {
        password: '',
        id: '',
        userStatus: '',
        username: ''
    }
    var index = 0;

    /*设置默认打开页面*/
    //头部事件
    util.event('lay-header-event', {
        //左侧菜单事件
        menuLeft: function (othis) {
            layer.msg('展开左侧菜单的操作', {icon: 0});
        }
        , menuRight: function () {
            index = layer.open({
                type: 1
                , content: $("#rightPanel")
                , area: ['260px', '100%']
                , offset: 'rt' //右上角
                , anim: 5
                , shadeClose: true
                , flex: false,
                resize: false,
                move: false,
            });
        }
    });

    var url = '.adminPress'
    /*打开内容主体区域*/
    $(function () {
        $('li>a').on('click', function (e) {
            e.preventDefault();
            $("#main").attr('src', $(this).attr('href'));
            /*自动点击*/
        })
        $('.adminPress').click();
        $('.exitLogin').on('click', function () {
            layer.confirm('你确定要退出吗？', {
                btn: ['确定', '取消']
            }, function () {
                /*消除Session*/
                /*sessionStorage.clear();
                $.ajax({
                    type: "POST",
                    url: 'http://localhost:8080/Press/user/loginOut',
                })*/
                /*if (window.history && window.history.pushState) {
                    $(window).on("popstate", function () {
                        window.history.pushState("forward", null, "#");
                        window.history.forward(1);
                    });
                }*/
                /*window.history.pushState("forward", null, "#"); //在IE中必须得有这两行
                window.history.forward(1);*/
                location.href='http://localhost:8080/Press/login.jsp';
            });
        })
        $('.change_password').on('click', function () {
            layer.alert('对不起，此功能还在开发中')
            /*layer.open({
                type: 1 //此处以iframe举例
                ,
                title: '修改密码'
                ,
                area: ['960px', '600px']
                ,
                shade: 0
                ,
                maxmin: true
                ,
                /!*offset: [ //为了演示，随机坐标
                    $(window).height()
                ]*!/

                content: $('#changPassword'),
                /!*btn: ['继续弹出', '全部关闭'] //只是为了演示
                ,
                yes: function () {
                    $(that).click();
                }
                ,
                btn2: function () {
                    layer.closeAll();
                }*!/
                /!*success: function (layero, index) {
                    layer.setTop(layero); //重点2. 保持选中窗口置顶
                    //记录索引，以便按 esc 键关闭。事件见代码最末尾处。
                    layer.escIndex = layer.escIndex || [];
                    layer.escIndex.unshift(index);
                    //选中当前层时，将当前层索引放置在首位
                    /!*layero.on('mousedown', function () {
                        var _index = layer.escIndex.indexOf(index);
                        if (_index !== -1) {
                            layer.escIndex.splice(_index, 1); //删除原有索引
                        }
                        layer.escIndex.unshift(index); //将索引插入到数组首位
                    });*!/
                }*!/

                /!*end: function () {
                    //更新索引
                    if (typeof layer.escIndex === 'object') {
                        layer.escIndex.splice(0, 1);
                    }
                }*!/
            });*/
        })
    })
    form.on('submit(changePassword)', function (data) {
        let field = data.field;
        /*if(isNull(field.newPwd) || isNull(field.newPwd)){
            layer.msg('以上选项不能为空');
            return false;
        }*/
        if (field.newPwd !== field.rNewPwd) {
            layer.msg('两次密码不一致');
            return false;
        }
        /*传值*/
        $data.password = field.newPwd;
        $.ajax({
            type: 'post',
            url: 'http://localhost:8080/Press/user/updataPassword',
            data: JSON.stringify($data),
            success: function (data) {
                if (data === 'changePwdSuccess') {
                    /*关闭修改密码窗口*/
                    layer.close(index);
                    /*密码修改成功！*/
                    layer.msg('密码修改成功！');
                    console.log($data)

                    setCookie('password', $data.password, 7)
                }
            },
            error: function (data) {
                console.log('出错了')
            }
        })
    })
});