<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>活动详情页面</title>
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
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                <div>
                    <div>活动ID: <input id="a_id" name="id" type="text"/></div>
                    <div>活动地址: <input id="a_address" name="address" type="text"/></div>
                    <div>活动发起时间: <input id="a_createTime" type="text"/></div>
                    <div>活动开始时间: <input id="a_startTime" type="text"/></div>
                    <div>活动描述: <input id="a_remark" name="remark" type="text"/></div>
                    <div>活动状态: <input id="a_status" type="text"/></div>
                    <div><input id="a_image" type="text"/></div>
                    <div id="a_images"></div>

                    <form id="uploadForm" action="#" method="post" enctype="multipart/form-data">
                        <div>上传活动图片:<input type="file" name="file" /></div>
                        <div><input id="upload" type="button" value="上传" /></div>
                    </form>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button onclick="insertImage();" type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script>

    /**
     * 获取页面跳转的时候所有的参数
     */
    var params = {};
    $(function () {
        var arr =  window.location.search.slice(1).split("&");
        for (var i = 0, len = arr.length; i < len; i++) {
            var nv = arr[i].split("=");
            params[nv[0]] = nv[1];
        }

        $.ajax({
            url: '/activity/query_activity_by_id',
            data:{"id":params.id},
            type:'post',
            success: function (data) {
                if(data.code == 200){
//                    alert(JSON.stringify(data.data));
                    var obj = data.data;
                    $("#a_id").val(obj.id);
                    $("#a_address").val(obj.address);
                    $("#a_remark").val(obj.remark);
                    $("#a_createTime").val(obj.createTime);
                    $("#a_startTime").val(obj.time1);
                    $("#a_status").val(obj.status);
                    // 图片
              //  <img alt="" src="/image/${imageName}">
                    var str = "";
                    for(var i=0; i<obj.images.length; i++){
                        str += '<img src="'+obj.images[i] +'">';
                    }
                    $("#a_images").html(str);
                }else{
                    alert(data.message);
                }
            }
        });
    })

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
                    params["imageUrl"] = data.data;
                }
            },
            error: function (returndata) {
                alert(returndata);
            }
        });
    });

    /**
     * 保存图片与活动关联
     */
    function insertImage(){
        $.ajax({
            url: '/common/insert_file',
            data:{
                "dataId":params.id,
                "tableName":"activity",
                "type":"IMAGE",
                "suffix":"jpg",
                "url":params.imageUrl,
            },
            type:'post',
            success: function (data) {
                alert(data.message);
            }
        });
    }

</script>
</html>