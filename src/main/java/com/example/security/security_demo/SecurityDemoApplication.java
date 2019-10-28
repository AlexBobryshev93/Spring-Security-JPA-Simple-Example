package com.example.security.security_demo;

import com.example.security.security_demo.model.User;
import com.example.security.security_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityDemoApplication {
    @Autowired
    PasswordEncoder encoder;

    @Bean
    public CommandLineRunner dataLoader(UserRepo userRepo) {
        return new CommandLineRunner() { // should be remade with data.sql
            @Override
            public void run(String... args) throws Exception {
                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("pass"));
                userRepo.save(user);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }


}
