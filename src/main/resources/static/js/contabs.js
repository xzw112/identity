
$(function () {
    //计算元素集合的总宽度
    function calSumWidth(elements) {
        var width = 0;
        $(elements).each(function () {
            width += $(this).outerWidth(true);
        });
        return width;
    }
    //滚动到指定选项卡
    function scrollToTab(element) {
        var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(element).nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        }, "fast");
    }
    //查看左侧隐藏的选项卡
    function scrollTabLeft() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".J_menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {//找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).prev();
                }
                scrollVal = calSumWidth($(tabElement).prevAll());
            }
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        }, "fast");
    }
    //查看右侧隐藏的选项卡
    function scrollTabRight() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".J_menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {//找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
            if (scrollVal > 0) {
                $('.page-tabs-content').animate({
                    marginLeft: 0 - scrollVal + 'px'
                }, "fast");
            }
        }
    }

    //通过遍历给菜单项加上data-index属性
    $(".J_menuItem").each(function (index) {
        if (!$(this).attr('data-index')) {
            $(this).attr('data-index', index);
        }
    });

    function menuItem() {
    	$(".J_menuItem").removeClass("active");
    	$(this).addClass("active").parent("li").siblings().find("a").removeClass("active");
        // 获取标识数据
        var dataUrl = $(this).attr('href'),
            dataIndex = $(this).data('index'),
            menuName = $.trim($(this).text()),
            flag = true;
        if (dataUrl === undefined || $.trim(dataUrl).length === 0){
        	return false;
        }
        if(dataUrl === "admin/home" || dataUrl === "admin/athlete_expire_index" || dataUrl === "admin/athlete_return_index"
                || dataUrl === "admin/sysMsg_index" || dataUrl === "admin/notice_index"){
        	$(".has-submenu").removeClass("menu-open");
        	$(".has-submenu ul").hide();
        	$(".has-submenu ul .J_menuItem").removeClass("active");
        }
        
        //更改标题
        changeTitle(dataUrl);

        // 选项卡菜单已存在
        $('.J_menuTab').each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                    scrollToTab(this);
                    // 显示tab对应的内容区
                    $('.J_mainContent J_iframe').each(function () {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.J_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });
        
        // 选项卡菜单不存在
        if (flag) {
            var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $('.J_menuTab').removeClass('active');

            // 添加选项卡对应的iframe
            var str1 = '<iframe class="J_iframe single-page-main" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '"></iframe>';
            $('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

            // 添加选项卡
            $('.J_menuTabs .page-tabs-content').append(str);
            scrollToTab($('.J_menuTab.active'));
        }
        return false;
    }

    $('.J_menuItem').on('click', menuItem);

    /**
     * 切换标题
     * @returns
     */
    function changeTitle(url){
    	var title = "";
    	//获取最后一个/的位置
        var site = url.lastIndexOf("\/");
        //截取最后一个/后的值
        var str = url.substring(site + 1, url.length);
    	// switch (str) {
    	// 	case "home":
    	// 		title = "首页";
    	// 		break;
    	// 	case "new_athlete_index":
    	// 		title = "运动员管理-运动员注册";
    	// 		break;
    	// 	case "athlete_confirm_index":
    	// 		title = "运动员管理-年度确认运动员";
    	// 		break;
    	// 	case "athlete_report_record_index":
    	// 		title = "运动员管理-上报记录";
    	// 		break;
    	// 	case "athlete_info_index":
    	// 		title = "运动员管理-运动员查询";
    	// 		break;
    	// 	case "athlete_pending_index":
    	// 		title = "运动员管理-运动员审核";
    	// 		break;
    	// 	case "athlete_expire_index":
    	// 		title = "协议到期运动员";
    	// 		break;
    	// 	case "athlete_return_index":
    	// 		title = "退回运动员";
    	// 		break;
    	// 	case "physical_test_index":
    	// 		title = "技术档案-体测信息";
    	// 		break;
    	// 	case "athlete_archives_index":
    	// 		title = "技术档案-运动员档案";
    	// 		break;
    	// 	case "field_validation_index":
    	// 		title = "赛场验证-赛场验证";
    	// 		break;
    	// 	case "validation_record_index":
    	// 		title = "赛场验证-验证记录";
    	// 		break;
    	// 	case "enterprise_statistics_index":
    	// 		title = "数据统计-单位统计";
    	// 		break;
        //     case "item_statistics_index":
        //         title = "数据统计-项目统计";
        //         break;
    	// 	case "statistics_report_index":
    	// 		title = "数据统计-统计报表";
    	// 		break;
    	// 	case "sysMsg_manage_index":
    	// 		title = "消息公告-系统消息";
    	// 		break;
    	// 	case "notice_manage_index":
    	// 		title = "消息公告-通知公告";
    	// 		break;
    	// 	case "sysMsg_index":
    	// 		title = "系统消息";
    	// 		break;
    	// 	case "notice_index":
    	// 		title = "通知公告列表";
    	// 		break;
    	// 	case "personalInfo_index":
    	// 		title = "个人中心-个人资料";
    	// 		break;
    	// 	case "modifyPassword_index":
    	// 		title = "个人中心-修改密码";
    	// 		break;
    	// 	case "enterprise_index":
    	// 		title = "组织架构-单位管理";
    	// 		break;
    	// 	case "adminUser_index":
    	// 		title = "组织架构-人员管理";
    	// 		break;
    	// 	case "role_index":
    	// 		title = "组织架构-角色管理";
    	// 		break;
    	// 	case "itemAuth_index":
    	// 		title = "组织架构-项目权限";
    	// 		break;
    	// 	case "timeManage_index":
    	// 		title = "系统设置-时间管理";
    	// 		break;
    	// 	case "systemLog_index":
    	// 		title = "系统设置-操作日志";
    	// 		break;
    	// 	case "regItem_index":
    	// 		title = "系统设置-数据字典-注册项目";
    	// 		break;
    	// 	case "behalfUnit_index":
    	// 		title = "系统设置-数据字典-代表单位";
    	// 		break;
    	// 	default:
    	// 		break;
    	// }
    	//$("#navigation_title").html(title);
    }
    
    // 关闭选项卡菜单
    function closeTab() {
        var closeTabId = $(this).parents('.J_menuTab').data('id');
        var currentWidth = $(this).parents('.J_menuTab').width();

        // 当前元素处于活动状态
        if ($(this).parents('.J_menuTab').hasClass('active')) {

            // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
            if ($(this).parents('.J_menuTab').next('.J_menuTab').size()) {

                var activeId = $(this).parents('.J_menuTab').next('.J_menuTab:eq(0)').data('id');
                $(this).parents('.J_menuTab').next('.J_menuTab:eq(0)').addClass('active');

                $('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.J_iframe').hide();
                        return false;
                    }
                });

                var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                if (marginLeftVal < 0) {
                    $('.page-tabs-content').animate({
                        marginLeft: (marginLeftVal + currentWidth) + 'px'
                    }, "fast");
                }

                //  移除当前选项卡
                $(this).parents('.J_menuTab').remove();

                // 移除tab对应的内容区
                $('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }

            // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
            if ($(this).parents('.J_menuTab').prev('.J_menuTab').size()) {
                var activeId = $(this).parents('.J_menuTab').prev('.J_menuTab:last').data('id');
                $(this).parents('.J_menuTab').prev('.J_menuTab:last').addClass('active');
                $('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.J_iframe').hide();
                        return false;
                    }
                });

                //  移除当前选项卡
                $(this).parents('.J_menuTab').remove();

                // 移除tab对应的内容区
                $('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }
        }
        // 当前元素不处于活动状态
        else {
            //  移除当前选项卡
            $(this).parents('.J_menuTab').remove();

            // 移除相应tab对应的内容区
            $('.J_mainContent .J_iframe').each(function () {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
            scrollToTab($('.J_menuTab.active'));
        }
        return false;
    }

    $('.J_menuTabs').on('click', '.J_menuTab i', closeTab);

    //关闭其他选项卡
    function closeOtherTabs(){
        $('.page-tabs-content').children("[data-id]").not(":first").not(".active").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').css("margin-left", "0");
    }
    $('.J_tabCloseOther').on('click', closeOtherTabs);

    //滚动到已激活的选项卡
    function showActiveTab(){
        scrollToTab($('.J_menuTab.active'));
    }
    $('.J_tabShowActive').on('click', showActiveTab);


    // 点击选项卡菜单
    function activeTab() {
        if (!$(this).hasClass('active')) {
            var currentId = $(this).data('id');
            // 显示tab对应的内容区
            $('.J_mainContent .J_iframe').each(function () {
                if ($(this).data('id') == currentId) {
                    $(this).show().siblings('.J_iframe').hide();
                    return false;
                }
            });
            $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
            scrollToTab(this);
        }
    }

    $('.J_menuTabs').on('click', '.J_menuTab', activeTab);

    //刷新iframe
    function refreshTab() {
        var target = $('.J_iframe[data-id="' + $(this).data('id') + '"]');
        var url = target.attr('src');
//        //显示loading提示
//        var loading = layer.load();
//        target.attr('src', url).load(function () {
//            //关闭loading提示
//            layer.close(loading);
//        });
    }

    $('.J_menuTabs').on('dblclick', '.J_menuTab', refreshTab);

    // 左移按扭
    $('.J_tabLeft').on('click', scrollTabLeft);

    // 右移按扭
    $('.J_tabRight').on('click', scrollTabRight);

    // 关闭全部
    $('.J_tabCloseAll').on('click', function () {
        $('.page-tabs-content').children("[data-id]").not(":first").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').children("[data-id]:first").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').show();
            $(this).addClass("active");
        });
        $('.page-tabs-content').css("margin-left", "0");
    });

});
