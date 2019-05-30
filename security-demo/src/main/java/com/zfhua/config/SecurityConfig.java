package com.zfhua.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger log = LoggerFactory.getLogger(SecurityConfig.class);


    /**
     *
     * AuthenticationManagerBuilder 用于创建一个 AuthenticationManager
     *
     * 实现内存验证、LADP验证、基于JDBC的验证、添加UserDetailsService、添加AuthenticationProvider
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("123").roles("USER").and()
                .withUser("admin").password("admin").roles("USER", "ADMIN");
    }

    /*
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }*/


    /**
     *
     * 默认配置为：
     *
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

        http.authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin().and()
                .httpBasic();
    }
}
