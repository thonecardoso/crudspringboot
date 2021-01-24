package com.springboot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootApplication.class, args);

		/*
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("123");
		$2a$10$WULDfqtHFa8pJg5i3RzxOOSef.LgUcoagoHqka/hLh.kAQPxKSnEu
		System.out.println(result);

		 */
	}

}
