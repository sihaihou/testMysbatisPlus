/**
 * 
 */
package com.reyco.testMybatisPlus.core.event;

/**
 * 事件广播器
 * @author reyco
 * @date 2021年7月30日
 */
public interface ReycoEventMulticaster {
	
	/**
	 * 添加一个监听器
	 * @param listener
	 */
	void addReycoListener(ReycoListener<?> listener);
	/**
	 * 添加一个监听器Bean
	 * @param listenerBeanName
	 */
	void addReycoListenerBean(String listenerBeanName);
	/**
	 * 移除一个监听器
	 * @param listener
	 */
	void removeReycoListener(ReycoListener<?> listener);
	/**
	 * 移除一个监听器Bean
	 * @param listenerBeanName
	 */
	void removeReycoListenerBean(String listenerBeanName);
	/**
	 * 清空监听器
	 */
	void removeAllListeners();
	
	/**
	 * 广播事件
	 * @param event
	 */
	void multicastEvent(ReycoEvent event);
}
