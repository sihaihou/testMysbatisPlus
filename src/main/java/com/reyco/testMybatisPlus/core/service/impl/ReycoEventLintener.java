/**
 * 
 */
package com.reyco.testMybatisPlus.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.reyco.testMybatisPlus.core.domain.User;
import com.reyco.testMybatisPlus.core.event.ReycoListener;
import com.reyco.testMybatisPlus.core.event.SimpleReycoEvent;

/** 
* @author reyco
* @date 2021年7月31日  
*/
@Service
public class ReycoEventLintener implements ReycoListener<SimpleReycoEvent>{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onReycoEvent(SimpleReycoEvent event) {
		Object source = event.getSource();
		String json = JSON.toJSONString(source);
		logger.debug("ReycoEventLintener=======logDefinition:"+json);
	}
	
	@EventListener
	public void test(User user) {
		System.out.println(user);
	}
}
