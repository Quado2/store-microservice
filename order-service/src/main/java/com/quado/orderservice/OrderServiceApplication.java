package com.quado.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient //This is not neccessary anymore but I want to check if this service will behave differently from others without the annotation.
public class OrderServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
