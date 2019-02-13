package com.rogo.inv.iadprojf1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Iadprojf1Application {
	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(Iadprojf1Application.class, args);
	}

}

