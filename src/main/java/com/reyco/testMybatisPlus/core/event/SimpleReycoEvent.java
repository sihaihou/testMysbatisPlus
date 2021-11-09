/**
 * 
 */
package com.reyco.testMybatisPlus.core.event;

/** 
 * 默认事件类型
 * @author reyco
 * @date 2021年7月30日  
 */
public class SimpleReycoEvent extends ReycoEvent{
	private static final long serialVersionUID = -4631077100789239425L;
	
	private Object eventInfo;
	
	public SimpleReycoEvent(Object source) {
		super(source);
	}
	public SimpleReycoEvent(Object source,Object eventInfo) {
		super(source);
		this.eventInfo = eventInfo;
	}
	public Object getEventInfo() {
		return eventInfo;
	}
	public void setEventInfo(Object eventInfo) {
		this.eventInfo = eventInfo;
	}
}
