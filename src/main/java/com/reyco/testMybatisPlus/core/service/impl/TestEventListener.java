/**
 * 
 */
package com.reyco.testMybatisPlus.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.reyco.testMybatisPlus.core.event.ReycoListener;
import com.reyco.testMybatisPlus.core.event.SimpleReycoEvent;

/** 
* @author reyco
* @date 2021年7月30日  
*/
@Component
public class TestEventListener implements ReycoListener<SimpleReycoEvent>{
	
	protected static Logger logger = LoggerFactory.getLogger(TestEventListener.class);
	
	@Override
	public void onReycoEvent(SimpleReycoEvent event) {
		Object source = event.getSource();
		String json = JSON.toJSONString(source);
		logger.debug("TestEventListener----logDefinition:"+json);
	}


}
