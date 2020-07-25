var id = $("#groupId").val();
$(function () {
    
});
// 初始化组数据
function initData() {
    
}

// 添加、编辑组
function addGroup() {
    var url = '';
    if (id == null || id == '') {
        url = "/group/add";
    } else {
        url = "/group/edit";
    }
    var parentId = $("#parent").val() == ''?0:$("#parent").val();
    var groupName = $("#groupName").val();
    var order = $("#order").val();
    var status = $("#status").val();
    var data = {};
    data['parentId'] = parentId;
    data['groupName'] = groupName;
    data['order'] = order;
    data['status'] = status;
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
            initData();
        }
    });
}

// 删除组
function delGroup(id) {
    if (id != null && id != '') {
        $.ajax({
            url: baseUrl + "/group/del?id=" + id,
            async: false,
            type: 'POST',
            contentType: "application/json",
            success: function (result) {
                narn('success', result.message);
                initData();
            }
        });
    }
}