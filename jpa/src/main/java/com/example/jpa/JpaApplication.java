package com.example.jpa;

import com.example.jpa.repo.AuthorRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(

){
return args ->{
	System.out.println("Hello server is running ⚙");
};
}
}
