<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>阿苏系统登录</title>
</head>
<body>
${error}
    <h1>请输入用户和密码</h1>
    <p/>
    用户:
    <input type="text" name="username" id="username"/>
    <p/>
    密码:
    <input type="password" name="password" id="password"/>
    <p/>
    <input type="checkbox" name="rememberMe" id="rememberMe">记住我</input>
    <p/>
    <input type="button" value="提交" id="submit-btn"/>
    <input type="reset" value="重置"/>
</form>
<script>
    // 防止 session 过期，iframe 框显示登录界面
    if (window.top != window) {
        window.top.location.href = window.location.href;
    }
</script>
<script type="text/javascript" src="/statics/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/statics/js/login.js"></script>

</body>
</html>