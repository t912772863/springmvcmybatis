package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.common.validation.NotNull;
import com.tian.springmvcmybatis.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色控制层
 * Created by Administrator on 2016/11/18 0018.
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
    @Autowired
    IRoleService roleService;

    /**
     * 根据主键查询角色信息
     * @param id
     * @return
     */
    @RequestMapping("query_role_by_id")
    @ResponseBody
    public ResponseData queryRoleById(@NotNull Long id){
        return successData.setData(roleService.queryRoleById(id));
    }


}
