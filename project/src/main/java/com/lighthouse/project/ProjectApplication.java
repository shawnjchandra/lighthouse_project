package com.lighthouse.project;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

		Connection conn = null;
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
