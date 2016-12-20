<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <c:import url="/WEB-INF/views/common/links.jsp"/>
    <base href="<%=basePath%>" />
    <rapid:block name="head"/>
    <style>
        /* .g-mainBox{margin-left:15px;margin-right:15px;background:#FFF;height:auto;position:relative;}
         .g-right{margin-left:270px;padding:0px 10px;}
         .g-left{position:absolute;top:0;left:0;width:140px;}*/
        /* .g-middle{margin-left:270px;padding:0px 10px;background-color: #F6F4F1;height: 100%;width: 8px;}*/
    </style>
</head>
<body class="infobar-offcanvas" style="height: auto !important; overflow-x: hidden;">
<div class="">
    <c:import url="/WEB-INF/views/common/header2.jsp"/>
    <div class="g-mainBox">
        <div  class="g-left sidebar-default fix-sidebar-default" id="g-left">
            <c:import url="/WEB-INF/views/common/left.jsp"/>
        </div>
        <div class="g-right" style="min-height: 750px;" id="g-right">
            <rapid:block name="main"/>
        </div>
    </div>
</div>
<div style="text-align: center;background-color: #F6F4F1;margin-top: 10px;margin-bottom: 10px;">2015 © 版权所有：深圳市享多多网络技术有限公司</div>
<div class="c-success"></div>
</body>
<script>

</script>
</html>
