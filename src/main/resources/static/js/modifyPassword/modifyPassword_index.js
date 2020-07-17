$(function () {
    //点击事件
    clickEvent();
});

/**
 * 点击事件
 */
function clickEvent() {
    // 修改密码
    $("#modify").off("click").click(function () {
        if ($("#oldPassword").val() == null || $("#oldPassword").val().trim() === "") {
            narn("error", "请输入当前密码！");
            return;
        }
        if ($("#newPassword").val() == null || $("#newPassword").val().trim() === "") {
            narn("error", "请输入新密码！");
            return;
        }
        if ($("#confirmedNewPassword").val() == null || $("#confirmedNewPassword").val().trim() === "") {
            narn("error", "请输入确认密码！");
            return;
        }
        if ($("#newPassword").val() !== $("#confirmedNewPassword").val()) {
            narn("error", "两次输入的密码不一致！");
            return;
        }
        var params = {};
        params['loginPassword'] = new Base64().encode($("#oldPassword").val());
        params['newPassword'] = new Base64().encode($("#newPassword").val());
        $("#modify").prop("disabled", true);
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
                    narn("success", res.message);
                }else{
                    narn("error", res.message);
                }
                $("#modify").prop("disabled", false);
            }
        });
    });
}
