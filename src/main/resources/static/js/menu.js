// 查询参数
$(function () {
    $.ajax({
        url: "admin/menu/getUserInfo",
        cache: false,
        async: true,
        type: "POST",
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        success: function (res) {
            var data = res.data;
            $("#userId").text(data.id);
            $("#userName").text(data.userName);
        }
    });
    clickEvent();
});

/**
 * 点击事件
 */
function clickEvent() {
    // 修改密码
    $("#modify").off("click").click(function () {
        if ($("#newPassword").val() !== $("#confirmedNewPassword").val()) {
            narn("error", "两次输入的密码不一致！");
            return;
        }
        var params = {};
        params['loginPassword'] = new Base64().encode($("#oldPassword").val());
        params['newPassword'] = new Base64().encode($("#newPassword").val());
        params['id'] = $("#userId").text();
        $.ajax({
            url: "admin/adminUser/modifyPassword",
            cache: false,
            async: true,
            data: JSON.stringify(params),
            type: "POST",
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            success: function (res) {
                if(res.code === 1){
                    window.location.href = ctxPath + "admin/login";
                }else{
                    narn("error", res.message);
                }
            }
        });
    });

    $("#realLogout").off("click").click(function () {
        window.location.href = ctxPath + "admin/logout";
    });
}

/**
 * 登出
 */
function logout() {
    // 登出
    $("#modal-logout").modal("show");
}

