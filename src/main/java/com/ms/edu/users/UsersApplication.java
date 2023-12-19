package com.ms.edu.users;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	CommandLineRunner repositoryCheck(UserRepository userRepository) {
		return (args) -> {
			User user = new User("fName1", "sName1", "mName1", "email1@email.com");
			userRepository.save(user);

			user = new User("fName2", "sName2", "mName2", "email2@email.com");
			userRepository.save(user);

			for (User repoUser: userRepository.findAll()) {
				System.out.println("user # " + repoUser.toString());
			}
		};
	}
}
