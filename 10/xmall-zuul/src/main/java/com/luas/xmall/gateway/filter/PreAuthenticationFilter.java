package com.luas.xmall.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class PreAuthenticationFilter extends ZuulFilter {

    public static final String TOKEN_ENDPOINT = "/gateway/auth/oauth/token";

    public static final String TOKEN_KEY_ENDPOINT = "/gateway/auth/oauth/token_key";

    public static final String CHECK_TOKEN_ENDPOINT = "/gateway/auth/oauth/check_token";

    private Logger logger = LoggerFactory.getLogger(getClass());

    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        String requestURI = request.getRequestURI();

        return !(requestURI.equals(TOKEN_ENDPOINT) || requestURI.equals(TOKEN_KEY_ENDPOINT) || requestURI.equals(CHECK_TOKEN_ENDPOINT));
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("uri {}", request.getRequestURI());
        }

        Authentication authentication = this.tokenExtractor.extract(request);

        if (authentication == null || authentication.getPrincipal() == null) {
            //不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("Full authentication is required to access this resource");
            requestContext.setResponseStatusCode(401);
            return null;
        }

        String accessToken = (String) authentication.getPrincipal();

        this.logger.info("token {}", accessToken);

        // todo 解析token，调用权限支撑平台，获取权限信息，组织到用户信息中传递给下游微服务

        return null;
    }
}
