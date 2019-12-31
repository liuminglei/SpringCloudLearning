package com.luas.xmall.product.clients.clients;

import cn.hutool.core.map.MapUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SkuService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "infoFallback")
    public Object info(String skuId) {
        return this.restTemplate.getForObject("http://xmall-product/sku/{skuId}", Object.class, skuId);
    }

    private Object infoFallback(String skuId) {
        return MapUtil.builder()
                .put("skuId", "0000000")
                .put("name", "未知")
                .put("price", "99999")
                .put("port", "未知")
                .build();
    }

}
