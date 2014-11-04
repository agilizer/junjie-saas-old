<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<div
		style="margin-left: auto; margin-right: auto; margin-top: 100px; text-align: center">
		<div class="error">${error}</div>
		<form action="${pageContext.request.contextPath}/register"
			method="post">
			用户名：<input type="text" name="username" value="<shiro:principal/>"><br />
			密码：<input type="password" name="password"><br /> 重复密码：<input
				type="password" name="passwordConfirm"><br /> 医院名称：<input
				type="text" name="companyName"><br /> <input type="submit"
				value="注册">
		</form>
	</div>
</body>
</html>