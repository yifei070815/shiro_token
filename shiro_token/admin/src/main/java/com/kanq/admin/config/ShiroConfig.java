package com.kanq.admin.config;

import com.kanq.admin.config.realm.AdminShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    /**
     Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
     //注意过滤器配置顺序 不能颠倒
     //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
     filterChainDefinitionMap.put("/logout", "logout");
     // 配置不会被拦截的链接 顺序判断
     filterChainDefinitionMap.put("/static/**", "anon");
     filterChainDefinitionMap.put("/ajaxLogin", "anon");
     filterChainDefinitionMap.put("/login", "anon");
     filterChainDefinitionMap.put("/**", "authc");
     //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
     shiroFilterFactoryBean.setLoginUrl("/unauth");
     // 登录成功后要跳转的链接
     //shiroFilterFactoryBean.setSuccessUrl("/index");
     //未授权界面;
     //shiroFilterFactoryBean.setUnauthorizedUrl("/403");
     shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

     */


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> filterDefMap = new LinkedHashMap<>();

        filterDefMap.put("/admin/login", "anon");
        filterDefMap.put("/swagger-ui.html", "anon");
        //filterDefMap.put("/testController/test1", "anon");
        filterDefMap.put("/admin/logout", "logout");

        //所有的url必须通过认证才能访问，authc表示需要认证才能访问 一般将/**放在最为下边 -->:这是一个坑呢
        filterDefMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterDefMap);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //如查没有登录访问了禁止接口就会转到  下面的登录接口去,接着会抛出错误信息“请先登录”
        shiroFilterFactoryBean.setLoginUrl("/admin/toLogin");
//        shiroFilterFactoryBean.setLoginUrl("/admin/login");


        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index/index");
        return shiroFilterFactoryBean;
    }


    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminShiroRealm());
        return securityManager;
    }

    @Bean
    public AdminShiroRealm adminShiroRealm() {
        AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
        return adminShiroRealm;
    }


    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



}


/**
 * @Configuration
 * public class ShiroConfig {
 *     @Bean
 *     public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
 *         ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
 *         // 必须设置 SecurityManager
 *         shiroFilterFactoryBean.setSecurityManager(securityManager);
 *         // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
 *         shiroFilterFactoryBean.setLoginUrl("/notLogin");
 *         // 设置无权限时跳转的 url;
 *         shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
 *
 *         // 设置拦截器
 *         Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
 *         //游客，开发权限
 *         filterChainDefinitionMap.put("/guest/**", "anon");
 *         //用户，需要角色权限 “user”
 *         filterChainDefinitionMap.put("/user/**", "roles[user]");
 *         //管理员，需要角色权限 “admin”
 *         filterChainDefinitionMap.put("/admin/**", "roles[admin]");
 *         //开放登陆接口
 *         filterChainDefinitionMap.put("/login", "anon");
 *         //其余接口一律拦截
 *         //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
 *         filterChainDefinitionMap.put("/**", "authc");
 *
 *         shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
 *         System.out.println("Shiro拦截器工厂类注入成功");
 *         return shiroFilterFactoryBean;
 *     }
 *
 *
 *      * 注入 securityManager
 *
 *      @Bean
 *     public SecurityManager securityManager(){
        *DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        *         // 设置realm.
        *securityManager.setRealm(customRealm());
        *return securityManager;
        *}
        *
        *
 *      * 自定义身份认证 realm;
 *      * <p>
 *      * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
 *      * 否则会影响 CustomRealm类 中其他类的依赖注入
 *
        @Bean
 *     public CustomRealm customRealm(){
        return new CustomRealm();
        }
    }
 */
