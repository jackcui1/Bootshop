package com.minibookstore;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.minibookstore.service.StorageFileService;

@SpringBootApplication
public class SpringBootWebApplication {
	@Resource
	StorageFileService storageFileService;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
	
    public void run(String args)throws Exception{
            storageFileService.init();
    }
}
