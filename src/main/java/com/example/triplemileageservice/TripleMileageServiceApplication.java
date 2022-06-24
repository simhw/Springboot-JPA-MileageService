package com.example.triplemileageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TripleMileageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripleMileageServiceApplication.class, args);
    }

}
