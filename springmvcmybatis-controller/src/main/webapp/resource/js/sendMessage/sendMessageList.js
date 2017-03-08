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


});
