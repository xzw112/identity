var baseUrl = window.location.origin;
$(function () {
    // 获取页面地址
    var url = window.location.href;
    // 获取地址中的code码
    var code = url.split("?")[1].split("=")[1];
    $.ajax({
        url:  baseUrl + '/server/getAccessToken?code=' + code,
        async: false,
        type: 'GET',
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            $("#tokenValue").val(result.data.access_token);
            $("#userId").val(result.data.userInfo.id);
        }
    });
});

function redirctLearn() {
    var redirectUrl = "http://192.168.1.120:8083/entry";
    var token =  $("#tokenValue").val();
    var userId = $("#userId").val();
    window.location.href = "http://192.168.1.71:8081/server/toClientIndex?redirectUri=" + redirectUrl + "&token=" + token + "&userId=" + userId;
}
