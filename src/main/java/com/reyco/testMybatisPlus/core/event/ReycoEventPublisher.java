/**
 * 
 */
package com.reyco.testMybatisPlus.core.event;

/**
 * 事件发布器
 * @author reyco
 * @date 2021年7月30日
 */
public interface ReycoEventPublisher {

	default void publishEvent(ReycoEvent event) {
		publishEvent((Object) event);
	}

	void publishEvent(Object event);

}
