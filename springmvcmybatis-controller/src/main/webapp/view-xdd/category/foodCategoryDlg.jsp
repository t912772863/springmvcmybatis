<%--
  Created by IntelliJ IDEA.
  User: jordan.liu
  Date: 2016/1/7
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px; ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form id="fixFrom" class="form-horizontal">
                    <input name="id" id="extn" type="hidden">
                    <!--根据登录用户类型显示商户选择不同-->
                    <c:if test="${loginUser.userType eq 1}">
                        <div class="form-group" id="merchantAdmin">
                            <label class="col-sm-3 control-label">主店：</label>
                            <div class="col-sm-7">
                                <div id="merchantSelect"></div>
                            </div>
                            <div class="col-sm-2">
                                <p class="help-block"></p>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${loginUser.userType eq 2}">
                        <div class="form-group" id="merchant">
                            <label for="shopName" class="col-sm-3 control-label">主店：</label>
                            <div class="col-sm-7">
                                <input type="text" disabled="disabled" class="form-control" id="shopName" placeholder="${merchant.name}">

                            </div>
                            <div class="col-sm-2">
                                <p class="help-block"></p>
                            </div>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">父级分类(可不选)：</label>
                        <div class="col-sm-7">
                            <div id="oneLevelCategory"></div>
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="categoryName" class="col-sm-3 control-label">分类名称：</label>
                        <div class="col-sm-7">
                            <input type="text" name="categoryName" class="form-control" id="categoryName" placeholder="请输入菜品分类名称">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>



                    <div class="form-group">
                        <label for="rank" class="col-sm-3 control-label">分类排序：</label>
                        <div class="col-sm-7">
                            <input type="text" name="rank" class="form-control" id="rank" placeholder="请输入菜品排序（数字越小，排名越靠前）">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注：</label>
                        <div class="col-sm-7">
                            <textarea cols="80" rows="3" name=" " class="form-control" id="remark" placeholder="请输入您的备注信息"></textarea>
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="xdd-btn-default xdd-btn-marginRight" data-dismiss="modal">关闭</button>
                <button type="button" class="xdd-btn-primary" id="save" onclick="foodCategoryAjax(<c:out value="${loginUser.userType}"/><c:if test="${loginUser.userType eq 2}">,<c:out value="${merchant.id}"/></c:if>)">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->
<script type="text/javascript">
    //一级菜品分类下拉框
    $('#oneLevelCategory').selectCss({
        url:contextpath+'/shopCategory/queryOneLevelCategory',
        nameField : 'categoryName',
        valueField : 'id',
        name:'categoryId',//提交时表单的name
        value:'${id}',
        onLoadSuccess:function(data){
            if(data == null) data = [];
            data.splice(0,0,{'id':'', categoryName:'--请选择--'});
            return data;
        },
        onChange:function(cur, prev){
            /* $('#foodCategoryId').selectCss("resetUrl", contextpath + '/foodCategory/queryFoodCategoryList?shopId='+cur.id, '');
             $('#fixForm').bootstrapValidator('revalidateField', "shopId_label");*/
        }
    });

    //商户下拉框
    $('#merchantSelect').selectCss({
        url:contextpath+'/shopCategory/queryShopListByLoginUser',
        nameField : 'name',
        valueField : 'id',
        name:'shopId',
        value:'${merchantId}',
        onLoadSuccess:function(data){
            if(data == null) data = [];
            data.splice(0,0,{'id':'', name:'--请选择--'});
            return data;
        },
        onChange:function(cur, prev){
            $('#oneLevelCategory').selectCss("resetUrl", contextpath + '/shopCategory/queryOneLevelCategory?shopId='+cur.id, '');
            $('#fixForm').bootstrapValidator('revalidateField', "shopId_label");
        }
    });
</script>