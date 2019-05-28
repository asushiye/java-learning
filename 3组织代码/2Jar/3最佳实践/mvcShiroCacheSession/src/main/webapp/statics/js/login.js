$(function() {
	
	$("#submit-btn").on("click",function() {
		var $username = $("#username").val();
		var $password = $("#password").val();
		var $rememberMe = $("#rememberMe").is(':checked');

		if ($username == "") {
			alert("用户名不能为空!");
			return;
		}
		
		if ($password == "") {
			alert("密码不能为空!");
			return;
		}
		
		$.ajax({
			"type": "post",
			"url": "/login",
			"data":{"username":$username,"password":$password,"rememberMe":$rememberMe},
			"success" : function(resp) {
				if (resp.code == 200) {
					window.location.href = resp.obj;
				} else {
					alert(resp.msg);
					return;
				}
			}
		});
	});
	
	$(document).on("keydown",function(e) {
		if (e.keyCode == 13) {
			$("#submit-btn").trigger("click");
		}
	});
	
});