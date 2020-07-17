var flag;
$(function () {
    // 初始化下拉选
    initSelect();
    // 初始化数据
    initData();
    // 点击事件
    clickEvent();
});

/**
 * 初始化下拉选
 */
function initSelect() {
    //获取角色信息
    getRoleInfo();
}

/**
 * 获取角色信息
 */
function getRoleInfo() {
    $.ajax({
        url: "admin/base/roleData",
        async: true,
        type: "POST",
        contentType: "application/json",
        success: function (res) {
            $("#roleId").html(res.data);
        }
    });
}

/**
 * 点击事件
 */
function clickEvent() {
    $("#save_btn").off("click").click(function () {
        // 数据校验
        if ($("#loginName").val().trim() === "") {
            narn("error", "请输入用户名！");
            return false;
        }
        if ($("#loginPassword").val().trim() === "") {
            narn("error", "请输入登录密码！");
            return false;
        }
        if ($("#roleId").val().trim() === "") {
            narn("error", "请选择角色！");
            return false;
        }
        if ($("#userName").val().trim() === "") {
            narn("error", "请输入姓名！");
            return false;
        }
        var data = {};
        data['id'] = $("#id").val().trim();
        data['loginName'] = $("#loginName").val().trim();
        if ($("#loginPassword").val().trim() !== '#*virtual@$password*') {
            data['loginPassword'] = new Base64().encode($("#loginPassword").val().trim());
        }
        data['roleId'] = $("#roleId").val().trim();
        data['userName'] = $("#userName").val().trim();
        data['userContact'] = $("#userContact").val().trim();
        if(data['userContact'] !== null && data['userContact'] !== ""){
            if(!telReg(data['userContact'])){
                narn("error", "联系方式格式不正确！");
                return false;
            }
        }
        data['userAddress'] = $("#userAddress").val().trim();
        data['mail'] = $("#mail").val().trim();
        data['remark'] = $("#remark").val().trim();
        $("#save_btn").prop("disabled", true);
        $.ajax({
            type: "POST",
            url: flag === "add" ? "admin/adminUser/add" : "admin/adminUser/update",
            data: JSON.stringify(data),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: function (res) {
                if (res.code === 1) {
                    narn("success", res.message);
                    $("#modal-default").modal("hide");
                    initTree();
                    initData();
                    enterpriseId = "";
                    flag = "";
                } else {
                    narn("error", res.message);
                }
                reset_btn();
                $("#save_btn").prop("disabled", false);
            }
        });
    });

    $("#del_btn").off("click").click(function () {
        var selectIds = $('#examplePagination').bootstrapTable("getSelections");
        var ids = [];
        for (var i in selectIds) {
            ids.push(selectIds[i].id);
        }
        ids = ids.join(",");
        $("#del_btn").prop("disabled", true);
        $.ajax({
            type: "POST",
            url: "admin/adminUser/batchDel",
            data: ids,
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: function (res) {
                if (res.code === 1) {
                    narn("success", res.message);
                    initData();
                    initTree();
                } else {
                    narn("error", res.message);
                }
                $("#modal-delete").modal("hide");
                reset_btn();
                $("#del_btn").prop("disabled", false);
            }
        });
    });

    $("#search").submit(function (e) {
        e.preventDefault();
        $("#treeDemo").jstree(true).search($("#enter").val());
    });
}

function initData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "admin/adminUser/getList", // 获取数据的地址
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
        idField: "id",
        columns: [{
            field: 'state',
            checkbox: true
        },{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'loginName',
            title: '用户名'
        },{
            field: 'userName',
            title: '姓名'
        },{
            field: 'userContact',
            title: '联系方式'
        },{
            field: 'userAddress',
            title: '联系地址'
        },{
            field: 'mail',
            title: '电子邮件'
        },{
            field: 'order',
            title: '排序号'
        },{
            field: 'order',
            title: '所属部门'
        },{
            field: 'order',
            title: '人员性质'
        },{
            field: 'post',
            title: '主岗'
        },{
            field: 'status',
            title: '状态',
            formatter:function (value) {
                if(value === 0){
                    return "启用";
                }else{
                    return "禁用";
                }
            }
        },{
            field: 'updateTimeStr',
            title: '更新时间'
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            return JSON.stringify(data);
        }
    });
}

/**
 * 新建
 */
function addAdminUser() {
    $("#modal-default").modal("show");
    $("#title_name").text("新建");
    $("#partner_form").get(0).reset();

    //初始化全局变量
    flag = "add";
    $("#id").val("");
}

/**
 * 编辑
 */
function editAdminUser() {
    $("#partner_form").get(0).reset();
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    if (selectIds.length > 1) {
        narn("error", "只能选择一条数据进行编辑！");
        return;
    }
    if (selectIds.length === 0) {
        narn("error", "请选择一条数据！");
        return;
    }
    var id = selectIds[0].id;
    $.ajax({
        type: "POST",
        url: "admin/adminUser/showDetail",
        data: id,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            $("#modal-default").modal("show");
            $("#title_name").text("修改");
            flag = "edit";
            if (res.code === 1) {
                $("#id").val(res.data.id);
                $("#loginName").val(res.data.loginName);
                $("#loginPassword").val(res.data.loginPassword);
                $("#roleId").val(res.data.roleId);
                $("#userName").val(res.data.userName);
                $("#userContact").val(res.data.userContact);
                $("#userAddress").val(res.data.userAddress);
                $("#mail").val(res.data.mail);
                $("#remark").val(res.data.remark);
            }
        }
    });
}

/**
 * 删除
 */
function delAdminUser() {
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    if (selectIds.length === 0) {
        narn("error", "请选择数据！");
        return;
    }
    $("#modal-delete").modal("show");
}

/**
 * 启用
 */
function enableAdminUser() {
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    if (selectIds.length > 1) {
        narn("error", "只能选择一条数据！");
        return;
    }
    if (selectIds.length === 0) {
        narn("error", "请选择一条数据！");
        return;
    }
    var id = selectIds[0].id;
    $.ajax({
        type: "POST",
        url: "admin/adminUser/enable",
        data: id,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            if (res.code === 1) {
                narn("success", res.message);
                initData();
            }else{
                narn("error", res.message);
            }
            reset_btn();
        }
    });
}

/**
 * 禁用
 */
function disableAdminUser() {
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    if (selectIds.length > 1) {
        narn("error", "只能选择一条数据！");
        return;
    }
    if (selectIds.length === 0) {
        narn("error", "请选择一条数据！");
        return;
    }
    var id = selectIds[0].id;
    $.ajax({
        type: "POST",
        url: "admin/adminUser/disable",
        data: id,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            if (res.code === 1) {
                narn("success", res.message);
                initData();
            }else{
                narn("error", res.message);
            }
            reset_btn();
        }
    });
}

/**
 * 重置按钮状态
 */
function reset_btn() {
    $("#del").prop('disabled', false);
    $("#enable").prop('disabled', false);
    $("#disable").prop('disabled', false);
}
