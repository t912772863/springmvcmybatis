<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>享多多</title>
    <jsp:include page="/WEB-INF/views/common/links.jsp"/>
</head>

<body class="infobar-offcanvas">
<!-- header start -->
<c:import url="/WEB-INF/views/common/header.jsp"/>
<!-- header end -->
<!-- main start -->
<div class="mainBox">
    <div class="mainInBox">
        <div class="indexBoxBg">
            <div class="mainHead mainHead_fix_one">
                <ul>
                    <li class="currentTxt"><em></em><a href="#nogo"><i class="statisticsIco"></i>500</a></li>
                </ul>
                <jsp:include page="/WEB-INF/views/user/userbanner.jsp"/>
            </div>
            <!-- item start -->
            <div class="itemBox">
                <div class="indexList clearfix" style="text-align: center; font-size: 50px; padding: 50px 0px;">
                        500 系统错误啦，请刷新重试吧！
                </div>
            </div>
            <!-- itme end -->
        </div>
    </div>
</div>
<!-- main end -->
<!-- Load site level scripts -->
</body>
</html>