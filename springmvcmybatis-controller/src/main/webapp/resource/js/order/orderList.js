$(function () {
    var tableData;
    $.ajax({
        url: '/order/query_order_by_page',
        type:'post',
        success: function (data) {
            if(data.code == 200){
                tableData = data.data.result;
                setData(tableData);
            }else{
                alert(data.message);
            }
        }
    });

    function setData(data) {
        var node = $("#order_tbody");
        node.html("");
        var str="";
        for(var i=0;i<data.length;i++){
            var temp = '<tr>'+
                '<td>'+data[i].id+'</td>'+
            '<td>'+data[i].thirdOrderId+'</td>'+
            '<td>'+data[i].userId+'</td>'+
            '<td>'+data[i].totalAmount+'</td>'+
            '<td>'+data[i].createTime+'</td>'+
            '<td>'+data[i].status+'</td>'+
            '<td>'+data[i].remark+'</td>'+
            '</tr>';
            str += temp;
        }
        node.html(str);
    }


});
