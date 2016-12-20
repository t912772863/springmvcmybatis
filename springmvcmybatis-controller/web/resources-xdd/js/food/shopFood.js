var shopFoodTable;
$(function() {
    shopFoodTable = $('#shopFoodTable').DataTable({
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
        "bSort": false, //是否启动各个字段的排序功能
        "aaSorting": [[0, "asc"]], //默认的排序方式，第2列，升序排列
        "aoColumns": [
           /* {
                "mDataProp": "picture",
                "sTitle":"图片",
                "sDefaultContent": "--",
                "sWidth": "15%",
                "sClass": "center flow",
                "bSearchable": true,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                   // if(sData != '--') $(nTd).attr('title',sData);
                    if(sData != '--'||sData!='')
                        $(nTd).html("<img src='"+contextpath+sData+"'/>");
                }
            },*/
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
                "mDataProp": "code",
                "sTitle":"菜品编码",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": true,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "name",
                "sTitle":"菜品名称",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "unit",
                "sTitle":"单位",
                "sDefaultContent": "--",
                "sWidth": "8%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "price",
                "sTitle":"价格",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "createDate",
                "sTitle":"创建时间",
                "sDefaultContent": "--",
                "sWidth": "25%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData == null || sData == undefined || sData == "") $(nTd).html("--");
                    if(sData != '--' && sData != null && sData != undefined && sData != "") $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "id",
                "sTitle":"操作",
                "sDefaultContent": "--",
                "sWidth": "240px",
                "sClass": "center",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var str="<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='addOneShopFood(" + iRow + ")'><i class='fa fa-pencil'></i>&nbsp;添加</a>&nbsp;&nbsp;";
                    $(nTd).html(str);
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
            url: contextpath + '/food/queryWmShopFoodListByShopId',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput_shop').val()));
                var $storeId = $("input[name='storeIds']").val();
                if($storeId != ''){
                    data['search.storeId'] = $storeId;
                }
                if($.trim($('#searchInput_shop').val())!=null) {
                    data['search.code'] = $.trim($('#searchInput_shop').val());
                    data['search.name'] = $.trim($('#searchInput_shop').val());
                }
                data['search.startTime'] = $('#startTime_shop').val();
                var endTime = $('#endTime_shop').val();
                if(endTime != "")data['search.endTime'] = endTime + ' 23:59:59';
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    }).on('draw.dt', function () {
        $('.dropdown-toggle').dropdown();
    });
    $('#shopFoodTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            shopFoodTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

//checkbox全选
    $("#selectAll").change(function () {
        if ($(this).is(":checked")) {
            $("#shopFoodTable :input[name='checkList']").attr("checked", "true");
        } else {
            $("#shopFoodTable :input[name='checkList']").removeAttr("checked");
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
    //时间插件
    function createTimeDaterangepicker_shop(){
        $('#createTime_shopDiv').daterangepicker({
            'parentEl' : '#createTimePicker2',
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
            $('#createTime_shopDiv span').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
            $('#startTime_shop').val(start.format('YYYY-MM-DD'));
            $('#endTime_shop').val(end.format('YYYY-MM-DD'));
            console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
        });

        $('#createTimePicker2 .range_inputs').append($('<button/>', {
            'class':'applyBtn btn btn-sm btn-success',
            'text':'清除选择',
            'click':function(){
                $('#startTime_shop').val('');
                $('#endTime_shop').val('');
                $('#createTime_shopDiv span').html('--请选择创建日期--');
                createTimeDaterangepicker_shop();
            }
        }));
    }
    createTimeDaterangepicker_shop();

});

/**
 * 条件查询
 */
function shopFoodSearchTable(){
    shopFoodTable.ajax.reload();
}
/**
 * 保存用户选择的的菜品数据
  */
function storeSelectSave()
{
//获取所有选择的数据
    var ids = [];
    $('#shopFoodTable :input[name=checkList]:checked').each(function(){
        ids.push($(this).val());
    });
    if(ids.length==0) {
        bootbox.alert("请选择要添加的菜品");
        return;
    }
    var idStr = ids.join(",");
    var storeId=$("input[name='storeIds']").val();
    if(storeId==null)
        bootbox.alert("没有拿到必要的参数");
    $.xAjax({
        url:contextpath + '/food/patchAddShopFood',
        type:"post",
        data: {
            "ids":idStr,
            "storeId":storeId
        },
        success: function (data) {
            bootbox.alert(data);
            $('#selectAll').removeAttr("checked");
            $("#shopFoodDiv").modal("hide");
            foodTable.ajax.reload();
        }
    });
}

/**
 * 获取多选id
 * @returns {string}
 */
function getIdStr(){
    var ids = [];
    $('#foodTable :input[name=checkList]:checked').each(function(){
        ids.push($(this).val());
    });
    var idStr = ids.join(",");
    return idStr;
}

/**
 * 添加一个商户主库的菜品
 * private Long storeId;//门店id

 private Long shopId;//商户的id

 private Long foodId;//外卖系统的菜品id
private String code;//菜品编码
private String dishName;//菜品名称(必填)
 private String unit;
 private BigDecimal price;
 private String categoryName;
private String categoryNameOrigin;
 private Integer sequence;
 private String picture;
 */
function addOneShopFood(iRow)
{
    var row=shopFoodTable.row(iRow).data();
    var foodId=row.id;
    var storeId=$("input[name='storeIds']").val();
    var shopId=row.shopId;
    var code=row.code;
    var dishName=row.name;
    var unit=row.unit;
    if(unit==''||unit==null)
       unit="份";
    var price=row.price;
    var categoryName=row.categoryName;
    var sequence=row.sequence;
    var rank=row.category_sequence;
    var picture=row.picture;
    $.xAjax({
        url:contextpath + '/food/addOneShopFood',
        type:"post",
        data: {
            "foodId":foodId,
            "storeId":storeId,
            "shopId":shopId,
            "code":code,
            "dishName":dishName,
            "unit":unit,
            "price":price,
            "categoryName":categoryName,
            "sequence":sequence,
            "picture":picture,
            "rank":rank
        },
        success: function (data) {
            bootbox.alert(data);
            $('#selectAll').removeAttr("checked");
            $("#shopFoodDiv").modal("hide");
            foodTable.ajax.reload();
        }
    });
}




