$(function () {
    loadData();
});


function loadData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: baseUrl + "/admin/opinion/getOpinionList", // 获取数据的地址
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
            field: 'opinionType',
            title: '消息类型',
            formatter: function (value, row, index) {
                if (value == 1) {
                    return '建议';
                } else if (value == 2) {
                    return '意见';
                } else if (value == 3) {
                    return '问题反馈';
                } else if (value == 4) {
                    return '其他';
                } else {
                    return '-';
                }
            }
        }, {
            field: 'opinionTitle',
            title: '标题'
        }, {
            field: 'opinionContent',
            title: '意见内容',
            formatter: function (value, row, index, field) {
                return "<span style='display: block;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;' title='" + value + "'>" + value + "</span>"
            },
            cellStyle: function (value, row, index, field) {
                return {
                    css: {
                        "white-space": "nowrap",
                        "text-overflow": "ellipsis",
                        "overflow": "hidden",
                        "max-width": "150px"
                    }
                }
            }
        }, {
            field: 'userName',
            title: '提交人'
        }, {
            field: 'createTimeStr',
            title: '提交时间'
        }, {
            field: 'operate',
            title: '操作',
            formatter: btnGroup,
            events: {
                'click #opinionDetail': function (event, value, row, index) {
                    detail(row);
                }
            }
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['opinionTitle'] = $("#searchtitle").val();
            data['opinionType'] = $("#opinionType").val();
            return JSON.stringify(data);
        }
    });
}

// 自定义方法，添加操作按钮
function btnGroup() {
    var html =
        '<a href="####"  id="opinionDetail" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="详情" >详情' +
        '</a>';
    return html
};

/**
 * 详情
 * @param row
 */
function detail(row) {
    $("#title_name").html("意见详情");
    $("#modal-default").modal('show');
    $("#opinionContent").val(row.opinionContent);
    $("#opinion_type").val(row.opinionType);
    $("#title").val(row.opinionTitle);
    var imgUrl = row.fileId.split(',');
    var imgName = row.fileName.split(',');
    if (imgUrl.length > 0) {
        var str = '';
        for (var i = 0; i < imgUrl.length; i++) {
            str += '<a type="" href="' + baseUrl + '/admin/file/downloadFile?fileId=' + imgUrl[i] + '&fileName=' + imgName[i] + '">' + imgName[i] + '</a>'
        }
        $("#opinionFile").html(str);
    }
}

/**
 * 搜索
 */
$("#searchBtn").click(function () {
    loadData();
});

/**
 * 清空
 */
$("#clearBtn").click(function () {
    $("#searchtitle").val("");
    $("#opinionType").val("")
    loadData();
});
