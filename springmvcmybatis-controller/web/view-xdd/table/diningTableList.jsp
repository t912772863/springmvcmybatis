<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="head">
    <meta charset="utf-8">
    <title>餐桌管理</title>
    <link href="<%=request.getContextPath()%>/resources/css/xdd.selectCss.css" type="text/css" rel="stylesheet">
    <link href="<c:url value="/assets/plugins/daterangepicker/daterangepicker.css"/>" type="text/css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.remote.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/utils/jquery.form.js"></script>
</rapid:override>
<rapid:override name="main">
<div id="createTimePicker"></div>
<div class="modal fade" id="addCountDlg" tabindex="-1" role="dialog" aria-labelledby="storeFoodDlgModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>
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
                <li ><em></em><a href="<c:url value="/shopCategory/manager"/>">商品分类管理</a></li>
                <li class="active"><em></em><a href="<c:url value="/store/storeManagerView"/>">门店管理</a></li>
                <li><em></em><a href="<c:url value="/order/manager"/>">订单管理</a></li>
                <li><em></em><a href="<c:url value="/order/toStatisManager"/>">统一对账</a></li>
                </ul>
            </div>
            <div class="fs14">
                <div class="recharge-title">
                <ul class="list-inline text-left list-sort">
                <li ><em></em><a href="/food/foodListView?storeId=${storeId}">门店菜品管理</a></li>
                <li class="active"><em></em><a href="/diningTable/diningTableList?storeId=${storeId}">餐桌管理</a></li>
                <li><em></em><a href="/diningTable/diningTableListNew?storeId=${storeId}">餐桌管理(新）</a></li>
                <li><em></em><a href="/tableCategory/tableCategoryListView?storeId=${storeId}">餐桌分类管理</a></li>
                </ul>
                </div>
            </div>
            <input type="hidden" name="storeIds" value="${storeId}">
            <div class="tableBox clearfix">
                <div class="tableOperating">
                    <div class="leftOperating">
                        <%--<dl>
                            <dd>
                                <a onclick="addConfigCount('','${wmType.code}','')" class="btn btn-default"><i class="fa fa-plus"></i> <span>新增</span></a>
                            </dd>
                        </dl>--%>
                        <span class="addBut btn btn-default" id="tableAddBut" data-toggle="modal" href="#diningTableDlg"><i class="fa fa-plus"></i>新增</span>
                    </div>
                    <div class="form-horizontal rightOperating" style="float: right; width: 600px;">
                        <div class="col-xs-4 col-sm-12 pdr0">
                            <div class="rightOperating" style="width: 50%;">
                                <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-search"></i>
                                        </span>
                                    <input type="text" class="form-control" id="searchInput" placeholder="餐桌编号">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button" onclick="storeSearchTable();">搜索</button>
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:import url="/WEB-INF/views/table/diningTableDlg.jsp"/>
                <div class="tableListBox tab-content clearfix" style="font-size: 14px;">
                    <table align="center" class="table table-hover table-striped table-bordered" id="diningTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/resources/js/utils/xxd.selectCss.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/table/storeTableList.js"></script>
</rapid:override>
<%@include file="/WEB-INF/views/common.jsp"%>
