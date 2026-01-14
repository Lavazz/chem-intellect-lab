package com.chem.lab.reagentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ReagentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReagentServiceApplication.class, args);
    }

}
