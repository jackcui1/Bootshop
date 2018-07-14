package com.bootshop.config;

import javax.annotation.Resource;

import com.bootshop.service.StorageFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;




@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootWebApplication {

	@Resource
	StorageFileService storageFileService;

	public static void main(String[] args) throws Exception {
				SpringApplication.run(
				SpringBootWebApplication.class, args);
	}

	// To excute storage file service for image storing.
	public void run(String args) throws Exception {
		storageFileService.init();
	}
}
