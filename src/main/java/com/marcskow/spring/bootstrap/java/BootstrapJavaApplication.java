package com.marcskow.spring.bootstrap.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BootstrapJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootstrapJavaApplication.class, args);
	}

}
