<%--
  Created by IntelliJ IDEA.
  User: jordan.liu
  Date: 2016/1/7
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="diningTableDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form id="tableFrom" class="form-horizontal">
                    <input name="id" id="id" type="hidden">
                    <input type="hidden" name="storeId" id="formStoreId" value="${storeId}">
                    <div class="form-group" id="merchantAdmin">
                        <label class="col-sm-3 control-label">餐桌类型(人)：</label>
                        <div class="col-sm-6">
                            <div id="typeNameSelect"></div>
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tableName" class="col-sm-3 control-label">餐桌名称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="tableName" class="form-control input-width-large required bs-tooltip" id="tableName" placeholder="餐桌名称">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                    <div class="form-group" id="tableNoDiv" style="display: none">
                        <label for="tableNo" class="col-sm-3 control-label">餐桌编号：</label>
                        <div class="col-sm-6">
                            <input type="text" name="tableNo" class="form-control" id="tableNo" placeholder="餐桌编号">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tableNo" class="col-sm-3 control-label">餐桌状态：</label>
                        <div class="col-sm-6 radio">
                            <label>
                                <input type="radio"  name="useStatus" value="1">
                                使用中
                            </label>
                            <label>
                            <input type="radio"  name="useStatus" value="2" checked>
                            空闲
                            </label>
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tableSeqence" class="col-sm-3 control-label">餐桌序列：</label>
                        <div class="col-sm-6">
                            <input type="text" name="tableSeqence" class="form-control input-width-small number required bs-tooltip" id="tableSeqence" maxlength="50" placeholder="餐桌序列" data-original-title="餐桌的排序，数字越小越靠前">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer" style="text-align:center">
                <button type="button" class="xdd-btn-default xdd-btn-marginRight" data-dismiss="modal">关闭</button>
                <button type="button" class="xdd-btn-primary" id="save" onclick="diningTableSave()">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->

<script type="text/javascript">
$('#typeNameSelect').selectCss({
url:contextpath+'/tableCategory/queryStoreCategoryList?storeId=${storeId}',
nameField : 'peopleNumber',
valueField : 'id',
name:'tableTypeId',
value:'',
onLoadSuccess:function(data){
if(data == null) data = [];
   data.splice(0,0,{'id':'', peopleNumber:'--请选择--'});
   return data;
},
 onChange:function(cur, prev){
    /*$('#oneLevelCategory').selectCss("resetUrl", contextpath + '/shopCategory/queryOneLevelCategory?shopId='+cur.id, '');
    $('#fixForm').bootstrapValidator('revalidateField', "shopId_label");*/
     $('#tableFrom').bootstrapValidator('revalidateField',"tableTypeId_label");
}
});
</script>