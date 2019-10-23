package com.example.security.security_demo;

import com.example.security.security_demo.model.User;
import com.example.security.security_demo.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserRepo userRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user = new User();
                user.setUsername("user");
                user.setPassword("pass");
                userRepo.save(user);
            }
        };
    }
}
