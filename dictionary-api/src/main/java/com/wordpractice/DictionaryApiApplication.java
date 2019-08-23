package com.wordpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan
public class DictionaryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryApiApplication.class, args);
	}

}
