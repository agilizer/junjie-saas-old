/**
 * junjie数据库访问服务封装，server端
 * 架构思路，数据服务由数据服务组件提供，客户端调用接口后，去调用数据服务获取数据。
 * 服务器端使用时需要实现 {@link{JunjieJdbcAccessor}}。
 */
package com.junjie.commons.db.server;

