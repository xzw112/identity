package com.tiptimes.identity.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity httpSecurity)throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/index").authenticated()
                .and()
                .csrf().disable()// 关闭防跨域攻击功能,否则无法使用post请求
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/link/gotoLogin?logout");
        httpSecurity.headers().frameOptions().disable();
    }



}
