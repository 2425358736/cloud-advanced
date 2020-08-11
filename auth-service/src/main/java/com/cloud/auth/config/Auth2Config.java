package com.cloud.auth.config;

import com.cloud.auth.config.exception.CustomWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author admin
 * EnableAuthorizationServer 开启授权服务功能
 */
@Configuration
@EnableAuthorizationServer
public class Auth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier(value = "authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService ;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 配置客户端基本信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 创建一个客户端 名字是spring
                .withClient("consumer")
                .secret(bCryptPasswordEncoder.encode("123456"))
                .scopes("server")
                .authorizedGrantTypes("refresh_token","password")
                .accessTokenValiditySeconds(36000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                // 指定token存储位置
        endpoints.tokenStore(tokenStore)
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // 是否重复使用 refresh_token
                .reuseRefreshTokens(false)
                // 自定义异常处理
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServer) {
        authorizationServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
