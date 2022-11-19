/*非空函数判断*/
function isNull(value) {
    if (value == null || value.length <= 0 || value === '') {
        return true;
    }
    return false;
}

//禁用“确认重新提交表单”
window.history.replaceState(null, null, window.location.href);
/*时间工具类*/
/*function packDate() {
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
}*/

/**/
layui.use(['table', 'laypage', 'util', 'layer', 'dropdown', 'form'], function () {
    /*修改窗口*/
    var updateOpen = '';
    /*新增窗口*/
    var insertOpen = '';
    var table = layui.table,
        dropdown = layui.dropdown,
        laypage = layui.laypage,
        $ = layui.$,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
    var currentPage = 0;
    var pageSize = 0;
    laydate.render({
        elem: "#pressDate",
        theme: '#393D49',//主题显示
    })
    /* 表单数据*/
    var $formData = {
        pressId: '',
        username: '',
        pressHead: '',
        pressTop: '',
        pressCreateDate: '',
        pressStatus: '',
        motifId: '',
        pressAbstract: '',
        pressContent: ''
    };
    $(function () {
        $('#pressPerson,#pressDate').keydown(function (e) {
            if (e.keyCode === 13) {
                form.submit();
            }
        })
        $('#cancelUpdate').on('click', function () {
            /*关闭窗口*/
            layer.close(updateOpen);
        })
        /*$('#cancelInsert').on('click', function () {
            /!*打开新增页面*!/
            //
            // layer.close(insertOpen);
            return false;
        })*/
    })
    var _this = this;
    /*表格数据*/
    // getTableData();
    /*表格*/
    var tableData = table.render({
        elem: '#table',
        method: 'POST',
        url: 'http://localhost:8080/Press/press/selectByPageAndCondition',
        /*数据格式*/
        parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.count,
                "data": res.data,
            }
        },
        contentType: 'application/json',
        where: $formData,
        /*分页工具栏事件*/
        /*page: true*/  //开启自动分页
        page: {
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']//自定义分页布局
            , limits: [10, 20, 30]
            , first: false //不显示首页
            , last: false //不显示尾页
        },
        limit: 10
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            , layEvent: 'LAYTABLE_TIPS'
            , icon: 'layui-icon-tips'
        }]
        , title: '新闻表'
        , cols: [[
            {type: 'checkbox', fixed: true, width: 80,},
            {
                field: 'pressId',
                type: 'numbers',
                title: '编号',
                width: 80,
                unresize: false,
                sort: true,
                align: 'center'
            }
            , {field: 'pressHead', title: '标题', width: 120, sort: true, align: 'center'}
            , {
                field: 'motifIdStr', title: "新闻分类", width: 150, align: 'center'
            }

            , {field: 'pressCreateDate', title: '发表时间', width: 200, align: 'center'}
            , {field: 'username', title: '创建人', align: 'center', width: 100,}
            , {field: 'pressAbstract', title: '摘要', width: 100, align: 'center'}
            , {field: 'pressContent', title: '内容', width: 120, align: 'center'}
            , {field: 'pressTraffic', title: '访问量', width: 120, align: 'center', sort: true}
            , {
                field: 'pressStatus', title: '状态', width: 150, align: 'center', templet: function (res) {
                    if (res.pressStatus === 1) {
                        return '审批中'
                    } else if (res.pressStatus === 2) {
                        return '已通过'
                    } else {
                        return '已退回'
                    }
                }
            }
            , {
                field: 'pressTop', title: '是否置顶', width: 100, align: 'center', templet: function (res) {
                    if (res.pressTop === 1) {
                        return '已置顶'
                    } else if (res.pressTop === 2) {
                        return '未置顶'
                    }
                }
            },

            {field: 'right', title: '操作', height: 100, width: 120, align: 'center', toolbar: '#barDemo'}
        ]],
        even: true,
        /*渲染完之后的回调函数*/
        done: function (res, currentPage, count) {

        }
    });
    /*头部行内表单*/
    form.on('submit(search)', function (data) {
        var data = data.field;
        console.log(data)
        formSubmit(data);
        /*防止提交表单*/
        return false;
    })

    /*将数据封装*/
    function formDataPack(data) {
        $formData.pressId=data.pressId;
        $formData.username = data.username || data.pressPerson;
        $formData.pressStatus = data.pressStatus;
        $formData.pressCreateDate = data.pressCreateDate;
        $formData.pressTop = data.pressTop;
        $formData.motifId = data.motifId;
        $formData.pressHead = data.pressHead;
        $formData.pressAbstract = data.pressAbstract;
        $formData.pressContent = data.pressContent;
    }

    /*渲染表格*/
    function formSubmit(data) {
        formDataPack(data);
        /*重新渲染表格*/
        tableData.reload({
            method: 'post',
            url: "http://localhost:8080/Press/press/selectByPageAndCondition",
            contentType: 'application/json',
            where: $formData
        })
    }

    var idList = new Array();
    //表格头部工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'insertPress':
                location.href = 'http://localhost:8080/Press/addPress.jsp';
                break;
            case 'deletePressAll':
                if (isNull(checkStatus.data)) {
                    layer.alert("请至少选择一行以上的数据");
                    return false;
                }
                layer.confirm('您确定要删除吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    for (let i = 0; i < checkStatus.data.length; i++) {
                        idList[i] = checkStatus.data[i].pressId
                    }
                    axios({
                        method: "post",
                        url: "http://localhost:8080/Press/press/deletePressAll",
                        data: idList,
                    }).then(function (resp) {
                        if (resp.data === "deleteAllSuccess") {
                            formSubmit('');
                            layer.msg('删除成功！');
                        }
                    })
                }, function () {
                    layer.msg('已撤销删除');
                });
                /*id数组*/
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
    /*修改页面开关监听事件*/
    form.on('switch(filter)', function (data) {
        // console.log(data.elem); //得到checkbox原始DOM对象
        if (data.elem.checked) {
            layer.msg("已设置置顶");
            $('#top').val(1);
            $formData.pressTop = 1;
        } else {
            layer.msg("已取消置顶");
            $('#top').val(2);
            $formData.pressTop = 2;
        }
        // console.log(data.elem.checked); //开关是否开启，true或者false
        // console.log(data.value); //开关value值，也可以通过data.elem.value得到

        // console.log(data.othis); //得到美化后的DOM对象
    });
    var pressId=0;
    var pressStatus=0;
    //监听行工具事件
    table.on('tool(test)', function (obj) {
        /*获取当前点击的行数据*/
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('你真的要删除吗？', function (index) {
                /*发送ajax请求*/
                $.ajax({
                    type: 'get',
                    url: 'http://localhost:8080/Press/press/deletePress?id=' + data.pressId,
                    success: function (response) {
                        if (response === 'deleteSuccess') {
                            /*重新渲染表格*/
                            formSubmit('');
                            layer.msg('删除成功！');
                        }
                    }, error: function (error) {
                        console.log('出错了')
                    }
                })
                layer.close(index);
            }, function () {
                layer.msg('已撤销删除');

            });
        } else if (obj.event === 'edit') {
            updateOpen = layer.open({
                type: 1,
                content: $("#updatePress"),
                title: '编辑新闻',
                maxmin: true,
                anim: 4,
                shade: 0.6,
                fixed: false,
                shadeClose: true,//点击遮罩关闭层
                area: ['960px', '600px'],

                id: 'edit',
            })
            /*if(data.pressStatus===1){
                /!*显示新闻单选框*!/
                $('#status').css('display', 'block');
            }else{
                $('#status').css('display', 'none');
            }*/
            /*$('#through').removeProp('checked');
            $('#back').removeProp('checked');*/
            /*重新渲染表单*/
            $('#pressHead').val(data.pressHead);
            $('#person').val(data.username);
            $('#pressAbstract').val(data.pressAbstract);
            $('#pressContent').val(data.pressContent);
            pressId = data.pressId;
            pressStatus=data.pressStatus;
            $('#top').val(data.pressTop);
            if (data.pressTop === 1) {
                $("#top").prop("checked", true);
                $('.layui-form-switch').addClass('layui-form-onswitch');
            } else {
                $("#top").removeProp("checked");
                $('.layui-form-switch').removeClass('layui-form-onswitch');
            }

            /*设置置顶状态*/
            /*layer.prompt({
                title:"编辑信息",
                formType: 2,
                formType: 1
                , value: data.email
            }, function (value, index) {
                obj.update({
                    email: value
                });
                layer.close(index);
            });*/
        } else if (obj.event === 'query') {
            layer.open({
                type : 1,
                title : ' 新闻详请',
                area : ['960px','580'],
                shade: 0,
                maxmin:true,
                content: $('#pressDetails'),
            })
            /*请空文本*/
            $('.press_content').children('p').text('');
            $('.pressHead').text(data.pressHead)
            $('.pressDate').text(data.pressCreateDate)
            $('.press_username').text(data.username);
            $('.press_abs').text(data.pressAbstract)
            for (const str of data.pressContent.split('\n')) {
                $('.press_content').append('<p>'+str.trim()+'</p>');
            }
            /*var str='';
            for (let i = 0; i < data.pressContent.length; i++) {
                // console.log(data.pressContent[i]
                str+=data.pressContent[i];
                // console.log(i)
                if(str.length%100===0){
                    console.log(1)
                    $('.press_content').append('<p>'+str+'</p>');
                    str='';
                }
            }*/
        }else if(obj.event==='through'){
            /*获取当前行的新闻状态*/
            if (data.pressStatus===2) {
                layer.msg('此新闻已被通过！无法再被通过');
                return false;
            }
            if(data.pressStatus!==2){
                data.pressStatus=2;
            }
            formDataPack(data);
            $.ajax({
                type:'post',
                url:'http://localhost:8080/Press/press/updatePress',
                data:JSON.stringify($formData),
                success : function(data){
                    if(data==='updateSuccess'){
                        layer.msg('已成功通过')
                        formSubmit('')
                    }
                }
            })
        }else if (obj.event === 'rejected') {
            if (data.pressStatus===3) {
                layer.msg('此新闻已被退回！无法再被退回');
                return false;
            }
            if(data.pressStatus!==3){
                data.pressStatus=3;
                /*取消置顶*/
                data.pressTop=2;
            }
            console.log(data)
            formDataPack(data);
            $.ajax({
                type:'post',
                url:'http://localhost:8080/Press/press/updatePress',
                data:JSON.stringify($formData),
                success : function(data){
                    if(data==='updateSuccess'){
                        layer.msg('已被退回')
                        formSubmit('');
                    }
                }
            })
        }
    });

    /*修改新闻状态*/
    form.on('radio(pressStatus)', function (data) {
        /*console.log(data.elem)
        console.log(data.value)
        console.log(data.elem.checked)*/
    })
    /*修改页面表表单提交事件*/
    form.on('submit(update)', function (data) {
        var data = data.field;
        data.pressTop = $('#top').val();
        // console.log(data.pressStatus);
        /*if (isNull(data.pressStatus)) {
            layer.msg('新闻状态不能空');
            return  false;
        }*/
        formDataPack(data);
        $formData.pressId=pressId;
        $formData.pressStatus=pressStatus;
        // return  false;
        /*$formData.pressPerson = data.pressPerson;
        $formData.pressStatus = data.pressStatus;
        $formData.pressCreateDate = data.pressCreateDate;
        $formData.pressTop = data.pressTop;
        $formData.motifId = data.motifId;
        $formData.pressHead = data.pressHead;*/
        // console.log(data)
        // console.log($formData);
        // return  false;
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/Press/press/updatePress',
            data: JSON.stringify($formData),
            success: function (resp) {
                if (resp === 'updateSuccess') {
                    /*渲染表格*/
                    formSubmit('');
                    /*关闭弹窗*/
                    layer.close(updateOpen)
                    layer.msg('修改成功！');
                } else if (resp === 'statusError') {
                    if ($formData.pressStatus === "2") {
                        layer.msg('此新闻已被审批，无法通过');
                    } else {
                        layer.msg('此新闻已被退回，无法退回');
                    }
                }
            },
            error: function () {
                console.log("出错了")
            }
        })
        return false;
    })
});