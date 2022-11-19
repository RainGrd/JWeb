//禁用“确认重新提交表单”
window.history.replaceState(null, null, window.location.href);
$(function () {
    /*var checkCode = "";
    // 获取对应验证码图片
    $("#changeImg").on('click', function () {
        /!*用于重置时间*!/
        var time = new Date().getMilliseconds();
        $('#verify_img').attr('src', "../items/checkCodeServlet?a" + time);
        console.log("图片的验证码：" + checkCode)
    })*/
    /*判断密码是否符合长度标准*/
    $("#password").on("blur", function () {
        var password = $(this).val();
        if (password.length < 6 || password.length > 16) {
            $("#password_err").text("密码的长度必须在6位到16位中");
            return false;
        } else {
            $("#password_err").text("");
        }
    })
    /*表单数据*/
    var $data = {
        username: "",
        email: "",
        password: "",
        phone: "",
    };

    /*判断用户的格式*/
    $("#userName").on("input", function () {
        let reg = /^(?!^[0-9]*$)^([a-z0-9]{8,16}$)/;
        /*光标离开邮箱名文本框的事件*/
        let str = $(this).val();
        if (!reg.test(str)) {
            $("#userName_err").text("用户必须要包含字母和数字，且不能低于8和16位");
            return false;
        }else{
            $("#userName_err").text("");
        }
    })
    /*光标离开邮箱名文本框的事件*/
    $("#emailName").on("input", function () {
        /*获取emailName的值*/
        var emailName = $(this).val();
        /*判断这个值是否满足邮箱格式*/
        var number = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!number.test(emailName)) {
            $("#emailName_err").text("邮箱必须包含@和.符号");
            return false;
        } else {
            $("#emailName_err").text("");
        }
    })

    /*设置表单提交事件*/
    $("#registered").submit(function () {
        /*获取提交的数据*/
        $data.username = $('#userName').val();
        $data.email = $("#emailName").val();
        $data.password = $("#password").val();
        $data.phone = $("#phone").val();
        var checkValue = $("#checkCode").val();
        /*发送axios请求*/
        axiosFrom($data);
        return false;
    })
    /**
     * 手机号码验证格式
     */
    $("#phone").on("input", function () {
        var regx = /^1(3|4|5|6|7|8|9)\d{9}$/;
        if (!regx.test($(this).val())) {
            $("#tel_err").text("手机号码格式不正确！");
            return false;
        } else {
            $("#tel_err").text("");
        }
    });
})

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
 * axios传递数据
 */
function axiosFrom($formData) {
    if (isNull($formData.username)) {
        $("#userName_err").text("注册失败！用户不能为空!")
    } else if (isNull($formData.email)) {
        $("#emailName_err").text("注册失败！邮箱不能为空!");
        return false;
    } else if (isNull($formData.password)) {
        $("#password_err").text("注册失败！密码不能为空!");
        return false;
    } else if (isNull($formData.phone)) {
        $("#tel_err").text("注册失败！手机号码不能为空!");
        return false;
    }
    if (!isNull($formData.username)) {
        $("#emailName_err").text("");
    } else if (!isNull($formData.password)) {
        $("#password_err").text("");
    } else if (!isNull($formData.phone)) {
        $("#tel_err").text("");
    }
    let reg = /^(?!^[0-9]*$)^([a-z0-9]{8,16}$)/;
    if (!reg.test($formData.username)) {
        $("#userName_err").text("用户必须要包含字母和数字，且不能低于8和16位");
        return false;
    }else{
        $("#userName_err").text("");
    }
    if ($formData.password.length < 6 || $formData.password.length > 16) {
        $("#password_err").text("密码的长度必须在6位到16位中");
        return false;
    } else {
        $("#password_err").text("");
    }
    /*判断这个值是否满足邮箱格式*/
    let number = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!number.test($formData.email)) {
        $("#emailName_err").text("邮箱必须包含@和.符号");
        return false;
    } else {
        $("#emailName_err").text("");
    }
    let regx = /^1(3|4|5|6|7|8|9)\d{9}$/;
    if (!regx.test($formData.phone)) {
        $("#tel_err").text("手机号码格式不正确！");
        return false;
    } else {
        $("#tel_err").text("");
    }
    /*ajax请求方法*/
    $.ajax({
        type:"post",
        url : "http://localhost:8080/Press/user/registered",
        data: JSON.stringify($formData),
        success : function(res){
            if(res==="registeredSuccess"){
                setCookie('username',$formData.username,7);
                setCookie('password',$formData.password,7);
                setCookie('checkboxStatus',1,7)
                location.href = "http://localhost:8080/Press/login.jsp?success=success";
            }else if (res === "email_register"){
                $("#emailName_err").text("邮箱已被注册")
            }else if (res === "phone_register"){
                $("#tel_err").text("手机号码已被注册")
            }
        },error:function(xhr, errorMessage, e) {
            console.log(errorMessage);
        }
    });
    /*axios({
        method: "POST",
        url: "http://localhost:8080/Management-System/registerServlet",
        data: $formData
    }).then(function (resp) {
        var data = resp.data;
        if (data == "success") {
            /!*axios({
                method : "POST",
                url : "http://localhost:5050/items/login.js",
                data : {
                    email:emailName,
                    password : password
                }
            }).then(function(resp) {
                console.log("成功！");
            })*!/
            location.href = "http://localhost:8080/Management-System/login.jsp?success=success";
        }
        if (data == "email_register") {
            $("#emailName_err").text("邮箱已被注册")
        }
        if (data == "phone_register") {
            $("#tel_err").text("手机号码已被注册")
        }
        return false;
    });*/


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
}