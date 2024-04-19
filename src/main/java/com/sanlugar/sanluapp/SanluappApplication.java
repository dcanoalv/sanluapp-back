package com.sanlugar.sanluapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanluappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanluappApplication.class, args);
		System.out.println("Levantado en el puerto 8080");
	}

}
