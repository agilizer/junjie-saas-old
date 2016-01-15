/**
 * junjie数据库访问服务封装,client端
 * 架构思路，数据服务由数据服务组件提供，客户端调用接口后，去调用数据服务获取数据。
 *　客户端使用时需要实现　{@link DataSourceSelecter}接口。
 */
package com.junjie.commons.db.client;

