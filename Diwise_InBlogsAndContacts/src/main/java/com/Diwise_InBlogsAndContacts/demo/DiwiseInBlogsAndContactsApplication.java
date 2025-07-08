package com.Diwise_InBlogsAndContacts.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.Diwise_InBlogsAndContacts")
@EntityScan("com.Diwise_InBlogsAndContacts.model")
@EnableJpaRepositories("com.Diwise_InBlogsAndContacts.Repository")
public class DiwiseInBlogsAndContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiwiseInBlogsAndContactsApplication.class, args);
	}

}
