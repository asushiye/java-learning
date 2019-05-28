<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>

<html>
<head>
    <title>用户管理</title>
</head>
<body>
<div style="margin-left: auto;margin-right: auto;width: auto">
    <h1>用户管理</h1>
    <p/>
    <p>demo：${title}</p>
    <div style="position: absolute;top:95px" id="btns">
        <shiro:hasPermission name="demo:setRole">
            <a href="#" class="btn" data-code="demo:setRole">设置角色</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="demo:add">
            <a href="#" class="btn" data-code="demo:add">新增</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="demo:update">
            <a href="#" class="btn" data-code="demo:update">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="demo:delete">
            <a href="#" class="btn" data-code="demo:delete">删除</a>
        </shiro:hasPermission>
    </div>
    <div style="position: absolute;top:125px;width: 100%" >
        Hello, shiro principal how are you today? <br>
        <shiro:principal/>
    </div>
</div>

<script type="text/javascript" src="/statics/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/statics/js/demo/list.js"></script>

</body>
</html>