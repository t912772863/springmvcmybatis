package com.tian.springmvcmybatis.controller.common;

import com.tian.common.other.BusinessException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器, 已经弃用, 改用切面实现,对所有调用controller的方法都拦截,进行是否登录的检验
 * Created by Administrator on 2016/11/30 0030.
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 配置一些请求地址不被拦截,比如登录请求等
     */
//    private List<String> uncheckUrls;

//    public List<String> getUncheckUrls() {
//        return uncheckUrls;
//    }

//    public void setUncheckUrls(List<String> uncheckUrls) {
//        this.uncheckUrls = uncheckUrls;
//    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取请求地址的第一层级
//        String requestUrl = httpServletRequest.getRequestURI();
//        requestUrl = requestUrl.substring(0,requestUrl.indexOf("/",1));
        // 免拦截,直接放行
//        if(uncheckUrls.contains(requestUrl)){
//            return true;
//        }
        // 先查看用户是否为登录状态
        if (httpServletRequest.getSession(true).getAttribute("user")!=null){
            return true;
        }
        // 不能放行.说明未登录
        throw new BusinessException(501,"请先登录");
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
