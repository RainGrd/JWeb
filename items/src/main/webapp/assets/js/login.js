$(function () {
    /*表单数据*/
    var $formData = {
        username: "",
        email: "",
        password: "",
        status: "",
    }
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
            $("#password_err").text("密码不能为空！")
            return false;
        } else {
            $("#password_err").text("");
        }
    })
    /*设置复选框默认选中*/
    var checkboxValue = $("#user_remember_me").val();
    if (checkboxValue === "1") {
        $("#user_remember_me").attr("checked", "checked");
        setCookie("checkboxStatus", checkboxValue, 7);
    }else{
        $("#user_remember_me").attr("checked", "");
        clearCookie("checkboxStatus");
    }
    /**
     * 判断复选中是否被选中
     */
    $("#user_remember_me").change(function () {
        var checkValue = !$("input[type=checkbox]").is(":checked") ? 0 : 1;
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

    /*判断密码强度*/
    // 进行登录操作
    /*form.on('submit(login)', function (data) {
        data = data.field;
        if (isNull(data.username)) {
            layer.msg('邮箱不能为空');
            return false;
        }
        if (isNull(data.password)) {
            layer.msg('密码不能为空');
            return false;
        }
        /!*获取数据*!/
        $formData.email = $("#username").val();
        $formData.password = $("#password").val();
        /!*发送请求*!/
        axios({
            method: "POST",
            url: "http://localhost:5050/items/loginServlet",
            data: $formData,
        }).then(function (response) {
            /!*返回响应的JSON数据*!/
            if (response.data.success === 1) {
                //判断cookie中复选框的状态
                isCheckStatus(getCookie("checkboxStatus"));
                /!*登录成功！*!/
                location.href='layuimini-2/index.html';

            } else {
                layer.msg('邮箱或密码错误！', function () {
                    return false;
                });
                /!*$("#userName_err").text("邮箱或密码错误！");*!/
                errCount++;
                if (errCount >= 3) {
                    layer.alert("你已经无法登录，请刷新页面后在登录");
                    /!*禁用登录按钮*!/
                    $("#login").attr("disable", "disable");
                    /!*设置两个文本框为只读属性*!/
                    $("#username").attr("readonly", "readonly");
                    $("#password").attr("readonly", "readonly");
                    return false;
                }
            }
        })
        return false;
    });*/
    /*表单提交事件*/
    $("#form").submit(function () {
        /*获取数据*/
        $formData.email = $("#username").val();
        $formData.password = $("#password").val();
        /*非空判断*/
        if (isNull($formData.email)) {
            $("#userName_err").text("邮箱不能为空!");
            return false;
        } else if (isNull($formData.password)) {
            $("#password_err").text("密码不能为空!");
            return false;
        }
        if (!isNull($formData.email)) {
            $("#userName_err").text("");
        } else if (!isNull($formData.password)) {
            $("#password_err").text("");
        }
        /*发送请求*/
        axios({
            method: "POST",
            url: "http://localhost:5050/items/loginServlet",
            data: $formData,
        }).then(function (response) {
            /*返回响应的JSON数据*/
            if (response.data.success === 1) {
                //判断cookie中复选框的状态
                isCheckStatus(getCookie("checkboxStatus"));
                /*登录成功！*/
                location.href = "http://localhost:5050/items/layuimini-2/index.html";
            } else {
                $("#userName_err").text("邮箱或密码错误！");
                errCount++;
                if (errCount >= 3) {
                    /*无法登录，请刷新页面后在登录*/
                    $('#password_err').text("你已经无法登录，请刷新页面后在登录");
                    /*禁用登录按钮*/
                    $("#login").attr("disable", "disable");
                    /*设置两个文本框为只读属性*/
                    $("#username").attr("readonly", "readonly");
                    $("#password").attr("readonly", "readonly");
                    return false;
                }
            }
        })
        return false;
    })

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

    /*判断*/
    if (getCookie("checkboxStatus") === "1") {
        var username = getCookie("username").replace(/\"/g, "");
        $("#username").val(username);
        $("#password").val(getCookie("password"));
    }
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


})


