<%--
  Created by IntelliJ IDEA.
  User: jordan.liu
  Date: 2016/1/7
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="tableCategoryDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form id="tableCategoryFrom" class="form-horizontal">
                    <input name="id" id="id" type="hidden">
                    <input type="hidden" name="storeId" id="formStoreId" value="${storeId}">
                    <div class="form-group">
                        <label for="typeName" class="col-sm-3 control-label">餐桌类型名称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="typeName" class="form-control" id="typeName" placeholder="餐桌类型名称">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="peopleNumber" class="col-sm-3 control-label">餐桌的人数：</label>
                        <div class="col-sm-4">
                            <input type="text" name="peopleNumber" class="form-control" id="peopleNumber" placeholder="餐桌的人数">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block" style="color:red">*</p>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="xdd-btn-default xdd-btn-marginRight" data-dismiss="modal">关闭</button>
                <button type="button" class="xdd-btn-primary" id="save" onclick="tableCategoryTableSave()">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->