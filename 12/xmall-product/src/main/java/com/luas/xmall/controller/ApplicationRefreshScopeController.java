package com.luas.xmall.controller;

import cn.hutool.core.map.MapUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application/refreshScope")
@RefreshScope
public class ApplicationRefreshScopeController {

    @Value("${system.application.name}")
    private String name;

    @Value("${system.application.version}")
    private String version;

    @RequestMapping
    public Object info() {
        return MapUtil.builder().put("name", name).put("version", version).build();
    }

}
