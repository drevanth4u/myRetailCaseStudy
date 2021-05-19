package com.target.retail.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Revanth
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	/**
	 * @return docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/products/*"))
				.apis(RequestHandlerSelectors.basePackage("com.target.retail"))		
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"MyRetail App API",
				"To get Poduct information and update Prodcut price",
				"1.0",
				"For Authorized users only",
				new springfox.documentation.service.Contact("Revanth Deevela", "http://target.com.retail/product", "deevelar4u@gmai.com"),
				"API License",
				"http://target.com.retail/product");
	}

}
