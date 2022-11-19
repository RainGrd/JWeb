//禁用“确认重新提交表单”
window.history.replaceState(null, null, window.location.href);

/**
 * 删除方法
 * @param attr
 */
function del(attr) {
    layer.confirm('您确定要删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        /*使用ajax进行异步请求*/
        /*var xhr=new XMLHttpRequest();
        xhr.open("POST",attr);
        xhr.send(JSON.stringify($delData));
        layer.msg("删除成功！",{icon : 1})
        /!*4.获取服务端响应到客户端的数据*!/
        xhr.onreadystatechange=function (){
            if(xhr.readyState === 4 && xhr.status === 200){

            }
        }*/
        location.href = attr;
    });
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

/**
 * 数组去重
 * @param arr
 * @returns {any[]}
 */
const unique = (arr) => {
    // 遍历arr，把元素分别放入tmp数组(不存在才放)
    var tmp = new Array();
    for (var i in arr) {
        //该元素在tmp内部不存在才允许追加
        if (tmp.indexOf(arr[i]) === -1) {
            tmp.push(arr[i]);
        }
    }
    return tmp;
}
/**
 * 用于将数组存放到cookie里面
 * @param name cookie名
 * @param value 值
 * @param day 存活时间
 */
const setCookie = (name, value, day) => {
    const date = new Date();//用来设置过期时间用的，获取当前时间加上传进来的iDay就是过期时间
    date.setDate(date.getDate() + day);
    const cookieObj = [];
    if (getCookie(name) !== null) {
        if (getCookie(name).split(',').length >= 1) {
            for (let i = 0; i < getCookie(name).split(',').length; i++) {
                cookieObj.push(getCookie(name).split(',')[i]);
            }
        } else {
            cookieObj.push(getCookie(name).split(','));
        }
    }
    cookieObj.push(value);
    let arr = unique(cookieObj);//数组去重
    document.cookie = name + '=' + arr + ';expires=' + date;
}
/**
 * @description: 用于获取存放在cookie里面的数据
 * @param user_name 索引
 */
const getCookie = (name) => {
    if (document.cookie.length > 0) {
        let start = document.cookie.indexOf(name + "=")
        if (start !== -1) {
            start = start + name.length + 1;
            let end = document.cookie.indexOf(";", start);
            if (end === -1) end = document.cookie.length
            return unescape(document.cookie.substring(start, end))
        }
    }
    return null;
}
/**
 * 删除cookie
 * @param name
 */
const removeCookie = (name) => {
    setCookie(name, 1, -1); //-1就是告诉浏览器数据已经过期，浏览器就会立刻去删除cookie
};

//自定义过滤函数
function trimSpace(array) {
    for (var i = 0; i < array.length; i++) {
        //这里为过滤的值
        if (array[i] == " " || array[i] == null || typeof (array[i]) == "undefined" || array[i] == '  ') {
            array.splice(i, 1);
            i = i - 1;
        }
    }
    return array;
}

$(function () {
    $("#selectBrand #btn").keydown(function (e) {
        if (e.keyCode === 13) {
            $("#thisForm").submit();
        }
    })
    /*修改按钮点击事件*/
    /*$(".delete").on("click", function () {
        var attr = $(this).siblings('.del').attr("href");
        del(attr);
    })*/
    $(".delete").on("click", function () {
        del("http://localhost:8080/brand-demo/deleterServlet?id=" + $(this).siblings().attr("name"));
    })
    /**
     *
     * @param val
     */
    Array.prototype.remove = function (val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    }
    /*存放复选框状态的数组*/
    var checkBoxStatus = new Array();
    /*根据存放的cookie名进行取值*/
    $(".isAll").on("click", function () {
        $("input[type=checkbox]").attr("checked", "checked");
        /*通过遍历获取以CheckBox为开头的name的属性值*/
        $("input[name^=checkbox]:checked").each(function (index, element) {
            checkBoxStatus.push($(this).attr("name").match(/\d+/g))
        })
        setCookie("status", checkBoxStatus, 7);
    });
    /*将选中的状态存放到cookie里面*/
    $(".cancelAll").on("click", function () {
        $("input[type=checkbox]").attr("checked", null);
        /*清空*/
        checkBoxStatus.length = 0;
        setCookie("status", null, -1);
    })
    //在页面关闭或者跳转的时候调用
    $("input[type=checkbox]").change(function () {
        /*如果复选框被选中了那么添加他的name*/
        var thisValue = parseInt($(this).attr("name").match(/\d+/g));
        if ($(this).is(":checked")) {
            /*插入数组后面*/
            checkBoxStatus.push(thisValue);
        } else {
            for (let i = 0; i < checkBoxStatus.length; i++) {
                /*进行比较*/
                if (checkBoxStatus[i] === thisValue) {
                    /*移除元素*/
                    checkBoxStatus.remove(checkBoxStatus[i]);
                }
            }
        }
        setCookie("status", checkBoxStatus, 7);
    });

    $(".batchOperation").on("click", function () {
        if (checkBoxStatus.length === 0) {
            layer.alert("对不起，请至少选中一行数据")
            return false;
        }
        del("http://localhost:8080/brand-demo/deleterServlet?description=" + checkBoxStatus);
    })
    layui.use('layer', function () {
//触发事件
        var active = {
            update: function () {
                layer.open({
                    title: '修改页面',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['1000px', '580px'],
                    content: $('#update'),
                    maxmin: true,
                    // minStack: true, //最小化堆叠在左下角
                    id: 'page1', //定义 ID，防止重复弹出
                });
            }
        }
        $('.update').on('click', function () {
            /*判断这一行的复选框是否被选中*/
            if (checkBoxStatus.length > 0) {
                layer.alert("不可修改！请取消勾选状态！");
                return false;
            }
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
            /*获取表格的数据*/
            var brandName = $(this).parent().siblings().eq(2).text();
            var companyName = $(this).parent().siblings().eq(3).text();
            var description = $(this).parent().siblings().eq(4).text();
            var ordered = $(this).parent().siblings().eq(5).text();
            var brandId = $(this).attr("name");
            if (brandName === null || companyName === null || description == null || ordered == null) {
                $("#err_msg").text("以上内容一项都不能为空")
                return false;
            }
            $("#brandId").val(brandId)
            $("#brandName").val(brandName);
            $("#companyName").val(companyName);
            $("#ordered").val(ordered);
            $("#description").val(description);
        });
    });
    /*$("#upload").on("click",function() {
        layui.use([ "element", "laypage", "layer", "upload"], function() {
            var element = layui.element;
            var laypage = layui.laypage;
            var layer = layui.layer;
            var upload = layui.upload;//主要是这个
            layui.upload.render({
                elem: "#upload",//导入id
                url: "http://localhost:8080/brand-demo/uploadServlet",
                size: '3072',
                accept: "file",
                exts: 'xls|xlsx|xlsm|xlt|xltx|xltm',
                done: function (result) {
                    if (result.status == 0) {
                        parent.layui.table.reload('LAY-app-emp-list');
                    }
                    if (result.message != null) {
                        parent.layui.table.reload('LAY-app-emp-list');
                        layer.msg(result.message)
                    }
                }
            });
        });
    });*/
})