package com.harmonious;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.harmonious.foundear")
public class FoundearApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoundearApplication.class, args);
    }
}