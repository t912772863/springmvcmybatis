package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.service.common.util.DocumentExport;
import com.tian.springmvcmybatis.dao.common.validation.NotNull;
import com.tian.springmvcmybatis.dao.entity.Order;
import com.tian.springmvcmybatis.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 订单控制层
 * Created by Administrator on 2016/12/3 0003.
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    /**
     * 根据订单ID查询订单信息
     * @param id
     * @return
     */
    @RequestMapping("query_order_by_id")
    @ResponseBody
    public ResponseData queryOrderById(@NotNull Long id){
        Order order = orderService.queryOrderById(id);
        return successData.setData(order);
    }

    /**
     * 条件综合查询订单信息
     * @return
     */
    @RequestMapping("query_order_by_rule")
    @ResponseBody
    public ResponseData queryOrderByRule(HttpServletRequest request){
        List<Order> list = orderService.queryOrderByRule(getSessionUser(request).getId());
        return successData.setData(list);
    }

    /**
     * 导出数据成一个EXCEL表格
     * @param response
     * @throws IOException
     */
    @RequestMapping("export_excel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 查询出数据
        List<Order> list = orderService.queryOrderByRule(getSessionUser(request).getId());
        OutputStream out = response.getOutputStream();
        // 设置响应头,
        response.setHeader("content-disposition", "attachment;filename=" + new String("demo.xlsx".getBytes("GB2312"), "ISO8859-1"));
        //写入excel文件中
        String[] strings = new String[]{"订单ID","第三方订单ID","用户ID","总金额(单位分)","备注","订单状态","创建时间","更新时间","数据状态"};
        DocumentExport.exportExcel("title",strings,list,out,"yyyy-MM-dd HH:mm:ss");
    }

}
