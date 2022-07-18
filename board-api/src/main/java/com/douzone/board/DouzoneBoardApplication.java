package com.douzone.board;

import com.douzone.board.entity.Role;
import com.douzone.board.entity.User;
import com.douzone.board.service.UserService;
import java.util.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DouzoneBoardApplication {
	public static void main(String[] args) {


		SpringApplication.run(DouzoneBoardApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			// role table init
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			// user table init
//			userService.saveUser(new User(null, "user", "jonghyunLim", "1",1));

			// role_user table init
//			userService.addRoleToUser("user", "ROLE_USER");
		};
	}
}