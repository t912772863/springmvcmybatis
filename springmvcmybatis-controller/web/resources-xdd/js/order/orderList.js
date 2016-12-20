/**
 * Created by Administrator on 2016-1-7.
 */
var orderTable;
$(function() {
    orderTable = $('#orderTable').DataTable({
        "bSort": true, //是否启动各个字段的排序功能
        "aaSorting": [[9, "desc"]], //默认的排序方式
        "aoColumns": [/*{
            "mDataProp": "id",
            "sTitle": "<input type='checkbox' id='selectAll'>",
            "bSortable": false,
            "sClass": "center",
            "sWidth": "40px",
            "bSearchable": false,
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                $(nTd).html("<input type='checkbox' name='checkList' class='checkList' value='" + sData + "'>");
                }
            },*/
            {
                "mDataProp": "orderId",
                "sTitle":"订单编号",
                "sDefaultContent": "--",
                "sWidth": "110px",
                "sClass": "center flow",
                "bSortable": false,//是否支持排序
                "sSearchable" : true,//是否支持条件查询
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
            "mDataProp": "storeName",
            "sTitle":"门店名称",
            "sDefaultContent": "--",
            "sWidth": "10%",
            "sClass": "center flow",
            "bSortable": false,//是否支持排序
            "bSearchable" : false,//是否支持条件查询
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                if(sData != '--') $(nTd).attr('title',sData);
                }
            },
           {
                "mDataProp": "mealTypeName",
                "sTitle":"点餐平台",
                "sDefaultContent": "--",
                "sWidth": "80px",
                "sClass": "center flow",
                "bSortable": false,//是否支持排序
                "bSearchable" : false,//是否支持条件查询
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },

            {
                "mDataProp": "mobile",
                "sTitle":"客户电话",
                "sDefaultContent": "--",
                "sWidth": "15%",
                "sClass": "center flow",
                "bSortable": false,
                "bSearchable" : true,//是否支持条件查询
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "tableNumber",
                "sTitle":"餐桌编号",
                "sDefaultContent": "--",
                "sWidth": "10%",
                "sClass": "center flow",
                "bSortable": false,//是否支持排序
                "bSearchable" : false,//是否支持条件查询
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "totalPrice",
                "sTitle": "订单金额(元)",
                "sWidth": "10%",
                "sDefaultContent": "--",
                "bSortable": false,
                "bSearchable" : false,//是否支持条件查询
                "sClass": "center flow",
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
            "mDataProp": "createTime",
            "sTitle": "下单时间",
            "sWidth": "15%",
            "sDefaultContent": "--",
            "bSortable": false,
            "sClass": "center flow",
            "bSearchable": false,//是否支持条件查询
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                if (sData != '--') $(nTd).attr('title', sData);
                }
            },

            {
                "mDataProp": "status",
                "sTitle": "订单状态",
                "sWidth": "10%",
                "sDefaultContent": "--",
                "bSortable": false,
                "sClass": "center flow",
                "bSearchable": false,//是否支持条件查询
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if (sData == 4) $(nTd).html("已接单").attr('title', "已接单");
                    else if (sData == 8) $(nTd).html("已清台").attr('title', "已清台");
                    else if (sData == 3) $(nTd).html("已取消").attr('title', "已取消");
                    else if (sData == 1) $(nTd).html("预订单").attr('title', "预订单");
                    else if (sData == 2) $(nTd).html("已下单").attr('title', "已下单");
                }
            },
            {
                "mDataProp": "printStatus",
                "sTitle": "打印状态",
                "sWidth": "10%",
                "sDefaultContent": "--",
                "bSortable": false,
                "sClass": "center flow",
                "bSearchable": false,//是否支持条件查询
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if (sData ==1) $(nTd).html("未打印").attr('title', "未打印");
                    else if (sData == 2) $(nTd).html("已打印").attr('title', "已打印");
                    else
                        $(nTd).attr('title', sData);
                }
            },
            {
            "mDataProp": "id",
            "sTitle": "操作",
            "sWidth": "180px",
            "sClass": "center flow",
            "bSearchable": false,
            "bSortable": false,
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                $(nTd).html("<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='orderDetail(" + iRow + ")'><i class='fa fa-pencil'></i>&nbsp;详细</a>&nbsp;&nbsp;")
                      .append("<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='deleteFun(" + sData + ")'><i class='fa fa-times'></i>&nbsp;删除</a>&nbsp;&nbsp;");

                }
            }
        ],
        "ajax": {
            url: contextpath + '/order/queryByPage',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                data['search.startTime'] = $('#startTime').val();
                var endTime = $('#endTime').val();
                if(endTime != "")data['search.endTime'] = endTime + ' 23:59:59';
                var storeId = $("#hid_storeId").val();
                if(storeId!=0)data['search.storeId']=storeId;
                var shopId = $("#hid_shopId").val();
                if(shopId!=0)data['search.shopId']=shopId;
               /* var clerkId = $("#hid_clerkId").val();
                if(clerkId!=0)data['search.clerkId']=clerkId;*/
                var mealTypeId = $("#hid_mealTypeId").val();
                if(mealTypeId!=0)data['search.mealTypeId']=mealTypeId;
                var status = $("#hid_status").val();
                if(status!=0)data['search.status']=status;
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    });

    $('#orderTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            orderTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    //弹窗关闭触发的事件
    $('#myModal').on('hide.bs.modal', function () {
        closeOrder();
    });

    //checkbox全选
    $("#selectAll").change(function () {
        if ($(this).attr("checked") == "checked") {
            $("input[name='checkList']").attr("checked", "checked");
        } else {
            $("input[name='checkList']").removeAttr("checked");
        }
    });

    //去除全选
    $(".checkList").live('change',function(){
        var f = false;
        var $c = $(".checkList");
        $c.each(function(){
            if ($(this).is(":checked") == false){
                $("#selectAll").removeAttr("checked");
                f = true;
                return;
            }
        });
        if(!f) $("#selectAll").attr("checked","checked");
    });

    function createTimeDaterangepicker(){
        $('#createTime').daterangepicker({
            'parentEl' : '#createTimePicker',
            "showDropdowns": true,
            "autoApply": true,
            "opens": "left",
            "startDate":moment().subtract(1, 'days'),
            "endDate":moment(),
            "maxDate" : moment(),
            "ranges" : {
                '今日': [moment(), moment()],
                '昨日': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '最近7天': [moment().subtract(6, 'days'), moment()],
                '30天内': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            locale : {
                format: 'YYYY-MM-DD',
                separator: ' 至 ',
                applyLabel: '确定',
                cancelLabel: '取消',
                fromLabel: '从',
                toLabel: '到',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 1
            }
        }, function(start, end, label) {
            $('#createTime span').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
            $('#startTime').val(start.format('YYYY-MM-DD'));
            $('#endTime').val(end.format('YYYY-MM-DD'));
            console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
        });

        $('#createTimePicker .range_inputs').append($('<button/>', {
            'class':'applyBtn btn btn-sm btn-success',
            'text':'清除选择',
            'click':function(){
                $('#startTime').val('');
                $('#endTime').val('');
                $('#createTime span').html('--请选择创建日期--');
                createTimeDaterangepicker();
            }
        }));
    }
    createTimeDaterangepicker();


    /**
     * 订单状态下拉框()
     */
    $('#statusSelects').selectCss({
        data:[{"name":"删除", "value":'-1'},
            {"name":"已取消", "value":'3'},
            {"name":"预订单", "value":'1'},
            {"name":"已下单", "value":'2'},
            {"name":"已接单", "value":'4'},
            {"name":"已清台", "value":'8'}],
        valueField : "value",
        nameField : "name",
        name:"status",//提交到后台的值
        value:''
    });
    $('#d_status').selectCss({
        data:[{"name":"删除", "value":'-1'},
            {"name":"已取消", "value":'3'},
            {"name":"预订单", "value":'1'},
            {"name":"已下单", "value":'2'},
            {"name":"已接单", "value":'4'},
            {"name":"已清台", "value":'8'}],
        valueField : "value",
        nameField : "name",
        name:"status",//提交到后台的值
        value:''
    });
});

var editFlag = false;
/**
 * 删除订单
 * @param id
 * @private
 */
function deleteFun(id) {
    bootbox.confirm("你真的要删除该记录吗?", function(result) {
        if(!result) return;
        $.xAjax({
            url: contextpath + "/order/deleteOrderByIds",
            data: {"idStr":id},
            success: function (data) {
                if(!data.success){
                    bootbox.alert(data.message);
                    return;
                }
                orderTable.ajax.reload();
            }
        });
    });
}


/**
 *订单详情
 **/
function orderDetail(iRow) {
    var rowData = orderTable.row(iRow).data();
    editFlag = true;
    $("#myModalLabel").text("订单详情");


    if (rowData.payType == 1) $("#payType").text("货到付款");
    else if (rowData.payType == 2) $("#payType").text("在线支付");

    //获取订单详情
    $.ajax({
        url: contextpath + "/order/queryOrderDetails",
        data: {
            "orderId": rowData.id
        },
        type: "post",
        success: function (data) {
            if (data != null && data != undefined && data != ''&&data.status!=-1) {
                $("#d_orderId").text(data.orderId?data.orderId:"--");
                $("#d_createTime").text(!data.createTime? "--" : data.createTime);
                $("#d_totalPrice").text(!data.totalPrice ? "--" : data.totalPrice+"元");
               /* $("#d_status").text(!rowData.status ? "--" : rowData.status);*/
                $("#d_payType").text(!rowData.mealTypeName ? "--" : rowData.mealTypeName);
                $("#d_contant").text(!data.contact ? "--" : data.contact);
                $("#d_mobile").text(!data.mobile ? "--" : data.mobile);
                $("#d_table").text(!data.tableNumber ? "--" : data.tableNumber);
                $("#d_note").text(!data.note ? "--" : data.note);
                /*$("#d_type").text(!data.type ? "--" : data.type);*/
                var items = data.orderItemsList;
                for (var i = 0; i < items.length; i++) {
//                    var $unit = items[i].quantity + items[i].unit;
                    var tr = "<tr class='canBeRemove'>"
                        + "<td style='width: 110px;height: 40px;text-align: center;'>" + (items[i].itemId == null ? "--" : items[i].itemId) + "</td>"
                        + "<td style='width: 110px;height: 40px;text-align: center;'>" + (items[i].itemName == null ? "--" : items[i].itemName) + "</td>"
                        + "<td style='width: 110px;height: 40px;text-align: center;'>" + (items[i].quantity == null ? "--" : items[i].quantity) + "</td>"
                        + "<td style='width: 110px;height: 40px;text-align: center;'>" + (items[i].itemSum == null ? "--" : items[i].itemSum) + "元" + "</td>"
                        + "<td style='width: 110px;height: 40px;text-align: center;'>" + (items[i].note == null ? "--" : items[i].note) + "</td>"
                        + "</tr>";
                    $("#detail").append(tr);
                }
                $('#d_status').selectCss('disable', true);
                //设置订单状态下拉框的默认值
                $('#d_status').selectCss('setValue', rowData.status);
                //显示总价
                $("#myModal").modal("show");
            }else
            {
                bootbox.alert("系统异常,请联系管理员");
            }

        },
        error: function (data) {
            bootbox.alert("系统异常,请联系管理员");
        }
    });


    /*$('#statusSelects').selectCss('disable', true);
    //设置订单状态下拉框的默认值
    $('#statusSelects').selectCss('setValue', rowData.status);
    //显示总价
    $("#myModal").modal("show");*/



    /**
     * 获取多选id
     * @returns {string}
     */
    function getIdStr() {
        var ids = [];
        $('#orderTable :input[name=checkList]:checked').each(function () {
            ids.push($(this).val());
        });
        var idStr = ids.join(",");
        return idStr;
    }


    /**
     * 批量删除订单信息
     */
    function deleteSelects() {
        //获取已选id
        var idStr = getIdStr();
        if (idStr == "") {
            bootbox.alert("请选择订单");
            return;
        }

        bootbox.confirm("你真的要删除这些记录吗?", function (result) {
            if (!result) return;

            //删除异步请求
            $.xAjax({
                url: contextpath + '/order/deleteOrderByIds',
                type: "post",
                data: {
                    "idStr": idStr
                },
                success: function (data) {
                    if (!data.success) {
                        bootbox.alert(data.message);
                        result;
                    }
                    orderTable.ajax.reload();
                    $('#selectAll').removeAttr("checked");
                }
            });
        });
    }

    /**
     * 获取多选id
     * @returns {string}
     */
    function getIdStr() {
        var ids = [];
        $('#orderTable :input[name=checkList]:checked').each(function () {
            ids.push($(this).val());
        });
        return ids.join(",");
    }



    /**
     * 按下enter键
     */
    function enter() {
        if (event.keyCode == 13) {
            searchTable();
        }
    }

}

/**===============================================*/

/**
 * 搜索
 */
function searchTable() {
    orderTable.ajax.reload();
}

/**
 * 点击关闭按钮，清除数据
 */
function closeOrder() {
    $('#statusSelects').selectCss('disable', false);
    /*获取第一个tr子元素也就是要保留的元素*/
    var a = $("#title");
    /*清空所用子元素*/
    $("#detail").empty();
    /*再把标题添加上去*/
    $("#detail").append(a);
    /*隐藏美团商家信息*/
    $("#shopInfo").hide();

}