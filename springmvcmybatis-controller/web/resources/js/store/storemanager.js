var storeManagerTable;
$(function() {
    storeManagerTable = $('#storeManagerTable').DataTable({
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
            {
                "mDataProp": "sid",
                "sTitle":"门店编号",
                "sDefaultContent": "--",
                "sWidth": "15%",
                "sClass": "center flow",
                "bSearchable": true,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
            {
                "mDataProp": "merchantName",
                "sTitle":"所属主店",
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
                "mDataProp": "name",
                "sTitle":"分店名称",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData != '--') $(nTd).attr('title',sData);
                }
            },
           /* {
                "mDataProp": "createTime",
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
            },*/
            {
                "mDataProp": "id",
                "sTitle":"操作",
                "sDefaultContent": "--",
                "sWidth": "300px",
                "sClass": "center",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var str="<ul class='demo-btns'>";
                       /* str = str + "<li><a class='editATxts' href='javascript:void(0);' onclick='toStore(" + iRow + ")'>门店管家</a></li>&nbsp;&nbsp;";*/
                        str = str + "<li><a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='toDish(" + iRow + ")'><i class='fa  fa-home'></i>菜品库管理</a></li>&nbsp;&nbsp;";
                        str = str + "<li><a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='toDishTable(" + iRow + ")'><i class='fa  fa-cubes'></i>餐桌管理</a></li>&nbsp;&nbsp;";
                        str = str + "<li><a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='editStoreInfo(" + iRow + ")'><i class='fa  fa-cubes'></i>营业信息</a></li>&nbsp;&nbsp;";
                        str=str+"</ul>";
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
            url: contextpath + '/store/storeManagerList',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                var $shopId = $("input[name='shop']").val();
                /*var $shoreId = $("input[name='store']").val();*/
                if($shopId != ''){
                    data['search.merchantId'] = $shopId;
                }
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    }).on('draw.dt', function () {
        $('.dropdown-toggle').dropdown();
    });
    $('#storeManagerTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            storeManagerTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#myModal').on('hide.bs.modal', function () {
        clear();
    });

    //商户下拉框
    $('#mt_shopSelect').selectCss({
        url: contextpath+'/merchant/searchMerchantList',
        nameField : 'name',
        valueField : 'id',
        name:'shop',
        value:'',
        onLoadSuccess:function(data){
            if(data == null) data = [];
            data.splice(0,0,{'id':'', name:'--请选择--'});
            return data;
        },
        onLoadError:function(){
            bootbox.alert("加载商户下拉列表出错");
        },
        onChange:function(cur, prev){
            //当商户改变时，门店也改变
           /* var selectStoreShop = $('#mt_shopSelect').data("selectValue");
            if(selectStoreShop) $('#mt_storeSelect').selectCss('resetUrl', contextpath+'/store/queryStoreList?shopId=' + selectStoreShop.id, '');
            else $('#mt_storeSelect').selectCss('resetUrl', contextpath+'/store/queryStoreList', '');
            $('#mt_storeSelect').selectCss('setData', [{id:'', name:'--请选择--'}], '');
            //$('#meituan_form').bootstrapValidator('revalidateField', "shopId_label");*/
        }
    });
    mtValidator();

    $('#storeAllInfoDlgModal').on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });

    $('#datepicker-range :input').click(function(){
        $(this).clockface('toggle');
    });
    //
    $('#datepicker-range :input:first').clockface({
        format: 'HH:mm',
        trigger: 'manual'
    });
    $('#datepicker-range :input:last').clockface({
        format: 'HH:mm',
        trigger: 'manual'
    });

    $('#addPlusBtn').click(function(){
        var titles = ['营业时间：', '时间段二：', '时间段三：', '时间段四：'];
        var size = $('#storeInfo_form div.form-group').size();
        if(size >= 2) return;
        var div = $('#timePickerDiv').clone();
        div.find('.control-label').text(titles[size]);
        div.find('i').removeClass('fa-plus').addClass('fa-minus');
        div.find('a').click(function(){
            $(this).parent().parent().remove();
            checkBusinessTimeIndex();
        });
        $('#storeInfo_form').append(div);
        div.find('input').each(function(index){
            $(this).attr('name', 'businessTimes['+size+"]").val('');
        });
        div.find('input').click(function(){
            $(this).clockface('toggle');
        }).clockface({
            format: 'HH:mm',
            trigger: 'manual'
        });
    });
});

/**
 * 条件查询
 */
function storeSearchTable(){
    storeManagerTable.ajax.reload();
}
/**
 * 跳转到门店管家
 * @param row
 */
function toStore(row)
{
    var storeId=storeManagerTable.row(row).data().id;
    document.location.href =contextpath+"/store/storeView?storeId="+storeId;
}
function toDish(row)
{
    var storeId=storeManagerTable.row(row).data().id;
    document.location.href =contextpath+"/food/foodListView?storeId="+storeId;
}
function toDishTable(row)
{
    var storeId=storeManagerTable.row(row).data().id;
    document.location.href =contextpath+"/diningTable/diningTableList?storeId="+storeId;
}

function editStoreInfo(row)
{
    storeInfoClear();
    var data=storeManagerTable.row(row).data();
    var storeId=data.id;
    var storeName=data.name;
    var url = contextpath + "/store/queryStoreInfo";
    $.xAjax({
        url:url,
        data: {"storeId":storeId},
        success: function (data) {
            if(data!=''||data!="") {//赋值
                $("input[name='online'][value="+data.online+"]").attr("checked",true);
                $("#id").val(data.id);
                var workTime=data.workTime;
                var times = workTime.split(",");
                var titles = ['营业时间：', '时间段二：', '时间段三：', '时间段四：'];
                for(var x in times) {
                    var startEndTimes = times[x].split('-');
                    if (x == 0) {
                        $('#timePickerDiv :input:first').val(startEndTimes[0]);
                        if (startEndTimes.length > 1) $('#timePickerDiv :input:last').val(startEndTimes[1]);
                        continue;
                    }
                    var div = $('#timePickerDiv').clone();
                    div.find('.control-label').text(titles[x]);
                    div.find('i').removeClass('fa-plus').addClass('fa-minus');
                    div.find('a').click(function () {
                        $(this).parent().parent().remove();
                        checkBusinessTimeIndex();
                    });
                    $('#storeInfo_form').append(div);
                    div.find('input:first').attr('name', 'businessTimes[' + x + ']').val(startEndTimes[0]);
                    if (startEndTimes.length > 1) div.find(':input:last').attr('name', 'businessTimes[' + x + ']').val(startEndTimes[1]);
                    else div.find(':input:last').attr('name', 'businessTimes[' + x + ']').val('');
                    div.find('input').click(function () {
                        $(this).clockface('toggle');
                    }).clockface({
                        format: 'HH:mm',
                        trigger: 'manual'
                    });
                }
            }
            $("#storeName").html(storeName);
            $("#storeId").val(storeId);
            $(".clockface").addClass("workTime");
            $("#storeInfoDlg").modal("show");
            //$("#new_merchant_adminLabel").text("新增");
            //console.log("结果" + data);
        }
    });

}

function checkBusinessTimeIndex(){
    $('#storeInfo_form div.form-group').each(function(index){
        $(this).find('input').attr('name', 'businessTimes[' + index + ']');
    });
}
//表单验
function mtValidator() {
    $('#storeInfo_form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            workTime1: {
                validators: {
                    notEmpty: {
                        message: '营业时间不能为空'
                    }
                }
            },
             online: {
                validators: {
                    notEmpty: {
                        message: '门店的状态不能为空'
                    }
                }
            }
        }
    });
}

/**
 * 门店的信息
 */
function storeInfoSave(){
    if($('#storeInfo_form').bootstrapValidator('validate').data('bootstrapValidator').isValid() == false) return;//验证
    var businessTimes = [];
    var check = true;
    /*$('#storeInfo_form div.form-group').each(function(){
        *//*var start = $(this).find('input:text[businessTimes]').val();
        var end = $(this).find('input:last').val();*//*
        var start=$("#businessTimes1").val();
        var end=$("#businessTimes2").val();
        if(start == '' || end == '' || start.length<5 || end.length<5){
            $("#workTimeDiv").modal("hide");
            bootbox.alert("请输入 " + $(this).find('.control-label').text() + ' 完整时间！', function(){
                $("#workTimeDiv").modal("show");
            });
            check = false;
            return;
        }
        businessTimes.push(start + "-" + end);
    });*/
    var start=$("#businessTimes1").val();
    var end=$("#businessTimes2").val();
    if(start == '' || end == '' || start.length<5 || end.length<5){
        $("#workTimeDiv").modal("hide");
        bootbox.alert("请输入 " + $(this).find('.control-label').text() + ' 完整时间！', function(){
            //$("#workTimeDiv").modal("show");
        });
        check = false;
        return;
    }
    businessTimes.push(start + "-" + end);
    if(!check) return;
    var storeId = $("#storeId").val();
    var online = $("input[name='online']:checked").val();
    $.xAjax({
        url: contextpath + "/store/storeInfoSave",
        data: {'workTime':businessTimes.join(','), 'storeId':storeId, 'online' :online},
        success: function (data) {
            $("#storeInfoDlg").modal("hide");
            bootbox.alert(data);
        }
    });
}
function storeInfoClear()
{
    $('#storeInfo_form').data('bootstrapValidator').resetForm(true);
    $("#storeId").val('');
    $("#storeName").html('');
    $("#id").val('');
    $("#businessTimes1").val('');
    $("#businessTimes2").val('');
    $("input:radio[name='online']").attr("checked",false);
   /* $('#storeInfo_form div.form-group').each(function(index, element){
        if(index == 0) return;
        $(this).remove();
    });*/
}