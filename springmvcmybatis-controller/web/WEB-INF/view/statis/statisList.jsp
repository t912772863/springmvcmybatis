<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="head">
    <meta charset="utf-8">
    <title>统一对账</title>
    <link href="<c:url value="/assets/plugins/daterangepicker/daterangepicker.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/xdd.selectCss.css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/resources/js/utils/xxd.selectCss.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.remote.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/echarts/echarts.common.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/jquery-autocomplete/jquery.autocomplete.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        userType = '${loginUser.userType}';
    </script>
    <style type="text/css">
        .fl{max-width: 230px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;    text-align: center;}
        @media screen and (max-width:1400px) {
            .fl{max-width: 180px;}
        }
    </style>
</head>
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
                        <li ><em></em><a href="<c:url value="/shopCategory/manager"/>">商品分类管理</a></li>
                        <li ><em></em><a href="<c:url value="/store/storeManagerView"/>">门店管理</a></li>
                        <li><em></em><a href="<c:url value="/order/manager"/>">订单管理</a></li>
                        <li class="active"><em></em><a href="<c:url value="/order/toStatisManager"/>">统一对账</a></li>
                    </ul>
                </div>
                <div class="tableBox clearfix">
                    <div class="row">
                        <div class="col-xs-3">
                            <a class="info-tiles tiles-inverse" href="#">
                                <div class="tiles-heading">
                                    <div class="pull-left">今日收银</div>
                                    <div class="pull-right">共${timeDTO.todayNum}笔</div>
                                </div>
                                <div class="tiles-body">
                                    <div class="pull-left"><i class="fa fa-rmb"></i></div>
                                    <div class="pull-right fl" id="today_amount"></div>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-3">
                            <a class="info-tiles tiles-green" href="#">
                                <div class="tiles-heading">
                                    <div class="pull-left">本月收银</div>
                                    <div class="pull-right">共${timeDTO.monthNum}笔</div>
                                </div>
                                <div class="tiles-body">
                                    <div class="pull-left"><i class="fa fa-rmb"></i></div>
                                    <div class="pull-right fl" id="month_amount"></div>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-3">
                            <a class="info-tiles tiles-blue" href="#">
                                <div class="tiles-heading">
                                    <div class="pull-left">今年收银</div>
                                    <div class="pull-right">共${timeDTO.yearNum}笔</div>
                                </div>
                                <div class="tiles-body">
                                    <div class="pull-left"><i class="fa fa-rmb"></i></div>
                                    <div class="pull-right fl" id="year_amount"></div>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-3">
                            <a class="info-tiles tiles-midnightblue" href="#">
                                <div class="tiles-heading">
                                    <div class="pull-left">总收银</div>
                                    <div class="pull-right">共${timeDTO.totalNum}笔</div>
                                </div>
                                <div class="tiles-body">
                                    <div class="pull-left"><i class="fa fa-rmb"></i></div>
                                    <div class="pull-right fl" id="total_amount"></div>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2><i class="fa fa-anchor"></i>平台类型占比</h2>
                            <div class="panel-ctrls">
                                <a href="#" class="button-icon"><i class="fa fa-refresh"></i></a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="form-horizontal row">
                                <div class="col-xs-3">
                                    <button class="btn btn-default pull-left" id="createTime" style="width: 218px; height: 34px; text-align: left;">
                                        <i class="fa fa-calendar visible-xs"></i>
                                        <input type="hidden" id="startTime" value="">
                                        <input type="hidden" id="endTime" value="">
                                        <span class="hidden-xs" style="text-transform: uppercase;">--请选择日期--</span> <b class="caret pull-right" style="margin-right: 5px;margin-top: 3px;"></b>
                                    </button>
                                </div>
                                <div>

                                </div>
                                    <div class="col-xs-4">
                                        <div class="input-group shopCode-fix input-group-fix pull-right">
                                            <label clsss="col-md-2"  style="float: left;line-height: 36px">主店：</label>
                                            <div class="col-md-7" id="a_shopName" style="width: 218px">
                                                <div id="ss_shopName"></div>
                                            </div>
                                            <%--<span class="input-group-btn">--%>
                                                <%--<button class="btn btn-default" type="button" onclick="refreshPicChart()">搜索</button>--%>
                                            <%--</span>--%>
                                        </div>

                                    </div>
                                    <div class="col-xs-4">
                                        <div class="input-group shopCode-fix input-group-fix pull-right">
                                        <label clsss="col-md-2"  style="float: left;line-height: 36px">分店：</label>
                                            <div class="col-md-7"  >
                                                <div id="storeSelect"></div>
                                            </div>
                                        <span class="input-group-btn">
                                        <button class="btn btn-default" type="button" onclick="refreshPicChart()" style="border-radius: 3px !important;padding: 3px 20px;margin-top: -10px;">搜索</button>
                                        </span>
                                        </div>
                                    </div>
                                    <%--<div class="col-xs-1" style="padding-left: 0px">
                                        <span class="input-group-btn">
                                                <button class="xdd-btn-default" type="button" onclick="refreshPicChart()">搜索</button>
                                        </span>
                                    </div>--%>
                            </div>
                            <div class="row" style="margin-top: 30px; clear: both;">
                                <div class="col-sm-6" style="text-align: center;">
                                    <div id="wmPie" style="height: 500px; width: 90%; margin: auto;"></div>
                                    <div id="no_data" hidden="hidden" style="height: 500px; width: 90%; margin: auto;"><h3><strong style="color: red">暂无数据</strong></h3></div>
                                </div>
                                <div class="col-sm-6" style="padding-top: 100px;">
                                    <p style="margin: 10px 0px; font-weight: bold;">点餐订单详情：</p>
                                    <table class="table table-hover table-striped table-bordered" id="wmTable"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- main end -->
<script src="<%=request.getContextPath()%>/resources/js/statis/statisList.js" type="text/javascript"></script>
<script>
    shopList = ${shopList};
    var temp = {id:0,name:"--请选择--"};
    shopList.unshift(temp);
    storeList = ${storeList}
    storeList.unshift(temp);
    var todayAmount = ${timeDTO.todayAmount};
    var monthAmount = ${timeDTO.monthAmount};
    var yearAmount = ${timeDTO.yearAmount};
    var totalAmount = ${timeDTO.totalAmount};

    todayAmount = todayAmount.toFixed(2);
    monthAmount = monthAmount.toFixed(2);
    yearAmount = yearAmount.toFixed(2);
    totalAmount = totalAmount.toFixed(2);

    $("#today_amount").html(todayAmount);
    $("#month_amount").html(monthAmount);
    $("#year_amount").html(yearAmount);
    $("#total_amount").html(totalAmount);

    $("#today_amount").attr("title",todayAmount);
    $("#month_amount").attr("title",monthAmount);
    $("#year_amount").attr("title",yearAmount);
    $("#total_amount").attr("title",totalAmount);

    //查询商户列表
    $("#ss_shopName").selectCss({
        data:shopList,
        nameField : 'name',     //页面展示的值,对应后台数据的属性名
        valueField : 'id',      //提交表单时的值,对应后台数据的属性名
        name:'ehecked_shopId',  //提交表单的name属性
        value:'',               //下拉框默认选中的值
        onChange:function(cur, prev){
           shopId=cur.id;
          $('#storeSelect').selectCss("resetUrl", contextpath + '/order/queryStoreListByShopId?shopId='+cur.id, '');
        }
    });
    $("#storeSelect").selectCss({
        data:[{'id':'', name:'--请选择--'}],
        nameField : 'name',     //页面展示的值,对应后台数据的属性名
        valueField : 'id',      //提交表单时的值,对应后台数据的属性名
        name:'ehecked_storeId',  //提交表单的name属性
        value:'',               //下拉框默认选中的值
        onLoadSuccess:function(data){
            if(data == null) data = [];
            data.splice(0,0,{'id':'', name:'--请选择--'});
            return data;
        }
    });

    //查询门店列表
    $("#ss_storeName").selectCss({
        data:storeList,
        nameField : 'name',     //页面展示的值,对应后台数据的属性名
        valueField : 'id',      //提交表单时的值,对应后台数据的属性名
        name:'ehecked_storeId',  //提交表单的name属性
        value:'0',               //下拉框默认选中的值
        onChange:function(cur, prev){
           storeId=cur.id;
        }
    });
</script>
</rapid:override>
<%@include file="/WEB-INF/views/common.jsp"%>