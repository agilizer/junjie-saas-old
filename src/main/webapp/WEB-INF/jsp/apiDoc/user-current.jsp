<%@ page pageEncoding="utf-8"%>
<h3>url: <a href="/api/v1/currentUser.do" target="_blank">/api/v1/currentUser.do</a></h3>
<h3>参数列表</h3>
无
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
        <td>成功</td>
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
      <tr>
        <td>data</td>
        <td>map</td>
        <td>获取成功时用户的信息</td>
    </tr>
    </tbody>
</table>

<code>

</code>