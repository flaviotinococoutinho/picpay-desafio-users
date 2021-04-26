package com.picpay.testebackend;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication é equivalente ao @Configuration, @EnableAutoConfiguration, e @ComponentScan juntos
@SpringBootApplication
@EnableBatchProcessing
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
