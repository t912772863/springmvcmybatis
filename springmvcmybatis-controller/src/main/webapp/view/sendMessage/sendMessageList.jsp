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
    <title>订单管理页面</title>
    <link rel="stylesheet" type="text/css" href="/resource/plugin/datatables/media/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.js"></script>
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.dataTables.js"></script>
</head>
<body>
    <table id="sendMessage_table" class="display">
        <thead>
        <tr>
            <th>ID</th>
            <th>标题</th>
            <th>内容</th>
            <th>显示端口号</th>
            <th>发送时间</th>
            <th>状态</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody id="sendMessage_tbody">
        <tr>

        </tr>
        </tbody>

    </table>
    <div ><a href="/view/sendMessage/sendMessageDetail.jsp">新增发送消息</a></div>
</body>
<script src="/resource/js/sendMessage/sendMessageList.js"></script>
</html>
