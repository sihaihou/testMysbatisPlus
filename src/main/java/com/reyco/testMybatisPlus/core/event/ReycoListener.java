/**
 * 
 */
package com.reyco.testMybatisPlus.core.event;

/**
 * 事件监听器
 * @author reyco
 * @date 2021年7月30日
 */
public interface ReycoListener<E extends ReycoEvent> extends java.util.EventListener{
	
	void onReycoEvent(E event);

}
