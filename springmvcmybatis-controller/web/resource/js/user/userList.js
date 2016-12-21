$(function () {
    var tableData;
    $.ajax({
        url: '/user/query_user_page',
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
        var node = $("#user_tbody");
        node.html("");
        var str="";
        for(var i=0;i<data.length;i++){
            var temp = '<tr>'+
                '<td>'+data[i].id+'</td>'+
            '<td>'+data[i].userName+'</td>'+
            '<td>'+data[i].mobile+'</td>'+
            '<td>'+data[i].createTime+'</td>'+
            '<td>'+data[i].status+'</td>'+
            '</tr>';
            str += temp;
        }
        node.html(str);
    }


});