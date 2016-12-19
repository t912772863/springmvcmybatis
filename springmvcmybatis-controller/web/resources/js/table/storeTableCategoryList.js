var tableCategoryTable;
$(function() {
    tableCategoryTable = $('#tableCategoryTable').DataTable({
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
                "mDataProp": "typeName",
                "sTitle":"类型名称",
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
                    $(nTd).html("<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='edit(" + iRow + ")'><i class='fa fa-pencil'></i>&nbsp;编辑</a>&nbsp;&nbsp;")
                        .append("<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='deleteCategory(" + sData + ")'><i class='fa fa-times'></i>&nbsp;删除</a>&nbsp;&nbsp;");
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
            url: contextpath + '/tableCategory/tableCategoryList',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                var $storeId = $("input[name='storeIds']").val();
                if($storeId != ''){
                    data['search.storeId'] = $storeId;
                }
                if($.trim($('#searchInput').val())!=null)
                    data['search.typeName'] = $.trim($('#searchInput').val());
                return data;
            }
        }
    }).on('xhr.dt', function ( e, settings, json, xhr ) {
        $('#selectAll').removeAttr('checked');
    }).on('draw.dt', function () {
        $('.dropdown-toggle').dropdown();
    });
    $('#tableCategoryTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            tableCategoryTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#tableCategoryDiv').on('hide.bs.modal', function () {
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
    tableCategoryTable.ajax.reload();
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
                tableCategoryTable.ajax.reload();//重新加载数据
            }
        });
    });
}
editFlag= false;
function clear()
{
    $("#id").val("");
    $("#typeName").val("");
    $("#peopleNumber").val("");
    $('#tableCategoryFrom').data('bootstrapValidator').resetForm(true);
}

function edit(iRow)
{
     var row=tableCategoryTable.row(iRow).data();
    $("#id").val(row.id);
    $("#typeName").val(row.typeName);
    $("#peopleNumber").val(row.peopleNumber);
    $("#tableCategoryDiv").modal("show");
    $("#new_merchant_adminLabel").text("编辑");
}
/**
 * 保存或者编辑
  */
function tableCategoryTableSave()
{
    var url = contextpath + "/tableCategory/saveOrUpdate";
    if($('#tableCategoryFrom').bootstrapValidator('validate').data('bootstrapValidator').isValid() == false) return;
    $.xAjax({
        url:url,
        data: $('#tableCategoryFrom').serialize(),
        success: function (data) {
            bootbox.alert(data);
            tableCategoryTable.ajax.reload();
            clear();
            $("#tableCategoryDiv").modal("hide");
            //$("#new_merchant_adminLabel").text("新增");
            //console.log("结果" + data);
        }
    });
    tableCategoryTable.ajax.reload();
}
/**
 *表达验证
 */
function validator()
{
    $('#tableCategoryFrom').bootstrapValidator({
        fields: {
            typeName: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                },
                stringLength: {
                    max: 20,
                    message: '长度不能超过20'
                }
            },
            peopleNumber: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    integer: {
                        message: '必选是数值'
                    }
                }
            }
        }
    });
}