package com.httpservlet.avigationandSessionmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class AvigationandSessionmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvigationandSessionmanagementApplication.class, args);

        System.out.println("\n🚀 App Started Successfully!");
        System.out.println("👉 Open: http://localhost:8080");
        System.out.println("👉 Login: http://localhost:8080/Loginproc");
        System.out.println("👉 Dashboard: http://localhost:8080/dashboard\n");
	}

}
