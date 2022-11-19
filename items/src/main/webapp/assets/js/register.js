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
    $("#password").on("blur",function() {
        var password=$(this).val();
        if(password.length<6 || password.length >16){
            $("#password_err").text("密码的长度必须在6位到16位中");
            return false;
        }else{
            $("#password_err").text("");
        }
    })
    /*表单数据*/
    var $data = {
        username :"",
        email: "",
        password: "",
        phone: "",
    };
    /*光标离开用户名文本框的事件*/
    $("#username").on("input", function () {
        /*获取username的值*/
        var username = $(this).val();
        /*判断这个值是否满足邮箱格式*/
        var number = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!number.test(username)) {
            $("#username_err").text("邮箱必须包含@和.符号");
            return;
        }else{
            $("#username_err").text("");
        }
    })

    /*设置表单提交事件*/
    $("#form").submit(function () {
        var email = $("#emailName").val();
        var password = $("#password").val();
        var phone = $("#phone").val();
        /*获取提交的数据*/
        $data.email = email;
        $data.password = password;
        $data.phone = phone;
        var checkValue = $("#checkCode").val();
        /*发送axios请求*/
        axiosFrom($data);
        return false;
    })
    /**
     *
     */
    $("#phone").on("input",function (){
        var phone=$(this).val();
        var regx=/^1(3|4|5|6|7|8|9)\d{9}$/;
        if(!regx.test(phone)){
            $("#tel_err").text("手机号码格式不正确！");
            return false;
        }else{
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
    if (value.length == 0) {
        return true;
    }
    return false;
}

/**
 * axios传递数据
 */
function axiosFrom($formData){
    console.log($formData)
    if (isNull($formData.email)) {
        $("#username_err").text("注册失败！邮箱不能为空!");
        return false;
    } else if (isNull($formData.password)) {
        $("#password_err").text("注册失败！密码不能为空!");
        return false;
    } else if (isNull($formData.phone)) {
        $("#tel_err").text("注册失败！手机号码不能为空!");
        return false;
    }
    if (!isNull($formData.username)) {
        $("#username_err").text("");
    } else if (!isNull($formData.password)) {
        $("#password_err").text("");
    } else if (!isNull($formData.phone)) {
        $("#tel_err").text("");
    }
    axios({
        method: "POST",
        url: "http://localhost:5050/items/registerServlet",
        data: $formData
    }).then(function (resp) {
        var data = resp.data;
        if (data == "success") {
            /*axios({
                method : "POST",
                url : "http://localhost:5050/items/login.js",
                data : {
                    email:emailName,
                    password : password
                }
            }).then(function(resp) {
                console.log("成功！");
            })*/
            location.href = "http://localhost:5050/items/login.html?success=success";
        }
        if (data == "email_register"){
            $("#username_err").text("邮箱已被注册")
        }
        if (data == "phone_register") {
            $("#tel_err").text("手机号码已被注册")
        }
    });
    return false;
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
}