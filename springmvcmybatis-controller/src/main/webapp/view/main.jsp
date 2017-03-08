<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit" />
    <title>菜单页面</title>
    <script src="../resource/js/common/jquery-1.10.2.min.js"></script>
    <script src="../resource/js/common/cookieTool.js"></script>
</head>
<body style="background: none;">
    <div id = menu_list>
        <div><a></a></div>
    </div>
</body>
<%@include file="/view/common/common.jsp"%>
<script type="text/javascript">
    /**
     * 查询系统菜单列表
     */
    $(function () {
        $.ajax({
            url: '/system_menu/query_system_menu',
            type:'post',
            success: function (data) {
                if(data.code == 200){
                    showMenu(data.data);
                }else{
                    alert(data.message);
                }
            }
        });
    });

    /**
     * 动态显示菜单列表
     */
    function showMenu(data) {
        var menuList = $("#menu_list");
        menuList.html("");
        var str="";
        for(var i = 0;data.length>i;i++){
           str = str+'<div><a href="'+data[i].url+'">'+data[i].name+'</a></div>';
            menuList.html(str);
        }
        alert(getCookie("user_id"));
    }

</script>

</html>
