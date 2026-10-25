package com.example.Crud_using_Postgress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudUsingPostgressApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsingPostgressApplication.class, args);
	}

}
