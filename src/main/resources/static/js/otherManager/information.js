$(function () {
    loadData();
});
var isDel = 1;
// tab 切换
$(".tag li").click(function () {
    $(this).addClass("active").siblings().removeClass("active");
    var tagactive = $(this).index();
    if (tagactive == 0) {
       $("#informationDelStatus").val(1);
       $("#addInformationDiv").show();
        isDel = 1;
    }
    if (tagactive == 1) {
        $("#informationDelStatus").val(0);
        $("#addInformationDiv").hide();
        isDel = 0;
    }
    $(".part .item").eq(tagactive).show().siblings().hide();
    loadData();
});

// 初始化数据
// 加载数据
function loadData() {
    $('#examplePagination').bootstrapTable('destroy');
    // 初始化表格,动态从服务器加载数据
    $("#examplePagination").bootstrapTable({
        method: "POST", // 使用get请求到服务器获取数据
        contentType: "application/json;charset=UTF-8",
        url: "/admin/information/getInformationList", // 获取数据的地址
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
            field: 'informationType',
            title: '消息类型',
            formatter: function (value, row, index) {
                if (value == 1) {
                    return '通知';
                } else if (value == 2) {
                    return '公告';
                } else if (value == 3) {
                    return '系统消息';
                } else {
                    return '-';
                }

            }
        },{
            field: 'title',
            title: '标题'
        },{
            field: 'sendType',
            title: '发送对象',
            formatter: function (value, row, index) {
                if (value == 1) {
                    return '内部用户';
                } else if (value == 2) {
                    return '外部用户';
                } else if (value == 3) {
                    return '内外部用户';
                } else {
                    return '-';
                }
            }
        },{
            field: 'isRelease',
            title: '是否发布',
            formatter: function (value, row, index) {
                var str = "";
                if (value == 1) {
                    str = "是";
                } else if (value == 0) {
                    str = "否";
                }
                return str;
            }
        },{
            field: 'userName',
            title: '创建人'
        },{
            field: 'loginName',
            title: '创建人账号'
        },{
            field: 'releaseTime',
            title: '发布时间'
        },{
            field: 'operate',
            title: '操作',
            formatter: btnGroup,
            events: {
                'click #changeSend': function (event, value, row, index) {
                    changeSend(row);
                },
                'click #informationDetail': function (event, value, row, index) {
                    detail(row);
                },
                'click #informationEdit': function (event, value, row, index) {
                    edit(row);
                },
                'click #informationDel': function (event, value, row, index) {
                    updateDel(row.id);
                },
                'click #reSend': function (event, value, row, index) {
                    reSend(row);
                },
                'click #del': function (event, value, row, index) {
                    del(row);
                }
            }
        }],
        queryParams: function queryParams(params) {
            // 设置查询参数
            var data = {};
            data['pageNumber'] = params.pageNumber;
            data['pageSize'] = params.pageSize;
            data['title'] = $("#searchtitle").val();
            data['isDel'] = isDel;
            data['sendType'] = $("#sendType").val();
            data['informationType'] = $("#informationType").val();
            return JSON.stringify(data);
        }
    });
}

// 搜索
$("#searchBtn").click(function () {
    loadData();
});

// 清空
$("#clearBtn").click(function () {
    $("#searchtitle").val('');
    $("#sendType").val('');
    $("#informationType").val('');
    loadData();
});

// 自定义方法，添加操作按钮
function btnGroup() {
    // data-target="xxx" 为点击按钮弹出指定名字的模态框
    var informationDelStatus = $("#informationDelStatus").val();
    var str = '';
    if (informationDelStatus == 1) {
        str = '<a href="####"  id="changeSend" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="撤销/发布">撤销/发布' +
            '</a>'+
            '<a href="####"  id="informationEdit" data-toggle="modal" data-target="#editinfo" ' +
            'style="margin-left:15px" title="修改" >修改' +
            '</a>'+
            '<a href="####"  id="informationDel" data-toggle="modal" data-target="#deleteuser" ' +
            'style="margin-left:15px" title="删除">删除' +
            '</a>';
    } else if (informationDelStatus == 0) {
        str = '<a href="####"  id="reSend" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="还原">还原' +
            '</a>'+
            '<a href="####"  id="del" data-toggle="modal" data-target="#deleteuser" ' +
            'style="margin-left:15px" title="删除">删除' +
            '</a>';
    }
    var html =
         str +
        '<a href="####"  id="informationDetail" data-toggle="modal" data-target="#editrole" style="margin-left:15px" title="详情" >详情' +
        '</a>'
    return html
};


// 跳转添加消息
$("#addInformation").click(function () {
    $("#title_name").html('发布消息');
    $("#newsType").val("");
    $("#title").val("");
    $("#targetType").val("");
    $("#sw_value").val(0);
    ue.ready(function(){
        ue.setContent('');
    });
    $("[name='my-checkbox']").bootstrapSwitch('state', false);
    $("#modal-default").modal('show');
});
$("#save_btn").click(function () {
    addOrEdit();
});
// 添加消息
function addOrEdit() {
    var informationType = $("#newsType").val();
    var title = $("#title").val();
    var sendType = $("#targetType").val();
    var isRelease = $("#sw_value").val();
    var content = ue.getContent();
    if (informationType == null || informationType == '') {
        return narn('error', "请选择消息类型！");
        return ;
    }
    if (title == null || title == '') {
        return narn('error', "消息标题不能为空！");
        return ;
    }
    if (sendType == null || sendType == '') {
        return narn('error', "请选择发送对象！");
        return ;
    }
    if (content == null || content == '') {
        return narn('error', "请填写消息内容！");
        return ;
    }
    var data = {};
    data['informationType'] = informationType;
    data['title'] = title;
    data['sendType'] =sendType;
    data['isRelease'] = isRelease;
    data['content'] = content;
    var id = $("#id").val();
    if (id == null || id == '') {
        url = '/admin/information/add';
    } else if (id != '' && id != null) {
        data['id'] = id;
        url = '/admin/information/updateInformation'
    }
    $.ajax({
        url: baseUrl + url,
        async: false,
        data: JSON.stringify(data),
        type: 'POST',
        contentType: 'application/json',
        success: function (result) {
            narn('success', result.message);
            $("#modal-default").modal('hide');
            loadData();
        }
    });
}
// 发布、撤销
function changeSend(row) {
    $("#modal-confirm").modal('show');
    var data = {};
    data['id'] = row.id;
    data['release'] = row.isRelease;
    $("#confirm_btn").off('click').click(function () {
        $.ajax({
            url: baseUrl + '/admin/information/updateInformationStatus',
            async: false,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (result) {
                if (result.code == 1) {
                    narn('success', result.message);
                } else {
                    narn('error', result.message);
                }
                loadData();
                $("#modal-confirm").modal('hide');
            }
        });
    });

}

function detail(row) {
    $("#title_name").html('消息详情');
    $("#newsType").val(row.informationType);
    $("#title").val(row.title);
    $("#targetType").val(row.sendType);
    $("#sw_value").val(row.isRelease);
    if (row.isRelease == 1) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else if (row.isRelease == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    ue.ready(function(){
        ue.setContent(row.content);
    });
    $("#save_btn").hide();
    $("#modal-default").modal('show');
}

function edit(row) {
    $("#title_name").html('编辑消息');
    $("#id").val(row.id);
    $("#newsType").val(row.informationType);
    $("#title").val(row.title);
    $("#targetType").val(row.sendType);
    $("#sw_value").val(row.isRelease);
    if (row.isRelease == 1) {
        $("[name='my-checkbox']").bootstrapSwitch('state', true);
    } else if (row.isRelease == 0) {
        $("[name='my-checkbox']").bootstrapSwitch('state', false);
    }
    ue.ready(function(){
        ue.setContent(row.content);
    });
    $("#save_btn").show();
    $("#modal-default").modal('show');
}

function updateDel(id) {
    if (id != null && id != '') {
        $("#modal-confirm").modal('show');
        $("#confirm_btn").off('click').click(function () {
          $.ajax({
              url: baseUrl + '/admin/information/updateDel?id=' + id,
              async: false,
              contentType: 'application/json',
              type: 'POST',
              success: function (result) {
                  if (result.code == 1) {
                      narn('success', result.message);
                  } else {
                      narn('error', result.message);
                  }
                  loadData();
                  $("#modal-confirm").modal('hide');
              }
          });
        })
    }
}

 /**
 * 还原
 * @param row
 */
function reSend(row) {
     $("#modal-confirm").modal('show');
     $("#confirm_btn").off('click').click(function () {
         $.ajax({
             url: baseUrl + '/admin/information/unDel?id=' + row.id,
             async: false,
             contentType: 'application/json',
             type: 'POST',
             success: function (result) {
                 if (result.code == 1) {
                     narn('success', result.message);
                 } else {
                     narn('error', result.message);
                 }
                 loadData();
                 $("#modal-confirm").modal('hide');
             }
         });
     });
}

/**
 * 删除
 * @param row
 */
function del(row) {
    $("#modal-confirm").modal('show');
    $("#confirm_btn").off('click').click(function () {
        $.ajax({
            url: baseUrl + '/admin/information/del?id=' + row.id,
            async: false,
            contentType: 'application/json',
            type: 'POST',
            success: function (result) {
                if (result.code == 1) {
                    narn('success', result.message);
                } else {
                    narn('error', result.message);
                }
                loadData();
                $("#modal-confirm").modal('hide');
            }
        });
    });
}