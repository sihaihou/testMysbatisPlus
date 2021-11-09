/**
 * 
 */
package com.reyco.testMybatisPlus.core.environment;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * @author reyco
 * @date 2021年8月16日
 */
public class RemoteEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
	
	public static final String PROPERTY_SOURCE_NAME = "reyco";

	public static final String PROPERTY_SOURCE_FILE = "/META-INF/reyco.properties";
	
	//public static final String PROPERTY_SOURCE_FILE = "file:C:/Users/reyco/Desktop/reyco.properties";
	
	//public static final String PROPERTY_SOURCE_FILE = "url:https://github.com/sihaihou/spring-cloud-config/blob/master/config-repo/application-dev.properties";
	
	private static final String FILE_ENCODING = "UTF-8";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		CompositePropertySource propertySource = new CompositePropertySource(PROPERTY_SOURCE_NAME);
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(
				application.getClassLoader());
		try {
			Resource[] resources = resourcePatternResolver.getResources(PROPERTY_SOURCE_FILE);
			for (Resource resource : resources) {
				if (resource.exists()) {
					String internalName = String.valueOf(resource.getURL());
					propertySource.addPropertySource(new ResourcePropertySource(internalName, new EncodedResource(resource, FILE_ENCODING)));
				}
			}
			environment.getPropertySources().addLast(propertySource);
		} catch (IOException e) {
			System.err.println("加载配置文件失败："+PROPERTY_SOURCE_FILE);
			e.printStackTrace();
		}
	}

	@Override
	public int getOrder() {
		return LOWEST_PRECEDENCE;
	}
}
