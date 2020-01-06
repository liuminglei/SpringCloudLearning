package com.luas.xmall.product.clients.controller;

import com.luas.xmall.product.clients.clients.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @RequestMapping("/{skuId}")
    public Object info(@PathVariable String skuId) {
        return this.skuService.info(skuId);
    }

}
