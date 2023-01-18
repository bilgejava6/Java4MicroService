package com.muhammet;

import com.muhammet.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthMicroserviceApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthMicroserviceApplication.class);
    }
}