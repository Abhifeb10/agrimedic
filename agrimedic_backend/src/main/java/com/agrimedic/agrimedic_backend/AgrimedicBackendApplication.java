package com.agrimedic.agrimedic_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.agrimedic.agrimedic_backend.entity")
public class AgrimedicBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgrimedicBackendApplication.class, args);
    }
}



