package com.kanq.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }
    @Bean//必须用Bean来加入到spring容器中，被上面的拦截调用
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }



//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationInterceptor())
//                .addPathPatterns("/**"); //拦截所有请求
//        //排除以下的路径请求
////        addInterceptor.excludePathPatterns("/employee/login");
////        addInterceptor.excludePathPatterns("/employee/register");
////        addInterceptor.excludePathPatterns("/employee/sendShortMsg");
////        addInterceptor.excludePathPatterns("/girl/findAll");
////        addInterceptor.excludePathPatterns("/employee/findAll");
////        addInterceptor.excludePathPatterns("/girl/findByAge");
//
//    }


/**

 浏览器中访问 http://localhost:8080/ 就是访问 main/resources/static/login.html 。
    该段代码相当于
    @Controller
    public class TestController{
        @RequestMapping("/")
        public String testController(){
            return "login";
        }
    }

 */


//    /* 视图跳转控制器 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置访问路径为 “/”
        registry.addViewController("/api").setViewName("forward:/swagger-ui");
//      registry.addViewController("/").setViewName("forward:/login");
        //设置为最高优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


//    静态资源处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
    //对静态资源的配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/smallapple/**")
                    // /apple/**表示在磁盘apple目录下的所有资源会被解析为以下的路径
                    .addResourceLocations("file:G:/itemsource/smallapple/") //媒体资源 是文件真实的存储路径
                    .addResourceLocations("classpath:/META-INF/resources/");  //swagger2页面
        } else {  //linux 和mac
            registry.addResourceHandler("/smallapple/**")
                    .addResourceLocations("file:/resources/smallapple/")   //媒体资源
                    .addResourceLocations("classpath:/META-INF/resources/");  //swagger2页面;
        }
    }
     */



    // 解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 允许跨域访问的路径
                .allowCredentials(true) // 是否发送cookie
                .allowedOrigins("*")// 允许跨域访问的源 也可以写具体的 http://domanin.com ,http://domanin.com,
                .allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS")// 允许请求方法
                .maxAge(3600);// 预检间隔时间
    }
}


    /**拦截器配置
    void addInterceptors(InterceptorRegistry var1);
    视图跳转控制器
    void addViewControllers(ViewControllerRegistry registry);
    静态资源处理
    void addResourceHandlers(ResourceHandlerRegistry registry);
    默认静态资源处理器
    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);


     这里配置视图解析器
    void configureViewResolvers(ViewResolverRegistry registry);

     配置请求视图映射


    @Bean
    public InternalResourceViewResolver resourceViewResolver()
    {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //请求视图文件的前缀地址
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        //请求视图文件的后缀
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    视图配置

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
        registry.viewResolver(resourceViewResolver());
      //  registry.jsp("/WEB-INF/jsp/",".jsp");



        配置内容裁决的一些选项
    void configureContentNegotiation(ContentNegotiationConfigurer configurer);

*/