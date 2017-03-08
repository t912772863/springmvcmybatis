<%--
  Created by IntelliJ IDEA.
  User: candy.zhao
  Date: 2016-3-18
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach items="${wmProValDtoList}" var="pp" varStatus="vs" step="2">
    <div class="row">
        <c:forEach items="${wmProValDtoList}" var="pro" varStatus="cvs" begin="${(vs.count-1)*2}" end="${vs.count*2-1}">
            <div class="col-sm-6 form-group">
                <label class="col-sm-4 control-label">${pro.keyWord}<c:if test="${pro.required == 1}"><span style="color:red;">*</span></c:if></label>
                <div class="col-sm-7">
                    <input type="hidden" name="proValList[${cvs.index}].id" value="${pro.proValId}">
                    <input type="hidden" name="proValList[${cvs.index}].busiId" value="${pro.busiId}">
                    <input type="hidden" name="proValList[${cvs.index}].propertyId" value="${pro.id}">
                    <c:choose>
                        <c:when test="${pro.inputType == 2}">
                            <textarea class="form-control" name="proValList[${cvs.index}].value">${pro.value}</textarea>
                        </c:when>
                        <c:when test="${pro.inputType == 3}">
                            <c:forTokens items="${pro.options}" delims="," var="item" varStatus="vs1">
                                <c:set var="labelVal" value="${fn:split(item, ':')}" />
                                <input type="radio" style="width: 20px;" id="c_box_${pro.id}_${vs1.index}" name="proValList[${cvs.index}].value" value="${labelVal[0]}" <c:if test="${labelVal[0] eq pro.value}">checked="checked"</c:if>/>
                                <label style="padding-right: 20px;" for="c_box_${pro.id}_${vs1.index}">${labelVal[1]}</label>
                            </c:forTokens>
                        </c:when>
                        <c:when test="${pro.inputType == 4}">
                            <select class="form-control" name="proValList[${cvs.index}].value">
                                <c:set var="iscontain" value="false" />
                                <c:forTokens items="${pro.options}" delims="," var="item" varStatus="vs">
                                    <c:forTokens items="${pro.value}" delims="," var="val">
                                        <c:if test="${val eq item}">
                                            <c:set var="iscontain" value="true" />
                                        </c:if>
                                    </c:forTokens>
                                    <c:set var="labelVal" value="${fn:split(item, ':')}" />
                                    <option value="${labelVal[0]}">${labelVal[1]}</option>
                                </c:forTokens>
                            </select>
                        </c:when>
                        <c:when test="${pro.inputType == 5}">
                            <div class="row">
                                <div class="col-xs-10">
                                    <input type="file" class="form-control" <c:if test="${empty pro.value and pro.required == 1}">required data-bv-notempty-message="该文件是必须上传的！"</c:if> name="proValList[${cvs.index}].file">
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
                                <input type="checkbox" style="width: 20px;" id="c_box_${pro.id}_${vs1.index}" name="proValList[${cvs.index}].value"
                                <c:if test="${iscontain eq true}">checked="checked" </c:if> value="${item}"/>
                                <label style="padding-right: 20px;" for="c_box_${pro.id}_${vs1.index}">${item}</label>
                            </c:forTokens>
                        </c:when>
                        <c:when test="${pro.inputType == 7}">
                            ${fn:replace(fn:replace(pro.options, "%shopId%", storeWmDto.storeVo.merchantId), "%index%", cvs.index)}
                        </c:when>
                        <c:otherwise>
                            <input class="form-control" name="proValList[${cvs.index}].value" value="${pro.value}" <c:if test="${pro.required == 1}">required data-bv-notempty-message="该字段是必填的！"</c:if>
                            <c:choose>
                                <c:when test="${not empty pro.validator}">${pro.validator}</c:when><c:otherwise>type="text"</c:otherwise>
                            </c:choose>
                            />
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
</c:forEach>
