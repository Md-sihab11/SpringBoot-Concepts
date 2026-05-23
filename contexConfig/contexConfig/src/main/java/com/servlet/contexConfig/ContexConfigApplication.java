package com.servlet.contexConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ContexConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContexConfigApplication.class, args);
	}

}
