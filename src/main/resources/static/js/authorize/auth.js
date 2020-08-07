$(function () {
    // 初始化部门信息
    initDepartmentData();
    initClientData2();
    // 初始化应用信息
    initClientData();
});
var clientIds = [];
var userId = '';
// 获取组织架构信息
function initDepartmentData() {
    $.ajax({
        url: baseUrl + '/customer/department/getDepartmentTreeList',
        method: 'POST',
        contentType: 'application/json',
        async: false,
        success: function (result) {
            var departments = result.data;
            if (departments.length > 0) {
                $("#departmentTree1").jstree({
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
                $("#departmentTree2").jstree({
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

// 初始化应用数据
function initClientData() {
    $('#appTable1').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#appTable1").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/customer/client/getClientList", // 获取数据的地址
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
            checkbox: true,
            formatter: function (value, row, index) {
                if ($.inArray(row.clientId, clientIds) != -1) {
                    return {
                        checked: true
                    };
                } else {
                    return {
                        checked: false
                    };
                }
            }
        }, {
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'clientName',
            title: '应用名称'
        }, {
            field: 'clientId',
            title: '应用ID'
        }, {
            field: 'createTimeStr',
            title: '创建时间'
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            return JSON.stringify(data);
        },
        // 全部选择
        onCheckAll:function(rows){
            if (rows.length > 0) {
                for (let i = 0; i < rows.length; i++) {
                    clientIds.push(rows[i].clientId);
                }
            }
        },
        // 单个选择
        onCheck:function(row){
            if (row.length > 0) {
                clientIds.push(row.clientId);
            }
        },
        // 单个取消
        onUncheck:function(row){
            if (row.length > 0) {
                clientIds.forEach(function (item, index, arr) {
                    if (item == row.clientId) {
                        arr.splice(index, 1);
                    }
                });
            }
        },
        // 全部取消
        onUncheckAll: function (row) {
            if (row.length > 0) {
                for (var i = 0; i < row.length; i++) {
                    for (var j = 0; j < clientIds.length; j++) {
                        if (row[i].clientId == clientIds[j]) {
                            clientIds.forEach(function (item, index, arr) {
                                if (item == clientIds[j]) {
                                    arr.splice(index, 1);
                                }
                            });
                        }
                    }
                }
            }
        },

    });
}

// 部门树点击事件
var departmentSelectArr = new Array(); // 保存选择的部门信息
var peopleArr = new Array(); // 保存部门下的人员
$("#departmentTree1").on("changed.jstree", function (e, data) {

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
                            name: users[i].userName,
                            loginName: users[i].loginName
                        });
                    }
                    peopleArr = unique(peopleArr);
                    var str = '';
                    for (var i = 0; i < peopleArr.length; i++) {
                        str += "<tr onclick='getPeople(event, this)' data-id='"+peopleArr[i].id+"'><td>"+peopleArr[i].loginName+"</td><td>"+peopleArr[i].name+"</td></tr>";
                    }
                    $("#exTable1").html("<tr>" +
                        "<th>账户名称</th>" +
                        "<th>姓名</th>" +
                        "</tr>");
                    $("#exTable1").append(str);
                }
            }
        });
        if (clientIds.length > 0) {
            clientIds = [];
            initClientData();
        }
    }
});

// 获取选中的用户
function getPeople(e, obj) {
    clientIds = [];
    userId = $(obj).attr('data-id');
    var data = {};
    data['userId'] = userId;
    $(obj).addClass('onn').siblings().removeClass('onn');
    $.ajax({
        url: baseUrl + '/customer/userClient/getAdminUserClientList',
        async: true,
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (result) {
            var clientList = result.data;
            if (clientList.length > 0) {
                for (var i = 0; i < clientList.length; i++) {
                    clientIds.push(clientList[i].clientId);
                }
            }
            // 初始化表单信息
            initClientData();
        }
    });
}
// 按账户授权应用 -- 保存
$("#saveBtn1").click(function () {
    if (clientIds.length > 0 && userId != '') {
        var data = {};
        data['userId'] =  userId;
        data['clientId'] = clientIds;
        $.ajax({
            url: baseUrl + '/customer/userClient/insert',
            async: true,
            method: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (result) {
                narn('success', result.message);
                initClientData();
            }
        });
    }
});


// =============================按应用授权============================

// 初始化应用数据
function initClientData2() {
    $('#appTable2').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#appTable2").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/customer/client/getClientList", // 获取数据的地址
        pagination: true, // 启动分页
        cache: true,
        maintainSelected: true,
        singleSelect: true,
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
            checkbox: true,
            formatter: function (value, row, index) {
                if ($.inArray(row.clientId, clientIds) != -1) {
                    return {
                        checked: true
                    };
                } else {
                    return {
                        checked: false
                    };
                }
            }
        }, {
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'clientName',
            title: '应用名称'
        }, {
            field: 'clientId',
            title: '应用ID'
        }, {
            field: 'createTimeStr',
            title: '创建时间'
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            return JSON.stringify(data);
        },
        // 单个选择
        onCheck:function(row){
            if (row.length > 0) {
                clientIds.push(row.clientId);
            }
        },
        // 单个取消
        onUncheck:function(row){
            if (row.length > 0) {
                clientIds.forEach(function (item, index, arr) {
                    if (item == row.clientId) {
                        arr.splice(index, 1);
                    }
                });
            }
        }
    });
}

// 部门树点击事件
var departmentSelectArr2 = new Array(); // 保存选择的部门信息
var peopleArr2 = new Array(); // 保存部门下的人员
$("#departmentTree2").on("changed.jstree", function (e, data) {

    if (data.selected.length > 0) {
        departmentSelectArr2 = [];
        departmentSelectArr2.push({
            name: data.node.text,
            id: data.selected
        });
    }
    if (departmentSelectArr2.length > 0) {
        $.ajax({
            url: baseUrl + '/customer/userDepartment/getUserByDepartmentId?departmentId=' + departmentSelectArr2[0].id,
            async: true,
            method: 'POST',
            contentType: 'application/json',
            success: function (result) {
                var users = result.data;
                if (users.length > 0) {
                    peopleArr2 = [];
                    for (var i = 0; i < users.length; i++) {
                        peopleArr2.push({
                            id: users[i].userId,
                            name: users[i].userName,
                            loginName: users[i].loginName
                        });
                    }
                    peopleArr2 = unique(peopleArr2);
                    var str = '';
                    for (var i = 0; i < peopleArr2.length; i++) {
                        str += "<tr>" +
                                "<td>" +
                                    "<div class='form-check-item checkbox-inline checkbox-custom'>" +
                                        "<input class='form-check-input' type='checkbox' id='inlineCheckbox" + i + "' value='" + peopleArr2[i].id + "'>" +
                                        "<label class='form-check-label' for='inlineCheckbox" + i + "'></label>" +
                                    "</div>" +
                                "</td>" +
                                "<td>" + peopleArr2[i].loginName + "</td>" +
                                "<td>" + peopleArr2[i].name + "</td>" +
                            "</tr>";
                    }
                    $("#exTable2").html("<tr>" +
                        "<th>" +
                            "<div class='form-check-all checkbox-inline checkbox-custom'>" +
                                "<input class='form-check-input' type='checkbox' id='inlineCheckbox' value=''>" +
                                "<label class='form-check-label' for='inlineCheckbox'></label>" +
                            "</div> " +
                        "</th>" +
                        "<th>账户名称</th>" +
                        "<th>姓名</th>" +
                        "</tr>");
                    $("#exTable2").append(str);
                }
            }
        });
    }
});

//单个
$("#exTable2").on('change', '.form-check-item input',function(){
    var lenth=$('.form-check-item').length;
    var len=$('.form-check-item input:checked').length;
    console.log(lenth);
    if(lenth==len){
        console.log("==");
        $('.form-check-all input').prop("checked",true);
    }else{
        console.log("1=");
        $('.form-check-all input').prop("checked",false);
    }
});

//全选
$("#exTable2").on('change', '.form-check-all input', function () {
    let boolean=$(this).is(':checked');
    if(boolean){
        console.log("==");
        $('.form-check-item input').prop("checked",true);
    }else{
        console.log("1=");
        $('.form-check-item input').prop("checked",false);
    }
});

var clientId2 = '';
$("#saveBtn2").click(function () {

    var clientRows = $("#appTable2").bootstrapTable('getSelections');

    if (clientRows.length > 0 && userRows.length > 0) {
        clientId2 = clientRows[0].clientId;
    } else {
        narn('error', '请选择授权应用！');
    }
});

