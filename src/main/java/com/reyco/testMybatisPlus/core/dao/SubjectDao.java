package com.reyco.testMybatisPlus.core.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.reyco.testMybatisPlus.core.domain.Subject;

public interface SubjectDao {
	
	@Insert({"insert into subject(`name`,`remark`) values(#{name}, #{remark})" })
	Integer insert(Subject subject);
	
	/**
	 * 查询表注释
	 */
	@Select({"select `column_name` as `columnName`,`column_comment` as `columnComment` from `information_schema`.`columns` where `table_schema`=#{databaseName} and `table_name`=#{tableName}"})
	List<Map<String,Object>>  getColumnByDatabase(@Param(value="databaseName")String databaseName,@Param(value = "tableName")String tableName);
	
	
}
