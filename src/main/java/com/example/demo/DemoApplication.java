package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
		Locale.setDefault(Locale.forLanguageTag("es_CO"));
	}

}
