$(function () {
    var tableData;
    $.ajax({
        url: '/activity/query_activity_page',
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

    var url = "http://"+window.location.host+"/marcopolo";
    // 创建SocketJS连接
    var sock = new SockJS(url);
    // 创建stomp客户端
    var stomp= Stomp.over(sock);
    var payload = JSON.stringify({'message':'Marco!'});
    // 连接stomp端点, 发送消息
    stomp.connect('guest', 'guest', function (frame) {
        stomp.send("/app/marco",{},payload);
    });


    stomp.connect('guest', 'guest', function (frame) {
        stomp.send("/app/marco2",{},payload);
        // 订阅主题,可以接收该主题的消息
        stomp.subscribe("/topic/shout",handleShout);
    });

    function handleShout(incoming) {
        var data = JSON.parse(incoming.body);
        alert("receive: "+data.message);
    }

});

function setData(data) {
    var node = $("#activity_tbody");
    node.html("");
    var str="";
    for(var i=0;i<data.length;i++){
        var temp = '<tr>'+
            '<td>'+data[i].id+'</td>'+
            '<td>'+data[i].name+'</td>'+
            '<td>'+data[i].address+'</td>'+
            '<td>'+data[i].status+'</td>'+
            '<td>'+data[i].remark+'</td>'+
            '<td><a onclick="showDetail('+data[i].id+');">详情</a></td>'+
            '</tr>';
        str += temp;
    }
    node.html(str);
}

function showDetail(id) {
    window.location.href='/view/activity/activityDetail.jsp?id='+id;

}
