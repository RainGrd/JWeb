$(function () {
    var currentPage = 1;
    var pageSize = $('.totalCount').val();
    /*提交数据*/
    var $data = {
        id: 1,
        brandName: 1,
        companyName: "城市",
        description: '666',
        ordered: 1,
        status: 1,
    }
    /*初始化*/
    selectBrandAll();
    /*下拉列表选中事件*/
    $(".totalCount").change(function () {
        /*删除表格数据*/
        currentPage = 1;
        pageSize = $(this).val();
        selectBrandAll();
    })

    /*下一页点击事件*/
    $('.down').on('click', function () {
        console.log(Number($('.index').text()) >= Number($('.page').text()))
        if (Number($('.index').text()) >= Number($('.page').text())) {
            alert("没有数据了");
            return false;
        }
        currentPage++;
        selectBrandAll();
    })
    /*上一页*/
    $('.up').on('click', function () {
        currentPage--;
        if (currentPage <= 0) {
            alert("到头了");
            return false
        }
        selectBrandAll();
    })
    /*最后一页*/
    $('.last').on('click', function () {
        currentPage = $('.page').text();
        selectBrandAll();
    })
    /*首页*/
    $('.home').on('click', function () {
        currentPage = 1;
        pageSize = $('.totalCount').val();
        selectBrandAll();
    })
    /*行内表单提交事件*/
    $('#form').submit(function () {
        currentPage = 1;
        /*获取数据*/
        $data.brandName = $('#brandName').val();
        $data.companyName = $('#companyName').val();
        $data.status = $('#status').val();
        /*发送ajax请求*/
        selectBrandAll();
        return false;
    });
    /**
     * 查询所有
     */
    var k = 0;
    var tableData = null;
    function selectBrandAll() {
        $('.tableData').remove();
        console.log($data)
        var page = '';
        $.ajax({
            type: "post",
            url: "http://localhost:8080/brand-Test/brand/selectBrandAll?currentPage="+currentPage+"&pageSize="+pageSize,
            dataType: "json",
            data: $data,
            success: function (data) {
                console.log(data)
                /*var totalCount = data.totalCount;
                tableData = data.data;
                var i = 0
                /!*清空表格内容*!/
                for (let j = 0; j < tableData.length; j++) {
                    $('table').append("<tr align='center' class='tableData'>" +
                        "<td>" + (++i) + "</td>" +
                        "<td>" + tableData[j].brandName + "</td>" +
                        "<td>" + tableData[j].companyName + "</td>" +
                        "<td>" + tableData[j].description + "</td>" +
                        "<td>" + tableData[j].ordered + "</td>" +
                        "<td>" + tableData[j].statusStr + "</td>" +
                        "<td><button>查看</button><button class='delete' name=" + tableData[j].id + ">删除</button></td>" +
                        "</tr>");
                }
                page = Math.round(totalCount / pageSize);
                if (totalCount.toString().charAt(totalCount.toString().length - 1) > 5) {
                    if (page / 5 !== 0) {
                        if ((totalCount / 5).toString().charAt((totalCount / 5).length - 1) / 5 >= 5) {
                            page = (totalCount / 5).toString().charAt((totalCount / 5).length - 1);
                        } else {
                            page++;
                        }
                    }
                    if (totalCount < pageSize) {
                        page--;
                    }
                }
                if (page === 0) {
                    page++;
                }
                if (totalCount === 1) {
                    page = totalCount;
                } else if (totalCount === 0) {
                    page=totalCount;
                    currentPage = 0;
                }
                $('.page').text(page)
                /!*显示数据*!/
                $('.count').text(totalCount);
                $('.index').text(currentPage);
                $('.delete').on("click", function () {
                    var flag = confirm("你确定要删除吗？")
                    /!*获取当前点击行的Id*!/
                    $data.id = $(this).attr("name");
                    if (flag) {
                        /!*发送ajax请求*!/
                        $.ajax({
                            type: "POST",
                            url: "http://localhost:8080/brand-Test/brand/deleteBrand",
                            data: JSON.stringify($data),
                            success: function (resp) {
                                console.log(tableData.length)
                                if (tableData.length === 1) {
                                    currentPage--;
                                    // pageSize=$('.page').val();
                                }
                                if(Number($('.index').text())<=1){
                                    currentPage=1;
                                }
                                selectBrandAll();
                            }, error: function (resp) {
                                console.log("出错了")
                            }
                        })
                    }
                })*/
            }
        })
    }
})