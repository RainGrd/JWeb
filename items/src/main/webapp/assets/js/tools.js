$(function() {
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