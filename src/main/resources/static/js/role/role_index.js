var flag;
$(function () {
    // 初始化数据
    initData();
    // 绑定事件
    clickEvent();
});

/**
 * 点击事件
 */
function clickEvent() {
    $("#save_btn").off("click").click(function () {
        // 数据校验
        if ($("#roleName").val().trim() === "") {
            narn("error", "请输入角色名称！");
            return false;
        }
        var data = {};
        data['id'] = $("#id").val().trim();
        data['roleName'] = $("#roleName").val().trim();
        data['remark'] = $("#remark").val().trim();
        $("#save_btn").prop("disabled", true);
        $.ajax({
            type: "POST",
            url: flag === "add" ? "admin/role/add" : "admin/role/update",
            data: JSON.stringify(data),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: function (res) {
                if (res.code === 1) {
                    narn("success", res.message);
                    $("#modal-default").modal("hide");
                    initData();
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
            url: "admin/role/batchDel",
            data: ids,
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: function (res) {
                if (res.code === 1) {
                    narn("success", res.message);
                    initData();
                } else {
                    narn("error", res.message);
                }
                $("#modal-delete").modal("hide");
                reset_btn();
                $("#del_btn").prop("disabled", false);
            }
        });
    });

    $('#examplePagination').on('check.bs.table uncheck.bs.table', function () {
        var status = getStatusSelections();
        if(status[0] == null){
            $("#enable").prop('disabled', false);
            $("#disable").prop('disabled', false);
        }else{
            if (status[0] === 0) {
                $("#disable").prop('disabled', !$('#examplePagination').bootstrapTable('getSelections').length);
                $("#enable").prop('disabled', true);
            } else {
                $("#disable").prop('disabled', true);
                $("#enable").prop('disabled', !$('#examplePagination').bootstrapTable('getSelections').length);
            }
        }
    });

    $("#search").submit(function(e) {
        e.preventDefault();
        $("#permissionTree").jstree(true).search($("#enter").val());
    });
}

function initData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "admin/role/getList", // 获取数据的地址
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
            field: 'roleName',
            title: '角色名称'
        },{
            field: 'remark',
            title: '角色说明'
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
function addRole() {
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
function editRole() {
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
        url: "admin/role/showDetail",
        data: id,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            $("#modal-default").modal("show");
            $("#title_name").text("修改");
            flag = "edit";
            if (res.code === 1) {
                $("#id").val(res.data.id);
                $("#roleName").val(res.data.roleName);
                $("#remark").val(res.data.remark);
            }
        }
    });
}

/**
 * 删除
 */
function delRole() {
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
function enableRole() {
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
        url: "admin/role/enable",
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
function disableRole() {
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
        url: "admin/role/disable",
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
    $("#enable").prop('disabled', false);
    $("#disable").prop('disabled', false);
}

/**
 * 权限设置
 */
function setAuth() {
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    if (selectIds.length > 1) {
        narn("error", "只能选择一条数据！");
        return;
    }
    if (selectIds.length === 0) {
        narn("error", "请选择一条数据！");
        return;
    }
    var roleId = selectIds[0].id;
    $("#permission_modal").modal("show");
    $.ajax({
        type: "POST",
        url: "admin/permission/listPermissionIds",
        data: roleId,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            loadPermissionTree(res.data);
        }
    });

    $("#save_permission").off("click").click(function () {

        //获取所有选中节点和半选中节点
        var checkedNodes = $('#permissionTree').jstree("get_all_checked");
        if (checkedNodes.length > 0) {
            checkedNodes.pop();
            var data = {};
            // 角色id
            data['roleId'] = roleId;
            // 权限id
            data['permissionIds'] = checkedNodes;
            $.ajax({
                type: "POST",
                url: "admin/role/saveRolePermission",
                data: JSON.stringify(data),
                async: true,
                contentType: 'application/json;charset=UTF-8',
                success: function (res) {
                    if (res.code === 1) {
                        narn("success", res.message);
                        $("#permission_modal").modal("hide");
                    } else {
                        narn("error", res.message);
                    }
                }
            });
        } else {
            narn("error", "请选择权限！");
        }
    });
}

/**
 * 加载权限树
 * @param permission
 */
function loadPermissionTree(permission) {
    $.ajax({
        url: "admin/permission/getPermissionTree",
        async: true,
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        success: function (res) {
            var data = res.data;
            if (data.length > 0) {
                $('#permissionTree').jstree("destroy");
                // 不显示节点前的图标
                $.jstree.defaults.core.themes.icons = false;
                $.jstree.defaults.core.expand_selected_onload = false;
                $('#permissionTree').data('jstree', false).empty();
                $("#permissionTree").jstree({
                    'core': {
                        "check_callback": true,
                        'data': data
                    },
                    "checkbox": {
                        // 父子节点checkbox关联
                        "three_state": true,
                        "whole_node": true,
                        "tie_selection": true
                    },
                    "plugins": ["search", "checkbox"]
                }).on('check_node.jstree', function (e, data) {
                }).on("loaded.jstree", function (event, data) {
                    //这两句化是在loaded所有的树节点后，然后做的选中操作，这点是需要注意的，loaded.jstree 这个函数
                    //取消选中，然后选中某一个节点
                    $("#permissionTree").jstree("deselect_all", true);
                    var ids = permission.split(",");
                    for (var i = 0; i < ids.length; i++) {
                        $('#permissionTree').jstree('check_node',ids[i],true);
                    }
                })
            }
        }
    });
}