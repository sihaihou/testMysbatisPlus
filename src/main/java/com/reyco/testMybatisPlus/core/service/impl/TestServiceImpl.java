package com.reyco.testMybatisPlus.core.service.impl;

import org.springframework.stereotype.Service;

import com.reyco.testMybatisPlus.core.dao.TestDao;
import com.reyco.testMybatisPlus.core.domain.User;
import com.reyco.testMybatisPlus.core.service.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<TestDao, User> implements TestService {
	
}
