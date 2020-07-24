$(function () {
    // 初始化数据
    loadData();
});

// 加载数据
function loadData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "client/getClientList", // 获取数据的地址
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
            field: 'clientName',
            title: '应用名称'
        },{
            field: 'imgUrl',
            title: '应用图标',
            formatter: function (value, row, index) {
                return '<img src="'+ value +'" width="40px" height="40px"/>';
            }
        },{
            field: 'clientId',
            title: '应用ID'
        },{
            field: 'clientType',
            title: '设备类型',
            formatter: function (value, row, index) {
                var str = "";
                if (value == 1) {
                    str = "Web应用";
                } else if (value == 2) {
                    str = "移动应用";
                } else if (value == 3) {
                    str = "PC客户端";
                }
                return str;
            }
        },{
            field: 'status',
            title: '状态',
            formatter:function (value) {
                if(value == 1){
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
                'click #clientDetail': function (event, value, row, index) {
                    detail(row.clientId);
                },
                'click #clientEdit': function (event, value, row, index) {
                    edit(row.clientId);
                },
                'click #clientDel': function (event, value, row, index) {
                    del(row.clientId);
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
        '<a href="####"  id="clientDetail" data-toggle="modal" data-target="#editrole" title="详情">详情' +
        '</a>' +
        '<a href="####"  id="clientEdit" data-toggle="modal" data-target="#editinfo" ' +
        'style="margin-left:15px" title="修改">修改' +
        '</a>' +
        '<a href="####"  id="clientDel" data-toggle="modal" data-target="#deleteuser" ' +
        'style="margin-left:15px" title="删除">删除' +
        '</a>'
    return html
};

$("#basic-addon2").click(function () {
    search();
});
function search() {
    var searchText = $("#searchText").val();
    if (searchText != null && searchText != '') {
        loadData();
    }
}

/**
 * 修改
 */
function edit(id) {
    window.location.href = baseUrl + "/admin/addApplication?clientId=" + id + "&action=edit"

}

/**
 * 添加应用
 */
$("#addApp").click(function () {
    window.location.href = baseUrl + "/admin/addApplication";
});


/**
 * 删除
 */
function del(id) {
    var rows  = $("#examplePagination").bootstrapTable('getSelections');
    //if (rows.length > 0) {
        var clientId = [];
        // for (var i = 0; i < rows.length; i++) {
        //     clientId.push(rows[i].id);
        // }
        clientId.push(id);
        var data = {};
        data['clientId'] = clientId;
        if (clientId.length > 0) {
            $.ajax({
                url: baseUrl + "/client/del?clientId=" + clientId,
                method: 'POST',
                async: true,
                contentType: 'application/json',
                success: function (result) {
                    if (result.success) {
                        narn('success', result.message);
                    } else {
                        narn('error', result.message);
                    }
                    loadData();
                }
            });
        }
    //} else {
        narn('error', '请先选择');
    //}



}

/**
 *详情
 */
function detail(id) {
    window.location.href = baseUrl + "/admin/addApplication?clientId=" + id + "&action=detail"
}