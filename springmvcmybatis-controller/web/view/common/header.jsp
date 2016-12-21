<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-1-4
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<header class="navbarTop">
    <div class="navTopPa">
        <a class="logoXdd" href="<c:url value="/order/toStatisManager"/>"></a>
        <div class="navBox">
            <div id="carousel" class="es-carousel-wrapper">
                <div class="es-carousel navListBox">
                    <ul>
                        <li>
                            <a href="${shop_url}" class="businessIco" taget="_self" style="border-width: 0px;">
                                <em></em><i></i>返回商户系统
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value='/gateway/set_'/>" class="channelIco" style="border-width: 0px;">
                                <em></em><i></i>网关配置
                            </a>
                        </li>
                        <shiro:hasAnyRoles name="merchantAdmin,merchant"></shiro:hasAnyRoles>
                        <li>
                            <a href="<c:url value="/shopCategory/manager"/>" class="businessIco" style="border-width: 0px;">
                                <i></i>商户管理
                            </a>
                        </li>

                        <li>
                            <a href="<c:url value="/store/storeManagerView"/>" class="businessIco" style="border-width: 0px;">
                                <i></i>门店管理
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/order/manager"/>" class="channelIco" style="border-width: 0px;">
                                <em></em><i></i>订单管理
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/order/toStatisManager"/>" class="dataIco" style="border-width: 0px;">
                                <em></em><i></i>统一对账
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
