package com.luas.xmall.gateway.filter;

import com.luas.xmall.gateway.product.clients.SkuService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class SkuFeignFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SkuService skuService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Object skuInfo = this.skuService.info("" + new Random().nextInt(4));

        this.logger.info("sku info is {}", skuInfo);

        return null;
    }
}
