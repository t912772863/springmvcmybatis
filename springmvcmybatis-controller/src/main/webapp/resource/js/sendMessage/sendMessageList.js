$(function () {
    var tableData;
    $.ajax({
        url: '/send_message/query_send_message_by_page',
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
        var node = $("#sendMessage_tbody");
        node.html("");
        var str="";
        for(var i=0;i<data.length;i++){
            var temp = '<tr>'+
                '<td>'+data[i].id+'</td>'+
            '<td>'+data[i].title+'</td>'+
            '<td>'+data[i].content+'</td>'+
            '<td>'+data[i].portNumber+'</td>'+
            '<td>'+data[i].sendTime+'</td>'+
            '<td>'+data[i].status+'</td>'+
            '<td>'+data[i].createTime+'</td>'+
            '</tr>';
            str += temp;
        }
        node.html(str);
    }


    // webSocket通信
    var url = "http://"+window.location.host+"/marcopolo";
    // 创建SocketJS连接
    var sock = new SockJS(url);
    // 创建stomp客户端
    var stomp= Stomp.over(sock);

    var payload = JSON.stringify({'message':'Marco!'});
    stomp.connect('guest', 'guest', function (frame) {
        stomp.send("/app/sendToUser",{},payload);

        /** 订阅主题,可以接收该主题的消息
         *
         * 注意, 这个目的地使用了"user"做前缀, 在内部, 以user做为前缀的目的地
         * 会以特殊的方式进行处理, 交给用户消息处理器, 并且会去掉user前缀,然后
         * 基于用户的会话添加一个后缀,匹配方法
         */
        stomp.subscribe("/user/queue/notifications",handleNotify);
    });

    /**
     * 处理接收到的消息内容的方法
     * @param incoming 从后台接收到的消息内容
     */
    function handleNotify(incoming) {
        alert("receive message: "+incoming.body);
    }

});
