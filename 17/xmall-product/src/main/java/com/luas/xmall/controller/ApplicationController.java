package com.luas.xmall.controller;

import com.luas.xmall.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationProperties applicationProperties;

    @RequestMapping
    public ApplicationProperties info() {
        return applicationProperties;
    }

}
