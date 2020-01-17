package com.luas.xmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrixDashboard
@EnableFeignClients
@EnableCircuitBreaker
@EnableZuulProxy
@SpringBootApplication
public class XmallZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallZuulApplication.class, args);
    }

}
