package com.neosoft.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.neosoft.webflux.repo.EmployeeRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EmployeeRepository.class)
public class SBootEmployeeWebFluxAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBootEmployeeWebFluxAppApplication.class, args);
	}

}
