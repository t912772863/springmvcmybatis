<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="utf-8">
    <title>用户管理</title>
<script src="../../resource/js/jquery-1.10.2.min.js"></script>
<div id="createTimePicker"></div>
    <div class="mainInBox">
        <div class="indexBoxBg">
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
<script src="../../resource/js/user/userList.js"></script>