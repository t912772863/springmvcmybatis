package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.common.validation.Enum;
import com.tian.springmvcmybatis.dao.common.validation.Length;
import com.tian.springmvcmybatis.dao.common.validation.Number;
import com.tian.springmvcmybatis.dao.common.validation.Regular;
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
    public ResponseData queryRoleById(@Regular("^(1[0-9]{10})$") Long id, @Enum(enumeration = {"1","2"},nullAble = false) Integer status){
        System.out.println(id);
        return successData.setData(roleService.queryRoleById(id));
    }

}
