package com.reyco.testMybatisPlus.core.dao;

import org.apache.ibatis.annotations.Insert;

import com.reyco.testMybatisPlus.core.domain.Student;

public interface StudentDao {
	
	@Insert({"insert into student(`name`,`remark`) values(#{name},#{remark})" })
	Integer insert(Student student);
}
