<%@ page pageEncoding="utf-8"%>
<h3>url: <a href="/api/v1/userList.do?max=10&offset=0" target="_blank">/api/v1/userList.do</a></h3>
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
        <td>max</td>
        <td>int</td>
        <td>获取最大数据条数</td>
    </tr>
    <tr>
        <td>offset</td>
        <td>int</td>
        <td>起始位置</td>
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
        <td>sumItem</td>
        <td>int</td>
        <td>总的数据条数</td>
    </tr>
    <tr>
        <td>pageItems</td>
        <td>list</td>
        <td>列表数据</td>
    </tr>
    </tbody>
</table>
<code>
</code>