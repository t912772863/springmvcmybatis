<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="static-sidebar" style="width: 100%;">
    <div class="sidebar" style="border: 0;">
        <div class="widget stay-on-collapse">
            <div class="re-shop" style="background-color: #FFFFFF">
                <a href="javascript:void(0);" class="name fix-name">运营平台</a>
            </div>
        </div>
        <div class="widget stay-on-collapse" id="widget-sidebar" style="font-size: 14px; border-top: 1px solid #E7E7E7; color: #818181;">
            <nav role="navigation" class="widget-body">
                <ul class="acc-menu">
                        <li><a class="p-menu fix-backgroundImage" href="javascript:;"><i></i> <span>商户管理</span></a>
                            <ul class="fix-show">
                                    <li><a href=" /store/storeManager"><span>门店管理</span></a></li>
                                    <li><a href=" /storeClerk/storeClerkManager"><span>营业员管理</span></a></li>
                                  <li><a href=" /userInfo/userInfoManager"><span>人员管理</span></a></li>
                            </ul>
                        </li>
                    <li><a class="p-menu fix-backgroundImage" href="javascript:;"><i></i><span>应用管理</span></a>
                        <ul class="fix-show">
                            <li class="left-list-add-fix"><a href=" /app/appShopInfo"><i class="fa fa-plus"></i> <span>添加应用</span></a></li>
                        </ul>
                    </li>

                        <li><a class="p-menu fix-backgroundImage" href=" /user/userInfo"><i></i> <span>账户管理</span></a>
                        </li>
                        <li <a class="p-menu fix-backgroundImage" href="  /casher/config/managerView"><i></i> <span>接口配置</span></a>
                        </li>
                    <li><a class="p-menu fix-backgroundImage" href=" /archive/manager" style="border-bottom: none"><i></i> <span>资质提交</span></a>
                    </li>
                    <li class="fix-qualifications">
                        <p>用于提交微信支付、支付宝</p>
                        <p>开户等资质</p>
                    </li>
                    <li class="fix-blank">

                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>