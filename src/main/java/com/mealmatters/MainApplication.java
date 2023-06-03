package com.mealmatters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// hit this url to understand the api flow after running



//-----  http://localhost:8180/api/swagger-ui.html


@EnableMongoRepositories
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(MainApplication.class, args);

	}

}
