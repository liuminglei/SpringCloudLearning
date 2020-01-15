package com.luas.xmall.gateway.configuration;

import com.luas.xmall.gateway.oauth2.endpoint.AuthorizationServerEndpoints;

import org.apache.commons.lang3.StringUtils;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private String prefixAntPattern;

    public ResourceServerConfiguration(ZuulProperties zuulProperties) {
        this.prefixAntPattern = StringUtils.isBlank(zuulProperties.getPrefix()) ? "/*" : zuulProperties.getPrefix() + "/*";
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(this.prefixAntPattern + AuthorizationServerEndpoints.TOKEN_ENDPOINT).permitAll()
                .antMatchers(this.prefixAntPattern + AuthorizationServerEndpoints.TOKEN_KEY_ENDPOINT).permitAll()
                .antMatchers(this.prefixAntPattern + AuthorizationServerEndpoints.CHECK_TOKEN_ENDPOINT).permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/proxy.stream").permitAll()
                .antMatchers("/hystrix.stream").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/hystrix/**").permitAll()
                .anyRequest().authenticated()
        ;
    }
}
