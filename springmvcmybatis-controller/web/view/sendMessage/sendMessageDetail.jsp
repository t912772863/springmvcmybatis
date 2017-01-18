<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>新增发送消息页面</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 模态框（Modal） -->
<div id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <div>
                    <div>消息标题: <input id="s_title" name="title" type="text"/></div>
                    <div>消息内容: <input id="s_content" name="content" type="text"/></div>
                    <%--<div>消息发送时间: <input id="s_sendTime" name="sendTime" type="text"/></div>--%>
                    <div>消息端口号: <input id="s_portNumber" name="portNumber" type="text"/></div>
                    <form id="uploadForm" action="#" method="post" enctype="multipart/form-data">
                        <div>上传发送号码:<input type="file" name="file" /></div>
                        <div><input id="upload" type="button" value="上传" /></div>
                    </form>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button onclick="insertImage();" type="button" class="btn btn-primary">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script>

    var fileUrl;
    /*异步上传,带返回值  */
    $("#upload").click(function(){
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: '/common/upload_file' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data.code == 200){
                    fileUrl = data.data;
                }
            },
            error: function (returndata) {
                alert(returndata);
            }
        });
    });

    /**
     * 保存发送消息
     */
    function insertImage(){
        $.ajax({
            url: '/send_message/insert_send_message',
            data:{
                "title":$("#s_title").val(),
                "content":$("#s_content").val(),
                "portNumber":$("#s_portNumber").val(),
                "fileUrl":fileUrl,
            },
            type:'post',
            success: function (data) {
                alert(data.message);
            }
        });
    }

</script>
</html>