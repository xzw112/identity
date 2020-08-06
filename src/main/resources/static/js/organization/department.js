var departmentId = '';
var selectArr = new Array();

$(function () {
    // 初始化分组数据
    initGroupData();
    // 初始化部门数据
    initDepartmentData();
    // 初始化岗位数据
    initPostData();
    // 初始部门树
    initTree();
});

// 初始化组数据
function initGroupData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/customer/group/getGroupList", // 获取数据的地址
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
            field: 'departmentId',
            title: 'departmentId',
            visible: false
        }, {
            field: 'departmentName',
            title: '所属部门',
        }, {
            field: 'groupName',
            title: '组名称'
        }, {
            field: 'id',
            title: '组ID'
        }, {
            field: 'groupParentName',
            title: '父级分组'
        }, {
            field: 'parentId',
            title: '关联账户数'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value) {
                if (value == 0) {
                    return "启用";
                } else {
                    return "禁用";
                }
            }
        }, {
            field: 'operate',
            title: '操作',
            formatter: btnGroup,
            events: {
                'click #relationUser': function (event, value, row, index) {

                },
                'click #groupDetail': function (event, value, row, index) {
                    detail(row);
                },
                'click #groupEdit': function (event, value, row, index) {
                    edit(row);
                },
                'click #groupDel': function (event, value, row, index) {
                    delGroup(row.id);
                }
            }
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['groupName'] = $("#searchText").val();
            data['departmentId'] = departmentId;
            return JSON.stringify(data);
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
    initGroupData();
    initDepartmentData();
    initPostData();
}

// 自定义方法，添加操作按钮
function btnGroup() {
    // data-target="xxx" 为点击按钮弹出指定名字的模态框
    var html =
        '<a href="####"  id="relationUser" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="关联用户">关联用户' +
        '</a>' +
        '<a href="####"  id="groupDetail" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="详情">详情' +
        '</a>' +
        '<a href="####"  id="groupEdit" data-toggle="modal" data-target="#editinfo" ' +
        'style="margin-left:15px" title="修改">修改' +
        '</a>' +
        '<a href="####"  id="groupDel" data-toggle="modal" data-target="#deleteuser" ' +
        'style="margin-left:15px" title="删除">删除' +
        '</a>'
    return html
};

// 搜索
$("#searchBtn").click(function () {
    initGroupData();
});

// 添加组
$("#addGroup").click(function () {
    $("#selectDept1").val('');
    $("#selectDeptIds1").val('');
    $("#selectParentsGroups").val('');
    $("#selectParentsGroupIds").val('');
    $("#groupId").val('');
    $("#parentGroup").val('');
    $("#groupName").val('');
    $("#sort").val('');
    $("[name='my-checkbox']").bootstrapSwitch('state', false);
    $("#saveBtn").show();
    $("#myModal").modal('show');
});

// 保存
$("#saveBtn").click(function () {
    addGroup();
});

// 编辑
function edit(row) {
    $("#selectParentsGroupIds").val(row.parentId);
    $("#selectParentsGroups").val(row.groupParentName);
    $("#selectDeptIds1").val(row.departmentId);
    $("#selectDept1").val(row.departmentName);
    $("#groupId").val(row.id);
    $("#parentGroup").val(row.parentId);
    $("#groupName").val(row.groupName);
    $("#sort").val(row.sort);
    $("#saveBtn").show();
    if (row.status == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    $("#myModal").modal('show');
}

// 详情
function detail(row) {
    $("#selectParentsGroupIds").val(row.parentId);
    $("#selectParentsGroups").val(row.groupParentName);
    $("#selectDeptIds1").val(row.departmentId);
    $("#selectDept1").val(row.departmentName);
    $("#groupId").val(row.id);
    $("#parentGroup").val(row.parentId);
    $("#groupName").val(row.groupName);
    $("#sort").val(row.sort);
    if (row.status == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    $("#saveBtn").hide();
    $("#myModal").modal('show');
}

// 添加、编辑组
function addGroup() {
    var id = $("#groupId").val();
    var parentVal = $("#selectParentsGroupIds").val();
    if (parentVal == null || parentVal == '') {
        parentVal = 0;
    }
    var parentId = parentVal;
    var groupName = $("#groupName").val();
    var sort = $("#sort").val();
    var status = stateVal;
    var departmentId = $("#selectParentsGroupIds").val();
    var data = {};
    data['departmentId'] = departmentId;
    data['parentId'] = parentId;
    data['groupName'] = groupName;
    data['sort'] = sort;
    data['status'] = status;
    var url = '';
    if (id == null || id == '') {
        url = "/customer/group/add";
    } else {
        url = "/customer/group/edit";
        data['id'] = id;
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
            initGroupData();
            $("#myModal").modal('hide');
        }
    });
}

// 删除组
function delGroup(id) {
    if (id != null && id != '') {
        $.ajax({
            url: baseUrl + "/customer/group/del?id=" + id,
            async: false,
            type: 'POST',
            contentType: "application/json",
            success: function (result) {
                narn('success', result.message);
                initGroupData();
            }
        });
    }
}

/**
 * 组展示
 */
var selectGroupArr = [];

function toShowGroupModal() {
    selectGroupArr = [];
    var selectDeptIds = $("#selectParentsGroupIds").val();
    var selectDeptName = $("#selectParentsGroups").val();

    if (selectDeptIds != '' && selectDeptName != '') {
        var ids = selectDeptIds.split(',');
        var names = selectDeptName.split(',');
        selectArr = [];
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
    selectGroupArr = [];
    selectGroupArr.push(fistSelectGroupArr[0]);
    selectGroupArr = unique(selectGroupArr);
    rewriteGroupSelect(selectGroupArr);
    $("#treeGroup li").removeClass("active")
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
    $("#selectParentsGroups").val(name);
    $("#selectParentsGroupIds").val(ids);
    $("#modal-group-select").modal("hide");
});
// =====================================组 结束============================================

// =====================================部门 开始============================================
// 初始化部门数据
function initDepartmentData() {
    $('#departmentTable').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#departmentTable").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/customer/department/getDepartmentList", // 获取数据的地址
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
        idField: "clientId",
        columns: [{
            field: 'state',
            checkbox: true
        }, {
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'name',
            title: '部门名称'
        }, {
            field: 'id',
            title: '部门ID'
        }, {
            field: 'sort',
            title: '排序'
        }, {
            field: 'parentName',
            title: '上级部门'
        }, {
            field: 'headerName',
            title: '部门主管'
        }, {
            field: 'reduceHeaderName',
            title: '部门分管领导'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value) {
                if (value == 0) {
                    return "启用";
                } else {
                    return "禁用";
                }
            }
        }, {
            field: 'operate',
            title: '操作',
            formatter: btnDepartment,
            events: {
                'click #departmentDetail': function (event, value, row, index) {
                    var type = 'detail';
                    departmentDetail(row, type);
                },
                'click #departmentEdit': function (event, value, row, index) {
                    var type = 'edit';
                    departmentDetail(row, type);
                },
                'click #departmentDel': function (event, value, row, index) {
                    delDepartment(row.id);
                }
            }
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['departmentName'] = $("#departmentSearchTxt").val();
            data['departmentId'] = departmentId;
            return JSON.stringify(data);
        }
    });
}

// 自定义方法，添加操作按钮
function btnDepartment() {
    // data-target="xxx" 为点击按钮弹出指定名字的模态框
    var html =
        '<a href="####"  id="departmentDetail" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="详情">详情' +
        '</a>' +
        '<a href="####"  id="departmentEdit" data-toggle="modal" data-target="#editinfo" ' +
        'style="margin-left:15px" title="修改">修改' +
        '</a>' +
        '<a href="####"  id="departmentDel" data-toggle="modal" data-target="#deleteuser" ' +
        'style="margin-left:15px" title="删除">删除' +
        '</a>'
    return html
};

// 部门详情
function departmentDetail(row, type) {
    console.log(row)
    if (type == 'detail') {
        $("#saveDepartmentBtn").hide();
    }
    if (type == 'edit') {
        $("#saveDepartmentBtn").show();
        $("#departmentId").val(row.id)
    }
    $("#selectDeptIds2").val(row.parentId);
    $("#selectDept2").val(row.parentName);
    $("#protectUserId").val(row.header);
    $("#protectUser").val(row.headerName);
    $("#protectUserId1").val(row.reduceHeader);
    $("#protectUser1").val(row.reduceHeaderName);
    $("#parentDepartment").val(row.parentId);
    $("#departmentName").val(row.name);
    $("#departmentSort").val(row.sort);
    $("#header").val(row.header);
    $("#reduceHeader").val(row.reduceHeader);
    if (row.status == 0) {
        $("#departmentStatus").bootstrapSwitch('state', true);
    } else {
        $("#departmentStatus").bootstrapSwitch('state', false);
    }

    $("#departmentModal").modal('show');
}

// 打开部门模态框
$("#addDepartment").click(function () {
    $("#saveDepartmentBtn").show();
    $("#protectUser").val('');
    $("#protectUserId").val('');
    $("#protectUser1").val('');
    $("#protectUserId1").val('');
    $("#selectDept2").val('');
    $("#selectDeptIds2").val('');
    $("#departmentId").val('');
    $("#parentDepartment").val('');
    $("#departmentName").val('');
    $("#departmentSort").val('');
    $("#header").val('');
    $("#reduceHeader").val('');
    $("[name='my-checkbox-department']").bootstrapSwitch();
    $("#departmentModal").modal('show');
});

// 保存部门
$("#saveDepartmentBtn").click(function () {
    var id = $("#departmentId").val();
    var parentDepartmentVal = '';
    if (selectArr.length > 0) {
        parentDepartmentVal = selectArr[0].id[0];
    }
    if (parentDepartmentVal == null || parentDepartmentVal == '') {
        parentDepartmentVal = 0;
    }
    var parentDepartment = parentDepartmentVal;
    var departmentName = $("#departmentName").val();
    var departmentSort = $("#departmentSort").val();
    var status = departmentStateVal;
    var header = $("#protectUserId").val(); // 主管
    var reduceHeader = $("#protectUserId1").val(); // 分管
    var data = {};
    data['parentId'] = parentDepartment;
    data['name'] = departmentName;
    data['sort'] = departmentSort;
    data['status'] = status;
    data['header'] = header;
    data['reduceHeader'] = reduceHeader;
    var url = '';
    if (id == null || id == '') {
        url = "/customer/department/add";
    } else {
        url = "/customer/department/edit";
        data['id'] = id;
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
            initDepartmentData();
            initTree();
            $("#departmentModal").modal('hide');
        }
    });
});
// 搜索
$("#departmentSearch").click(function () {
    initDepartmentData();
});

// 删除部门
function delDepartment(id) {
    $.ajax({
        url: baseUrl + '/customer/department/del?id=' + id,
        async: false,
        type: 'POST',
        contentType: "application/json",
        success: function (result) {
            initDepartmentData();
            narn('success', result.message);
        }
    });
}

/**
 * 部门展示
 */
var tabType = '';

function toShowDepartmentModal(type) {
    selectArr = [];
    tabType = type;
    var selectDeptIds = '';
    var selectDeptName = '';
    if (type == 1) {
        selectDeptIds = $("#selectDeptIds1").val();
        selectDeptName = $("#selectDept1").val();
    }
    if (type == 2) {
        selectDeptIds = $("#selectDeptIds2").val();
        selectDeptName = $("#selectDept2").val();
    }
    if (type == 3) {
        selectDeptIds = $("#selectDeptIds3").val();
        selectDeptName = $("#selectDept3").val();
    }
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
    if (type == 4) {
        // 分组树状
        loadGroupSelectData();
    } else {
        loadDepartmentSelectData();
    }
    $("#modal-department-select").modal("show");
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
    selectArr = [];
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
    if (tabType == 1) {
        $("#selectDept1").val(name);
        $("#selectDeptIds1").val(ids);
    }
    if (tabType == 2) {
        $("#selectDept2").val(name);
        $("#selectDeptIds2").val(ids);
    }
    if (tabType == 3) {
        $("#selectDept3").val(name);
        $("#selectDeptIds3").val(ids);
    }
    if (tabType == 4) {
        $("#selectParentsGroups").val(name);
        $("#selectParentsGroupIds").val(ids);
    }
    $("#modal-department-select").modal("hide");
});

//添加选中人员
var surePeopleArr = new Array(); // 保存选择的人员

/**
 * 显示人员选择模态框
 *
 */
var headerType = '';

function toShowPeople(type) {
    headerType = type;
    var selectPeopleIds = '';
    var selectPeopleName = '';
    if (type == 1) {
        selectPeopleIds = $("#protectUserId").val();
        selectPeopleName = $("#protectUser").val();
    }
    if (type == 2) {
        selectPeopleIds = $("#protectUserId1").val();
        selectPeopleName = $("#protectUser1").val();
    }
    surePeopleArr = [];
    if (selectPeopleIds != '' && selectPeopleName != '') {
        var ids = selectPeopleIds.split(',');
        var names = selectPeopleName.split(',');

        for (var i = 0; i < ids.length; i++) {
            surePeopleArr.push({
                name: names[i],
                id: ids[i]
            });
        }
    }
    $("#sureSelected").html("");
    rewriteSelect1(surePeopleArr);
    loadDepartmentPeopleData();
    $("#modal-people-select").modal("show");
}

// 部门--人员选择的时候
var departmentSelectArr = new Array(); // 保存选择的部门信息
var peopleArr = new Array(); // 保存部门下的人员
$("#peopleSelect").on("changed.jstree", function (e, data) {
    var str = "";
    if (data.selected.length > 0) {
        departmentSelectArr = [];
        departmentSelectArr.push({
            name: data.node.text,
            id: data.selected
        })
    }
    if (departmentSelectArr.length > 0) {
        $.ajax({
            url: baseUrl + '/customer/userDepartment/getUserByDepartmentId?departmentId=' + departmentSelectArr[0].id,
            async: true,
            method: 'POST',
            contentType: 'application/json',
            success: function (result) {
                var users = result.data;
                if (users.length > 0) {
                    peopleArr = [];
                    for (var i = 0; i < users.length; i++) {
                        peopleArr.push({
                            id: users[i].userId,
                            name: users[i].userName
                        });
                    }
                    peopleArr = unique(peopleArr);
                    for (var i = 0; i < peopleArr.length; i++) {
                        str += "<li data-id = '" + peopleArr[i].id + "' onclick='selectPeople(event, this)'><span class='people-name'>" + peopleArr[i].name + "</span></li>";
                    }
                }
                $("#departmentTreePeople").html("");
                $("#departmentTreePeople").append(str);
            }
        });
    }
});
// 组--人员选择的时候
var zuSelectArr = new Array(); // 保存选择的部门信息
var zuPeopleArr = new Array(); // 保存部门下的人员
$("#zuSelect").on("changed.jstree", function (e, data) {
    var str = "";
    if (data.selected.length > 0) {
        zuSelectArr = [];
        zuSelectArr.push({
            name: data.node.text,
            id: data.selected
        })
    }
    if (zuSelectArr.length > 0) {
        var data = {};
        var groupId = zuSelectArr[0].id[0];
        console.log(groupId)
        data['groupId'] = groupId;

        $.ajax({
            url: baseUrl + '/customer/userGroup/getUserByGroupId',
            async: true,
            method: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (result) {
                var users = result.data;
                if (users.length > 0) {
                    zuPeopleArr = [];
                    for (var i = 0; i < users.length; i++) {
                        zuPeopleArr.push({
                            id: users[i].userId,
                            name: users[i].userName
                        });
                    }
                    zuPeopleArr = unique(zuPeopleArr);
                    for (var i = 0; i < zuPeopleArr.length; i++) {
                        str += "<li data-id = '" + zuPeopleArr[i].id + "' onclick='selectPeople(event, this)'><span class='people-name'>" + zuPeopleArr[i].name + "</span></li>";
                    }
                }
                $("#zuTreePeople").html("");
                $("#zuTreePeople").append(str);
            }
        });
    }
});

$(".transfer-right1").click(function () {
    var liObj = $("#departmentTreePeople li");
    var liObjPost = $("#gangTreePeople li");
    var liObjZu = $("#zuTreePeople li");
    for (var i = 0; i < liObj.length; i++) {
        if ($(liObj[i]).hasClass("active")) {
            surePeopleArr.push({
                name: $(liObj[i]).find(".people-name").text(),
                id: $(liObj[i]).attr("data-id")
            })
        }
    }
    for (var i = 0; i < liObjZu.length; i++) {
        if ($(liObjZu[i]).hasClass("active")) {
            surePeopleArr.push({
                name: $(liObjZu[i]).find(".people-name").text(),
                id: $(liObjZu[i]).attr("data-id")
            })
        }
    }
    for (var i = 0; i < liObjPost.length; i++) {
        if ($(liObjPost[i]).hasClass("active")) {
            surePeopleArr.push({
                name: $(liObjPost[i]).find(".people-name").text(),
                id: $(liObjPost[i]).attr("data-id")
            })
        }
    }
    surePeopleArr = unique(surePeopleArr);
    rewriteSelect1(surePeopleArr);
    $("#departmentTreePeople li").removeClass("active");
    $("#gangTreePeople li").removeClass("active");
    $("#zuTreePeople li").removeClass("active");
});


//重写选中列表
function rewriteSelect1(arr) {
    $("#sureSelected").empty();
    $.each(arr, function (index, item) {
        $("#sureSelected").append("<li data-id='" + item.id + "' onclick='selectPeople(event,this)' ><span class='people-name'>" + item.name + "</span></li>")
    })
}

$("#surePeopleSelect").click(function () {
    var ids = [];
    var name = [];
    $.each($("#sureSelected li"), function (index, item) {
        ids.push($(item).attr('data-id'));
        name.push($(item).text());
    });
    ids = ids.join(',');
    name = name.join(',');
    if (headerType == 1) {
        $("#protectUser").val(name);
        $("#protectUserId").val(ids);
    }
    if (headerType == 2) {
        $("#protectUser1").val(name);
        $("#protectUserId1").val(ids);
    }
    $("#modal-people-select").modal("hide");
});

// =====================================部门 结束============================================


// =====================================岗位 开始============================================
// 初始化岗位数据
function initPostData() {
    $('#postTable').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#postTable").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/customer/post/getPostList", // 获取数据的地址
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
        idField: "clientId",
        columns: [{
            field: 'state',
            checkbox: true
        }, {
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'departmentId',
            title: 'departmentId',
            visible: false
        }, {
            field: 'departmentName',
            title: '归属部门',
        }, {
            field: 'postName',
            title: '岗位名称'
        }, {
            field: 'postCode',
            title: '岗位编码'
        }, {
            field: 'postDec',
            title: '岗位描述'
        }, {
            field: 'sort',
            title: '排序'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value) {
                if (value == 0) {
                    return "启用";
                } else {
                    return "禁用";
                }
            }
        }, {
            field: 'operate',
            title: '操作',
            formatter: btnPost,
            events: {
                'click #relationUser': function (event, value, row, index) {

                },
                'click #postDetail': function (event, value, row, index) {
                    var type = 'detail';
                    postDetail(row, type);
                },
                'click #postEdit': function (event, value, row, index) {
                    var type = 'edit';
                    postDetail(row, type);
                },
                'click #postDel': function (event, value, row, index) {
                    postDel(row.id);
                }
            }
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['postName'] = $("#postSearchText").val();
            data['departmentId'] = departmentId;
            return JSON.stringify(data);
        }
    });
}

// 自定义方法，添加操作按钮
function btnPost() {
    // data-target="xxx" 为点击按钮弹出指定名字的模态框
    var html =
        '<a href="####"  id="postDetail" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="详情">详情' +
        '</a>' +
        '<a href="####"  id="postEdit" data-toggle="modal" data-target="#editinfo" ' +
        'style="margin-left:15px" title="修改">修改' +
        '</a>' +
        '<a href="####"  id="postDel" data-toggle="modal" data-target="#deleteuser" ' +
        'style="margin-left:15px" title="删除">删除' +
        '</a>'
    return html;
};

// 新增岗位
$("#addPost").click(function () {
    $("#selectDept3").val('');
    $("#selectDeptIds3").val('');
    $("#postId").val('');
    $("#postName").val('');
    $("#postCode").val('');
    $("#postSort").val('');
    $("#postDec").val('');
    $("[name='my-checkbox-post']").bootstrapSwitch();
    $("#postModal").modal('show');
});

// 保存岗位
$("#savePostBtn").click(function () {
    var postId = $("#postId").val();
    var postName = $("#postName").val();
    var postCode = $("#postCode").val();
    var postSort = $("#postSort").val();
    var postDec = $("#postDec").val();
    var departmentId = $("#selectDeptIds3").val();
    var data = {};
    data['postName'] = postName;
    data['postCode'] = postCode;
    data['postDec'] = postDec;
    data['status'] = postStateVal;
    data['sort'] = postSort;
    data['departmentId'] = departmentId;
    var url = '';
    if (postId == null || postId == '') {
        url = '/customer/post/add';
    }
    if (postId != null && postId != '') {
        data['id'] = postId;
        url = '/customer/post/edit';
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            narn('success', result.message);
            initPostData();
            $("#postModal").modal('hide');
        }
    });
});

// 详情
function postDetail(row, type) {
    if (type == 'edit') {
        $("#savePostBtn").show();
    }
    if (type == 'detail') {
        $("#savePostBtn").hide();
    }
    $("#selectDeptIds3").val(row.departmentId);
    $("#selectDept3").val(row.departmentName);
    $("#postId").val(row.id);
    $("#postName").val(row.postName);
    $("#postCode").val(row.postCode);
    $("#postSort").val(row.sort);
    $("#postDec").val(row.postDec);
    if (row.status == 0) {
        $("#postStatus").bootstrapSwitch('state', true);
    } else {
        $("#postStatus").bootstrapSwitch('state', false);
    }
    $("#postModal").modal('show');
}

// 删除
function postDel(id) {
    if (id != null && id != '') {
        $.ajax({
            url: baseUrl + '/customer/post/del?id=' + id,
            async: false,
            type: 'POST',
            contentType: "application/json",
            success: function (result) {
                narn('success', result.message);
                initPostData();
            }
        })
    }
}

// 搜索
$("#postSearch").click(function () {
    initPostData();
});

// =====================================岗位 结束============================================

/**
 * 加载部门数据 getGroupTreeList
 */

function loadDepartmentSelectData() {
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
                $('#departmentTree').jstree(true).refresh();
            }
        }
    });
}

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

/**
 * 加载部门---选择人
 */
function loadDepartmentPeopleData() {
    $.ajax({
        url: baseUrl + '/customer/department/getDepartmentTreeList',
        method: 'POST',
        contentType: 'application/json',
        async: false,
        success: function (result) {
            var departments = result.data;
            if (departments.length > 0) {
                $("#peopleSelect").jstree({
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
                })
            }
        }
    });
}

/**
 * 加载组
 */
function loadGroupList() {
    $.ajax({
        url: baseUrl + '/customer/group/getGroupTreeList',
        method: 'POST',
        contentType: 'application/json',
        async: false,
        success: function (result) {
            var groups = result.data;
            if (groups.length > 0) {
                $("#zuSelect").jstree({
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
                })
            }
        }
    });
}

// 加载岗位信息
function loadPostList() {
    $("#gangSelect").html("");
    var data = {};
    data['pageSize'] = 10000;
    data['pageNumber'] = 1;
    $.ajax({
        url: baseUrl + '/customer/post/getPostList',
        async: true,
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (result) {
            var positionList = result.rows;
            if (positionList.length > 0) {
                var str = "";
                $("#gangTreePeople").html("");
                for (var i = 0; i < positionList.length; i++) {
                    str += "<li data-id='" + positionList[i].id + "' onclick='getUserByPostId(event, this)'><span class='people-name'>" + positionList[i].postName + "</span></li>"
                }
                $("#gangSelect").append(str);
            }
        }
    });
}

// 岗位下，确定岗位人员
function selectSurePosition (e, obj){
    $(obj).addClass("active").siblings().removeClass("active");
}


