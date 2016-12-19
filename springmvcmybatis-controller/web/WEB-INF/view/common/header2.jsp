<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    String shopPath = "http://sm.kf.xiangqianpos.com";
%>
<div class="navbarTop navbar-fixed-top clearfix fix-header">
    <div class="navTopPa hidden-sm hidden-xs">
        <a class="logoXdd" href="<%=shopPath%>/index"></a>
    </div>
    <ul class="navbar-nav toolbar pull-right" style="padding-top: 7px; padding-right: 18px;">
        <li class="text fix-news" style="position: relative;">
            <a href="<%=shopPath%>/message/messageManager"><span>系统消息</span></a>
            <i>...</i>
        </li>

        <li class="text fix-manager"><a href="<%=request.getContextPath()%>/user/userInfo"><span><shiro:principal property="roleName"/>  [<shiro:principal property="userName"/>] </a>    |    <a href="<%=shopPath%>/recharge/rechargeRecordManager">余额</span><span style="color:#f08200;"> <shiro:principal property='remainingAmount'/> </span>元</a> </li>
        <li class="text fix-password">
            <span class="gray"><a data-toggle="modal" class="link" href="#modifyDlg">修改密码</a></span>
        </li>
        <li class="text fix-exit">
            <span class="gray"><a class="link" href="javascript:logout();">退出</a></span>
        </li>
    </ul>
</div>
<div style="height: 72px; width: 100%"><!--占位符，否则会造成弹窗位置错误--></div>
<div class="modal fade" id="modifyDlg" tabindex="-1" role="dialog" aria-labelledby="modifyDlgLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modifyDlgLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form id="pwdModifyForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="currentPwd" class="col-sm-3 control-label">原始密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="currentPwd" name="password" placeholder="请输入原始密码">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newpassword" class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="newpassword" name="newPwd" placeholder="请输入新密码">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="comfirmpassword" class="col-sm-3 control-label">确认密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="comfirmpassword" name="comfirmPwd" placeholder="请输入确认密码">
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="xdd-btn-default xdd-btn-marginRight" data-dismiss="modal">取消</button>
                <button type="button" class="xdd-btn-primary" id="saveBtn" onclick="modifyPwd();">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->
<script type="text/javascript">
    function modifyPwd(){
        if($('#pwdModifyForm').bootstrapValidator('validate').data('bootstrapValidator').isValid() ==false) return;
        $.xAjax({
            url : contextpath + "/user/modifyPwd",
            type : 'post',
            data : $('#pwdModifyForm').serialize(),
            success : function(data){
                if(data.success){
                    $("#modifyDlg").modal("hide");
                    bootbox.alert("修改密码成功！");
                }else{
                    bootbox.alert(data.message);
                }
            }
        });
    }
    /*修改密码*/
    $(function(){
        $('#modifyDlg').on('hide.bs.modal', function () {
            $('#pwdModifyForm').data('bootstrapValidator').resetForm(true);
        });

        $('#pwdModifyForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                password: {
                    validators: {
                        notEmpty: {
                            message: '原密码必填！'
                        }
                    }
                },
                newPwd: {
                    validators: {
                        notEmpty: {
                            message: '新密码是必填的！'
                        },
                        different: {
                            field: 'password',
                            message: '新密码和原密码不能相同！'
                        }
                    }
                },
                comfirmPwd: {
                    validators: {
                        notEmpty: {
                            message: '确认密码是必填的！'
                        },
                        identical: {
                            field: 'newPwd',
                            message: '确认密码和新密码不一致！'
                        }
                    }
                }
            }
        });
    });
    function logout(){
        bootbox.confirm("您真的要注销吗？", function(result){
            if(!result) return;
           window.location.href = '${logoutUrl}/logout';
            //window.location.href="http://192.168.190.102:8093/chapter14-server/logout?service=<%=basePath%>/logout";
//            window.location.href= "http://admin.kf.xiangqianpos.com/shiroCas/logout?service=http://120.26.96.41:8088/cas";
        });
    }
</script>
