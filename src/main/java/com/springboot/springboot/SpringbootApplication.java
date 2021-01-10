package com.springboot.springboot;

import com.springboot.springboot.controller.ProdutoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.springboot.springboot.*")
@EntityScan("com.springboot.springboot.model")
@EnableJpaRepositories(basePackages = {"com.springboot.springboot.Repository"})
@EnableTransactionManagement
@ComponentScan(basePackageClasses = ProdutoController.class)
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
