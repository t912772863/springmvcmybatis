<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="accountDiv">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">账号配置</h4>
    </div>
    <div class="modal-body" style="max-height: 500px; overflow: hidden; overflow-y: auto;">
        <form id="countForm" class="form-horizontal">
            <c:choose>
             <c:when test="${storeAccountDto.organizeType== 2}">
                 <div class="row form-group">
                     <label for="wm_shopSelect" class="col-sm-3 control-label">主店<span style="color:red;">*</span></label>
                     <div class="col-sm-6">
                         <div id="wm_shopSelect" ></div>
                     </div>
                     <div class="col-sm-2">
                         <p class="help-block"></p>
                     </div>
                 </div>
             </c:when>
             <c:otherwise>

             </c:otherwise>
            </c:choose>
            <input type="hidden" name="mealTypeId" value="${storeAccountDto.mealTypeId}">
            <input type="hidden" name="id" value="${storeAccountDto.id}">
            <input type="hidden" name="account" id="account" value="${storeAccountDto.account}">
            <input type="hidden" name="organizeId" id="organizeId" value="${storeAccountDto.organizeId}">
            <input type="hidden" name="organizeType" id="organizeType" value="${storeAccountDto.organizeType}">
            <c:forEach items="${storeAccountDto.accountDTOList}" var="pro" varStatus="cvs">
                <div class="form-group row" mark="extend">
                    <label class="col-sm-3 control-label">${pro.keyWord}<c:if test="${pro.required == 1}"><span style="color:red;">*</span></c:if></label>
                    <div class="col-sm-8">
                        <input type="hidden" name="accountDTOList[${cvs.index}].proValId" value="${pro.proValId}">
                        <input type="hidden" name="accountDTOList[${cvs.index}].applicationTableId" value="${pro.applicationTableId}">
                        <input type="hidden" name="accountDTOList[${cvs.index}].propertyId" value="${pro.id}">
                        <input type="hidden" name="accountDTOList[${cvs.index}].applicationType" value="${pro.applicationType}">
                        <c:choose>
                            <c:when test="${pro.inputType == 2}">
                                <textarea class="form-control" name="accountDTOList[${cvs.index}].value" value="${pro.value}"></textarea>
                            </c:when>
                            <c:when test="${pro.inputType == 3}">
                                <c:forTokens items="${pro.options}" delims="," var="item" varStatus="vs1">
                                    <input type="radio" style="width: 20px;" id="c_box_${pro.id}_${vs1.index}" name="propertyValueList[${cvs.index}].value" value="${item}"/>
                                    <label style="padding-right: 20px;" for="c_box_${pro.id}_${vs1.index}">${item}</label>
                                </c:forTokens>
                            </c:when>
                            <c:when test="${pro.inputType == 4}">
                                <select class="form-control" name="accountDTOList[${cvs.index}].value">
                                    <c:forTokens items="${pro.options}" delims="," var="item" varStatus="vs">
                                        <option value="${item}">${item}</option>
                                    </c:forTokens>
                                </select>
                            </c:when>
                            <c:when test="${pro.inputType == 5}">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <input type="file" class="form-control" <c:if test="${empty pro.value and pro.required == 1}">required data-bv-notempty-message="该文件是必须上传的！"</c:if> name="storeAccountDTOList[${cvs.index}].file" <c:if test="${not empty pro.validator}">${pro.validator}</c:if>>
                                    </div>
                                    <div class="col-xs-2" style="line-height: 30px;">
                                        <c:if test="${not empty pro.value}">
                                            <a href="${pro.value}" target="_blank">查看</a>
                                        </c:if>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${pro.inputType == 6}">
                                <c:set var="iscontain" value="false" />
                                <c:forTokens items="${pro.options}" delims="," var="item" varStatus="vs1">
                                    <c:forTokens items="${pro.value}" delims="," var="val">
                                        <c:if test="${val eq item}">
                                            <c:set var="iscontain" value="true" />
                                        </c:if>
                                    </c:forTokens>
                                    <input type="checkbox" style="width: 20px;" id="c_box_${pro.id}_${vs1.index}" name="accountDTOList[${cvs.index}].value"
                                           <c:if test="${iscontain eq true}">checked="checked" </c:if> value="${item}"/>
                                    <label style="padding-right: 20px;" for="c_box_${pro.id}_${vs1.index}">${item}</label>
                                </c:forTokens>
                            </c:when>
                            <c:otherwise> <%--name="accountDTOList[${cvs.index}].value" ${pro.keyWordEn}--%>
                                <input class="form-control"  name="accountDTOList[${cvs.index}].value" value="${pro.value}"
                                       <c:if test="${pro.required == 1}">required data-bv-notempty-message="该字段是必填的！"</c:if>
                                        <c:choose>
                                            <c:when test="${not empty pro.validator}">${pro.validator}</c:when><c:otherwise>type="text"</c:otherwise>
                                        </c:choose>
                                        />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>
    <script type="text/javascript">
        $('#wm_shopSelect').selectCss({
            url: contextpath+'/merchant/searchMerchantList',
            nameField : 'name',
            valueField : 'id',
            name:'shopId',
            value:'${shopId}',
            disable : '${storeAccountDto.id}' == ''?false:true,
            onLoadSuccess:function(data){
                if(data == null) data = [];
                data.splice(0,0,{'id':'', name:'--请选择--'});
                return data;
            },
            onLoadError:function(){
                bootbox.alert("加载商户下拉列表出错");
            },
            onChange:function(cur, prev){
                $('#countForm').bootstrapValidator('revalidateField', "shopId_label");
            }
        }).selectCss('disable', '${storeAccountDto.id}' == ''?false:true);
        Validator();

        //表单验证
        function Validator() {
            $('#countForm').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    shopId_label: {
                        message: '商户不能为空！',
                        validators: {
                            notEmpty: {
                                message: '商户不能为空！'
                            },
                            callback: {
                                callback: function (value, validator, $field) {
                                    if (value == "--请选择--") return false;
                                    return true;
                                }
                            }
                        }
                    }
                }

            });
        }
    </script>
    <div class="modal-footer">
        <button type="button" class="xdd-btn-default xdd-btn-marginRight" data-dismiss="modal">关闭</button>
        <button type="button" class="xdd-btn-primary" onclick="countSave()">保存</button>
    </div>
</div>