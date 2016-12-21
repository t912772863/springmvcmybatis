<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/21 0021
  Time: 上午 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uesrList</title>
    <link rel="stylesheet" type="text/css" href="/resource/plugin/datatables/media/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.js"></script>
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.dataTables.js"></script>
</head>
<body>
    <table id="user_table" class="display">
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>创建时间</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody id="user_tbody">
        <tr>
            <td>Row 1 Data 1</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
        </tr>
        </tbody>
    </table>
</body>
<script src="/resource/js/user/userList.js"></script>
</html>
