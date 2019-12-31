package com.luas.xmall.product.clients.clients;

import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Component;

@Component
public class SkuFallbackService implements SkuService {

    @Override
    public Object info(String skuId) {
        return MapUtil.builder()
                .put("skuId", "0000000")
                .put("name", "未知")
                .put("price", "99999")
                .put("port", "未知")
                .build();
    }
}
