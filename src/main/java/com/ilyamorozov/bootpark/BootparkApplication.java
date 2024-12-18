package com.ilyamorozov.bootpark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootparkApplication {

	@Value("${PORT:8001}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(BootparkApplication.class, args);
	}

}
