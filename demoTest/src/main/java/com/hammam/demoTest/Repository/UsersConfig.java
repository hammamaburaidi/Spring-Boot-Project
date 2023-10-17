package com.hammam.demoTest.Repository;

import com.hammam.demoTest.Model.Users;
import com.hammam.demoTest.Repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UsersConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UsersRepository usersRepository) {
        return args -> {
            Users hammam = new Users(
                    1L,
                    "Hammam",
                    "Aburaidi",
                    "hammamaburaidi@gmail.com",
                    757575758L
            );

            Users esref = new Users(
                    2L,
                    "John",
                    "Kerry",
                    "JohnKerry@gmail.com",
                    999111234L
            );

            usersRepository.saveAll(
                    List.of(hammam,esref)
            );
        };
    }
}
