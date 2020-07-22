package com.tiptimes.identity.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity httpSecurity)throws Exception {
        httpSecurity .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**", "/token/**", "/actuator/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll();
        httpSecurity.authorizeRequests()
                .antMatchers("/clientLogin").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //.anyRequest().authenticated()
                .antMatchers("/client").permitAll()
                .and()
                .csrf().disable()// 关闭防跨域攻击功能,否则无法使用post请求
                .formLogin()
                .loginPage("/clientLogin")
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/link/gotoLogin?logout");
        httpSecurity.headers().frameOptions().disable();
    }



}
