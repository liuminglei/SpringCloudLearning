package com.luas.xmall.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

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
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(new RedisTokenStore(redisConnectionFactory));
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置方法1，只需配置DataSource即可，其它交给框架自动配置
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);

        // 配置方法2，构建ClientDetailsServiceBuilder
        //clients.configure(jdbcClientDetailsServiceBuilder());

        // 配置方法3，使用ClientDetailsServiceBuilder构建ClientDetailsService
        //clients.withClientDetails(jdbcClientDetailsService());

        // 配置方法4，自定义ClientDetailsService
        //clients.withClientDetails(myClientDetailsService());
    }

    private JdbcClientDetailsServiceBuilder jdbcClientDetailsServiceBuilder() {
        return new JdbcClientDetailsServiceBuilder().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    private ClientDetailsService jdbcClientDetailsService() throws Exception {
        return new JdbcClientDetailsServiceBuilder().dataSource(dataSource).passwordEncoder(passwordEncoder).build();

        // 直接构建JdbcClientDetailsService对象
//        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
//        return jdbcClientDetailsService;
    }

    public ClientDetailsService myClientDetailsService() throws Exception {
        return new ClientDetailsService() {
            @Override
            public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
                // TODO 调用dao、service查询自定义数据结构、库，组织对应的ClientDetails
                return null;
            }
        };
    }
}
