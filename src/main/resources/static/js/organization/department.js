$(function () {
    initSelect();
    initDepartmentSelect();
    initUser();
    initGroupData();
    initDepartmentData();
    initPostData();
});
// 初始化组数据
function initGroupData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "group/getGroupList", // 获取数据的地址
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
        idField: "clientId",
        columns: [{
            field: 'state',
            checkbox: true
        },{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'groupName',
            title: '组名称'
        },{
            field: 'id',
            title: '组ID'
        },{
            field: 'groupParentName',
            title: '父级分组'
        },{
            field: 'parentId',
            title: '关联账户数'
        },{
            field: 'status',
            title: '状态',
            formatter:function (value) {
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
            return JSON.stringify(data);
        }
    });
}

function initSelect() {
    var data = {};
    data['pageSize'] = '1000000';
    data['pageNumber'] = 1;
    $.ajax({
        url: baseUrl + '/group/getGroupList',
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            var str = '<option value="">--请选择--</option>';
            for (var i = 0; i < result.rows.length; i++) {
                var entity = result.rows[i];
                str += '<option value="'+entity.id+'">'+entity.groupName+'</option>';
            }
            $("#parentGroup").html(str);
        }
    });
}

// 自定义方法，添加操作按钮
function btnGroup () {
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
    $("#groupId").val('');
    $("#parentGroup").val('');
    $("#groupName").val('');
    $("#sort").val('');
    $("[name='my-checkbox']").bootstrapSwitch('state', false);
    $("#saveBtn").show();
    $("#myModal").modal('show');
});

// 保存
$("#saveBtn").click(function () {
    addGroup();
});

// 编辑
function edit(row) {
    $("#groupId").val(row.id);
    $("#parentGroup").val(row.parentId);
    $("#groupName").val(row.groupName);
    $("#sort").val(row.sort);
    $("#saveBtn").show();
    if (row.status == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    $("#myModal").modal('show');
}

// 详情
function detail(row) {
    $("#groupId").val(row.id);
    $("#parentGroup").val(row.parentId);
    $("#groupName").val(row.groupName);
    $("#sort").val(row.sort);
    if (row.status == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    $("#saveBtn").hide();
    $("#myModal").modal('show');
}

// 添加、编辑组
function addGroup() {
    var id = $("#groupId").val();
    var parentVal = $("#parentGroup").val();
    if (parentVal == null || parentVal == '') {
        parentVal = 0;
    }
    var parentId = parentVal;
    var groupName = $("#groupName").val();
    var sort = $("#sort").val();
    var status = stateVal;
    var data = {};
    data['parentId'] = parentId;
    data['groupName'] = groupName;
    data['sort'] = sort;
    data['status'] = status;
    var url = '';
    if (id == null || id == '') {
        url = "/group/add";
    } else {
        url = "/group/edit";
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
    alert(111)
    alert(id);
    if (id != null && id != '') {
        $.ajax({
            url: baseUrl + "/group/del?id=" + id,
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

// =====================================部门 开始============================================
// 初始化部门数据
function initDepartmentData() {
    $('#departmentTable').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#departmentTable").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "department/getDepartmentList", // 获取数据的地址
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
        idField: "clientId",
        columns: [{
            field: 'state',
            checkbox: true
        },{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'name',
            title: '部门名称'
        },{
            field: 'id',
            title: '部门ID'
        },{
            field: 'sort',
            title: '排序'
        },{
            field: 'parentName',
            title: '上级部门'
        },{
            field: 'headerName',
            title: '部门主管'
        },{
            field: 'reduceHeaderName',
            title: '部门分管领导'
        },{
            field: 'status',
            title: '状态',
            formatter:function (value) {
                if(value == 0){
                    return "启用";
                }else{
                    return "禁用";
                }
            }
        },{
            field: 'operate',
            title: '操作',
            formatter: btnDepartment,
            events: {
                'click #departmentDetail': function (event, value, row, index) {
                    var type = 'detail';
                    departmentDetail(row,type);
                },
                'click #departmentEdit': function (event, value, row, index) {
                    var type = 'edit';
                    departmentDetail(row,type);
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
            return JSON.stringify(data);
        }
    });
}

// 自定义方法，添加操作按钮
function btnDepartment () {
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

function initDepartmentSelect() {
    var data = {};
    data['pageSize'] = '1000000';
    data['pageNumber'] = 1;
    $.ajax({
        url: baseUrl + '/department/getDepartmentList',
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            var str = '<option value="">--请选择--</option>';
            for (var i = 0; i < result.rows.length; i++) {
                var entity = result.rows[i];
                str += '<option value="'+entity.id+'">'+entity.name+'</option>';
            }
            $("#parentDepartment").html(str);
        }
    });
}
  // 部门详情
function departmentDetail(row,type) {
    if (type == 'detail') {
        $("#saveDepartmentBtn").hide();
    }
    if(type == 'edit') {
        $("#saveDepartmentBtn").show();
        $("#departmentId").val(row.id)
    }
    $("#parentDepartment").val(row.parentId);
    $("#departmentName").val(row.name);
    $("#departmentSort").val(row.sort);
    $("#header").val(row.header);
    $("#reduceHeader").val(row.reduceHeader);
    if (row.status == 0) {
        $("#departmentStatus").bootstrapSwitch('state', true);
    } else {
        $("#departmentStatus").bootstrapSwitch('state', false);
    }
    $("#departmentModal").modal('show');
}

// 初始化人员数据
function initUser(){
    var data = {};
    data['pageNumber'] = 1;
    data['pageSize'] = 100000;
    $.ajax({
        url: baseUrl + "/admin/adminUser/getList",
        async: false,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            var str = '<option value="">--请选择--</option>';
            for (var i = 0; i < result.rows.length; i++) {
                var entity = result.rows[i];
                str += '<option value="'+entity.id+'">'+entity.userName+'</option>';
            }
            $("#header").html(str);
            $("#reduceHeader").html(str);
        }
    });
}

// 打开部门模态框
$("#addDepartment").click(function () {
    $("#saveDepartmentBtn").show();
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
    var parentDepartmentVal = $("#parentDepartment").val();
    if (parentDepartmentVal == null || parentDepartmentVal == '') {
        parentDepartmentVal = 0;
    }
    var parentDepartment = parentDepartmentVal;
    var departmentName = $("#departmentName").val();
    var departmentSort = $("#departmentSort").val();
    var status = departmentStateVal;
    var header = $("#header").val();
    var reduceHeader = $("#reduceHeader").val();
    var data = {};
    data['parentId'] = parentDepartment;
    data['name'] = departmentName;
    data['sort'] = departmentSort;
    data['status'] = status;
    data['header'] = header;
    data['reduceHeader'] = reduceHeader;
    var url = '';
    if (id == null || id == '') {
        url = "/department/add";
    } else {
        url = "/department/edit";
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
        url: baseUrl + '/department/del?id=' + id,
        async: false,
        type: 'POST',
        contentType: "application/json",
        success: function (result) {
            initDepartmentData();
            narn('success', result.message);
        }
    });

}


// =====================================部门 结束============================================


// =====================================岗位 开始============================================
// 初始化岗位数据
function initPostData() {
    $('#postTable').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#postTable").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "post/getPostList", // 获取数据的地址
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
        idField: "clientId",
        columns: [{
            field: 'state',
            checkbox: true
        },{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'postName',
            title: '岗位名称'
        },{
            field: 'postCode',
            title: '岗位编码'
        },{
            field: 'postDec',
            title: '岗位描述'
        },{
            field: 'sort',
            title: '排序'
        },{
            field: 'status',
            title: '状态',
            formatter:function (value) {
                if(value == 0){
                    return "启用";
                }else{
                    return "禁用";
                }
            }
        },{
            field: 'operate',
            title: '操作',
            formatter: btnPost,
            events: {
                'click #relationUser': function (event, value, row, index) {

                },
                'click #postDetail': function (event, value, row, index) {
                    var type = 'detail';
                    postDetail(row,type);
                },
                'click #postEdit': function (event, value, row, index) {
                    var type = 'edit';
                    postDetail(row,type);
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
            return JSON.stringify(data);
        }
    });
}

// 自定义方法，添加操作按钮
function btnPost () {
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
    var data = {};
    data['postName'] = postName;
    data['postCode'] = postCode;
    data['postDec'] = postDec;
    data['status'] = postStateVal;
    data['sort'] = postSort;
    var url = '';
    if (postId == null || postId == '') {
        url = '/post/add';
    }
    if (postId != null && postId != '') {
        data['id'] = postId;
        url = '/post/edit';
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
    $("#postId").val(row.id);
    $("#postName").val(row.postName);
    $("#postCode").val(row.postCode);
    $("#postSort").val(row.sort);
    $("#postDec").val(row.postDec);
    if (row.status == 0) {
        $("#postStatus").bootstrapSwitch('state', true);
    } else {
        $("#postStatus").bootstrapSwitch('state', false);
    }
    $("#postModal").modal('show');
}
// 删除
function postDel(id) {
    if (id != null && id != '') {
        $.ajax({
            url: baseUrl + '/post/del?id=' + id,
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