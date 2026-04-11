package com.example.userservice;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new User("Amit", "amit@test.com"));
                repository.save(new User("Priya", "priya@test.com"));
                repository.save(new User("Rahul", "rahul@test.com"));
            }
        };
    }
}