$(function () {
    $('#operateTime').daterangepicker({
        timePicker: true,
        timePicker24Hour: true,
        timePickerIncrement: 1,
        locale: {
            format: 'YYYY-MM-DD HH:mm',
            applyLabel:'确认',
            cancelLabel:'取消',
            fromLabel:'从',
            toLabel:'到',
            weekLabel:'W',
            customRangeLabel:'选择时间',
            daysOfWeek:["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
        }
    });

    // 初始化数据
    initData();
});

function initData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "admin/systemLog/getList", // 获取数据的地址
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
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['modules'] = $("#modules").val().trim();
            data['operateType'] = $("#operateType").val().trim();
            data['operatorName'] = $("#operatorName").val().trim();
            data['startTime'] = $("#operateTime").val().split(" - ")[0];
            data['endTime'] = $("#operateTime").val().split(" - ")[1];
            return JSON.stringify(data);
        }
    });
}

/**
 * 所属模块回车搜索
 */
$("#modules").bind('keypress', function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        initData();
    }
});

/**
 * 操作类型回车搜索
 */
$("#operateType").bind('keypress', function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        initData();
    }
});

/**
 * 操作人员回车搜索
 */
$("#operatorName").bind('keypress', function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        initData();
    }
});

/**
 * 时间框改变搜索
 */
$("#operateTime").change(function () {
    initData();
});

/**
 * 重置
 */
function resetData() {
    $("#modules").val("");
    $("#operateType").val("");
    $("#operatorName").val("");
    var currentDate = dateFmt(new Date());
    var val = currentDate + ' 00:00 - ' + currentDate + " 23:59";
    $("#operateTime").val(val);
    $('#operateTime').daterangepicker({
        timePicker: true,
        timePicker24Hour: true,
        timePickerIncrement: 1,
        locale: {
            format: 'YYYY-MM-DD HH:mm',
            applyLabel:'确认',
            cancelLabel:'取消',
            fromLabel:'从',
            toLabel:'到',
            weekLabel:'W',
            customRangeLabel:'选择时间',
            daysOfWeek:["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
        }
    });
    initData();
}