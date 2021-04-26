package com.picpay.testebackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConfigurationProperties("api.picpay")
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	private String host;
	private String version;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Bean
	public Docket devApi() {
		return new Docket(DocumentationType.SWAGGER_2)//.host(host)// .host(host)
				.select().apis(RequestHandlerSelectors.basePackage("com.picpay.testebackend")).build()
				.apiInfo(metaData());
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/swagger-ui.html");
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("DESAFIO PICPAY API").description("Api Simples de Busca de Usuários")
				.version(version).license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"").build();
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).build();
	}

}
