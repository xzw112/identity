var clientId = '';
$(function () {
    $("#appDiv").hide();
    clientId = $("#clientId").val();
    var action = $("#action").val();
    if (action == 'detail') {
        $("#save_btn").hide();
    }
    if (clientId != null && clientId != '') {
        $("#appDiv").show();
        $.ajax({
            url: baseUrl + '/client/getClientDetail?clientId=' + clientId,
            async: false,
            type: 'POST',
            contentType: "application/json",
            success: function (result) {
                console.log(result);
                var data = result.data;
                $("#appName").val(data.clientName);
                $("#appDomain").val(data.clientDomain);
                $("#photo").val(data.imgUrl);
                $("#photo_img").attr("src", data.imgUrl);
                $("#appId").html(data.clientId);
                $("input[name='appType']:checked").val(data.clientType);
                $("#exampleFormControlTextarea1").val(data.redirectUrl);
                if (data.status == 1) {
                    $("[name='my-checkbox']").bootstrapSwitch('state', true);
                }
                if (data.status == 0) {
                    $("[name='my-checkbox']").bootstrapSwitch('state', false);
                }

            }
        });
    }
});
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
    var url = '';
    if (clientId == '' || clientId == null) {
        url = '/client/add'
    }
    if (clientId != null && clientId != '') {
        data['clientId'] = clientId;
        url = '/client/edit'
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
            window.location.href = baseUrl + "/admin/applicationList"
        }
    });
});
// 图标上传
$("#photo_img").click(function () {
    $("#photo_file").click();
    uploadImg($("#photo_file"));
});

// 取消
$("#cancel").click(function () {
    window.location.href = baseUrl + "/admin/applicationList"
});

