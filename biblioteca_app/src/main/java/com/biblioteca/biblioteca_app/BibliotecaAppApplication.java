package com.biblioteca.biblioteca_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.biblioteca.biblioteca_app.controllers", "com.biblioteca.biblioteca_app.services"} )
public class BibliotecaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaAppApplication.class, args);
	}

}
