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
    <%--引入socketjs, 实现后台与web页面的通讯功能--%>
    <script type="text/javascript" src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.js"></script>
    <script type="text/javascript" charset="utf8" src="/resource/plugin/datatables/media/js/jquery.dataTables.js"></script>
</head>
<body>
    <table id="order_table" class="display">
        <thead>
        <tr>
            <th>订单号</th>
            <th>第三方订单号</th>
            <th>用户编号</th>
            <th>总金额</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>订单备注</th>
        </tr>
        </thead>
        <tbody id="order_tbody">
        <tr>
            <td>Row 1 Data 1</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
            <td>Row 1 Data 2</td>
        </tr>
        </tbody>

    </table>
    <div ><a href="/order/export_excel">导出订单详情</a></div>
</body>
<script src="/resource/js/order/orderList.js"></script>
</html>
