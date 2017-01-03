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
            '<td><a >详情</a></td>'+
            '</tr>';
        str += temp;
    }
    node.html(str);
}
