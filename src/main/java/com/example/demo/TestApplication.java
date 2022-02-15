package com.example.demo;



import com.example.demo.configure.configure;
import com.example.demo.entity.User;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		System.out.println("\n Look \n\n");
		ApplicationContext appcon= new AnnotationConfigApplicationContext(configure.class);
        User user = (User) appcon.getBean("user");
		System.out.println(user);


	}

}

