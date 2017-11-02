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
		//ApplicationContext ac = 
				SpringApplication.run(
				SpringBootWebApplication.class, args);

//		System.out.println("ApplicationName:" + ac.getApplicationName());
//		System.out
//				.println("BeanDefinitionCount:" + ac.getBeanDefinitionCount());
//		String[] beans = ac.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String s : beans) {
//			System.out.println(s);
//		}
	}

	// To excute storage file service for image storing.
	public void run(String args) throws Exception {
		storageFileService.init();
	}
}
