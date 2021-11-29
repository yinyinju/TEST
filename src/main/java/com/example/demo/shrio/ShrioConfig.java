package com.example.demo.shrio;

import com.example.demo.jwt.JWTFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioConfig {

    /**
     * 日志打印
     * @return
     */
    private final  static org.slf4j.Logger log= LoggerFactory.getLogger(ShrioConfig.class);

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("shiroFilter")
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        log.info("--------shiro拦截器开始------------");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/login","anon");
        filterMap.put("/401","anon");

        //添加过滤器并且命名jwt
        LinkedHashMap<String,Filter> filterLinkedHashMap=new LinkedHashMap<>();
        filterLinkedHashMap.put("jwt",new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterLinkedHashMap);
        filterMap.put("/**","jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        /**
         * 关闭shiro自带的session,详情见文档
         *
         */
        DefaultSubjectDAO subjectDAO=new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator=new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager  securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;

    }
}
