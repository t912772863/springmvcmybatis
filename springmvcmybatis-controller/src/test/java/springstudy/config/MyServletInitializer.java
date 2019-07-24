package springstudy.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *  自定义Servlet初始化容器
 * Created by Administrator on 2017/6/27 0027.
 */
public class MyServletInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 注册Servlet
//        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet",MyServlet.class);
        // 添加Servlet的映射
//        myServlet.addMapping("/myServlet");

        // 注册Filter
//        FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
        // 添加过滤路径
//        myFilter.addMappingForUrlPatterns(null, false, "/myServlet");

    }
}
