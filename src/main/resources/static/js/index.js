var baseUrl = window.location.origin;
$(function () {
    // 获取页面地址
    var url = window.location.href;
    // 获取地址中的code码
    var code = url.split("?")[1].split("=")[1];
    $.ajax({
        url:  baseUrl + '/server/getAccessToken?code='+code,
        async: false,
        type: 'GET',
        contentType: "application/json'",
        success: function (result) {
            console.log(result)
        }
    });
});
