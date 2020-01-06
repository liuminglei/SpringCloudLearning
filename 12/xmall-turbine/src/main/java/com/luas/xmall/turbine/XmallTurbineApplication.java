package com.luas.xmall.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class XmallTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallTurbineApplication.class, args);
    }

}
