$(document).ready(function () {
  // $("#tag1 li").click(function () {
  //   var index = $(this).index();
  //   $(this).addClass("active").siblings().removeClass("active");
  //   // if (index==0){
  //     $(".ren").show();
  //   //   $(".gang").hide();
  //   // } else if (index==1){
  //   //   $(".ren").hide();
  //   //   $(".gang").show();
  //   // }
  // })
})

$(".transfer-up").click(function () {
  var liObj = $("#treeSelected li.active");
  sortUp($(liObj).index())
});
$(".transfer-down").click(function () {
  var liObj = $("#treeSelected li.active");
  sortDown($(liObj).index())
})



//下移
function sortDown(index) {
  if (index !== -1 && index < $("#treeSelected li").length-1) {
    $("#treeSelected li").eq(index).addClass("active").siblings().removeClass("active");
    var insert = $("#treeSelected li").eq(index).closest('li');
    var tr = insert.next('li');
    tr.after(insert);
    var temp = selectArr[index];
    selectArr[index] = selectArr[index + 1];
    selectArr[index + 1] = temp;
  }
}

//上移
function sortUp(index) {
  if (index !== -1 && index > 0) {
    $("#treeSelected li").eq(index).addClass("active").siblings().removeClass("active");
    var insert = $("#treeSelected li").eq(index).closest('li');
    var tr = insert.prev('li');
    tr.before(insert);
    var temp = selectArr[index];
    selectArr[index] = selectArr[index - 1];
    selectArr[index - 1] = temp;
  }
}
var Click = new Array();
//删除选中人员
var clearArr = [];
$(".transfer-left").click(function () {
  var liObj = $("#treeSelected li");
  for (var i = 0; i < liObj.length; i++) {
    if ($(liObj[i]).hasClass("active")) {
      clearArr.push({
        name: $("#treeSelected li:eq(" + i + ") .people-name").text(),
        id: $("#treeSelected li:eq(" + i + ")").attr("data-id")
      })
    }
  }
  for (var i = 0; i < selectArr.length; i++) {
    for (var j = 0; j < clearArr.length; j++) {
      if (selectArr[i].id == clearArr[j].id) {
        selectArr.splice(i, 1);
      }
    }
  }
  clearArr = [];
  rewriteSelect(selectArr);
});

//删除选中人员
var clearArr1 = [];
$(".transfer-left1").click(function () {
  var liObj = $("#sureSelected li");
  for (var i = 0; i < liObj.length; i++) {
    if ($(liObj[i]).hasClass("active")) {
      clearArr1.push({
        name: $("#sureSelected li:eq(" + i + ") .people-name").text(),
        id: $("#sureSelected li:eq(" + i + ")").attr("data-id")
      })
    }
  }
  for (var i = 0; i < surePeopleArr.length; i++) {
    for (var j = 0; j < clearArr1.length; j++) {
      if (surePeopleArr[i].id == clearArr1[j].id) {
        surePeopleArr.splice(i, 1);
      }
    }
  }
  clearArr1 = [];
  rewriteSelect1(surePeopleArr);
});

// 多选列表
function selectPeople(e, obj) {
  Click.push($(obj).index());
  if (e.shiftKey) {
    var iMin = Math.min(Click[Click.length - 2], Click[Click.length - 1]);
    var iMax = Math.max(Click[Click.length - 2], Click[Click.length - 1]);
    for (i = iMin; i <= iMax; i++) {
      $(obj).parents(".tree-box-ul").children().eq(i).addClass("active");
    }
  } else {
    $(obj).addClass("active").siblings().removeClass("active");
  }
}

//岗位选择
function getUserByPostId(e, obj) {
  $(obj).addClass("active").siblings().removeClass("active");
  var positionId = $(obj).attr('data-id');
  $.ajax({
    url: baseUrl + '/customer/userPost/getUserByPostId?postId='+positionId,
    method: 'POST',
    contentType: 'application/json',
    async: true,
    success: function (result) {
      console.log(result)
      var userList = result.data;
      var str = "";
      $("#gangTreePeople").html("");
      if (userList.length > 0) {
        for (var i = 0; i < userList.length; i++) {
          str += "<li data-id='"+userList[i].userId+"' onclick='selectSurePosition(event, this)'><span class='people-name'>"+userList[i].userName+"</span></li>"
        }
        $("#gangTreePeople").append(str);
      }
    }
  });
}
//部门--重写选中列表
function rewriteSelect(arr) {
  $("#treeSelected").empty();
  $.each(arr, function (index, item) {
    $("#treeSelected").append("<li data-id='" + item.id + "' onclick='selectPeople(event,this)' ><span class='people-name'>" + item.name + "</span></li>")
  })
}

//组--重写选中列表
function rewriteGroupSelect(arr) {
  $("#treeGroupSelected").empty();
  $.each(arr, function (index, item) {
    $("#treeGroupSelected").append("<li data-id='" + item.id + "' onclick='selectPeople(event,this)' ><span class='people-name'>" + item.name + "</span></li>")
  })
}
//去重
function unique(arr) {
  var hash = {};
  var filtArr = arr.reduce(function (item, next) {
    hash[next.id] ? '' : hash[next.id] = true && item.push(next);
    return item
  }, [])
  return filtArr
}

// 清除数组
function arraymove(arr, fromIndex, toIndex) {
  var element = arr[fromIndex];
  arr.splice(fromIndex, 1);
  arr.splice(toIndex, 0, element);
}

//循环子节点，push
function eachChild(child) {
  $.each(child, function (index, item) {
    if (item.children.length == 0) {
      peopleList.push(item);
    }
    return eachChild(item.children);
  })
}
//无子节点push
function getChild(data) {
  peopleList.push(data);
  return eachChild(data.children);
};