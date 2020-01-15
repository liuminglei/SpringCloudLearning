package com.luas.xmall.gateway.configuration;

import com.luas.xmall.gateway.filter.PreAuthenticationFilter;
import com.luas.xmall.gateway.filter.PreAuthenticationFilterFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.CollectionUtils;

@Configuration
@EnableConfigurationProperties(SysProperties.class)
public class SysConfiguration {

    private SysProperties sysProperties;

    private ZuulProperties zuulProperties;

    public SysConfiguration(SysProperties sysProperties, ZuulProperties zuulProperties) {
        this.sysProperties = sysProperties;
        this.zuulProperties = zuulProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "sys.zuul", name = "pre-authentication-filter.enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean(PreAuthenticationFilter.class)
    public PreAuthenticationFilterFactoryBean preAuthenticationFilter(@Qualifier("userInfoTokenServices") ResourceServerTokenServices tokenServices) {
        SysProperties.PreAuthenticationFilter preAuthFilter = this.sysProperties.getPreAuthenticationFilter();

        PreAuthenticationFilterFactoryBean preAuthenticationFilterFactoryBean = new PreAuthenticationFilterFactoryBean();
        preAuthenticationFilterFactoryBean.setZuulPrefix(this.zuulProperties.getPrefix());
        preAuthenticationFilterFactoryBean.setTokenServices(tokenServices);
        preAuthenticationFilterFactoryBean.setOrder(preAuthFilter.getOrder());

        if (!CollectionUtils.isEmpty(preAuthFilter.getIgnoreAntPatterns())) {
            preAuthenticationFilterFactoryBean.ignoreAntPattern(preAuthFilter.getIgnoreAntPatterns());
        }

        return preAuthenticationFilterFactoryBean;
    }

}
