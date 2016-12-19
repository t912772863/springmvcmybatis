var foodTable;
$(function() {
    foodTable = $('#foodTable').DataTable({
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
                "mDataProp": "dishName",
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
                "mDataProp": "categoryName",
                "sTitle":"所属分类",
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
                "mDataProp": "salePrice",
                "sTitle":"售价",
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
                "mDataProp": "status",
                "sTitle":"菜品状态",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    /*if(sData != '--') $(nTd).attr('title',sData);*/
                    var status=sData;
                    if(status==1)
                        $(nTd).html("可售");
                    else
                        $(nTd).html("估清")
                }
            },
            {
                "mDataProp": "originType",
                "sTitle":"菜品来源",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    /*if(sData != '--') $(nTd).attr('title',sData);*/
                    var status=sData;
                    if(status==1)
                        $(nTd).html("门店自增");
                    else if(status==2)
                        $(nTd).html("外卖系统");
                    else
                        $(nTd).html("其他");
                }
            }
            ,
            {
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
                    var str="<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='edit(" + iRow + ")'><i class='fa fa-pencil'></i>&nbsp;编辑</a>&nbsp;&nbsp;";
                    str=str+"<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='foodSync(" + iRow + ")'><i class='fa fa-share'></i>&nbsp;同步</a>&nbsp;&nbsp;"
                    str=str+"<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='foodDelete(" + sData + ")'><i class='fa fa-times'></i>&nbsp;删除</a>&nbsp;&nbsp;"
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
            url: contextpath + '/food/storeFoodPageList',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                var $storeId = $("input[name='storeIds']").val();
                if($storeId != ''){
                    data['search.storeId'] = $storeId;
                }
                if($.trim($('#searchInput').val())!=null) {
                    data['search.code'] = $.trim($('#searchInput').val());
                    data['search.dishName'] = $.trim($('#searchInput').val());
                }
                data['search.startTime'] = $('#startTime').val();
                var endTime = $('#endTime').val();
                if(endTime != "")data['search.endTime'] = endTime + ' 23:59:59';
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    }).on('draw.dt', function () {
        $('.dropdown-toggle').dropdown();
    });
    $('#foodTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            foodTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#foodAddDlg').on('hide.bs.modal', function () {
        clear();
    });
    validator();
    $("#tableAddBut").on("click",function(){
        clear();
    });
  /*  $('#ratyRating').raty({
        path: '/bootstrap-validator/vendor/raty/img',
        size: 24,
        // The name of hidden field generated by Raty
        scoreName: 'star',
        // Re-validate the star rating whenever user change it
        click: function (score, evt) {
            $('#foodFrom')
                .data('bootstrapValidator')             // Return the bootstrapValidator instance
                .updateStatus('star', 'NOT_VALIDATED')  // Mark the star field as not validated yet
                .validateField('star');                 // Validate it again
        }
    });*/

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

/**
 * 条件查询
 */
function storeSearchTable(){
    foodTable.ajax.reload();
}

function foodDelete(id)
{
    if(id==null||id=='')
        bootbox.alert("数据传输错误！");
    bootbox.confirm("您确定要删除吗?", function(result) {
        if(!result)
            return;
        $.xAjax({
            url: contextpath + "/food/modifyStatus",
            data: {"id":id},
            success: function (data) {
                bootbox.alert(data);
                foodTable.ajax.reload();//重新加载数据
            }
        });
    });
}

function clear()
{
    $("#id").val("");
    $("#code").val("");
    $("#dishName").val("");
    $("#picture").val("");
    $("#foodDiv").hide();
    $("#unit").val("");
    $("#price").val("");
    $("#salePrice").val("");

    $("#salesVolume").val("");
    $("#maxUnitPerRequest").val("");
    $("#dishNameEng").val("");
    $("#dishNameOther").val("");
    $("#description").val("");
    $("#sequence").val("");

    $('#foodFrom').data('bootstrapValidator').resetForm(true);
    $("#categorySelect").selectCss('disable', false);
    $("#categorySelect").selectCss("setValue",'');//下拉框恢复默认

    $(":radio[name='specialDishes'][value="+ 0 +"]").attr('checked', 'checked');
    $(":radio[name='regionid'][value="+ 0 +"]").attr('checked', 'checked');
    $(":radio[name='mealTypeId'][value="+ 0 +"]").attr('checked', 'checked');
    $(":radio[name='taste'][value='不辣']").attr('checked', 'checked');
    $(":radio[name='status'][value="+ 1 +"]").attr('checked', 'checked');
    $(":radio[name='recommentIndex'][value="+0+"]").attr('checked', 'checked');

    $("#imageDiv").hide();
    $("#pic").attr("src",'');

    flag=false;
}
var flag=false;
function edit(iRow)
{
     var row=foodTable.row(iRow).data();
    $("#id").val(row.id);
    $("#code").val(row.code);
    $("#dishName").val(row.dishName);
    $("#picture").val(row.picture);
    if(row.picture!=null&&row.picture!='') {
        $("#imageDiv").show();
        $("#pic").attr("src", row.picture);
    }
    $("#foodDiv").show();
    $("#food_code").html(row.code);
    $("#unit").val(row.unit);
    $("#price").val(row.price);
    $("#salePrice").val(row.salePrice);
    $("#categorySelect").selectCss('disable', true);
    $("#categorySelect").selectCss("setValue", row.categoryId);
    $("#salesVolume").val(row.salesVolume);
    $("#maxUnitPerRequest").val(row.maxUnitPerRequest);
    $("#dishNameEng").val(row.dishNameEng);
    $("#dishNameOther").val(row.dishNameOther);
    $("#sequence").val(row.sequence);
    $(":radio[name='specialDishes'][value="+ row.specialDishes +"]").attr('checked', 'checked');
    $(":radio[name='regionid'][value="+ row.regionid +"]").attr('checked', 'checked');
    $(":radio[name='mealTypeId'][value="+ row.mealTypeId +"]").attr('checked', 'checked');
    $(":radio[name='taste'][value="+ row.taste +"]").attr('checked', 'checked');
    $(":radio[name='status'][value="+ row.status +"]").attr('checked', 'checked');
    $(":radio[name='recommentIndex'][value="+ row.recommentIndex +"]").attr('checked', 'checked');
    $("#description").val(row.description);
    $("#foodAddDlg").modal("show");
    $("#myModalLabel").text("编辑");
    flag=true;
}
//打开新增div
function openAddFoodDiv()
{
    clear();
    $("#myModalLabel").text("新增");
    $("#foodAddDlg").modal("show");
    flag=false;
}
/**
 * 打开shopfood选择的界面
 */
function shopFoodSelect(storeId)
{
    clear();
    $("#shopFoodDiv").modal("show");
    flag=false;
}
/**
 * 保存或者编辑
  */
function foodSave()
{
    var picture=$("#picture").val();
    if($('#foodFrom').bootstrapValidator('validate').data('bootstrapValidator').isValid() == false) return;
    if(picture=="") {
        $("#fileDiv").addClass("has-error");
        $("small[data-bv-for='certpath']").show();
        if($('#foodFrom').bootstrapValidator('validate').data('bootstrapValidator').isValid() == false) return;
        return;
    }
    var url = contextpath + "/food/storeFoodCustomAdd";
    if(flag)
        url=contextpath + "/food/storeFoodCustomUpdate";//编辑
  /*  $('#foodFrom').ajaxSubmit({
        url:url,
        type : "post",
        data: $("#foodFrom").serialize(),
        success: function (data) {
            if(data.success == false){
                bootbox.alert(data.message);
                return;
            }
            foodTable.ajax.reload();
            clear();
            $("#foodAddDlg").modal("hide");
            //$("#new_merchant_adminLabel").text("新增");
            //console.log("结果" + data);
        }
    });*/
    $.xAjax({
        url:url,
        data: $('#foodFrom').serialize(),
        success: function (data) {
            bootbox.alert(data);
            foodTable.ajax.reload();
            clear();
            $("#foodAddDlg").modal("hide");
            flag=false;
        }
    });
}

function updateStatus(id,flag)
{
    bootbox.confirm("您确定要删除吗?", function(result) {
        if(!result)
            return;
        $.xAjax({
            url: contextpath + "/foodTable/modifyStatus",
            data: {"id":id,"mark":flag},
            success: function (data) {
                bootbox.alert(data);
                foodTable.ajax.reload();//重新加载数据
            }
        });
    });
}
/**
 *表达验证
 */
function validator()
{
    $('#foodFrom').bootstrapValidator({
        fields: {
            tableNo: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^[a-z\s0-9]+$/i,
                        message: '必须是字母或者数字'
                    }
                }
            },
            sequence: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*[1-9][0-9]*$/i,
                        message: '必须是整数'
                    }
                }
            },
            categoryId_label: {
                message: '请选择菜品类型！',
                validators: {
                    notEmpty: {
                        message: '请选择菜品类型！'
                    },
                    callback: {
                        callback: function (value, validator, $field) {
                            if (value == "--请选择--") return false;
                            return true;dishName
                        }
                    }
                }
            },
            dishName: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
           /* picture: {
                validators: {
                    notEmpty: {
                        message: '图片不能为空'
                    },
                    file: {
                        extension: 'jpeg,png,JPG',
                        type: 'image/jpeg,image/png,image/JPG',
                        maxSize: 1024 * 1024*3,   // 3 MB
                        message: 'The selected file is not valid'
                    }
                }
            },*/
            unit: {
                validators: {
                    notEmpty: {
                        message: '单位不能为空'
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: '价格不能为空'
                    },
                    regexp: {
                        regexp: /^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/i,
                        message: '必须是整数或者浮点数'
                    }
                }
            },
            salePrice: {
                validators: {
                    regexp: {
                        regexp: /^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/i,
                        message: '必须是整数或者浮点数'
                    }
                }
            },
            salesVolume: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*[1-9][0-9]*$/i,
                        message: '必须是整数'
                    }
                }
            },
            maxUnitPerRequest: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*[1-9][0-9]*$/i,
                        message: '必须是整数'
                    }
                }
            }
        }
    });
}

//文件上传
$("#certpaths").uploadify({
    'uploader':contextpath+'/assets/plugins/uploadify.v2.1.4/uploadify.swf?var='+new Date().getTime(),
    'script':contextpath+'/food/fileUpload;jsessionid=<%=session.getId()%>',
    'cancelImg':contextpath+'/assets/plugins/uploadify.v2.1.4/uploadify-cancel.png',
    'folder' : 'c:',
    'auto' : true,
    'scriptAccess': 'always',
    'multi': false,
    'buttonText' : '选择文件',
    'fileDesc': '*.JPG,*.png',
    'fileExt': '*.JPG,*.png',
    'buttonImg': contextpath+'/assets/plugins/uploadify.v2.1.4/upload.jpg',
    'onUploadSuccess' : function(file, data,response) {
        bootbox.alert(data);
        bootbox.alert("上传成功");
    },
    'onComplete': function(e, queueId, fileObj, re, d) {

        var da=JSON.parse(re);
        //jbootbox.alert(da.result);
        if(da.status == 0){
            $("#picture").val(da.result);
            $("#certpathsQueue").hide();
            $("#imageDiv").show();
            $("#pic").attr("src", da.result);
            $("small[data-bv-for='certpath']").hide();
            $("#fileDiv").removeClass("has-error");
            return true;
        }else
        {
            bootbox.alert(da.result);
        }
        return false;
    }
});

/**单个菜品更新*/
function foodSync(iRow){
    var row=foodTable.row(iRow).data();
    $.tipbox('正在上传，请稍后...', 'loading');
    $.xAjax({
        url: contextpath + "/food/storeDataUploadBatch",
        data: {"storeId":row.storeId,"n":true,"mealTypeId":1,"storeFoodId":row.id},
        success: function (data) {
            $.closeTipbox();
            bootbox.alert(data);
            foodTable.ajax.reload();//重新加载数据
        }
    });
    $.closeTipbox();

}

/**批量上传*/
function uploadAll(storeId){
    $.tipbox('正在上传，请稍后...', 'loading');
    $.xAjax({
        url: contextpath + "/food/storeDataUploadBatch",
        data: {"storeId":storeId,"n":false,"mealTypeId":1},
        success: function (data) {
            $.closeTipbox();
            bootbox.alert(data);
            foodTable.ajax.reload();//重新加载数据
        }
    });
    $.closeTipbox();
}
