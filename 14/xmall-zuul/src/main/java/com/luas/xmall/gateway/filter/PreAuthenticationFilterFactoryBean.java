package com.luas.xmall.gateway.filter;

import com.luas.xmall.gateway.oauth2.endpoint.AuthorizationServerEndpoints;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PreAuthenticationFilterFactoryBean implements FactoryBean<PreAuthenticationFilter>, InitializingBean {

    private static final int DEFAULT_FILTER_ORDER = 0;

    private String zuulPrefix;

    private Set<String> ignoreAntPatterns = new HashSet<>();

    private transient Set<AntPathRequestMatcher> ignoreAntPathRequestMatchers = new HashSet<>();

    private int order = DEFAULT_FILTER_ORDER;

    private ResourceServerTokenServices tokenServices;

    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Nullable
    private PreAuthenticationFilter preAuthenticationFilter;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.tokenServices, "ResourceServerTokenServices bean must not be null.");
        Assert.notNull(this.tokenExtractor, "TokenExtractor bean must not be null.");

        buildIgnorePatterns();

        build();
    }

    public void build() {
        PreAuthenticationFilter preAuthenticationFilter = new PreAuthenticationFilter();
        preAuthenticationFilter.setOrder(this.order);
        preAuthenticationFilter.setTokenServices(this.tokenServices);
        preAuthenticationFilter.setTokenExtractor(this.tokenExtractor);
        preAuthenticationFilter.setIgnoreAntPathRequestMatchers(this.ignoreAntPathRequestMatchers);

        this.preAuthenticationFilter = preAuthenticationFilter;
    }

    private void buildIgnorePatterns() {
        for (String pattern : this.ignoreAntPatterns) {
            this.ignoreAntPathRequestMatchers.add(new AntPathRequestMatcher(pattern));
        }

        // 添加系统默认不拦截路径
        String prefixAntPattern = StringUtils.isBlank(this.zuulPrefix) ? "/*" : this.zuulPrefix + "/*";

        this.ignoreAntPathRequestMatchers.add(new AntPathRequestMatcher(prefixAntPattern + AuthorizationServerEndpoints.TOKEN_ENDPOINT));
        this.ignoreAntPathRequestMatchers.add(new AntPathRequestMatcher(prefixAntPattern + AuthorizationServerEndpoints.CHECK_TOKEN_ENDPOINT));
        this.ignoreAntPathRequestMatchers.add(new AntPathRequestMatcher(prefixAntPattern + AuthorizationServerEndpoints.TOKEN_KEY_ENDPOINT));
    }

    @Override
    public PreAuthenticationFilter getObject() throws Exception {
        return this.preAuthenticationFilter;
    }

    @Override
    public Class<?> getObjectType() {
        return PreAuthenticationFilter.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void ignoreAntPattern(String[] patterns) {
        Assert.notEmpty(patterns, "patterns must be not null.");
        this.ignoreAntPatterns.addAll(Arrays.asList(patterns));
    }

    public void ignoreAntPattern(Set<String> patterns) {
        Assert.notEmpty(patterns, "patterns must be not null.");
        this.ignoreAntPatterns.addAll(patterns);
    }

    public void ignoreAntPattern(String pattern) {
        Assert.hasLength(pattern, "pattern must be not null.");
        this.ignoreAntPatterns.add(pattern);
    }

    public String getZuulPrefix() {
        return zuulPrefix;
    }

    public void setZuulPrefix(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix;
    }

    public Set<String> getIgnoreAntPatterns() {
        return ignoreAntPatterns;
    }

    public void setIgnoreAntPatterns(Set<String> ignoreAntPatterns) {
        this.ignoreAntPatterns = ignoreAntPatterns;
    }

    public Set<AntPathRequestMatcher> getIgnoreAntPathRequestMatchers() {
        return ignoreAntPathRequestMatchers;
    }

    public void setIgnoreAntPathRequestMatchers(Set<AntPathRequestMatcher> ignoreAntPathRequestMatchers) {
        this.ignoreAntPathRequestMatchers = ignoreAntPathRequestMatchers;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ResourceServerTokenServices getTokenServices() {
        return tokenServices;
    }

    public void setTokenServices(ResourceServerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    public TokenExtractor getTokenExtractor() {
        return tokenExtractor;
    }

    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

}
