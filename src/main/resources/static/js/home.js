var regYear;
$(function () {
    $.ajax({
        type : "POST",
        url : "admin/base/timeData",
        async:false,
        contentType:'application/json;charset=UTF-8',
        success : function(res) {
            if(res.code === 1){
                regYear = res.data;
                //判断是否有到期运动员
                showExpireDialog();
                //展示统计数字
                showStatisticNumber();
                //展示饼状图
                showPieChart();
            }else{
                narn("error", res.message);
            }
        }
    });
    //展示系统消息
    showSysMsg();
    //展示通知公告
    showNotice();
});

/**
 * 显示统计数据
 */
function showStatisticNumber() {
    var data = {};
    data['regYear'] = regYear;
    $.ajax({
        type: "POST",
        url: "admin/home/selectStatisticNumber",
        async: true,
        data: JSON.stringify(data),
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            if (res.code === 1) {
                $("#firstRegCount").html(res.data.firstRegCount);
                $("#reRegCount").html(res.data.reRegCount);
                $("#yearConfirmCount").html(res.data.yearConfirmCount);
                $("#auditPassCount").html(res.data.auditPassCount);
                $("#auditReturnCount").html(res.data.auditReturnCount);
            } else {
                narn("error", res.message);
            }
        }
    });
}

/**
 * 饼图展示
 */
function showPieChart(){
    var data = {};
    data['regYear'] = regYear;
    $.ajax({
        type: "POST",
        url: "admin/home/selectPieData",
        async: true,
        data: JSON.stringify(data),
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            if (res.code === 1) {
                var pieData = res.data;
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('chart'));
                option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom'
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel'],
                                option: {
                                    funnel: {
                                        x: '25%',
                                        width: '50%',
                                        funnelAlign: 'center',
                                        max: 1548
                                    }
                                }
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    //设置饼状图每个颜色块的颜色
                    color : [ "#9860E5", "#889BEA", "#8A7CD4", "#B58BEF", "#759FEB", "#4ECCCB", "#58CA73", "#87E1A1",
                        "#F9D247", "#EAA675", "#73B987", "#D26517", "#DC81D2", "#F2637B", "#88D1EA"],
                    series: [
                        {
                            name: '注册量占比',
                            legendHoverLink:true,
                            type: 'pie',
                            radius : ['50%', '70%'],
                            itemStyle : {
                                normal : {
                                    label : {
                                        show : false
                                    },
                                    labelLine : {
                                        show : false
                                    }
                                },
                                emphasis : {
                                    label : {
                                        show : true,
                                        position : 'center',
                                        textStyle : {
                                            fontSize : '30',
                                            fontWeight : 'bold'
                                        }
                                    }
                                }
                            },
                            data: pieData
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            } else {
                narn("error", res.message);
            }
        }
    });
}

/**
 * 系统消息
 */
function showSysMsg() {
    var data = {};
    data['pageNumber'] = 1;
    data['pageSize'] = 5;
    data['status'] = 1;
    $.ajax({
        type: "POST",
        url: "admin/sysMsg/getViewList",
        data: JSON.stringify(data),
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            var list = res.rows;
            for(var i = 0; i < list.length; i++){
                var item = '<div class="home-note-item">' +
                            '   <p class="txt">' + list[i].content + '</p>' +
                            '   <span class="time">' + list[i].publishTime + '</span>' +
                            '</div>';
                $("#sysMsgDiv").append(item);
            }
        }
    });
}

/**
 * 通知公告
 */
function showNotice() {
    var data = {};
    data['pageNumber'] = 1;
    data['pageSize'] = 5;
    data['status'] = 1;
    $.ajax({
        type: "POST",
        url: "admin/notice/getList",
        data: JSON.stringify(data),
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            var list = res.rows;
            for(var i = 0; i < list.length; i++){
                var item = '<div class="home-note-item home-note-item1">' +
                    '   <p class="txt">' + list[i].title + '</p>' +
                    '   <span class="time">' + list[i].publishTime + '</span>' +
                    '</div>';
                $("#noticeDiv").append(item);
            }
        }
    });
}

/**
 * 到期运动员
 */
function showExpireDialog() {
    var data = {};
    data['pageNumber'] = 1;
    data['pageSize'] = 5;
    data['isAgreementExpire'] = 1;
    data['queryCurrentTable'] = 1;
    data['regYear'] = regYear;
    $.ajax({
        type: "POST",
        url: "admin/athleteApply/getAthleteList",
        data: JSON.stringify(data),
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            var list = res.rows;
            if(list != null && list.length > 0){
                $('.home-dialog').show();

                var title = '<tr><th>姓名</th><th>证件号</th><th>协议开始时间</th><th>协议结束时间</th></tr>';
                $("#expireTable").append(title);
                for(var i = 0; i < list.length; i++){
                    var item = '<tr>' +
                                '   <td>' + list[i].name + '</td>' +
                                '   <td>' + list[i].certificateNo + '</td>' +
                                '   <td>' + list[i].agreementStartTime + '</td>' +
                                '   <td>' + list[i].agreementEndTime + '</td>' +
                                '</tr>';
                    $("#expireTable").append(item);
                }

                $('.close').click(function(){
                    $('.home-dialog').hide();
                });
            }
        }
    });
}

/**
 * 跳转到系统消息列表
 */
function goSysMsg() {
    parent.document.getElementById("sysMsg").classList.add("active");
    parent.document.getElementById("sysMsg").click();
}

/**
 * 跳转到通知公告列表
 */
function goNotice() {
    parent.document.getElementById("notice").classList.add("active");
    parent.document.getElementById("notice").click();
}

/**
 * 跳转到协议到期运动员列表
 */
function goAthleteExpire() {
    parent.document.getElementById("athlete-expire").classList.add("active");
    parent.document.getElementById("athlete-expire").click();
}

