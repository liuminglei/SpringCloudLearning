package com.luas.xmall.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.
                allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String client_secret = passwordEncoder.encode("123456");

        clients
                .inMemory()
                // admin，授权码认证、密码认证、客户端认证、简单认证、刷新token
                .withClient("admin")
                .secret(client_secret)
                .resourceIds("xmall-auth", "xmall-product")
                .scopes("server", "select")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials", "implicit")
                .redirectUris("http://www.baidu.com")

                .and()
                // client_1，密码认证、刷新token
                .withClient("client_1")
                .secret(client_secret)
                .resourceIds("xmall-auth", "xmall-product")
                .scopes("server", "select")
                .authorizedGrantTypes("password", "refresh_token")

                .and()
                // client_2，客户端认证、刷新token
                .withClient("client_2")
                .secret(client_secret)
                .resourceIds("xmall-auth", "xmall-product")
                .scopes("server", "select")
                .authorizedGrantTypes("client_credentials", "refresh_token");
    }

}
