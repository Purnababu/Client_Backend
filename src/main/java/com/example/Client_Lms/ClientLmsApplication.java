package com.example.Client_Lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class ClientLmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientLmsApplication.class, args);
	}

}
