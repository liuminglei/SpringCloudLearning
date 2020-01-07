package com.luas.xmall.order.controller;

import cn.hutool.core.map.MapUtil;
import com.luas.xmall.order.clients.product.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private SkuService skuService;

    @RequestMapping("/{id}")
    public Object info(@PathVariable("id") String id) {
        Object skuInfo = this.skuService.info("" + new Random().nextInt(4));

        return MapUtil.builder()
                .put("id", id)
                .put("amount", "9988")
                .put("timestmap", LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_DATE_TIME))
                .put("sku", skuInfo)
                .build();
    }

}
