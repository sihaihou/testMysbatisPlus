package com.reyco.testMybatisPlus.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.core.binlog.model.LogDefinition;
import com.reyco.core.binlog.processor.AbstractLogPostProcessor;
import com.reyco.testMybatisPlus.core.dao.SubjectDao;

/** 
 * @author  reyco
 * @date    2021.09.22
 * @version v1.0.1 
 */
@Service
public class logPostProcessorImpl extends AbstractLogPostProcessor{
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public void postProcessAftereInitialization(List<LogDefinition> logDefinitions, Object... ob) {
		if(logDefinitions != null && !logDefinitions.isEmpty()) {
			LogDefinition logDefinition = logDefinitions.get(0);
			List<Map<String,Object>> columnByDatabase = subjectDao.getColumnByDatabase(logDefinition.getDatabaseName(),logDefinition.getTableName());
			for (LogDefinition LogDefinition : logDefinitions) {
				setColumnDesc(LogDefinition, columnByDatabase);
			}
		}
	}
	/**
	 * 
	 * @param logDefinition
	 * @param columnByDatabase
	 */
	private void setColumnDesc(LogDefinition logDefinition,List<Map<String,Object>> columnByDatabase ) {
		for (Map<String,Object> map : columnByDatabase) {
			String columnName = map.get("columnName").toString();
			String logDefinitionColumn = logDefinition.getColumn();
			if(columnName.equalsIgnoreCase(logDefinitionColumn)) {
				String columnComment = null;
				Object columnCommentObj = map.get("columnComment");
				if(columnCommentObj!=null) {
					columnComment = columnCommentObj.toString();
				}
				logDefinition.setColumnDesc(columnComment);
				break;
			}
		}
	}
}
