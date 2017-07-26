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


    // 自己通过webSocket实现web页面与后台消息互通
    // ws:// 表示这是一个基于WebSocket类型的连接
    // var url ="ws://"+window.location.host+"/marco";
    var url = "/marco";
    // 打开webSocket
   // var sock = new WebSocket(url);
    var sock = new SockJS(url);
    // 处理连接开启事件
    sock.onopen = function () {
        alert("Opening");
        sayMarco();
    }
    // 处理信息
    sock.onmessage = function (e) {
        alert("receiced message: "+e.data);
        setTimeout(function () {
            sayMarco();
        }, 2000);
    }
    // 处理连接关闭事件
    sock.onclose = function () {
        alert("closeing");
    }
    // 发送消息
    function sayMarco() {
        alert("sending Marco! ");
        sock.send("Marco!");
    }

});
