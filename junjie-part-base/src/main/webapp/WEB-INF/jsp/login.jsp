<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}
    .info{color:blue;}
    </style>
</head>
<body>
<div style="margin-left:auto;margin-right:auto;margin-top:100px;text-align:center">
<div class="error">${error}</div>
<div class="info">${info}</div>
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="username" value="<shiro:principal/>"><br/>
    密码：<input type="password" name="password"><br/>
    自动登录：<input type="checkbox" name="rememberMe" value="true"><br/>
    <input type="submit" value="登录">
    <a href="${pageContext.request.contextPath}/registerPage">注册</a>
</form>
</div>
</body>
</html>