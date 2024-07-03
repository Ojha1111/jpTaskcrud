package com.javaupgradation.javacrudoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration.class
})
public class JavacrudoperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavacrudoperationApplication.class, args);
	}

}
