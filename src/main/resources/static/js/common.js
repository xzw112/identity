$(function(){

	//去除历史记录
	$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

	// 在模态框出现后添加可拖拽功能
	/*$(document).on("show.bs.modal", ".modal", function() {
		// draggable 属性规定元素是否可拖动
		$(this).draggable({
			// handle: ".modal-header", // 只能点击头部拖动
			cursor: 'move' //光标呈现为指示链接的指针（一只手）,
		});
	});*/
});

/**
 * 页面回退
 * @returns
 */
function go_back(){
	history.go(-1);
}

/**
 * 校验联系方式
 * @param obj
 * @returns
 */
function telReg(obj){
	if(/[\d]([-])*/.test(obj)){
		return true;
	}else{
		return false;
	}
}

/**
 * 提示信息
 * @param type
 * @param msg
 */
function narn(type, msg) {
	naranja()[type]({
		title: msg,
		timeout: '2000'
	});
}

/**
 * 浮点型数据相加
 * @param arg
 * @returns {number}
 */
Number.prototype.add = function (arg) {
	var r1, r2, m;
	try {
		r1 = this.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (this * m + arg * m) / m
};

/**
 * 上传图片
 * @returns
 */
function uploadImg(_this){
	var file = $(_this).attr("id");
	var photo = file.substring(0, file.length-5);
	// 判断浏览器类型
	var ieVersion = IEVersion();
	var uploadUrl = "admin/file/upload";
	// IE11时
	if(ieVersion == 11){
		if(_this.val() !== ''){
			if(fileChangeNo(file) === false){
				return;
			}
			$.ajaxFileUpload({
				url: uploadUrl,
				secureuri:false,
				dataType: 'json',
				fileElementId : [file],
				success: function (res){//上传成功
					if(res.code === 0){
						narn("error", "只支持png, jpg, jpeg格式");
						return;
					}
					$("#" + photo + "_img").attr("src", "admin/file/downloadFile?fileId=" + res.data);
					$("#" + photo + "").val(res.data);
				},
				error: function(){
					narn("error", "上传失败");
				}
			});
		}
	}else{
		$(_this).change(function(){
			if(document.getElementById(file).value === ""){
				return;
			}
			if(fileChangeNo(file) === false){
				document.getElementById(file).value = "";
				return;
			}
			$.ajaxFileUpload({
				url: uploadUrl,
				secureuri:false,
				dataType: 'json',
				fileElementId : [file],
				success: function (res){
					if(res.code === 0){
						narn("error", "只支持png, jpg, jpeg格式");
						document.getElementById(file).value = "";
						return;
					}
					$("#" + photo + "_img").attr("src", "admin/file/downloadFile?fileId=" + res.data);
					$("#" + photo + "").val(res.data);
					document.getElementById(file).value = "";
				},
				error: function(){
					narn("error", "上传失败");
				}
			});
		});
	}
}

/**
 * 上传Base64格式的图片
 * @returns
 */
function uploadBase64Img(selectId, base64Url){
	var data = {};
	data['base64Url'] = base64Url;
	var uploadUrl = "admin/file/uploadBase64";
	$.ajax({
		type: 'POST',
		url: uploadUrl,
		dataType: 'json',
		data: JSON.stringify(data),
		async: true,
		contentType: 'application/json;charset=UTF-8',
		success: function (res) {
			if(res.code === 0){
				narn("error", "只支持png, jpg, jpeg格式");
				return;
			}
			$("#photo0_img").attr("src", "admin/file/downloadFile?fileId=" + res.data);
			$("#photo0").val(res.data);
		},
		error: function(){
			narn("error", "上传失败");
		}
	});
}

/**
 * 日期格式转化
 * @param fmt
 * @param date
 * @returns
 */
function dateFtt(fmt,date){   
	var o = {   
		"M+" : date.getMonth()+1,                 //月份   
		"d+" : date.getDate(),                    //日   
		"h+" : date.getHours(),                   //小时   
		"m+" : date.getMinutes(),                 //分   
		"s+" : date.getSeconds(),                 //秒   
    	"q+" : Math.floor((date.getMonth()+3)/3), //季度   
    	"S"  : date.getMilliseconds()             //毫秒   
	};   
	if(/(y+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));  
	}   
	for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		}   
	}   
	return fmt;   
} 

//年月日时分秒格式化显示
function dateTimeFmt(value){
    var crtTime = new Date(value);
    return dateFtt("yyyy-MM-dd hh:mm:ss",crtTime);
}

//年月日格式化显示
function dateFmt(value){
    var crtTime = new Date(value);
    return dateFtt("yyyy-MM-dd",crtTime);
}

/**
 * 输入数字和小数点
 * @param obj
 * @returns
 */
function clearNoNum(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value = parseFloat(obj.value); 
    } 
}

/**
 * 只能输入数字
 * @param obj
 * @returns
 */
function onlyNum(obj){ 
    obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value = parseInt(obj.value); 
    } 
}

//刷新图片  
function changeImg() {
	var imgSrc = $("#imgObj");
	var src = imgSrc.attr("src");
	imgSrc.attr("src", changeUrl(src));
}

//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
function changeUrl(url) {
	var timestamp = (new Date()).valueOf();
	var index = url.indexOf("?", url);
	if (index > 0) {
		url = url.substring(index, url.indexOf(url, "?"));
	}
	if ((url.indexOf("&") >= 0)) {
		url = url + "×tamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	return url;
}

// 获取IE版本
/**
 * @return {number}
 */
function IEVersion() {
	// 取得浏览器的userAgent字符串
	var userAgent = navigator.userAgent;
	// 判断是否为小于IE11的浏览器
	var isLessIE11 = userAgent.indexOf('compatible') > -1 && userAgent.indexOf('MSIE') > -1;
	// 判断是否为IE的Edge浏览器
	var isEdge = userAgent.indexOf('Edge') > -1 && !isLessIE11;
	// 判断是否为IE11浏览器
	var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf('rv:11.0') > -1;
	if (isLessIE11) {
		var IEReg = new RegExp('MSIE (\\d+\\.\\d+);');
		// 正则表达式匹配浏览器的userAgent字符串中MSIE后的数字部分，，这一步不可省略！！！
		IEReg.test(userAgent);
		// 取正则表达式中第一个小括号里匹配到的值
		var IEVersionNum = parseFloat(RegExp['$1']);
		if (IEVersionNum === 7) {
			// IE7
			return 7;
		} else if (IEVersionNum === 8) {
			// IE8
			return 8
		} else if (IEVersionNum === 9) {
			// IE9
			return 9
		} else if (IEVersionNum === 10) {
			// IE10
			return 10
		} else {
			// IE版本<7
			return 6
		}
	} else if (isEdge) {
		// edge
		return 'edge'
	} else if (isIE11) {
		// IE11
		return 11
	} else {
		// 不是ie浏览器
		return -1
	}
}

