package com.bootshop;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.bootshop.service.StorageFileService;

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
