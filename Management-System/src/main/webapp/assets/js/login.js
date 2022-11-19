//禁用“确认重新提交表单”
window.history.replaceState(null, null, window.location.href);

/**
 * 非空函数
 * @param value 参数值
 * @returns {boolean}
 */
function isNull(value) {
    if (value == null || value.length <= 0 || value === '') {
        return true;
    }
    return false;
}

/**
 * @description: 如果等于1的话，将用户名和密码存放在输入框中 否则消除数据
 * @param status 判断cookie中复选框的状态
 */
function isCheckStatus(status) {
    if (status === "1") {
        /*去掉两边的引号*/
        var username = getCookie("username").replace(/\"/g, "");
        /*创建用户和密码的cookie对象*/
        setCookie("username", username, 7);
        setCookie("password", getCookie("password"), 7);
        $("#username").val(username);
        $("#password").val(getCookie("password"));
    } else {
        $("#username").val("");
        $("#password").val("");
        /*删除cookie的值*/
        clearCookie("username");
        clearCookie("password");
        clearCookie("checkboxStatus");
    }
    $("#user_remember_me").val(Number(status))
}

/**
 *
 * @param name 删除cookie的名字
 */
function clearCookie(name) {
    setCookie(name, "", 0);
}

/**
 * 用于将值存入cookie中
 * @param name cookie的名字
 * @param value 值
 * @param iDay 过期时间(天)
 */
function setCookie(name, value, iDay) {
    var oDate = new Date();
    oDate.setDate(oDate.getDate() + iDay);
    document.cookie = name + '=' + value + ';expires=' + oDate;
}

/**
 * @param name 用于获取URL地址栏中的参数
 * @returns {string}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return r[2];
    return '';
}

/**
 * @description: 用于获取存放在cookie里面的数据
 * @param user_name 索引长度
 */
function getCookie(user_name) {
    if (document.cookie.length > 0) {
        var arr = document.cookie.split(";");
        for (let i = 0; i < arr.length; i++) {
            var split = arr[i].trim().split("=");
            if (split[0] === user_name) {
                return split[1];
            }
        }
    }
    return "";
}

/*layui*/
layui.use(['form', 'jquery'], function () {
    /*获取layui的对象*/
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer;
    // 登录过期的时候，跳出ifram框架
    if (top.location != self.location) top.location = self.location;
    $('.bind-password').on('click', function () {
        if ($(this).hasClass('icon-5')) {
            $(this).removeClass('icon-5');
            $("input[name='password']").attr('type', 'password');
        } else {
            $(this).addClass('icon-5');
            $("input[name='password']").attr('type', 'text');
        }
    });

    $('.icon-nocheck').on('click', function () {
        if ($(this).hasClass('icon-check')) {
            $(this).removeClass('icon-check');
        } else {
            $(this).addClass('icon-check');
        }
    });
    /**
     * 判断复选中是否被选中
     */
    if ($("#user_remember_me").val() === "1") {
        $(".layui-unselect").addClass("layui-form-checked");
        $("#user_remember_me").attr("checked", "checked");
        if (getCookie("checkboxStatus") === "1") {
            $("#username").val(getCookie("username"));
            $("#password").val(getCookie("password"));
        }
    } else if ($("user_remember_me").val() === "0") {
        $(".layui-unselect").removeClass("layui-form-checked");
        $("user_remember_me").attr("checked", "");
        clearCookie("checkboxStatus");
    }
    /*表单数据*/
    var $formData = {
        username: "",
        email: "",
        password: "",
        status: "",
    }
    $('#password').focus();
    $(function () {

        $("#login>b").hover(function () {
            $(this).siblings("a").css("textDecoration", "underline");
        }, function () {
            $(this).siblings("a").css("textDecoration", "none");
        })
        $("#login>b").on("click", function () {
            location.href = "../../register.html";
        })
        /*错误判断次数*/
        var errCount = 0;
        $("#user_remember_me").change(function () {
            var checkValue = !$(this).is(":checked") ? 0 : 1;
            /*将值存到cookie里面*/
            setCookie("checkboxStatus", checkValue, 7);
            $(this).val(checkValue);
            if (getCookie("checkboxStatus") === "0") {
                $(this).attr("checked", "");
            } else {
                $(this).attr("checked", "checked");
            }
        });
        /**
         * 用户名文本移开事件
         */
        $("#username").on("input", function () {
            var username = $(this).val();
            var number = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if (!number.test(username)) {
                $("#userName_err").text("邮箱格式不正确！");
                return false;
            } else {
                $("#userName_err").text("");
            }
        });
        /**
         * 密码输入事件
         */
        $("#password").on("input", function () {
            var password = $(this).val();
            if (isNull(password)) {
                layer.msg('密码不能为空!');
                return false;
            }
        })
        /*判断*/
        /*var checkboxValue = $("#user_remember_me").val();
        if (checkboxValue === "1") {
            setCookie("checkboxStatus", checkboxValue, 7);
        } else {
            clearCookie("checkboxStatus");
        }*/
        /*设置复选框默认选中*/
        if (getCookie("checkboxStatus") === "1") {
            var username = getCookie("username").replace(/\"/g, "");
            $("#username").val(username);
            $("#password").val(getCookie("password"));
        }
        /*判断密码强度*/

        /*表单提交事件*/
        $("#form").submit(function () {
            /*获取数据*/
            $formData.email = $("#username").val();
            $formData.password = $("#password").val();
            /*非空判断*/
            if (isNull($formData.email)) {
                layer.msg('请输入用户名!');
                // $("#userName_err").text("邮箱不能为空!");
                return false;
            } else if (isNull($formData.password)) {
                layer.msg('请输入密码!');
                // $("#password_err").text("密码不能为空!");
                return false;
            }
            if (!isNull($formData.email)) {
                $("#userName_err").text("");
            } else if (!isNull($formData.password)) {
                $("#password_err").text("");
            }
            /*获取复选框的值*/
            var checkboxValue = $("#user_remember_me").val();
            if (checkboxValue === "0") {
                clearCookie("checkboxStatus");
            } else {
                setCookie("checkboxStatus", checkboxValue, 7);
            }
            /*发送请求*/
            axios({
                method: "POST",
                url: "http://localhost:8080/Management-System/loginServlet",
                data: $formData,
            }).then(function (response) {
                /*返回响应的JSON数据*/
                if (response.data === "success") {
                    //判断cookie中复选框的状态
                    isCheckStatus(getCookie("checkboxStatus"));
                    /*登录成功！*/
                    location.href = "http://localhost:8080/Management-System/admin.jsp";
                } else {
                    // $("#userName_err").text("邮箱或密码错误！");
                    errCount++;
                    /*清空cookie*/
                    if (errCount >= 3) {
                        layer.alert("你已经无法登录，请刷新页面后在登录");
                        /*无法登录，请刷新页面后在登录*/
                        /*$('#password_err').text("你已经无法登录，请刷新页面后在登录");*/
                        /*禁用登录按钮*/
                        $("#login").attr("disable", "disable");
                        /*设置两个文本框为只读属性*/
                        $("#username").attr("readonly", "readonly");
                        $("#password").attr("readonly", "readonly");
                        return false;
                    }
                    /*错误提示*/
                    layer.alert('邮箱或密码错误！', {
                        time: 5*1000
                        ,success: function(layero, index){
                            var timeNum = this.time/1000, setText = function(start){
                                layer.title((start ? timeNum : --timeNum) + ' 秒后关闭', index);
                            };
                            setText(!0);
                            this.timer = setInterval(setText, 1000);
                            if(timeNum <= 0) clearInterval(this.timer);
                        }
                        ,end: function(){
                            clearInterval(this.timer);
                        }
                    });
                }
            })
            return false;
        })

        /*获取注册过来的数据*/
        var success = getQueryString("success");
        if (!(isNull(success))) {
            /*将数据放到密码框里*/
            var username = getCookie("username").replace(/\"/g, "");
            var password = getCookie("password");
            $("#username").val(username);
            $("#userName_err").val("请先登录！")
            $("#password").val(password);
        }
    })
    form.on('checkbox(filter)', function (data) {
        // console.log(data.elem); //得到checkbox原始DOM对象
        // console.log(data.elem.checked); //是否被选中，true或者false
        var othis = data.othis;
        if (document.getElementById("user_remember_me").value === 1) {
            othis.addClass("layui-form-checked")
        }
        if (data.elem.checked) {
            document.getElementById("user_remember_me").value = 1;
        } else {
            document.getElementById("user_remember_me").value = 0;
        }
        // console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        // console.log(data.othis); //得到美化后的DOM对象
    });
    /*设置身份复选框单选*/
    form.on('checkbox(oneCheck)', function (data) {
        // console.log(data.elem); //得到checkbox原始DOM对象
        // console.log(data.elem.checked); //是否被选中，true或者false
        $("input[name='identity']").prop('checked', false);
        $(this).prop('checked', true);
        form.render('checkbox');
        console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        $formData.status=data.value;
        // console.log(data.othis); //得到美化后的DOM对象
    });
    form.render('checkbox');
    // 进行登录操作
    /*form.on('submit(login)', function (data) {
        data = data.field;
        if (data.username=== '') {
            layer.msg('用户名不能为空');
            return false;
        }
        if (data.password === '') {
            layer.msg('密码不能为空');
            return false;
        }
        if (data.captcha === '') {
            layer.msg('验证码不能为空');
            return false;
        }
        layer.load('登录成功', function () {
            window.location = '../index.html';
        });
        return false;
    });*/

});



