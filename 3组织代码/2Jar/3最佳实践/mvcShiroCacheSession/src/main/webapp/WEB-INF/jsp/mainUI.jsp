<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8"/>
    <title>欢迎阿苏系统</title>
    <link rel="stylesheet" href="/statics/css/main/style.css"/>
</head>
<body>

<div id="header">
    <h1>欢迎进入阿苏系统</h1>
    <input type="button" value="注销" id="logout-btn"/>
</div>

<ul class="nav " id="nav">
    <c:forEach var="category" items="${user.menuList}" varStatus="st">
        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text" id="category_${st.index}"> ${category.name} </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <c:forEach var="menu" items="${category.children }">
                    <li>
                        <a href="javascript:void(0)" data-url="${menu.url}" data-category="category_${st.index}"
                           class="menu-click">
                            <i class="icon-double-angle-right"></i>
                                ${menu.name}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>

<div id="section">
    <div style="overflow: auto;">
        <iframe id="contentPanel" width="100%" height="800px" frameborder="no" border="0" marginwidth="0"
                marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
    </div>
</div>

<div id="footer">
    Copyright ? daphne.com
    <p>用户名：${user.username}</p>
    <p>手机号：${user.phone}</p>
</div>

<script type="text/javascript" src="/statics/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/statics/js/main/main.js"></script>

</body>
</html>