var baseUrl = window.location.origin;
$(function () {
    getInClientCount();
    getOutClientCount();
    getInUserCount();
    getOutUserCount();
    initOperateData();
    initLoginData();
    initInformation();
    initOpinion();
});

/**
 * 获取内部应用的数量
 */
function getInClientCount() {
    $.ajax({
        url: baseUrl + '/customer/client/selectInClientCount',
        async: false,
        method: 'GET',
        contentType: 'application/json',
        success: function (result) {
            $("#inClientTotalCount").html(result.data)
        }
    });
}
/**
 * 获取外部应用的数量
 */
function getOutClientCount() {
    $.ajax({
        url: baseUrl + '/customer/client/selectOutClientCount',
        async: false,
        method: 'GET',
        contentType: 'application/json',
        success: function (result) {
            $("#outClientTotalCount").html(result.data)
        }
    });
}

/**
 * 获取内部用户的数量
 */
function getInUserCount() {
    $.ajax({
        url: baseUrl + '/admin/adminUser/selectInUserCount',
        async: false,
        method: 'GET',
        contentType: 'application/json',
        success: function (result) {
            $("#inUserCount").html(result.data)
        }
    });
}

/**
 * 获取外部用户的数量
 */
function getOutUserCount() {
    $.ajax({
        url: baseUrl + '/admin/adminUser/selectOutUserCount',
        async: false,
        method: 'GET',
        contentType: 'application/json',
        success: function (result) {
            $("#outUserCount").html(result.data)
        }
    });
}

function initOperateData() {
    $('#operatePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#operatePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "admin/systemLog/getList", // 获取数据的地址
        pagination: true, // 启动分页
        cache: true,
        maintainSelected: true,
        striped: true, // 表格显示条纹
        pageNumber: 1, // 当前第几页
        pageSize: 6, // 每页显示的记录数
        pageList: [6], // 记录数可选列表
        formatNoMatches: function () {  //没有匹配的结果
            return '没有找到匹配的记录';
        },
        sidePagination: "server", // 表示服务端分页
        // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        // 设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        idField: "id",
        columns: [{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'operateTime',
            title: '操作时间'
        },{
            field: 'operatorName',
            title: '操作人员'
        },{
            field: 'modules',
            title: '所属模块'
        },{
            field: 'operateType',
            title: '操作类型'
        },{
            field: 'ipAddressStr',
            title: 'IP'
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = 1;
            data['pageSize'] = 6;
            return JSON.stringify(data);
        }
    });
}

function initLoginData() {
    $('#loginPagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#loginPagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "admin/systemLog/getLoginLogList", // 获取数据的地址
        pagination: true, // 启动分页
        cache: true,
        maintainSelected: true,
        striped: true, // 表格显示条纹
        pageNumber: 1, // 当前第几页
        pageSize: 6, // 每页显示的记录数
        pageList: [6], // 记录数可选列表
        formatNoMatches: function () {  //没有匹配的结果
            return '没有找到匹配的记录';
        },
        sidePagination: "server", // 表示服务端分页
        // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        // 设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        idField: "id",
        columns: [{
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },{
            field: 'createTime',
            title: '操作时间'
        },{
            field: 'userName',
            title: '操作人员'
        },{
            field: 'loginName',
            title: '账号'
        },{
            field: 'operateType',
            title: '操作类型',
            formatter: function (value, row, index) {
                if (value == 1) {
                    return "登录系统";
                }
                if (value == 2) {
                    return "登出系统";
                }
            }
        },{
            field: 'operateLog',
            title: '操作内容'
        },{
            field: 'ipAddress',
            title: 'IP'
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = 1;
            data['pageSize'] = 6;
            return JSON.stringify(data);
        }
    });
}

// 消息
function initInformation() {
    var data = {};
    data['pageSize'] = 4;
    data['pageNumber'] = 1;
    $.ajax({
        url: baseUrl + '/admin/information/getInformationList',
        method: 'POST',
        async: false,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (result) {
            var str = '';
            for (var i = 0; i < result.rows.length; i++) {
                str += "<tr>" +
                    "<td>"+result.rows[i].title+"</td>" +
                    "<td>"+result.rows[i].createTimeStr.split(' ')[0]+"</td>" +
                    "</tr>"
            }
            $("#informationList").html(str);
        }
    });
}

// 意见
function initOpinion() {
    var data = {};
    data['pageSize'] = 4;
    data['pageNumber'] = 1;
    $.ajax({
        url: baseUrl + '/admin/opinion/getOpinionList',
        method: 'POST',
        async: false,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (result) {
            var str = '';
            for (var i = 0; i < result.rows.length; i++) {
                str += "<tr>" +
                    "<td>"+result.rows[i].opinionTitle+"</td>" +
                    "<td>"+result.rows[i].createTimeStr.split(' ')[0]+"</td>" +
                    "</tr>"
            }
            $("#opinionList").html(str);
        }
    });
}