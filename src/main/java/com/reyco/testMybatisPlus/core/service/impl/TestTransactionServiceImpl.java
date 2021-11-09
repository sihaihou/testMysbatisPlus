package com.reyco.testMybatisPlus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reyco.testMybatisPlus.core.domain.Student;
import com.reyco.testMybatisPlus.core.domain.Subject;

@Service
public class TestTransactionServiceImpl {

	@Autowired
	private SubjectServiceImpl subjectServiceImpl;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Transactional/*(propagation=Propagation.REQUIRES_NEW)*/
	public void test1() {
		try {
			Student student = new Student();
			student.setName("zs1");
			student.setRemark("test1");
			studentServiceImpl.insert(student);
			
			Subject subject = new Subject();
			subject.setName("zs1");
			subject.setRemark("test1");
			subjectServiceImpl.insert(subject);
		} catch (Exception e) {
			
		}
	}
	
}
