<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="head">
    <meta charset="utf-8">
    <title>网关配置</title>
    <link href="<%=request.getContextPath()%>/resources/css/xdd.selectCss.css" type="text/css" rel="stylesheet">
    <link href="<c:url value="/assets/plugins/daterangepicker/daterangepicker.css"/>" type="text/css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.remote.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/utils/jquery.form.js"></script>
    <script type="text/javascript" >
         mealTypeId = ${wmType.id};
        var mealTypeCode = '${wmType.code}';
        var organizeType = ${wmType.organizeType};
    </script>
</rapid:override>
<rapid:override name="main">
<div class="modal fade" id="addCountDlg" tabindex="-1" role="dialog" aria-labelledby="storeFoodDlgModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="min-height: 450px;">
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
                        <li class="active"><em></em><a href="<c:url value="/gateway/set_"/>">网关配置</a></li>
                        <li ><em></em><a href="<c:url value="/shopCategory/manager"/>">商品分类管理</a></li>
                        <li ><em></em><a href="<c:url value="/store/storeManagerView"/>">门店管理</a></li>
                        <li><em></em><a href="<c:url value="/order/manager"/>">订单管理</a></li>
                        <li><em></em><a href="<c:url value="/order/toStatisManager"/>">统一对账</a></li>
                    </ul>
                </div>

                <div class="">
                    <div class="recharge-title">
                        <ul class="list-inline text-left list-sort">
                            <c:forEach items="${wmTypeList}" var="type">
                                <li <c:if test="${wmType.id eq type.id}"> class="active"</c:if>>
                                    <em></em>
                                    <a href="<c:url value='/gateway/set_${type.id}'/>">
                                        <i class="${type.iconCls}"></i>
                                        ${type.name}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            <input type="hidden" id="mealTypeId" value="${wmType.id}"/>
            <div class="tableBox clearfix">
                <div class="tableOperating">
                    <div class="leftOperating" style="float: none;">
                            <%--搜索栏--%>
                        <div class="input-group searchIput-fix">
                            <input type="text" id="searchInput" class="form-control" placeholder="门店编号">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="storeSearchTable()">搜索</button>
                            </span>
                        </div>

                        <div class="shopCode-fix xdd-marginRight input-group-fix xdd-fr">
                            <div class="shopCode-fix-one">
                                <label for="mt_shopSelect" class="control-label">主店：</label>
                            </div>
                            <div>
                                <div id="mt_shopSelect" class="xdd-selectBar-fix xdd-selectBar-fix-standar"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tableListBox tab-content clearfix">
                    <table align="center" class="table table-hover table-striped table-bordered" id="storeManagerTable"></table>
                </div>
            </div>
        </div>
    </div>
<script src="<%=request.getContextPath()%>/resources/js/utils/xxd.selectCss.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/config/dp_account.js"></script>
</rapid:override>
<%@include file="/WEB-INF/views/common.jsp"%>

<%--<div class="tableOperating">
               <div class="leftOperating">
                   <dl>
                       <dd>
                           <a onclick="addConfigCount('','${wmType.code}','')" class="btn btn-default"><i class="fa fa-plus"></i> <span>新增</span></a>
                       </dd>
                   </dl>
               </div>
           </div>--%>


<%--<div class="tableOperating">--%>
    <%--<div class="form-horizontal" style="float: right; width: 900px;">--%>
        <%--<div class="form-group row">--%>
            <%--<div class="col-xs-6 col-sm-6">--%>
                <%--<label for="mt_shopSelect" class="col-sm-3 control-label">商户：</label>--%>
                <%--<div class="col-sm-9">--%>
                    <%--<div id="mt_shopSelect"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-xs-6 col-sm-6">--%>
                <%--<div class="rightOperating" style="width: 100%;">--%>
                    <%--<div class="input-group">--%>
                                        <%--<span class="input-group-addon">--%>
                                            <%--<i class="fa fa-search"></i>--%>
                                        <%--</span>--%>
                        <%--<input type="text" class="form-control" id="searchInput" placeholder="门店编号">--%>
                                        <%--<span class="input-group-btn">--%>
                                            <%--<button class="btn btn-default" type="button" onclick="storeSearchTable();">搜索</button>--%>
                                        <%--</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>