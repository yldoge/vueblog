/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/21/21
 * Time: 3:10 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.config
 * Class: ShiroConfig
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.config;

import com.yldog.vueblog.shiro.AccountRealm;
import com.yldog.vueblog.shiro.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class ShiroConfig {

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        // inject redisSessionDAO
        sessionManager.setSessionDAO(redisSessionDAO);

        return sessionManager;
    }

    @Bean(name = "securityManager")
    public SessionsSecurityManager securityManager(AccountRealm accountRealm,
                                                   SessionManager sessionManager,
                                                   RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //inject sessionManager
        securityManager.setSessionManager(sessionManager);

        // inject redisCacheManager
        securityManager.setCacheManager(redisCacheManager);

        // ?????????realm
        securityManager.setRealm(accountRealm);

        // ????????????session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator=new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    @Bean(name = "filterChainDef")
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition filterChainDef = new DefaultShiroFilterChainDefinition();
        LinkedHashMap<String, String> urlFilterMap = new LinkedHashMap<>();

        // "anon" ?????????url?????? "jwt" ??????put????????????????????????/unauthorized/** ???????????????????????????
        // ?????? /unauthorized/** ?????????????????????????????????
        urlFilterMap.put("/unauthorized/**", "anon");
        // ???????????????????????? jwt filter
        urlFilterMap.put("/**", "jwt");

        filterChainDef.addPathDefinitions(urlFilterMap);

        return filterChainDef;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager,
                                                         @Qualifier("filterChainDef") ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        // ?????????????????? jwt filter
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        bean.setFilters(filterMap);

        bean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());

        bean.setUnauthorizedUrl("/unauthorized/");
        return bean;
    }

    /**
     * ???????????????????????????????????????????????????????????????
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
