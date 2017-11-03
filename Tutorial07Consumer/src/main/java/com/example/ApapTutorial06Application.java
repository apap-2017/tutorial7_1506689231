package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApapTutorial06Application
{

    public static void main (String[] args)
    {
        SpringApplication.run (ApapTutorial06Application.class, args);
    }
    
    // Referensi : https://stackoverflow.com/questions/36151421/could-not-autowire-fieldresttemplate-in-spring-boot-application
    // Method untuk mencegah error creating bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
