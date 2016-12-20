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
            /* {
             "mDataProp": "id",
             "sTitle": "选项",
             "bSortable": false,
             "sClass": "center",
             "bSearchable": false,
             "sWidth": "10%",
             "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
             $(nTd).html("<input type='radio' class='apppoicode' name='apppoicode' value='" + sData + "' onclick='app_poi_codeSet(\""+oData.app_poi_code+"\")'>" );
             //<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick='app_poi_codeSet("+oData.app_poi_code+")'>
             }
             },*/
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
            {
                "mDataProp": "account",
                "sTitle":"配置",
                "sDefaultContent": "--",
                "sWidth": "20%",
                "sClass": "center flow",
                "bSearchable": false,
                "bSortable": false,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var str="<ul class='demo-btns'>";
                    var account=oData.account;
                    if(account==''||account==null) {
                        str = str + "<li><a class='editATxts' href='javascript:void(0);' onclick='addConfigCount(" + iRow + ",1)'>帐号配置</a></li>";
                    }else {
                        str = str + "<li><a class='editATxts' href='javascript:void(0);' onclick='addConfigCount(" + iRow + ",2)'>" + account + "</a></li>&nbsp;&nbsp;";
                    }
                    str=str+"</ul>";
                    $(nTd).html(str);
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
                    var str="<ul class='demo-btns'>";
                        var status=oData.accountStatus;
                        if(status==1)
                        {//开启
                            str = str + "<li><a class='editATxts btn btn-xs btn-default' href='javascript:void(0);' onclick='statusUpdate(" + oData.applicationTableId + ",2)'><i class='fa fa-times'></i>&nbsp;停用</a></li>&nbsp;&nbsp;";
                        }else
                        {//停用
                            str = str + "<li><a class='editATxts btn btn-xs btn-default' href='javascript:void(0);' onclick='statusUpdate(" + oData.applicationTableId + ",1)'><i class='fa fa-times'></i>&nbsp;开启</a></li>&nbsp;&nbsp;";
                        }
                        str = str + "<li><a class='editATxts btn btn-xs btn-default' href='javascript:void(0);' onclick='statusUpdate(" + oData.applicationTableId + ",-1)'><i class='fa fa-pencil'></i>&nbsp;删除配置</a></li>&nbsp;&nbsp;";
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
            url: contextpath + '/store/storeList',
            type:"post",
            data: function(data){
                $.xDataTablesParams(data, $.trim($('#searchInput').val()));
                var $shopId = $("input[name='shop']").val();
                /*var $shoreId = $("input[name='store']").val();*/
                var mealTypeId=$("#mealTypeId").val();
                data['search.mealId']=mealTypeId;
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
    $('#countForm').bootstrapValidator();
    $('#addCountDlg').on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });
});

/**
 * 条件查询
 */
function storeSearchTable(){
    storeManagerTable.ajax.reload();
}
var editFlag = false;
/**
 * 展示网关帐号信息页面
 * @param storeWm
 */
function addConfigCount(iRow,flag){
    var rowData = storeManagerTable.row(iRow).data();
    var organizeId=rowData.id;
   var mealTypeId=$("#mealTypeId").val();
    var accountId=rowData.applicationTableId;
    if(accountId==null)
        accountId='';
    $('#addCountDlg').modal({
        show: true,
        remote:contextpath + "/gateway/accountInfoView?organizeId="+organizeId+"&mealId=" + mealTypeId + "&accountId="+accountId
    });
    if(flag==2)
        editFlag = true;
}
/**
 * 帐号状态修改
 * @param id
 * @param flag
 */
function statusUpdate(id,flag)
{
    var msg="";
    if(id==null||id=='')
    {
        bootbox.alert("您还没有配置");
        return;
    }
    if(flag==-1)
    {
        msg="您确定要删除帐号吗";
    }else if(flag==1)
    {
        msg="您要启用帐号吗";
    }else if(flag==2)
    {
        msg="您确定要停止帐号使用吗";
    }
    bootbox.confirm(msg, function(result) {
        if(!result)
            return;
        $.xAjax({
            url: contextpath + "/account/modifyAccountStatus",
            data: {"id":id,"mark":flag},
            success: function (data) {
                bootbox.alert(data);
                storeManagerTable.ajax.reload();//重新加载数据
            }
        });
    });
}

/**
 * 编辑或添加账号
 */
function countSave() {
    var url = contextpath + '/account/accountAdd';
    if(editFlag){
        url = contextpath + '/account/accountEdit';
    }
    if($('#countForm').bootstrapValidator('validate').data('bootstrapValidator').isValid() == false) return;

    $('#account').val($('#countForm div[mark=extend] :input:visible:first').val());
    $('#countForm').ajaxSubmit({
        url :url,
        type: "post",
        success: function(data){
            $.successFunc(data, function(result){
                bootbox.alert(result);
                $("#addCountDlg").modal("hide");
            });
            storeManagerTable.ajax.reload();
        },
        error:function(){
            bootbox.alert("修改失败");
        }
    });

}
