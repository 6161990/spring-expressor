package com.sp.fc.web.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import javax.sql.DataSource;

@EnableCaching
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AclGlobalMethodConfig extends GlobalMethodSecurityConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(aclPermissionEvaluator());
        return expressionHandler;
    }

//    @Bean
//    public PermissionEvaluator aclPermissionEvaluator() {
//        AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(
//                aclService()
//        );
//
//        return permissionEvaluator;
//    }
//
//    @Bean
//    public AclService aclService() {
//        JdbcMutableAclService aclService = new JdbcMutableAclService(
//                dataSource(),
//                lookupStrategy(),
//                aclCache()
//        );
//        return aclService;
//    }
//
//    @Bean
//    public EhCacheBasedAclCache aclCache() {
//        return new EhCacheBasedAclCache(
//                aclEhCacheFactoryBean().getObject(),
//                permissionGrantingStrategy(),
//                aclAuthorizationStrategy()
//        );
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean ehcacheFactoryBean() {
//        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
//        return factoryBean;
//    }
//
//    @Bean
//    public EhCacheFactoryBean aclEhCacheFactoryBean() {
//        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
//        ehCacheFactoryBean.setCacheManager(ehcacheFactoryBean().getObject());
//        ehCacheFactoryBean.setCacheName("aclCache");
//        return ehCacheFactoryBean;
//    }
//
//    @Bean
//    PermissionGrantingStrategy permissionGrantingStrategy(){
//        return new DefaultPermissionGrantingStrategy(consoleAuditLogger());
//    }
//
//    @Bean
//    AuditLogger consoleAuditLogger() {
//        return new ConsoleAuditLogger();
//    }
//
//
//    @Bean
//    LookupStrategy lookupStrategy() {
//        return new BasicLookupStrategy(
//                dataSource(),
//                aclCache(),
//                aclAuthorizationStrategy(),
//                consoleAuditLogger()
//        );
//    }
//
//    private AclAuthorizationStrategy aclAuthorizationStrategy() {
//        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }
}