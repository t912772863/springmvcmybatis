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
    <title>活动列表</title>
    <link rel="stylesheet" type="text/css" href="/resource/plugin/datatables/media/css/jquery.dataTables.css">
    <script type="text/javascript" src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.js"></script>
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.dataTables.js"></script>
</head>
<body>
    <table id="user_table" class="display">
        <thead>
        <tr>
            <th>活动编号</th>
            <th>活动名称</th>
            <th>活动地址</th>
            <th>当前状态</th>
            <th>备注信息</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="activity_tbody">
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
<script src="/resource/js/activity/activityList.js"></script>
</html>
