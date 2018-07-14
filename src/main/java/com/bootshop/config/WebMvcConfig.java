//package com.bootshop.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
//import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
//
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;
//import org.thymeleaf.spring4.view.FlowAjaxThymeleafView;
//
//@EnableWebMvc
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//
////
////    @Autowired
////    private SpringTemplateEngine springTemplateEngine;
//
//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//		"classpath:/META-INF/resources/", "classpath:/resources/",
//		"classpath:/static/", "classpath:/public/"
//	};
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    	if (!registry.hasMappingForPattern("/webjars/**")) {
//    		registry.addResourceHandler("/webjars/**").addResourceLocations(
//    				"classpath:/META-INF/resources/webjars/");
//    	}
//    	if (!registry.hasMappingForPattern("/**")) {
//    		registry.addResourceHandler("/**").addResourceLocations(
//    				CLASSPATH_RESOURCE_LOCATIONS);
//    	}
//    }
//
////    @Bean
////    public AjaxThymeleafViewResolver ajaxThymeleafViewResolver() {
////        AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
////        viewResolver.setViewClass(FlowAjaxThymeleafView.class);
////        viewResolver.setTemplateEngine(springTemplateEngine);
////        viewResolver.setOrder(0);
////        return viewResolver;
////    }
//}
