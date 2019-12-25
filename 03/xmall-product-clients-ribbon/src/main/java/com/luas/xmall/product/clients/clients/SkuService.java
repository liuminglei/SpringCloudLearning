package com.luas.xmall.product.clients.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SkuService {

    @Autowired
    private RestTemplate restTemplate;

    public Object info(String skuId) {
        return this.restTemplate.getForObject("http://xmall-product/sku/{skuId}", Object.class, skuId);
    }

}
