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
<script src="/static/js/My97DatePicker/WdatePicker.js"></script>
<script src="/static/js/validation/dist/jquery.validate.min.js"></script>

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
					<h1 class="page-header" id="resource">权限</h1>
					<h2 id="resource-list">权限列表</h2>
					<h3>url: <a href="/api/v1/resource/list.do" target="_blank">/api/v1/resource/list.do</a></h3>
					<h1 class="page-header" id="user">用户</h1>
					<h2 id="user-create">添加用户</h2>
					<jsp:include page="./user-create.jsp"  ></jsp:include>
					<h2 id="user-list">用户列表</h2>
					<jsp:include page="./user-list.jsp"  ></jsp:include>
					<h2 id="user-list-select">用户下拉列表</h2>
					<jsp:include page="./user-list-select.jsp"  ></jsp:include>
					<h2 id="user-resources-get">当前用户权限</h2>
					<jsp:include page="./user-resources-get.jsp"  ></jsp:include>
					<h2 id="user-resources-change">权限更改</h2>
					<jsp:include page="./user-resources-change.jsp"  ></jsp:include>
					<h2 id="user-current">当前用户</h2>
					<jsp:include page="./user-current.jsp"  ></jsp:include>
					<h1 class="page-header" id="event">任务</h1>
					<h2 id="event-type-list">任务类型获取</h2>
					<h3>url: <a href="/api/v1/eventType/list.do" target="_blank">/api/v1/eventType/list.do</a></h3>
					<h2 id="event-create">创建任务</h2>
					<jsp:include page="./event-create.jsp"  ></jsp:include>
					<h2 id="event-edit">编辑任务</h2>
					<jsp:include page="./event-edit.jsp"  ></jsp:include>
					<h2 id="event-create">展示任务</h2>
					<h3>url: <a href="/api/v1/event/show.do?id=1" target="_blank">/api/v1/event/show.do?id=1</a></h3>
					<h1 class="page-header" id="buildProject">项目</h1>
					<h2 id="buildProject-list">项目列表</h2>
					<jsp:include page="./buildProject-list.jsp"  ></jsp:include>
					<h1 class="page-header" id="configDomain">配置</h1>
					<h2 id="configDomain-list">配置列表</h2>
					<h3>url: <a href="/api/v1/configDomain/list.do" target="_blank">/api/v1/configDomain/list.do</a></h3>
					<h2 id="configDomain-listMap">配置map列表</h2>
					<h3>url: <a href="/api/v1/configDomain/listMap.do" target="_blank">/api/v1/configDomain/listMap.do</a></h3>
				</div>
			</div>
			<div class="col-md-3">
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix"
					role="complementary" style="top: 250px;">
					<ul class="nav bs-docs-sidenav">
				  		<li class="active"><a href="#resource">权限</a>
						<ul class="nav">
							<li><a href="#resource-list">权限列表</a></li>
						</ul>
						</li>
					    <li class="active"><a href="#user">用户</a>
						<ul class="nav">
							<li><a href="#user-create">创建用户</a></li>
							<li><a href="#user-list">用户列表</a></li>
							<li><a href="#user-list-select">用户下拉列表</a></li>
							<li><a href="#user-resources-get">当前用户权限</a></li>
							<li><a href="#user-resources-change">权限更改</a></li>
							<li><a href="#user-current">当前用户</a></li>
						</ul>
						</li>
						<li class="active"><a href="#event">任务</a>
						<ul class="nav">
							<li><a href="#event-type-list">任务类型获取</a></li>
							<li><a href="#event-create">创建任务</a></li>
							<li><a href="#event-show">任务展示</a></li>
							<li><a href="#event-edit">任务编辑</a></li>
						</ul>
						<li class="active"><a href="#buildProject">项目管理</a>
						<ul class="nav">
							<li><a href="#buildProject-list">项目列表</a></li>
						</ul>
						
						<li class="active"><a href="#configDomain">配置管理</a>
						<ul class="nav">
							<li><a href="#configDomain-list">配置列表</a></li>
							<li><a href="#configDomain-listMap">配置map列表</a></li>
						</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>