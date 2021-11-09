package com.reyco.testMybatisPlus.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.core.binlog.model.LogDefinition;
import com.reyco.core.binlog.service.OperationLogService;
import com.reyco.testMybatisPlus.core.event.ReycoEventPublisher;

@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService{
	
	protected static Logger logger = LoggerFactory.getLogger(OperationLogServiceImpl.class);
	
	@Autowired
	private ReycoEventPublisher eventPublisher;
	
	@Override
	public void save(List<LogDefinition> logDefinitions) throws Exception {
		for (LogDefinition logDefinition : logDefinitions) {
			logger.debug("发布事件："+logDefinition);
			eventPublisher.publishEvent(logDefinition);
		}
	}

}
