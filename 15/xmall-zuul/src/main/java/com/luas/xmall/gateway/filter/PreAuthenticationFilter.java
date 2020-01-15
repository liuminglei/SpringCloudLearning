package com.luas.xmall.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class PreAuthenticationFilter extends ZuulFilter {

    private static final int DEFAULT_FILTER_ORDER = 0;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Set<AntPathRequestMatcher> ignoreAntPathRequestMatchers = new HashSet<>();

    private int order = DEFAULT_FILTER_ORDER;

    private ResourceServerTokenServices tokenServices;

    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return order;
    }

    @Override
    public boolean shouldFilter() {
        return !isIgnoreRequest();
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        this.logger.debug("processing uri {}", request.getRequestURI());

        Authentication authentication = this.tokenExtractor.extract(request);

        if (authentication == null || authentication.getPrincipal() == null) {
            //不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("Full authentication is required to access this resource");
            requestContext.setResponseStatusCode(401);
            return null;
        }

        String accessToken = (String) authentication.getPrincipal();

        this.logger.debug("access token is {}", accessToken);

        /* 解析token，将user信息放入request，传递给下游微服务 */

        // 解析token
        OAuth2Authentication oAuth2Authentication = this.tokenServices.loadAuthentication(accessToken);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("user is {}", oAuth2Authentication.getName());
        }

        // 根据用户名调用feign客户端或rest方式获取user信息

        // 将user信息放入request，传递给下游微服务
        // requestContext.addZuulRequestHeader("", "");
        return null;
    }

    private boolean isIgnoreRequest() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        for (AntPathRequestMatcher matcher : ignoreAntPathRequestMatchers) {
            if (matcher.matches(request)) {
                return true;
            }
        }

        return false;
    }

    private Set<String> obtainAuthorities(OAuth2Authentication authentication) {
        if (CollectionUtils.isEmpty(authentication.getAuthorities())) {
            return null;
        }

        return authentication.getAuthorities().stream().map(authority -> authority.toString()).collect(Collectors.toSet());
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
