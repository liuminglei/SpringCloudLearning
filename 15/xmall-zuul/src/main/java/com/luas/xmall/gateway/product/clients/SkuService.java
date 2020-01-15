package com.luas.xmall.gateway.product.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "xmall-product", fallback = SkuFallbackService.class)
public interface SkuService {

    @RequestMapping(value = "/sku/{id}", method = RequestMethod.GET)
    Object info(@PathVariable("id") String skuId);

}
