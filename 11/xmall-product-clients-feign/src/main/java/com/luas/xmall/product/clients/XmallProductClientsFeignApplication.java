package com.luas.xmall.product.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients("com.luas.xmall.product.clients.clients")
@SpringBootApplication
public class XmallProductClientsFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallProductClientsFeignApplication.class, args);
    }

}
