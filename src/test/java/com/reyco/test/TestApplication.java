package com.reyco.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.reyco.testMybatisPlus.core.TestMybatisApplication;
import com.reyco.testMybatisPlus.core.service.impl.TestTransactionServiceImpl;

public class TestApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestMybatisApplication.class);
		TestTransactionServiceImpl bean = context.getBean(TestTransactionServiceImpl.class);
		bean.test1();
	}
	
	
	
}
