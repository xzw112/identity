var currentUserId;
$(function () {
    //初始化数据
    initData();
    //点击事件
    clickEvent();
});

/**
 * 点击事件
 */
function clickEvent() {
    $("#save_btn").off("click").click(function () {
        if($("#userName").val() == null || $("#userName").val().trim() === ""){
            narn("error", "请输入姓名！");
            return false;
        }
        var data = {};
        data['id'] = currentUserId;
        data['userName'] = $("#userName").val();
        data['userContact'] = $("#userContact").val();
        if(data['userContact'] !== null && data['userContact'] !== ""){
            if(!telReg(data['userContact'])){
                narn("error", "联系方式格式不正确！");
                return false;
            }
        }
        data['userAddress'] = $("#userAddress").val();
        data['mail'] = $("#mail").val();
        $("#save_btn").prop("disabled", true);
        $.ajax({
            type: "POST",
            url: "admin/adminUser/updateAdminUserInfo",
            data: JSON.stringify(data),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: function (res) {
                if (res.code === 1) {
                    narn("success", res.message);
                    initData();
                } else {
                    narn("error", res.message);
                }
                $("#save_btn").prop("disabled", false);
            }
        });
    });
}

function initData() {
    $.ajax({
        url: "admin/adminUser/showCurrentUserInfo",
        async: true,
        type: "POST",
        contentType: "application/json",
        success: function (res) {
            var data = res.data;
            currentUserId = data.id;
            $("#enterpriseName").html(data.enterpriseName);
            $("#loginName").html(data.loginName);
            $("#userName").val(data.userName);
            $("#userContact").val(data.userContact);
            $("#userAddress").val(data.userAddress);
            $("#mail").val(data.mail);
        }
    });
}