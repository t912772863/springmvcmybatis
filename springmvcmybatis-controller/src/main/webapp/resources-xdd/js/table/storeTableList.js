var diningTable;
$(function() {
    diningTable = $('#diningTable').DataTable({
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
                "mDataProp": "tableNo",
                "sTitle":"餐桌编号",
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
                "mDataProp": "tableName",
                "sTitle":"餐桌名称",
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
                "mDataProp": "peopleNumber",
                "sTitle":"餐桌人数",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    //if(sData != '--') $(nTd).attr('title',sData);
                    $(nTd).html("<span class='badge badge-success'>"+sData+"</span>");
                }
            },
            {
                "mDataProp": "typeName",
                "sTitle":"餐桌类型名称",
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
                "mDataProp": "useStatus",
                "sTitle":"使用状态",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    /*if(sData != '--') $(nTd).attr('title',sData);*/
                    var status=sData;
                    if(status==1)
                        $(nTd).html("使用中");
                    else
                        $(nTd).html("空闲")
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
                        var useStatus=oData.useStatus
                         if(useStatus==1)
                         {
                             str=str+"<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='updateStatus(" + sData + ",2)'><i class='fa fa-times'></i>&nbsp;置为空闲</a>&nbsp;&nbsp;"
                         }else
                         {
                             str=str+"<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='updateStatus(" + sData + ",-1)'><i class='fa fa-times'></i>&nbsp;删除</a>&nbsp;&nbsp;"
                         }
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
            url: contextpath + '/diningTable/tablePageList',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                var $storeId = $("input[name='storeIds']").val();
                if($storeId != ''){
                    data['search.storeId'] = $storeId;
                }
                if($.trim($('#searchInput').val())!=null)
                    data['search.tableNo'] = $.trim($('#searchInput').val());
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    }).on('draw.dt', function () {
        $('.dropdown-toggle').dropdown();
    });
    $('#diningTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            diningTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#diningTableDlg').on('hide.bs.modal', function () {
        clear();
    });
    validator();
    $("#tableCategoryAddBut").on("click",function(){
        clear();
    });
});

/**
 * 条件查询
 */
function storeSearchTable(){
    diningTable.ajax.reload();
}

function deleteCategory(id)
{
    if(id==null||id=='')
        bootbox.alert("数据传输错误！");
    bootbox.confirm("您确定要删除吗?", function(result) {
        if(!result)
            return;
        $.xAjax({
            url: contextpath + "/tableCategory/modifyStatus",
            data: {"id":id},
            success: function (data) {
                bootbox.alert(data);
                diningTable.ajax.reload();//重新加载数据
            }
        });
    });
}
editFlag= false;
function clear()
{
    $("#id").val("");
    $("#tableNo").val("");
    $("#tableSeqence").val("");
    $("#tableName").val("");
    $("input[name='useStatus'][value="+2+"]").attr("checked",true);
    $('#tableFrom').data('bootstrapValidator').resetForm(true);
    $("#typeNameSelect").selectCss("setValue",'');//下拉框恢复默认
    $("#tableNoDiv").hide();
}

function edit(iRow)
{
     var row=diningTable.row(iRow).data();
    $("#id").val(row.id);
    $("#tableNo").val(row.tableNo);
    $("#tableNo").attr("readonly","readonly");
    $("#tableSeqence").val(row.tableSeqence);
    $("#tableName").val(row.tableName);
    $("input[name='useStatus'][value="+row.useStatus+"]").attr("checked",true);
    $("#typeNameSelect").selectCss("setValue", row.tableTypeId);
    $("#diningTableDlg").modal("show");
    $("#tableNoDiv").show();
    $("#myModalLabel").text("编辑");
}
/**
 * 保存或者编辑
  */
function diningTableSave()
{
    var url = contextpath + "/diningTable/saveOrUpdate";
    if($('#tableFrom').bootstrapValidator('validate').data('bootstrapValidator').isValid() == false) return;
    $.xAjax({
        url:url,
        data: $('#tableFrom').serialize(),
        success: function (data) {
            bootbox.alert(data);
            diningTable.ajax.reload();
            clear();
            $("#diningTableDlg").modal("hide");
            //$("#new_merchant_adminLabel").text("新增");
            //console.log("结果" + data);
        }
    });
    diningTable.ajax.reload();
}

function updateStatus(id,flag)
{
    var msg="";
    if(flag==-1)
    {
        msg="您确定要删除吗";
    }else if(flag==1)
    {
        msg="您要使用吗";
    }else if(flag==2)
    {
        msg="您确定要置为空闲状态吗？";
    }
    bootbox.confirm(msg, function(result) {
        if(!result)
            return;
        $.xAjax({
            url: contextpath + "/diningTable/modifyStatus",
            data: {"id":id,"mark":flag},
            success: function (data) {
                bootbox.alert(data);
                diningTable.ajax.reload();//重新加载数据
            }
        });
    });
}
/**
 *表达验证
 */
function validator()
{
    $('#tableFrom').bootstrapValidator({
        fields: {
            /*tableNo: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^[a-z\s0-9]+$/i,
                        message: '必须是字母或者数字'
                    }
                }
            },*/
            tableSeqence: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/i,
                        message: '必须是数字'
                    }
                }
            },
            tableName: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            tableTypeId_label: {
                message: '请选择餐桌类型！',
                validators: {
                    notEmpty: {
                        message: '请选择餐桌类型！'
                    },
                    callback: {
                        callback: function (value, validator, $field) {
                            if (value == "--请选择--") return false;
                            return true;
                        }
                    }
                }
            }
        }
    });
}