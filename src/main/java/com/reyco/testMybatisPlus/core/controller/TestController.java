package com.reyco.testMybatisPlus.core.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.testMybatisPlus.core.domain.User;
import com.reyco.testMybatisPlus.core.service.TestService;
import com.reyco.testMybatisPlus.core.service.impl.TestTransactionServiceImpl;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Value("${reyco.name}")
	private String name;

	@Autowired
	private TestService testService;
	@Autowired
	private TestTransactionServiceImpl testTransactionServiceImpl;
	@Autowired
	MessageSource messageSource;
	
	@GetMapping(value="test")
	public Object test(HttpServletRequest request,User user) throws UnsupportedEncodingException {
		List<User> list = testService.query().eq(StringUtils.isNotBlank(user.getPassword()),User::getPassword, user.getPassword())
				.eq(StringUtils.isNotBlank(user.getName()),User::getName, user.getName()).list();
		Locale locale = LocaleContextHolder.getLocale();
		//方法1.推荐使用
		String message = messageSource.getMessage("login.username",new Object[]{},locale);
		//方法2.这种方式不建议
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/login", locale );
		String message2 = new String(bundle.getString("login.username").getBytes("ISO-8859-1"), "UTF-8");  
		list.get(0).setName(message);
		return list;
	}
	
	@GetMapping(value="testTransaction")
	public Object testTransaction() {
		testTransactionServiceImpl.test1();
		return "Ok";
	}
	
	@GetMapping(value="test1")
	public Object test1(){
		return new User(name);
	}
}	
