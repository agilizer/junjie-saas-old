<%@ page pageEncoding="utf-8"%>
<h3>url: <a href="/api/v1/user/add.do?username=18190910299&password=123456&fullName=张三" target="_blank">/api/v1/user/add.do</a></h3>
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
        <td>username</td>
        <td>string</td>
        <td>用户名</td>
    </tr>
    <tr>
        <td>password</td>
        <td>string</td>
        <td>密码</td>
    </tr>
    <tr>
        <td>fullName</td>
        <td>string</td>
        <td>真实姓名</td>
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