<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="head">
    <meta charset="utf-8">
    <title>商户管理</title>
    <link href="<%=request.getContextPath()%>/resources/css/xdd.selectCss.css" type="text/css" rel="stylesheet">
    <link href="<c:url value="/assets/plugins/daterangepicker/daterangepicker.css"/>" type="text/css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.remote.js" type="text/javascript"></script>
    <script type="text/javascript">
        var loginUserType = ${loginUser.userType};
        <c:if test="${loginUser.userType eq 2}">
        var merchantId = ${merchant.id};
        </c:if>
    </script>
</rapid:override>
<rapid:override name="main">
<div id="createTimePicker"></div>
    <div class="mainInBox">
        <div class="indexBoxBg">
            <div class="mainHead mainHead_fix_one">
                <ul>
                    <li class="active"><em></em><a href="<c:url value="/order/toStatisManager"/>">大众点餐</a></li>
                    <%--<li class="active"><em></em><a href="<c:url value="/shopCategory/manager"/>"><i class="busIco"></i>商品分类管理</a></li>--%>
                </ul>
            </div>
            <div class="content">
                <div class="nav">
                    <ul class="list-inline list-title">
                            <li><em></em><a href="<c:url value="/gateway/set_"/>">网关配置</a></li>
                             <li class="active"><em></em><a href="<c:url value="/shopCategory/manager"/>">商品分类管理</a></li>
                            <li ><em></em><a href="<c:url value="/store/storeManagerView"/>">门店管理</a></li>
                            <li><em></em><a href="<c:url value="/order/manager"/>">订单管理</a></li>
                            <li><em></em><a href="<c:url value="/order/toStatisManager"/>"></i>统一对账</a></li>
                    </ul>
                </div>
            <jsp:include page="/WEB-INF/views/category/foodCategoryDlg.jsp"/>
            <div class="tableBox clearfix">
                <div class="tableOperating">
                    <div class="leftOperating">
                        <dl>
                            <dd>
                                <a data-toggle="modal" href="#" onclick="addfoodCategory();" class="btn btn-default"><i class="fa fa-plus"></i> <span>新增</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:deletefoodCategoryList();" class="btn btn-default xdd-btn-marginLeft"><i class="fa fa-remove"></i> <span>批量删除</span></a>
                            </dd>
                        </dl>
                    </div>

                    <div class="form-horizontal" style="width: 600px; float: right;">
                        <div class="form-group">
                            <div class="col-xs-6">
                                <button class="btn btn-default pull-right" id="createTime" style="width:218px; height: 34px; text-align: left;">
                                    <i class="fa fa-calendar visible-xs"></i>
                                    <input type="hidden" id="startTime" value="">
                                    <input type="hidden" id="endTime" value="">
                                    <span class="hidden-xs" style="text-transform: uppercase;">--请选择创建日期--</span> <b class="caret pull-right" style="margin-right: 5px;margin-top: 3px;"></b>
                                </button>
                            </div>
                            <div class="col-xs-6 ">
                                <div class="rightOperating" style="width: 280px;">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-search"></i>
                                        </span>
                                        <input type="text" class="form-control" id="searchInput" placeholder="分类名称">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button" onclick="searchTable();">搜索</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tableListBox tab-content clearfix" style="font-size: 14px;">
                    <table class="table table-hover table-striped table-bordered" id="example"></table>
                </div>
            </div>

            </div>
        </div>
    </div>

<!-- main end -->
<!-- Load site level scripts -->
<script src="<%=request.getContextPath()%>/resources/js/utils/xxd.selectCss.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/foodCategory/foodCategoryList.js"></script>
</rapid:override>
<%@include file="/WEB-INF/views/common.jsp"%>