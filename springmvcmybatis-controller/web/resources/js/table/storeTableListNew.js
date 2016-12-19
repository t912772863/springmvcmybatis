$(function(){
    loadStoreFood(1);
    $('#diningTableDlg').on('hide.bs.modal', function () {
        clear();
    });
    validator();
    $("#tableCategoryAddBut").on("click",function(){
        clear();
    });

});

/**
 * 加载门店菜品
 * @param pageNum
 */
function loadStoreFood(pageNum){
    var params = {
        'search.tableNo':$.trim($('#searchInput').val()),
        'start': (pageNum-1) * 9,
        'length':9,
        'search.storeId': $('#tableSearchStoreDiv input[name=storeId]').val()
    };
    var cateId = $('.inbox-menu-item.active').attr('categoryId');
    if(cateId) params['search.categoryId'] = cateId;
    $.xAjax({
        url: contextpath + '/diningTable/tablePageList',
        type:"post",
        data: params,
        beforeSend: function(){
            $.tipbox('正在加载数据，请稍后…', 'loading');
        },
        success: function(page){
            $.closeTipbox();
            if(page.success == false){
                bootbox.alert('数据请求失败，请刷新重试！');
                return;
            }
            var storeFoodDiv = $('#storeFoodDiv').empty();
            if(page.data == null || page.data.length == 0){
                storeFoodDiv.append('<div class="noData">暂无数据</div>');
            }else {
                var info="<table class='table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover table-list'><tbody><tr>";
                for (var x in  page.data) {
                    var storeTable = page.data[x];
                    info = info + "<td class='td_bor' style='border:0px solid #c4ccd3;'>";
                    info = info + "<div class='table-listTop'>";
                    var useStatus = storeTable.useStatus;
                    if (useStatus == 1)
                        useStatus = "<p class='badge badge-danger' style='padding-top:1px;'>使用中</p>";
                    else
                        useStatus = "<p class='badge badge-success ' style='padding-top:1px;'>空闲</p>";
                    info = info + "<span>编号：" + storeTable.tableNo + "</span>" + useStatus;
                    info = info + "<p>人数：" + storeTable.peopleNumber + "</p>";
                    info = info + "</div>";
                    info = info + "<h2>" + storeTable.tableName + "</h2>";
                    info = info + "<div class='table-listBom clearfix'>";
                    info = info + "<a href='javascript:void(0);' onclick='edit(" + JSON.stringify(storeTable) + ")'><i class='fa fa-pencil'></i> 编辑</a>";
                    info = info + "<a href='javascript:void(0);' onclick='updateStatus(" + storeTable.id + ",2)'><i class='fa fa-pencil'></i> 置为空闲</a>";
                    info = info + "<a href='javascript:void(0);' onclick='updateStatus(" + storeTable.id + ",-1)'><i class='fa  fa-times'></i> 删除</a></div>";

                    info = info + "<div class='table-listBom clearfix'>";
                    info = info + "<a href='javascript:void(0);' onclick='qrcode(" + storeTable.id + ")'><i class='fa fa-qrcode'></i> 二维码</a>";
                    info = info + "</div></td>";
                    //storeFoodDiv.append(getTableHtml(storeTable));
                }
                info=info+"</tr></tbody></<table>";
                storeFoodDiv.html(info);
            }
            $("[data-toggle='tooltip']").tooltip();
            $('#paginator').paginator({
                total: page.recordsTotal,
                first: '<<',
                last: '>>',
                next: '>',
                prev: '<',
                theme: 'blue-lg',
                pageSize: 9,
                pageNumber: pageNum,
                showGo: true,
                showFirst: true,
                showLast: true,
                onchange: function(cur){
                    loadStoreFood(cur);
                }
            });
        },
        error: function(){
            $.closeTipbox();
            bootbox.alert('数据请求失败！');
        }
    });
}

/**
 * 获取单个html
 * @param storeFood
 */
function getTableHtml(storeTable){
    var foodHtml = $('#storeFoodTemplate').clone().show().removeAttr("id");
    foodHtml.find('.product-title a').text(storeTable.name);
    foodHtml.find('.product-price').text(" ￥" + (storeTable.price?storeTable.price.toFixed(2):'--'));
    foodHtml.find('.product-remark').text(storeTable.description);

    foodHtml.append("<div class='table-listTop'>");
    foodHtml.append("<p>餐桌编号："+storeTable.tableNo+"</p>");
    foodHtml.append("<p>可容纳人数："+storeTable.peopleNumber+"人</p>");
    foodHtml.append("</div>");
    foodHtml.append("<h2>"+storeTable.tableName+"</h2>");
    foodHtml.append("<div class='table-listBom clearfix'>");
    foodHtml.append("<a href='javascript:void(0);'><i class='icon-pencil'></i> 编辑</a>");
    foodHtml.append("<a href='javascript:void(0);'><i class='icon-trash'></i> 删除</a>");
    foodHtml.append("</div>");
    foodHtml.data('data', storeTable);
    return foodHtml;
}
/**
 * 条件查询
 */
function storeSearchTable(){
    loadStoreFood(1);
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
    var row=iRow;
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
                storeSearchTable();//重新加载数据
            }
        });
    });
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
            storeSearchTable();
            clear();
            $("#diningTableDlg").modal("hide");
            //$("#new_merchant_adminLabel").text("新增");
            //console.log("结果" + data);
        }
    });
    storeSearchTable();
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
/**餐桌二维码*/
function qrcode(id)
{
    //bootbox.alert("该功能还未启用");

    $.xAjax({
        url:contextpath + "/diningTable/tableCodeImage",
        data:{"id":id},
        success: function (data) {
          //打开二维码界面
            $('#qrCode').attr("src",data);
            $('#Modal').modal("show");
        }
    });
}