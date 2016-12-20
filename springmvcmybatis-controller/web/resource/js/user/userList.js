/**
 * Created by Administrator on 2016-1-4.
 */
var foodCategoryTable;
$(function() {
    foodCategoryTable = $('#example').DataTable({
        'sDom': 'rt<"row"<"col-sm-6"<"p_left"l>i><"col-sm-6"p>>',
        "bProcessing": true, //DataTables载入数据时，是否显示‘进度’提示
        "bServerSide": true, //是否启动服务器端数据导入
        "bStateSave": false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
        "bJQueryUI": true, //是否使用 jQury的UI theme
//        "sScrollY" : 450, //DataTables的高
//        "sScrollX" : 820, //DataTables的宽
        "aLengthMenu": [
            [10, 20, 50],
            [10, 20, 50]
        ],
        "iDisplayLength": 10,
        "bAutoWidth": false, //是否自适应宽度
        //"bScrollInfinite" : false, //是否启动初始化滚动条
        "bScrollCollapse": true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
        "bPaginate": true, //是否显示（应用）分页器
        "bInfo": true, //是否显示页脚信息，DataTables插件左下角显示记录数
//        "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页
        "bSort": true, //是否启动各个字段的排序功能
        "aaSorting": [[4, "asc"]], //默认的排序方式，第2列，升序排列
        "aoColumns": [
            {
                "mDataProp": "id",
                "sTitle": "<input type='checkbox' id='selectAll'>",
                "bSortable": false,
                "sClass": "center",
                "bSearchable": false,
                "bSortable": false,
                "sWidth": "40px",
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<input type='checkbox' class='checkList' name='checkList' value='" + sData + "'>");
                }
            },
            {
                "mDataProp": "categoryName",
                "sTitle":"分类名称",
                "sDefaultContent": "--",
                "sWidth": "25%",
                "sClass": "center flow",
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "shopName",
                "sTitle":"主店名称",
                "sDefaultContent": "--",
                "sWidth": "25%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "createDate",
                "sTitle": "创建时间",
                "sDefaultContent": "--",
                "sWidth": "25%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": true,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "rank",
                "sTitle":"排序",
                "sDefaultContent": "--",
                "sWidth": "25%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": true,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "id",
                "sTitle": "操作",
                "sDefaultContent": "--",
                "sWidth": "190px",
                "sClass": "center",
                "bSortable": false,
                "bSearchable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='editfoodCategory(" + iRow + ")'><i class='fa fa-pencil'></i>&nbsp;编辑</a>&nbsp;&nbsp;")
                        .append("<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='deleteFun(" + sData + ")'><i class='fa fa-times'></i>&nbsp;删除</a>&nbsp;&nbsp;");
                }
            }
        ],
        "oLanguage": { //国际化配置
            "sProcessing": "正在获取数据，请稍后...",
            "sLengthMenu": "显示 _MENU_ 条",
            "sZeroRecords": "没有您要搜索的内容",
            "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
            "sInfoEmpty": "记录数为0",
            "sInfoFiltered": "(全部记录数 _MAX_ 条)",
            "sInfoPostFix": "",
            "sSearch": "搜索",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "尾页"
            }
        },
        "ajax": {
            url: '/shopCategory/queryShopCategoryByPage',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                data['search.startTime'] = $('#startTime').val();
                var endTime = $('#endTime').val();
                if(endTime != "")data['search.endTime'] = endTime + ' 23:59:59';
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    });
    $('#example tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            foodCategoryTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#myModal').on('hide.bs.modal', function () {
        clear();
    });

    $('#modifyRefundPassword').on('hide.bs.modal', function () {
        clearResetPwd();
    });

    //checkbox全选
    $("#selectAll").change(function () {
        if ($(this).is(":checked")) {
            $("#example :input[name='checkList']").attr("checked", "true");
        } else {
            $("#example :input[name='checkList']").removeAttr("checked");
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



    /**
     * 表单校验
     */
    $('#fixFrom').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            categoryName: {
                validators: {
                    notEmpty: {
                        message: '分类名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '分类名称必须大于1，小于50个字'
                    },
                    remote: {
                        type: 'POST',
                        url: contextpath + '/shopCategory/categoryNameUseable',
                        delay: 500,
                        data:function(validator){
                            var mid;
                            if(loginUserType==1) {
                                var m = $('#merchantSelect').data("selectValue");
                                if(m) mid = m.id;
                            }
                            if(loginUserType==2) mid = merchantId;
                           return {
                                name:validator.getFieldElements('categoryName').val(),
                                shopId:mid,
                                foodCategoryId:$('#extn').val()
                            };
                        },
                        message: '分类名称已存在',
                        before:function(validator){
                            var n = validator.getFieldElements('categoryName').val();
                            if($("#categoryName").data('oldName') == n) return false;
                            var mid;
                            if(loginUserType==1) {
                                var m = $('#merchantSelect').data("selectValue");
                                if(m) mid = m.id;
                            }
                            if(loginUserType==2) mid = merchantId;
                            if(!mid){
                                return false;
                            }
                            return true;
                        }
                    }
                }
            },
            merchantId_label:{
                message:'请选择商户！',
                validators: {
                    notEmpty: {
                        message: '请选择商户！'
                    },
                    callback: {
                        callback:function(value, validator, $field){
                            if(value == "--请选择--") return false;
                            return true;
                        }
                    }
                }
            },
            rank: {
                validators: {
                    notEmpty: {
                        message: '分类排序不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9][0-9]?$/,
                        message: '请输入1-99的数字'
                    }
                }
            }
        }
    });

    //时间插件
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


});

var editFlag = false;

/**
 * 删除商户菜品分类
 * @param id
 * @private
 */
function deleteFun(id) {
    bootbox.confirm("你真的要删除该记录吗?", function(result) {
        if(!result) return;
        //删除异步请求
        $.xAjax({
            url:contextpath + '/shopCategory/deleteShopCategory',
            type:"post",
            data: {
                "idStr":id
            },
            success: function (data) {
                if(!data.success){
                    bootbox.alert(data.message);
                    return;
                }
                foodCategoryTable.ajax.reload();
            }
        });
    });
}
/**
 * 清除编辑和新建输入页面的数据
 */
function clear() {

    $("#categoryName").val("");
    $("#rank").val("");
    $("#remark").val("");
    $("#extn").val("");
    //清空验证消息
    $('#fixFrom').data('bootstrapValidator').resetForm(true);
    $("#merchantSelect").selectCss('setValue', '').selectCss('disable', false);
    $("#oneLevelCategory").selectCss('setValue', '').selectCss('disable', false);
    //回复原来div显示
    $('#new').show();
    $('#edit').hide();
    editFlag = false;
}

/**
 * 点击添加数据按钮
 **/
function addfoodCategory() {
    editFlag = false;
    $("#myModalLabel").text("新增");
    $("#myModal").modal("show");
}

/**
 *编辑方法
 **/
function editfoodCategory(iRow) {
    console.log(iRow);
    var rowData = foodCategoryTable.row(iRow).data();
    editFlag = true;
    $("#myModalLabel").text("修改");
    $("#categoryName").val(rowData.categoryName).data('oldName', rowData.categoryName);
    $("#rank").val(rowData.rank);
    $("#remark").val(rowData.remark);
    $("#merchantSelect").selectCss('setValue', rowData.shopId).selectCss("disable", true);
    $("#oneLevelCategory").selectCss('setValue', rowData.parentId).selectCss("disable", true);
    $("#extn").val(rowData.id);

    $('#new').hide();
    $('#edit').show();
    $("#myModal").modal("show");
}

/**
 *新增商户菜品分类方法
 **/
function foodCategoryAjax(userType, userId) {

    var url = contextpath + '/shopCategory/insertShopCategory';
    if(editFlag){
        url = contextpath + '/shopCategory/updateShopCategory';
        var id =  $("#extn").val();
    }
    //获取数据
    var merchantId = "";
    if(userType==1) merchantId = $('#merchantSelect').data("selectValue").id;
    if(userType==2) merchantId = userId;

    var categoryName = $.trim($('#categoryName').val());
    var rank = $.trim($('#rank').val());
    var remark = $.trim($('#remark').val());
    var parentId = $("input[name='categoryId']").val();
    if(!parentId||parentId==""){
        parentId = 0;
    }


    //验证
    if($('#fixFrom').bootstrapValidator('validate').data('bootstrapValidator').isValid() ==false) return;
    //发送数据到后台
    $.xAjax({
        url:url,
        type:"post",
        data: {
            "id":id,
            "categoryName":categoryName,
            "shopId":merchantId,
            "rank":rank,
            "remark":remark,
            "parentId":parentId
        },
        success: function (data) {
            if(!data.success){
                bootbox.alert(data.message);
                return;
            }
            foodCategoryTable.ajax.reload(null,editFlag?false:true);
            $("#myModal").modal("hide");
            $("#myModalLabel").text("新增");
            clear();
            console.log("结果" + data);
        }
    });

}

/**
 * 获取多选id
 * @returns {string}
 */
function getIdStr(){
    var ids = [];
    $('#example :input[name=checkList]:checked').each(function(){
        ids.push($(this).val());
    });
    var idStr = ids.join(",");
    return idStr;
}

/**
 * 批量删除商户菜品分类
 */
function deletefoodCategoryList(){
    //获取已选id
    var idStr = getIdStr();
    if(idStr==""){
        bootbox.alert("请选择分类信息");
        return;
    }
    bootbox.confirm("你真的要删除该记录吗?", function(result) {
        if(!result) return;
        //删除异步请求
        $.xAjax({
            url:contextpath + '/shopCategory/deleteShopCategory',
            type:"post",
            data: {
                "idStr":idStr
            },
            success: function (data) {
                if(!data.success){
                    bootbox.alert(data.message);
                    return;
                }
                foodCategoryTable.ajax.reload();
            }
        });
    });
}

/**
 * 条件查询
 */
function searchTable(){
    foodCategoryTable.ajax.reload();
}
/**
 * 按下enter键
 */
function enter(){
    if(event.keyCode==13){
        searchTable();
    }
}
/*
 add this plug in
 // you can call the below function to reload the table with current state
 Datatables刷新方法
 oTable.fnReloadAjax(oTable.fnSettings());
 */
$.fn.dataTableExt.oApi.fnReloadAjax = function (oSettings) {
//oSettings.sAjaxSource = sNewSource;
    this.fnClearTable(this);
    this.oApi._fnProcessingDisplay(oSettings, true);
    var that = this;

    $.getJSON(oSettings.sAjaxSource, null, function (json) {
        /* Got the data - add it to the table */
        for (var i = 0; i < json.aaData.length; i++) {
            that.oApi._fnAddData(oSettings, json.aaData[i]);
        }
        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        that.fnDraw(that);
        that.oApi._fnProcessingDisplay(oSettings, false);
    });
}