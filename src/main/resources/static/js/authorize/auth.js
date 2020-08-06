$(function () {
    initDepartmentData();
});

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
                console.log(users)
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
    }
});

// 获取选中的用户
function getPeople(e, obj) {
    var userId = $(obj).attr('data-id');
    console.log(obj);

}

// 初始化部门数据
function initDepartmentData() {
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
        }],
    });
}