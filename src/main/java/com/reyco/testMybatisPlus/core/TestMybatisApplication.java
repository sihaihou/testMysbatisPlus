package com.reyco.testMybatisPlus.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.reyco.core.binlog.annotation.EnableBinlog;

@EnableAsync
@EnableBinlog
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.reyco.testMybatisPlus.core.dao")
public class TestMybatisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TestMybatisApplication.class, args);
	}
	
}
