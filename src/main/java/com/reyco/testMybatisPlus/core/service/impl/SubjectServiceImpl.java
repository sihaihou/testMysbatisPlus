package com.reyco.testMybatisPlus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reyco.testMybatisPlus.core.dao.SubjectDao;
import com.reyco.testMybatisPlus.core.domain.Subject;

@Service
public class SubjectServiceImpl{
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Transactional(propagation=Propagation.NESTED)
	public void insert(Subject subject) {
		subjectDao.insert(subject);
		throw new RuntimeException("SubjectServiceImpl.insert exception");
	}
}
