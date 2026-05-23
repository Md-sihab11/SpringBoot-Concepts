package com.JsessionId.SessionTracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SessionTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionTrackingApplication.class, args);

        System.out.println("SessionTrackingApplication started");
        System.out.println("http://localhost:8080");
        System.out.println("http://localhost:8080/startsession");
        System.out.println("http://localhost:8080/profile");
	}

}
