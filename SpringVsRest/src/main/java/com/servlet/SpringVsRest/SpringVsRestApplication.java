package com.servlet.SpringVsRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringVsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVsRestApplication.class, args);
	}

}
