package com.reyco.testMybatisPlus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reyco.testMybatisPlus.core.dao.StudentDao;
import com.reyco.testMybatisPlus.core.domain.Student;

@Service
public class StudentServiceImpl{
	
	@Autowired
	private StudentDao studentDao;
	
	@Transactional(propagation=Propagation.NESTED)
	public void insert(Student student) {
		studentDao.insert(student);
		//throw new RuntimeException("SubjectServiceImpl.insert exception");
	}
}
