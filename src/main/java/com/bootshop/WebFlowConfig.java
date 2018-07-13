package com.bootshop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.config.AbstractFacesFlowConfiguration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

@Configuration
public class WebFlowConfig extends AbstractFacesFlowConfiguration {

	@Autowired
	private WebMvcConfig webMvcConfig;

	@Autowired
	private List<ViewResolver> viewResolvers;
	Logger logger = LoggerFactory.getLogger(WebFlowConfig.class);

	@Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(flowRegistry())
                .addFlowExecutionListener(new SecurityFlowExecutionListener(), "*")
                .build();
    }

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		logger.info("===============ajaxThymeleafViewResolver======================" );
		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
				.setBasePath("classpath*:/templates")
				.addFlowLocationPattern("/flows/activation-flow.xml").build();
	}

	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder()
				.setViewFactoryCreator(mvcViewFactoryCreator())
				.setDevelopmentMode(true).build();
	}

	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(viewResolvers);
		factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}
}
