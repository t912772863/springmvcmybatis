<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String cashPath = "http://cg.kf.xiangqianpos.com";
    String shopPath = "http://sm.kf.xiangqianpos.com";
    String fansPath = "http://120.26.96.41:8119";
    String wmPath = "http://120.26.96.41:8086";
    String dpPath = "http://120.26.96.41:8088";
    String kbPath="http://120.26.96.41:8112";
%>
<div class="static-sidebar" style="width: 100%;">
    <div class="sidebar" style="border: 0;">
        <div class="widget stay-on-collapse">
            <div class="re-shop" style="background-color: #FFFFFF">
                <a href="javascript:void(0);" class="name fix-name">运营平台</a>
            </div>
        </div>
        <div class="widget stay-on-collapse" id="widget-sidebar" style="font-size: 14px; border-top: 1px solid #E7E7E7; color: #818181;">
            <%--<span class="widget-heading" style="font-size: 16px;background-color: #ffffff; padding: 16px 20px;"><i class="fa fa-home"></i> 我的应用</span>--%>
            <nav role="navigation" class="widget-body">
                <ul class="acc-menu">
                    <shiro:hasPermission name="P_SHOP_MANAGER">
                        <li><a class="p-menu fix-backgroundImage" href="javascript:;"><i></i> <span>商户管理</span></a>
                            <ul class="fix-show">
                               <%-- <shiro:hasPermission name="SHOP_MANAGER">
                                    <li><a href="<%=shopPath%>/merchant/merchantUi"><span>子商户管理</span></a></li>
                                </shiro:hasPermission>--%>
                                <shiro:hasPermission name="STORE_MANAGER">
                                    <li><a href="<%=shopPath%>/store/storeManager"><span>门店管理</span></a></li>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="CLERK_MANAGER">
                                    <li><a href="<%=shopPath%>/storeClerk/storeClerkManager"><span>营业员管理</span></a></li>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="USER_MANAGER">
                                  <li><a href="<%=shopPath%>/userInfo/userInfoManager"><span>人员管理</span></a></li>
                                </shiro:hasPermission>
                            </ul>
                        </li>
                    </shiro:hasPermission>
                    <li><a class="p-menu fix-backgroundImage" href="javascript:;"><i></i><span>应用管理</span></a>
                        <ul class="fix-show">
                            <shiro:hasPermission name="APP_MENBER_MARKETING">
                                <li <c:if test="${viewType eq 'member'}" >class="active" </c:if> ><a href="<%=fansPath%>/index"><span>会员营销</span></a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="APP_COUPON_MANAGER">
                                <li <c:if test="${viewType eq 'coupon'}" >class="active" </c:if> ><a href="<%=fansPath%>/coupon/manager"><span>优惠劵</span></a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="APP_ONE_CODE_PASS">
                                <li <c:if test="${viewType eq 'qrcode'}" >class="active" </c:if> ><a href="<%=shopPath%>/qrcode/toManager"><span>一码通</span></a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="APP_PAY_CASH">
                                <li <c:if test="${viewType eq '/report/manager'}" >class="active" </c:if> ><a href="<%=cashPath%>/casher/report/manager"><span>收银台</span></a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="APP_THIRD_WM">
                                <li <c:if test="${viewType eq 'wm'}" >class="active" </c:if> ><a href="<%=wmPath%>/index.jsp"><span>聚合外卖</span></a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="APP_DIANPING_ORDER_FOOD">
                                <li class="active"><a href="<%=request.getContextPath()%>/order/toStatisManager"><span>大众点餐</span></a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="APP_KB_STORE">
                                <li> <a href="<%=kbPath%>/index"><span>口碑店铺</span></a></li>
                            </shiro:hasPermission >
                            <shiro:hasPermission name="ADD_APP">
                            <li class="left-list-add-fix"><a href="<%=shopPath%>/app/appShopInfo"><i class="fa fa-plus"></i> <span>添加应用</span></a></li>
                            </shiro:hasPermission >
                        </ul>
                    </li>

                    <shiro:hasPermission name="ACCOUNT_MANAGER">
                        <li><a class="p-menu fix-backgroundImage" href="<%=shopPath%>/user/userInfo"><i></i> <span>账户管理</span></a>
                                <%--<ul class="acc-menu">--%>
                                <%--<li <c:if test="${viewType eq '/storeClerk/storeClerkManager'}" >class="active" </c:if> ><a href="${shopDomainName}/index"><i></i> <span>账户信息</span></a></li>--%>
                                <%--<li <c:if test="${viewType eq '/storeClerk/storeClerkManager'}" >class="active" </c:if> ><a href="${shopDomainName}/coupon/manager"><i></i> <span>账户余额</span></a></li>--%>
                                <%--</ul>--%>

                                <%--<shiro:hasPermission name="RECHARGE_CONSUME_LOG">--%>
                                <%--<li><a href="${shopDomainName}/recharge/rechargeRecordManager"><i class="fa fa-cny"></i> <span>充值/消费流水</span></a></li>--%>
                                <%--</shiro:hasPermission>--%>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="P_CONF">
                        <li <c:if test="${viewType eq '/config/managerView'}" >class="active" </c:if>><a class="p-menu fix-backgroundImage" href="<%=cashPath%>/casher/config/managerView"><i></i> <span>接口配置</span></a>
                                <%--<ul class="acc-menu">--%>
                                <%--<shiro:hasPermission name="PAY_CONF">--%>
                                <%--<li <c:if test="${viewType eq '/config/managerView'}" >class="active" </c:if> ><a href="<%=request.getContextPath()%>/config/managerView"><i class="fa fa-cog"></i> <span>支付配置</span></a></li>--%>
                                <%--</shiro:hasPermission>--%>
                                <%--</ul>--%>
                        </li>
                    </shiro:hasPermission>
                    <%--<li><a class="p-menu fix-backgroundImage" href="javascript:;"><i></i> <span>系统消息</span></a>--%>
                    <%--<ul class="acc-menu">--%>
                    <%--<li><a href="${shopDomainName}/message/messageManager"><i class="fa fa-comments"></i> <span>系统消息</span></a></li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                    <shiro:hasPermission name="RESOUCE_MANAGER">
                    <li><a class="p-menu fix-backgroundImage" href="<%=shopPath%>/archive/manager" style="border-bottom: none"><i></i> <span>资质提交</span></a>
                        <%--<ul class="acc-menu">--%>
                        <%--<li><a href="${shopDomainName}/message/messageManager"><i class="fa fa-file-text"></i> <span>开户档案</span></a></li>--%>
                        <%--</ul>--%>
                    </li>
                    <li class="fix-qualifications">
                        <p>用于提交微信支付、支付宝</p>
                        <p>开户等资质</p>
                    </li>
                    </shiro:hasPermission>
                    <li class="fix-blank">

                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>