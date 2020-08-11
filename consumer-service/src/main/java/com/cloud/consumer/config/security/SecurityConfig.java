package com.cloud.consumer.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 刘志强
 * @date 2020/7/21 09:34
 * * @EnableGlobalMethodSecurity(securedEnabled=true)
 * * 开启@Secured 注解过滤权限
 * *
 * * @EnableGlobalMethodSecurity(jsr250Enabled=true)
 * * 开启@RolesAllowed 注解过滤权限
 * *
 * * @EnableGlobalMethodSecurity(prePostEnabled=true)
 * * 使用表达式时间方法级别的安全性 4个注解可用
 * * -@PreAuthorize 在方法调用之前,基于表达式的计算结果来限制对方法的访问
 * * -@PostAuthorize 允许方法调用,但是如果表达式计算结果为false,将抛出一个安全性异常
 * * -@PostFilter 允许方法调用,但必须按照表达式来过滤方法的结果
 * * -@PreFilter 允许方法调用,但必须在进入方法之前过滤输入值
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthorizationTokenFilter authenticationTokenFilter;

    public SecurityConfig(AuthorizationTokenFilter authenticationTokenFilter, UserDetailsServiceImpl userDetailsService) {
        this.authenticationTokenFilter = authenticationTokenFilter;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("ROOT");
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                // /actuator 下的请求需要ROOT角色
                .antMatchers("/actuator/**").hasRole("ROOT")
                .and().httpBasic();

        http.authorizeRequests()
                // /user下的请求需要user全部权限
                .antMatchers("/user/**").hasAuthority("user")
                // /admin 下的请求需要admin权限
                .antMatchers("/admin/**").hasAuthority("admin")
                // /authenticated 下的请求允许认证过的用户访问
                .antMatchers("/authenticated/**").authenticated()
                // 其余的请求允许所有用户无条件访问
                .antMatchers("/login", "/getConsumer","/my/test3").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
