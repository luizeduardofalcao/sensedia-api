package com.example.sensedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.example.sensedia",
    "br.com.aula"
})
public class SensediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensediaApplication.class, args);
    }
}