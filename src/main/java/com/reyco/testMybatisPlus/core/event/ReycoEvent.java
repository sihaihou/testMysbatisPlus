/**
 * 
 */
package com.reyco.testMybatisPlus.core.event;

import java.util.EventObject;

/**
 * 事件
 * @author reyco
 * @date 2021年7月30日
 */
public abstract class ReycoEvent extends EventObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -12511161678532003L;

	public ReycoEvent(Object source) {
		super(source);
	}

	public String toString() {
		return getClass().getName() + "[source=" + source + "]";
	}
}
