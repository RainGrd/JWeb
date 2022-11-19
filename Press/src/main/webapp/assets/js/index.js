$(function () {
    $("#nav_ul ul>li>a").hover(function () {
        $(this).addClass("topnav_current");
    }, function () {
        $(this).removeClass("topnav_current");
        /* 保留兄弟原有样式 */
        $("#nav_ul ul>li>a").eq(0).addClass("topnav_current");
    })
    $(".nav a").hover(function () {
        $(this).css({
            color: '#333',
        })
    }, function () {
        $(this).css({
            color: '#666',
        })
    })
})