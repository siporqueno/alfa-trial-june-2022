package com.niko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AlfaTrialJune2022Application {

    public static void main(String[] args) {
        SpringApplication.run(AlfaTrialJune2022Application.class, args);
    }

}
