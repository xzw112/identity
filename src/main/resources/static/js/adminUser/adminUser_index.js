var flag;
var selectArr = new Array();
var departmentId = '';
$(function () {
    // 初始化下拉选
    initSelect();
    // 初始化数据
    initData();
    // 点击事件
    clickEvent();
    // 加载岗位信息
    loadPostData(null);
    // 加载部门树
    initTree()
});
// tab 切换
$(".tag li").click(function () {
    $(this).addClass("active").siblings().removeClass("active");
    var tagactive = $(this).index();
    if (tagactive == 0) {
        $("#userType").val(0);
    }
    if (tagactive == 1) {
        $("#userType").val(1);
    }
    $(".part .item").eq(tagactive).show().siblings().hide();
    initData();
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
        url: baseUrl + "/admin/base/roleData",
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
        if (data['userContact'] !== null && data['userContact'] !== "") {
            if (!telReg(data['userContact'])) {
                narn("error", "联系方式格式不正确！");
                return false;
            }
        }
        data['userAddress'] = $("#userAddress").val().trim();
        data['mail'] = $("#mail").val().trim();
        data['remark'] = $("#remark").val().trim();
        data['sort'] = $("#sort").val().trim();
        data['department'] = $("#selectDeptIds").val();
        data['post'] = $("#postId").val();
        data['isLeave'] = $("#isLeave").val().trim();
        data['nature'] = $("#nature").val().trim();
        var groupId = $("#selectGroupIds").val();
        var tagName=[];
        var tagId=[];
        $("#deputyPostId").find("option:selected").each(function () {
            tagName.push($(this).text());
            tagId.push($(this).val());
        });
        data['deputyPost'] = tagId.join(",");
        data['groupId'] = groupId;
        $("#save_btn").prop("disabled", true);
        $.ajax({
            type: "POST",
            url: flag === "add" ? baseUrl + "/admin/adminUser/add" : baseUrl + "/admin/adminUser/update",
            data: JSON.stringify(data),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: function (res) {
                if (res.code === 1) {
                    narn("success", res.message);
                    $("#modal-default").modal("hide");
                    initTree();
                    initData();
                    departmentId = "";
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
            url: baseUrl + "/admin/adminUser/batchDel",
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
        url: baseUrl + "/admin/adminUser/getList", // 获取数据的地址
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
        queryParamsType: "undefined",
        idField: "id",
        columns: [{
            field: 'state',
            checkbox: true
        }, {
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'loginName',
            title: '用户名'
        }, {
            field: 'userName',
            title: '姓名'
        }, {
            field: 'userContact',
            title: '联系方式'
        }, {
            field: 'userAddress',
            title: '联系地址'
        }, {
            field: 'mail',
            title: '电子邮件'
        }, {
            field: 'sort',
            title: '排序号'
        }, {
            field: 'departmentName',
            title: '所属部门'
        }, {
            field: 'nature',
            title: '人员性质',
            formatter: function (value) {
                if (value == 0) {
                    return '正式编';
                } else {
                    return '社会化'
                }
            }
        }, {
            field: 'postName',
            title: '主岗'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value) {
                if (value === 0) {
                    return "启用";
                } else {
                    return "禁用";
                }
            }
        }, {
            field: 'updateTimeStr',
            title: '更新时间'
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['departmentId'] = departmentId;
            data['userType'] = 1;
            data['isLeave'] = $("#userType").val();
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
    var data = {};
    var id = selectIds[0].id;
    data['userId'] = id;
    data['userType'] = 1;
    $.ajax({
        type: "POST",
        url: baseUrl + "/admin/adminUser/showDetail",
        async: true,
        data: JSON.stringify(data),
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            $("#modal-default").modal("show");
            $("#title_name").text("修改");
            flag = "edit";
            if (res.code === 1) {
                console.log(res);
                $("#id").val(res.data.id);
                $("#loginName").val(res.data.loginName);
                $("#loginPassword").val(res.data.loginPassword);
                $("#roleId").val(res.data.roleId);
                $("#userName").val(res.data.userName);
                $("#userContact").val(res.data.userContact);
                $("#userAddress").val(res.data.userAddress);
                $("#mail").val(res.data.mail);
                $("#remark").val(res.data.remark);
                $("#selectDept").val(res.data.departmentName);
                $("#selectDept").val(res.data.departmentName);
                $("#selectDeptIds").val(res.data.departmentId);
                $("#sort").val(res.data.sort);
                $("#selectGroupIds").val(res.data.groupId);
                $("#selectGroup").val(res.data.groupName);
                loadPostData(res.data.deputyPostId);
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
        url: baseUrl + "/admin/adminUser/enable",
        data: id,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            if (res.code === 1) {
                narn("success", res.message);
                initData();
            } else {
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
        url: baseUrl + "/admin/adminUser/disable",
        data: id,
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            if (res.code === 1) {
                narn("success", res.message);
                initData();
            } else {
                narn("error", res.message);
            }
            reset_btn();
        }
    });
}

// 离职
function leaveUser() {
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    var id = [];
    if (selectIds.length > 0) {
        for (var i = 0; i < selectIds.length; i++) {
            id.push(selectIds[i].id)
        }
        if (id.length > 0) {
            $.ajax({
                type: "POST",
                url: baseUrl + "/admin/adminUser/updateUserLeave",
                data: JSON.stringify(id),
                async: true,
                contentType: 'application/json;charset=UTF-8',
                success: function (res) {
                    console.log(res);
                    narn('success', res.message)
                    initData();
                }
            });
        }
    } else {
        narn('error', '请选择要离职的用户');
    }
}
// 还原
function unLeaveUser() {
    var selectIds = $('#examplePagination').bootstrapTable("getSelections");
    var id = [];
    if (selectIds.length > 0) {
        for (var i = 0; i < selectIds.length; i++) {
            id.push(selectIds[i].id)
        }
        if (id.length > 0) {
            $.ajax({
                type: "POST",
                url: baseUrl + "/admin/adminUser/updateUserUnLeave",
                data: JSON.stringify(id),
                async: true,
                contentType: 'application/json;charset=UTF-8',
                success: function (res) {
                    console.log(res);
                    narn('success', res.message)
                    initData();
                }
            });
        }
    } else {
        narn('error', '请选择要还原的用户');
    }
}

/**
 * 重置按钮状态
 */
function reset_btn() {
    $("#del").prop('disabled', false);
    $("#enable").prop('disabled', false);
    $("#disable").prop('disabled', false);
}

/**
 * 加载部门数据
 */

function loadDepartmentData() {
    $.ajax({
        url: baseUrl + '/customer/department/getDepartmentTreeList',
        method: 'POST',
        contentType: 'application/json',
        async: false,
        success: function (result) {
            var departments = result.data;
            if (departments.length > 0) {
                $("#departmentTree").jstree({
                    'plugins': ["search", "themes", "types", "line"],
                    'types': {
                        'default': {
                            'icon': true
                        },
                    },
                    "checkbox": {
                        tie_selection: true,
                        keep_selected_style: true,
                        whole_node: true
                    },
                    'core': {
                        'data': departments,
                        'themes': {
                            "icons": true,	//默认图标
                            "theme": "classic",
                            "dots": true,
                            "stripes": true,	//增加条纹
                        },	//关闭文件夹样式
                        'dblclick_toggle': true,   //允许tree的双击展开,默认是true
                        "multiple": false, // 单选
                        "check_callback": true
                    }
                });
            }
        }
    });
}

/**
 * 加载岗位信息
 */
function loadPostData(tagCodes) {
    var data = {};
    $.ajax({
        url: baseUrl + '/customer/post/getPostList',
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        async: false,
        success: function (result) {
            var option_="";
            $.each(result.rows, function (i, item) {
                option_+="<option value='"+item.id+"' data-id='"+item.id+"'>"+item.postName+"</option>";
            });
            $("#deputyPostId").html(option_);
            $("#searchUserPost").append(option_);
            $("#postId").html(option_);
            if (tagCodes != null){
                tagCodes=tagCodes.split(",");
                $("#deputyPostId").selectpicker('val', tagCodes);//给多选selectpicker赋值，array是数组
            }
            $("#deputyPostId").selectpicker('refresh');
            $("#deputyPostId").selectpicker('render');

        }
    });
}

/**
 * 加载单位树
 * @returns
 */
function initTree() {
    var data = {};
    data['status'] = 0;
    $.ajax({
        url: baseUrl + '/customer/department/getDepartmentTreeList',
        async: true,
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (res) {
            var data = res.data;
            if (data.length > 0) {
                $('#treeDemo').data('jstree', false).empty();
                $("#treeDemo").jstree({
                    'core': {
                        "check_callback": true,
                        'data': data
                    },
                    "plugins": ["search"],
                    "checkbox": {
                        "whole_node": true,
                        "tie_selection": true
                    }
                });
                // 展开节点
                $("#treeDemo").on("loaded.jstree", function (event, data) {
                    data.instance.open_all(); //opens all nodes in the container
                });
                $('#treeDemo').on("changed.jstree", function (e, data) {
                    jsTreeOnClick(data);
                });
            }
        }
    });
}

/**
 * 树点击事件
 * @param data
 */
function jsTreeOnClick(data) {
    var node = data.node.original;
    departmentId = node.id;
    initData();
}

/**
 * 部门展示
 */
function toShowDepartment() {
    var selectDeptIds = $("#selectDeptIds").val();
    var selectDeptName = $("#selectDept").val();
    if (selectDeptIds != '' && selectDeptName != '') {
        var ids = selectDeptIds.split(',');
        var names = selectDeptName.split(',');
        selectArr = [];
        for (var i = 0; i < ids.length; i++) {
            selectArr.push({
                name: names[i],
                id: ids[i]
            });
        }
    }
    rewriteSelect(selectArr);
    $("#treePeople li").removeClass("active");
    $("#modal-department-select").modal("show");
    loadDepartmentData();
}

var fistSelectArr = new Array();
$("#departmentTree").on("changed.jstree", function (e, data) {
    if (data.selected.length > 0) {
        fistSelectArr = [];
        fistSelectArr.push({
            name: data.node.text,
            id: data.selected
        })
    }
});
//添加选中人员
$(".transfer-right").click(function () {
    selectArr.push(fistSelectArr[0]);
    selectArr = unique(selectArr);
    rewriteSelect(selectArr);
    $("#treePeople li").removeClass("active")
});

//获取选中人员name, id
$("#gain").click(function () {
    var ids = [];
    var name = [];
    $.each($("#treeSelected li"), function (index, item) {
        ids.push($(item).attr('data-id'));
        name.push($(item).text());
    });
    ids = ids.join(',');
    name = name.join(',');
    $("#selectDept").val(name);
    $("#selectDeptIds").val(ids);
    $("#modal-department-select").modal("hide");
});


/**
 * 组展示
 */
var selectGroupArr = [];
function toShowGroupModal() {
    selectGroupArr = [];
    var selectDeptIds = $("#selectGroupIds").val();
    var selectDeptName = $("#selectGroup").val();
    if (selectDeptIds != '' && selectDeptName != '') {
        var ids = selectDeptIds.split(',');
        var names = selectDeptName.split(',');
        for (var i = 0; i < ids.length; i++) {
            selectGroupArr.push({
                name: names[i],
                id: ids[i]
            });
        }
    }
    rewriteGroupSelect(selectGroupArr);
    $("#treeGroup li").removeClass("active");
    loadGroupSelectData();
    $("#modal-group-select").modal("show");
}

var fistSelectGroupArr = new Array();
$("#groupTree").on("changed.jstree", function (e, data) {
    if (data.selected.length > 0) {
        fistSelectGroupArr = [];
        fistSelectGroupArr.push({
            name: data.node.text,
            id: data.selected
        })
    }
});

//添加选中人员
$(".transfer-right-group").click(function () {
    selectGroupArr.push(fistSelectGroupArr[0]);
    selectGroupArr = unique(selectGroupArr);
    rewriteGroupSelect(selectGroupArr);
    $("#treeGroup li").removeClass("active")
});

//删除选中人员
var clearGroupArr = [];
$(".transfer-left-group").click(function () {
    var liObj = $("#treeGroupSelected li");
    for (var i = 0; i < liObj.length; i++) {
        if ($(liObj[i]).hasClass("active")) {
            clearGroupArr.push({
                name: $("#treeGroupSelected li:eq(" + i + ") .people-name").text(),
                id: $("#treeGroupSelected li:eq(" + i + ")").attr("data-id")
            })
        }
    }
    for (var i = 0; i < selectGroupArr.length; i++) {
        for (var j = 0; j < clearGroupArr.length; j++) {
            if (selectGroupArr[i].id == clearGroupArr[j].id) {
                selectGroupArr.splice(i, 1);
            }
        }
    }
    clearArr = [];
    rewriteGroupSelect(selectGroupArr);
});

//获取选中人员name, id
$("#gainGroup").click(function () {
    var ids = [];
    var name = [];
    $.each($("#treeGroupSelected li"), function (index, item) {
        ids.push($(item).attr('data-id'));
        name.push($(item).text());
    });
    ids = ids.join(',');
    name = name.join(',');
    $("#selectGroup").val(name);
    $("#selectGroupIds").val(ids);
    $("#modal-group-select").modal("hide");
});

/**
 * 加载分组数据 getGroupTreeList
 */

function loadGroupSelectData() {
    $.ajax({
        url: baseUrl + '/customer/group/getGroupTreeList',
        method: 'POST',
        contentType: 'application/json',
        async: false,
        success: function (result) {
            var groups = result.data;
            if (groups.length > 0) {
                $("#groupTree").jstree({
                    'plugins': ["search", "themes", "types", "line"],
                    'types': {
                        'default': {
                            'icon': true
                        },
                    },
                    "checkbox": {
                        tie_selection: true,
                        keep_selected_style: true,
                        whole_node: true
                    },
                    'core': {
                        'data': groups,
                        'themes': {
                            "icons": true,	//默认图标
                            "theme": "classic",
                            "dots": true,
                            "stripes": true,	//增加条纹
                        },	//关闭文件夹样式
                        'dblclick_toggle': true,   //允许tree的双击展开,默认是true
                        "multiple": false, // 单选
                        "check_callback": true
                    }
                });
                $('#groupTree').jstree(true).refresh();
            }
        }
    });
}