package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class DemoApplication {

	public static void main(String[] args) {
		// System.out.println("\n Look \n\n");
		SpringApplication.run(DemoApplication.class, args);
		// ConfigurableApplicationContext run=
		// ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
		// System.out.println(run.getBean("user",User.class));

	}

}
