// 保存应用
$("#save_btn").click(function () {
    var appName = $("#appName").val();
    var appDomain = $("#appDomain").val();
    var imgUrl = $("#photo").val();
    var appType = $("input[name='appType']:checked").val();
    var redirectUrl = $("#exampleFormControlTextarea1").val();
    var status = $("#sw_value").val();
    var data = {};
    data['clientName'] = appName;
    data['clientDomain'] = appDomain;
    data['imgUrl'] = imgUrl;
    data['clientType'] = appType;
    data['redirectUrl'] = redirectUrl;
    data['status'] = status;
    $.ajax({
        url: baseUrl + "/client/add",
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
        }
    });
});
// 图标上传
$("#photo_img").click(function () {
    $("#photo_file").click();
    uploadImg($("#photo_file"));
});

