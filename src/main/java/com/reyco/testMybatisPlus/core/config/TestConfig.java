/**
 * 
 */
package com.reyco.testMybatisPlus.core.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/** 
* @author reyco
* @date 2021年7月31日  
*/
@Configuration
public class TestConfig {
	
	@Bean
	public ThreadPoolTaskExecutor asyncExcutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		int core = Runtime.getRuntime().availableProcessors();
		executor.setCorePoolSize(core);
		executor.setMaxPoolSize(core * 2 + 1);
		executor.setKeepAliveSeconds(3);
		executor.setThreadNamePrefix("listener-Thread-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// 设置拒绝策略
		executor.initialize();
		return executor;
	}
	
}
