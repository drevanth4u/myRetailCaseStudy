package com.target.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author Revanth
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class MyRetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}

}
