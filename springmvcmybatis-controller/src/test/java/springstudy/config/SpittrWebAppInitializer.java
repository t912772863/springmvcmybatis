package springstudy.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 通过java类配置的方式来代替web.xml方式对DispatcherServlet的配置
 * Created by Administrator on 2017/6/26 0026.
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 返回的
     带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下
     文中的bean
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    /**
     * 返回的带有@Configuration注解的类将会用来定
     义DispatcherServlet应用上下文中的bean
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    /**
     *
     * @return
     */
    protected String[] getServletMappings() {
        // 将DispatcherServlet映射到"/"
        return new String[]{"/"};
    }
}
