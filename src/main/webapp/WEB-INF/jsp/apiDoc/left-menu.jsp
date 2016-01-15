<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                       		 <a href="#resource"><i class="fa fa-bar-chart-o fa-fw"></i>权限<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#resource-list">权限列表</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                        	<a href="#user"><i class="fa fa-table fa-fw"></i> 用户<span class="fa arrow"></span></a>
                        	  <ul class="nav nav-second-level">
                              	<li><a href="#user-create">创建用户</a></li>
								<li><a href="#user-list">用户列表</a></li>
								<li><a href="#user-list-select">用户下拉列表</a></li>
								<li><a href="#user-resources-get">当前用户权限</a></li>
								<li><a href="#user-resources-change">权限更改</a></li>
								<li><a href="#user-current">当前用户</a></li>
                            </ul>
                        </li>
                        <li>
                        	<a href="#event"><i class="fa fa-edit fa-fw"></i>任务<span class="fa arrow"></span></a>
                        	<ul class="nav nav-second-level">
							<li><a href="#event-type-list">任务类型获取</a></li>
							<li><a href="#event-create">创建任务</a></li>
							<li><a href="#event-show">任务展示</a></li>
							<li><a href="#event-edit">任务编辑</a></li>
							<li><a href="#event-del">任务删除</a></li>
							<li><a href="#event-current-list">当前用户任务列表</a></li>
						</ul>
                        </li>
                        <li>
                        	<a href="#buildProject"><i class="fa fa-wrench fa-fw"></i> 项目管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                              <li><a href="#buildProject-list">项目列表</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                        	<a href="#configDomain"><i class="fa fa-sitemap fa-fw"></i> 配置管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                              <li><a href="#configDomain-list">配置列表</a></li>
							<li><a href="#configDomain-listMap">配置map列表</a></li>
                                   </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                      <li>
                        	<a href="#buildProject"><i class="fa fa-wrench fa-fw"></i>文档管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                              <li><a href="#buildProject-list">目录管理<span class="fa arrow"></span></a>
                              <ul class="nav nav-second-level">
                              <li><a href="#buildProject-list">目录创建</a></li>
                              <li><a href="#buildProject-list">目录更新</a></li>
                              <li><a href="#buildProject-list">目录列表</a></li>
                            </ul>
                              </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->