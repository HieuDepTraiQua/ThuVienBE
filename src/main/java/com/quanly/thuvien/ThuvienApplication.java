package com.quanly.thuvien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ThuvienApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ThuvienApplication.class, args);
	}

}
