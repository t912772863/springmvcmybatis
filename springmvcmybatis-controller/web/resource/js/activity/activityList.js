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
    alert(id);
    $.ajax({
        url: '/activity/query_activity_by_id',
        data:{"id":id},
        type:'post',
        success: function (data) {
            if(data.code == 200){
                alert(JSON.stringify(data.data));
            }else{
                alert(data.message);
            }
        }
    });
}
