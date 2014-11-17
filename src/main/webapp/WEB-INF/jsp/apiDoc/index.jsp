<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html>
<head>
<title>junjie-saas-api-doc</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/static/css/doc.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="content" class="bs-docs-header">
		<div class="container">
			<h1>junjie saas resource base api doc</h1>
			<p>junjie saas  resource base api doc</p>
		</div>
	</div>
	<div class="container bs-docs-container">

		<div class="row">
			<div class="col-md-9" role="main">
				<div class="bs-docs-section">
					<h1 class="page-header" id="user">用户</h1>
					<h2 id="user-create">添加用户</h2>
					<jsp:include page="./user-create.jsp"  ></jsp:include>
					<h2 id="user-list">用户列表</h2>
					<jsp:include page="./user-list.jsp"  ></jsp:include>
					<h2 id="user-resources-get">权限获取</h2>
					<jsp:include page="./user-resources-get.jsp"  ></jsp:include>
					<h2 id="user-resources-change">权限更改</h2>
					<jsp:include page="./user-resources-change.jsp"  ></jsp:include>
					<h2 id="user-current">当前用户</h2>
					<jsp:include page="./user-current.jsp"  ></jsp:include>
				</div>
			</div>
			<div class="col-md-3">
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix"
					role="complementary" style="top: 250px;">
					<ul class="nav bs-docs-sidenav">
						<li class="active"><a href="#user">用户</a>
							<ul class="nav">
								<li><a href="#user-create">创建用户</a></li>
								<li><a href="#user-list">用户列表</a></li>
								<li><a href="#user-resources-get">权限获取</a></li>
								<li><a href="#user-resources-change">权限更改</a></li>
								<li><a href="#user-current">当前用户</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>