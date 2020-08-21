$(function () {
    // 初始化数据

    initData();
});

function initData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/customer/clientUser/getOutUserList", // 获取数据的地址
        pagination: true, // 启动分页
        cache: true,
        maintainSelected: true,
        striped: true, // 表格显示条纹
        pageNumber: 1, // 当前第几页
        pageSize: 15, // 每页显示的记录数
        pageList: [15, 20, 25, 50], // 记录数可选列表
        formatNoMatches: function () {  //没有匹配的结果
            return '没有找到匹配的记录';
        },
        sidePagination: "server", // 表示服务端分页
        // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        // 设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        idField: "userId",
        columns: [{
            field: 'state',
            checkbox: true
        },{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'userId',
            title: 'userId',
            visible: false
        },{
            field: 'loginPassword',
            title: 'loginPassword',
            visible: false
        },{
            field: 'loginName',
            title: '登录账号',
        },{
            field: 'userName',
            title: '姓名'
        },{
            field: 'sex',
            title: '性别',
            formatter: function (value) {
                if (value == 1) {
                    return '男'
                } else {
                    return '女'
                }
            }
        },{
            field: 'createTime',
            title: '注册时间'
        },{
            field: 'ipAddr',
            title: '注册IP'
        },{
            field: 'status',
            title: '状态',
            formatter: function (value) {
                if(value == 0){
                    return "启用";
                }else{
                    return "禁用";
                }
            }
        },{
            field: 'operate',
            title: '操作',
            formatter: btnGroup,
            events: {
                'click #userDetail': function (event, value, row, index) {
                    detail(row);
                },
                'click #userEdit': function (event, value, row, index) {
                    edit(row);
                },
                'click #userStatusEdit': function (event, value, row, index) {
                    userStatusEdit(row);
                },
                'click #userDel': function (event, value, row, index) {
                    delUser(row.userId);
                }
            }
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['searchText'] = $("#searchText").val();
            return JSON.stringify(data);
        }
    });
}

// 自定义方法，添加操作按钮
function btnGroup () {
    // data-target="xxx" 为点击按钮弹出指定名字的模态框
    var html =
        '<a href="####"  id="userDetail" style="margin-left:15px" data-toggle="modal" data-target="#editrole" title="详情" shiro:hasPermission="user_out_detail">详情' +
        '</a>' +
        '<a href="####"  id="userEdit" style="margin-left:15px" data-toggle="modal" data-target="#editrole" title="修改" shiro:hasPermission="user_out_edit">修改' +
        '</a>' +
        '<a href="####"  id="userStatusEdit" style="margin-left:15px" data-toggle="modal" data-target="#editrole" title="启用/禁用" shiro:hasPermission="user_out_use">启用/禁用' +
        '</a>' +
        '<a href="####"  id="userDel" style="margin-left:15px" data-toggle="modal" data-target="#editrole" title="删除" shiro:hasPermission="user_out_del">删除' +
        '</a>'
    return html
}

// 搜索
$("#basic-addon2").click(function () {
    initData();
});
// 清空
$("#clearSearch").click(function () {
    $("#searchText").val('');
    initData();
});

function detail(row) {
    $("#myModalLabel").html('用户详情');
    $("#loginName").val(row.loginName);
    $("#loginPassword").val(row.loginPassword);
    $("#nickName").val(row.nickName);
    $("#sex").val(row.sex);
    $("#userOutType").val(row.userOutType);
    $("#sw_value").val(row.status);
    if (row.status == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    $("#saveBtn").hide();
    $("#myModal").modal('show');
}
// 编辑去显示
function edit(row) {
    $("#myModalLabel").html('编辑用户');
    $("#userId").val(row.userId);
    $("#loginName").val(row.loginName);
    $("#loginPassword").val(row.loginPassword);
    $("#userName").val(row.userName);
    $("#nickName").val(row.nickName);
    $("#sex").val(row.sex);
    $("#userOutType").val(row.userOutType);
    $("#sw_value").val(row.status);
    if (row.status == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    $("#saveBtn").show();
    $("#loginName").attr("readonly", true);
    $("#loginPassword").attr("readonly", true);
    $("#myModal").modal('show');
}
// 启用、禁用
function userStatusEdit(row) {
    var url = '';
    if (row.status == 0) {
        url = "/admin/adminUser/updateUserUnUse?id="+row.userId;
    }
    if (row.status == 1) {
        url = "/admin/adminUser/updateUserUse?id="+row.userId;
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
            initData();
        }
    });
}
// 删除用户
function delUser(id) {
    if (id != null && id != '') {
        $("#modal-confirm").modal('show');
        $("#confirm_btn").off('click').click(function () {
            $.ajax({
                url: baseUrl + '/admin/adminUser/batchDel',
                async: false,
                type: 'POST',
                data: id,
                contentType: "application/json",
                success: function (result) {
                    if (result.code == 1) {
                        narn('success', result.message);
                    } else {
                        narn('error', result.message);
                    }
                    initData();
                    $("#modal-confirm").modal('hide');
                }
            });
        });
    }
}

$("#addOutUser").click(function () {
    $("#userId").val('');
    $("#loginName").val('');
    $("#loginPassword").val('');
    $("#nickName").val('');
    $("#sex").val(1);
    $("#userOutType").val('');
    $("#sw_value").val('');
    $("#myModalLabel").html('添加用户');
    $("#myModal").modal('show');
});

$("#saveBtn").click(function () {
    addOrEdit();
});

// 新增
function addOrEdit() {
    var userId = $("#userId").val();
    var data = {};
    var loginName = $("#loginName").val().trim();
    var loginPassword = '';
    if ($("#loginPassword").val().trim() !== '#*virtual@$password*') {
        loginPassword = new Base64().encode($("#loginPassword").val().trim());
    }
    var userName = $("#userName").val().trim();
    var nickName = $("#nickName").val().trim();
    var sex = $("#sex").val().trim();
    var userOutType = $("#userOutType").val().trim();
    var status = $("#sw_value").val().trim();
    if (loginName == null || loginName == '') {
        narn('error', '请输入登录名');
        return;
    }
    if (userId == null || userId == '' ) {
        data['id'] = userId;
        if (loginPassword == null || loginPassword == '') {
            narn('error', '请输入密码');
            return;
        } else {
            data['loginPassword'] = loginPassword;
        }
    } else {
        if(loginPassword != null && loginPassword != ''){
            data['loginPassword'] = loginPassword;
        }
    }
    if (userName == null || userName == '') {
        narn('error', '请输入姓名');
        return;
    }
    if (nickName == null || nickName == '') {
        narn('error', '请输入昵称');
        return;
    }
    data['loginName'] = loginName;
    data['nickName'] = nickName;
    data['userName'] = userName;
    data['sex'] = sex;
    data['userOutType'] = userOutType;
    data['status'] = status;
    data['id'] = userId;
    var url = '';
    if (userId != null && userId != '') {
        url = '/admin/adminUser/updateOutUser';
    } else {
        url = '/admin/adminUser/insertOutUser';
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            if (result.code == 0) {
                narn('error', result.message);
            } else {
                narn('success', result.message);
                initData();
            }
            $("#myModal").modal('hide');
        }
    });
}