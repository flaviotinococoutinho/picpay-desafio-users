package com.picpay.testebackend;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication é equivalente ao @Configuration, @EnableAutoConfiguration, e @ComponentScan juntos
@SpringBootApplication
@EnableElasticsearchRepositories("com.picpay.testebackend.repository.es")
@EnableScheduling
public class PicpayTesteBackendApplication extends SpringBootServletInitializer {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PicpayTesteBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PicpayTesteBackendApplication.class, args);
	}

}
