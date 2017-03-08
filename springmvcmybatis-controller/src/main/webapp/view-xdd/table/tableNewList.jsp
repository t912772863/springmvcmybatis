<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="head">
    <meta charset="utf-8">
    <title>餐桌管理(新)</title>
    <link href="<c:url value="/assets/plugins/daterangepicker/daterangepicker.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/xdd.selectCss.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/assets/plugins/paginator/xdd.paginator.css" type="text/css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resources/js/utils/xxd.selectCss.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.remote.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/assets/plugins/magicsuggest/magicsuggest-min.css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/assets/plugins/magicsuggest/magicsuggest-min.js" type="text/javascript"></script>
    <style type="text/css">
        .panel .panel-heading .nav-tabs li.bv-tab-error > a {color: #a94442;}
        .border-top{border-top: 1px solid #dadfe3}
        .inbox-menu{font-size: 14px;}
        .inbox-menu a.active{background-color: #DDE4E6;}
        .inbox-menu .inbox-menu-item.active:hover {text-decoration: none; background-color: #DDE4E6;}
        .inbox-menu .collapsible-menu span.inbox-leftbar-category a.category-heading{padding-bottom: 12px; font-size: 14px;}
        .store .box{padding: 15px; margin-bottom: 10px;}
        .img-responsive{height: 92px; border: 1px solid #ccc;}
        .product-title{color: #000000; font-size: 14px; font-weight: bold;}
        .store .box.new{background: url("<%=request.getContextPath()%>/resources/images/new.png") top right no-repeat;}
        .store .box.select{background: url("<%=request.getContextPath()%>/resources/images/select-mark.png") top right no-repeat #a5e3de; border:1px solid #33a49a;}
        .product-remark{width: 100%; height: 42px; display:block; text-overflow:ellipsis; overflow: hidden; }
        .product-title{width: 100%; white-space: nowrap; text-overflow:ellipsis; overflow: hidden; }
        .noData{height: 200px; line-height: 200px; font-size: 16px; text-align: center;}
        .myPager.blue-lg span.current {
             padding: 7px 12px;
             color: #ffffff;
            background-color: #3498db;
            border: 1px solid #3498db;
            cursor: default;
        }
        .myPager.blue-lg a {
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            text-decoration: none;
            color: #3498db;
            background-color: #ffffff;
            border: 1px solid #dadfe3;
            margin-left: -1px;
        }
        .myPager.blue-lg input.input {
            font-size: 14px;
            height: 30px !important;
            line-height: 28px;
            width: 35px;
            margin: 0px 5px;
        }
        .table-list td {
            float: left;
            width: 370px;
            margin: 5px;
            border:1px solid red;
            overflow: hidden;
        }
        .table-list h2 {
            width: 100%;
            height: 70px;
            margin: 0;
            line-height: 70px;
            text-align: center;
            font-size: 26px;
            color: #383838;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
        .table-listBom a {
            float: left;
            width: 30%;
            height: 100%;
            line-height: 33px;
            text-align: center;
            color: #383838;
        }
        .table-listTop p {
            float: right;
            height: 30px;
            margin: 0;
            padding-right: 8px;
            line-height: 30px;
            text-align: right;
            font-size: 14px;
        }
        .table-listTop span {
            float: left;
            height: 30px;
            margin: 0;
            padding-right: 8px;
            line-height: 30px;
            text-align: right;
            font-size: 14px;
        }
        .table-list td_bor{
            border:1px solid red;
        }
    </style>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap-selector/bootstrap.select.css" type="text/css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrap-selector/tabcomplete.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrap-selector/livefilter.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrap-selector/bootstrap-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/paginator/xdd.paginator.js" type="text/javascript"></script>
</rapid:override>
<rapid:override name="main">
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
                <div class="fs14" style="border: none;">
                    <div class="recharge-title">
                        <ul class="list-inline text-left list-sort">
                            <li ><em></em><a href="/food/foodListView?storeId=${storeId}">门店菜品管理</a></li>
                            <li ><em></em><a href="/diningTable/diningTableList?storeId=${storeId}">餐桌管理</a></li>
                            <li class="active"><em></em><a href="/diningTable/diningTableListNew?storeId=${storeId}">餐桌管理(新）</a></li>
                            <li><em></em><a href="/tableCategory/tableCategoryListView?storeId=${storeId}">餐桌分类管理</a></li>
                        </ul>
                    </div>
                </div>
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
                <jsp:include page="/WEB-INF/views/table/qrDlg.jsp"/>
                <div class="row">
                    <div  id="tableSearchStoreDiv">
                        <input type="hidden" name="storeId" value="${storeId}">
                    </div>
                    <div class="col-md-12">
                        <div class="panel panel-inbox">
                            <div class="panel-body">
                                <div class="store" style="padding: 0px 10px;">
                                   <%--<div class="col-sm-4" id="storeFoodTemplate" style="display: none;">
                                        <table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover table-list">
                                            <tbody>
                                            <tr>

                                                <td>
                                                    <div class="table-listTop">
                                                        <p >可容纳人数：2人</p>
                                                        <p>可容纳人数：2人</p>
                                                    </div>
                                                    <h2>1号桌</h2>
                                                    <div class="table-listBom clearfix">
                                                        <a href="/Hotel/Storetable/save/st_id/9"><i class="icon-pencil"></i> 编辑</a>
                                                        <a href="/Hotel/Storetable/del/st_id/9" target="formSubmit" class="delete-dialog"><i class="icon-trash"></i> 删除</a>
                                                    </div>
                                                    <div class="table-listBom clearfix">
                                                        <a href="javascript:getQrcode('35','1');"><i class="icon-qrcode"></i> 微信二维码</a>
                                                        <a href="javascript:getQrcode('35','2');"><i class="icon-qrcode"></i> 支付宝二维码</a>
                                                    </div>
                                                </td>

                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>--%>
                                    <div class="msg-attachments row" id="storeFoodDiv">

                                    </div>
                                    <div id="paginator" style="text-align: right; width: 100%">

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- main end -->
<!-- Load site level scripts -->
<script src="<%=request.getContextPath()%>/resources/js/table/storeTableListNew.js" type="text/javascript"></script>
</rapid:override>
<%@include file="/WEB-INF/views/common.jsp"%>