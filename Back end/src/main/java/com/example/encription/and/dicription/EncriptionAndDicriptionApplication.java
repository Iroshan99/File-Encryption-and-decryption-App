package com.example.encription.and.dicription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EncriptionAndDicriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncriptionAndDicriptionApplication.class, args);
	}

}
