<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit" />
    <title>登录页面</title>
    <script src="resource/js/common/jquery-1.10.2.min.js"></script>
</head>
<body style="background: none;">
    <form id="login_in">
        <div>用户名: <input type="text" name="userName"/></div>
        <div>密码: <input type="password" name="password"/></div>

    </form>
    <button onclick="login();">登录</button>
</body>

<script type="text/javascript">
    function login(){
        $.ajax({
            url: '/login/login',
            type:'post',
            data: $("#login_in").serialize(),
            success: function (data) {
                if(data.code == 200){
                    window.location.href = "view/main.jsp"
                }else{
                    alert(data.message);
                }
            }
        });
    }
</script>
</html>
