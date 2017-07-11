package springstudy.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springstudy.aop.Audience;
import springstudy.knights.BraveKnight;
import springstudy.knights.SlayDragonQuest;

/**
 * spring中可以通过java类的形式来实现配置文件, 可以用于替换application.xml文件,甚至更加强大.
 *
 * @Configuration 注解表明这个类是一个配置类，该类应该包含在Spring应用上下文中如何创建bean的细节
 *
 * @ComponentScan 组件扫描当前类所在的包, 及所有子包的组件
 *
 * @Bean 注解的方法可以采用任何必要的Java功能来产生bean实例
 *
 * @Import 在4.2之前只支持导入配置类在4.2,@Import注解支持导入普通的java类,并将其声明成一个bean
 *
 * @ImportResource 可以把xml导入, 使得xml中配置的bean和java配置中的bean可以交互
 *
 * @Profile 可以指定在特定环境才注入某一个bean,类似于xml文件中的beans标签的profile属性功能一致
 *      激活Profile属性, 在web.xml文件中激活.最好两个地方都要设置,一个是应用上下文,一个是servlet
 *
 * @Conditional 定义条件化的Bean,只有满足指定的条件时,才会注入的Bean,此功能是Profile的加强版
 *
 * @Primary 当依赖注入某一类型的Bean有多类型的实现的时候,可能会出现自动注入失败的情况, 这时可以针对其中
 *      某一个类,添加该属性, 表示在注入的时候优先选择这个类型.
 *
 * @Qualifier 当依赖注入的时候(和@Auwared注解一起用),存在多个可选,可以直接指定其中某一个进行注入.
 *      当和@Bean或者@Compnent注解一起用的时候,相关于给这个Bean命名. 这个相当于给Bean多了一个特性,这个方法还不行可以
 *      通过BeanId的方式解决类似注入问题
 *
 * @Scope 在注入Bean的时候可以指定其作用域, 默认都是单例的.
 *      proxyMode属性: 注入接口类型的话用ScopedProxyMode.INTERFACES.  注入类,类型的代理用ScopedProxyMode.TARGET_CLASS
 *
 * @EnableAspectJAutoProxy 该注解在java配置中启用aop切面相关功能.
 *
 * @EnableWebMvc 相当于xml配置文件中的<mvc:annotation-driven>标签,可以启用springmvc相关功能.
 *
 *
 * Created by Administrator on 2017/6/21 0021.
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@EnableWebMvc
public class JavaConfig extends WebMvcConfigurerAdapter{
    /**
     * 配置JSP视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     * 配置静态资源的处理,
     * 要求DispatcherServlet将静态资源文件的请求转发到容器默认的Servlet上,而不是DispatcherServlet上
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }



    /**
     * 注入切面类
     * @return
     */
    @Bean
    public Audience audience(){
        return new Audience();
    }

    /*
    @Bean注解会把下面这个方法返回的bean实例添加到spring容器中. 该bean默认的名字就是方法名,可以通道name属性重新指定
     */
    @Bean(name = "slayDragonQuest")
    public SlayDragonQuest getSlayDragonQuest() {
        return new SlayDragonQuest(null);
    }

    /*
    除了上面的简单bean,还有一些有依赖关系的bean要注解,
     */
    @Bean(name = "braveKnight")
    public BraveKnight getBraveKnight() {
        // 直接引用前面的bean的创建方法,就可以实现依赖注入了
        /*
        看起来，BraveKnight是通过调用getSlayDragonQuest()得到的，但情况并非完全如此。因
        为getSlayDragonQuest()方法上添加了@Bean注解，Spring将会拦截所有对它的调用，并确保直接
        返回该方法所创建的bean，而不是每次都对其进行实际的调用。
         */
        return new BraveKnight(getSlayDragonQuest());
    }

    /*
    如果觉得上面的依赖注入需要传入方法,有点困惑,还可以直接通过参数
    spring在发现注入BraveKnight的时候需要一个SlayDragonQuest类型的参数,会自动在上下文环境找
    到一个合适的Bean进行注入.
     */
    @Bean(name = "braveKnight")
    public BraveKnight getBraveKnight(SlayDragonQuest slayDragonQuest){
        return new BraveKnight(slayDragonQuest);
    }

    /**
     * 环境类型注入Bean
     * @return
     */
    @Bean
    @Profile("dev")
    public BraveKnight a(){
        return new BraveKnight(null);
    }

    /**
     * 条件注入Bean
     * @return
     */
    @Bean
    @Conditional(TestConditional.class)
    public BraveKnight b(){
        return new BraveKnight(null);
    }

}
