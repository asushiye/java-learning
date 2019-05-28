$(function() {

	// 设置角色方法
	var setRole = function() {
        alert("setRole");
	}

	// 新增方法
	var addData = function() {
        alert("addData");
	}

	// 修改方法
	var updateData = function() {
        alert("updateData");
        return;
	}

	// 删除方法
	var deleteData = function() {
        alert("deleteData");
        return;
	}

	// 绑定按钮事件
	var btns = $("#btns").find("a");
	btns.each(function(index, domEle) {
		var btn = $(domEle);
		var code = btn.data("code");
		if (code == "demo:setRole") {
			btn.on("click", setRole);
		} else if (code == "demo:add") {
			btn.on("click", addData);
		} else if(code == "demo:update"){
			btn.on("click", updateData);
		}else if (code == "demo:delete") {
			btn.on("click", deleteData);
		}
	});

});