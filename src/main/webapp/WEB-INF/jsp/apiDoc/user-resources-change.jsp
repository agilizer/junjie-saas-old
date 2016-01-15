<%@ page pageEncoding="utf-8"%>
<h3>url: <a href="/api/v1/user/changeResources.do?resourcesId=1,2&username=asdtiang11" target="_blank">
/api/v1/user/changeResources.do</a></h3>
<h3>参数列表</h3>
<table class="table">
    <thead>
    <tr>
        <th>属性</th>
        <th>值的类型</th>
        <th>说明描述</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>resourcesId</td>
        <td>string</td>
        <td>权限id字符串格式:1,2,3,5,9</td>
    </tr>
    <tr>
        <td>username</td>
        <td>string</td>
        <td>用户名</td>
    </tr>
    </tbody>
</table>
<h3>返回值说明</h3>
<table class="table">
    <thead>
    <tr>
        <th>属性</th>
        <th>值的类型</th>
        <th>说明描述</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>success</td>
        <td>boolean</td>
        <td>创建用户成功</td>
    </tr>
    <tr>
        <td>errorCode</td>
        <td>int</td>
        <td>success为false的失败标识</td>
    </tr>
    <tr>
        <td>msg</td>
        <td>string</td>
        <td>success为false的失败信息</td>
    </tr>
    </tbody>
</table>
<code>

</code>