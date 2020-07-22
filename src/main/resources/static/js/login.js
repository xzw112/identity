// 查询参数
$(function () {
    clickEvent();
});

/**
 * 点击事件
 */
function clickEvent() {
    // 登录
    $("#login").off("click").click(function () {
        login();
    });
}

/**
 * 登录
 */
function login() {
    if ($("#loginName").val().trim() === "") {
        narn("error", "请输入账号！");
        return false;
    }
    if ($("#password").val().trim() === "") {
        narn("error", "请输入密码！");
        return false;
    }
    // if ($("#code").val().trim() === "") {
    //     narn("error", "请输入验证码！");
    //     return false;
    // }
    var params = {};
    params['loginName'] = $("#loginName").val();
    params['loginPassword'] = new Base64().encode($("#password").val());
    //params['code'] = $("#code").val().trim();
    $.ajax({
        url: "admin/login/login",
        cache: false,
        async: true,
        data: JSON.stringify(params),
        type: "POST",
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        success: function (res) {
            if (res.code === 1) {
                window.location.href = ctxPath + "admin/menu";
            }else{
                narn("error", res.message);
            }
        }
    });
}

$(document).keydown(function(e){
    if(e.keyCode==13){
        login();
    }
});