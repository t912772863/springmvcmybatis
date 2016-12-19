var orderChartPie;
var wmTable;
$(function(){
    createTimeDaterangepicker();
    //初始化饼图
    orderChartPie = echarts.init(document.getElementById('wmPie'));
    refreshPicChart();

});

function createTimeDaterangepicker(){
    $('#createTime').daterangepicker({
        'parentEl' : '#createTimePicker',
        "showDropdowns": true,
        "autoApply": true,
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
    });

    $('#createTimePicker .range_inputs').append($('<button/>', {
        'class':'applyBtn btn btn-sm btn-success',
        'text':'清除选择',
        'click':function(){
            $('#startTime').val('');
            $('#endTime').val('');
            $('#createTime span').html('--请选择日期--');
            createTimeDaterangepicker();
        }
    }));
}
shopId=0;storeId=0;
function refreshPicChart(){
    var params = {};
    var bt = $('#startTime').val();
    if(bt != '') params['startTime'] = bt + ' 00:00:00';
    var et = $('#endTime').val();
    if(et != '') params['endTime'] = et + ' 23:59:59';
    params['shopId'] = shopId;
    params['storeId'] = storeId;
    $.ajax({
        url: contextpath + '/order/countOrderGroupType',
        cache: false,
        type: 'post',
        dataType: 'json',
        data:params,
        beforeSend:function(){
            orderChartPie.showLoading({effect:'bar'});
        },
        error: function (data) {
            orderChartPie.hideLoading();
        },
        success:function(countOrderList){
            var legendData = [];
            var seriesData = [];
            var tableData = [];
            var total = 0;
            var temp=false;
            for(var x in countOrderList){
                var countOrder = countOrderList[x];
                legendData.push(countOrder.name);
                if(countOrder.amount!=0){
                    temp=true;
                    seriesData.push({name:countOrder.name, value:countOrder.amount.toFixed(2)});
                }

                tableData.push({name:countOrder.name, value:countOrder.amount.toFixed(2)});
                total += countOrder.amount;
            }
            if(temp){
                $("#wmPie").show();
                $("#no_data").hide();
            }else{
                $("#wmPie").hide();
                $("#no_data").show();
            }
            total = total.toFixed(2);
            tableData.push({name:"合计", value:  total});

            if ($.fn.DataTable.isDataTable('#wmTable')) {
                wmTable.destroy();
            }
            wmTable = $('#wmTable').DataTable({
                'sDom': 't',
                "bProcessing": false, //DataTables载入数据时，是否显示‘进度’提示
                "bServerSide": false, //是否启动服务器端数据导入
                'aaData': tableData,
                "bSort": false,
                "aoColumns": [
                    {
                        "mDataProp": "name",
                        "sTitle": "点餐类型",
                        "sClass": "left",
                        "bSortable": false,
                        "sWidth": "50%"
                    },
                    {
                        "mDataProp": "value",
                        "sTitle":"订单金额总数",
                        "sDefaultContent": "--",
                        "sWidth": "50%",
                        "sClass": "left",
                        "bSortable": true,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            if(oData.name == '合计') $(nTd).html('<strong>' + sData + '元</strong>');
                        }
                    }

                ]
            });

            var pieoption = {
                title : {
                    text: '点餐订单数据统计',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c}元 ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: legendData
                },
                series : [
                    {
                        name:'点餐类型比例',
                        type:'pie',
                        radius : '55%',
                        data: seriesData,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ],
                color:['#3EB1CC','#30D178','#F54768']
            };

            orderChartPie.hideLoading();
            orderChartPie.setOption(pieoption);
        }
    });
}
