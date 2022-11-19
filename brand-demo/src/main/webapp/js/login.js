$(function () {
    $("#register>b").hover(function () {
        $(this).siblings("a").css("textDecoration", "underline");
    }, function () {
        $(this).siblings("a").css("textDecoration", "none");
    })
    $("#register>b").on("click", function () {
        location.href = "../../register.html";
    })
    /*表单数据*/
    var $formData = {
        username: "",
        email: "",
        password: "",
        status: "",
    }

    /**
     * 非空函数
     * @param value 参数值
     * @returns {boolean}
     */
    function isNull(value) {
        if (value == null || value.length <= 0) {
            return true;
        }
        return false;
    }

    /*用户名文本移开事件*/
    $("#emailName").on("input", function () {
        var username = $(this).val();
        var number = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!number.test(username)) {
            $("#errorMsg").text("邮箱格式不正确！");
            return;
        } else {
            $("#errorMsg").text("");
        }
    });
    $("#password").on("input", function () {
        var password = $(this).val();
        if (isNull(password)) {
            $("#password_err").text("密码不能为空!");
            return false;
        }

    })

    /**
     * 判断复选中是否被选中
     */
    $("#user_remember_me").change(function () {

        var checkValue = !$("input[type=checkbox]").is(":checked") ? 0 : 1;
        $(this).val(checkValue)
        if (checkValue == 0) {
            $(this).attr("checked", "");
        } else {
            $(this).attr("checked", "checked");
        }
        /*改变复选框的值*/
        $formData.status = checkValue;
    })
    /*判断密码强度*/
    /*表单提交事件*/
    $("#form").submit(function () {
        /*获取数据*/
        $formData.email = $("#emailName").val();
        $formData.password = $("#password").val();
        $formData.status = $("#user_remember_me").val();
        /*非空判断*/
        if (isNull($formData.email)) {
            $("#emailName_err").text("登录失败！邮箱不能为空!");
            return false;
        } else if (isNull($formData.password)) {
            $("#password_err").text("登录失败！密码不能为空!");
            return false;
        }
        if (!isNull($formData.email)) {
            $("#emailName_err").text("");
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
            if (response.data.success == 1) {
                //判断cookie中复选框的状态
                isCheckStatus(response.data.status);
                /*登录成功！*/
                location.href = "http://localhost:5050/items/person/index.html";
            } else {
                $("#errorMsg").text("邮箱或密码错误！");
            }

        })
        return false;
    })
    //假如当前页面的链接是 www.19870125.xyz?name=quhao&uid=1;
//将GET传过来的值转为数组
    function get(keyword) {
        var reg = new RegExp("(^|&)" + keyword + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;   //注意此处参数是中文，解码使用的方法是unescape ，那么在传值的时候如果是中文，需要使用escape('曲浩')方法来编码。
    }

    /**
     * @description: 如果等于1的话，将用户名和密码存放在输入框中 否则消除数据
     * @param status 判断cookie中复选框的状态
     */
    function isCheckStatus(status) {
        if (status === "1") {
            /*去掉两边的引号*/
            var username = getCookie("emailName").replace(/\"/g, "");
            $("#emailName").val(username);
            $("#password").val(getCookie("password"));
        } else {
            $("#emailName").val("");
            $("#password").val("");
        }
        /*设置复选框的值*/
        $("#user_remember_me").val(Number(status));
    }
    /*获取注册过来的数据*/
    var success = getQueryString("success");
    if (!(isNull(success))) {
        /*将数据放到密码框里*/
        var emailName = getCookie("emailName").replace(/\"/g, "");
        var password = getCookie("password");
        $("#emailName").val(emailName);
        $("#username_err").val("请先登录！")
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


