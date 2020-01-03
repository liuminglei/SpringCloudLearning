package com.luas.xmall.gateway.configuration;

import com.luas.xmall.gateway.filter.PreAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/application").permitAll()
                // 放开token授权、token key获取、token验证端点
                .antMatchers(PreAuthenticationFilter.TOKEN_ENDPOINT).permitAll()
                .antMatchers(PreAuthenticationFilter.TOKEN_KEY_ENDPOINT).permitAll()
                .antMatchers(PreAuthenticationFilter.CHECK_TOKEN_ENDPOINT).permitAll()
                .anyRequest()
                .authenticated();
    }
}
